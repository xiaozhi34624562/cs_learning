# Redis(Remote Dictionary Server)
## Redis的优势
- Redis与其他key-value缓存产品的特点
  - Reids支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加在进行使用
  - Redis不仅仅支持简单的key-value类型数据，同时还提供list，set，zset，hash等数据结构的储存
  - Redis支持数据的备份，及master-slave（主从）模式的数据备份
- Redis的优势
  - 性能极高，读的速度11万/s，写的速度8.1万/s
  - 丰富的数据类型，支持二进制案例的String，Lists，Hashes，Sets及Ordered Sets数据类型操作
  - 原子，具有原子性，还支持对几个操作合并后的原子性执行
  - 丰富的特性，支持publish/subscribe，通知，key过期等等特性
  - 采用单线程，避免了不必要的上下文切换和竞争条件，也不存在多进程或者多线程导致的切换二消耗CPU，不用去考虑各种锁的问题，不存在加锁释放锁的操作，没有因为可能出现死锁而导致的性能消耗
  - 可以使用多路I/O复用模型，非阻塞I/O.
## <a href="http://doc.redisfans.com/index.html">Redis 常用指令</a>
- String类型
  - set key value e.g. set k1 zhangsan 
  - get key  e.g. get k1
  - mset k2 zhangsan k3 lisi k4 wangwu
  - mget k2 k3
  - del k2
- 自增自减
  - incr key e.g. ince k1
  - decr key
  - incrby key
  - decby key
- Hash散列：提供了字段和字段值的映射，字段值只能是字符串类型，不支持散列类型，集合类型等其他类型。插入操作返回1，更新操作返回0
  - hset key field value
  - hset k5 name wangwu
  - hset k5 age 12
  - hget name
  - hget age
  - hmset k6 name zhangsan age 12 id 1234
  - hmgetall k6
  - hdel k6 id
- 队列list：采用链表来储存，特点，增删快，查询慢（LinkedList）这个队列是有序的
  - lpush key value (value ...)
  - lpop key
  - rpush key value (value...)
  - rpop key
  - llen key (列表中元素的个数)
  - lrange key start stop
- Set集合：无序，不可重复
  - sadd key member (member...)
  - srem key member (member...) 删除元素
  - smembers key 获得集合中的所有元素
  - sismember key member
- Zset有序集合：sortedSet，可排序但是唯一。给set元素一个分数，然后通过这个分数进行排序。
  - zadd key score member(score member...)
  - zrevrange key start stop (withscores可以显示出分数)
  - zscore key member
  - zrem key member
  - zincreby key score member
- HyoperLogLog命令：给出可以接受多个元素的输入，并给出输入元素的基数估算值
  - pfadd key element(element ...) 将指定的元素添加到制定的HyperLogLog中
  - pfcount key(key ...) 返回给丁HyperLogLog的基数估算值
  - pfmerge destkey sourcekey (sourcekey ...) 将多个HyperLogLog合并为一个HyperLogLog
- 其他命令
  - keys user* 查询以user开头的key
  - keys * 查询所有的key
  - exists key 确认key是否存在
  - del key 删除一个key
  - rename odlKey newKey
  - type key 
  - expire key seconds e.g. expire k1 60 多少秒后自动删除这个key
  - ttl key 查询key剩余的生存时间
  - persist key 清楚生存时间
  - info 过去服务器信息和统计
  - flushdb 删除当前库中的所有key
  - flushall 删除所有库中所有key
  - select 1 库切换

## Redis事务管理
事物是一个单独隔离的操作，事物中所有的命令都会序列化按顺序的执行。事物在执行的过程中，不会被其他客户端发送来的命令请求所打断。要不全部命令执行，要不全部不执行。
- multi 开始一个事物
- 输入命令
- exec 执行命令

## Redis持久化方式
由于Redis的值放在内存中，为防止突然断电，需要对数据进行持久化备份（将内存数据保存在硬盘中）。
- RDB持久化
  - RDB是以二进制文件，在某个时间点将数据写入一个临时文件，持久化结束后，用这个临时文件替换上次持久化的文件，以达到数据恢复。
  - 优点：使用单独子进程来进行持久化，主进程不会进行任何IO操作，保证了redis的高性能
  - 缺点：RDB是间隔一段时间进行持久化，如果在持久化之间redis发生故障，会发生数据丢失。所以这种方式更适合数据要求不严谨的时候
- AOF持久化
  - Append-Only-File，将“操作+数据”以格式化指令的方式追加到操作日志文件的尾部，在append操作返回后（已经写入到文件或者将要写入文件），才进行实际的数据变更，“日志文件”保存了历史所有的操作过程；当server需要数据恢复时，可以直接replay此日志文件。aof相对可靠，文件内容都是字符串，非常容易阅读和解析。
  - 优点：可以保持更高的数据完整性，如果设置追加 file 的时间是 1s，如果 redis 发生故障，最多会丢失 1s 的数 据;且如果日志写入不完整支持 redis-check-aof 来进行日志修复;AOF 文件没被 rewrite 之前(文件过大时会对 命令进行合并重写)，可以删除其中的某些命令(比如误操作的 flushall)。
  - 缺点：aof文件比rdb文件打大，且恢复速度慢

## Redis主从复制
- 持久化保证了即使redis服务重启也不会丢失数据，但是当redis服务器的硬盘损毁了，可能会导致数据丢失，通过redis的主从复制机制就可以避免这种单点故障
- 主redis的数据和从上的书库保持实时同步，当redis写入数据时通过主从复制到两个从服务上
- 主从复制不会阻塞master，在同步数据时，master可以继续处理client请求
- 主机master配置，无需配置
##### 操作
- redis.conf里面需要把replicaof设置主机的ip（bind）和端口号
- redis.conf里面的port修改成从机端口号，如6380
- logfile, dbfilename, daemonize, pidfile
## Redis哨兵模式
可以监控主数据库和从数据库是否运行正常，主数据库出现故障后，自动将从数据库转化为主数据库