-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.20 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2014-07-21 20:23:46
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for proyectocvc
DROP DATABASE IF EXISTS `proyectocvc`;
CREATE DATABASE IF NOT EXISTS `proyectocvc` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `proyectocvc`;


-- Dumping structure for table proyectocvc.categoria
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombreCategoria` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `descripcionCategoria` text COLLATE latin1_spanish_ci,
  `fecha_alta` date NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Dumping data for table proyectocvc.categoria: ~13 rows (approximately)
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`idCategoria`, `nombreCategoria`, `descripcionCategoria`, `fecha_alta`) VALUES
	(1, 'Art \r\n\r\nand Antiques', 'Decoración, Antigüedades Rústicas, Artesania y Manualidades, Litografías y Grabados, Utensilios \r\n\r\nde cocina, Material de Bellas Artes, Muebles Antiguos y Decoración', '2013-02-11'),
	(2, 'Audio', 'Altavoces y Amplificadores, Audio portátil, Equipos de Música, Reproductores MP4, Ipod, Accesorios', '2013-02-11'),
	(3, 'Babies', 'Alimentación del bebé, Andadores, Carritos, Cunas, Disfraces, \r\n\r\nJuguetes y juegos, Libros de bebé y premamá, Maternidad y premamá, Pañales y limpieza, Ropa', '2013-02-11'),
	(4, 'Sports', 'Aerobic y Fitness, Atletismo y Running, Baloncesto, Camping, Caza, Ciclismo, Escalada, Fútbol, Golf, \r\n\r\nMontaña y esquí, Padel y Tenis, Patinaje, Pesca, Submarinismo', '2013-02-11'),
	(5, 'Electronics', 'Accesorios electrónica, Consola y videojuegos, Fotografía, Telefonía', '2013-02-11'),
	(6, 'Home', 'Casa, Jardín, Bricolaje', '2013-02-11'),
	(7, 'Computing', 'Impresoras, Monitores, Ordenador Portátil, PC Sobremesa, Periféricos, Redes, Scanners, Software', '2013-02-11'),
	(8, 'Instruments', 'Accesorios, Amplificadores, Cuerda, Guitarras, Percusión, Pianos y teclados, Viento', '2013-02-11'),
	(9, 'Books and Comics', 'Comics, Libros, Revistas', '2013-02-11'),
	(10, 'Fashion', 'Ropa, Zapatos, Complementos', '2013-02-11'),
	(11, 'OTRAS', 'Todas esas cosas que no se han podido encasillar en alguna Categoría...', '2013-02-16'),
	(12, 'Others', 'Categorías distintas a las anteriores', '2013-02-11'),
	(13, 'Weed', 'indica,xativa,etc...', '2013-05-11');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;


-- Dumping structure for table proyectocvc.producto
DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `idProducto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombreProducto` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `descripcionProducto` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `permiteMostrar` tinyint(4) DEFAULT '1' COMMENT '1=True ; 0=False',
  `permiteVender` tinyint(4) DEFAULT '1' COMMENT '1=True ; 0=False',
  `permiteCambiar` tinyint(4) DEFAULT '0' COMMENT '1=True ; 0=False',
  `precioEuros` decimal(10,2) DEFAULT NULL,
  `idCategoria` int(10) unsigned NOT NULL,
  `idUsuario` int(10) unsigned NOT NULL,
  `fecha_alta` date NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FK_producto_categoria` (`idCategoria`),
  KEY `FK_producto_usuario` (`idUsuario`),
  CONSTRAINT `FK_producto_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `FK_producto_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Dumping data for table proyectocvc.producto: ~4 rows (approximately)
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`idProducto`, `nombreProducto`, `descripcionProducto`, `permiteMostrar`, `permiteVender`, `permiteCambiar`, `precioEuros`, `idCategoria`, `idUsuario`, `fecha_alta`) VALUES
	(1, 'Book illustrations', 'Buy artbook Oswaldo Guayasamin. Good condition.', 1, 1, 0, 1.45, 9, 27, '2013-02-13'),
	(2, 'Baby carriage', 'Buy Jane Rider full transporter. Includes chassis, seat, canopy, carrycot, maxicosi, rain cover and matching bag. Official papers. Impeccable. Bought at the Corte Inglés in 2011.', 1, 1, 0, 350.00, 3, 29, '2013-02-13'),
	(3, 'Computer Intel Core i7', 'Hard disk capacity: 1000 Processor Speed: 13600 Primary Drive: DVD RW Memory RAM: 8192 Features: Network card, sound card, graphics card, USB', 1, 0, 1, 499.00, 7, 27, '2013-02-13'),
	(4, 'Computer Intel Dual Core 5.2GHZ', 'Processor Speed 5000 Primary Drive DVDRW Memory RAM 4096', 1, 0, 1, 200.00, 7, 29, '2013-02-13');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;


