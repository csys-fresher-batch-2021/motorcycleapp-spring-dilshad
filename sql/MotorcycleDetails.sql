DROP TABLE motorcycle_details;
CREATE TABLE motorcycle_details(
id SERIAL PRIMARY KEY,
bike_number VARCHAR(14) UNIQUE NOT NULL,
manufacturer VARCHAR(30) NOT NULL,
model VARCHAR(30) NOT NULL,
color VARCHAR(30),
price FLOAT NOT NULL,
odometer_reading INTEGER NOT NULL,
fuel_type VARCHAR(20) NOT NULL,
manufacture_year INTEGER NOT NULL,
added_date DATE,
verification_status BOOLEAN NOT NULL,
market_status VARCHAR(20)
);

INSERT INTO motorcycle_details (bike_number, manufacturer, model, color, price, odometer_reading, fuel_type, manufacture_year, added_date, verification_status, market_status)
VALUES ('TN-23-VC-3490', 'HERO', 'Splendor', NULL, '22800', '67909', 'PETROL', 2003, '2021-06-25', 'false', 'AVAILABLE');

SELECT * FROM motorcycle_details;