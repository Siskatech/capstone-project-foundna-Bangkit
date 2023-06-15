const admin = require('firebase-admin');

const serviceAccount = require('./serviceAccountKey.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

const auth = admin.auth();

const getUserId = (req) => {
  const authToken = req.headers.authorization;
  if (authToken && authToken.startsWith('Bearer ')) {
    const idToken = authToken.split('Bearer ')[1];
    return auth
      .verifyIdToken(idToken)
      .then((decodedToken) => decodedToken.uid)
      .catch((error) => {
        console.error('Error verifying Firebase ID token:', error);
        return null;
      });
  }

  return null;
};

const authenticateUser = (req, res, next) => {
  const userId = getUserId(req);

  if (userId) {
    next();
  } else {
    res.status(401).json({ message: 'Unauthorized' });
  }
};

module.exports = { admin, authenticateUser, getUserId };
