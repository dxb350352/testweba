<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>article list</title>
</head>
<body>
  <c:forEach items="${list}" var="item">
    ${item.id }--${item.title }--${item.content }<br />
  </c:forEach>
  <br/>
  <a href="/user/pageList?pageNum=${page.prePage}">上一页</a>
 <a href="/user/pageList?pageNum=${page.nextPage}">下一页</a>
</body>
</html>
