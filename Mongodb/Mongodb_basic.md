# MongoDB
Mongodb是一个介于关系数据库和给关系数据库之间的产品。支持的数据结构非常松散，类似于json的bson格式  
说明:BSON是一种计算机数据交换格式，主要被用作MongoDB数据库中的数据存储和网络传输格式。 它是一种二进制表示形式，能用来表示简单数据结构、关联数组(MongoDB中称为“对象”或“文档”)以 及MongoDB中的各种数据类型。BSON之名缘于JSON，含义为Binary JSON(二进制JSON)。
- MongoDB的特点
  - 面向集合储存，易存储对象类型的数据
  - 支持动态查询
  - 支持完全索引，包含内部对象
  - 支持复制和故障恢复
  - 支持多种开发语言
  - 使用高效的二进制数据存储
- 使用的场景
  - 网站实时数据处理，它非常的适合实时的插入，更新和查询，并具备网站实时数据存储所需的复制及高度伸缩性
  - 缓存。由于性能很高，它适合作为信息基础设施的缓存层。在系统重启后，由它搭建的持久化缓存层可以避免下层的数据源过载
  - 高伸缩性的场景。非常适合由舒适或数百台服务器组成的数据库，它的路线图中已经包含对MapReduce引擎 的内置支持
- 不适用的场景
  - 要求高度事务性的系统
  - 传统的商业智能应用
  - 复杂的跨文档级连查询
 
## mongodb 
- mongdb 的开启
  - 进入bin文件夹 ./mongod -f mongodb.conf
  - 服务器登录 ./mongo
