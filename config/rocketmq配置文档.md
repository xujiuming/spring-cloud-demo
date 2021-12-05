
下载文件
```shell
wget https://dlcdn.apache.org/rocketmq/4.9.2/rocketmq-all-4.9.2-bin-release.zip
```
修改 runnamesrv.sh  runbroker.sh 中的 xmx xms参数     

```shell
nohup sh bin/mqnamesrv &
tail -f ~/logs/rocketmqlogs/namesrv.log
sh bin/mqshutdown namesrv
```
```shell
nohup sh bin/mqbroker -n localhost:9876 &
tail -f ~/logs/rocketmqlogs/broker.log 
sh bin/mqshutdown broker
```