<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- pageBar -->
<div class="row">
	<div class="dataTables_paginate pull-left">
		<ul class="pagination">
			<li class="prev ${pageVo.prev.classStr}"><a href="${pageVo.actionUrl}/${pageVo.prev.pageNo}"><i
					class="fa fa-angle-left"></i></a></li>
<c:forEach items="${pageVo.pageList}" var="page">
			<li ${page.classStr }><a href="${pageVo.actionUrl}/${page.pageNo}">${page.pageNo}</a></li>
</c:forEach>			
			<li class="next ${pageVo.next.classStr }"><a href="${pageVo.actionUrl}/${pageVo.next.pageNo}"><i
					class="fa fa-angle-right"></i></a></li>
		</ul>
	</div>
	<div class="dataTables_info pull-right">共 ${pageVo.pageTotal} 页</div>
	<div class="input-group input-small pull-right dataTables_input">
		<input type="number" class="form-control" value="${pageVo.pageNo}" id="id_pageBar_pageNo"> <span
			class="input-group-btn">
			<button class="btn default" type="button" data-action="${pageVo.actionUrl}" data-limit="${pageVo.pageTotal}" id="id_pageBar_go" >Go!</button>
		</span>
	</div>
</div>
<!-- /pageBar -->
