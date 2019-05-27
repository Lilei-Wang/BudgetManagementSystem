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
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
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
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyDetail.js"></script>--%>
    <style type="text/css">
        body {
            padding-top: 70px;
        }

        table {;
        }

        .sidenav {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 1;
            top: 70px;
            left: 0;
            background-color: #66afe9;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
        }

        .sidenav a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 15px;
            color: white;
            display: block;
            transition: 0.3s;
        }

        .sidenav a:hover, .offcanvas a:focus {
            color: #f1f1f1;
        }

        .sidenav .closebtn {
            position: absolute;
            top: 0;
            right: 25px;
            font-size: 36px;
            margin-left: 50px;
        }

        #main {
            transition: margin-left .5s;
            padding: 0px;
        }

        @media screen and (max-height: 450px) {
            .sidenav {
                padding-top: 15px;
            }

            .sidenav a {
                font-size: 18px;
            }
        }
    </style>

    <%--Vue--%>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
</head>
<body onload="equipmentVue.showlist()">
<div>
    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <table class="table">
            <thead>
            <tr>
                <th>费用</th>
                <th>实际</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>设备费</td>
                <td>{{budget.equipment.sum}}</td>
            </tr>
            <tr>
                <td>材料费</td>
                <td>{{budget.material.sum}}</td>
            </tr>
            <tr>
                <td>测试化验加工费</td>
                <td>{{budget.testAndProcess.sum}}</td>
            </tr>
            <tr>
                <td>燃料动力费</td>
                <td>{{budget.power.sum}}</td>
            </tr>
            <tr>
                <td>差旅费</td>
                <td>{{budget.travel.sum}}</td>
            </tr>
            <tr>
                <td>会议费</td>
                <td>{{budget.conference.sum}}</td>
            </tr>
            <tr>
                <td>国际合作交流费</td>
                <td>{{budget.international.sum}}</td>
            </tr>
            <tr>
                <td>出版/文献/信息传播/知识产权事务费</td>
                <td>{{budget.property.sum}}</td>
            </tr>
            <tr>
                <td>劳务费</td>
                <td>{{budget.labour.sum}}</td>
            </tr>
            <tr>
            <td>咨询费</td>
            <td>{{budget.consultation.sum}}</td>
            </tr>
            <tr>
                <td>其他费用</td>
                <td>{{budget.others.sum}}</td>
            </tr>
            <tr>
                <td>间接费用</td>
                <td>{{budget.indirect.sum}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="main">
        <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; 打开预算分布</span>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">预算辅助管理系统</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/">创建预算</a></li>
                        <li><a href="${pageContext.request.contextPath}/Budget/HistoryPage">历史预算</a></li>
                        <li><a href="${pageContext.request.contextPath}/Rule/">修改规则</a></li>
                        <%--<li><a href="${pageContext.request.contextPath}/Budget/Download">导出最新预算</a></li>--%>
                        <li><a href="${pageContext.request.contextPath}/Test">测试</a></li>
                        <li><a href="${pageContext.request.contextPath}/usercenter.jsp">用户中心</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/Budget/Detail">修改预算</a></li>
                    </ul>
                </div>
            </div>
        </nav>


        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#equipment" data-toggle="tab" onclick=equipmentVue.showlist()>
                    设备费
                </a>
            </li>
            <li><a href="#material" data-toggle="tab" onclick="materialVue.showlist()">材料费</a></li>
            <li><a href="#test" data-toggle="tab" onclick="testVue.showlist()">测试化验加工费</a></li>
            <li><a href="#power" data-toggle="tab">燃料动力费</a></li>
            <li><a href="#travel" data-toggle="tab" onclick=travelVue.showlist()>差旅费</a></li>
            <li><a href="#conference" data-toggle="tab" onclick="conferenceVue.showlist()">会议费</a></li>
            <li><a href="#international" data-toggle="tab" onclick="internationalVue.showlist()">国际交流合作费</a></li>
            <li><a href="#property" data-toggle="tab" onclick="propertyVue.showlist()">出版/文献/信息传播/知识产权事务费</a></li>
            <li><a href="#labour" data-toggle="tab" onclick="labourVue.showlist()">劳务费</a></li>
            <li><a href="#consultation" data-toggle="tab" onclick="consultationVue.showlist()">咨询费</a></li>
            <li><a href="#others" data-toggle="tab">其他费用</a></li>
            <li><a href="#indirect" data-toggle="tab" onclick="indirectVue.showlist()">间接费用</a></li>

        </ul>
        <h3>
            <div>
                <div class="text-center">
                    <label>总预算</label>
                    期望：<label id="total-req"></label>
                    实际：<label id="total-sum"></label>
                    <span id="total-hint"></span><label id="total-diff"></label>
                </div>
                <div class="text-center">
                    <label>本类预算</label>
                    期望：<label id="this-req"></label>
                    实际：<label id="this-sum"></label>
                    <span id="this-hint"></span><label id="this-diff"></label>
                </div>
            </div>
        </h3>

        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="equipment">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>编号</th>
                        <th>名称</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody id="equipment-table">
                    <tr v-for="item in items">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>
                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加到本费用</button>
                            <button class="btn btn-success" @click="addToDatabase(sample)">添加到数据库</button>
                        </td>
                    </tr>
                    <tr class="info" v-if="database.length>0">
                        <td>
                            <select v-model="selectedIndex" class="form-control">
                                <option v-for="(record,i) in database" :value="i">
                                    {{record.name}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <input type="number" v-model="database[selectedIndex].price" class="form-control">
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="addFromDatabase(database[selectedIndex])">添加
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane fade" id="material">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>单价</th>
                        <th>数量</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>
                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="tab-pane fade" id="test">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>单价</th>
                        <th>数量</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>
                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="tab-pane fade" id="power">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>编号</th>
                        <th>名称</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody id="power-table">
                    <tr v-for="power in items">
                        <td><input type="text" readonly v-model="power.name"></td>
                        <td><input type="number" v-model="power.price"></td>
                        <td><input type="number" v-model="power.nums"></td>
                        <td>
                            <button class="btn btn-success" @click="update(power)">确认</button>
                            <button class="btn btn-danger" @click="del(power)">删除</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane fade" id="travel">
                <table class="table table-hover text-nowrap">
                    <thead>
                    <tr>
                        <th>城市</th>
                        <th>市际交通（往返）</th>
                        <th>伙食补助</th>
                        <th>市内交通补助（公杂费）</th>
                        <th>住宿补助</th>
                        <th>人数</th>
                        <th>天数</th>
                        <th>次数</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody id="travel-table">
                    <tr v-for="item in items" v-bind:title="item.name">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td><input type="number" v-model="item.food" class="form-control"></td>
                        <td><input type="number" v-model="item.traffic" class="form-control"></td>
                        <td><input type="number" v-model="item.accommodation" class="form-control"></td>
                        <td><input type="number" v-model="item.people" class="form-control"></td>
                        <td><input type="number" v-model="item.days" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>

                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.food" class="form-control"></td>
                        <td><input type="number" v-model="sample.traffic" class="form-control"></td>
                        <td><input type="number" v-model="sample.accommodation" class="form-control"></td>
                        <td><input type="number" v-model="sample.people" class="form-control"></td>
                        <td><input type="number" v-model="sample.days" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加</button>
                        </td>
                    </tr>

                    <tr class="info" v-if="database.length>0">
                        <td>
                            <select v-model="selectedIndex" class="form-control">
                                <option v-for="(record,i) in database" :value="i">
                                    {{record.name}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <input type="number" v-model="database[selectedIndex].price" class="form-control">
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].food" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].traffic" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].accommodation" class="form-control">
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].people" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].days" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(database[selectedIndex])">添加</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane fade" id="conference">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>编号</th>
                        <th>名称</th>
                        <th hidden>专家类型</th>
                        <th hidden>需要专家人数</th>
                        <th>单价</th>
                        <th>参会人数</th>
                        <th>天数</th>
                        <th>会议次数</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items" v-bind:title="item.name">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td><input type="number" v-model="item.people" class="form-control"></td>
                        <td><input type="number" v-model="item.days" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>

                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.people" class="form-control"></td>
                        <td><input type="number" v-model="sample.days" class="form-control"></td>
                        <td><input type="number" v-model="sample.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="tab-pane fade" id="international">
                <div>
                    <p class="help-block"><b>市际</b>交通：指代往返价格、会议注册费等一次性费用</p>
                    <p class="help-block"><b>市内</b>交通：指代一切公杂费</p>
                </div>
                <table class="table table-hover text-nowrap">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>市际交通</th>
                        <th>伙食补贴</th>
                        <th>市内交通</th>
                        <th>住宿补贴</th>
                        <th>人数</th>
                        <th>天数</th>
                        <th>次数</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items" v-bind:title="item.name">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td>
                            <select class="form-control" v-model="item.price">
                                <option v-for="n in 40000" v-if="n%1000==0">{{n}}</option>
                            </select>
                        </td>
                        <td><input type="number" v-model="item.food" class="form-control"></td>
                        <td><input type="number" v-model="item.traffic" class="form-control"></td>
                        <td><input type="number" v-model="item.accommodation" class="form-control"></td>
                        <td><input type="number" v-model="item.people" class="form-control"></td>
                        <td><input type="number" v-model="item.days" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>

                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.food" class="form-control"></td>
                        <td><input type="number" v-model="sample.traffic" class="form-control"></td>
                        <td><input type="number" v-model="sample.accommodation" class="form-control"></td>
                        <td><input type="number" v-model="sample.people" class="form-control"></td>
                        <td><input type="number" v-model="sample.days" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加</button>
                        </td>
                    </tr>

                    <tr class="info" v-if="database.length>0">
                        <td>
                            <select v-model="selectedIndex" class="form-control">
                                <option v-for="(record,i) in database" :value="i">
                                    {{record.name}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <input type="number" v-model="database[selectedIndex].price" class="form-control">
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].food" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].traffic" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].accommodation" class="form-control">
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].people" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].days" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(database[selectedIndex])">添加</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="tab-pane fade" id="property">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>编号</th>
                        <th>名称</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>
                    <tr class="success">
                        <td><input type="text" v-model="sample.name" class="form-control"></td>
                        <td><input type="number" v-model="sample.price" class="form-control"></td>
                        <td><input type="number" v-model="sample.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(sample)">添加</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="tab-pane fade" id="labour">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>编号</th>
                        <th>类型</th>
                        <th>劳务费（每人每月）</th>
                        <th>人</th>
                        <th>月</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items" v-bind:title="item.name">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <td><input type="number" v-model="item.price" class="form-control">五险一金：{{item.tax}}</td>
                        <td><input type="number" v-model="item.people" class="form-control"></td>
                        <td><input type="number" v-model="item.months" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>

                    <tr class="info" v-if="database.length>0">
                        <td>
                            <select v-model="selectedIndex" class="form-control">
                                <option v-for="(record,i) in database" :value="i">
                                    {{record.name}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <input type="number" v-model="database[selectedIndex].price" class="form-control">
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].people" class="form-control"></td>
                        <td><input type="number" v-model="database[selectedIndex].months" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(database[selectedIndex])">添加</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="tab-pane fade" id="consultation">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>编号</th>
                        <th>人员类型</th>
                        <th>费用标准</th>
                        <th>人数</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="item in items" v-bind:title="item.name">
                        <td><input type="text" readonly v-model="item.name" class="form-control"></td>
                        <%--<td><input type="number" v-model="item.price"></td>--%>
                        <td>
                            <select class="form-control" v-model="item.price">
                                <option v-for="n in 2800" v-if="n%100==0">{{n}}</option>
                            </select>
                        </td>
                        <td><input type="number" v-model="item.nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                            <button class="btn btn-danger" @click="del(item)">删除</button>
                        </td>
                    </tr>

                    <tr class="info" v-if="database.length>0">
                        <td>
                            <select v-model="selectedIndex" class="form-control">
                                <option v-for="(record,i) in database" :value="i">
                                    {{record.name}}
                                </option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" v-model="database[selectedIndex].price">
                                <option v-for="n in 2800" v-if="n%100==0">{{n}}</option>
                            </select>
                        </td>
                        <td><input type="number" v-model="database[selectedIndex].nums" class="form-control"></td>
                        <td>
                            <button class="btn btn-success" @click="add(database[selectedIndex])">添加</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane fade" id="others">

            </div>

            <div class="tab-pane fade" id="indirect">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden>ID</th>
                        <th>名称</th>
                        <th>费用</th>
                        <th>数量</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody id="indirect-body">
                    <tr v-for="item in items">
                        <td>{{item.name}}</td>
                        <td><input type="number" v-model="item.price" class="form-control"></td>
                        <td>{{item.nums}}</td>
                        <td>
                            <button class="btn btn-success" @click="update(item)">确认</button>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </div>
        </div>
    </div>
