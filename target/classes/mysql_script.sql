CREATE DATABASE Business;

use Business;

CREATE TABLE CustomerInfo

(CourseName varchar(50),

PurchasedDate date,

Amount int(50),

Location varchar(50));



INSERT INTO CustomerInfo values("selenium",CURRENT_DATE(),120,'Africa');

INSERT INTO CustomerInfo values("Protractor",CURRENT_DATE(),45,'Africa');

INSERT INTO CustomerInfo values("Appium",CURRENT_DATE(),99,'Asia');

INSERT INTO CustomerInfo values("WebServices",CURRENT_DATE(),21,'Asia');

INSERT INTO CustomerInfo values("Jmeter",CURRENT_DATE(),76,'Asia');

INSERT INTO CustomerInfo values("Cypress",CURRENT_DATE(),76,'Argentina');

INSERT INTO CustomerInfo values("Detox",CURRENT_DATE(),76,'Argentina');

INSERT INTO CustomerInfo values("Postman",CURRENT_DATE(),76,'Argentina');

SELECT * From CustomerInfo WHERE location = "Argentina" AND PurchasedDate= CURDATE();