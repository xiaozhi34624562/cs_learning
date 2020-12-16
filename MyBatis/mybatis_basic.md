# MyBatis
## 搭建MyBatis
1. 添加驱动包(mysql.jar; mybatis.jar)
2. 添加配置文件mybatis-config.xml
   - 指定连接数据库的url, username, password, driver，指定编码方式
   - 由框架自动获取连接
   - 指定了事务的管理对象
   - 配置文件中的default与id的值相同，default表示默认访问环境；也可以自己指定使用哪个数据源
3. 创建实体类和接口
4. 添加mapper文件（在mapper文件里保存sql语句)
  ``` java
    <mapper namespace="接口的完整路径">
        <insert id="方法名" parameterType="参数类型">
                //sql
            </insert>
        <select id="方法名" resultType="查询后的返回值类型"> //sql语句---注:sql语句没有分号
        </select> 
    </mapper>
  ```
5. 修改mybatis的配置文件，让该配置文件知道mapper文件的存在
6. 获得sqlSession，通过该对象进行数据的操作
    ``` java
    //1.加载配置文件
    Reader r=Resources.getResourceAsReader("mybatis-config.xml");
    //2.创建SqlSessionFactoryBuilder对象
    SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
    //3.得到session工厂
    SqlSessionFactory factory=builder.build(r, 数据库id（不指定则为default）); 
    //4.得到session
    SqlSession session= factory.openSession(); 
    //5.调取sql语句,insert("方法的完整路径")，路径=namespace+id 
    int rs=session.insert("dao.EmpDao.insertEmp",e); 
    session.commit();
    //或者可以用
     Reader r=Resources.getResourceAsReader("mybatis.xml"); 
     SqlSession session = new SqlSessionFactoryBuilder().build(r).openSession(); 
     //参数是接口的class类
     StudentDao dao=session.getMapper(StudentDao.class);
     dao.findAll()
    ``` 
## MyBatis实现CRUD
（FreeMyBatis plugin）
- resultType， parameterType
- mapper文件中的参数读取
  - 单个基本类型参数或String类型：#{参数名}
  - 参数类型为对象类型时：#{对象中的属性名} 如（#{studentId}, #{studentNo}, #{StuName}）
  - 多参数，使用map 如#{key值}, #{key值}
  - 处理结果和实体类做对应的时候，用map如count（id）的返回值
-  增删改
   - 返回值为受影响的行数，不需要配置resultType
   - 需要提交事务session.commit(); session.rollback();
- 查询的时候需要添加resultType属性  

## ThreadLocal处理sqlSession
为每一个使用该变量的线程提供一个变量值的副本，不会和其他线程的副本冲突
``` java
class Test{
  private ThreadLocal<String> str = new ThreadLocal<String>(); 
  private List<String> list = new ArrayList<String>();
  class A extends Thread {
      public void run() {
          str.set("zhangsan"); 
          System.out.println("A...." + str.get()); 
          list.add("xxx"); 
          System.out.println("A<<<"+list.get(0));
      }}
  class B extends Thread {
      public void run() { 
        System.out.println("B...." + str.get()); 
        list.add("xxx"); 
        System.out.println("B<<<"+list.get(0));
}}}
```
## 给类起别名
- 起别名
  ``` java
  <!—给实体类起别名 --> 
  <typeAliases>
  <!--
  <typeAlias alias="u" type="com.yhp.bean.Users"> 
  </typeAlias>-->
  <!--指定哪些包的类可以使用别名,默认别名:类名首字母小写(实际使用的时候，全部小写也可以做结果映射) 
  <package name="bean"></package>
  </typeAliases>
  ```
- 获得新增数据的id
  ``` java
  <insert id="insertStu" parameterType"com.kaikeba.bean.Student" useGenerateKeys="true" keyProperty="studentId">
    insert into student(studentno, stuname) values(#{studentNo}, #{stuName})
  </insert>
  ```
