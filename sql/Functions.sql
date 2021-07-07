-- Function to get current active asset
DECLARE current_asset Int;

BEGIN

SELECT SUM(price) INTO current_asset
FROM motorcycle_details
WHERE market_status != 'BOOKED' AND verification_status = 'true';

RETURN current_asset;
END;
