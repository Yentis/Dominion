/*
SQLyog Community v12.01 (64 bit)
MySQL - 5.6.17 : Database - dominion
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dominion` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `dominion`;

/*Table structure for table `beurt` */

DROP TABLE IF EXISTS `beurt`;

CREATE TABLE `beurt` (
  `beurtnr` int(4) NOT NULL AUTO_INCREMENT,
  `actie` int(2) DEFAULT NULL,
  `koop` int(2) DEFAULT NULL,
  `geld` int(2) DEFAULT NULL,
  PRIMARY KEY (`beurtnr`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `beurt` */

insert  into `beurt`(`beurtnr`,`actie`,`koop`,`geld`) values (1,1,1,0);

/*Table structure for table `beurtkaartspeler` */

DROP TABLE IF EXISTS `beurtkaartspeler`;

CREATE TABLE `beurtkaartspeler` (
  `beurtnr` int(4) NOT NULL,
  `kaartnr` int(4) NOT NULL,
  `spelernr` int(4) NOT NULL,
  PRIMARY KEY (`beurtnr`,`kaartnr`,`spelernr`),
  KEY `cardnr` (`kaartnr`),
  KEY `playernr3` (`spelernr`),
  CONSTRAINT `cardnr` FOREIGN KEY (`kaartnr`) REFERENCES `kaart` (`kaartnr`),
  CONSTRAINT `playernr3` FOREIGN KEY (`spelernr`) REFERENCES `speler` (`spelernr`),
  CONSTRAINT `turnnr2` FOREIGN KEY (`beurtnr`) REFERENCES `beurt` (`beurtnr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `beurtkaartspeler` */

insert  into `beurtkaartspeler`(`beurtnr`,`kaartnr`,`spelernr`) values (1,1,1),(1,2,1);

/*Table structure for table `kaart` */

DROP TABLE IF EXISTS `kaart`;

CREATE TABLE `kaart` (
  `kaartnr` int(4) NOT NULL AUTO_INCREMENT,
  `naam` varchar(15) DEFAULT NULL,
  `kost` int(2) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `omschrijving` tinytext,
  `waarde` int(1) DEFAULT '0',
  PRIMARY KEY (`kaartnr`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

/*Data for the table `kaart` */

insert  into `kaart`(`kaartnr`,`naam`,`kost`,`type`,`omschrijving`,`waarde`) values (1,'Heks',5,'Actie','+2 Kaarten\r\nAlle andere spelers krijgen een Vloek kaart.',0),(2,'Tuinen',4,'Overwinning','Geeft 1 Overwinningspunt per 10 kaarten in je deck (afgerond naar beneden).',0),(3,'Kelder',2,'Actie','+1 Actie\r\nLeg een aantal kaarten af.\r\n+1 Kaart voor elke afgelegde kaart.',0),(4,'Kerk',2,'Actie','Plaats tot 4 kaarten in de vuilbak.',0),(5,'Gracht',2,'Actie-Reactie','+2 Kaarten\r\nWanneeer een andere speler een aanvalskaart speelt, \r\nmag je deze kaart tonen om de aanval te annuleren.\r\n',0),(6,'Kanselier',3,'Actie','+2 Geld\r\nJe mag onmiddelijk je deck in de aflegstapel plaatsen.',0),(7,'Dorps',3,'Actie','+1 Kaart\r\n+2 Acties',0),(8,'Houthakker',3,'Actie','+1 Koop\r\n+2 Geld',0),(9,'Werkplaats',3,'Actie','Neem een kaart die hoogstens 4 Geld kost.',0),(10,'Bureaucraat',4,'Actie-Aanval','Neem een actiekaart; plaats het bovenop je deck.\r\nElke andere speler toont een overwinningskaart \r\nvan zijn hand en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)\r\n',0),(11,'Feest',4,'Actie','Leg deze kaart in de vuilbakstapel.\r\nNeem een kaart die hoogstens 5 Geld kost.',0),(12,'Schutterij',4,'Actie-Aanval','+2 Geld\r\nElke andere speler legt kaarten af tot hij er 3 over heeft.',0),(13,'Geldverlener',4,'Actie','Leg een koper kaart in de vuilbakstapel om +3 Geld te krijgen.',0),(14,'Ombouwen',4,'Actie','Leg een kaart in de vuilbakstapel.\r\nKrijg een kaart die tot 2 Geld meer kost.',0),(15,'Smederij',4,'Actie','+3 Kaarten',0),(16,'Spion',4,'Actie-Aanval','+1 Kaart\r\n+1 Actie\r\nElke speler (inclusief jij) toont de bovense kaart\r\nvan zijn deck en legt het af of plaatst het terug, jouw keuze.',0),(17,'Dief',4,'Actie-Aanval','Elke andere speler toont de bovenste 2 kaarten van zijn deck.\r\nAls ze Geldkaarten tonen dan mag je er één in de vuilbakstapel leggen.\r\nJe mag zoveel van de Geldkaarten nemen voor jezelf als je wil.\r\nAlle andere Geldkaarten worden in de aflegstapel gestopt',0),(18,'Troonzaal',4,'Actie','Kies een actiekaart, speel deze 2 keer.',0),(19,'Raadzaal',5,'Actie','+4 Kaarten\r\n+1 Koop\r\nElke andere speler trekt een kaart.',0),(20,'Festival',5,'Actie','+2 Acties\r\n+1 Koop\r\n+2 Geld',0),(21,'Laboratorium',5,'Actie','+2 Kaarten\r\n+1 Actie',0),(22,'Bibliotheek',5,'Actie','Trek kaarten tot je er 7 in je hand hebt.\r\nJe mag actiekaarten aan de kant zetten die je getrokken gebt.\r\nLeg de kaarten die je aan de kant gezet hebt af.',0),(23,'Markt',5,'Actie','+1 Kaart\r\n+1 Actie\r\n+1 Koop\r\n+1 Geld',0),(24,'Mijn',5,'Actie','Leg een Geldkaart in de vuilbakstapel uit je hand.\r\nNeem een Geldkaart die 3 Geld meer kost van het veld.\r\nPlaats deze in je hand.',0),(25,'Advonturier',6,'Actie','Draai kaarten van je deck om tot je 2 Geldkaarten omdraait.\r\nPlaats deze Geldkaarten in je hand en leg de anderen af.',0),(26,'Koper',0,'Geld','+1 Geld',1),(27,'Zilver',3,'Geld','+2 Geld',2),(28,'Goud',6,'Geld','+3 Geld',3),(29,'Landgoed',2,'Overwinning','+1 Overwinningspunt',1),(30,'Hertogdom',5,'Overwinning','+3 Overwinningspunten',3),(31,'Provincie',8,'Overwinning','+6 Overwinningspunten',6),(32,'Vloek',0,'Vloek','-1 Overwinningspunt',-1);

/*Table structure for table `spel` */

DROP TABLE IF EXISTS `spel`;

CREATE TABLE `spel` (
  `spelnr` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`spelnr`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `spel` */

insert  into `spel`(`spelnr`) values (1);

/*Table structure for table `speler` */

DROP TABLE IF EXISTS `speler`;

CREATE TABLE `speler` (
  `spelernr` int(4) NOT NULL AUTO_INCREMENT,
  `naam` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`spelernr`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `speler` */

insert  into `speler`(`spelernr`,`naam`) values (1,'yentl');

/*Table structure for table `spelspeler` */

DROP TABLE IF EXISTS `spelspeler`;

CREATE TABLE `spelspeler` (
  `spelnr` int(4) NOT NULL,
  `spelernr` int(4) NOT NULL,
  `punten` int(3) DEFAULT NULL,
  PRIMARY KEY (`spelnr`,`spelernr`),
  KEY `playernr` (`spelernr`),
  CONSTRAINT `gamenr` FOREIGN KEY (`spelnr`) REFERENCES `spel` (`spelnr`),
  CONSTRAINT `playernr` FOREIGN KEY (`spelernr`) REFERENCES `speler` (`spelernr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `spelspeler` */

insert  into `spelspeler`(`spelnr`,`spelernr`,`punten`) values (1,1,25);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
