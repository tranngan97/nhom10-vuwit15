-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 09, 2019 lúc 09:38 AM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlycuahang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `input_bill`
--

CREATE TABLE `input_bill` (
  `CODE_INPUT_BILL` varchar(20) NOT NULL,
  `CODE_STAFF` varchar(20) NOT NULL,
  `DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `input_bill`
--

INSERT INTO `input_bill` (`CODE_INPUT_BILL`, `CODE_STAFF`, `DATE`) VALUES
('PN-3126-6791', 'STF-8144-7663', '2019-01-01'),
('PN-5043-6027', 'STF-8144-7663', '2019-01-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `list_items`
--

CREATE TABLE `list_items` (
  `CODE_BILL` varchar(20) NOT NULL,
  `CODE_PRODUCT` varchar(20) NOT NULL,
  `CODE_SUPPLIER` varchar(20) NOT NULL,
  `CODE_TYPE` varchar(20) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `PRICE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `list_items`
--

INSERT INTO `list_items` (`CODE_BILL`, `CODE_PRODUCT`, `CODE_SUPPLIER`, `CODE_TYPE`, `QUANTITY`, `PRICE`) VALUES
('PN-3126-6791', 'PROD-8153-6353', 'SUPPL-2337-7648', 'TYP-4524-3176', 6, 10000000),
('PX-4446-0044', 'PROD-8153-6353', 'SUPPL-2337-7648', 'TYP-4524-3176', 4, 10000000),
('PX-9373-5070', 'PROD-8153-6353', 'SUPPL-2337-7648', 'TYP-4524-3176', 3, 10000000),
('PN-5043-6027', 'PROD-1910-0363', 'SUPPL-1757-9626', 'TYP-4095-7632', 3, 20000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `output_bill`
--

CREATE TABLE `output_bill` (
  `CODE_OUTPUT_BILL` varchar(20) NOT NULL,
  `CODE_STAFF` varchar(20) NOT NULL,
  `NAME_CUSTOMMER` varchar(50) NOT NULL,
  `DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `output_bill`
--

INSERT INTO `output_bill` (`CODE_OUTPUT_BILL`, `CODE_STAFF`, `NAME_CUSTOMMER`, `DATE`) VALUES
('PX-4446-0044', 'STF-8144-7663', 'adad', '2019-01-01'),
('PX-9373-5070', 'STF-8144-7663', 'bvvv', '2019-01-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `CODE_PRODUCT` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `CODE_SUPPLIER` varchar(20) NOT NULL,
  `CODE_TYPE` varchar(20) NOT NULL,
  `IMAGE` varchar(512) CHARACTER SET ascii NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`CODE_PRODUCT`, `NAME`, `CODE_SUPPLIER`, `CODE_TYPE`, `IMAGE`) VALUES
('PROD-0583-5733', 'SamSung Galaxy Tab E 9.6', 'SUPPL-9220-4141', 'TYP-9587-2395', 'product.png'),
('PROD-1693-1717', 'iphone 7', 'SUPPL-2337-7648', 'TYP-4524-3176', 'product.png'),
('PROD-1910-0363', 'laptop delll ins 3558', 'SUPPL-1757-9626', 'TYP-4095-7632', 'product.png'),
('PROD-2521-3957', 'IPad 2018 Cellular', 'SUPPL-2337-7648', 'TYP-9587-2395', 'product.png'),
('PROD-2592-9668', 'SamSung Galaxy Tab A Plus', 'SUPPL-9220-4141', 'TYP-9587-2395', 'product.png'),
('PROD-3038-3712', 'Dell Inspiron 3576', 'SUPPL-1757-9626', 'TYP-4095-7632', 'product.png'),
('PROD-3240-6434', 'SamSung Galaxy A9', 'SUPPL-9220-4141', 'TYP-4524-3176', 'product.png'),
('PROD-3488-6651', 'SamSung Galaxy A5', 'SUPPL-9220-4141', 'TYP-4524-3176', 'product.png'),
('PROD-4634-0381', 'SamSunng Galaxy J7', 'SUPPL-9220-4141', 'TYP-4524-3176', 'product.png'),
('PROD-4663-4571', 'IPad Pro ', 'SUPPL-2337-7648', 'TYP-9587-2395', 'product.png'),
('PROD-4796-2076', 'SamSung Galaxy J6', 'SUPPL-9220-4141', 'TYP-4524-3176', 'product.png'),
('PROD-4867-7450', 'IPad wifi ', 'SUPPL-2337-7648', 'TYP-9587-2395', 'product.png'),
('PROD-5304-4536', 'SamSung Galaxy Tab A6', 'SUPPL-9220-4141', 'TYP-9587-2395', 'product.png'),
('PROD-5554-9692', 'MacBook Air MREF2SA', 'SUPPL-2337-7648', 'TYP-4095-7632', 'product.png'),
('PROD-6808-9746', 'SamSung Galaxy A7', 'SUPPL-9220-4141', 'TYP-4524-3176', 'product.png'),
('PROD-7546-5799', 'iphone 7 Plus ', 'SUPPL-2337-7648', 'TYP-4524-3176', 'product.png'),
('PROD-8107-6897', 'Iphone X ', 'SUPPL-2337-7648', 'TYP-4524-3176', 'product.png'),
('PROD-8116-0777', 'Iphone 8', 'SUPPL-2337-7648', 'TYP-4524-3176', 'product.png'),
('PROD-8153-6353', 'iphone 6', 'SUPPL-2337-7648', 'TYP-4524-3176', 'product.png'),
('PROD-8296-3308', 'ASUS X507', 'SUPPL-2681-1576', 'TYP-4095-7632', 'product.png'),
('PROD-8875-2509', 'SamSung Galaxy Note 8', 'SUPPL-9220-4141', 'TYP-4524-3176', 'product.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roll_call`
--

CREATE TABLE `roll_call` (
  `CODE_STAFF` varchar(20) NOT NULL,
  `DATE` date DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `roll_call`
--

INSERT INTO `roll_call` (`CODE_STAFF`, `DATE`, `STATUS`) VALUES
('STF-8144-7663', '2019-04-09', 0),
('STF-1516-3448', '2019-04-09', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staffs`
--

CREATE TABLE `staffs` (
  `CODE_STAFF` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `PHONE_NUMBER` varchar(11) DEFAULT NULL,
  `IMAGE` varchar(512) CHARACTER SET ascii NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `staffs`
--

INSERT INTO `staffs` (`CODE_STAFF`, `NAME`, `DATE_OF_BIRTH`, `PHONE_NUMBER`, `IMAGE`) VALUES
('STF-1516-3448', 'nhân viên 2', '2019-01-01', '1234567891', 'profile.png'),
('STF-8144-7663', 'demo 1', '2019-01-01', '0134567889', 'profile.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `status`
--

CREATE TABLE `status` (
  `CODE_PRODUCT` varchar(20) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `PRICE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `status`
--

INSERT INTO `status` (`CODE_PRODUCT`, `QUANTITY`, `PRICE`) VALUES
('PROD-0583-5733', 0, 0),
('PROD-1693-1717', 50, 15000000),
('PROD-1910-0363', 3, 20000000),
('PROD-2521-3957', 0, 0),
('PROD-2592-9668', 0, 0),
('PROD-3038-3712', 0, 0),
('PROD-3240-6434', 0, 0),
('PROD-3488-6651', 0, 0),
('PROD-4634-0381', 0, 0),
('PROD-4663-4571', 0, 0),
('PROD-4796-2076', 0, 0),
('PROD-4867-7450', 0, 0),
('PROD-5304-4536', 0, 0),
('PROD-5554-9692', 0, 0),
('PROD-6808-9746', 50, 15000000),
('PROD-7546-5799', 0, 0),
('PROD-8107-6897', 0, 0),
('PROD-8116-0777', 0, 0),
('PROD-8153-6353', 13, 10000000),
('PROD-8296-3308', 1, 14000000),
('PROD-8875-2509', 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `CODE_SUPPLIER` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `COUNTRY` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `supplier`
--

INSERT INTO `supplier` (`CODE_SUPPLIER`, `NAME`, `COUNTRY`) VALUES
('SUPPL-1757-9626', 'dell', 'United States'),
('SUPPL-2337-7648', 'Apple', 'United States'),
('SUPPL-2681-1576', 'ASUS', 'United States'),
('SUPPL-5628-5720', 'Nokia', 'China'),
('SUPPL-8341-8452', 'MSI', 'United States'),
('SUPPL-8545-1365', 'HP', 'Vietnam'),
('SUPPL-9220-4141', 'SamSung', 'North Korea');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type_of_product`
--

CREATE TABLE `type_of_product` (
  `CODE_TYPE` varchar(20) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `UNIT` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `type_of_product`
--

INSERT INTO `type_of_product` (`CODE_TYPE`, `NAME`, `UNIT`) VALUES
('TYP-4095-7632', 'laptop', 'chiếc'),
('TYP-4524-3176', 'smartphone', 'chiếc'),
('TYP-9587-2395', 'Tablet', 'chiếc');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `input_bill`
--
ALTER TABLE `input_bill`
  ADD PRIMARY KEY (`CODE_INPUT_BILL`),
  ADD KEY `CODE_STAFF` (`CODE_STAFF`);

--
-- Chỉ mục cho bảng `list_items`
--
ALTER TABLE `list_items`
  ADD KEY `CODE_PRODUCT` (`CODE_PRODUCT`),
  ADD KEY `CODE_SUPPLIER` (`CODE_SUPPLIER`),
  ADD KEY `CODE_TYPE` (`CODE_TYPE`);

--
-- Chỉ mục cho bảng `output_bill`
--
ALTER TABLE `output_bill`
  ADD PRIMARY KEY (`CODE_OUTPUT_BILL`),
  ADD KEY `CODE_STAFF` (`CODE_STAFF`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`CODE_PRODUCT`),
  ADD KEY `CODE_SUPPLIER` (`CODE_SUPPLIER`),
  ADD KEY `CODE_TYPE` (`CODE_TYPE`);

--
-- Chỉ mục cho bảng `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`CODE_STAFF`);

--
-- Chỉ mục cho bảng `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`CODE_PRODUCT`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`CODE_SUPPLIER`);

--
-- Chỉ mục cho bảng `type_of_product`
--
ALTER TABLE `type_of_product`
  ADD PRIMARY KEY (`CODE_TYPE`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `input_bill`
--
ALTER TABLE `input_bill`
  ADD CONSTRAINT `input_bill_ibfk_1` FOREIGN KEY (`CODE_STAFF`) REFERENCES `staffs` (`CODE_STAFF`);

--
-- Các ràng buộc cho bảng `list_items`
--
ALTER TABLE `list_items`
  ADD CONSTRAINT `list_items_ibfk_1` FOREIGN KEY (`CODE_PRODUCT`) REFERENCES `products` (`CODE_PRODUCT`),
  ADD CONSTRAINT `list_items_ibfk_2` FOREIGN KEY (`CODE_SUPPLIER`) REFERENCES `supplier` (`CODE_SUPPLIER`),
  ADD CONSTRAINT `list_items_ibfk_3` FOREIGN KEY (`CODE_TYPE`) REFERENCES `type_of_product` (`CODE_TYPE`);

--
-- Các ràng buộc cho bảng `output_bill`
--
ALTER TABLE `output_bill`
  ADD CONSTRAINT `output_bill_ibfk_1` FOREIGN KEY (`CODE_STAFF`) REFERENCES `staffs` (`CODE_STAFF`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`CODE_SUPPLIER`) REFERENCES `supplier` (`CODE_SUPPLIER`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`CODE_TYPE`) REFERENCES `type_of_product` (`CODE_TYPE`);

--
-- Các ràng buộc cho bảng `status`
--
ALTER TABLE `status`
  ADD CONSTRAINT `status_ibfk_1` FOREIGN KEY (`CODE_PRODUCT`) REFERENCES `products` (`CODE_PRODUCT`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
