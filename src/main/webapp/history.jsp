<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/5/12
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>历史预算</title>
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
<body onload="budgetListVue.getBudgetHistory()">

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">预算辅助管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">创建预算</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/Budget/HistoryPage">历史预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Detail" >修改预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
                <li><a href="${pageContext.request.contextPath}/Logout">注销</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="budgetList">
    <ul>
        <li v-for="budget in budgetList">
            <a v-bind:href="'${pageContext.request.contextPath}/Budget/Detail/'+budget.id">
                {{budget.id}}，创建时间：{{budget.date}}
            </a>
        </li>
    </ul>
</div>


<script type="text/javascript">
    var budgetListVue=new Vue({
        el:"#budgetList",
        data:{
            budgetList:[]
        },
        methods:{
            getBudgetHistory:function () {
                /*alert("getBudgetHistory")*/
                this.$http.get("${pageContext.request.contextPath}/Budget/HistoryList").then(
                    function (data) {
                        this.budgetList = data.body.data;
                        console.log("show history list");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            detail:function (budget) {
            }
        }
    })
</script>

</body>
</html>
