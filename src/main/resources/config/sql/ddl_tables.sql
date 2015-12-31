CREATE DATABASE IF NOT EXISTS AUTOMATE;

CREATE TABLE `BASIS_SUBSTANCE_TYPE` (
  `ID` varchar(64) NOT NULL,
  `TYPE_NAME` varchar(64) DEFAULT NULL,
  `DESCN` varchar(128) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DISP_INX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BASIS_ATTR` (
  `ID` varchar(64) NOT NULL,
  `ATTR_NAME` varchar(64) DEFAULT NULL,
  `ATTR_COLUMN` varchar(64) DEFAULT NULL,
  `ATTR_TYPE` varchar(64) DEFAULT NULL,
  `REQUIRED` varchar(64) DEFAULT NULL,
  `BASIS_SUBSTANCE_TYPE_ID` varchar(64) DEFAULT NULL,
  `DESCN` varchar(128) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DISP_INX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BASIS_SUBSTANCE` (
  `ID` varchar(64) NOT NULL,
  `BASIS_SUBSTANCE_TYPE_ID` varchar(64) DEFAULT NULL,
  `DESCN` varchar(128) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DISP_INX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BASIS_VALUE` (
  `ID` varchar(64) NOT NULL,
  `STRING_VALUE` varchar(64) DEFAULT NULL,
  `TEXT_VALUE` text,
  `DOUBLE_VALUE` double(12,4) DEFAULT NULL,
  `INT_VALUE` int(11) DEFAULT NULL,
  `DATE_VALUE` timestamp NULL DEFAULT NULL,
  `BASIS_ATTR_ID` varchar(64) DEFAULT NULL,
  `BASIS_SUBSTANCE_ID` varchar(64) DEFAULT NULL,
  `DESCN` varchar(128) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DISP_INX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE INDEX INDEX_BASIS_ATTR_ID ON BASIS_VALUE (BASIS_ATTR_ID);
CREATE INDEX INDEX_BASIS_SUBSTANCE_ID ON BASIS_VALUE (BASIS_SUBSTANCE_ID);
CREATE INDEX INDEX_BASIS_VALUE ON BASIS_VALUE (BASIS_ATTR_ID, BASIS_SUBSTANCE_ID);

CREATE TABLE SYS_VERSION(
`ID` VARCHAR(64) NOT NULL,
`VERSION` INT,
`DESCN` varchar(128) DEFAULT NULL,
`STATUS` varchar(64) DEFAULT NULL,
`CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
`MODIFY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
`DISP_INX` int(11) DEFAULT NULL,
PRIMARY KEY (`ID`)
);
CREATE TABLE SYS_LOGIN_(
)