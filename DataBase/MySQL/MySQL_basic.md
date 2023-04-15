# MySQL数据库
## 数据库的介绍
- 数据库（database）：按照数据结构来组织、存储和管理数据的仓库；专业的数据库是对数据进行创建，访问，管理，搜索等操作的软件，比起我们自己用文件读写的方式对象数据进行管理更加方便，快速和安全。
- 作用：数据持久保存；方便数据的存储和查询；可以处理并发访问；更加安全的访问管理机制
- 常见的数据库：
  - 关系型数据库：MySQL，Oracle，PostgreSQL
  - 非关系型数据库：Redis内存数据库，MongoDB文档数据库
- 数据库语法特点
  - 可以换行，但是要以分号结尾
  - 命令不区分大小写，关键字和函数建议大写
  - 命令打错了后，不可以取消，可以用\c取消
<br>

## mysql的基本命令
  - mysql -u root -p 登录mysql，在终端 
  - mysql -h localhost -u root -p   :-h 服务器地址 -u登录账号 -p回车后输入密码 -P（端口号）
  - show databases; 查看所有的库
  - use 库名; 进入库
  - show tables; 查看表格
  - create database PackageName default charset=utf8; 创建一个库
  - create table tableName(name1 type1, name2 type2)engine=innodb default charset=utf8; 创建一个表
  - drop database packageName; 删库
  - drop table tableName; 删表
  - desc tableName; 展示表结构
  - show create table tableName; 查看建标语句
  
  - \c 终止命令
  - \G 格式化输出，竖立显示
  - \s 查看服务器端信息
  - \q 退出当前命令行模式
  - \h 查看帮助
  ``` sql
  create table if not exists user(name1 type1, name2 type2)engine=innodb default charset=utf8;

  create table user(
      name varchar(20),
      age int,
      sex char(1)
  )engine=innodb default charset=utf8;
  ```
  - select * from tableName; 查看整个表格;
  - select column1,column2 from tableName; 查看这个表格的两列;
  - select * from tableName where name1=value1;
  - insert into user(name,age,sex) values('admin',26,'男'),('xiao',23,'女');
  - insert into user value('xiao',23,'女');
  - insert into user(name,sex) value('admin','男') 
  - update tableName set name=value where id=1;
  - update tableName set name1=value1,name2=value2 where id>2;
  - update tableName set 字段=字段+值 where id>2;
  - delete from tableName where 字段=某个值;
  - 库和表之间的关系：库就像文件夹，表就像一个excel文件，表中可以定义不同的列（字段），表中可以根据结构区储存很多的数据
<br>

## MySQL的数据类型
整型、浮点型、字符串、日期等
- 字符串数据类型，如名字，地址，电话号码，邮政编码等
  - char 定长串，接受长度固定的字符串，长度是在创建表的时候指定的，会被分配固定的储存空间用于存放数据 char（7）
  - varchar 变长串，灵活但是性能不好，处理定长列快得多
  - text 变长文本类型存储。
  - CHAR 1~255个字符 
  - ENUM 接受最多64k个串
  - LONGTEXT 最大长度为4GB
  - MEDIUMTEXT 最大长度为16K
  - SET 最多64k
  - TEXT 最大长度为64k
  - TINYTEXT 最大长度为255字节
  - VARCHAR 长度可变，最大255字节
- 数值类型（有符号数，无符号数）
  - BIT 1-64位
  - BIGINT 
  - BOOLEAN
  - DECIMAL 精读可变浮点值 DECIMAL（8， 2）
  - DOUBLE 双精度可变浮点值
  - FLOAT 单精度浮点值
  - INT 整数，-20亿到20亿，0-40亿
  - MEDIUMINT 整数，-800万到800万，0-1600万
  - REAL 4字节的浮点值
  - SMALLINT 整数，-3万到3万，0-6万
  - TINYINT 整数，-128～127， 0～255
- 日期和时间类型
  - DATE 表示1000-01-01～9999-12-31
  - DATETIME date 和 time 的组合
  - TIMESTAMP 功能和datetime一样，但范围小
  - TIME 格式为HH：MM：SS
  - YEAR 用两位数字表示，范围是70～69，用四位数字表示，1901～2155
- 二进制数据类型
  - BLOB
  - MEDIUMBLOB
  - LONGBLOB
  - TINYBLOB
- 表的字段约束
  - unsigned 无符号（表示正数，如果没有，则政府都可以）
  - char(5) varchar(7) 表示字符串的长度 int(4)没有意义 int(10)有符号 int(11)无符号 int(4) unsigned zerofill 会自动补零
  - not null 不能为空
  - dafault 设置默认值
  - primary key 主键不能为空， 且唯一，一般和自动递增一起使用
  - auto_increment 一般用于主键，数值会自动加1
  - unique 唯一索引（数据不能重复），可以增加查询速度，但是会降低插入和更新的速度
- 运算符
  -  +、 -、 *、 /、 %
  -  =、 >、 <、 >=、 <=、!=
  -  in、not in、is null、is not null、like、between、and
  -  and、or、not
  -  like: 支持特殊符号%和_
  