-- Dumping structure for table proyectocvc.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  `fecha_alta` date NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Dumping data for table proyectocvc.rol: ~3 rows (approximately)
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`idRol`, `nombre`, `descripcion`, `fecha_alta`) VALUES
	(1, 'admin', 'Todos los privilegios. ROOT. No tiene porque ser unico.', '2013-02-16'),
	(2, 'invitado', 'Solo ver.', '2013-02-16'),
	(3, 'cliente', 'Manipular sus productos y perfil. Comprar y Cambiar.', '2013-02-16');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;


-- Dumping structure for table proyectocvc.trueque
DROP TABLE IF EXISTS `trueque`;
CREATE TABLE IF NOT EXISTS `trueque` (
  `idTrueque` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `articulo1` int(10) unsigned NOT NULL,
  `descripcion` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_trueque` date NOT NULL,
  `cliente1` int(10) unsigned NOT NULL,
  `cliente2` int(10) unsigned NOT NULL,
  `articulo2` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idTrueque`),
  KEY `idCliente1` (`cliente1`),
  KEY `idCliente2` (`cliente2`),
  KEY `idArticulo1` (`articulo1`),
  KEY `idArticulo2` (`articulo2`),
  CONSTRAINT `idArticulo1` FOREIGN KEY (`articulo1`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `idArticulo2` FOREIGN KEY (`articulo2`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `idCliente1` FOREIGN KEY (`cliente1`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `idCliente2` FOREIGN KEY (`cliente2`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Dumping data for table proyectocvc.trueque: ~0 rows (approximately)
/*!40000 ALTER TABLE `trueque` DISABLE KEYS */;
/*!40000 ALTER TABLE `trueque` ENABLE KEYS */;


-- Dumping structure for table proyectocvc.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(128) COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(9) COLLATE latin1_spanish_ci NOT NULL,
  `correo` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ciudad` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `provincia` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `pais` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `idRol` int(10) unsigned NOT NULL DEFAULT '3',
  `fecha_alta` date NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `correo` (`correo`),
  KEY `rol` (`idRol`),
  CONSTRAINT `rol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Dumping data for table proyectocvc.usuario: ~0 rows (approximately)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


-- Dumping structure for table proyectocvc.venta
DROP TABLE IF EXISTS `venta`;
CREATE TABLE IF NOT EXISTS `venta` (
  `idVenta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `precio` decimal(10,0) NOT NULL,
  `unidades` int(10) NOT NULL,
  `fecha_venta` date NOT NULL,
  `estado` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `comprador` int(10) unsigned NOT NULL,
  `vendedor` int(10) unsigned NOT NULL,
  `articulo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `idComprador` (`comprador`),
  KEY `idVendedor` (`vendedor`),
  KEY `idArticulo` (`articulo`),
  CONSTRAINT `idArticulo` FOREIGN KEY (`articulo`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `idComprador` FOREIGN KEY (`comprador`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `idVendedor` FOREIGN KEY (`vendedor`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Dumping data for table proyectocvc.venta: ~0 rows (approximately)
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


INSERT INTO `proyectocvc`.`usuario` (`idUsuario`, `login`, `password`, `apellidos`, `nombre`, `telefono`, `correo`, `direccion`, `ciudad`, `provincia`, `pais`, `idRol`,`fecha_alta`) VALUES
	(1, 'pepe', 'nuJgN1/O9Q1gpMxJoLFBDwQPD5KIirF6', 'Garcia moreno', 'Jose Emilio', '654254144', 'pepecvc@cvc.com', 'calle principal 35, 2', 'Valencia', 'Valencia', 'EspaÃ±a', 1,CURRENT_DATE),
	(2, 'juan', 'kNKTFSJuIxjwgIo2MU3I5tTvxoRtVyYw', 'Martin Soler', 'Jose Juan', '647645164', 'juancvc@cvc.com', 'plaza Napoles y Sicilia 30,12', 'Valencia', 'Barcelona', 'EspaÃ±a', 1,CURRENT_DATE),
	(3, 'mari', 'B3rXcX7+qK5Ot49vtMt42NmOBIimcI7Z', 'Beltran Arenas', 'Mari Carmen', '642754154', 'maricvc@cvc.com', 'calle Nueve de Octubre 1,2', 'Leganes', 'Madrid', 'EspaÃ±a', 1,CURRENT_DATE),
	(4, 'kiko', 'baxNcwUJV8PvU8ufYkpXXPiGN/miGEOY', 'Diaz Juarez', 'Enrique', '698758969', 'kikocvc@cvc.com', 'calle Navardera 5, 3', 'Valencia', 'Valencia', 'EspaÃ±a', 1,CURRENT_DATE),
	(5, 'sandra', 'kial/AFih/vvjXNuaT96pE+9iAMsRNnB', 'Lopez Soria', 'Maria Sandra', '654525855', 'sandracvc@cvc.com', 'plaza Nelson Mandela 78, 9', 'Mallorca', 'Islas Baleares', 'EspaÃ±a', 1,CURRENT_DATE),
	(6, 'luis', '0gUTMr9B8XHGeFFlDwyNdqvHSNBXrh+B', 'Casanz Perez', 'Angel Luis', '665856587', 'luiscvc@cvc.com', 'calle Nino Bravo', 'Madrid', 'Madrid', 'EspaÃ±a', 1,CURRENT_DATE),
	(7, 'sergio', 'fCkXwN2jA0YbPCb2JRiB4NpvyfWUqDoO', 'Teruel Madrid', 'Roberto Sergio', '666452546', 'sergiocvc@cvc.com', 'avenida Cid 54,2', 'Santa Coloma Gramanet', 'Barcelona', 'EspaÃ±a', 1,CURRENT_DATE),
	(8, 'manu', '2Gixw1JCVLwVZxbCJO/VKedg0dCQNHu8', 'Fontanillas Rincon', 'Manuel', '655546785', 'manuelcvc@cvc.com', 'calle Calderon de la Barca 23, 1', 'Malaga', 'Malaga', 'EspaÃ±a', 1,CURRENT_DATE),
	(9, 'lorenzo', 'aL93YcfWMoMqbvTJdZv/FA5/+74zAupP', 'Vacas Rodriguez', 'Juan Lorenzo', '627542565', 'lorenzocvc@cvc.com', 'calle Carreres Puchalt 1,1', 'Gijon', ' Asturias', 'EspaÃ±a', 1,CURRENT_DATE),
	(10, 'carla', 'u/G9lu4Dt3jC8GsWFWC1z/5Io7uvwdMA', 'Urios Miguez', 'Maria Carla', '655578546', 'carlacvc@cvc.com', 'calle Cieza 12, 7', 'Quart de Poblet', 'Valencia', 'EspaÃ±a', 1,CURRENT_DATE),
	(11, 'abel', 'fuUm5r/GqAT0frWh1b7rU196laiib2Wj', 'Suarez Egea', 'Pedro Abel', '636312435', 'abelcvc@cvc.com', 'Calle Rio Iguazu 78, 9', 'Oviedo', ' Asturias', 'EspaÃ±a', 1,CURRENT_DATE),
	(12, 'alex', 'MhwT8X0wR347W2OwKZdP9miQX898uQbd', 'Lindes Bastos', 'Manuel Alejandro', '645124211', 'alexcvc@cvc.com', 'Calle Rosario 65,9', 'Madrid', 'Madrid', 'EspaÃ±a', 1,CURRENT_DATE),
	(13, 'joaquin', 'xOqPvSTvjGSmolrYEQlnLaSdKVOlABy3', 'Yuren ToÃ±o', 'Ximo', '645789154', 'joaquincvc@cvc.com', 'Plaza Rendicion Breda 87,1', 'Mislata', 'Valencia', 'EspaÃ±a', 1,CURRENT_DATE),
	(14, 'kike', 'W83UhZMP8OglwCKIjI5udd1x/kuyD+g5', 'Corcoles Martinez', 'Enrique', '645787548', 'kikecvc@cvc.com', 'Calle Ramirez Tome 5,9', 'Carmona', 'Sevilla', 'EspaÃ±a', 2,CURRENT_DATE),
	(15, 'ramon', 'VQYzBYMZn/o+42YmHfRGdCyPAwksGyjI', 'Navarro Haba', 'Juan Ramon', '650645046', 'ramoncvc@cvc.com', 'Plaza Republica Dominicana 43,4', 'Lorca', ' Murcia', 'EspaÃ±a', 2,CURRENT_DATE),
	(16, 'vicente', 'q+2defT52XGh+6ZTxMwdmOSJoppiOmCt', 'Soler Garcia', 'Daniel Vicente', '630450458', 'vicentecvc@cvc.com', 'Calle Picos Europa 5,5', 'Murcia', ' Murcia', 'EspaÃ±a', 2,CURRENT_DATE),
	(17, 'jesus', 'O6l6Xeg/1Aig7HZMHM/swu65SqzT4TQD', 'moreno Martin', 'Maria Jesus', '687850585', 'jesuscvc@cvc.com', 'Calle Padre Jose Maria 13,5', 'Sevilla', 'Sevilla', 'EspaÃ±a', 2,CURRENT_DATE),
	(18, 'daniel', 'yGa+NvIvfTqP9h15q/Zpsr7/29Nb6FLs', 'Arenas Diaz', 'Pedro Daniel', '696547014', 'danielcvc@cvc.com', 'Avenida Pedro Diez 76,7', 'Sant Boi del Llobregat', 'Barcelona', 'EspaÃ±a', 2,CURRENT_DATE),
	(19, 'diego', 'PypZBRV+IMSTx+3xvrOu1Hq5pDl3Kzgt', 'Soria Casanz', 'Juan Diego', '645871540', 'diegocvc@cvc.com', 'Plaza Chueca 7,9', 'Puerto de la cruz', 'Santa Cruz de Tenerife', 'EspaÃ±a', 2,CURRENT_DATE),
	(20, 'estela', 'tFGJikL8u/kFTL8ObExf9SpDhXzY2YDO', 'Madrid Fontanillas', 'Estrella', '687945120', 'estelacvc@cvc.com', 'Plaza Castilla 8,6', 'Bilbao', 'Vizcaya', 'EspaÃ±a', 2,CURRENT_DATE),
	(21, 'lucio', 'sgiutG2z1FuJn4jaQiMzVpxyT5GoNsAK', 'Rodriguez Urios', 'Lucildo', '649747514', 'luciocvc@cvc.com', 'Paseo Castellana 23, 12', 'San Sebastian', 'Vizcaya', 'EspaÃ±a', 2,CURRENT_DATE),
	(22, 'jose', 'Vf52wjk+AYrfjosYhDmCArEWiO2thE55', 'Egea Lindes', 'Angel Jose', '678946751', 'josecvc@cvc.com', 'Calle CaÃ±averal 9,10', 'Alcala de Henares ', 'Madrid', 'EspaÃ±a', 2,CURRENT_DATE),
	(23, 'anais', 'K2I8RRyLW5Al3vRsNNwv2CDtF4myrCKh', 'ToÃ±o Corcoles', 'Maria Anais', '648152154', 'anaiscvc@cvc.com', 'Plaza Corregidor Alonso Tobar 1,54', 'A coruÃ±a', 'La CoruÃ±a', 'EspaÃ±a', 2,CURRENT_DATE),
	(24, 'carlos', 'Ls6LB+1k3pB+FZzIq16KwRgOdEgI1nbu', 'Haba Soler', 'Ramon Carlos', '651245124', 'carloscvc@cvc.com', 'Calle Andorra 7, 9', 'Albacete', 'Albacete', 'EspaÃ±a', 2,CURRENT_DATE),
	(25, 'javier', '/XdXGmCCl9ZzwGmXQAkJ6oVq1Yxh8jDn', 'Martin Arenas', 'Carlos Javier', '647845645', 'javicvc@cvc.com', 'Calle Andres Mellado 87,9', 'Hospitalet', 'Barcelona', 'EspaÃ±a', 3,CURRENT_DATE),
	(26, 'marta', 'sPCeYO+7CM343RjzUFLlnTnRDzxpMyZn', 'Urios Madrid', 'Maria Marta', '649746547', 'martacvc@cvc.com', 'Avenida Arces 9,11', 'Ciudad Rodrigo', 'Salamanca', 'EspaÃ±a', 3,CURRENT_DATE),
	(27, 'catalina', '+3lXKxyjrdQMtSASnw9gPsvGcYIzSPs0', 'Corcoles Egea', 'Maria Catalina', '645784574', 'catacvc@cvc.com', 'Calle Arganda 162,15', 'Cuenca', 'Cuenca', 'EspaÃ±a', 3,CURRENT_DATE),
	(28, 'pilar', '1248As2h6M+OsiOMY4N6j4zxyxsbIWTu', 'Garcia Martin', 'Maria Pilar', '647897545', 'pilicvc@cvc.com', 'Paseo Acacias 114,6', 'Barcelona', 'Barcelona', 'EspaÃ±a', 3,CURRENT_DATE),
	(29, 'jacin', 'qqUUTgmzve04aXG3jof5xdL3gUiim0H5', 'Soler Rodriguez', 'Jacinto', '658547455', 'jacintocvc@cvc.com', 'Paseo Castellana 112,2', 'Madrid', 'Madrid', 'EspaÃ±a', 3,CURRENT_DATE),
	(30, 'administrador', 't5XIC5lUILrVomu6+uH3yXkCsEPD9lz1', 'Sorita Belenguer', 'Alejandrito', '659579674', 'alexmluisa@gmail.es', 'calle principal 35, 4', 'mislata', 'Valencia', 'España', 1,CURRENT_DATE),
	(33, 'logongas', '7kQiUFTcLuMYftfOL1IVx8/wjVZQM5ml', 'profe', 'lorenzo', '', '', '', '', '', '', 1,CURRENT_DATE),
	(34, 'alejo', 'r+LHqTZnJ6LuLJyQ/8l5I8IfY/7BZP5N', 'Soria', 'Alex', '659579673', 'alexmluisa@gmail.com', 'calle llansol 25, 26', 'mislata', 'Valencia', 'España', 1,CURRENT_DATE);
