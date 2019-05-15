<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/5/15
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预算不存在</title>
</head>
<body>
<div>
    <h1 class="center-block">预算文件不存在，3秒钟后跳转到首页</h1>
</div>
<script language=javascript>
    setTimeout('window.location="${pageContext.request.contextPath}/"',3000)
</script>
</body>
</html>
