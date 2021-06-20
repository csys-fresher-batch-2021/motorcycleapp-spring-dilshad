DROP TABLE motorcycleapp_admin_details;
CREATE TABLE motorcycleapp_admin_details(
id SERIAL UNIQUE,
admin_name VARCHAR(20) NOT NULL,
admin_id VARCHAR(15) NOT NULL PRIMARY  KEY,
admin_password VARCHAR(20) NOT NULL
);

SELECT * FROM motorcycleapp_admin_details;
INSERT INTO motorcycleapp_admin_details(admin_name, admin_id, admin_password)
VALUES ('Dilshad', 'dil123', 'Dilshad123');




--Views
CREATE OR REPLACE VIEW admin_vw AS
SELECT admin_name, admin_id, admin_password FROM admin_details;

SELECT * FROM admin_vw;