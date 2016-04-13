create database CRMSystemDB;

use CRMSystemDB;

CREATE TABLE `Users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userFullName` nvarchar(50) default null,
  `userAddress` nvarchar(255) default null,
  `userPhone` varchar(50) default null,
  `userEmail` varchar(50) default null,
  `dateCreated` date DEFAULT NULL,
  `loginName` varchar(50) default null,
  `loginPass` varchar(50) default null,
  `userRole` varchar(20) default null,
  `isActive` boolean default null,
  PRIMARY KEY (`userID`)
);

CREATE TABLE `Campaign` (
  `campaignID` int NOT NULL AUTO_INCREMENT,
  `campaignDescription` nvarchar(1024) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `budget` double DEFAULT NULL,
  `userID` int NOT NULL,
  `target` double DEFAULT NULL,
  PRIMARY KEY (`campaignID`),
  foreign key (`userID`) references `Users` (`userID`)
);

CREATE TABLE `Products` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `productName` nvarchar(50) DEFAULT NULL,
  `productDescription` nvarchar(1024) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `campaignID` int NOT NULL,
  PRIMARY KEY (`productID`),
  foreign key (`campaignID`) references `campaign` (`campaignID`)
);

CREATE TABLE `Leads` (
  `leadID` int NOT NULL AUTO_INCREMENT,
  `leadName` nvarchar(50) DEFAULT NULL,
  `leadDOB` date DEFAULT NULL,
  `leadAddress` nvarchar(255) DEFAULT NULL,
  `leadPhone` varchar(20) DEFAULT NULL,
  `leadCareer` nvarchar(50) DEFAULT NULL,
  `leadEmail` varchar(100) DEFAULT NULL,
  `dateCreated` date DEFAULT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`leadID`),
  foreign key (`userID`) references `Users` (`userID`)
);

CREATE TABLE `Opportunity` (
  `opportunityID` int NOT NULL AUTO_INCREMENT,
  `opportunityDate` date DEFAULT NULL,
  `leadID` int NOT NULL,
  `productID` int NOT NULL,
  `quantity` int default NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`opportunityID`),
  foreign key (`leadID`) references `Leads` (`leadID`),
  foreign key (`productID`) references `Products` (`productID`),
  foreign key (`userID`) references `Users` (`userID`)
);

CREATE TABLE `Orders` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL,
  `opportunityID` int NOT NULL,
  `paymentInfo` varchar(30) default null,
  `shippingInfo` varchar(30) default null,
  `quantity` int default NULL,
  `totalAmount` double DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  foreign key (`opportunityID`) references `Opportunity` (`opportunityID`)
);

CREATE TABLE `Follow` (
  `followID` int NOT NULL AUTO_INCREMENT,
  `followDate` date DEFAULT NULL,
  `opportunityID` int NOT NULL,
  `followContent` varchar(50) default null,
  `followResponse` nvarchar(255) default null,
  `followNextPlan` nvarchar(255) default null,
  `nextDate` date DEFAULT NULL,
  PRIMARY KEY (`followID`),
  foreign key (`opportunityID`) references `Opportunity` (`opportunityID`)
);


CREATE TABLE `Plan` (
  `planID` int NOT NULL AUTO_INCREMENT,
  `planYear` int(4) default null,
  `planName` nvarchar(255) default null,
  `planDescription` nvarchar(1024) default null,
  `userID` int NOT NULL,
  PRIMARY KEY (`planID`),
  foreign key (`userID`) references `Users` (`userID`)
);

CREATE TABLE `Month` (
  `monthID` int NOT NULL AUTO_INCREMENT,
  `month` int(2) default null,
  `value` double default null,
  `planID` int NOT NULL,
  PRIMARY KEY (`monthID`),
  foreign key (`planID`) references `Plan` (`planID`)
);

CREATE TABLE `EmailKey` (
	`keyID` int,
  `userEmail` varchar(50),
  PRIMARY KEY (`keyID`)
);

insert into users values (1 ,'admin', '123abc', '0123456789', 'abc@gmail.com', '2000/10/10', 'admin', '123456', 'administrator', 1);