- mongodb支持的数据类型
  - null {"x" : null}
  - {"x" : true}
  - {"x" : 2.32} or {"x" : 2}
  - {"x" : NumberInt(2)}
  - {"x" : NumberLong(2)}
  - {"x" : "123@qq.con"}
  - {"x" : new Date()}
  - {"x" : /zjemgzeshi/i}
  - {"x" : ["kaikeba", "kaikeba.com]} 数组
  - {"x" : {"kaikeba", "kaikeba.com"}}
- _id和Object（Mongodb中每一个文档都有一个“id”键，“id”可以是任何类型，不指“_id“时，会生成object对象，示例{"_id" : ObjectId()}
-  ObjectId是一个12字节（24个16进制数字）的存储空间，ObjectId的12字节数据组织方式如下：前四位是时间戳，三位机器码，两位PID，三位计数器
- Mongo的操作指令
  - exit 退出
  - show dbs 所有的库
  - use dbName 跳转到某个库；创建某个库
  - db 显示当前所在的库
  - show collections 显示库里的表
  - db.dropDatabase() 删除库
  - db.createCollection() 创建一个表
  - db.collectionName.drop() 删除表
  - db.oldCollectionName.renameCollection("newName") 表重命名
  - db.collectionName.insert() 插入一条数据
  - db.collectionName.save() 插入一条数据
  - db.collectionName.find() 查找数据
  - db.collectionName.find().pretty()
  - db.collectionName.find({"age" : 26}) 当age为26
  - db.collectionName.find({"age" : {$gt : 100}}) 当age大于100
  - db.collectionName.find({"age" : {$gte : 100}}) 大于等于
  - db.collectionName.find({"age" : {$lt : 100}}) 小于
  - db.collectionName.find({"age" : {$lte : 100}}）小于等于
  - db.collectionName.find({"age" : {$lt : 100. $gt : 50}}) 之间
  - db.collectionName.find({"age" : {$ne : 50}}) 不等于
  - db.collectionName.find({key1:value1, key2:value2}).pretty() 并列
  - db.collectionName.find({$or: [ {key1: value1}, {key2:value2}]}).pretty() 或
  - b.collectionName.remove({}) //条件删除:remove({key:value}) //删除满足条件的一条数据:remove({key:value},1)
  - db.collectionName.findOne();
  - db.collectionName.find({},{name:1,age:1,sex_orientation:true})
  - db.collectionName.distinct('sex') 查找去重
  - db.collectionName.find().sort({salary:1}) //升序
  - db.collectionName.find().sort({salary:-1}) //降序
  - db.collectionName.find().count() 计算数量
  - db.collectionName.find().limit(number) 
  - db.collectionName.find().limit(NUMBER).skip(NUMBER)
  - `db.collectionName.update(<query>,<update>,{upsert: <boolean>, multi: <boolean>) ` 更新数据
    - query: update的查询条件; update : update的对象和一些更新的操作符(如$,$inc...)等，也可以理解为sql update查询内set后面
的; upsert : 可选，如果不存在update的记录，是否插入objNew,true为插入，默认是false，不插入; multi : 可选，mongodb 默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出 来多条记录全部更新。
    - db.collectionName.update({name:'tom'},{$set:{age:23}},false,true)

## mongoDB索引
db.collection.createIndex(keys, options)
- background Boolean 建索引过程会阻塞其它数据库操作，background可指定 以后台方式创建索引，即增加 "background" 可选参数。 "background" 默认值为false。
- unique Boolean 建立的索引是否唯一。指定为true创建唯一索引。默认值 为false.
- name string 索引的名称。如果未指定，MongoDB的通过连接索引的 字段名和排序顺序生成一个索引名称。
- sparse Boolean 对文档中不存在的字段数据不启用索引;这个参数需要特 别注意，如果设置为true的话，在索引字段中不会查询出 不包含对应字段的文档.。默认值为 false.
- expireAfterSeconds integer 指定一个以秒为单位的数值，完成 TTL设定，设定集合的 生存时间。
- v index version 索引的版本号。默认的索引版本取决于mongod创建索引 时运行的版本。
- weights document 索引权重值，数值在 1 到 99,999 之间，表示该索引相对 于其他索引字段的得分权重。
- default_language string 对于文本索引，该参数决定了停用词及词干和词器的规则 的列表。 默认为英语
- language_override string 对于文本索引，该参数指定了包含在文档中的字段名，语 言覆盖默认的language，默认值为 language.
#### 索引分类
- 默认索引 MongoDB有个默认的“id” 键上，它是默认索引，索引名叫“_id_”，是无法被删除的。我们可以通过以下方式查看:db.collectionName.getIndexes()
- 单列索引 在单个键上创建的索引就是单列索引，例如我们要在Users集合上给title键创建一个单列索引，语法如下 db.collectionName.createIndex({"title":1})
- 组合索引 另外，我们还可以同时对多个键创建组合索引。如下代码创建了按照“UserId”正序，“UserName” 逆序的组合索引: db.collectionName.createIndex({"userid":1,"username":-1})
- 唯一索引 唯一索引限制了对当前键添加值时，不能添加重复的信息。值得注意的是，当文档不存在指定键 时，会被认为键值是“null”，所以“null”也会被认为是重复的，所以一般被作为唯一索引的键，最好都要 有键值对。db.collectionName.CreateIndex({"UserId":1}, { unique: true });
- TTL索引 TTL指生命周期的意思。即存储的document存储带有过期时间属性，超过生命周期自己主动删除。 像日志数据、系统自己主动产生的暂时数据、会话数据等均符合这一场景。构建方式如: db.log_events.createIndex( { "createdAt": 1 }, { expireAfterSeconds: 3600 } )
- 删除索引 新手常陷入的误区是，认为集合被删除，索引就不存在了。关系型数据库中，表被删除了，索引也 不会存在。在MongoDB中不存在删除集合的说法，就算集合数据清空，索引都是还在的，要移除索引 还需要手工删除。db.collectionName.dropIndexes()

## MongoDB的备份与恢复
- mongodump命令可以通过参数指定导出的数据量级转存的服务器。
- **mongodump -h dbhost -d dbname -o dbdirectory**
  - -h 服务器地址，也可以加上端口号 127.0.0.1:27017；  -d 需要备份的数据库实例；-o 备份数据存放的位置
  - mongodump --host runoob.com --port 27017
  - mongodump --dbpath /data/db/ --out /data/backup/
  - mongodump --collection mycol --db test
- mongodb使用 mongorestore 命令来恢复备份的数据。** `mongorestore -h <hostname><:port> -d dbname <path>` **
  - --host <:port>, -h <:port>: MongoDB所在服务器地址，默认为: localhost:27017
  - --db , -d : 需要恢复的数据库实例，例如:test，当然这个名称也可以和备份时候的不一样，比如test2
  - --drop: 恢复的时候，先删除当前数据，然后恢复备份的数据。就是说，恢复后，备份后添加修改的数据都 会被删除，慎用哦!
  - mongorestore 最后的一个参数，设置备份数据所在位置，例如:c:\data\dump\test。 你不能同时指定 和 --dir 选项，--dir也可以设置备份目录。
  - --dir: 指定备份的目录 你不能同时指定 和 --dir 选项。 

## 集群搭建
1. 准备三台虚拟机服务器，并各自安装好mongoDB
2. 修改mongodb.conf文件，添加replSet配置(三台都需要修改成同一个名称)，然后启动服务器 replSet=rep1
3. 初始化复制集 rs.initiate({_id:'rep1',members:[{_id:1,host:'192.168.197.132:27017'}, {_id:2,host:'192.168.197.133:27017'},{_id:3,host:'192.168.197.134:27017'}]}) 说明: _id 指复制集名称，members指复制集服务器列表，数组中的_id是服务器唯一的id,host服务器主 机ip
4. 查看集群状态 rs.status()
5. 主复制集添加仲裁者(arbiter) 要求集群是偶数 rs.remove("ip:端口号") //删除从节点 在一主一从关系中，任意节点宕机都无法选举出主节点，无法提供写操作，此时需要加入仲裁者节点即 可。rs.addArb("ip:端口号")
   