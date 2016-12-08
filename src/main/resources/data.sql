DROP TABLE Client IF EXISTS;
CREATE TABLE IF NOT EXISTS Client(id IDENTITY PRIMARY KEY, firstName VARCHAR(255), email VARCHAR(255), city VARCHAR(255), phoneNumber VARCHAR(255));
DELETE FROM Client;
INSERT INTO Client VALUES(1, 'Client One', 'email', 'city', '534534534534');
INSERT INTO Client VALUES(2, 'Client two', 'email', 'City', '534534534534');
INSERT INTO Client VALUES(3, 'Client three', 'email', 'City', '534534534534');