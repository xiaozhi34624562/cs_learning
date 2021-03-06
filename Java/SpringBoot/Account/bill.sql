/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.27 : Database - bill-manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bill-manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bill-manager`;

/*Table structure for table `bill_` */

DROP TABLE IF EXISTS `bill_`;

CREATE TABLE `bill_` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `title_` varchar(100) DEFAULT NULL,
  `bill_time_` date DEFAULT NULL,
  `type_id_` bigint(20) DEFAULT NULL,
  `price_` double(10,2) DEFAULT NULL,
  `explain_` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `fk_type_bill` (`type_id_`),
  CONSTRAINT `fk_type_bill` FOREIGN KEY (`type_id_`) REFERENCES `bill_type_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=505 DEFAULT CHARSET=utf8;

/*Data for the table `bill_` */

insert  into `bill_`(`id_`,`title_`,`bill_time_`,`type_id_`,`price_`,`explain_`) values 
(1,'交通费','2017-06-26',1,1.23,'a'),
(2,'饭补','2017-06-27',1,2.23,'b'),
(3,'出差费','2017-06-28',2,33.00,'c'),
(4,'奖金','2017-06-29',2,44.00,'d'),
(20,'1','2018-10-18',2,1.00,NULL),
(21,'采购办公用品2222','2019-05-29',3,666.00,'888'),
(23,'as',NULL,NULL,NULL,NULL),
(24,'f',NULL,NULL,NULL,NULL),
(25,'asdf',NULL,NULL,NULL,NULL),
(26,'as',NULL,NULL,NULL,NULL),
(27,'f',NULL,NULL,NULL,NULL),
(28,'asdf',NULL,NULL,NULL,NULL),
(29,'as',NULL,NULL,NULL,NULL),
(30,'df',NULL,NULL,NULL,NULL),
(31,'as',NULL,NULL,NULL,NULL),
(32,'r',NULL,NULL,NULL,NULL),
(33,'w',NULL,NULL,NULL,NULL),
(34,'a',NULL,NULL,NULL,NULL),
(35,'df',NULL,NULL,NULL,NULL),
(36,'aw',NULL,NULL,NULL,NULL),
(37,'er',NULL,NULL,NULL,NULL),
(38,'as',NULL,NULL,NULL,NULL),
(39,'df',NULL,NULL,NULL,NULL),
(40,'asd',NULL,NULL,NULL,NULL),
(41,'f',NULL,NULL,NULL,NULL),
(42,'wae',NULL,NULL,NULL,NULL),
(43,'asd',NULL,NULL,NULL,NULL),
(44,'as',NULL,NULL,NULL,NULL),
(45,'df',NULL,NULL,NULL,NULL),
(46,'as',NULL,NULL,NULL,NULL),
(47,'f',NULL,NULL,NULL,NULL),
(48,'w',NULL,NULL,NULL,NULL),
(49,'r',NULL,NULL,NULL,NULL),
(50,'asd',NULL,NULL,NULL,NULL),
(51,'f',NULL,NULL,NULL,NULL),
(52,'asd',NULL,NULL,NULL,NULL),
(53,'awse',NULL,NULL,NULL,NULL),
(54,'r',NULL,NULL,NULL,NULL),
(55,'qer',NULL,NULL,NULL,NULL),
(56,'as',NULL,NULL,NULL,NULL),
(57,'df',NULL,NULL,NULL,NULL),
(58,'as',NULL,NULL,NULL,NULL),
(59,'f',NULL,NULL,NULL,NULL),
(60,'awse',NULL,NULL,NULL,NULL),
(61,'asdf',NULL,NULL,NULL,NULL),
(62,'as',NULL,NULL,NULL,NULL),
(63,'f',NULL,NULL,NULL,NULL),
(64,'sdf',NULL,NULL,NULL,NULL),
(65,'asd',NULL,NULL,NULL,NULL),
(66,'as',NULL,NULL,NULL,NULL),
(67,'df',NULL,NULL,NULL,NULL),
(68,'asdf',NULL,NULL,NULL,NULL),
(69,'as',NULL,NULL,NULL,NULL),
(70,'df',NULL,NULL,NULL,NULL),
(71,'asdf',NULL,NULL,NULL,NULL),
(72,'as',NULL,NULL,NULL,NULL),
(73,'df',NULL,NULL,NULL,NULL),
(74,'sdf',NULL,NULL,NULL,NULL),
(75,'as',NULL,NULL,NULL,NULL),
(76,'df',NULL,NULL,NULL,NULL),
(77,'asdf',NULL,NULL,NULL,NULL),
(78,'as',NULL,NULL,NULL,NULL),
(79,'df',NULL,NULL,NULL,NULL),
(80,'asdf',NULL,NULL,NULL,NULL),
(81,'asd',NULL,NULL,NULL,NULL),
(82,'a',NULL,NULL,NULL,NULL),
(83,'df',NULL,NULL,NULL,NULL),
(84,'asdf',NULL,NULL,NULL,NULL),
(85,'a',NULL,NULL,NULL,NULL),
(86,'sdf',NULL,NULL,NULL,NULL),
(87,'as',NULL,NULL,NULL,NULL),
(88,'f',NULL,NULL,NULL,NULL),
(89,'asd',NULL,NULL,NULL,NULL),
(90,'f',NULL,NULL,NULL,NULL),
(91,'awer',NULL,NULL,NULL,NULL),
(92,'abcdefg','2019-05-22',3,666.00,'1234'),
(93,'采购办公用品','2019-05-22',4,666.00,'采购办公用品'),
(94,'采购办公用品','2019-05-22',4,666.00,'采购办公用品'),
(95,'采购办公用品','2019-05-22',3,555.00,'222'),
(96,'采购办公用品','2019-05-22',2,666.00,'采购办公用品'),
(97,'采购办公用品','2019-05-22',2,222.00,'1234'),
(98,'采购办公用品','2019-12-25',2,666.00,'采购办公用品'),
(99,'a','2020-06-10',2,22.00,'1234'),
(100,'采购椅子','2020-06-17',2,200.00,'采购办公用品'),
(101,'asd',NULL,NULL,NULL,NULL),
(102,'as',NULL,NULL,NULL,NULL),
(103,'df',NULL,NULL,NULL,NULL),
(104,'asdf',NULL,NULL,NULL,NULL),
(105,'as',NULL,NULL,NULL,NULL),
(106,'df',NULL,NULL,NULL,NULL),
(107,'aw',NULL,NULL,NULL,NULL),
(108,'r',NULL,NULL,NULL,NULL),
(109,'asd',NULL,NULL,NULL,NULL),
(110,'f',NULL,NULL,NULL,NULL),
(111,'sdf',NULL,NULL,NULL,NULL),
(112,'as',NULL,NULL,NULL,NULL),
(113,'df',NULL,NULL,NULL,NULL),
(114,'sdf',NULL,NULL,NULL,NULL),
(115,'as',NULL,NULL,NULL,NULL),
(116,'f',NULL,NULL,NULL,NULL),
(117,'asd',NULL,NULL,NULL,NULL),
(118,'a',NULL,NULL,NULL,NULL),
(119,'wsd',NULL,NULL,NULL,NULL),
(120,'as',NULL,NULL,NULL,NULL),
(121,'f',NULL,NULL,NULL,NULL),
(122,'asdf',NULL,NULL,NULL,NULL),
(123,'a',NULL,NULL,NULL,NULL),
(124,'df',NULL,NULL,NULL,NULL),
(125,'asdf',NULL,NULL,NULL,NULL),
(126,'as',NULL,NULL,NULL,NULL),
(127,'df',NULL,NULL,NULL,NULL),
(128,'asdf',NULL,NULL,NULL,NULL),
(129,'as',NULL,NULL,NULL,NULL),
(130,'df',NULL,NULL,NULL,NULL),
(131,'asdf',NULL,NULL,NULL,NULL),
(132,'as',NULL,NULL,NULL,NULL),
(133,'f',NULL,NULL,NULL,NULL),
(134,'sdf',NULL,NULL,NULL,NULL),
(135,'asd',NULL,NULL,NULL,NULL),
(136,'f',NULL,NULL,NULL,NULL),
(137,'sdf',NULL,NULL,NULL,NULL),
(138,'asd',NULL,NULL,NULL,NULL),
(139,'a',NULL,NULL,NULL,NULL),
(140,'df',NULL,NULL,NULL,NULL),
(141,'asd',NULL,NULL,NULL,NULL),
(142,'a',NULL,NULL,NULL,NULL),
(143,'df',NULL,NULL,NULL,NULL),
(144,'asdf',NULL,NULL,NULL,NULL),
(145,'as',NULL,NULL,NULL,NULL),
(146,'f',NULL,NULL,NULL,NULL),
(147,'awer',NULL,NULL,NULL,NULL),
(148,'asdf',NULL,NULL,NULL,NULL),
(149,'as',NULL,NULL,NULL,NULL),
(150,'f',NULL,NULL,NULL,NULL),
(151,'asdf',NULL,NULL,NULL,NULL),
(152,'as',NULL,NULL,NULL,NULL),
(153,'df',NULL,NULL,NULL,NULL),
(154,'sdf',NULL,NULL,NULL,NULL),
(155,'asd',NULL,NULL,NULL,NULL),
(156,'f',NULL,NULL,NULL,NULL),
(157,'sdf',NULL,NULL,NULL,NULL),
(158,'asd',NULL,NULL,NULL,NULL),
(159,'f',NULL,NULL,NULL,NULL),
(160,'sdf',NULL,NULL,NULL,NULL),
(161,'as',NULL,NULL,NULL,NULL),
(162,'df',NULL,NULL,NULL,NULL),
(163,'asdf',NULL,NULL,NULL,NULL),
(164,'as',NULL,NULL,NULL,NULL),
(165,'df',NULL,NULL,NULL,NULL),
(166,'asdf',NULL,NULL,NULL,NULL),
(167,'as',NULL,NULL,NULL,NULL),
(168,'df',NULL,NULL,NULL,NULL),
(169,'asdf',NULL,NULL,NULL,NULL),
(170,'as',NULL,NULL,NULL,NULL),
(171,'df',NULL,NULL,NULL,NULL),
(172,'asdf',NULL,NULL,NULL,NULL),
(173,'a',NULL,NULL,NULL,NULL),
(174,'wsdr',NULL,NULL,NULL,NULL),
(175,'wae',NULL,NULL,NULL,NULL),
(176,'s',NULL,NULL,NULL,NULL),
(177,'df',NULL,NULL,NULL,NULL),
(178,'asd',NULL,NULL,NULL,NULL),
(179,'a',NULL,NULL,NULL,NULL),
(180,'df',NULL,NULL,NULL,NULL),
(181,'asd',NULL,NULL,NULL,NULL),
(182,'a',NULL,NULL,NULL,NULL),
(183,'df',NULL,NULL,NULL,NULL),
(184,'asd',NULL,NULL,NULL,NULL),
(185,'fa',NULL,NULL,NULL,NULL),
(186,'sdf',NULL,NULL,NULL,NULL),
(187,'asd',NULL,NULL,NULL,NULL),
(188,'fa',NULL,NULL,NULL,NULL),
(189,'df',NULL,NULL,NULL,NULL),
(190,'asd',NULL,NULL,NULL,NULL),
(191,'a',NULL,NULL,NULL,NULL),
(192,'df',NULL,NULL,NULL,NULL),
(193,'asdf',NULL,NULL,NULL,NULL),
(194,'as',NULL,NULL,NULL,NULL),
(195,'df',NULL,NULL,NULL,NULL),
(196,'sdf',NULL,NULL,NULL,NULL),
(197,'as',NULL,NULL,NULL,NULL),
(198,'df',NULL,NULL,NULL,NULL),
(199,'asdf',NULL,NULL,NULL,NULL),
(200,'as',NULL,NULL,NULL,NULL),
(201,'df',NULL,NULL,NULL,NULL),
(202,'asdf',NULL,NULL,NULL,NULL),
(203,'as',NULL,NULL,NULL,NULL),
(204,'df',NULL,NULL,NULL,NULL),
(205,'asdf',NULL,NULL,NULL,NULL),
(206,'as',NULL,NULL,NULL,NULL),
(207,'df',NULL,NULL,NULL,NULL),
(208,'asdf',NULL,NULL,NULL,NULL),
(209,'as',NULL,NULL,NULL,NULL),
(210,'df',NULL,NULL,NULL,NULL),
(211,'asd',NULL,NULL,NULL,NULL),
(212,'as',NULL,NULL,NULL,NULL),
(213,'f',NULL,NULL,NULL,NULL),
(214,'asdf',NULL,NULL,NULL,NULL),
(215,'as',NULL,NULL,NULL,NULL),
(216,'df',NULL,NULL,NULL,NULL),
(217,'asdf',NULL,NULL,NULL,NULL),
(218,'as',NULL,NULL,NULL,NULL),
(219,'f',NULL,NULL,NULL,NULL),
(220,'asd',NULL,NULL,NULL,NULL),
(221,'as',NULL,NULL,NULL,NULL),
(222,'fd',NULL,NULL,NULL,NULL),
(223,'asdf',NULL,NULL,NULL,NULL),
(224,'asd',NULL,NULL,NULL,NULL),
(225,'f',NULL,NULL,NULL,NULL),
(226,'df',NULL,NULL,NULL,NULL),
(227,'as',NULL,NULL,NULL,NULL),
(228,'f',NULL,NULL,NULL,NULL),
(229,'sdf',NULL,NULL,NULL,NULL),
(230,'as',NULL,NULL,NULL,NULL),
(231,'df',NULL,NULL,NULL,NULL),
(232,'asdf',NULL,NULL,NULL,NULL),
(233,'as',NULL,NULL,NULL,NULL),
(234,'df',NULL,NULL,NULL,NULL),
(235,'asdf',NULL,NULL,NULL,NULL),
(236,'as',NULL,NULL,NULL,NULL),
(237,'df',NULL,NULL,NULL,NULL),
(238,'asd',NULL,NULL,NULL,NULL),
(239,'as',NULL,NULL,NULL,NULL),
(240,'f',NULL,NULL,NULL,NULL),
(241,'asdf',NULL,NULL,NULL,NULL),
(242,'as',NULL,NULL,NULL,NULL),
(243,'df',NULL,NULL,NULL,NULL),
(244,'asdf',NULL,NULL,NULL,NULL),
(245,'as',NULL,NULL,NULL,NULL),
(246,'df',NULL,NULL,NULL,NULL),
(247,'asdf',NULL,NULL,NULL,NULL),
(248,'a',NULL,NULL,NULL,NULL),
(249,'df',NULL,NULL,NULL,NULL),
(250,'asfd',NULL,NULL,NULL,NULL),
(251,'as',NULL,NULL,NULL,NULL),
(252,'f',NULL,NULL,NULL,NULL),
(253,'asdf',NULL,NULL,NULL,NULL),
(254,'as',NULL,NULL,NULL,NULL),
(255,'df',NULL,NULL,NULL,NULL),
(256,'asd',NULL,NULL,NULL,NULL),
(257,'as',NULL,NULL,NULL,NULL),
(258,'df',NULL,NULL,NULL,NULL),
(259,'as',NULL,NULL,NULL,NULL),
(260,'fa',NULL,NULL,NULL,NULL),
(261,'df',NULL,NULL,NULL,NULL),
(262,'as',NULL,NULL,NULL,NULL),
(263,'f',NULL,NULL,NULL,NULL),
(264,'asdf',NULL,NULL,NULL,NULL),
(265,'as',NULL,NULL,NULL,NULL),
(266,'df',NULL,NULL,NULL,NULL),
(267,'as',NULL,NULL,NULL,NULL),
(268,'fas',NULL,NULL,NULL,NULL),
(269,'f',NULL,NULL,NULL,NULL),
(270,'as',NULL,NULL,NULL,NULL),
(271,'df',NULL,NULL,NULL,NULL),
(272,'asdf',NULL,NULL,NULL,NULL),
(273,'a',NULL,NULL,NULL,NULL),
(274,'sdf',NULL,NULL,NULL,NULL),
(275,'as',NULL,NULL,NULL,NULL),
(276,'df',NULL,NULL,NULL,NULL),
(277,'asd',NULL,NULL,NULL,NULL),
(278,'a',NULL,NULL,NULL,NULL),
(279,'df',NULL,NULL,NULL,NULL),
(280,'asd',NULL,NULL,NULL,NULL),
(281,'a',NULL,NULL,NULL,NULL),
(282,'df',NULL,NULL,NULL,NULL),
(283,'as',NULL,NULL,NULL,NULL),
(284,'fa',NULL,NULL,NULL,NULL),
(285,'df',NULL,NULL,NULL,NULL),
(286,'as',NULL,NULL,NULL,NULL),
(287,'df',NULL,NULL,NULL,NULL),
(288,'asdf',NULL,NULL,NULL,NULL),
(289,'as',NULL,NULL,NULL,NULL),
(290,'f',NULL,NULL,NULL,NULL),
(291,'sdf',NULL,NULL,NULL,NULL),
(292,'as',NULL,NULL,NULL,NULL),
(293,'f',NULL,NULL,NULL,NULL),
(294,'awe',NULL,NULL,NULL,NULL),
(295,'r',NULL,NULL,NULL,NULL),
(296,'sd',NULL,NULL,NULL,NULL),
(297,'a',NULL,NULL,NULL,NULL),
(298,'df',NULL,NULL,NULL,NULL),
(299,'asd',NULL,NULL,NULL,NULL),
(300,'f',NULL,NULL,NULL,NULL),
(301,'sd',NULL,NULL,NULL,NULL),
(302,'fa',NULL,NULL,NULL,NULL),
(303,'sd',NULL,NULL,NULL,NULL),
(304,'r',NULL,NULL,NULL,NULL),
(305,'qwae',NULL,NULL,NULL,NULL),
(306,'as',NULL,NULL,NULL,NULL),
(307,'df',NULL,NULL,NULL,NULL),
(308,'as',NULL,NULL,NULL,NULL),
(309,'f',NULL,NULL,NULL,NULL),
(310,'asdf',NULL,NULL,NULL,NULL),
(311,'asd',NULL,NULL,NULL,NULL),
(312,'f',NULL,NULL,NULL,NULL),
(313,'sdf',NULL,NULL,NULL,NULL),
(314,'as',NULL,NULL,NULL,NULL),
(315,'fa',NULL,NULL,NULL,NULL),
(316,'df',NULL,NULL,NULL,NULL),
(317,'as',NULL,NULL,NULL,NULL),
(318,'f',NULL,NULL,NULL,NULL),
(319,'asd',NULL,NULL,NULL,NULL),
(320,'fas',NULL,NULL,NULL,NULL),
(321,'f',NULL,NULL,NULL,NULL),
(322,'asdf',NULL,NULL,NULL,NULL),
(323,'as',NULL,NULL,NULL,NULL),
(324,'f',NULL,NULL,NULL,NULL),
(325,'asd',NULL,NULL,NULL,NULL),
(326,'fa',NULL,NULL,NULL,NULL),
(327,'df',NULL,NULL,NULL,NULL),
(328,'asf',NULL,NULL,NULL,NULL),
(329,'a',NULL,NULL,NULL,NULL),
(330,'df',NULL,NULL,NULL,NULL),
(331,'asd',NULL,NULL,NULL,NULL),
(332,'f',NULL,NULL,NULL,NULL),
(333,'sdf',NULL,NULL,NULL,NULL),
(334,'asd',NULL,NULL,NULL,NULL),
(335,'a',NULL,NULL,NULL,NULL),
(336,'df',NULL,NULL,NULL,NULL),
(337,'asdf',NULL,NULL,NULL,NULL),
(338,'as',NULL,NULL,NULL,NULL),
(339,'fa',NULL,NULL,NULL,NULL),
(340,'f',NULL,NULL,NULL,NULL),
(341,'as',NULL,NULL,NULL,NULL),
(342,'f',NULL,NULL,NULL,NULL),
(343,'asdf',NULL,NULL,NULL,NULL),
(344,'asd',NULL,NULL,NULL,NULL),
(345,'f',NULL,NULL,NULL,NULL),
(346,'asdf',NULL,NULL,NULL,NULL),
(347,'as',NULL,NULL,NULL,NULL),
(348,'fd',NULL,NULL,NULL,NULL),
(349,'asd',NULL,NULL,NULL,NULL),
(350,'as',NULL,NULL,NULL,NULL),
(351,'df',NULL,NULL,NULL,NULL),
(352,'asdf',NULL,NULL,NULL,NULL),
(353,'as',NULL,NULL,NULL,NULL),
(354,'df',NULL,NULL,NULL,NULL),
(355,'asd',NULL,NULL,NULL,NULL),
(356,'fa',NULL,NULL,NULL,NULL),
(357,'sd',NULL,NULL,NULL,NULL),
(358,'asd',NULL,NULL,NULL,NULL),
(359,'f',NULL,NULL,NULL,NULL),
(360,'f',NULL,NULL,NULL,NULL),
(361,'asd',NULL,NULL,NULL,NULL),
(362,'a',NULL,NULL,NULL,NULL),
(363,'sdf',NULL,NULL,NULL,NULL),
(364,'as',NULL,NULL,NULL,NULL),
(365,'f',NULL,NULL,NULL,NULL),
(366,'sdf',NULL,NULL,NULL,NULL),
(367,'sa',NULL,NULL,NULL,NULL),
(368,'fdasfd',NULL,NULL,NULL,NULL),
(369,'as',NULL,NULL,NULL,NULL),
(370,'f',NULL,NULL,NULL,NULL),
(371,'asdf',NULL,NULL,NULL,NULL),
(372,'as',NULL,NULL,NULL,NULL),
(373,'d',NULL,NULL,NULL,NULL),
(374,'f',NULL,NULL,NULL,NULL),
(375,'asdf',NULL,NULL,NULL,NULL),
(376,'asdf',NULL,NULL,NULL,NULL),
(377,'asd',NULL,NULL,NULL,NULL),
(378,'f',NULL,NULL,NULL,NULL),
(379,'asdf',NULL,NULL,NULL,NULL),
(380,'as',NULL,NULL,NULL,NULL),
(381,'fa',NULL,NULL,NULL,NULL),
(382,'df',NULL,NULL,NULL,NULL),
(383,'asd',NULL,NULL,NULL,NULL),
(384,'a',NULL,NULL,NULL,NULL),
(385,'df',NULL,NULL,NULL,NULL),
(386,'asdf',NULL,NULL,NULL,NULL),
(387,'as',NULL,NULL,NULL,NULL),
(388,'df',NULL,NULL,NULL,NULL),
(389,'asdf',NULL,NULL,NULL,NULL),
(390,'as',NULL,NULL,NULL,NULL),
(391,'df',NULL,NULL,NULL,NULL),
(392,'asdf',NULL,NULL,NULL,NULL),
(393,'as',NULL,NULL,NULL,NULL),
(394,'df',NULL,NULL,NULL,NULL),
(395,'sadf',NULL,NULL,NULL,NULL),
(396,'as',NULL,NULL,NULL,NULL),
(397,'f',NULL,NULL,NULL,NULL),
(398,'sdf',NULL,NULL,NULL,NULL),
(399,'asd',NULL,NULL,NULL,NULL),
(400,'as',NULL,NULL,NULL,NULL),
(401,'fd',NULL,NULL,NULL,NULL),
(402,'asdf',NULL,NULL,NULL,NULL),
(403,'as',NULL,NULL,NULL,NULL),
(404,'f',NULL,NULL,NULL,NULL),
(405,'df',NULL,NULL,NULL,NULL),
(406,'asdf',NULL,NULL,NULL,NULL),
(407,'as',NULL,NULL,NULL,NULL),
(408,'df',NULL,NULL,NULL,NULL),
(409,'asdf',NULL,NULL,NULL,NULL),
(410,'asd',NULL,NULL,NULL,NULL),
(411,'f',NULL,NULL,NULL,NULL),
(412,'asdf',NULL,NULL,NULL,NULL),
(413,'as',NULL,NULL,NULL,NULL),
(414,'f',NULL,NULL,NULL,NULL),
(415,'sdf',NULL,NULL,NULL,NULL),
(416,'asd',NULL,NULL,NULL,NULL),
(417,'f',NULL,NULL,NULL,NULL),
(418,'asdf',NULL,NULL,NULL,NULL),
(419,'asdf',NULL,NULL,NULL,NULL),
(420,'as',NULL,NULL,NULL,NULL),
(421,'f',NULL,NULL,NULL,NULL),
(422,'asdf',NULL,NULL,NULL,NULL),
(423,'asd',NULL,NULL,NULL,NULL),
(424,'f',NULL,NULL,NULL,NULL),
(425,'asfd',NULL,NULL,NULL,NULL),
(426,'as',NULL,NULL,NULL,NULL),
(427,'f',NULL,NULL,NULL,NULL),
(428,'asdf',NULL,NULL,NULL,NULL),
(429,'asd',NULL,NULL,NULL,NULL),
(430,'f',NULL,NULL,NULL,NULL),
(431,'asdf',NULL,NULL,NULL,NULL),
(432,'asd',NULL,NULL,NULL,NULL),
(433,'f',NULL,NULL,NULL,NULL),
(434,'df',NULL,NULL,NULL,NULL),
(435,'asd',NULL,NULL,NULL,NULL),
(436,'f',NULL,NULL,NULL,NULL),
(437,'sdf',NULL,NULL,NULL,NULL),
(438,'asdf',NULL,NULL,NULL,NULL),
(439,'as',NULL,NULL,NULL,NULL),
(440,'f',NULL,NULL,NULL,NULL),
(441,'asdf',NULL,NULL,NULL,NULL),
(442,'sad',NULL,NULL,NULL,NULL),
(443,'asdf',NULL,NULL,NULL,NULL),
(444,'as',NULL,NULL,NULL,NULL),
(445,'df',NULL,NULL,NULL,NULL),
(446,'asdf',NULL,NULL,NULL,NULL),
(447,'asd',NULL,NULL,NULL,NULL),
(448,'fa',NULL,NULL,NULL,NULL),
(449,'df',NULL,NULL,NULL,NULL),
(450,'asdf',NULL,NULL,NULL,NULL),
(451,'sadf',NULL,NULL,NULL,NULL),
(452,'asd',NULL,NULL,NULL,NULL),
(453,'fa',NULL,NULL,NULL,NULL),
(454,'df',NULL,NULL,NULL,NULL),
(455,'asdf',NULL,NULL,NULL,NULL),
(456,'asdf',NULL,NULL,NULL,NULL),
(457,'asdf',NULL,NULL,NULL,NULL),
(458,'asdf',NULL,NULL,NULL,NULL),
(459,'asdf',NULL,NULL,NULL,NULL),
(460,'asdf',NULL,NULL,NULL,NULL),
(461,'as',NULL,NULL,NULL,NULL),
(462,'df',NULL,NULL,NULL,NULL),
(463,'asdf',NULL,NULL,NULL,NULL),
(464,'asd',NULL,NULL,NULL,NULL),
(465,'fas',NULL,NULL,NULL,NULL),
(466,'f',NULL,NULL,NULL,NULL),
(467,'asdf',NULL,NULL,NULL,NULL),
(468,'asdf',NULL,NULL,NULL,NULL),
(469,'asd',NULL,NULL,NULL,NULL),
(470,'a',NULL,NULL,NULL,NULL),
(471,'df',NULL,NULL,NULL,NULL),
(472,'asdf',NULL,NULL,NULL,NULL),
(473,'a',NULL,NULL,NULL,NULL),
(474,'df',NULL,NULL,NULL,NULL),
(475,'asdf',NULL,NULL,NULL,NULL),
(476,'asd',NULL,NULL,NULL,NULL),
(477,'f',NULL,NULL,NULL,NULL),
(478,'asdf',NULL,NULL,NULL,NULL),
(479,'asd',NULL,NULL,NULL,NULL),
(480,'asdf',NULL,NULL,NULL,NULL),
(481,'asd',NULL,NULL,NULL,NULL),
(482,'f',NULL,NULL,NULL,NULL),
(483,'asdf',NULL,NULL,NULL,NULL),
(484,'asd',NULL,NULL,NULL,NULL),
(485,'f',NULL,NULL,NULL,NULL),
(486,'sdf',NULL,NULL,NULL,NULL),
(487,'asd',NULL,NULL,NULL,NULL),
(488,'f',NULL,NULL,NULL,NULL),
(489,'sadf',NULL,NULL,NULL,NULL),
(490,'asd',NULL,NULL,NULL,NULL),
(491,'f',NULL,NULL,NULL,NULL),
(492,'asdf',NULL,NULL,NULL,NULL),
(493,'asd',NULL,NULL,NULL,NULL),
(494,'a',NULL,NULL,NULL,NULL),
(495,'df',NULL,NULL,NULL,NULL),
(496,'asdf',NULL,NULL,NULL,NULL),
(497,'as',NULL,NULL,NULL,NULL),
(498,'f',NULL,NULL,NULL,NULL),
(499,'asdf',NULL,NULL,NULL,NULL),
(500,'asd',NULL,NULL,NULL,NULL),
(501,'f',NULL,NULL,NULL,NULL),
(502,'asdf',NULL,NULL,NULL,NULL),
(504,'借给小美4000元','2020-07-01',3,4000.00,'不用还');

/*Table structure for table `bill_type_` */

DROP TABLE IF EXISTS `bill_type_`;

CREATE TABLE `bill_type_` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `bill_type_` */

insert  into `bill_type_`(`id_`,`name_`) values 
(1,'收入'),
(2,'支出'),
(3,'借入'),
(4,'借出');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
