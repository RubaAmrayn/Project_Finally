-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 28 أبريل 2019 الساعة 00:40
-- إصدار الخادم: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mycode`
--
CREATE DATABASE IF NOT EXISTS `mycode` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mycode`;

-- --------------------------------------------------------

--
-- بنية الجدول `codes`
--

CREATE TABLE IF NOT EXISTS `codes` (
  `Code_id` varchar(50) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Content` text NOT NULL,
  `Code_desc` varchar(150) DEFAULT NULL,
  `Create_Date` datetime NOT NULL,
  `Language` varchar(20) NOT NULL,
  `permition` varchar(20) NOT NULL,
  `Users_user_id` int(10) NOT NULL,
  PRIMARY KEY (`Code_id`),
  KEY `Code_fk0` (`Users_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- إرجاع أو استيراد بيانات الجدول `codes`
--

INSERT INTO `codes` (`Code_id`, `Title`, `Content`, `Code_desc`, `Create_Date`, `Language`, `permition`, `Users_user_id`) VALUES
('122111', 'Javascript Arrays', 'let arr = []', NULL, '2019-02-19 00:00:00', 'JAVASCRIPT', 'Public', 1),
('123456', 'HTML Attribute', 'html tags', 'hhh', '2019-02-25 00:00:00', 'HTML', 'Public', 1);

-- --------------------------------------------------------

--
-- بنية الجدول `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `Group_id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Create_date` datetime NOT NULL,
  `Users_user_id` int(10) NOT NULL,
  PRIMARY KEY (`Group_id`),
  KEY `Groups_fk0` (`Users_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- إرجاع أو استيراد بيانات الجدول `groups`
--

INSERT INTO `groups` (`Group_id`, `Name`, `Create_date`, `Users_user_id`) VALUES
(1, 'OurCodes', '2019-02-24 10:17:25', 1),
(26, 'projects', '2019-04-27 23:49:26', 2),
(27, 'Just Last Test :(', '2019-04-27 23:50:23', 2),
(28, 'MyCodes', '2019-04-27 23:51:12', 2),
(29, 'JavaScript', '2019-04-28 00:33:15', 2);

-- --------------------------------------------------------

--
-- بنية الجدول `group_members`
--

CREATE TABLE IF NOT EXISTS `group_members` (
  `Users_User_id` int(10) NOT NULL,
  `Groups_Group_id` int(10) NOT NULL,
  `Enroll_Date` datetime DEFAULT NULL,
  KEY `Group_Members_fk0` (`Users_User_id`),
  KEY `Group_Members_fk1` (`Groups_Group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- إرجاع أو استيراد بيانات الجدول `group_members`
--

INSERT INTO `group_members` (`Users_User_id`, `Groups_Group_id`, `Enroll_Date`) VALUES
(1, 1, '2019-02-17 05:13:15');

-- --------------------------------------------------------

--
-- بنية الجدول `shared_code`
--

CREATE TABLE IF NOT EXISTS `shared_code` (
  `Codes_Code_id` varchar(50) NOT NULL,
  `Groups_Group_id` int(10) NOT NULL,
  `Share_Date` datetime NOT NULL,
  KEY `Shared_Code_fk0` (`Codes_Code_id`),
  KEY `Shared_Code_fk1` (`Groups_Group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- إرجاع أو استيراد بيانات الجدول `shared_code`
--

INSERT INTO `shared_code` (`Codes_Code_id`, `Groups_Group_id`, `Share_Date`) VALUES
('123456', 1, '2019-02-25 00:00:00'),
(' 4443', 1, '2019-02-24 00:00:00'),
('122111', 1, '2019-02-24 00:00:00'),
('34', 1, '2019-02-25 00:00:00');

-- --------------------------------------------------------

--
-- بنية الجدول `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(150) NOT NULL,
  `First_Name` varchar(150) NOT NULL,
  `Last_Name` varchar(150) NOT NULL,
  `Email` varchar(150) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- إرجاع أو استيراد بيانات الجدول `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `First_Name`, `Last_Name`, `Email`) VALUES
(1, 'Ruba', 'Ruba12345', 'Ruba', 'Amraim', 'ly.ly.123@hotmail.com'),
(3, 'rr', '123', 'null', 'R', 'lyll@hotmail.com'),
(8, 'Ruba', '123456', 'R', 'A', 's43700143@st.uqu.edu.sa'),
(9, 'Ruba', '123456', 'R', 'A', 's4370143@st.uqu.edu.sa'),
(13, 'admin', '123456', 'R', 'xcc', 'aaa'),
(14, 'qqq', '654', 'R', 't', 'uuu'),
(15, 'o', '123', 'y', 'u', 'h'),
(16, 'Ruba', '55', 'R', 't', 'y@'),
(17, 'Ruba', '12', 'R', 'A', 'r@'),
(18, 'Ruba', '22', 'R', 't', 'rr@'),
(19, 'Ruba', '123', 'R', 't', 'w@'),
(21, 'Ruba', '1w', 'R', 't', 'lyly123@hotmail.com'),
(22, 'Ruba', '12', 'RE', 'Ee', 'ly-ly-123@hotmail.com'),
(25, 'Ruba', '12', 'R', 't', 'ly-y-123@hotmail.com');

--
-- قيود الجداول المحفوظة
--

--
-- القيود للجدول `codes`
--
ALTER TABLE `codes`
  ADD CONSTRAINT `Code_fk0` FOREIGN KEY (`Users_user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
