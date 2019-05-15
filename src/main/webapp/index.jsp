<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
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
        body{
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
                <li class="active"><a href="${pageContext.request.contextPath}/">创建预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/HistoryPage">历史预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Detail" >修改预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
                <li><a href="${pageContext.request.contextPath}/usercenter.jsp">用户中心</a></li>
            </ul>
        </div>
    </div>
</nav>


<%
    //一些默认值
    int total = 150,
        equiment = 20,
        material = 12,
        test = 15,
        power = 0,
        travel = 12,
        conference = 5,
        international = 6,
        property = 8,
        labour = 66,
        consultation = 2,
        others = 0; %>

<iframe  hidden id="hidden_frame" name="hidden_frame"></iframe>
<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Budget/Generate">

    <div class="form-group">
        <label class="control-label col-sm-4">总预算（万元）</label>
        <div class="col-sm-4">
            <input name="total" type="number" placeholder="" class="form-control" required value=<%=total%>>
        </div>
    </div>


    <hr>


    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="equipment" checked required>
            设备费
        </label>
        <div class="col-sm-4">
            <input name="equipment-number" class="form-control" type="number" value=<%=equiment%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="material" checked required>
            材料费
        </label>
        <div class="col-sm-4">
            <input name="material-number" class="form-control" type="number" value=<%=material%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="test-and-process" checked required>
            测试化验加工费
        </label>
        <div class="col-sm-4">
            <input name="test-and-process-number" class="form-control" type="number" value=<%=test%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="power" checked required>
            燃料动力费
        </label>
        <div class="col-sm-4">
            <input name="power-number" class="form-control" type="number" value=<%=power%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="travel" checked required>
            差旅费
        </label>
        <div class="col-sm-4">
            <input name="travel-number" class="form-control" type="number" value=<%=travel%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="conference" checked required>
            会议费
        </label>
        <div class="col-sm-4" id="tip">
            <input name="conference-number" class="form-control" type="number" value=<%=conference%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="international-communication" checked required>
            国际合作交流费
        </label>
        <div class="col-sm-4">
            <input name="international-communication-number" class="form-control" type="number"
                   value="<%=international%>"></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="property" checked required>
            产权费
        </label>
        <div class="col-sm-4">
            <input name="property-number" class="form-control" type="number" value="<%=property%>"></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="labour" checked required>
            劳务费
        </label>
        <div class="col-sm-4">
            <input name="labour-number" class="form-control" type="number" value="<%=labour%>"></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="consultation" checked required>
            咨询费
        </label>
        <div class="col-sm-4">
            <input name="consultation-number" class="form-control" type="number" value="<%=consultation%>"></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="others" checked required>
            其他费用
        </label>
        <div class="col-sm-4">
            <input name="others-number" class="form-control" type="number" value="<%=others%>"></div>
    </div>

    <!-- Button -->
    <div>
        <button class="btn btn-default btn-lg center-block" type="submit">生成预算</button>
    </div>

</form>

</body>
</html>
