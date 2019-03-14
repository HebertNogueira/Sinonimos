SET NAMES utf8;
SET time_zone = '-03:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `game`;
CREATE DATABASE `game` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `game`;

DROP TABLE IF EXISTS `palavras`;
CREATE TABLE `palavras` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `palavra` varchar(20) NOT NULL,
  `sinonimo1` varchar(20) NOT NULL,
  `sinonimo2` varchar(20) NOT NULL,
  `sinonimo3` varchar(20) NOT NULL,
  `utilizado` varchar(1) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `palavras` (`palavra`, `sinonimo1`, `sinonimo2`, `sinonimo3`, `utilizado`) VALUES
('Vassoura',	'Casa',	'Limpeza',	'Varrer',	'N'),
('WhatsApp',	'Vicio',	'Verde',	'Mensageiro',	'N'),
('Bicicleta',	'Mobilidade',	'Rodas',	'Pedal',	'N'),
('Paris',	'Cidade',	'Amor',	'Eiffel',	'N');
