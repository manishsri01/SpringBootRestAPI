-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.12 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table db_test.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table db_test.hibernate_sequence: 2 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(31),
	(31);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table db_test.table_message
CREATE TABLE IF NOT EXISTS `table_message` (
  `MSG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MSG_USR_ID` int(11) NOT NULL,
  `MSG_TEXT` text,
  PRIMARY KEY (`MSG_ID`),
  KEY `FK_MSG_USR_ID` (`MSG_USR_ID`),
  CONSTRAINT `FK_MSG_USR_ID` FOREIGN KEY (`MSG_USR_ID`) REFERENCES `table_user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- Dumping data for table db_test.table_message: ~1 rows (approximately)
/*!40000 ALTER TABLE `table_message` DISABLE KEYS */;
INSERT INTO `table_message` (`MSG_ID`, `MSG_USR_ID`, `MSG_TEXT`) VALUES
	(1, 1, 'test111'),
	(27, 1, 'test message..123'),
	(28, 1, 'test message..123'),
	(29, 1, 'test message..123'),
	(30, 1, 'test message..123');
/*!40000 ALTER TABLE `table_message` ENABLE KEYS */;

-- Dumping structure for table db_test.table_user
CREATE TABLE IF NOT EXISTS `table_user` (
  `USR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USR_NAME` varchar(50) NOT NULL,
  `USR_PSW` text NOT NULL,
  PRIMARY KEY (`USR_ID`),
  UNIQUE KEY `USR_NAME` (`USR_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- Dumping data for table db_test.table_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `table_user` DISABLE KEYS */;
INSERT INTO `table_user` (`USR_ID`, `USR_NAME`, `USR_PSW`) VALUES
	(1, 'user1', '$2a$10$.d3DheSdGTcbvvedqMHjKudG0mRq0Fcij2cEIhdnAMeFVU8BN1Ew2'),
	(2, 'user2', '$2a$10$.d3DheSdGTcbvvedqMHjKudG0mRq0Fcij2cEIhdnAMeFVU8BN1Ew2');
/*!40000 ALTER TABLE `table_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
