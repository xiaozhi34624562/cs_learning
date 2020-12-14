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
    int rs=session.insert("dao.EmpDao.insertEmp",e); session.commit();
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