# JDBC(java DataBase Connectivity)
## 使用步骤
1. 引入jar文件
2. 加载数据库驱动（javaSE项目中可以省略，javaweb项目中必须编写此步骤）  
   Class.forName("com.mysql.jdbc.Driver");
3. 通过驱动管理器，获取JDBC连接对象  
   Connection conn = DriverManager.getConnection("数据库连接地址","账号","密码");
   // 连接地址格式：主协议：子协议：//ip地址：端口号/数据库名称  
   // mysql连接地址：jdbc:mysql://localhost:3306/java35  
   // oracle连接地址: jdbc:oracle:thin:@localhost:1521:ORCL
4. 通过连接对象，创建SQL执行对象（SQL执行环境）  
   Statement state = conn.createStatement();
5. 通过SQL执行对象，执行SQL语句
   state.execute(string sql语句);
6. 释放资源  
   state.close();
   sonn.close();
## JDBC常用的类型和方法
- DriverManager:驱动管理器
  - 常用的方法：数据库连接：static Connection getConnection(String 数据库地址, String 账号, String 密码)
- connection: 数据库连接对象
  - 常用方法：创建SQL执行对象 Statement creatStatement();
- Statement: SQL执行对象
  - 常用方法
  - 执行SQL语句(查询语句返回true，其他语句返回false) boolean execute(string sql);
  - 执行DML语句(insert update delete)和DDL语句(create alter drop)（返回int值，表示语句对数据库表格的影响行数）（返回值>0，则执行成功）  
   int executeUpdate(String sql);
  - 执行DQL语句（Select）  
    ResultSet executeQuery(String sql); 
- ResultSet:结果集对象（指的是一个select语句的查询结果）
  - 常用方法：
  - 控制游标移动的常用方法：
    - boolean next() 控制游标向下一行移动，移动成功返回true，失败则false
    - boolean privious()
    - boolean absolute(int num)
    - boolean beforeFirst()
    - boolean afterLast()
  - 获取游标指向行的字段值的常用方法
    - xxx getxxx(String 列名) 根据字段名，得到此字段的值  
    - xxx getxxx(int 字段的索引) 根据字段的索引，得到字段的值，从索引1开始
# 工厂方法设计模式（静态工厂方法模式）
优点：
- 面向接口编程，体现了面向对象的思想
- 降低了耦合，将创建对象的工作转移到了工厂类
``` java
1. 水果接口
public interface Fruit {
        void eat();
}
2. 苹果 (水果的一种)
public class Apple implements Fruit{
    @Override
    public void eat() {
        System.out.println("苹果吃起来甜甜的脆脆的.");
}
3. 香蕉 (水果的一种)
public class Banana implements Fruit{
    @Override
    public void eat() {
        System.out.println("香蕉吃起来软软的甜甜的"); }
}
4. 静态工厂类
public class FruitFactory {
    public static Fruit get(){
        //return new Apple();
        return new Banana();
    } 
}
```
# DAO(Data Access Object)
是一个数据访问接口，在这个应用程序中， 当需要和数据源进行交互的时候则使用这个接口，并且编写一个单独的类来实现这个接口在逻辑上对应这个 特定的数据存储。  
一个典型的DAO实现有下列几个组件：
1. 一个DAO工厂类
2. 一个DAO接口
3. 至少一个实现DAO接口的具体类
4. 数据传递对象（有时叫Bean对象）
# SQL注入问题
## 问题
``` sql
进行用户登录时, 输入不存在的帐号 和 如下的密码: 1' or '1'='1
结果显示登录成功.
因为用户输入的密码, 与我们的查询语句拼接后, 使得我们的查询语句产生了歧义:
原查询语句:
select * from xzk_user where username='' and password='密码'
拼接后:
select * from xzk_user where username='hahahaheiheihei' and password='1' or '1'='1'
```
可以将SQL语句与参数分离，将参数作为SQL的特殊部分进行预处理
## PreparedStatement预编译的SQL执行环境
``` java
内部实现原理:
1. 将未拼接参数的SQL语句, 作为SQL指令, 先传递给数据库 进行编译.
2. 再将参数传递给数据库, 此时传递的参数不会再作为指令执行, 只会被当作文本存在.

操作流程与Statement基本一致:
1. 如何得到一个PreparedStatement 对象
PreparedStatement state = conn.prepareStatement("预编译的SQL语句");
2. 预编译的SQL语句如何编写
需要填充参数的位置, 使用?代替即可! 例如:
select id from xzk_user where username=? and password=?
3. 参数如何填充
state.setXXX(int index,XXX value);
setXXX中XXX指的是数据类型,
参数1: index : SQL语句中?的索引值 , 从1开始
参数2: value : 填充的参数值.
4. 如何执行填充完毕参数的SQL
- boolean execute();
- int executeUpdate();
- ResultSet executeQuery();
```
## PreparedStatement与Statement谁的性能高？
取决于数据库的类型。在mysql中, preparedStatement原理是拼接SQL, 所以Statement性能高；在Oracle中, preparedStatement原理是对SQL指令进行预处理, 再传递的参数不具备特殊含义.有更好 的SQL缓存策略,PreparedStatement高.
# 事物
将多条SQL语句，看作一个整体， ，要么一起成功，要么一起失败
## 命令行操作
- start transaction / begin 开启事物
- rollback 放弃所有sql操作
- commit  所有sql操作提交
## java 操作
- conn.setAutoCommit(false);开启事物
- conn.rollback();回滚事物
- conn.commit();提交事物
## 事物面试题
1. 事物的四大特性：
   - 原子性：事物是一个整体，要么同时成功，要么同时失败
   - 持久性：当事物提交或者回滚，数据库会持久化的保存数据
   - 隔离性：多个事务之间，隔离开，相互独立
   - 一致性：事物操作前后，数据的总量不变
