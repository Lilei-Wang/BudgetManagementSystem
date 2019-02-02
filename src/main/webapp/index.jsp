<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/1/26
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<a href="Crawler/Workstation">爬取工作站数据</a><br>
${pageContext.request.contextPath}<br>


<form class="form-horizontal" method="post" action="">
    <fieldset>
        <div id="legend" class="">
            <legend class="">预算辅助管理系统</legend>
        </div>
        <div class="control-group">

            <!-- Select Basic -->
            <label class="control-label">预算类型</label>
            <div class="controls">
                <select class="input-xlarge">
                    <option>专项</option>
                    <option>自筹</option>
                </select>
            </div>

        </div>

        <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">总预算（万元）</label>
            <div class="controls">
                <input type="text" placeholder="" class="input-xlarge">
                <p class="help-block"></p>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"></label>

            <!-- Button -->
            <div class="controls">
                <button class="btn btn-success">生成预算</button>
            </div>
        </div>

    </fieldset>
</form>


</body>
</html>
