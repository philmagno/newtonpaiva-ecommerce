CREATE DATABASE  IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookstore`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.7.3-m13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`),
  UNIQUE KEY `idcategoria_UNIQUE` (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Desktops','Produtos Eletrônicos P/ Desktop'),(2,'Laptops','Produtos Eletrônicos P/ Laptops'),(3,'Componentes','Componentes Eletrônicos'),(4,'Tablets','Tablets e acessórios para tablets'),(5,'Smartphones e Celulares','');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` int(11) NOT NULL,
  `telefone` int(11) DEFAULT NULL,
  `celular` int(11) DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `sexo` varchar(45) NOT NULL,
  `fkUsuario` int(11) NOT NULL,
  `fkfatura` int(11) DEFAULT NULL,
  `fkEnderecoEntrega` int(11) NOT NULL,
  `fkEnderecoFatura` int(11) NOT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `fk_cliente_usuario1_idx` (`fkUsuario`),
  KEY `fk_cliente_endereco1_idx` (`fkEnderecoEntrega`),
  KEY `fk_cliente_endereco2_idx` (`fkEnderecoFatura`),
  CONSTRAINT `fk_cliente_endereco1` FOREIGN KEY (`fkEnderecoEntrega`) REFERENCES `endereco` (`idendereco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_endereco2` FOREIGN KEY (`fkEnderecoFatura`) REFERENCES `endereco` (`idendereco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_usuario1` FOREIGN KEY (`fkUsuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `idendereco` int(11) NOT NULL AUTO_INCREMENT,
  `enderecocol` varchar(45) DEFAULT NULL,
  `fkRua` int(11) DEFAULT NULL,
  PRIMARY KEY (`idendereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `idatendente` int(11) NOT NULL AUTO_INCREMENT,
  `fkUsuario` int(11) NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idatendente`),
  KEY `fk_atendente_usuario_idx` (`fkUsuario`),
  CONSTRAINT `fk_atendente_usuario` FOREIGN KEY (`fkUsuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itempedido`
--

DROP TABLE IF EXISTS `itempedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itempedido` (
  `iditemPedido` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `fkPedido` int(11) DEFAULT NULL,
  `fkItem` int(11) NOT NULL,
  `desconto` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`iditemPedido`),
  KEY `fk_item_pedido1_idx` (`fkPedido`),
  KEY `fk_itempedido_livro1_idx` (`fkItem`),
  CONSTRAINT `fk_itempedido_livro1` FOREIGN KEY (`fkItem`) REFERENCES `produto` (`idproduto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_pedido1` FOREIGN KEY (`fkPedido`) REFERENCES `pedido` (`idpedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itempedido`
--

LOCK TABLES `itempedido` WRITE;
/*!40000 ALTER TABLE `itempedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `itempedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idpedido` int(11) NOT NULL AUTO_INCREMENT,
  `desconto` decimal(11,0) DEFAULT NULL,
  `valorfrete` decimal(11,0) NOT NULL,
  `fkCliente` int(11) DEFAULT NULL,
  `fkPagamento` int(11) DEFAULT NULL,
  `dataPedido` datetime NOT NULL,
  `fkendereco` int(11) NOT NULL,
  `statusPagamento` int(11) NOT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `fk_pedido_cliente1_idx` (`fkCliente`),
  KEY `fk_pedido_endereco1_idx` (`fkendereco`),
  CONSTRAINT `fk_pedido_cliente1` FOREIGN KEY (`fkCliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_endereco1` FOREIGN KEY (`fkendereco`) REFERENCES `endereco` (`idendereco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `idproduto` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(250) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `descricao` longtext NOT NULL,
  `idcategoria` int(11) NOT NULL,
  `preco` decimal(11,2) DEFAULT NULL,
  `caminhoimagem` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idproduto`),
  KEY `nome` (`nome_produto`),
  KEY `fk_produto_categoria1_idx` (`idcategoria`),
  CONSTRAINT `fk_produto_categoria1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'BLU TOUCH BOOK P-50 PRETO',836,'preto',4,73.00,NULL),(2,'IPAD MINI2 16GB WIFI 4G ME800E/A CINZ SG',836,'cinza',4,535.00,NULL),(3,'IPAD MINI2 16GB WIFI 4G ME814E/A PRET SG',836,'',4,535.00,NULL),(4,'IPAD MINI2 16GB WIFI ME276E/A CINZA SG',836,'',4,405.00,NULL),(5,'IPAD MINI2 16GB WIFI ME279E/A SIL SG',836,'',4,405.00,NULL),(6,'IPAD MINI2 32GB WIFI 4G ME820E/A CINZ SG',836,'',4,630.00,NULL),(7,'IPAD MINI2 32GB WIFI 4G ME824E/A PRET SG',836,'',4,630.00,NULL),(8,'IPAD MINI2 64GB WIFI 4G ME828E/A CINZ SG',836,'',4,735.00,NULL),(9,'Acer AM3970-UR10P',50,'branco',1,630.00,NULL),(10,'Desktop Sony SVL-24114FX/B',100,'Preto',1,1490.00,NULL),(11,'HP Omni 27-1054',50,'Cinza/Preto',1,1480.00,NULL),(12,'Philips HMP-3000',60,'Preto',1,48.00,NULL),(13,'Philips HMP-5000',50,'Preto',1,58.00,NULL),(14,'Satellite K-631',50,'Preto',1,35.00,NULL),(15,'Acer Aspire 4560 SB601',300,'Preto com Cinza',2,415.00,NULL),(16,'Acer Aspire 4560-7492',312,'Preto',2,450.00,NULL),(17,'Acer Aspire E1-531-4852',235,'Preto',2,380.00,NULL),(18,'Acer Aspire V5-471-6677',236,'Prata',2,510.00,NULL),(19,'Acer E1-471-6851',456,'Preto',2,417.00,NULL),(20,'Airis Exilis N1300T',724,'Preto',2,213.00,NULL),(21,'HP 2000-2B09WM AZUL',753,'Branco',2,315.00,NULL),(22,'HP 2000-2B19WM',432,'Preto',2,340.00,NULL),(23,'HP ProBook 4540S',532,'Cinza',2,455.00,NULL),(24,'NB Acer Aspire E1-430-4424',642,'Preto',2,359.00,NULL),(25,'NB Acer Aspire E1-522-3617',423,'Preto',2,340.00,NULL),(26,'NB Acer Aspire E1-531-2802',765,'Preto',2,347.00,NULL),(27,'NB Acer Aspire E1-572-6688',345,'Preto',2,579.00,NULL),(28,'NB Acer Aspire V5-431-2618',824,'Prata',2,380.00,NULL),(29,'NB ACER E1-422-3419 AMD 1.4 4G/500G/14.0',417,'Preto',2,340.00,NULL),(30,'NB ACER E1-722-6473 I5 2.5 6G/1TB/17.3\"P',153,'Cinza',2,710.00,NULL),(31,'NB ACER V5 123-3889 AMD1.0/4G/500G/11.6',263,'Vermelho e preto',2,330.00,NULL),(32,'NB BAK 120 PRE',173,'Prata',2,105.00,NULL),(33,'NB Bak BK-NBR119X',782,'Branco',2,102.00,NULL),(34,'NB Bak BK-NBR119X',312,'Preto',2,102.00,NULL),(35,'NB Bak BK-NBR719X',143,'Rosa',2,82.00,NULL),(36,'NB Bak BK-NBR719X',421,'Branco',2,82.00,NULL),(37,'NB HP 2000 2D49WM',513,'Azul',2,330.00,NULL),(38,'NETBOOK Bak NBR719X',234,'Preto',2,82.00,NULL),(39,'NOTEBOOK Acer Aspire V3-471-6841',636,'Preto',2,520.00,NULL),(40,'NOTEBOOK Bak BK-120',512,'Branco',2,105.00,NULL),(41,'NOTEBOOK Samsung ATIV Book 2 (NP270E5G-K01JM)',531,'Preto Mineral',2,538.00,NULL),(42,'NOTEBOOK Samsung ATIV Book 2 (NP270E5G-K02JM)',534,'Preto Mineral',2,474.00,NULL),(43,'NOTEBOOK Samsung ATIV Book 2 (NP270E5G-K03JM)',643,'Preto Mineral',2,397.00,NULL),(44,'NOTEBOOK Samsung ATIV Book 9 (NP900X3E-K01JM)',737,'Preto Mineral',2,676.00,NULL),(45,'NOTEBOOK Samsung ATIV Book 9 (NP900X3E-K01JM)',441,'Preto Mineral',2,615.00,NULL),(46,'NOTEBOOK Samsung ATIV Book 9 (NP915S3G-K01JM)',617,'Branco',2,765.00,NULL),(47,'NOTEBOOK Sony Vaio SVF-14212CX',233,'Branco',2,604.00,NULL),(48,'NOTEBOOK Sony Vaio SVF-14212CX',233,'Preto',2,604.00,NULL),(49,'NOTEBOOK Sony Vaio SVF-14215CX',523,'Preto',2,780.00,NULL),(50,'NOTEBOOK Sony Vaio SVF-14215CX',223,'Branco',2,780.00,NULL),(51,'NOTEBOOK Sony Vaio SVF-15213CX',232,'Branco',2,665.00,NULL),(52,'NOTEBOOK Sony Vaio SVF-15213CX',516,'Preto',2,665.00,NULL),(53,'Sony Vaio SVE14138CXW',314,'Branco',2,728.00,NULL),(54,'Sony Vaio SVE15137CXS',541,'Silver',2,720.00,NULL),(55,'Sony Vaio SVE15137CXW',314,'Branco',2,720.00,NULL),(56,'ULTRABOOK Sony Vaio SVT-13136CXS',513,'Silver',2,880.00,NULL),(57,'ULTRABOOK Sony Vaio SVT-1412ACXS',454,'Silver',2,762.00,NULL),(58,'DURACELL 2X1 8GB CLASS4',76,'Preto',3,7.10,NULL),(59,'Kingston Micro SDC4/16GB',432,'Preto',3,8.23,NULL),(60,'Kingston Micro SDC4/32GB',4231,'Preto',3,17.10,NULL),(61,'Kingston Micro SDC4/4GB',6423,'Preto',3,3.57,NULL),(62,'Kingston SDC4/8GB',42,'Preto',3,4.21,NULL),(63,'MEMORIA S/MARCA MICRO SD 4GB 2X1 S/G ',65,'verde',3,3.50,NULL),(64,'Memoria Corsair CM3X4GSD1066',2431,'Verde',3,32.00,NULL),(65,'Memoria Corsair CMSA4GX3M1A1333C9',65,'Verde',3,49.00,NULL),(66,'Memoria Corsair VS1GSDS333',4315,'Verde',3,32.00,NULL),(67,'Memoria Corsair VS1GSDS400',5,'Verde',3,32.00,NULL),(68,'Memoria Corsair VS2GB667D2',22457,'Verde',3,34.00,NULL),(69,'Memoria Corsair VS2GB800D2',45,'Verde',3,34.00,NULL),(70,'MEMORIA MATRIZ 1.93',6,'',3,1.75,NULL),(71,'MEMORIA P/NB CORSAIR DDR3 4GB 1600MHZ',435,'',3,51.00,NULL),(72,'MEMORIA P/PC Corsair XMS3 CMX4GX3M1A1333C9',34,'Verde',3,47.70,NULL),(73,'MEMORIA P/PC CORSAIR 8GB 1866M VENGEANCE',13,'',3,106.00,NULL),(74,'MEMORIA P/PC CORSAIR DDR3 4GB 1600MHZ(XM',37,'',3,51.00,NULL),(75,'MEMORIA P/PC CORSAIR DDR3 4GB 2133MHZ',156,'',3,62.00,NULL),(76,'MEMORIA P/PC CORSAIR DDR3 8G 1333M(XMS3',5431,'',3,91.35,NULL),(77,'MEMORIA P/PC Corsair Dominator CMT8GX3M4A1866C9',56431,'Preto',3,47.70,NULL),(78,'MEMORIA P/PC Corsair Value Select DDR3 8GB',3146,'Verde',3,91.00,NULL),(79,'MEMORIA P/PC Corsair Vengeance 4GB 1866Mhz',6341,'Preto',3,61.00,NULL),(80,'Memoria PlayStation Vita',664,'Preto',3,21.50,NULL),(81,'Memoria PlayStation Vita',6431,'Preto',3,9.50,NULL),(82,'MEMORIA Sandisk SD 16GB Extreme Pro',634,'Preto',3,17.18,NULL),(83,'MEMORIA Sandisk SD 32GB Extreme Pro',314,'Preto',3,47.70,NULL),(84,'MEMORIA Sandisk SD 64G Extreme Pro',6123,'Preto',3,62.00,NULL),(85,'MEMORIA Sandisk SD 64G Extreme Pro',42,'Preto',3,118.50,NULL),(86,'MEMORIA Sandisk SDSDQUI-008G',4231,'Cinza',3,47.70,NULL),(87,'MEMORIA Sandisk SDSDQUI-016G',4231,'Cinza',3,47.70,NULL),(88,'MEMORIA Sandisk SDSDXPA-008G',4132,'Preto',3,19.60,NULL),(89,'Memoria SD Sony Class 4 8Gb',642,'Azul',3,6.15,NULL),(90,'Memoria SD Sony SF32U',247,'Preto',3,18.50,NULL),(91,'Memoria SD Sony SF4B4',134,'Azul',3,4.70,NULL),(92,'MEMORIA Sony Micro SD SR-2A1',5231,'Preto',3,3.45,NULL),(93,'Memoria Sony MSHX16B',264,'Preto',3,43.90,NULL),(94,'Memoria Sony MSHX8B',513,'Preto',3,22.10,NULL),(95,'Memoria Sony MSMT2GN',5314,'Preto',3,8.30,NULL),(96,'Memoria Sony MSMT4GN',356,'Preto',3,12.08,NULL),(97,'Memoria Sony SD 16GB Class 4',436,'Preto',3,10.60,NULL),(98,'MEMORIA Sony SF8N4',531,'Preto',3,4.90,NULL),(99,'Memoria Sony SR-4A4',742,'Preto',3,3.52,NULL),(100,'Memory Card 8Mb PlayStation 2',745,'Preto',3,5.50,NULL),(101,'Memory Card PlayStation Vita',513,'Preto',3,38.50,NULL),(102,'Sandisk Extreme Pro SDSDXPA-008G',543,'Preto',3,19.50,NULL),(103,'Sandisk Micro SD SAMSD16GBA',5431,'Preto',3,47.70,NULL),(104,'Sandisk Micro SD SAMSDU64GB Ultra',5314,'Vermelho',3,47.70,NULL),(105,'Sandisk Micro SDSDQM-004G-B35',6542,'Preto',3,47.70,NULL),(106,'Sandisk Micro SDSDQM-008G-B35',4624,'Preto',3,47.70,NULL),(107,'Sandisk Micro SDSDQM-032G-B35',245,'Preto',3,47.70,NULL),(108,'Sandisk SAECF16GB 60MB/s',6542,'Preto',3,47.70,NULL),(109,'Sandisk SAECF32GB Compact Flash Extreme',134,'Preto',3,77.75,NULL),(110,'Sandisk SAECF64GB extreme 60MB/s',5341,'Preto',3,77.75,NULL),(111,'Sandisk SAECF8GB Compact Flash Extreme',531,'Preto',3,29.50,NULL),(112,'Sandisk SAEPSDU16GB 95MB Extreme Pro',531,'Preto',3,37.00,NULL),(113,'Sandisk SAUCF16GB 30MB/s Ultra',531,'Preto',3,36.10,NULL),(114,'Sandisk SAUCF8GBC 30MB/s Compact Flash Ultra',531,'Preto',3,20.00,NULL),(115,'IPAD MINI2 64GB WIFI 4G ME832E/A PRET SG',836,'',4,735.00,NULL),(116,'IPAD MINI2 64GB WIFI ME278E/A CINZA SG',836,'',4,620.00,NULL),(117,'IPAD MINI2 64GB WIFI ME281E/A SILVE SG',836,'',4,620.00,NULL),(118,'PROBOX PB 705',836,'',4,72.00,NULL),(119,'PROBOX PB 731S',836,'',4,82.00,NULL),(120,'Pyramid TPD705',836,'Preto',4,58.00,NULL),(121,'Pyramid TPD705',836,'Cinza',4,58.00,NULL),(122,'Pyramid TPD710',836,'Branco',4,78.00,NULL),(123,'Pyramid TPD900',836,'Branco',4,82.00,NULL),(124,'Pyramid TPD900',836,'Preto',4,82.00,NULL),(125,'Pyramid TPD971',836,'Branco',4,121.00,NULL),(126,'Samsung Tablet Galaxy N8000',836,'Preto',4,479.00,NULL),(127,'TABLET Acer Iconia B1-A71',836,'Preto/Azul',4,95.00,NULL),(128,'TABLET AIRIS ONEPAD TAB-715',836,'Preto',4,60.00,NULL),(129,'TABLET AIRIS ONEPAD TAB-90D',836,'Preto',4,176.00,NULL),(130,'TABLET AIRIS TAB-11E',836,'Preto',4,120.00,NULL),(131,'TABLET AOC BREEZE MW-0712',836,'Silver/Preto',4,89.00,NULL),(132,'TABLET AOC BREEZE MW-0712',836,'Roxo/Preto',4,89.00,NULL),(133,'TABLET AOC BREEZE MW-0712',836,'Vermelho/Preto',4,89.00,NULL),(134,'TABLET AOC BREEZE MW-0712',836,'Azul/Preto',4,89.00,NULL),(135,'TABLET AOC BREEZE MW-0712',836,'Verde/Preto',4,89.00,NULL),(136,'TABLET AOC BREEZE MW-0931',836,'Silver/Preto',4,180.00,NULL),(137,'TABLET Apple iPad 2 16Gb MC979E/A',836,'Branco',4,400.00,NULL),(138,'TABLET Apple iPad 2 16Gb MC769/E',836,'Preto',4,400.00,NULL),(139,'TABLET Apple iPad Air 16Gb MD788LL/A',836,'Silver',4,493.00,NULL),(140,'TABLET Apple iPad Air 16Gb/Wifi/4G MD794E/A',836,'',4,625.00,NULL),(141,'TABLET APPLE IPAD MINI',836,'Branco',4,510.00,NULL),(142,'TABLET APPLE IPAD MINI',836,'Preto',4,428.00,NULL),(143,'TABLET APPLE IPAD MINI',836,'Branco',4,635.00,NULL),(144,'TABLET Apple iPad Mini 16Gb 4G MD537E/A',836,'Branco',4,428.00,NULL),(145,'TABLET Apple iPad Mini 16Gb MD531E/A',836,'Branco',4,303.00,NULL),(146,'TABLET Apple iPad Mini 64Gb MD530E/A',836,'Preto',4,510.00,NULL),(147,'TABLET Apple Ipad Mini MD536LL/A',836,'Preto',4,635.00,NULL),(148,'TABLET Apple iPad Mini ME280E/A 32Gb/Wifi',836,'Prata',4,505.00,NULL),(149,'TABLET BAK 117',836,'Preto',4,145.00,NULL),(150,'TABLET BAK 3R7',836,'Preto',4,47.00,NULL),(151,'TABLET BAK 3R7',836,'Blanco',4,47.00,NULL),(152,'TABLET BAK 7040',836,'Branco',4,53.00,NULL),(153,'TABLET BAK 7040',836,'Cinza',4,53.00,NULL),(154,'TABLET BAK 7040',836,'Rosa',4,53.00,NULL),(155,'TABLET BAK 7040',836,'Silver',4,53.00,NULL),(156,'TABLET Bak iBak 7400',836,'Rosa',4,48.00,NULL),(157,'TABLET BAK iBAK-707DTV',836,'Silver',4,53.00,NULL),(158,'TABLET BAK iBAK-707DTV',836,'Branco',4,53.00,NULL),(159,'TABLET BAK iBAK-707DTV',836,'Preto',4,53.00,NULL),(160,'TABLET BAK iBAK-707DTV',836,'Rosa',4,53.00,NULL),(161,'TABLET BAK iBAK-7200CAP',836,'Branco',4,46.00,NULL),(162,'TABLET BAK iBAK-7200CAP',836,'Cinza',4,46.00,NULL),(163,'TABLET BAK iBAK-7200CAP',836,'Rosa',4,46.00,NULL),(164,'TABLET BAK iBAK-7200CAP',836,'Preto',4,46.00,NULL),(165,'TABLET BAK iBAK-7200CAP',836,'Vermelho',4,46.00,NULL),(166,'TABLET BAK iBAK-7200CAP',836,'Silver',4,46.00,NULL),(167,'TABLET BAK iBAK-722',836,'Rosa',4,70.00,NULL),(168,'TABLET BAK iBAK-722',836,'Roxo',4,70.00,NULL),(169,'TABLET BAK iBAK-722',836,'Azul',4,70.00,NULL),(170,'TABLET BAK iBAK-722',836,'Preto',4,70.00,NULL),(171,'TABLET BAK iBAK-733',836,'Branco',4,82.00,NULL),(172,'TABLET BAK iBAK-733',836,'Cinza',4,82.00,NULL),(173,'TABLET BAK iBAK-733',836,'Rosa',4,82.00,NULL),(174,'TABLET BAK iBAK-733',836,'Preto',4,82.00,NULL),(175,'TABLET BAK iBAK-7400CAP',836,'Branco',4,48.00,NULL),(176,'TABLET BAK iBAK-7400CAP',836,'Cinza',4,48.00,NULL),(177,'TABLET BAK iBAK-7400CAP',836,'Preto',4,48.00,NULL),(178,'TABLET BAK iBAK-771',836,'Branco',4,100.00,NULL),(179,'TABLET BAK iBAK-771',836,'Preto',4,100.00,NULL),(180,'TABLET BAK iBAK-771',836,'Rosa',4,100.00,NULL),(181,'TABLET BAK iBAK-771',836,'Silver',4,100.00,NULL),(182,'TABLET BAK iBAK-782',836,'Silver',4,80.00,NULL),(183,'TABLET BAK iBAK-782',836,'Rosado',4,80.00,NULL),(184,'TABLET BAK iBAK-782',836,'Preto',4,80.00,NULL),(185,'TABLET BAK iBAK-782',836,'Branco',4,80.00,NULL),(186,'TABLET BAK iBAK-789M',836,'Negro',4,75.00,NULL),(187,'TABLET BAK iBAK-789M',836,'Branco',4,75.00,NULL),(188,'TABLET BAK iBAK-789M',836,'Branco',4,75.00,NULL),(189,'TABLET BAK iBAK-789M',836,'Branco',4,75.00,NULL),(190,'TABLET BAK iBAK-799',836,'Preto',4,95.00,NULL),(191,'TABLET BAK iBAK-799',836,'Branco',4,95.00,NULL),(192,'TABLET BAK iBAK-799',836,'Rosa',4,95.00,NULL),(193,'TABLET BAK iBAK-799',836,'Silver',4,95.00,NULL),(194,'TABLET BAK iBAK-858CAP',836,'Silver',4,65.00,NULL),(195,'TABLET BAK iBAK-Mini7',836,'Preto',4,47.00,NULL),(196,'TABLET BAK iBAK-Mini7',836,'Branco',4,47.00,NULL),(197,'TABLET BLU Touch Book P-400',836,'Preto',4,190.00,NULL),(198,'TABLET BLU TOUCH BOOK P-50',836,'',4,73.00,NULL),(199,'Airis TM500',687,'Preto',5,140.00,NULL),(200,'ALVO ZOOM BRANCO/ROXO',124,'',5,19.90,NULL),(201,'Apple iPhone 4S 16gb',375,'Branco',5,445.00,NULL),(202,'Apple iPhone 5 16gb',425,'Branco',5,625.00,NULL),(203,'BAK 9780',424,'',5,25.00,NULL),(204,'BAK BK-MP7930X',727,'BRANCO/ROXO',5,25.00,NULL),(205,'BAK BK-MP7930X',662,'BRANCO/VERMELHO',5,25.00,NULL),(206,'BAK BK-Q611',627,'',5,14.00,NULL),(207,'Blackberry Curve 9220',687,'Branco',5,114.00,NULL),(208,'BlackBerry Curve 9320',124,'Preto',5,144.00,NULL),(209,'Blackberry Curve 9360',375,'Preto',5,229.00,NULL),(210,'BlackBerry Flow Z10',425,'Preto',5,220.00,NULL),(211,'BlackBerry Z10',424,'Preto',5,220.00,NULL),(212,'Blu Amour D290A',727,'Branco',5,132.00,NULL),(213,'BLU JENNY T172T VERMELHO/PRETO',662,'Preto',5,23.90,NULL),(214,'CAMERA Samsung Galaxy Camera EK-GC110',627,'Preto',5,355.00,NULL),(215,'CAMERA Samsung Galaxy Camera EK-GC110',687,'Preto',5,355.00,NULL),(216,'CEL ALVO COOL DUAL BRANCO LARANJA',124,'Branco',5,46.50,NULL),(217,'CEL BAK 221+M DUAL GPS AND VERM',375,'',5,70.00,NULL),(218,'CEL BAK 332 DUAL AND SIL',425,'Preto/Azul',5,30.00,NULL),(219,'CEL BAK 7930X DUAL TV BRAN/AZUL',424,'Branco',5,25.00,NULL),(220,'CEL BAK 7950X',727,'Preto',5,25.00,NULL),(221,'CEL BAK P173 PANZER DUAL PRE BRA',662,'Preto',5,17.00,NULL),(222,'CEL BAK S3MINI DUAL TV VERMELHO',627,'Preto',5,25.00,NULL),(223,'CEL BLACKBERRY Q10 SQN100-3 4G BRA',687,'Branco',5,415.00,NULL),(224,'CEL BLACKBERRY Z10 STL100-1 3G BRA',124,'Preto',5,220.00,NULL),(225,'CEL BLU ADVANCE A270A DUAL 850 BRA',375,'Preto',5,105.00,NULL),(226,'CEL BLU ADVANCE A270A DUAL 850 PRE',425,'Preto',5,105.00,NULL),(227,'CEL BLU DASH D171I DUAL 2100 ANA BRA',687,'',5,71.00,NULL),(228,'CEL BLU DASH D171I DUAL 2100 ANA PRE',124,'',5,71.00,NULL),(229,'CEL BLU DASH D271A DUAL 850 ANDR4.0 PRE',375,'',5,98.00,NULL),(230,'CEL BLU DASH D310A DUAL ANDROID 4.5 PRET',425,'',5,129.00,NULL),(231,'CEL BLU DASH D410',424,'Preto',5,120.00,NULL),(232,'CEL BLU DASH D410A DUAL 850 ANDR4.2 AMA',727,'Preto',5,120.00,NULL),(233,'CEL BLU DASH D410A DUAL 850 ANDR4.2 AZU',662,'cinza',5,120.00,NULL),(234,'CEL BLU DASH D410I DUAL 2100 ANDR4.2 AMA',627,'Branco',5,120.00,NULL),(235,'CEL BLU DASH D410I DUAL 2100 ANDR4.2 BRA',687,'cinza',5,120.00,NULL),(236,'CEL BLU HERO PRO Q333W 3CH TV WIF ROS',124,'Branco',5,39.00,NULL),(237,'CEL BLU JENNY T176T DUAL ANAT TV PRE AZU',375,'cinza',5,30.00,NULL),(238,'CEL BLU LIFE ONE L120 DUAL 2100 SIL',425,'Branco',5,249.00,NULL),(239,'CEL BLU STUDIO D570A DUAL 2100 AD BRA+CA',424,'cinza',5,169.00,NULL),(240,'CEL BLU TANK T191 DUAL PRE VERM ANA S/F',727,'Preto',5,23.00,NULL),(241,'CEL BLU TANK W110A BR',662,'cinza',5,121.00,NULL),(242,'CEL BLU VIVO D910A 4.3 DUAL 850 AND AMA',627,'Branco',5,171.00,NULL),(243,'CEL BLU VIVO D910A 4.3 DUAL 850 AND ROS',687,'cinza',5,171.00,NULL),(244,'CEL DOTCOM 722 3CHIP TV ROS',124,'Branco',5,29.00,NULL),(245,'CEL DOTCOM 745 DUAL ROS SIL',662,'cinza',5,28.00,NULL),(246,'CEL DOTCOM 745 DUAL TV AZU SIL',627,'Branco',5,28.00,NULL),(247,'CEL DOTCOM 745 DUAL TV VER SIL',687,'cinza',5,28.00,NULL),(248,'CEL DOTCOM 781 DUAL TV PRE',124,'Preto',5,20.00,NULL),(249,'CEL DOTCOM 786 DUAL TV DIG BRA',375,'cinza',5,22.50,NULL),(250,'CEL DOTCOM 786 DUAL TV DIG PRE',425,'Branco',5,22.50,NULL),(251,'CEL GENESIS GP505 DUAL 3G WIFI ANDRO BRA',424,'cinza',5,135.00,NULL),(252,'CEL GENESIS GP505 DUAL 3G WIFI ANDRO PRE',727,'Branco',5,135.00,NULL),(253,'CEL LG A-255 PRE CIN**',662,'cinza',5,48.00,NULL),(254,'CEL LG A-395 4CHIP PRE S/C',627,'Branco',5,57.00,NULL),(255,'CEL LG C-199 DUAL VERM',687,'cinza',5,49.50,NULL),(256,'CEL LG E-415 OPTIMUS L1 DUAL BRA',124,'Preto',5,75.00,NULL),(257,'CEL LG E-415 OPTIMUS L1 DUAL PRE',375,'cinza',5,75.00,NULL),(258,'CEL MOX 647 3CH TV VERMEL/PRET',425,'Branco',5,18.00,NULL),(259,'CEL MOX A8 DUAL ANDROI WIFI BRANCO',687,'cinza',5,105.00,NULL),(260,'CEL MOX M12 DUAL CIN',124,'Branco',5,26.00,NULL),(261,'CEL MOX M2 DUAL PRETO/AMARE',375,'cinza',5,17.50,NULL),(262,'CEL MOX M2 DUAL PRETO/AZUL',425,'Branco',5,18.00,NULL),(263,'CEL MOX M30 DUAL CINZA',424,'cinza',5,23.90,NULL),(264,'CEL MOX M31 DUAL PRETO/VERMELH',727,'Branco',5,18.00,NULL),(265,'CEL MOX T30 3CH TV VERMELHO',662,'Branco',5,20.00,NULL),(266,'CEL MOX T5 DUAL TV PRETO/ROSA',627,'Branco',5,35.00,NULL);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-08  2:48:54
