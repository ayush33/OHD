/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - ohd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`ohd` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ohd`;

/*Table structure for table `block` */

DROP TABLE IF EXISTS `block`;

CREATE TABLE `block` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`id`,`name`) values (1,'admin block'),(2,'main block'),(3,'civil block');

/*Table structure for table `facilitator` */

DROP TABLE IF EXISTS `facilitator`;

CREATE TABLE `facilitator` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` char(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `facilitator` */

insert  into `facilitator`(`id`,`name`,`email`,`phone`,`password`) values (12345,'pk sharma','talentayush@gmail.com','8946992225','pksharma'),(54321,'ak jain','talentayush@gmail.com','7737819430','akjain'),(56789,'am madaan','madaan1996@gmail.com','7894561238','ammadaan');

/*Table structure for table `facilities` */

DROP TABLE IF EXISTS `facilities`;

CREATE TABLE `facilities` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `facilities` */

insert  into `facilities`(`id`,`name`) values (1,'fan'),(2,'tables'),(3,'computer'),(4,'tubelight');

/*Table structure for table `providers` */

DROP TABLE IF EXISTS `providers`;

CREATE TABLE `providers` (
  `facilitator_id` int(11) default NULL,
  `facility_id` int(11) default NULL,
  `block_id` int(11) default NULL,
  KEY `block_id` (`block_id`),
  KEY `facilitator_id` (`facilitator_id`),
  KEY `facility_id` (`facility_id`),
  CONSTRAINT `providers_ibfk_1` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`),
  CONSTRAINT `providers_ibfk_2` FOREIGN KEY (`facilitator_id`) REFERENCES `facilitator` (`id`),
  CONSTRAINT `providers_ibfk_3` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `providers` */

insert  into `providers`(`facilitator_id`,`facility_id`,`block_id`) values (54321,1,2),(12345,2,3),(56789,3,2),(54321,4,2),(12345,2,2);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` varchar(11) NOT NULL,
  `block_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `block_id` (`block_id`),
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `room` */

insert  into `room`(`id`,`block_id`) values ('101',2),('102',2),('103',2),('104',2),('cl-10',2),('3f1',3),('3f2',3),('3f3',3),('3f4',3);

/*Table structure for table `session` */

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `id` int(7) default NULL,
  `r_no` int(11) default NULL,
  `sess` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `session` */

insert  into `session`(`id`,`r_no`,`sess`) values (NULL,NULL,'admin+13654'),(12212,3405,'12212+u+3405'),(12223,22761,'12223+u+22761'),(12223,94429,'12223+u+94429'),(12223,67700,'12223+u+67700'),(54321,79691,'54321+f+79691'),(12223,80639,'12223+u+80639'),(12223,37813,'12223+u+37813'),(54321,49118,'54321+f+49118'),(54321,79344,'54321+f+79344'),(54321,85389,'54321+f+85389'),(54321,9705,'54321+f+9705'),(12212,79667,'12212+u+79667'),(12212,24391,'12212+u+24391'),(12212,31569,'12212+u+31569'),(12212,10724,'12212+u+10724'),(NULL,NULL,'admin+628'),(NULL,NULL,'admin+26066'),(NULL,NULL,'admin+1444');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `date` datetime default NULL,
  `facilitator_id` int(11) default NULL,
  `description` varchar(150) default NULL,
  `facility_id` int(11) default NULL,
  `room_id` varchar(11) default NULL,
  `status` varchar(10) default 'pending',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `facilitator_id` (`facilitator_id`),
  KEY `facility_id` (`facility_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `token_ibfk_2` FOREIGN KEY (`facilitator_id`) REFERENCES `facilitator` (`id`),
  CONSTRAINT `token_ibfk_3` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `token` */

insert  into `token`(`id`,`user_id`,`date`,`facilitator_id`,`description`,`facility_id`,`room_id`,`status`) values (1,12223,'2017-12-05 23:09:50',56789,'computer no 11 not working',3,'cl-10','pending'),(2,12212,'2017-12-05 23:47:17',12345,'aaaa',2,'cl-10','pending'),(3,12212,'2017-12-05 23:50:44',54321,'sss',4,'cl-10','reported'),(4,12223,'2017-12-06 00:36:49',54321,'tubelight',4,'cl-10','resolved'),(5,12223,'2017-12-06 01:12:45',56789,'rgrgrgrrrrrrr',3,'cl-10','pending'),(6,12223,'2017-12-06 10:27:13',54321,'not work',1,'101','pending'),(7,12223,'2017-12-06 10:32:39',54321,'right corner fan is not working',1,'102','pending');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phone` char(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`email`,`phone`,`password`,`type`) values (11111,'aaa','ayush@gmail.com','1234456578','444444','student'),(12212,'Anmol Madaan','madaan1996@gmail.com','7742231111','anmolmadaan','student'),(12223,'Ayush Jain','ayush.1996.jain@gmail.com','8946992225','ayushjain','student'),(12232,'ayush didwaniya','didwaniya.ayush@gmail.com','8824743301','ayush','student'),(55555,'Vaibhav','vaibhavvijay9@gmail.com','7894561237','vaibhav','student');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
