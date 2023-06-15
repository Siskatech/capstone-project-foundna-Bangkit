const mysql = require('mysql');

const connection = mysql.createConnection({
    host: '34.101.131.243',
    user: 'root',
    database: 'foundnadatabase',
    password: 'kijangkeren',
});


console.log(process.env.DB_HOST)

connection.connect(function(err) {
    if (err) {
        console.error('Error connecting: ' + err.stack);
        return;
    }
    console.log('Connected as thread id: ' + connection.threadId);
});

module.exports = connection;