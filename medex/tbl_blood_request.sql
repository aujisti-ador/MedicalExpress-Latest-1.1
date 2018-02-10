-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2018 at 04:15 PM
-- Server version: 10.1.24-MariaDB
-- PHP Version: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_blood_request`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_blood_request`
--

CREATE TABLE `tbl_blood_request` (
  `id` int(3) NOT NULL,
  `name` varchar(20) NOT NULL,
  `blood_group` varchar(5) NOT NULL,
  `place` text NOT NULL,
  `phone_number` varchar(16) NOT NULL,
  `date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_blood_request`
--

INSERT INTO `tbl_blood_request` (`id`, `name`, `blood_group`, `place`, `phone_number`, `date`) VALUES
(1, 'ador', 'o+', 'dhaka medical college', '+8801677577052', '2017-10-10'),
(2, 'mamun', 'ab+', 'care hospital', '01521101414', '2017-10-09'),
(3, 'nayeem', 'AB+', 'E&T Hospital,Dhanmondi. Dhaka', '01634479852', '2017-10-17'),
(4, 'android.support.v7.w', 'andro', 'android.support.v7.widget.AppCompatEditText{404d3e7 VFED..CL. ......I. 0,0-0,0 #7f0f0155 app:id/et_place}', 'android.support.', '0000-00-00'),
(5, 'android.support.v7.w', 'andro', 'android.support.v7.widget.AppCompatEditText{fe55c2c VFED..CL. ........ 30,382-589,473 #7f0f0155 app:id/et_place}', 'android.support.', '0000-00-00'),
(6, 'xxx', 'O+', 'Dhaka', '01923301616', '2017-10-02'),
(7, 'yyy', 'AB+', 'Djdjdhgahsjksshgshshsjshgsshhszhhzhzhzhzzhshhsjks', '019228282828', '0000-00-00'),
(8, '', '', '', '', '0000-00-00'),
(9, 'ffff', 'fff', 'ffff', 'ffffffff', '0000-00-00'),
(10, '', '', '', '', '0000-00-00'),
(11, '', '', '', '', '0000-00-00'),
(12, 'www', 'www', 'www', '0101010191', '2017-10-10'),
(13, 'sss', 'ssss', 'sssss', '01236666666', '0000-00-00'),
(14, 'aaaa', 'a', 'aaaaa', '01922722765', '1-1-2018'),
(15, 'zzz', 'zzz', 'zzz', '00000000', '12-31-2017'),
(16, 'gg', 'gg', 'hh', '000000', '11-18-2017'),
(17, 'lll', 'lll', 'llll', '0000', 'Jan-31-2018'),
(18, 'vvv', 'vvv', 'vvv', '000000', 'Nov-24-2017');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_blood_request`
--
ALTER TABLE `tbl_blood_request`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_blood_request`
--
ALTER TABLE `tbl_blood_request`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
