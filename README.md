```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fio` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `login_password` (`login`,`password`),
  KEY `fio` (`fio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

```