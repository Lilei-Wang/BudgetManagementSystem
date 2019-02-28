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
            <a class="navbar-brand" href="#">预算辅助管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">创建预算</a></li>

                <li>
                    <a href="${pageContext.request.contextPath}/Budget/Detail" >
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


<%
    //一些默认值
    int total = 150,
        equiment = 20,
        material = 12,
        test = 15,
        power = 0,
        travel = 12,
        conference = 3,
        international = 6,
        property = 8,
        labour = 66,
        others = 0; %>


<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Budget/Generate">
    <%--<fieldset>--%>
    <div class="form-group">
        <label class="control-label col-sm-4">预算类型</label>
        <div class="col-sm-4">
            <select name="budget-type" class="form-control">
                <option>专项</option>
                <option>自筹</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">总预算（万元）</label>
        <div class="col-sm-4">
            <input name="total" type="number" placeholder="" class="form-control" required value=<%=total%>>
        </div>
    </div>


    <hr>


    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="equipment" checked>
            设备费
        </label>
        <div class="col-sm-4">
            <input name="equipment-number" class="form-control" type="number" value=<%=equiment%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="material" checked>
            材料费
        </label>
        <div class="col-sm-4">
            <input name="material-number" class="form-control" type="number" value=<%=material%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="test-and-process" checked>
            测试化验加工费
        </label>
        <div class="col-sm-4">
            <input name="test-and-process-number" class="form-control" type="number" value=<%=test%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="power" checked>
            燃料动力费
        </label>
        <div class="col-sm-4">
            <input name="power-number" class="form-control" type="number" value=<%=power%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="travel" checked>
            差旅费
        </label>
        <div class="col-sm-4">
            <input name="travel-number" class="form-control" type="number" value=<%=travel%>>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="conference" checked>
            会议费
        </label>
        <div class="col-sm-4" id="tip">
            <input name="conference-number" class="form-control" type="number" value=<%=conference%>>
            （系统计算相应的咨询费）
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="international-communication" checked>
            国际合作交流费
        </label>
        <div class="col-sm-4">
            <input name="international-communication-number" class="form-control" type="number"
                   value="<%=international%>"></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="property" checked>
            产权费
        </label>
        <div class="col-sm-4">
            <input name="property-number" class="form-control" type="number" value="<%=property%>"></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="labour" checked>
            劳务费
        </label>
        <div class="col-sm-4">
            <input name="labour-number" class="form-control" type="number" value="<%=labour%>"></div>
    </div>

    <%--<div class="checkbox">
          <span class="add-on">
            <label class="checkbox">
                咨询费
              <input name="items" type="checkbox" value="consultation">
            </label>
          </span>
        <input name="consultation-number" class="form-control" type="number" value="0">
    </div>--%>

    <div class="form-group">
        <label class="control-label col-sm-4">
            <input name="items" type="checkbox" value="others" checked>
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
