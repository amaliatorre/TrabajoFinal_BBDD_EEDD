
create database tiendadb;
use tiendadb;

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `clientes` ( `nombre`, `ciudad`) VALUES
( 'LIONEL', 'VALENCIA'),
( 'SERGI', 'VALENCIA'),
( 'ALFREDO', 'VALENCIA'),
( 'PACO', 'VALENCIA'),
( 'MARIA', 'CASTELLON'),
( 'PEPITO', 'ALICANTE'),
( 'LUIS', 'ELX'),
( 'ANTONIA', 'XATIVA'),
( 'CARMEN', 'PATERNA');

CREATE TABLE `descuento` (
  `id_descuento` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_rebaja` varchar(50) NOT NULL,
  `porcentaje` double NOT NULL,
  `fecha_creacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (id_descuento)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `descuento` ( `tipo_rebaja`, `porcentaje` ) VALUES
( 'PREMIUM', 30),
( 'BASIC', 10);

DROP procedure IF EXISTS `sp_DescuentosPremium`;

DELIMITER $$
USE `tiendadb`$$
CREATE PROCEDURE `sp_DescuentosPremium` (IN ciudad varchar(50))
BEGIN
	SELECT IF(STRCMP(ciudad, "VALENCIA") = 0, (SELECT porcentaje FROM descuento WHERE tipo_rebaja = 'PREMIUM') , (SELECT porcentaje FROM descuento WHERE tipo_rebaja = 'BASIC')) AS 'Descuento';
END$$

DELIMITER ;