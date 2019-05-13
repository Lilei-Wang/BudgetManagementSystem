<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/5/13
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户中心</title>
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
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyDetail.js"></script>--%>
    <style type="text/css">
        body {
            padding-top: 70px;
        }
    </style>

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">预算辅助管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">创建预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/HistoryPage">历史预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Detail">修改预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/usercenter.jsp">用户中心</a></li>
            </ul>
        </div>
    </div>
</nav>


<div>
    <h1>你好！<label id="username" style="color:green;"></label></h1>
    <button class="btn btn-danger" onclick="logout()">退出登录</button>
</div>



<script type="text/javascript">
    var username = get_cookie("username");
    document.getElementById("username").innerText = username;

    function logout() {
        if (confirm(username + "确认退出登录？")) {
            window.location.href = "${pageContext.request.contextPath}/Logout";
        }
    }

    function get_cookie(Name) {
        var search = Name + "="//查询检索的值
        var returnvalue = "";//返回值
        if (document.cookie.length > 0) {
            sd = document.cookie.indexOf(search);
            if (sd != -1) {
                sd += search.length;
                end = document.cookie.indexOf(";", sd);
                if (end == -1)
                    end = document.cookie.length;
                //unescape() 函数可对通过 escape() 编码的字符串进行解码。
                returnvalue = unescape(document.cookie.substring(sd, end))
            }
        }
        return returnvalue;
    }
</script>
</body>
</html>
