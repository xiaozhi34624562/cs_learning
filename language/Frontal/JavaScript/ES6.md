# ES6
- let和const命令
  - var存在变量提升，使用的变量再声明变量前面也不会报错；let变量要先声明再使用
  - var可以重复声明变量；let不可以重复声明
  - var不支持块级作用域；let和const都支持块级作用域
  - var不能用于定义常量；const可以用于定义只读常量（数值，字符串，布尔值）值就保存在变量指向的那个内存地址，因此等 同于常量；对于复合型的数据（对象和数组）保存的只是一个指向实际数据的指针，里面的属性可以改变。
- 变量的解构赋值
  - 数组的解构赋值
    - `let [a, b, c] = [10, 20, 30];`
    - `let [a, [b, c]] = [10, [20, 30]];`
    - `let [x, , y] = [10, 20, 30];`
    - `let [x, ...y]=[10, 20, 30, 40]` x=10, y = (20, 30, 40)
    - `let [a, b=0] = [10, 20]` b的初始值为0
  - 对象的解构赋值
    - `let {name, age} = {name:'张三', age:20};`
    - `let {email:em, password:ps, id=100} = {email:'zs@163.com', password:'123456'};`如果变量名与属性名不一致，必须写成下面这样，同样可以使用初始化值
  - 字符串的解构赋值
    - ` const [a, b, c, d, e] = 'hello';`
  - 数值和布尔值的解构赋值
    - `let {toString: s1} = 123;  console.log(s1 === Number.prototype.toString); // true`
    - `let {toString: s2} = true; console.log(s2 === Boolean.prototype.toString); // true`
  - 函数参数的解构赋值
    - ``` function move({x=0, y=0} = {}) {
            return [x, y];
          }
        console.log(move({x:3, y:8})); // [3, 8]
        console.log(move({x:3})); 
        console.log(move());// [3, 0]
       ```
  - 圆括号问题
  - 用途
    - 交换变量的值
    - 从函数返回多个值
    - 函数参数的定义
    - 提取json数据
