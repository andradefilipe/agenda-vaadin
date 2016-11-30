drop table Client if exists;
CREATE TABLE IF NOT EXISTS Client(id IDENTITY PRIMARY KEY, firstName VARCHAR(255), street VARCHAR(255), city VARCHAR(255), zipCode VARCHAR(255), phoneNumber VARCHAR(255), email VARCHAR(255), deleted BOOLEAN);
DELETE FROM Client;
INSERT INTO Client VALUES(1, 'Client One', 'Street', 'City', 'zipCode', 'phone', 'email', true);
INSERT INTO Client VALUES(2, 'Client two', 'Street', 'City', 'zipCode', 'phone', 'email', false);
INSERT INTO Client VALUES(3, 'Client two', 'Street', 'City', 'zipCode', 'phone', 'email', false);
