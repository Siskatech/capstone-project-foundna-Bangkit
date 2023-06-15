const express = require('express');
const router = express.Router();
const { uploadImage } = require('./dataHandler');
const { getUserId } = require('./firebase');
const { getImageData } = require('./dataHandler')

router.post('/upload', async (req, res, next) => {
    try {
      await getUserId(req);
      next();
    } catch (error) {
      console.error('Error verifying Firebase ID token:', error);
      res.status(401).json({ error: 'Unauthorized' });
    }
}, uploadImage);

router.get('/image/:userId/:modelId/:imageName', getImageData);

module.exports = router;
