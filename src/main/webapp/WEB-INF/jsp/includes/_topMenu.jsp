<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:forEach items="${menuList}" var="menu">
	<li ${menu.classStr}><a href="${menu.href}">${menu.name}</a></li>
</c:forEach>
