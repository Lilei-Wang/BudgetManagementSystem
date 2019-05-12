<%@ page import="java.util.Map" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.*" %>
<%@ page import="dao.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/2/28
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改规则</title>
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
                <li><a href="${pageContext.request.contextPath}/Budget/HistoryPage">历史预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Detail" >修改预算</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                <li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>
                <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
                <li><a href="${pageContext.request.contextPath}/Logout">注销</a></li>
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
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <%
                ServletContext servletContext = this.getServletConfig().getServletContext();
                ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

                IEquipmentDao equipmentDao = applicationContext.getBean(IEquipmentDao.class);
                List<Equipment> equipments = equipmentDao.selectAll();
                for (Equipment equipment : equipments) {
                    out.write("<tr>");
                    out.write("<td>"+equipment.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +equipment.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +equipment.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=equipRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=equipRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>0</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='0"
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=equipRule(this,0)>添加</button>"+
                        "</td>");
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
                IMaterialDao materialDao = applicationContext.getBean(IMaterialDao.class);
                List<Material> materials = materialDao.selectAll();
                for (Material material : materials) {
                    out.write("<tr>");
                    out.write("<td>"+material.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +material.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +material.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=materialRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=materialRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>0</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='0"
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=materialRule(this,0)>添加</button>"+
                        "</td>");
            %>
            </tbody>

        </table>
    </div>
    <div class="tab-pane fade" id="test">
        <p></p>
    </div>
    <div class="tab-pane fade" id="power">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>

    <div class="tab-pane fade" id="travel">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>城市</th>
                <th>往返价格</th>
                <th>伙食补助</th>
                <th>交通补助</th>
                <th>住宿补助</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <%
                /*Map<Travel, Pair> travelsMap = budget.getTravels();*/
                ITravelDao travelDao =
                        applicationContext.getBean(ITravelDao.class);
                List<Travel>  travels= travelDao.selectAll();
                for (Travel item : travels) {
                    out.write("<tr>");
                    out.write("<td>" + item.getId() + "</td>");
                    out.write("<td>"
                            +"<input type='text' size=20 value='"
                            +item.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getPrice()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getFood()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getTraffic()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getAccommodation()
                            +"'/></td>");
                    out.write("<td>" +
                            "<button class='btn btn-success' onclick=travelRule(this,2)>确认</button>" +
                            "<button class='btn btn-danger' onclick=travelRule(this,1)>删除</button>" +
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>" + 0 + "</td>");
                out.write("<td>"
                        +"<input type='text' size=20 value='"
                        +"new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>" +
                        "<button class='btn btn-success' onclick=travelRule(this,0)>添加</button>" +
                        "</td>");
            %>
            </tbody>

        </table>
    </div>

    <div class="tab-pane fade" id="conference">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名称</th>
                <th>所需专家个数</th>
                <th>参会人数</th>
                <th>单价</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <%
                IConferenceDao conferenceDao =
                        applicationContext.getBean(IConferenceDao.class);
                List<Conference>  conferences= conferenceDao.selectAll();
                for (Conference item : conferences) {
                    out.write("<tr>");
                    out.write("<td>"+item.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +item.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input id='experts' type='number' value='"
                            +item.getExperts()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input id='experts' type='number' value='"
                            +item.getPeople()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input id='price' type='number' value='"
                            +item.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=conferenceRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=conferenceRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>"+0+"</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='"
                        +"new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input id='experts' type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"
                        +"<input id='experts' type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"
                        +"<input id='price' type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=conferenceRule(this,0)>添加</button>"+
                        "</td>");
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
                IInternationalCommunicationDao dao = applicationContext.getBean(IInternationalCommunicationDao.class);
                List<InternationalCommunication> items = dao.selectAll();
                for (InternationalCommunication item : items) {
                    out.write("<tr>");
                    out.write("<td>"+item.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +item.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=internationalRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=internationalRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>"+0+"</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='"
                        +"new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=internationalRule(this,0)>添加</button>"+
                        "</td>");
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
                IPropertyDao propertyDao = applicationContext.getBean(IPropertyDao.class);
                List<Property> properties = propertyDao.selectAll();
                for (Property item : properties) {
                    out.write("<tr>");
                    out.write("<td>"+item.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +item.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=propertyRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=propertyRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>"+0+"</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='"
                        +"new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=propertyRule(this,0)>添加</button>"+
                        "</td>");
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
                ILabourDao labourDao = applicationContext.getBean(ILabourDao.class);
                List<Labour> labour= labourDao.selectAll();
                for (Labour item : labour) {
                    out.write("<tr>");
                    out.write("<td>"+item.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +item.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input type='number' value='"
                            +item.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=labourRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=labourRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>"+0+"</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='"
                        +"new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=labourRule(this,0)>添加</button>"+
                        "</td>");
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
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <%
                IConsultationDao consultationDao =
                        applicationContext.getBean(IConsultationDao.class);
                List<Consultation>  consultations= consultationDao.selectAll();
                for (Consultation item : consultations) {
                    out.write("<tr>");
                    out.write("<td>"+item.getId()+"</td>");
                    out.write("<td>"
                            +"<input type='text' size=50 value='"
                            +item.getName()
                            +"'/></td>");
                    out.write("<td>"
                            +"<input id='price' type='number' value='"
                            +item.getPrice()
                            +"'/></td>");
                    out.write("<td>"+
                            "<button class='btn btn-success' onclick=consultationRule(this,2)>确认</button>"+
                            "<button class='btn btn-danger' onclick=consultationRule(this,1)>删除</button>"+
                            "</td>");
                }
                out.write("<tr>");
                out.write("<td>"+0+"</td>");
                out.write("<td>"
                        +"<input type='text' size=50 value='"
                        +"new"
                        +"'/></td>");
                out.write("<td>"
                        +"<input id='price' type='number' value='"
                        +0
                        +"'/></td>");
                out.write("<td>"+
                        "<button class='btn btn-success' onclick=consultationRule(this,0)>添加</button>"+
                        "</td>");
            %>
            </tbody>

        </table>
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
<script>
    function equipRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Equip",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }
    function materialRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Material",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function internationalRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/International",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function propertyRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Property",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function labourRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Labour",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function conferenceRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var experts=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        var people=btn.parentElement.parentElement.childNodes.item(3).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(4).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Conference",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                experts:experts,
                people:people,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }


    function consultationRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Consultation",
            type:"post",
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                curd:curd
            },
            success:function () {
                alert("success");
                //location.reload();
            }
        })
    }

    function travelRule(btn,curd) {
        var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
        var name=btn.parentElement.parentElement.childNodes.item(1).firstChild.value;
        var price=btn.parentElement.parentElement.childNodes.item(2).firstChild.value;
        var food=btn.parentElement.parentElement.childNodes.item(3).firstChild.value;
        var traffic=btn.parentElement.parentElement.childNodes.item(4).firstChild.value;
        var accommodation=btn.parentElement.parentElement.childNodes.item(5).firstChild.value;
        $.ajax({
            url:"${pageContext.request.contextPath}/Budget/Modify/Travel",
            type:"post",
            async:false,
            data:{
                mode:1,
                id:id,
                name:name,
                price:price,
                food:food,
                traffic:traffic,
                accommodation:accommodation,
                curd:curd
            },
            success:function (data) {
                //alert("success");
                alert(data);
                //location.reload();
            }
        })
    }


</script>
</body>
</html>
