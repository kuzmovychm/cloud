const mysql = require('mysql');

const QUERY = 'SHOW DATABASES;';

const connection = mysql.createConnection({
    host: process.env.RDS_HOSTNAME,
    user: process.env.RDS_USERNAME,
    password: process.env.RDS_PASSWORD,
    port: process.env.RDS_PORT,

});

exports.handler = async (event) => {
    try {
        const data = await new Promise((resolve, reject) => {
            event.Records.forEach(record => {
                const { body } = record;
                const parsed = JSON.parse(body);
                const query = `INSERT INTO info (longitude, latitude, timestamp, data) VALUE (${parsed.longitude}, ${parsed.latitude}, ${parsed.timestamp}, ${parsed.data});`;
                connection.query(query, function (err, result) {
                    if (err) {
                        console.log("Error->" + err);
                        reject(err);
                    }
                    resolve(result);
                });
            });
        });

        return {
            statusCode: 200,
            body: JSON.stringify('Success')
        }
    } catch (err) {
        return {
            statusCode: 400,
            body: err.message
        }
    }
};