Vue
- vue的三种安装方式
  - 直接下载源码，然后通过路径引入 [开发版本](https://vuejs.org/js/vue.js) , [生产版本](https://vuejs.org/js/vue.min.js)
  - 在线cdn引入的方式`<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>`
  - 采用npm安装的方式 `npm install vue`
- vue的使用案例
  - body中设置Vue管理的视图 `<div id="app"></div>`
  - 引入vue.js `<script src="./vue.js"></script> <script>`
  - 实例化vue对象
    ```
    <script>
        new Vue({
            el: '#app',
            data: {
                list: 'my name is '
            }
        })
    </script> 
    ```
  - 设置vue实例的选项
  - 在 中通过{{}} 使用data中的数据
- 实例选项 el
  - el: '#app' // id选择器
  - el: '.app' // class选择器
  - el: document.getElementById("#app") // dom对象
- 实例选项 data， 是响应式数据，数据驱动视图
  ```
   let vm = new Vue({ 
        el: "#app",
        data: {
            msg: 'abc',
            count: 100
            list: [1, 2, 3]
        },
        methods: {
            getData() {
                console.log(this.msg)
            },
            getData2() {
                this.getData()
            }
        }
    })
    vm.msg = 200 
    console.log(vm) 
    console.log(vm.msg) 
    console.log(vm.$data.msg)
  ```
- 实例选项 methods
  - methods其值为一个对象
  - 可以通过vm实例访问这些方法，或者在指令表达式中使用
  - 方法中的this自动绑定为vue实例
  - methods中的所有方法同样也被带离岛vue实例对象上，都可以通过this访问
  - 不应该使用箭头函数来定义method，箭头函数绑定了父级作用域的上下文，所以this将不会按照期望指向Vue实例
- 术语解释-指令
  - v-text 更新标签中的内容
  - v-html 更新标签中的内容或者是标签
  - v-if 和 v-show
    - 后面跟着的表达式都是布尔值，布尔值来决定是否隐藏该元素
    - v-if 直接决定元素的添加或者删除，有更高的切换开销
    - v-show 只是根据样式来决定显示隐藏，有更高的初始渲染开销
    - 需要非常频繁地切换，则使用 v-show 较好；在运行时条件很少改变，则使用 v-if 较好
  - v-on
    - 第一种：v-on:事件名=“方法名“ v-on:click="fn"; 第二种：@事件名=“方法名” @click="fn"
    - 修饰符
      - .once 只触发一次回调
      - .prevent 调用event.preventDefault()阻止默认事件
    - 事件对象
      - 第一种:方法名中采用$event的方式传形参；第二种:直接写事件名 默认第一个参数为event事件参数
  - v-for
    - 列表数据变动会导致 视图列表重新更新 为了提升性能 方便更新 需要提供一个属性 key`<li v-for="(v,i) in arr" :key="i">{{v}}</li>`
      ```
      <ul>
          <li v-for="item in items">{{item.name}}</li>
      </ul>
      <ul>
          <li v-for="(item, index) in items">{{index}} {{item.name}}</li>
      </ul>
      data: {
          items: [
              {name: 'dawa'},
              {name: 'erwa'}
          ]
      }
      ```
  - v-bind 绑定标签上的任何属性,当标签上的属性是变量/动态/需要改变的
    - 用法 `<p v-bind:id="ID"></p>` 简写 `<p :id="ID"></p>`
    - 绑定class对象语法 `<p class="obox" :class="{obox:isBn}">内容</p>`
  - v-model 表单元素的绑定，双向数据绑定。
    - 数据发生变化可以更新到界面，通过界面可以更改数据
    - v-model 绑定表单元素，会忽略所有表单元素的 value 、 checked 、 selected 特性的初始值
    - 表单元素会将 Vue 实例的data中的数据作为数据来源，所以应该在 data 选项中声明初始值。
  - v-cloak 解决页面初次渲染时页面模版闪屏现象
    ```
    <style>
    [v-cloak]{
        display: none;
    }
    </style>
    <div id="app" v-cloak>{{msg}}</div>
    ```
  - v-once 指令所在元素只渲染一次
  - 过滤器
    - 全局过滤器 Vue.filter('filterName', function(param) {return param.charAt(0).toUpperCase() + param.slice(1)})
    - 局部过滤器在实例里面的 filters:{过滤器名字:function(param) {return ...}}
    - 多个过滤器
      ```
      <p>{{count|a('元')|b}}</p>
      filters: {
          a:function(val, y){
              // val是count值, y是'元'
          }
      } 
      ```
    - 过滤器完成日期格式处理
      - 引入第三方 moment.js
      - Vue.filter("fmtDate", function(v) {return moment(v).format('YYYY-MM-DD h:mm:ss a')})
  - 自定义指令
