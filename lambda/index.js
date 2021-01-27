const mysql = require('mysql');

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
                // Kinesis data is base64 encoded so decode here
                var payload = JSON.parse(Buffer.from(record.kinesis.data, 'base64').toString('ascii'));
                const query = `INSERT INTO lab_data.info (event, device, submission_date) VALUE (${payload.event}, ${payload.device}, ${payload.timestamp});`;
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