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
    - `let {toString: s1} = 123; console.log(s1 === Number.prototype.toString); // true`
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
  - 标签模版
    ```
    alert("hello"); 等同于 alert`hello`;