java.io.file总结

IO流概述
- Java重的IO操作，主要指的是java.io包下的一些使用类的操作，通过这些常用类对数据进行读取（输入input）和写出（输出output）
  
IO流分类
- 按照流的方向：输入流和输出流
- 按照流动的数据类型：字节流和字符流

字节流：输入流InputStream; 输出流OutputStream  
字符流：输入流Reader；输出流Writer  
一切皆字节：计算机中任何东西储存都是以二进制形式，传输时也是以二进制

## FileOutputStream
- 构造方法
  - FileOutputStream(File file, boolean append)
  - FileOutputStream(String name, boolean append)
  - **append==true时，原来文件接着写；append不给值或者append=false时，都是重写文件**  

## FileInputStream
- 读取数组，一次读取一组的方法，最后一次内容不够一个数组的时候，不够的部分为上一次读取的值
  - **如何避免这个问题**
  ```java
  FileInputStream file = new FileInputStream(c:\\a.txt);
  byte[] bytes = new byte[10];
  int len = file.read(bytes);
  System.out.println(new String(bytes, 0, len));
  ```
文件的加密和解密
- 任何文件^两次都是自己本身  
  

## 字符编码
计算机结构
- 硬盘
  - 机械硬盘：能长时间储存，耐高温低温
  - 固态硬盘：存的快读的快，不耐高温
- 内存：30G-60G/s读写，不能存储
- cpu：中央处理器
- 显卡：图形处理器
- 显示器 ：1920*1080，横向1920个灯泡，共有1080行，每个灯泡有三种小灯泡构成
UTF-8：可变长度字符编码

## 字符读取（需要刷新管道
字符读取自己会有一个缓存的状态**不刷新的话，都存在内存中，不会写入**使用file.flush()是刷新，file.close()也是包含了一次刷新

### Write
- FileWrite
  - file.append()返回的是本身
### Reader
```java
// 输出的时候可以避免出现大量的空格
FileReader file = new FileReader("C:\\a.txt");
char[] chars = new char[100];
int len = file.read(chars);
String text = new String(chars, 0, len);
```

## 转换流
把字节流“装饰”成字符流：使用了装饰者设计模式
```java
FileInputStream file = new FileInputStream("C:\\a.txt");
// para1:需要转换的字节流； para2:转换的格式
InputStreamReader isr = new InputStreamReader(file, "gdk")
```
## 打印流（也可以用字节输出）
打印流，向文件输出
```java
// 打印流
PrintStream f = new PrintStream("c:\\a.txt");
f.println("以二餐");

// 字节流转化为字符流
FileOutputStream fil = new FileOutputStream("C:\\a.txt");
PrintStream pfil = new PrintStream(fil);
pfil.println("萨仁高娃热");

// 缓存读取流，将字符输入流转换为带有缓存，可以一次读取一行缓存字符读取流
FileReader r = new FileReader("c:\\a.txt");
BufferedReader br = new BufferenReader(r);
String text = br.readLine();
```

## 异常日志收集
``` java
try{
  String a = null;
  a.toString();
} catch(Exception e){
  PrintWriter pw = new PrintWriter("C:\\bug.txt");
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM");
  pw.printfln(format.format(new Date()));
  e.printStackTrace();
  pw.close();

}
```


## Properties 
可以用来写配置文件，属于map类下面的；
**需要总结**

## 序列化和反序列化
类需要标记接口implements Serializable，类中使用的类也需要被序列化标记；
序列化：将对象存储到文件的过程
反序列化：将文件里存的对象再转变为对象的过程
