<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/1/28
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>测试页面</title>
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
    <script src="https://cdn.staticfile.org/angular.js/1.6.3/angular.min.js"></script>

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
                <li><a href="${pageContext.request.contextPath}/Budget/Detail">修改预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li class="active"><a href="#">测试</a></li>
            </ul>
        </div>
    </div>
</nav>

<button class="btn btn-success" onclick=jsp()>test</button>
<label id="xxx">123445</label>

<a href="Crawler/Workstation">爬取工作站数据</a><br>
<a href="Budget/Download">下载</a><br>
${pageContext.request.contextPath}<br>
<%
    for (int i = 1; i <= 3; i++) {%>
<font size="<%=i%>"> hhhh</font>
<% }
%>

<hr>
<div id="1" ng-app="myApp" ng-controller="ctrl">
    <table>
        <tr ng-repeat="id1 in IDS1">
            <td>{{ id1 }}</td>
            <td>
                <button class="btn btn-success" ng-click="update(this,0)">anwo</button>
            </td>
        </tr>
    </table>
</div>

<div id="2" ng-app="myApp2" ng-controller="ctrl2">
    <table>
        <tr ng-repeat="id2 in IDS2">
            <td>{{ id2 }}</td>
            <td>
                <button class="btn btn-success" ng-click="update(this,0)">anwo</button>
            </td>
        </tr>
    </table>
</div>

<div id="VueTest">
<select v-model="num">
    <option v-for="n in 2800" v-if="n%100==0">{{n}}</option>
</select>
    <p>{{num}}</p>
</div>


<script type="text/javascript">
    angular.module('myApp', []).controller('ctrl', function ($scope, $http) {
        $scope.IDS1 = [1, 2, 3, 4, 5];
        $scope.update = function (btn, curd) {
            alert("update!!!")
            $scope.showList()
        }
        $scope.showList = function () {
            $scope.IDS1 = [6, 7, 8];
            alert("showList")
        }
    });

    angular.module('myApp2', []).controller('ctrl2', function ($scope, $http) {
        $scope.IDS2 = [1, 2, 3, 4, 5];
        $scope.update = function (btn, curd) {
            alert("update!!!")
            $scope.showList()
        }
        $scope.showList = function () {
            $scope.IDS2 = [6, 7, 8];
            alert("showList")
        }
    });
    angular.bootstrap(document.getElementById("2"), ['myApp2']);


    var colors = ["red", "green", "blue", "orange"];
    var i = 0;

    function jsp() {
        document.getElementById("xxx").style.color = colors[i % colors.length];
        i++;
    }

    var vueTest=new Vue({
        el:"#VueTest",
        data:{
            list:[1,2,3,4,5],
            num:500
        }
    });
</script>
</body>
</html>
