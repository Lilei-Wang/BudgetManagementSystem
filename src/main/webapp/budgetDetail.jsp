<%@ page import="java.awt.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="beans.*" %>
<%@ page import="dao.*" %><%--
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
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyDetail.js"></script>--%>
    <style type="text/css">
        body {
            padding-top: 70px;
        }
    </style>
</head>
<body onload="showEquipment()">

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">预算辅助管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">创建预算</a></li>

                <li class="active">
                    <a href="#">
                        修改预算
                    </a>
                </li>

                <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
            </ul>
        </div>
    </div>
</nav>
<%--
<div>
    <button class="btn btn-success">同步预算</button>
    <p class="help-block center-block">当且仅当在生成预算之后对规则进行了更改时需要此操作</p>
</div>--%>

<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#equipment" data-toggle="tab" onclick=showEquipment()>
            设备费
        </a>
    </li>
    <li><a href="#material" data-toggle="tab" onclick=showMaterial()>材料费</a></li>
    <li><a href="#test" data-toggle="tab">测试化验加工费</a></li>
    <li><a href="#power" data-toggle="tab">燃料动力费</a></li>
    <li><a href="#travel" data-toggle="tab" onclick=showTravel()>差旅费</a></li>
    <li><a href="#conference" data-toggle="tab">会议费(包含咨询费)</a></li>
    <li><a href="#international" data-toggle="tab">国际交流合作费</a></li>
    <li><a href="#property" data-toggle="tab">出版/文献/信息传播/知识产权事务费</a></li>
    <li><a href="#labour" data-toggle="tab">劳务费</a></li>
    <li><a href="#consultation" data-toggle="tab">咨询费</a></li>
    <li><a href="#others" data-toggle="tab">其他费用</a></li>
    <li><a href="#indirect" data-toggle="tab" onclick=showIndirect()>间接费用</a></li>

</ul>

<div>
    <div class="text-center">
        <label>总预算</label>
        期望：<label id="total-req"></label>
        实际：<label id="total-sum"></label>
        还差：<label id="total-diff"></label>
    </div>
    <div class="text-center">
        <label>本类预算</label>
        期望：<label id="this-req"></label>
        实际：<label id="this-sum"></label>
        还差：<label id="this-diff"></label>
    </div>
</div>

