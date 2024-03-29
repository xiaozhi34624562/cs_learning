# Kafka Usage

### kafka的术语

- 传输消息（Record）的方式
  - 点对点模型（Peer to Peer）：A系统发送的消息只能被B系统接收
    - kafka中实现这种P2P模型的方法是引入了消费者组（Consumer Group），多个消费者共同组成一个组来消费一组主题。这组主题中的每个分区都只会被组内的一个消费者实例消费，其他消费者实例不能消费它。引入消费者组的目的，是为了提升消费端的吞吐量，多个消费者实例同时消费，增加整个消费端的吞吐量（TPS）
      - 消费者组里面的所有消费者实例不仅瓜分订阅主题的数据，还可以彼此协助。假设组里的某个实例挂掉了，kafka可以自动检测到，然后把这个failed实例之前负责的分区转移给其他活着的消费者。
  - 发布/订阅模型：在一个Topic的下，多个发布者（publisher）和多个接收者（Subscriber）交流信息。
  - 采用这种消息传递方式，而不是A系统直接给B系统发送消息的原因是为了削峰填谷。
  - 消费者位移（Consumer Offset）：每个消费者在消费消息的过程中必然需要有一个字段记录他当前消费到了分区的那个位置上。
  - 分区位移：一旦消息被成功写入到一个分区上，它的位移就是固定的了，

- kafka的高可用
  - 一个kafka集群是由多个Broker（服务端）组成，Broker负责接收和处理客户端发送过来的请求，以及对消息进行持久化。
  - 备份机制（Replication）。kafka定义了两类副本：领导者副本（Leader Replica）和追随者副本（Follower Replica）。LR提供对外服务，与客户端程序进行交互；FR只能被动的追随LR。生产者总是向LR写消息，而消费者总是从LR读取消息。FR向领导者副本发送请求，保持与LR的同步
- Scalability（单台Broker机器容纳的数据达到了极限）
  - 分区（Partitioning）：每个主题会划分成多个分区，每个分区是一组有序的消息日志。生产者生产的每条消息只会被分发到一个分区中，如果向一个双分区的主题发送一条消息，这条消息要么在分区 0 中，要么在分区 1 中。
- Kafka的三层消息架构
  - 第一层，每个主题可以分配M个分区，而每个分区又可以配N个副本
  - 第二层，每个分区的N个副本中，只有一个充当领导者角色，对外提供服务，其他N-1个副本是追随者副本，只提供数据冗余用
  - 第三层，分区中包含若干消息，每条消息的位移从0开始，依次递增
  - 客户端程序只能与分区的领导者副本进行交互
- kafka Broker是如何做持久化数据
  - kafka使用消息日志（Log）来保存数据，一个日志就是磁盘上一个只能追加写（Append- only）消息的物理文件。因为只能追加写入，避免了缓慢的随机I/O操作，改成了性能较好的顺序I/O。
  - 定期删除消息以回收磁盘。通过日志段（Log Segment）机制，在Kafka底层，一个日志又进一步细分成多个日志段，消息被追加写到最新的日志段中，当写满一个日志段后，kafka会自动切分出一个新的日志段，并将老的日志段封存起来，kafka在后台还有定时任务，定期的检查老的日志段是否能够被删除，从而实现磁盘空间的回收

- kafka与其他主流大数据流式jisuankuangjia相比的优势
  - 更容易实现端到端的正确性
  - kafaka Streams是一个用于搭建实时流处理的客户端库，而非是一个完整的功能系统

