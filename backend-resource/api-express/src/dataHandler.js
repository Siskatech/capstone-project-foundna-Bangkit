const { Storage } = require('@google-cloud/storage');
const { admin, getUserId } = require('./firebase');
const database = require('./database');
const multer = require('multer');

// Configure Cloud Storage
const storage = new Storage();
const bucketName = 'foundna-app.appspot.com';

// Configure multer for file upload
const upload = multer().single('file');

// Configure the MySQL connection
const uploadImage = (req, res) => {
  upload(req, res, (err) => {
    if (err) {
      console.error('Error uploading file:', err);
      res.status(500).json({ error: 'Failed to upload file' });
      return;
    }

    const { originalname, buffer } = req.file;
    const { model_output } = req.body;

    // Verify the Firebase ID token
    admin.auth()
      .verifyIdToken(req.headers.authorization)
      .then((decodedToken) => {
        const userId = decodedToken.uid;

        const fileName = `${userId}_${Date.now()}_${originalname}`;
        const fileObj = storage.bucket(bucketName).file(fileName);

        fileObj.save(buffer, (err) => {
          if (err) {
            console.error('Error uploading image:', err);
            res.status(500).json({ error: 'Failed to upload image' });
          } else {
            const query = 'INSERT INTO photos (user_id, model_id, photo_name) VALUES (?, ?, ?)';
            database.query(query, [userId, model_output, fileName], (error) => {
              if (error) {
                console.error('Error inserting data into database: ', error);
                res.status(500).json({ error: 'Failed to insert data into database' });
              } else {
                res.status(200).json({ message: 'Image uploaded successfully' });
              }
            });
          }
        });
      })
      .catch((error) => {
        console.error('Error verifying Firebase ID token:', error);
        res.status(401).json({ error: 'Unauthorized' });
      });
  });
};

const getImageData = (req, res) => {
  const { userId, modelId, imageName } = req.params;

  // Perform database query to retrieve image data based on userId, modelId, and imageName
  // Replace the code below with your own logic to fetch the image data from the database
  const query = 'SELECT * FROM photos WHERE user_id = ? AND model_id = ? AND photo_name = ?';
  const values = [userId, modelId, imageName];

  database.query(query, values, (error, results) => {
    if (error) {
      console.error('Error retrieving image data:', error);
      res.status(500).json({ error: 'Failed to retrieve image data' });
    } else {
      if (results.length === 0) {
        res.status(404).json({ error: 'Image data not found' });
      } else {
        const imageData = results[0]; // Assuming only one row is returned
        res.status(200).json(imageData);
      }
    }
  });
};

module.exports = {
  uploadImage,
  getUserId,
  getImageData
};