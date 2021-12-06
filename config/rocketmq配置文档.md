
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
nohup sh bin/mqbroker -n localhost:9876 -c /home/ubuntu/rocketmq/rocketmq-all-4.4.0-bin-release/conf/broker.conf &
tail -f ~/logs/rocketmqlogs/broker.log 
sh bin/mqshutdown broker
```
```shell
# 查看节点信息 
sh bin/mqadmin clusterList -n localhost:9876
#查看所有消费组group:
sh mqadmin consumerProgress -n localhost:9876
#查看指定消费组下的所有topic数据堆积情况：
sh mqadmin consumerProgress -n localhost:9876 -g warning-group
#查看所有topic :
sh mqadmin topicList -n localhost:9876
#查看topic信息列表详情统计
sh mqadmin topicstatus -n localhost:9876 -t topicWarning
# 新增topic
sh mqadmin updateTopic –n localhost:9876 –c DefaultCluster –t topicWarning
#删除topic
sh mqadmin deleteTopic –n localhost:9876 –c DefaultCluster –t topicWarning
#集群消息
sh mqadmin  clusterList -n localhost:9876
```

```text
# 超时 10000ms
waitTimeMillsInSendQueue=10000
# 自动创建topic 
autoCreateTopicEnable=true
# 设置brokerIp 
brokerIP1=81.68.206.251
```