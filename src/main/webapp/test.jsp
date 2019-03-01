<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2019/1/28
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>测试页面</title>
</head>
<body>
<a href="Crawler/Workstation">爬取工作站数据</a><br>
<a href="Budget/Download">下载</a><br>
${pageContext.request.contextPath}<br>
<%
    for (int i = 1; i <= 3; i++) {%>
        <font size="<%=i%>"> hhhh</font>
 <%   }
%>
</body>
</html>