- 字符串的扩展
  - 模版字符串
    - 字符串的增强版的字符串，用反引号（`)标识
    - 它可以当做普通字符串使用，也可以用来定义多行字符串，或者在字符串中潜入变量
    - 在模版中需要使用反引号，则前面要使用反斜杠转义
    - 如果使用模版字符串便是多行字符串，所有的空格和缩进都会被保留在输出之中
    - 模版字符串之中可以放js表达式，对象属性，还能调用函数
    ```
    let url = "http://baidu.com";
    let title = "百度";
    // 传统的字符串拼接
    console.log('<a href="'+ url + '">' + title + '</a>');
    // 使用模版字符串
    console.log(`<a href="${url}">${title}</a>`);
    // 使用模版字符串可以标识多行字符串
    let a = `
        <ul>
            <li>上海</li>
            <li>北京</li>
            <li>香港</li>
        </ul>`
    // 模版字符串支持使用对象属性，表达式运算和函数调用
    let stu = {name:"张三", age:18};
    console.log(`my name is ${stu.name}. I am ${stu.age}`);
    ```
  - 标签模版(函数调用的一种特殊)
    ```
    alert("hello"); 
    // 等同于 
    alert`hello`;
    
    tag(['Hello ', ' world ', ''], 15, 50);
    // 等同于
    tag`Hello ${ a + b } world ${ a * b }`;
    ```
- 字符串的新增方法
  - includes(), startsWith(), endsWith() 子串查找，返回布尔值
  - repeat(), 返回一个新字符串，表示将原来字符串重复n次
  - padStart(), padEnd() 字符串补全长度的功能
  - trimStart(), trimEnd() 消除字符串头部或尾部的空格，返回新字符串，不改变原来的字符串，trim()消除前后的空格
  - matchALL() 返回一个正则表达式在当前字符串的所有匹配 
- 运算符的扩展
  - 对象的扩展运算符
    ```
    let stu1 = {name:'zhansan', age:23};
    let stu2 = {...stu1};
    stu1.age = 30;
    console.log(stu2); // {name:"zhansan", age:23}
    ``` 
  - 数组的扩展运算符
    ```
    let a = [10, 20, 30];
    let b = [...a];
    a[2] = 300;
    console.log(b);

    // 使用三个点将 数组转化成参数序列    
    function demo(x, y, z) {
      console.log(x, y, z);
    }
    demo(...a);
    ```
  - 剩余参数的处理
    ```
    const info = ["zhangsan","1002",12,23,45,34];
    const [name,id,...params] = info;
    console.log(name,id,params); //zhangsan 1002 [12, 23, 45, 34]

    // 使用arguments来收集函数调用传递的任意数量参数
    function sum() {
      let res = 0;
      for (let v of arguments) {
        res += v;
      }
      return res;
    }
    console.log(sum(10, 20, 30, 40));
    
    // 执行效果同时，使用三点来收集所有参数
    function sum2(...numbers) {
      let res = 0;
      for (let v of numbers) {
        res += v;
      }
      return res;
    }
    console.log(sum2(10, 20, 30, 40));

     
     //定义一个处理折扣的函数，第一个参数为折率，其余参数为价格 function discount(rate,...prices){
        return prices.map(p => p*rate); 
      }
      //计算0.68参数以外的其他价格，打完6.8折后的值。 
      console.log(discount(0.68,125,98,246,50));//[85, 66.64, 167.28, 34]

    ```
- 数值的扩展
  - 二进制和八进制的表示法
    - 0b11 === 3
    - 0o11 === 9
  - Number.isFinite(), Number.isNaN()
  - Number.parseInt(), Number.parseFloat()
  - Number.isInteger()
- 函数的扩展
  - 函数参数的默认值
    - ES6允许为函数的参数设置默认值，即直接写在参数定义的后面`function func(x=0, y=1) {return x+y}`
    - 跳过第一个参数给第二个参数传值 `func(undefined, 20)`
  - 箭头函数。如果箭头函数的代码块部分多于一条语句，就要使用大括号将他们扩起来，并且使用return语句返回；由于大括号被解释为代码块，所以如果箭头函数直接返回一个对象，必须在对象外面加上括号，否则会报错。
    ```
    var f = v => v;
    var f = function(v) {return v;}; 

    // 箭头函数不需要参数或需要多个参数，就一定需要一个圆括号代表参数部分
    var f = () => 5;
    var f = (num1, num2) => num1 + num2;

    let getItem = sid => ({id:sid, name:"temp"});
    ```
    
  - 箭头函数中this的理解
    - 箭头函数中没有自己的this，故此处this是继承父作用域的.而且是在定义的时候已指定，不会随着调用而改变。
  - 箭头函数使用注意点
    - 函数体内的this对象，就是定义时所在的对象，而不是使用时所在的对象。
    - 不可以当作构造函数，也就是说，不可以使用new命令，否则会抛出一个错误。
    - 当你真的需要this的时候，如为对象添加普通方法或事件绑定回调函数使用箭头函数，可能获取不 到this。
    - 不可以使用arguments对象，该对象在函数体内不存在。
- 数组的扩展
  - 数组的遍历
    - 传统的for循环
    - 使用for...in遍历 for(let c in a){console.log()} 会遍历出其他非数字属性的信息
    - 使用forEach遍历数组，不支持break和continue `a.forEach(function(v){console.log(v);})` 或者 a.forEach(v => {console.log(v)})
    - 使用for...of循环，不会遍历非数字属性，支持break和continue的使用
  - for...of循环的使用实例
  - Array.form(arrayLike, mapFu, thisArg) 将一个类数组对象或者可遍历对象转换成一个真正的数组
    - arrayLike 想要转换成数组的伪数组对象或者可迭代对象 
    - mapFn 可选，新数组中的每个元素都会执行该回调函数
    - thisArg 可选，执行回填函数时this对象
  - Array.of() 创建一个新数组
    - console.log(Array.of()); //[] 创建一个空数组
    - console.log(Array.of(8)); //[8] 创建只有一个元素值为8的数组 
    - console.log(Array.of(1, 2, 3)); //[1,2,3] 创建一个值为1，2，3的数组
  - find()
    - 用于找出第一个符合条件的数组成员
    - 参数是一个回调函数，所有数组成员依次执行该回调函数。 直到找出第一个返回值为true的成员，然后返回该成员。 如果没有符合条件的成员，则返回undefined。
  - findIndex() 返回第一个符合条件的数组成员的位置，使用方法同上
  - some() 只要其中一个为true 就会返回true
    - let s1 = data.some(v => v['name'].startsWith("wang"));
  - every() 须所有都返回true才会返回true
    - let s2 = data.every(v => v['age']>20);
  - fill() 使用指定的元素替换原数组内容，会改变原来的数组 arr.fill(value[, start[, end]])
    - value:替换值。
    - start:替换起始位置(数组的下标)，可以省略。
    - end:替换结束位置(数组的下标)，如果省略不写就默认为数组结束
- Set和Map数据结构
  - Set 初始化 var s = new Set(); var s2 = new Set([1, 2, 3])
    - size:返回成员总数
    - add(value):添加某个值，返回Set结构本身 
    - delete(value):删除某个值，返回一个布尔值，表示删除是否成功 
    - has(value):返回一个布尔值，表示该值是否为Set的成员 
    - clear():清除所有成员，没有返回值
  - Map 初始化 var m1 = new Map(); var m2 = new Map([['a', 123], ['b', 456], [3, 'abc']]);
    - set(key, value):设置键值对
    - get(key):获取键对应的值
    - has(key):是否存在某个键 
    - delete(key):删除某个键值对，返回一个布尔值，表示删除是否成功
- 对象的扩展，属性的简介表示法
  - 属性简写
    - const foo = 'bar'; const baz = {foo}; // {foo: "bar"}
    - function f(x, y) {return{x, y};} 等同于 function f(x, y) {return{x:x, y:y}}
  - 方法简写
    - const o = {method(){return "Hello";}} 等同于 const o = {method: function() {return "Hello";}}
- 对象的新增方法
  - Object.is() 用来比较两个值是否严格相等，与严格比较运算符 (===)的行为基本一致。
  - Object.assign() 将源对象(source)的所有可枚举属性，复制到目标对象(target)
    - const target = { a: 1 }; const source1 = { b: 2 }; const source2 = { c: 3 }; Object.assign(target, source1, source2); // {a:1, b:2, c:3}
  - Object.getOwnPropertyDescriptors()
  - Object.keys()，Object.values()，Object.entries()
    - var obj = { foo: 'bar', baz: 42 }; Object.keys(obj)
- Class 的基本语法
  - 例子
    ```
    class Point {
      constructor(x, y) {
        this.x = x;
        this.y = y; }

      toString() {
      return '(' + this.x + ', ' + this.y + ')';} 
    } 
    ```
  - constructor方法是类的默认方法，通过new命令生成对象实例时，自动调用该方法。
  - 与函数一样，类也可以使用表达式的形式定义
  - 静态方法
    ```    
    class Foo {
      static classMethod() {
        return 'hello';
      }
    }
    Foo.classMethod() // 'hello' 
    ```
- Class 的继承
  - 例子
  ```     
  class Point {}

  class ColorPoint extends Point {
    constructor(x, y, color) {
      super(x, y); // 调用父类的constructor(x, y)
      this.color = color; }

    toString() {
      return this.color + ' ' + super.toString(); // 调用父类的toString()
    }
  }
  ```
  -  super这个关键字，既可以当作函数使用，也可以当作对象使用
  -  ES6 要求，子类的构造函数必须执行一次super函数。