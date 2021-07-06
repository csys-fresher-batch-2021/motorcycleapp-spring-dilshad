
CREATE VIEW vw_bike_details AS 
SELECT bike_number, manufacturer_id, bm.manufacturer, model, color, price, odometer_reading, fuel_id, manufacture_year, added_date, market_status 
FROM motorcycle_details md 
INNER JOIN bike_manufacturer bm ON md.manufacturer_id = bm.id 
WHERE verification_status = true AND market_status != 'BOOKED';