- log4j日志记录
  - 添加jar包
  ``` java
    <dependency> 
      <groupId>org.slf4j</groupId> 
      <artifactId>slf4j-api</artifactId> 
      <version>1.7.5</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId> 
      <artifactId>slf4j-log4j12</artifactId> 
      <version>1.7.12</version>
    </dependency>
    <dependency>
      <groupId>log4j</groupId> 
      <artifactId>log4j</artifactId> 
      <version>1.2.17</version>
    </dependency>
   ```
  - 添加log4j.properties文件
  ``` java
   log4j.rootLogger=DEBUG, Console 
   log4j.appender.Console=org.apache.log4j.ConsoleAppender 
   log4j.appender.Console.layout=org.apache.log4j.PatternLayout 
   log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n
   log4j.logger.java.sql.ResultSet=INFO 
   log4j.logger.org.apache=INFO 
   log4j.logger.java.sql.Connection=DEBUG 
   log4j.logger.java.sql.Statement=DEBUG 
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```
## MyBatis的复杂查询
- in 查询
  - foreach标签中属性说明：
    - item 每一个元素迭代时候的别名
    - index 可以不写，指定一个名字，用于表示在迭代过程中，每次迭代到的位置
    - open 表示该语句以什么开始，
    - separator 表示在每次进行迭代之间以什么符号作为分隔符 
    - close 表示以什么结束，
    - collection 属性是必须指定的list， map（写map的key）， array 
- 模糊查询
  - 多个参数传递用map
  - 动态sql #{}相当于占位符， ${}相当于拼接sql语句
- 区间查询
  - between 开始值 and 结束值
  - <= 使用 `<![CDATA[ <= ]]>`
- resultMap
  - 处理表单中的关系（类中的属性名称和数据库中的field名称不一致，使用resultMap主键用id，其他用result）
  - 处理多表的关系
    - 存的是集合的话，使用Collection子标签加上ofType；存的是一方的话，使用的是association子标签加上javaType
    - 一对多(多里有集合，一里有对象)
    - 多对一 
    - 多对多
    - 一对一
- PageHelper分页
  - rowBounds实现内存分
    - List<Users> usersList = sqlSession.selectList("com.yhp.dao.UsersDao.findall", null, new RowBounds(0, 3));//rowBounds(开始位置,显示条数)
    - 在sql里面书写带有物理分页的参数来完成物理分页，也可以使用分页插件来完成物理分页
    - 优缺点
      - 物理分页每次都访问数据库，逻辑分页只访问一次
      - 物理分页占用内存少，逻辑分页相对较多
      - 物理分页数据每次都是最新的，逻辑分页可能存在滞后 
  - 使用分页插件
    - 导入jar包
    - 加入依赖
    - mybatis.xml中配置插件plugins
- 缓存
  - 一级缓存 sqlSession的缓存，自动开启
  - 二级缓存 在不同的缓存中共享数据，sqlSessionFactory的缓存，需要手动开启，需要在映射文件中配置.
  - eviction：二级缓存中，缓存的对象从缓存中移除的策略，先进先出； flushInterval：刷新缓存的时间间隔，单位是毫秒； size：缓存对象的个数； readOnly：是否只读
   ``` java
    <mapper namespace="接口路径"> 
      <cache eviction="FIFO"
        flushInterval="60000" 
        size="512" 
        readOnly="true"/>
    </mapper>
   ```
## MyBatis注解
在mybatis中可以将sql语句通过注解的方式定义在java中，此时配置文件扫描该注解的位置即可，代码如下:`<mapper class="com.dao.StudentDao"></mapper>`
 ``` 
@Insert("insert into student(username,password,birthday) values(#{user_name},#{password},# {birthday})")
@Options(useGeneratedKeys = true,keyProperty = "userid") 
public int insertstu(Student student);
```

```
@Delete("delete from student where userid=#{userid}") 
public int deleteuser(int userid);
```
```
@Update("update student set username=#{user_name},sex=#{sex} where userid=#{userid}") public int updateuser(Student stu);
```
```
@Select("select * from student") /* 
@Results({
@Result(id = true, property = "id", column = "test_id")
@Result(column = "username",property = "user_name") })*/
```
- 可以使用lombok插件