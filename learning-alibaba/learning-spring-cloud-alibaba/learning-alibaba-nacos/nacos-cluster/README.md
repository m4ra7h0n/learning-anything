启动文件在resources文件夹下

# 踩坑
1.注意，如果Nacos和Mysql版本不匹配，Nacos自带连接驱动版本过低，会启动不了，网上搜一下Nacos和Mysql版本就行了

2.连接数据库的时候要注意%是否可以登录root
`use mysql;
update user set host = '%' where user = 'root';
flush privileges; `