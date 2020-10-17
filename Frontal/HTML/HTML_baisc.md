# HTML文本
## HTML基本结构
- 开头 <!doctype html>
- html包括head和body两部分，其中head提供关于网页的信息，body提供网页的具体内容，最外层是<html>...</html>
- 标签有两种，双标签（<标签名>...<标签名>）和单标签(<标签名/>)
- html标签中还可以添加属性：<标签名 属性名1=“值1” 属性名2=“值2” 属性名3=“值3”> ...</标签名>
- 标签规范：标签名大小写、属性使用双引号、标签要闭合

## HTML注释
` <!-- 注释 -->`

## HTML中head的设置
- head标签作用于网页头部，它的内容不会在正文中显示出来，主要完成对当前页面的各种设置
```
  - <head> ： <title>网页标题</title> 定义了文档的标题
  - <base> ：<base href="http://www.***.com/" target="_blank"> 定义了页面链接标签的默认链接地址
  - <link> 导入CSS文件, 定义了一个文档和外部资源之间的关系 <link type="text/css" rel="stylesheet"
  href="**.css"/>  
  - <meta> 定义了html文档中的元数据，设置⽹网⻚页编码、关键字、描述<meta charset="utf-8"/> <meta name="Keywords" content="关键字" /> <meta name="Description" content="简介、 描述" />
  - <script> 定义了客户端的脚本文件  <script >JavaScript脚本程序 </script>
  - <style> 定义了html文档的样式文件  <style type="text/css">嵌⼊入css样式代码 </style>
```

## HTML文本标签
- <hn>...</hn> 其中n为1-6，都是标题
- <i>...</i> 斜体
- <em>...</em>强调斜体 在搜索引擎里有强调作用
- <b>...</b> 加粗
- <strong>..</strong> 强调加粗
- <cite>...</cite> 作品的引用（标题）
- <sub>...</sub> 下标 <sup>...<sup> 上标
- <del>...<del> 删除线

## HTML格式化标签
- <br/> 换行
- <p>...</p> 换段
- <hr/> 水平分割线
- 列表：
  - <ul>...</ul> 无序列表
  - <ol>...</ol> 有序列表
  - <li>...</li> 列表项
  - <dl>...</dl> 自定义列表
  - <dt>...</dt> 自定义列表表头
  - <dd>...</dd> 自定义列表内容
- <div>...</div> 常用于组合块级元素，一边对于CSS来对这些元素格式化
- <span>...</span> 常用于包含的文本，可以使用CSS对它定义样式，或使用JavaScript对它进行操作
  
## HTML图像标签
- 在网页中插入一张图片，使用img标签，它是一个单标签：<img />
  - src : 图片名及url地址
  - alt : 图片加载失败时提示信息
  - title : 文字提示属性
  - width : 图片宽度
  - height : 图片高度
  - border : 边框线粗细

## HTML超链接标签
- 格式：<a href="超链接url地址">显示的内容</a>
- a标签的属性
  - href：必须，指的是链接跳转地址
  - target：表示链接打开的方式
    - _blank 新窗口
    - _parent 父窗口
    - _self 本窗口（默认）
    - _top 顶级窗口
    - framename 窗口名
    - title 文字提示属性
- 锚点链接
  - 定义一个锚点：<a href="a1"></a>
  - 使用锚点：<a href="#a1">跳到a1处</a> 

## HTML表格标签
表格中的标签
|表格|描述|常用属性|
|:-----:|:-----:|:-----:|
|`<table>`|描述表格|border,width,cellspacing,cellPadding|
|`<caption>`|定义表格的标题|align排列|
|`<th>`|定义表格的表头|align，valign，bgcolor，rowspan，colspan，width，height|
|`<tr>`|定义表格的行|align，valign，bgcolor|
|`<td>`|定义表格的单元格|align，valign，bgcolor，rowspan，colspan，width，height|
|`<thead>`|定义表格的页眉|align，valign|
|`<tbody>`|定义表格的主体|align，valign|
|`<tfoot>`|定义表格的页脚|align，valign|

