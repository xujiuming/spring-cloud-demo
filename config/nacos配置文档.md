参考:
https://github.com/nacos-group/nacos-docker  

* nacos.yaml

```yaml
version: "2"
services:
  nacos:
    image: nacos/nacos-server:2.0.3
    container_name: nacos-standalone
    environment:
    - PREFER_HOST_MODE=hostname
    - MODE=standalone
    volumes:
    - /home/ubuntu/nacos/logs/:/home/nacos/logs
    - /home/ubuntu/nacos/custom.properties:/home/nacos/init.d/custom.properties
    ports:
    - "8848:8848"
    - "9848:9848"
# 先屏蔽监控相关的组件      
#  prometheus:
#    container_name: prometheus
#    image: prom/prometheus:latest
#    volumes:
#      - ./prometheus/prometheus-standalone.yaml:/etc/prometheus/prometheus.yml
#    ports:
#      - "9090:9090"
#    depends_on:
#      - nacos
#    restart: on-failure
#  grafana:
#    container_name: grafana
#    image: grafana/grafana:latest
#    ports:
#      - 3000:3000
#    restart: on-failure
```

* 启动 

```shell
docker-compose -f nacos.yaml up -d
```

