####  mybatis的学习Demo.

```sql
CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `birthday` date DEFAULT NULL,
  `sex` char(10) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

```

1. 原始的dao开发方法(程序需要开发dao interface 和dao impl)
2. mybatis的mapper接口(相当于dao接口)代理开发方法
