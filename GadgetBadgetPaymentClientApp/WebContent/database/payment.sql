-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 06:35 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment`
--

-- --------------------------------------------------------

--
-- Table structure for table `carddetails`
--

CREATE TABLE `carddetails` (
  `paymentid` int(11) NOT NULL,
  `cardnumber` varchar(20) NOT NULL,
  `cvv` varchar(5) NOT NULL,
  `cardtype` varchar(10) NOT NULL,
  `expiredate` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `carddetails`
--

INSERT INTO `carddetails` (`paymentid`, `cardnumber`, `cvv`, `cardtype`, `expiredate`) VALUES
(1, '23454356765', '098', 'visa', '08/22'),
(9, '544657575', '223', 'Visa', '09/22'),
(10, '34r4574443r3', '222', 'Visa', '02/23'),
(11, '3373388888888', '788', 'Visa', '04/22'),
(12, '223335767', '111', 'Visa', '09/22'),
(12, '223335767', '111', 'Visa', '09/22'),
(13, '223335767', '111', 'Visa', '09/22'),
(13, '223335767', '111', 'Visa', '09/22'),
(14, '4546565757', '211', 'Visa', '08/23'),
(14, '4546565757', '211', 'Visa', '08/23'),
(15, '5676876555', '888', 'Visa', '09/22'),
(15, '5676876555', '888', 'Visa', '09/22'),
(16, '545464646565', '222', 'Visa', '09/23'),
(16, '545464646565', '222', 'Visa', '09/23'),
(17, '99997777666', '333', 'Visa', '09/23'),
(17, '99997777666', '333', 'Visa', '09/23'),
(18, '5666455444', '889', 'Master', '06/23'),
(18, '5666455444', '889', 'Master', '06/23');

-- --------------------------------------------------------

--
-- Table structure for table `paymentmain`
--

CREATE TABLE `paymentmain` (
  `paymentid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `orderid` int(11) NOT NULL,
  `researchid` int(11) NOT NULL,
  `fundingid` int(11) NOT NULL,
  `paymentmethod` varchar(20) NOT NULL,
  `paymenttime` datetime NOT NULL DEFAULT current_timestamp(),
  `purpose` varchar(200) NOT NULL,
  `statues` varchar(20) NOT NULL,
  `amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paymentmain`
--

INSERT INTO `paymentmain` (`paymentid`, `userid`, `orderid`, `researchid`, `fundingid`, `paymentmethod`, `paymenttime`, `purpose`, `statues`, `amount`) VALUES
(1, 100, 201, 11, 29, '\"card\"', '2021-04-23 06:42:29', '\"buying exsiting research\"', 'cancel', 78000),
(2, 2, 22, 4, 45, 'card', '2021-04-24 20:34:22', 'funding+a+research', 'cancel', 21001),
(3, 1, 3, 2, 5, 'card', '2021-05-08 12:01:50', 'aaaaa', 'paid', 23001),
(4, 1, 3, 2, 5, 'cash', '2021-05-08 12:01:54', 'buying a finger moveemnt research', 'paid', 34000),
(6, 3, 34, 12, 43, 'card', '2021-05-08 12:19:36', 'renting a project', 'cancel', 10000),
(7, 3, 23, 12, 33, 'Card', '2021-05-08 13:01:45', 'buynig a new project', 'paid', 22000),
(8, 8, 90, 66, 33, '\"Card\"', '2021-05-08 13:03:43', '\"hhfff\"', 'cancel', 120000),
(9, 12, 23, 22, 54, 'Card', '2021-05-14 16:36:57', 'wwww', 'paid', 2310),
(10, 121, 34, 32, 43, 'Card', '2021-05-14 16:41:32', 'dsjcndck', 'paid', 1200),
(11, 111, 21, 66, 67, 'Card', '2021-05-14 16:44:03', 'fbgnh', 'paid', 1000),
(12, 11, 23, 98, 22, 'Card', '2021-05-14 16:46:11', 'dcndcdn', 'paid', 200),
(13, 11, 23, 98, 22, 'Card', '2021-05-14 16:46:24', 'dcndcdn', 'paid', 200),
(14, 444, 43, 67, 88, 'Card', '2021-05-14 17:07:13', 'frgrtg', 'paid', 122),
(15, 32, 21, 12, 55, 'Card', '2021-05-14 17:10:15', 'bgnhmi', 'paid', 5999),
(16, 11, 77, 77, 5, 'Card', '2021-05-14 17:15:05', 'vffhf', 'paid', 334),
(17, 99, 87, 56, 89, 'Card', '2021-05-14 17:16:58', 'knlm', 'paid', 400),
(18, 44, 99, 8, 8, 'Card', '2021-05-14 17:19:48', 'ndhcbdc h', 'paid', 500),
(19, 5, 3, 33, 4, 'Card', '2021-05-14 17:23:29', 'vfbnfbnj', 'cancel', 300),
(20, 1111, 32, 32, 88, 'Card', '2021-05-14 20:40:23', 'aaaaa', 'cancel', 1256);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paymentmain`
--
ALTER TABLE `paymentmain`
  ADD PRIMARY KEY (`paymentid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `paymentmain`
--
ALTER TABLE `paymentmain`
  MODIFY `paymentid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
