CREATE DATABASE IF NOT EXISTS monitored_urls;

USE monitored_urls;

CREATE TABLE urls(
	URL varchar(255) NOT NULL,
	Min_response_time long,
	Max_response_time long,
	Monitoring_time long,
	Response_code int,
	Min_size int,
	Max_size int,

    PRIMARY KEY (URL)
);

INSERT INTO urls (URL, Min_response_time, Max_response_time, Monitoring_time, Response_code, Min_size, Max_size)
	VALUES ('https://www.youtube.com/', 0, 5, 400, 200, 20, 100);
INSERT INTO urls (URL, Min_response_time, Max_response_time, Monitoring_time, Response_code, Min_size, Max_size)
	VALUES ('https://www.i.ua/', 0, 5, 400, 200, 20, 100);
INSERT INTO urls (URL, Min_response_time, Max_response_time, Monitoring_time, Response_code, Min_size, Max_size)
	VALUES ('https://github.com/eddmorso', 0, 5, 400, 200, 20, 100);

