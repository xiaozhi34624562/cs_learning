# javascript 脚本语言
动态的网页需要用js来实现
- HTML是一种标记语言，用来结构化我们网页的内容并赋予内容含义，例如定义段落、标题和数据表，或在页面中嵌入图片和视频
- CSS是一种样式规则语言，可将样式应用于HTML内容，例如设置背景颜色和字体，再多列中布局内容
- JavaScript是一种脚本语言，可以用来创建动态更新的内容，控制多媒体，制作图像动画等等
## javascript简介
- 一种客户端脚本语言
- 通常直接嵌入html页面，有浏览器执行解释
- 解释性语言，不需要进行预编译
- 特点：弱类型和基于对象
- 核心: ECMAScript基本语法、DOM、BOM
## javaScript使用方法
- 内部javaScript：使用`<script>...</script>`
  - 属性：charset：字符集设置
  - defer：可选执行顺序
  - src：可使用外部脚本
    ```
    <script type="text/javascript>
        yuju
    </script>
    ```
- 外部用JavaScript（使用导入的js文件）
    `<script type="text/javascript" src="my.js"><script>`
- 内联javascript处理器（将js代码写到html代码中，如在html标签的事件中或超级链接里
    ```
    <button onclick="javascript language"><button>
    <a href="javascript:alert('aa');alert('bb')>点击</a>
    ```
## javascript的基础语法
- 输出
  - window.alert()弹出警告框
  - document.write()将内容写入到HTML文档中
  - innerHTML 写入到HTML元素
  - consolo.log() 写入到控制台
- 变量
  - var name;
  - var name; name = "zhangsan";
  - var name, age, sex; name="zhang"; age=15; sex=male;
- 数值类型
  - 基本数据类型：String, Number, Boolean, Null, Undefined, Symbol
  - 引用数据类型：Object, Array, Function
  - 值类型理解：变量之间的相互赋值，是指开辟一块新的内存空间，将变量值赋给新变量，保存到新开辟的内存里面；之后两个变量的值的变动互不影响。
  - 引用类型理解：变量之间的互相赋值，只是指针的交换，而并非将对象（普通对象，函数对象，数组对象）复制一份给新的变量，对象依然还是只有一个，只是多了一个指引。
- 类型转换
  - 使用JavaScript函数
    - Boolean(), Number(), String(), parseInt(), parseFloat(), isNaN() 
    - 自动类型转换
- 运算符
  - === 数值相等且类型相等
  - !== 值不相等或者类型不相等 
  - **java是32位有符号数**
  - << 零填充，左位移 
  - \>> 有符号，右位移
  - \>>> 零填充，右位移
  - 有符号与无符号唯一的区别：有符号位移时，如果数字为正数时，位移后在后面补零，为负数时则补 1
- 函数
  - 标准格式
    ```
    function Add(m, n){
        var c = m + n;
        return c;
    }
    Add(2, 3);
    ```
  - 构造函数定义函数 var c = new Function("x","y","var d = x + y; return d;");
  - 表达式中定义函数 var c = function(a, b){ return a+b;}
  - 函数自调用 (function() {consolo.log("Hello");})();
  - arguments 对象
  - escape(), unescape(), eval(), isNaN(), parseInt(), parseFloat()