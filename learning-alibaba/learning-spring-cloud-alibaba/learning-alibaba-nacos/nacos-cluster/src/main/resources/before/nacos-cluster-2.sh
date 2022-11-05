docker run -it \
 -e PREFER_HOST_MODE=ip \
 -e MODE=cluster \
 -e NACOS_SERVERS="10.151.206.252:9901 10.151.206.252:9902 10.151.206.252:9903" \
 -e SPRING_DATASOURCE_PLATFORM=mysql \
 -e MYSQL_SERVER=10.151.206.252 \
 -e MYSQL_SERVICE_PORT=3306 \
 -e MYSQL_SERVICE_DB_NAME=ag_nacos \
 -e MYSQL_SERVICE_USER=root \
 -e MYSQL_SERVICE_PASSWORD=xiaozijian2000 \
 -p 9902:8848 \
 --name nacos2 \
 --restart=always \
 nacos/nacos-server
