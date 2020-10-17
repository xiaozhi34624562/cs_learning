## javaScript自定义对象
1. 使用原始方式创建对象
    ``` javaScript
        var obj = new Object();
        obj.name = "zhangsan";
        obj.age = 12;
        obj.say = function(){
            console.log(this.name);
        }
    ```
2. 使用工厂方式创建对象
   ``` javascript
    function creatObject(name, age){
        var obj = new Object();
        obj.name = "zhangsan";
        obj.age = 12;
        obj.say = function(){
            console.log(this.name);
        }
        return obj;
    }
    var ob1 = creatObject("zhangsan", 12);
   ```
3. 使用自定义构造函数定义对象
   ``` javascript
   function Stu(name, age){
       this.age = age;
       this.name = name;
       this.say = function(){
           console.log(this.name);
       }
   }
   var ob1 = new Stu("zhangsan", 34);
   ```
4. 直接创建自定义对象
   ``` javascript
   var ob = {
       name:"zhangsan",
       age:12,
       say:function(){
           console.log(this.name);
       }}
   ```
验证方法： 获取构造方法 
``` javascript
a = [11, 12, 13];
console.log(a.constructor == Array);
console.log(ob1 instanceof Stu);
```
## javaScript内置对象
### Array
- 定义一个数组
    ``` javascript
    a1 = new Array();//空数组
    a2 = new Array(10);//长度为10的数组
    a3 = new Array(10, 20, 30);// 长度为3的数组，并且已经赋值
    a4 = [1, 2, 3];
    ```
- 遍历数组
    ``` javascript
    for(var i=0;i<a4.length;i++){
        console.log(i, " ", a4[i]);
    }

    for(var i in a4){
        console.log(i, " ", a4[i]);
    }

    a4.forEach(function(c){
        console.log(i, " ", a4[i]);
    })
    ```
- 常用方法
  - sort()数组排序
  - toString() 数组转换成字符串
  - join() 将数组所有元素放入一个字符串，元素通过指定的分隔符进行分隔
  - pop() 删除并返回最后一个元素
  - push() 想数组增加一个或多个元素，返回新数组的长度
  - shift() 取出数组的第一个元素，修改length的属性
  - unshift() 在数组最前面插入项，返回数组的长度
  - reverse() 翻转数组
  - concat() 把参数拼接到当前数组
- 清空数组
  - arr = []
  - arr.length = 0
  - arr.splice(0, arr.length)
### 基本包装类型（Number String Boolean）
  - String
    - indexof("Li", index)搜索的词语和启示的位置，index可以省略； search() 只能是第一次的位置 and lastIndexof()
    - slice(start, end) 两个参数可正可负; substring(start, end) 只能是正数； substr(start, length) 
    - concate()
    - replace()
  - Number
    - toFixed()指定小数位
    - toString()
  - Boolean
    - valueOf 返回原始值
### Date
var date = new Date();
- getDate() 1~31 
- getDay() 0~6
- getMonth() 0~11
- getFullYear() 返回四位数字的年份
- getHours() 0～23
- getMinutes() 0～59
- getSeconds() 0～59
- getMiniseconds() 0～999
- getTime() 返回1970年1月1日至今的毫秒数
### Math
- abs(a)
- ceil(a) 进1取整 
- floor(a) 舍取整
- random()
- round()
- max(x, y)
- min(x, y)
### JS Browser DOM -- Timing
- setTimeout() 等待时间后执行函数
- setInterval() 每隔一定时间后执行函数
- clearTimeout()
- clearInterval()
### 事件绑定方式
  ``` javascript
// 第一种
  <button onclick="func()">Yes</button>
  <script>
    function func(){
        console.log("hello");
    }
  </script>
// 第二种
  <button id="bu">No</button>
  <script>
    document.getElementByID("bu").onclick() = func(){
        console.log("hello");
    }
  </script>
  ```
### 获取事件元对象
  ``` javascript
// 第一种 前面要写this，在函数里面传一个参数，参数代表this
  <button onclick="func(this)">Yes</button>
  <script>
    function func(ob){
        console.log("hello");
        console.log(ob);
    }
  </script>
// 第二种 直接用this代表源
  <button id="bu">No</button>
  <script>
    document.getElementByID("bu").onclick() = func(){
        console.log("hello");
        console.log(this);
    }
  </script>
  ```
### contextmenu右击事件 DOM Event Handler