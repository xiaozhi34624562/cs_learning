## CSS使用样式
1. 浏览器缺省值设置（html本身带的样式）
2. 外部样式表(外部链入)
   - 在head标签中使用标签导入一个css文件，作用于本页面，实现css样式设置`<link href="wenjian.css" rel="stylesheet"/>
   - 使用import在style标签中导入文件
    ```
    <style type="text/css">
        @import "style.css"
    </style>
    ```
   
3. 内部样式表（位于`<head>`标签内部，作用一当前整个页面）
   - 在head标签中使用`<style type="text/css">...</style>`标签来设置css样式
    ```
    <style text="text/css">
        css样式代码
    </style>
    ``` 
4. 内联样式（在html元素内部）
   - 在html的标签中使用style属性来设置css样式
   - 格式`<html标签 style="属性：值；属性值：...">被修饰的内容</html>`
   - `<p style="color:blue;font-family:隶书>在HTML中如何使用css样式</p>`

<br/>

|样式表|优点|缺点|使用情况|控制范围|
|:-----:|:-----:|:-----:|:-----:|:-----:|
|行内样式|书写方便|没有实现样式和结构分离|较少|控制一个标签|
|内部样式表|部分结构和样式想分离|没有彻底分离|较多|控制一个页面|
|外部样式表|完全实现结构和样式相分离|需要引入|最多|控制整个站点|

## css中常用选择器
### css2中的选择符
1. html选择符（标签选择器）
   - 把html标签作为选择符使用，如p{....}网页中所有p标签采用此样式
2. class选择器
   - 定义 :.类名{样式} 匿名类 其他选择符名.类名{样式...} 使用：`<html标签 class="类名">...</html标签>`
   - .mc{color:blue;} ./*凡是class属性值为mc的都采用此样式*/
   - p.ps{color:green;} /* 只用p标签中class属性值为ps的才采用此样式*/
   - ***可以在网页中重复使用***
3. id选择器
   - 定义: #id名{样式...} 
   - 使用：`<html标签 id="id名">...</html标签>`
   - ***id选择符只在网页中使用一次***
4. 关联选择器（包含选择符）
   - 格式: 选择符1 选择符2 ...
   - table a{...} /*table标签里的a标签才采用此样式*/
   - h1 p{color:green}/*只有h1标签里面的p标签才采用此样式*/
5. 组合选择器
   - 格式 选择符1，选择符2，选择符3{样式...}
   - h1,h2,h3{color:green} /*h3,h4,h5都采用此样式*/
6. 伪类选择器
   - 格式: 标签名：伪类名{样式...}
   - a:link{color: #FF0000; text-decoration:none} /*未访问的链接*/
   - a:visited{color: #FF0000; text-decoration:none}/*已访问的链接*/
   - a:hover{color: #FF0000; text-decoration:underling/*鼠标在连接上*/}
   - a:active{color: #FF0000; text-decoration:underling}/*激活链接*/

## CSS3中的选择器
1. 关系选择器
   - div>p 选择所有作为div元素的子元素p
   - div+p 选择紧贴在div元素之后p元素
   - div~p 选择div元素后面所有的兄弟元素p
2. 属性选择器
   - [attribute] 含有此属性的
   - [attribute=value] 含有此属性，且value为此值的
   - [attribute~=value] 属性为用空格分离的字词列表，其中有一个的值为value
   - [attribute|=value] 以value开头并用链接符“-”分隔的字符串
   - [attribute^=value] 以value开头的
   - [attribute$=value] 以value结尾的
   - [attribute*=value] 含有value的
3. 结构性伪类选择器
   ```
   ::first-letter设置对象内的第⼀一个字符的样式。
   ::first-line设置对象内的第⼀行的样式
   :before设置在对象前(依据对象树的逻辑结构)发⽣生的内容。
   :after设置在对象后(依据对象树的逻辑结构)发⽣生的内容。
   :lang(language)匹配使⽤用特殊语⾔言的E元素
   :element1~element2: :first-of-type匹配同类型中的第⼀一个同级兄弟元素
   :last-of-type匹配同类型中的最后⼀一个同级兄弟元素
   :only-of-type匹配同类型中的唯⼀一的⼀一个同级兄弟元素
   :only-child匹配⽗父元素仅有的⼀一个⼦子元素
   *:nth-child(n)匹配⽗父元素的第n个⼦子元素
   :nth-last-child(n)匹配同类型中的倒数第n个同级兄弟元素
   *:first-child 匹配⽗父元素的第⼀一个⼦子元素
   * :last-child 匹配⽗父元素的最后⼀一个⼦子元素
   :root匹配元素在⽂文档的根元素。在HTML中，根元素永远是HTML
   :empty匹配没有任何⼦子元素(包括text节点)的元素
   ```
4. 状态伪类选择器
   ```
   :visited 设置超链接a在其链接地址已被访问过时的样式
   :active 设置元素在被⽤用户激活(在⿏鼠标点击与释放之间发⽣生的事件)时的样式
   *:hover 设置元素在其⿏鼠标悬停时的样式
   *:focus 设置元素在其获取焦点时的样式
   :target 匹配相关URL指向的E元素
   :enabled 匹配⽤用户界⾯面上处于可⽤用状态的元素
   :disabled 匹配⽤用户界⾯面上处于禁⽤用状态的元素
   :checked 匹配⽤用户界⾯面上处于选中状态的元素
   :not(selector)匹配不不含有selector选择符的元素
   ::selection 设置对象被选择时的样式
   ```
5. 其他伪类选择器
   ``` 
   E:not(s) : {attribute} 匹配所有不不匹配简单选择符s的元素E
   p:not(.bg) {background-color:#00FF00;}
   ```