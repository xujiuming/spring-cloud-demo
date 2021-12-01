
* mysql.yaml

```yaml
version: "2"
services:
  nacos:
    image: mysql:8.0.27
    container_name: db
    command: --default-authentication-plugin=mysql_native_password
    environment:
    - MYSQL_ROOT_PASSWORD=123456
    volumes:
    - /home/ubuntu/mysql/data:/var/lib/mysql
    - /home/ubuntu/mysql/config:/etc/mysql/conf.d
    ports:
    - "3306:3306"
```

* 启动

```shell
docker-compose -f mysql.yaml up
```

