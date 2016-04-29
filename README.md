#test_mybatis
###mybatis开发Dao有两种方式:
1. 原始的dao开发方法(程序需要开发dao interface 和dao impl)
2. mybatis的mapper接口(相当于dao接口)代理开发方法


```sql
CREATE TABLE `att_detail` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `start_time` datetime default NULL,
  `end_time`   datetime default NULL,
  `minute` bigint(20) default NULL,
  `month` bigint(20) default NULL,
  `user_no` varchar(255) default NULL,
  `card_no` bigint(20) default NULL,
  `door_no` bigint(20) default NULL,
  `door_name` varchar(255) default NULL,
   `create_time`   datetime default NULL,
    `update_time`   datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `birthday` date DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
```
