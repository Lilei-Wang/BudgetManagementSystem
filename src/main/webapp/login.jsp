<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/4/14
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登录/注册</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
</head>
<body>

<div id="login">
<form>
    <div class="form-group">
        <label for="userName">用户名</label>
        <input type="text" class="form-control" id="userName" placeholder="Name" v-model="user.name">
    </div>
    <div class="form-group">
        <label for="password">密码</label>
        <input type="password" class="form-control" id="password" placeholder="Password" v-model="user.password">
    </div>
    <button type="button" class="btn btn-default" @click="login()">登录</button>
    <button type="button" class="btn btn-default" @click="register()">注册</button>
</form>
    <p id="hint" style="color: red"></p>
</div>
<script>
    var loginVue=new Vue({
        el:"#login",
        data:{
            user:{name:"",password:""}
        },
        methods:{
            login:function () {
                console.log(this.user.name);
                console.log(this.user.password);
                this.$http.post("${pageContext.request.contextPath}/Login",
                    {
                        name:this.user.name,
                        password:this.user.password
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    location.reload();
                },function (reason) {
                    document.getElementById("hint").innerText="用户名或密码不正确";
                });
            },
            register:function () {
                console.log(this.user.name);
                console.log(this.user.password);
                if(this.user.name==="" || this.user.password===""){
                    document.getElementById("hint").innerText="用户名与密码不能为空";
                }else{
                    this.$http.post("${pageContext.request.contextPath}/Register",
                        {
                            name:this.user.name,
                            password:this.user.password
                        },
                        {emulateJSON: true}
                    ).then(function (value) {
                        location.reload();
                    },function (reason) {
                        document.getElementById("hint").innerText="用户名已存在";
                    });
                }
            }
        }
    });
</script>
</body>
</html>