2. 脏读、幻读、不可重复读
   - 脏读：读取到了一个事务未提交的数据
   - 幻读：一个事务A在执行DML语句时，另一个事务B也在执行DML语句，B修改了A修改的数据，导致A在查询是发感觉自己仿佛没有修改
   - 不可重复读：一个事务中，两次连续的读取，结果不一致（中间被其他事务更改了）
3. 事物的隔离级别
   - 读未提交(read uncommitted):可能产生脏读，不可重读，幻读
   - 读已提交(read committed)：可能产生不可重复读和幻读
   - 可重复读(repeatable read)：default 可能产生幻读
   - 串行化(serializable)：
查看数据库当前的隔离级别：select @@tx_isolation  
数据库设置隔离级别：set global transaction isolation level 级别字符串
# 批处理
将多条语句放到一起批量处理：将多条SQL语句，转换为一个SQL指令，显著的提高大量SQL语句执行时的数据库性能  
Statement 对象使用流程：
1. 得到Statement对象
   Statement state = conn.createStatement():
2. 将一条SQL语句，加入到批处理中
   state.addBatch(String sql);
3. 执行批处理
   state.executeBatch();
4. 清空批处理
   state.clearBatch();
<br>

PreparedStatement对象使用流程：
1. 得到PreparedStatement对象
   PreparedStatement state = conn.prepareStatement("预编译的SQL");
2. 填充预编译的参数
   state.setxxx(1, 填充参数)；
3. 将一条填充完毕参数的SQL，加入到批处理中
   state.addBatch();
4. 执行批处理
   state.executeBatch();
5. 清空批处理
   state.clearBatch();

# Properties 作为配置文件
Properties类 是java中Map集合的实现类
.properties文件，用于通过文件描述一组键值对  
.properties文件，可以快速的转换为Properties类的对象  
文件中内容的格式：
文件内容都是字符串，键与值之间通过等号连接，多个键值对之间换行分割 e.g.  
url=xxx  
user=xxx  
password=xxx  
<b>如何将文件转换为集合</b>：  

1. 创建Properties对象
   Properties ptt = new Properties():
2. 创建一个字节输入流，指向.properties文件  
   InputStream is = new FileInputStream("文件地址");
3. 将字节输入流，传递给properties对象，进行加载  
   ppt.load();

# 连接池(DataSource)的使用
连接池用于缓存连接。使用DBCP和Druid，两种。  