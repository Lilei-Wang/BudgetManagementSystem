<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<a href="Crawler/Workstation">爬取工作站数据</a><br>
<a href="Budget/Download">下载</a><br>
${pageContext.request.contextPath}<br>


<% int total = 150,
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
    <fieldset>
        <div id="legend" class="">
            <legend class="">预算辅助管理系统</legend>
        </div>
        <div class="control-group">

            <!-- Select Basic -->
            <label class="control-label">预算类型</label>
            <div class="controls">
                <select name="budget-type" class="input-xlarge">
                    <option>专项</option>
                    <option>自筹</option>
                </select>
            </div>

        </div>

        <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">总预算（万元）</label>
            <div class="controls">
                <input name="total" type="number" placeholder="" class="input-xlarge" required value=<%=total%>>
                <p class="help-block"></p>
            </div>
        </div>


        <div class="control-group">

            <!-- Prepended checkbox -->
            <label class="control-label">选择预算包含的项目：</label>
            <div class="controls">
                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            设备费
                          <input name="items" type="checkbox" value="equipment" checked>
                        </label>
                      </span>
                    <input name="equipment-number" class="span2" type="number" value=<%=equiment%>>
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            材料费
                          <input name="items" type="checkbox" value="material" checked>
                        </label>
                      </span>
                    <input name="material-number" class="span2" type="number" value=<%=material%>>
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            测试化验加工费
                          <input name="items" type="checkbox" value="test-and-process" checked>
                        </label>
                      </span>
                    <input name="test-and-process-number" class="span2" type="number" value=<%=test%>
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            燃料动力费
                          <input name="items" type="checkbox" value="power" checked>
                        </label>
                      </span>
                    <input name="power-number" class="span2" type="number" value=<%=power%>>
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            差旅费
                          <input name="items" type="checkbox" value="travel" checked>
                        </label>
                      </span>
                    <input name="travel-number" class="span2" type="number" value=<%=travel%>>
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            会议费
                          <input name="items" type="checkbox" value="conference" checked>
                        </label>
                      </span>
                    <input name="conference-number" class="span2" type="number" value=<%=conference%>>
                    （系统计算相应的咨询费）
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            国际合作交流费
                          <input name="items" type="checkbox" value="international-communication" checked>
                        </label>
                      </span>
                    <input name="international-communication-number" class="span2" type="number" value="<%=international%>">
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            产权费
                          <input name="items" type="checkbox" value="property" checked>
                        </label>
                      </span>
                    <input name="property-number" class="span2" type="number" value="<%=property%>">
                </div>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            劳务费
                          <input name="items" type="checkbox" value="labour" checked>
                        </label>
                      </span>
                    <input name="labour-number" class="span2" type="number" value="<%=labour%>">
                </div>

                <%--<div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            咨询费
                          <input name="items" type="checkbox" value="consultation">
                        </label>
                      </span>
                    <input name="consultation-number" class="span2" type="number" value="0">
                </div>--%>

                <div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            其他费用
                          <input name="items" type="checkbox" value="others" checked>
                        </label>
                      </span>
                    <input name="others-number" class="span2" type="number" value="<%=others%>">
                </div>

                <%--<div class="input-prepend">
                      <span class="add-on">
                        <label class="checkbox">
                            间接费用
                          <input name="items" type="checkbox" value="indirect">
                        </label>
                      </span>
                    <input name="indirect-number" class="span2" type="number" value="0">
                </div>--%>

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
