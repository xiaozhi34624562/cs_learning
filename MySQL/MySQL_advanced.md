# MySQL_advanced
## 子查询和表链接
### 子查寻(subquery)
嵌套在其他查询中的查询
- 利用子查询进行过滤
- 作为计算字段使用子查询
### 关系表(join)、表链接
- 关系表：把信息分解成多个表，一类数据一个表，个表通过常用的值相互关联。
- 表链接：在一条select语句中关联表
- 使用表别名时，可以使用as
### 自联结
### 外部链接(left join; right join)
### 组合查询UNION
- 必须由两条或者两条以上的select语句组成，语句之间用关键字UNION分隔
- UNION中的每个查询必须包含相同的列、表达式和聚集函数
- 列数据类型必须兼容，类型不必完全相同，但是必须是DBMS可以隐含的转换的类型
- UNION会自动去重，如果想得到全部的数据，使用UNION ALL
## 事物
- 原子性：指事务必须是一个原子的操作序列列单元。事务中包含的各项操作在一次执⾏行行过程中，只允许出现两种状态之⼀。全部执⾏行行成功;全部执⾏行行失败
- 一致性：指事务的执⾏不能破坏数据库数据的完整性和一致性，一个事务在执⾏之前和执行之后，数据库都必须处以一致性状态。
- 隔离性:一个事务内部的操作及使⽤用的数据对其它并发事务是隔离的，并发执⾏行行的各个事务是不不能互相⼲干扰的。
- 持久性：事务的持久性是指事务一旦提交后，数据库中的数据必须被永久的保存下来。即使服务器器系统崩溃或服务器器宕机等故障。只要数据库重新启动，那么一定能够将其恢复到事务成功结束后的状态。
### 事物的语法
1. start transcation;/begin;开始
2. commit; 是当前的修改得到确认
3. rollback;是当前的修改被放弃
### 事物并发的问题
- 脏读：读取到了了没有提交的数据, 事务A读取了了事务B更更新的数据，然后B回滚操作，那么A读取到的 数据是脏数据。
- 不可重复：同一条命令返回不同的结果集(更新).事务A多次读取同一数据，事务B在事务A 多次读取的过程中，对数据作了更新并提交，导致事务A多次读取同一数据时，结果不一致。
- 幻读：重复查询的过程中，数据就发⽣生了了量量的变化(insert， delete)。
