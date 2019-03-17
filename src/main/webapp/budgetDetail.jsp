<%@ page import="java.awt.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="dao.IEquipmentDao" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="dao.IMaterialDao" %>
<%@ page import="dao.ITestAndProcessDao" %>
<%@ page import="beans.*" %><%--
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
<body>

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

<div>
    <button class="btn btn-success">同步预算</button>
    <p class="help-block center-block">当且仅当在生成预算之后对规则进行了更改时需要此操作</p>
</div>

<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#equipment" data-toggle="tab">
            设备费
        </a>
    </li>
    <li><a href="#material" data-toggle="tab">材料费</a></li>
    <li><a href="#test" data-toggle="tab">测试化验加工费</a></li>
    <li><a href="#power" data-toggle="tab">燃料动力费</a></li>
    <li><a href="#travel" data-toggle="tab">差旅费</a></li>
    <li><a href="#conference" data-toggle="tab">会议费</a></li>
    <li><a href="#international" data-toggle="tab">国际交流合作费</a></li>
    <li><a href="#property" data-toggle="tab">出版/文献/信息传播/知识产权事务费</a></li>
    <li><a href="#labour" data-toggle="tab">劳务费</a></li>
    <li><a href="#consultation" data-toggle="tab">咨询费</a></li>
    <li><a href="#others" data-toggle="tab">其他费用</a></li>
    <li><a href="#indirect" data-toggle="tab">间接费用</a></li>

</ul>


<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="equipment">
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
                Budget budget = (Budget) request.getAttribute("budget");
                Map<Item,Integer> map = (Map) budget.getEquipments();

                ServletContext servletContext = this.getServletConfig().getServletContext();
                ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

                IEquipmentDao equipmentDao = applicationContext.getBean(IEquipmentDao.class);
                List<Equipment> equipments = equipmentDao.selectAll();
                for (Equipment equipment : equipments) {
                    out.write("<tr>");
                    out.write("<td>"+equipment.getId()+"</td>");
                    out.write("<td>"+equipment.getName()+"</td>");
                    out.write("<td>"+equipment.getPrice()+"</td>");
                    int num=0;
                    if(map.get(equipment)!=null)
                    {
                        num=map.get(equipment);
                    }
                    out.write("<td>" +
                            "<input type='number' value='"+num+"' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=equip(this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>
        </table>
    </div>
    <div class="tab-pane fade" id="material">
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
                map = (Map)budget.getMaterials();
                IMaterialDao materialDao = applicationContext.getBean(IMaterialDao.class);
                List<Material> materials = materialDao.selectAll();
                for (Material material : materials) {
                    out.write("<tr>");
                    out.write("<td>"+material.getId()+"</td>");
                    out.write("<td>"+material.getName()+"</td>");
                    out.write("<td>"+material.getPrice()+"</td>");
                    int num=0;
                    if(map.get(material)!=null)
                    {
                        num=map.get(material);
                    }
                    out.write("<td>" +
                            "<input type='number' value='"+num+"' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=material(this)>确认</button>" +
                            "</td>");
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
                map = (Map)budget.getTestAndProcesses();
                ITestAndProcessDao testAndProcessDao = applicationContext.getBean(ITestAndProcessDao.class);
                List<TestAndProcess> testAndProcesses = testAndProcessDao.selectAll();
                for (TestAndProcess testAndProcess:testAndProcesses) {
                    out.write("<tr>");
                    out.write("<td>"+testAndProcess.getId()+"</td>");
                    out.write("<td>"+testAndProcess.getName()+"</td>");
                    out.write("<td>"+testAndProcess.getPrice()+"</td>");
                    int num=0;
                    if(map.get(testAndProcess)!=null)
                    {
                        num=map.get(testAndProcess);
                    }
                    out.write("<td>" +
                            "<input type='number' value='"+num+"' />" +
                            "<button type='btn btn-default' class='updateItem' onclick=updateItem('test',this)>确认</button>" +
                            "</td>");
                }
            %>
            </tbody>

        </table>
    </div>
    <div class="tab-pane fade" id="power">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="travel">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="conference">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="international">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="property">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="labour">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="consultation">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="others">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="indirect">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>
</div>


</body>
</html>




<script>
    /*function updateItem(type,btn) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num=btn.parentElement.firstElementChild.value;
        /!*alert(type)
        alert(id)
        alert(num)*!/
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify",
            type:"post",
            data:{
                type:type,
                id:id,
                nums:num
            },
            success:function () {
                alert("success")
            }
        })
    }*/

    function equip(btn) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num=btn.parentElement.firstElementChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Material",
            type:"post",
            data:{
                mode:0,
                id:id,
                nums:num
            },
            success:function () {
                alert("success")
            }
        })
    }

    function material(btn) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var num=btn.parentElement.firstElementChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Material",
            type:"post",
            data:{
                mode:0,
                id:id,
                nums:num
            },
            success:function () {
                alert("success")
            }
        })
    }

   /* function init() {
        var updateEquips=document.getElementsByClassName("updateEquipment");
        alert(updateEquips.length);
        var i;
        for(i=0;i<updateEquips.length;i++)
        {
            var btn=updateEquips[i];
            var num=btn.parentElement.parentElement.firstElementChild.innerHTML
            btn.addEventListener("click",function () { alert(num) })
        }
    }*/

</script>
