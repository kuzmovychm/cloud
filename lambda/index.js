const mysql = require('mysql');

const QUERY = 'SHOW DATABASES;';

const connection = mysql.createConnection({
    host: 'flightdb.cdmh7o8qmr8i.us-east-2.rds.amazonaws.com',
    user: 'admin',
    password: 'Ghs27hYsdj12',
    port: '3306',
});

exports.handler = async (event) => {
    console.log(event);
    try {
        const data = await new Promise((resolve, reject) => {
            event.Records.forEach(record => {
                // Kinesis data is base64 encoded so decode here
                var payload = JSON.parse(Buffer.from(record.kinesis.data, 'base64').toString('ascii'));
                console.log('Iserting new value:', payload);
                const query = `INSERT INTO lab_data.info (event, device, submission_date) VALUE ('${payload.event}', '${payload.device}', '${payload.timestamp}');`;
                connection.query(query, function (err, result) {
                    if (err) {
                        console.log("Error:" + err);
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
        console.log('Error:', err);
        return {
            statusCode: 400,
            body: err.message
        }
    }
};