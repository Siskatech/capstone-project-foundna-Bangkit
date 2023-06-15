const express = require('express');
const bodyParser = require('body-parser');
const routes = require('./src/routes');
const app = express();

const PORT = process.env.PORT || 4000; // Use the environment variable PORT or fallback to port 3000

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', (req, res) => {
  res.send('Server was Connected');
});

app.use('/api', routes);

app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).json({ error: 'Server error' });
});

app.listen(PORT, () => {
  console.log(`Server running at http://127.0.0.1:${PORT}`);
});