- 主键
  - 表中每一行都应该有可以唯一标识自己的一列，不允许NULL，不在主键中使用可能更改的值，可以使用自增整数型，可以使用多个列作为联合主键，不常用
<br>

## MySQL数据库与数据表操作
- 创建表
``` sql
create table users(
    -- 创建ID字段，为正整数，不允许为空 主键，自动递增
    id int unsigned not null primary key auto_increment,
    -- 创建 存储 名字的字段，为字符串类型，最大长度 5个字符，不允许为空 username varchar(5) not null,
    -- 创建存储 密码 的字段，固定长度 32位字符， 不允许为空
    password char(32) not null,
    -- 创建 年龄 字段，不允许为空，默认值为 20 age tinyint not null default 20
)engine=innodb default charset=utf8; 
# 查看表结构
 desc users;
#查看建表语句
show create table users;
```
- 修改表
  - 添加字段
    - alter table user add num int not null;
    - alter table user add email varchar(50) after age;
    - alter table user add phone char(11) not null after age;
    - alter table user add aa int first;
  - 删除字段
    - alter table user drop aa; 
  - 修改字段(change,modify change可以修改字段名，modify不能修改字段名)
    - alter table user modify num tinyint not null dafault 12;
    - alter table user change num nn int;
  - 修改表名
    - alter table user rename as newName;
  - 更改表中的自增值
    - alter table user auto_increment = 1000
  - 修改表的引擎
    - alter table user engine = 'myisam';
  - 删除表
    - drop table tableName;
<br>

## 数据库查询
- and or
- like+%/_ 模糊搜索，尽量不要去使用，如需使用时，尽可能不要把通配符放在开头处
``` sql
使用 % 模糊搜索。%代表任意个任意字符
-- 查询name字段中包含五的
select * from users where name like '%五';
-- 查询name字段中最后一个字符 为 五的
select * from users where name like '%五';
-- 查询name字段中第一个字符 为 王 的
select * from users where name like '王%';
-- 使用 _ 单个的下划线。表示一个任意字符，使用和%类似 -- 查询表中 name 字段为两个字符的数据
select * from users where name like '__';
-- 查询 name 字段最后为五，的两个字符的数据 
select * from users where name like '_五';
```
- MySQL 中的统计函数max(),min(),count(),sum(),avg()
``` sql
select count(*) from users;
-- 使用id来进行统计的时候，如果值为null则不被计算在内
select count(id) from users;
select max(age) as max_age,min(age) min_age,sum(age) as sum_age,avg(age) as avg_age from users;
``` 
- Group By 分组：根据一个或多个列对结果集进行分组
- having 是在分组聚合计算后，对结果再一次进行过滤
```sql
-- 很明显按照上面的需要，可以写出两个语句进行分别统计
select count(*) from users where sex = '女'; select count(*) from users where sex = '男';
-- 可以使用分组进行统计，更方便
select sex,count(*) from users group by sex;
select classid,sex,count(*) as num from users group by classid,sex;
select classid,count(*) as num from users group by classid having num >=5;
```
- order by 排序
``` sql
 -- 按照年龄对结果进行排序，从大到小 
 select * from users order by age desc;
-- 从小到大排序 asc 默认就是。可以不写 
select * from users order by age;
-- 也可以按照多个字段进行排序
select * from users order by age,id;
-- 先按照age进行排序，age相同情况下，按照id进行排序 select * from users order by age,id desc;
```
- limit 数据分页（limit n 提取n条数据；limit m，n 跳过m条数据，提取n条数据
``` sql
-- 查询users表中的数据，只要3条 
select * from users limit 3;
-- 跳过前4条数据，再取3条数据 
select * from users limit 4,3;
-- limit一般应用在数据分页上面
-- 例如每页显示10条数据，第三页的 limit应该怎么写? 思考 第一页 limit 0,10
第二页 limit 10,10
第三页 limit 20,10
第四页 limit 30,10
-- 提取 user表中 年龄最大的三个用户数据 怎么查询? 
select * from users order by age desc limit 3;
```
<br>

## MySQL数据库导入导出和授权
- 数据库数据导出（不要进入mysql，然后输入以下命令 导出某个库中的数据） 
  - mysqldump -u root -p tlxy > ~/Desktop/code/tlxy.sql
- 数据库中的表导出
  - mysqldump -u root -p tlxy tts > ~/Desktop/code/tlxy-tts.sql
- 数据导入
  ``` sql
  --在新的数据库中 导入备份的数据，导入导出的sql文件 
  mysql -u root -p ops < ./tlxy.sql
  -- 把导出的表sql 导入数据库
  mysql -u root -p ops < ./tlxy-tts.sql
  ```
- 权限管理
  - grant 授权的操作 on 授权的库.授权的表 to 账户@登录地址 identified by ‘密码’;
  ``` sql
   --在mysql中 创建一个 zhangsan 用户，授权可以对tlxy这个库中的所有表 进行 添加和查询 的权限 
   grant select,insert on tlxy.* to zhangsan@'%' identified by '123456';
   -- 用户 lisi。密码 123456 可以对tlxy库中的所有表有 所有操作权限 
   grant all on tlxy.* to lisi@'%' identified by '123456';
   -- 删除用户
   drop user 'lisi'@'%';
  ```