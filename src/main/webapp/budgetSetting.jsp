<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/5/23
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预算属性</title>
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
                <%--<li><a href="${pageContext.request.contextPath}/Budget/Detail" >修改预算</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <%--<li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
                <li><a href="${pageContext.request.contextPath}/usercenter.jsp">用户中心</a></li>
                <li class="active"><a href="#">预算属性</a></li>
            </ul>
        </div>
    </div>
</nav>


<iframe hidden id="hidden_frame" name="hidden_frame"></iframe>
<div id="form">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Budget/Setting.do">

        <div class="form-group">
            <label class="control-label col-sm-4">预算名称</label>
            <div class="col-sm-4">
                <input name="name" type="text" placeholder="预算名称" class="form-control" required v-model="budget.name">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">总预算（万元）</label>
            <div class="col-sm-4">
                <input name="total" type="number" placeholder="填写大于0的数字" class="form-control" required v-model="budget.req">
            </div>
            <div class="col-sm-4">
                <label>{{sum}}</label>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">项目周期（年）</label>
            <div class="col-sm-4">
                <input name="years" type="number" placeholder="填写大于0的数字" class="form-control" required>
            </div>
        </div>


        <hr>


        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="equipment" checked required>
                设备费
            </label>
            <div class="col-sm-4">
                <input name="equipment-number" class="form-control" type="number" v-model="budget.equipment.req">
            </div>
            <div class="col-sm-2">
                <input name="equipment-distribution" class="form-control" type="text" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="material" checked required>
                材料费
            </label>
            <div class="col-sm-4">
                <input name="material-number" class="form-control" type="number"  v-model="budget.material.req">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="test-and-process" checked required>
                测试化验加工费
            </label>
            <div class="col-sm-4">
                <input name="test-and-process-number" class="form-control" type="number"  v-model="budget.testAndProcess.req">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="power" checked required>
                燃料动力费
            </label>
            <div class="col-sm-4">
                <input name="power-number" class="form-control" type="number"  v-model="budget.power.req">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="travel" checked required>
                差旅费
            </label>
            <div class="col-sm-4">
                <input name="travel-number" class="form-control" type="number"  v-model="budget.travel.req">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="conference" checked required>
                会议费
            </label>
            <div class="col-sm-4" id="tip">
                <input name="conference-number" class="form-control" type="number"  v-model="budget.conference.req">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="international-communication" checked required>
                国际合作交流费
            </label>
            <div class="col-sm-4">
                <input name="international-communication-number" class="form-control" type="number"
                       v-model="budget.international.req"></div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="property" checked required>
                产权费
            </label>
            <div class="col-sm-4">
                <input name="property-number" class="form-control" type="number"  v-model="budget.property.req"></div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="labour" checked required>
                劳务费
            </label>
            <div class="col-sm-4">
                <input name="labour-number" class="form-control" type="number"  v-model="budget.labour.req"></div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="consultation" checked required>
                咨询费
            </label>
            <div class="col-sm-4">
                <input name="consultation-number" class="form-control" type="number"  v-model="budget.consultation.req"></div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4">
                <input name="items" type="checkbox" value="others" checked required>
                其他费用
            </label>
            <div class="col-sm-4">
                <input name="others-number" class="form-control" type="number"  v-model="budget.others.req"></div>
        </div>

        <!-- Button -->
        <div title="所有复选框必须勾选，如果不需要某类费用，后面的数字填0即可">
            <button class="btn btn-default btn-lg center-block" type="submit">生成预算</button>
            <p class="help-block" style="color:red">所有复选框必须勾选，如果不需要某类费用，后面的数字填0即可</p>
        </div>
    </form>
</div>

<script type="text/javascript">
    var formVue = new Vue({
        el: "#form",
        data:{
            arr:[0,2,5,0,2,5, 6,8,6,2,0],
            total:150,
            budget:{}
        },
        computed:{
            sum:function () {
                var sum=Number(0);
                sum+=(Number(this.budget.equipment.req)
                    +Number(this.budget.material.req)
                    +Number(this.budget.testAndProcess.req)
                    +Number(this.budget.power.req)
                    +Number(this.budget.travel.req)
                    +Number(this.budget.conference.req)
                    +Number(this.budget.international.req)
                    +Number(this.budget.property.req)
                    +Number(this.budget.labour.req)
                    +Number(this.budget.consultation.req)
                    +Number(this.budget.others.req)
                );
                return sum;
            }
        },
        created:function () {
            this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Stats").then(
                function (data) {
                    this.budget = data.body;
                    console.log("show history list");
                }, function (error) {
                    console.log(error)
                }
            )
        }
    })
</script>
</body>
</html>