<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="equipment">
        <%
            Budget budget = (Budget) request.getAttribute("budget");
            double equipSum = 0.00;
            Map<Equipment, Integer> equipmentMap = budget.getEquipments();
            for (Equipment equipment : equipmentMap.keySet()) {
                equipSum += (equipmentMap.get(equipment) * equipment.computeUnitPrice());
            }
        %>
        <%--<div class="center-block">
            需求：<label id="equip-req"><%=budget.getRequirement().getEquip()%>
        </label>
            实际：
            <label id="equip-sum"><%=equipSum%>
            </label>
            还差：
            <label id="equip-diff"><%=budget.getRequirement().getEquip() - equipSum%>
            </label>
        </div>--%>
        <table class="table table-hover">
            <thead>
            <tr>
                <th hidden>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody id="equipment-table">
            <%
                budget = (Budget) request.getAttribute("budget");
                Map<Item, Integer> map = (Map) budget.getEquipments();

                ServletContext servletContext = this.getServletConfig().getServletContext();
                ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

                IEquipmentDao equipmentDao = applicationContext.getBean(IEquipmentDao.class);
                List<Equipment> equipments = equipmentDao.selectAll();
                /*for (Equipment equipment : equipments) {
                    out.write("<tr>");
                    out.write("<td>" + equipment.getId() + "</td>");
                    out.write("<td>" + equipment.getName() + "</td>");
                    out.write("<td>" + equipment.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(equipment) != null) {
                        num = map.get(equipment);
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + num + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=equip(this)>确认</button>" +
                            equipment.getPrice() * num +
                            "</td>");
                }*/
            %>
            </tbody>
            <tr class="success">
                <td>
                    <input type="text" value="new" class="name">
                </td>
                <td><input type="number" value="0" class="price">
                </td>
                <td>
                    <input type="number" value="0" class="nums">
                    <button class="btn" onclick=updateEquipment(this,0)>添加</button>
                </td>
            </tr>
        </table>
    </div>
    <div class="tab-pane fade" id="material">
        <%
            double sum = 0.00;
            double req = budget.getRequirement().getMaterial();
            Map<Material, Integer> materialsMap = budget.getMaterials();
            for (Material material : materialsMap.keySet()) {
                sum += (material.computeUnitPrice() * materialsMap.get(material));
            }
        %>
        <div class="center-block">
            需求：<label id="material-req"><%=req%>
        </label>
            实际：
            <label id="material-sum"><%=sum%>
            </label>
            还差：
            <label id="material-diff"><%=req - sum%>
            </label>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody>
            <%
                map = (Map) budget.getMaterials();
                IMaterialDao materialDao = applicationContext.getBean(IMaterialDao.class);
                List<Material> materials = materialDao.selectAll();
                for (Material material : materials) {
                    out.write("<tr>");
                    out.write("<td>" + material.getId() + "</td>");
                    out.write("<td>" + material.getName() + "</td>");
                    out.write("<td>" + material.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(material) != null) {
                        num = map.get(material);
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + num + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=material(this)>确认</button>" +
                            material.computeUnitPrice() * num + "</td>");
                }
            %>
            </tbody>

        </table>
    </div>
    <div class="tab-pane fade" id="test">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody>
            <%
                map = (Map) budget.getTestAndProcesses();
                ITestAndProcessDao testAndProcessDao = applicationContext.getBean(ITestAndProcessDao.class);
                List<TestAndProcess> testAndProcesses = testAndProcessDao.selectAll();
                for (TestAndProcess testAndProcess : testAndProcesses) {
                    out.write("<tr>");
                    out.write("<td>" + testAndProcess.getId() + "</td>");
                    out.write("<td>" + testAndProcess.getName() + "</td>");
                    out.write("<td>" + testAndProcess.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(testAndProcess) != null) {
                        num = map.get(testAndProcess);
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + num + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=updateItem('test',this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>

        </table>
    </div>
    <div class="tab-pane fade" id="power" >
        <table class="table table-hover">
            <thead>
            <tr>
                <th hidden>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody id="power-table">
            <tr ng-repeat="item in items">
                <td>{{item.name}}</td>
                <td>{{item.price}}</td>
                <td>{{item.nums}}</td>
            </tr>
            </tbody>
            <tr class="success">
                <td>
                    <input type="text" value="new" class="name">
                </td>
                <td><input type="number" value="0" class="price">
                </td>
                <td>
                    <input type="number" value="0" class="nums">
                    <button class="btn" onclick=updateEquipment(this,0)>添加</button>
                </td>
            </tr>
        </table>
    </div>

    <div class="tab-pane fade" id="travel">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>城市</th>
                <th>往返价格</th>
                <th>伙食补助</th>
                <th>交通补助</th>
                <th>住宿补助</th>
                <th>人数</th>
                <th>天数</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody id="travel-table">
            </tbody>
            <form>
            <tr class="success">
                <td><input name="name" type="text"></td>
                <td><input name="price" type="number"></td>
                <td><input name="food" type="number"></td>
                <td><input name="traffic" type="number" ></td>
                <td><input name="accommodation" type="number"></td>
                <td><input name="people" type="number" ></td>
                <td><input name="days" type="number"></td>
                <td>
                    <button class="btn" onclick=updateTravel(this.form,0)>添加</button>
                </td>
            </tr>
            </form>
        </table>
    </div>

    <div class="tab-pane fade" id="conference">
        <%
            sum = 0.0;
            req = budget.getRequirement().getConference();
            Map<Conference, Pair> conferencesMap = budget.getConferences();
            Map<Consultation, Integer> consultationsMap = budget.getConsultations();
            Consultation consultation = null;
            for (Consultation item : consultationsMap.keySet()) {
                consultation = item;
            }
            for (Conference conference : conferencesMap.keySet()) {
                sum += conferencesMap.get(conference).getDays() * ((consultation.getPrice() * conference.getExperts())
                        + conference.getPrice() * conferencesMap.get(conference).getPeople());
            }
        %>
        <div class="center-block">
            需求：<label id="conf-req"><%=req%>
        </label>
            实际：
            <label id="conf-sum"><%=sum%>
            </label>
            还差：
            <label id="conf-diff"><%=req - sum%>
            </label>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>需要专家</th>
                <th>参会人数*会议次数</th>
            </tr>
            </thead>

            <tbody>
            <%
                IConferenceDao conferenceDao =
                        applicationContext.getBean(IConferenceDao.class);
                List<Conference> conferences = conferenceDao.selectAll();
                for (Conference item : conferences) {
                    out.write("<tr>");
                    out.write("<td>" + item.getId() + "</td>");
                    out.write("<td>" + item.getName() + "</td>");
                    out.write("<td>" + item.getPrice() + "</td>");
                    out.write("<td>" + item.getExperts() + "</td>");
                    int people = 0, days = 0;
                    if (conferencesMap.get(item) != null) {
                        people = conferencesMap.get(item).getPeople();
                        days = conferencesMap.get(item).getDays();
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + people + "' />" +
                            "<input type='number' value='" + days + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=conference(this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>

        </table>
    </div>

    <div class="tab-pane fade" id="international">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody>
            <%
                map = (Map) budget.getInternationalCommunications();
                IInternationalCommunicationDao internationalCommunicationDao =
                        applicationContext.getBean(IInternationalCommunicationDao.class);
                List<InternationalCommunication> internationalCommunications = internationalCommunicationDao.selectAll();
                for (InternationalCommunication item : internationalCommunications) {
                    out.write("<tr>");
                    out.write("<td>" + item.getId() + "</td>");
                    out.write("<td>" + item.getName() + "</td>");
                    out.write("<td>" + item.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(item) != null) {
                        num = map.get(item);
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + num + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=international(this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>

        </table>
    </div>

    <div class="tab-pane fade" id="property">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody>
            <%
                map = (Map) budget.getProperties();
                IPropertyDao propertyDao =
                        applicationContext.getBean(IPropertyDao.class);
                List<Property> properties = propertyDao.selectAll();
                for (Property item : properties) {
                    out.write("<tr>");
                    out.write("<td>" + item.getId() + "</td>");
                    out.write("<td>" + item.getName() + "</td>");
                    out.write("<td>" + item.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(item) != null) {
                        num = map.get(item);
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + num + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=property(this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>

        </table>
    </div>

    <div class="tab-pane fade" id="labour">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>单价</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody>
            <%
                map = (Map) budget.getLabour();
                ILabourDao labourDao =
                        applicationContext.getBean(ILabourDao.class);
                List<Labour> labour = labourDao.selectAll();
                for (Labour item : labour) {
                    out.write("<tr>");
                    out.write("<td>" + item.getId() + "</td>");
                    out.write("<td>" + item.getName() + "</td>");
                    out.write("<td>" + item.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(item) != null) {
                        num = map.get(item);
                    }
                    out.write("<td>" +
                            "<input type='number' value='" + num + "' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=labour(this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>

        </table>
    </div>

    <div class="tab-pane fade" id="consultation">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>人员类型</th>
                <th>费用标准</th>
                <th>人数</th>
            </tr>
            </thead>

            <tbody>
            <%
                map = (Map) budget.getConsultations();
                IConsultationDao consultationDao =
                        applicationContext.getBean(IConsultationDao.class);
                List<Consultation> consultations = consultationDao.selectAll();
                for (Consultation item : consultations) {
                    out.write("<tr>");
                    out.write("<td>" + item.getId() + "</td>");
                    out.write("<td>" + item.getName() + "</td>");
                    out.write("<td>" + item.getPrice() + "</td>");
                    int num = 0;
                    if (map.get(item) != null) {
                        num = map.get(item);
                    }
                    out.write("<td>" + num + "</td>");
                }
            %>
            </tbody>
        </table>
        <p class="help-block">此项由会议费决定，不可单独更改</p>
    </div>

    <div class="tab-pane fade" id="others">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="indirect">
        <table class="table table-hover">
            <thead>
            <tr>
                <th hidden>ID</th>
                <th>名称</th>
                <th>费用</th>
                <th>数量</th>
            </tr>
            </thead>

            <tbody id="indirect-body">
            </tbody>
            <tr>
                <td>
                    <input type="text">
                </td>
                <td><input type="number">
                </td>
                <td>
                    <input type="number">
                    <button class="btn" onclick=updateIndirect(this)>添加</button>
                </td>
            </tr>
        </table>
    </div>
</div>

<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>


<script>

    function equip(btn) {
        var id = btn.parentElement.parentElement.firstElementChild.innerHTML;
        var price = btn.parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling.innerHTML;
        var num = btn.parentElement.firstElementChild.value;
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Equip",
            type: "post",
            data: {
                mode: 0,
                id: id,
                price: price,
                nums: num
            },
            success: function (data) {
                req = Number(document.getElementById("equip-req").innerHTML)
                data = Number(data)

                document.getElementById("equip-sum").innerHTML = data
                document.getElementById("equip-diff").innerHTML = req - data
                //alert("success");
                //location.reload();
            }
        })
    }

    function updateEquipment(button, curd) {
        var root = button.parentNode.parentNode;
        var name = root.getElementsByClassName("name").item(0).value
        var price = root.getElementsByClassName("price").item(0).value
        var nums = root.getElementsByClassName("nums").item(0).value
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Equip",
            type: "post",
            data: {
                name: name,
                price: price,
                nums: nums,
                curd: curd
            },
            success: function (data) {
                showEquipment();
            }
        })
    }

    function appendEquipment(id, name, price, nums, root) {
        var table = root;
        var tr = document.createElement("tr");
        // var td=document.createElement("td");
        //td.innerHTML=(id==null)?0:id;
        //tr.appendChild(td);
        td = document.createElement("td");
        input = document.createElement("input");
        input.type = "text";
        input.id = "name";
        input.value = name;
        input.setAttribute("readOnly", true);
        input.classList.add("name");
        td.appendChild(input);
        tr.appendChild(td);
        td = document.createElement("td");
        input = document.createElement("input");
        input.type = "number";
        input.value = price;
        input.id = "price";
        input.classList.add("price");
        td.appendChild(input);
        tr.appendChild(td);
        td = document.createElement("td");
        input = document.createElement("input");
        input.type = "number";
        input.value = nums;
        input.id = "nums";
        input.classList.add("nums");
        td.appendChild(input);
        var button = document.createElement("button");
        button.innerHTML = "确认";
        button.classList.add("btn");
        button.classList.add("btn-success");
        button.onclick = function (ev) {
            updateEquipment(button, 2)
        };
        var del = document.createElement("button");
        del.innerHTML = "删除";
        del.classList.add("btn");
        del.classList.add("btn-danger");
        del.onclick = function (ev) {
            updateEquipment(del, 1)
        };
        td.appendChild(button);
        td.appendChild(del);
        tr.appendChild(td);
        table.appendChild(tr);
    }

    function showEquipment() {
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Detail/Equipment",
            type: "post",
            dataType: "json",
            success: function (data) {
                var table = document.getElementById("equipment-table");
                table.innerText = "";
                //alert(data.length)
                list = data.equipments;
                for (var i = 0; i < list.length; i++) {
                    appendEquipment(list[i].id, list[i].name, list[i].price, list[i].nums, table)
                }
                //appendEquipment(0,"",0,0,table.nextElementSibling);
                updateBudgetPage("equipment");
            },
            error: function (data) {
                alert(data);
            }
        })
    }

    function material(btn) {
        var id = btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num = btn.parentElement.firstElementChild.value;
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Material",
            type: "post",
            data: {
                mode: 0,
                id: id,
                nums: num
            },
            success: function (data) {
                req = Number(document.getElementById("material-req").innerHTML)
                data = Number(data)

                document.getElementById("material-sum").innerHTML = data
                document.getElementById("material-diff").innerHTML = req - data
                //alert("success");
                //location.reload();
            }
        })
    }

    function international(btn) {
        var id = btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num = btn.parentElement.firstElementChild.value;
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/International",
            type: "post",
            data: {
                mode: 0,
                id: id,
                nums: num
            },
            success: function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function property(btn) {
        var id = btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num = btn.parentElement.firstElementChild.value;
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Property",
            type: "post",
            data: {
                mode: 0,
                id: id,
                nums: num
            },
            success: function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function labour(btn) {
        var id = btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num = btn.parentElement.firstElementChild.value;
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Labour",
            type: "post",
            data: {
                mode: 0,
                id: id,
                nums: num
            },
            success: function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function conference(btn) {
        var id = btn.parentElement.parentElement.firstElementChild.innerHTML;
        var people = btn.parentElement.childNodes.item(0).value;
        var days = btn.parentElement.childNodes.item(1).value;
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Conference",
            type: "post",
            data: {
                mode: 0,
                id: id,
                people: people,
                days: days
            },
            success: function (data) {
                req = Number(document.getElementById("conf-req").innerHTML)
                data = Number(data)

                document.getElementById("conf-sum").innerHTML = data
                document.getElementById("conf-diff").innerHTML = req - data
                //alert("success");
                //location.reload();
            }
        })
    }

    function updateTravel(form,curd) {
        form.action="${pageContext.request.contextPath}/Budget/Modify/Travel";
        form.appendChild(getInput("number",curd,"curd",false));
        form.method="post";
        form.target="hidden_frame";
        form.submit();
    }

    function showTravel() {
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Detail/Travel",
            type: "post",
            dataType: "json",
            success: function (data) {
                var table = document.getElementById("travel-table");
                table.innerText = "";
                list = data.travels;
                for (var i = 0; i < list.length; i++) {
                    appendTravel(list[i].name, list[i].price, list[i].food,
                        list[i].traffic,
                        list[i].accommodation,
                        list[i].people,
                        list[i].days,
                        table)
                }
                updateBudgetPage("travel");
            },
            error: function (data) {
                alert(data);
            }
        })
    }

    function getTr(width) {
        tr=document.createElement("tr");
        tr.style.width=width;
        return tr;
    }

    function getTd(width) {
        td=document.createElement("td");
        td.style.width=width;
        return td;
    }

    function getInput(type, value, name, readOnly) {
        var input=document.createElement("input");
        input.type=type;
        input.value=value;
        input.name=name;
        input.readOnly=readOnly;
        return input;
    }

    function getButton(innerHTML, claszzList, onclick) {
        var button=document.createElement("button");
        button.innerHTML=innerHTML;
        button.onclick=onclick;
        for(var i=0;i<claszzList.length;i++)
            button.classList.add(claszzList[i]);
        return button;
    }

    function appendTravel(name,price,food,traffic,accommodation,people,days,table) {
        width=100;
        var form=document.createElement("form");
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("text",name,"name",true))));
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("number",price,"price",false))));
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("number",food,"food",false))));
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("number",traffic,"traffic",false))));
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("number",accommodation,"accommodation",false))));
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("number",people,"people",false))));
        form.appendChild(getTr(width).appendChild(getTd(width).appendChild(getInput("number",days,"days",false))));
        form.appendChild(getTr(width)
            .appendChild(getTd(width).appendChild(getButton("确认",["btn","btn-success"],function () {
                updateTravel(form,2);
            }))));
        form.appendChild(getTr(width)
            .appendChild(getTd(width).appendChild(getButton("删除",["btn","btn-danger"],function () {
                updateTravel(form,1);
            }))));
        form.appendChild(tr);
        table.appendChild(form);
        //body.appendChild(table);
    }


    /**
     * 更新差值
     * @param type 当前预算种类
     */
    function updateBudgetPage(type) {
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Detail/Stats",
            type: "post",
            dataType: "json",
            success: function (data) {
                document.getElementById("total-req").innerHTML = data.req;
                document.getElementById("total-sum").innerHTML = data.sum;
                document.getElementById("total-diff").innerHTML = data.diff;
                if (data.diff < 0)
                    document.getElementById("total-diff").style.color = "red";
                else
                    document.getElementById("total-diff").style.color = "green";

                document.getElementById("this-req").innerHTML = data[type].req;
                document.getElementById("this-sum").innerHTML = data[type].sum;
                document.getElementById("this-diff").innerHTML = data[type].diff;
                if (data[type].diff < 0)
                    document.getElementById("this-diff").style.color = "red";
                else
                    document.getElementById("this-diff").style.color = "green";
                //alert("updateBudgetPage");
                //location.reload();
            }
        })
    }

    function showIndirect() {
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Detail/Indirect",
            type: "post",
            dataType: "json",
            success: function (data) {
                var table = document.getElementById("indirect-body");
                table.innerText = "";
                //alert(data.length)
                list = data.indirects;
                for (var i = 0; i < list.length; i++) {
                    appendIndirect(list[i].id, list[i].name, list[i].price, list[i].nums)
                }
                updateBudgetPage("indirect");
            },
            error: function (data) {
                alert(data);
            }
        })
    }

    function appendIndirect(id, name, price, nums) {
        var table = document.getElementById("indirect-body");
        var tr = document.createElement("tr");
        // var td=document.createElement("td");
        //td.innerHTML=(id==null)?0:id;
        //tr.appendChild(td);
        td = document.createElement("td");
        input = document.createElement("input");
        input.type = "text";
        input.value = name;
        td.appendChild(input);
        tr.appendChild(td);
        td = document.createElement("td");
        input = document.createElement("input");
        input.type = "number";
        input.value = price;
        td.appendChild(input);
        tr.appendChild(td);
        td = document.createElement("td");
        input = document.createElement("input");
        input.type = "number";
        input.value = nums;
        td.appendChild(input);
        var button = document.createElement("button");
        button.innerHTML = "确认";
        button.classList.add("btn");
        button.onclick = function (ev) {
            updateIndirect(button)
        };
        td.appendChild(button);
        tr.appendChild(td);
        table.appendChild(tr);
    }

    function updateIndirect(btn) {
        //alert(btn.innerHTML);
        $.ajax({
            url: "${pageContext.request.contextPath}/Budget/Modify/Indirect",
            type: "post",
            dataType: "json",
            success: function (data) {
                showIndirect();
                updateBudgetPage();
            },
            error: function (data) {
                alert("error");
            }
        })
    }

</script>
