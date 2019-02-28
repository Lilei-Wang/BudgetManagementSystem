<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/2/28
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预算明细</title>
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
    <style type="text/css">
        body {
            padding-top: 70px;
        }
    </style>
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

                <li class="dropdown active">
                    <a href="#">
                        修改预算
                    </a>
                    <%--<ul class="dropdown-menu">
                        <li><a href="#">设备费</a></li>
                        <li><a href="#">材料费</a></li>
                        <li><a href="#">测试化验加工费</a></li>
                        <li><a href="#">燃料动力费</a></li>
                        <li><a href="#">差旅费</a></li>
                        <li><a href="#">会议费</a></li>
                        <li><a href="#">国际合作交流费</a></li>
                        <li><a href="#">出版/文献/信息传播/知识产权事务费</a></li>
                        <li><a href="#">劳务费</a></li>
                        <li><a href="#">咨询费</a></li>
                        <li><a href="#">其他费用</a></li>
                    </ul>--%>
                </li>

                <li><a href="#">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
            </ul>
        </div>
    </div>
</nav>




<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#equipment" data-toggle="tab">
            设备费
        </a>
    </li>
    <li><a href="#ios" data-toggle="tab">iOS</a></li>
    <li class="dropdown">
        <a href="#" id="myTabDrop1" class="dropdown-toggle"
           data-toggle="dropdown">Java
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
            <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
            <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
        </ul>
    </li>
</ul>


<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="equipment">
        设备费
        ${budget.listToMap(budget.getEquipments())}
    </div>
    <div class="tab-pane fade" id="ios">
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="tab-pane fade" id="jmeter">
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="tab-pane fade" id="ejb">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>
</div>

</body>
</html>
