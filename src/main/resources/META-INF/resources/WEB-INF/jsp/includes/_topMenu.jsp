<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- NAV BAR BEGIN-->
<div class="page-header">
	<!-- BEGIN HEADER MENU -->
	<div class="page-header-menu " style="display: block;">
		<div class="container-fluid">
			<div class="hor-menu">
				<ul class="nav navbar-nav">
					<c:forEach items="${menuList}" var="menu">
						<li ${menu.classStr}><a href="${menu.href}">${menu.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!-- END HEADER MENU -->
</div>
<!-- NAV BAR END-->

