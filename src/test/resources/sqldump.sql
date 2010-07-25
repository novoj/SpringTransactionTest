DROP TABLE IF EXISTS `t_user_property`;
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_LOGIN_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `propertyName` varchar(255) NOT NULL,
  `propertyValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_USER_PROPERTY_UNIQUE` (`idUser`,`propertyName`),
  CONSTRAINT `t_user_property_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

INSERT INTO `t_user` VALUES ('72', 'veska', 'tajneHeslo');
INSERT INTO `t_user` VALUES ('73', 'franc', 'superTajneHeslo');
INSERT INTO `t_user` VALUES ('74', 'cap', 'jsemBuh');