</div>

<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
</body>
</html>


<script>

    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }

    function inItems(value, items) {
        for (var i = 0; i < items.length; i++) {
            if (value.name === items[i].name) {
                return true;
            }
        }
        return false;
    }


    var sidenavVue=new Vue({
        el:"#mySidenav",
        data:{
            budget:{}
        }
    });

    var equipmentVue = new Vue({
        el: "#equipment",
        data: {
            sample: {name: "sample", price: 0, nums: 0},
            items: [],
            database: [{name: "sample", price: 0, nums: 0}],
            selectedIndex: 0
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            addFromDatabase: function (item) {
                this.doUpdate(item, 3);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Equipment").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("equipment");
                        this.showDatabase();
                    }, function (error) {
                        console.log(error)
                    }
                );
            },
            showDatabase: function () {
                this.$http.get("${pageContext.request.contextPath}/Database/Query/Equipment").then(
                    function (data) {
                        var tmp = data.body.data;
                        for (var i = 0; i < tmp.length; i++) {
                            if (inItems(tmp[i], this.items)) {
                                console.log("inItems:true, " + tmp[i].name);
                                tmp.splice(i, 1);
                                i--;
                            }
                        }
                        this.database = tmp;
                        console.log("show database");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Equipment",
                    {
                        name: item.name,
                        price: item.price,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                }, function (error) {
                    alert(error.bodyText);
                    this.showlist()
                });
            }
        },
        created: function () {
        }
    });

    var materialVue = new Vue({
        el: "#material",
        data: {
            items: [],
            sample: {name: "sample", price: 0, nums: 0}
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Material").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("material")
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Material",
                    {
                        name: item.name,
                        price: item.price,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                }, function (error) {
                    alert(error.bodyText);
                    this.showlist()
                });
            }
        }
    });

    var testVue = new Vue({
        el: "#test",
        data: {
            items: [],
            sample: {name: "sample", price: 0, nums: 0}
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/TestAndProcess").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("testAndProcess")
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/TestAndProcess",
                    {
                        name: item.name,
                        price: item.price,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                }, function (error) {
                    alert(error.bodyText);
                    this.showlist()
                });
            }
        }
    });

    var internationalVue = new Vue({
        el: "#international",
        data: {
            items: [],
            sample: {name: "sample", price: 0, food: 0, accommodation: 0, traffic: 0, days: 0, people: 0, nums: 0},
            database: [{name: "sample", price: 0, food: 0, accommodation: 0, traffic: 0, days: 0, people: 0, nums: 0}],
            selectedIndex: 0
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/International").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("international");
                        this.showDatabase();
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            showDatabase: function () {
                this.$http.get("${pageContext.request.contextPath}/Database/Query/International").then(
                    function (data) {
                        var tmp = data.body.data;
                        for (var i = 0; i < tmp.length; i++) {
                            if (inItems(tmp[i], this.items)) {
                                console.log("inItems:true, " + tmp[i].name);
                                tmp.splice(i, 1);
                                i--;
                            }
                        }
                        this.database = tmp;
                        console.log("show database");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/International",
                    {
                        name: item.name,
                        price: item.price,
                        food: item.food,
                        accommodation: item.accommodation,
                        traffic: item.traffic,
                        days: item.days,
                        people: item.people,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        }
    });

    var powerVue = new Vue({
        el: "#power",
        data: {
            items: {}
        },
        methods: {
            update: function (item) {
                alert("update")
            },
            del: function (item) {
                alert("delete")
            },
            add: function (item) {
                alert("add")
            }
        },
        created: function () {
        }
    });

    var propertyVue = new Vue({
        el: "#property",
        data: {
            items: [],
            sample: {name: "sample", price: 0, nums: 0}
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Property").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("property");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Property",
                    {
                        name: item.name,
                        price: item.price,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        },
        created: function () {
        }
    });

    var travelVue = new Vue({
        el: "#travel",
        data: {
            items: [],
            sample: {name: "sample", price: 0, food: 0, accommodation: 0, traffic: 0, days: 0, people: 0, nums: 0},
            database: [{name: "sample", price: 0, food: 0, accommodation: 0, traffic: 0, days: 0, people: 0, nums: 0}],
            selectedIndex: 0
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Travel").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("travel");
                        this.showDatabase();
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            showDatabase: function () {
                this.$http.get("${pageContext.request.contextPath}/Database/Query/Travel").then(
                    function (data) {
                        var tmp = data.body.data;
                        for (var i = 0; i < tmp.length; i++) {
                            if (inItems(tmp[i], this.items)) {
                                console.log("inItems:true, " + tmp[i].name);
                                tmp.splice(i, 1);
                                i--;
                            }
                        }
                        this.database = tmp;
                        console.log("show database");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Travel",
                    {
                        name: item.name,
                        price: item.price,
                        food: item.food,
                        accommodation: item.accommodation,
                        traffic: item.traffic,
                        days: item.days,
                        people: item.people,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        },
        created: function () {
        }
    });

    var indirectVue = new Vue({
        el: "#indirect",
        data: {
            items: []
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Indirect").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("indirect-showlist");
                        updateBudgetPage("indirect")
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Indirect",
                    {
                        name: item.name,
                        price: item.price,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        },
        created: function () {
        }
    });

    var conferenceVue = new Vue({
        el: "#conference",
        data: {
            items: [],
            sample: {name: "sample", expertType: "专家", experts: 0, price: 0, people: 0, days: 0, nums: 0}
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Conference").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("conference")
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Conference",
                    {
                        name: item.name,
                        expertType: item.expertType,
                        experts: item.experts,
                        price: item.price,
                        people: item.people,
                        days: item.days,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        },
        created: function () {
        }
    });


    var labourVue = new Vue({
        el: "#labour",
        data: {
            items: [],
            sample: {name: "sample", price: 0, nums: 0},
            selectedIndex: 0,
            database: [{name: "sample", price: 0, nums: 0, people: 0, months: 0}]
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                item.nums = 1;
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Labour").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("labour");
                        this.showDatabase()
                    }, function (error) {
                        console.log(error)
                    }
                );
            },
            showDatabase: function () {
                this.$http.get("${pageContext.request.contextPath}/Database/Query/Labour").then(
                    function (data) {
                        var list = data.body.data;
                        for (var i = 0; i < list.length; i++) {
                            if (inItems(list[i], this.items)) {
                                list.splice(i, 1);
                                i--;
                            }
                        }
                        this.database = list;
                        console.log("show database");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Labour",
                    {
                        name: item.name,
                        price: item.price,
                        people: item.people,
                        months: item.months,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        },
        created: function () {
        }
    });


    var consultationVue = new Vue({
        el: "#consultation",
        data: {
            items: [],
            sample: {name: "sample", price: 0, nums: 0},
            database: [{name: "sample", price: 0, nums: 0}],
            selectedIndex: 0
        },
        methods: {
            update: function (item) {
                this.doUpdate(item, 2);
            },
            del: function (item) {
                this.doUpdate(item, 1);
            },
            add: function (item) {
                this.doUpdate(item, 0);
            },
            showlist: function () {
                this.$http.get("${pageContext.request.contextPath}/Budget/Detail/Consultation").then(
                    function (data) {
                        this.items = data.body.data;
                        console.log("showlist");
                        updateBudgetPage("consultation");
                        this.showDatabase();
                    }, function (error) {
                        console.log(error)
                    }
                );
            },
            showDatabase: function () {
                this.$http.get("${pageContext.request.contextPath}/Database/Query/Consultation").then(
                    function (data) {
                        var list = data.body.data;
                        console.log(this.items);
                        for (var i = 0; i < list.length; i++) {
                            if (inItems(list[i], this.items)) {
                                console.log("deleted: " + list[i].name);
                                list.splice(i, 1);
                                i--;
                            }
                        }
                        this.database = list;
                        console.log("show database");
                    }, function (error) {
                        console.log(error)
                    }
                )
            },
            doUpdate: function (item, curd) {
                this.$http.post("${pageContext.request.contextPath}/Budget/Modify/Consultation",
                    {
                        name: item.name,
                        price: item.price,
                        nums: item.nums,
                        curd: curd
                    },
                    {emulateJSON: true}
                ).then(function (value) {
                    this.showlist();
                });
            }
        },
        created: function () {
        }
    });


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
                sidenavVue.budget=data;
                var over = "预算超出：";
                var under = "预算不足，再来";
                var equal = "凑够啦！";
                var colors = {
                    over: "red",
                    under: "blue",
                    equal: "green"
                };
                document.getElementById("total-req").innerHTML = data.req;
                document.getElementById("total-sum").innerHTML = data.sum;
                document.getElementById("total-diff").innerHTML = abs(data.diff);
                if (data.diff < 0) {
                    document.getElementById("total-diff").style.color = colors.over;
                    document.getElementById("total-hint").innerHTML = over;
                }
                else if (data.diff > 0) {
                    document.getElementById("total-diff").style.color = colors.under;
                    document.getElementById("total-hint").innerHTML = under;
                }
                else {
                    document.getElementById("total-diff").style.color = colors.equal;
                    document.getElementById("total-hint").innerHTML = equal;
                }

                document.getElementById("this-req").innerHTML = data[type].req;
                document.getElementById("this-sum").innerHTML = data[type].sum;
                document.getElementById("this-diff").innerHTML = abs(data[type].diff);
                if (data[type].diff < 0) {
                    document.getElementById("this-hint").innerHTML = over
                    document.getElementById("this-diff").style.color = colors.over
                }
                else if (data[type].diff > 0) {
                    document.getElementById("this-hint").innerHTML = under
                    document.getElementById("this-diff").style.color = colors.under;
                }
                else {
                    document.getElementById("this-diff").style.color = colors.equal;
                    document.getElementById("this-hint").innerHTML = equal;
                }
                //alert("updateBudgetPage");
                //location.reload();
            }
        })
    }

    function abs(number) {
        if (number < 0) return -number;
        return number;
    }


</script>
