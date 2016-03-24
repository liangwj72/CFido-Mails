<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${userVo.po.userName} - CFido 邮件快照 </title>
<%@include file="includes/_css.jsp"%>
</head>

<body>
	<%@include file="includes/_topMenu.jsp"%>

	<div class="page-container">
		<div class="page-head">
			<div class="container-fluid">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<!-- InstanceBeginEditable name="pageTitle" -->
					发件人: <strong>${userVo.po.userName}</strong> 
					总发帖数: <span class="label label-default">${userVo.po.mailNum }</span> 
					发帖排名: <span class="label label-info">${userVo.rank }</span>
					<!-- InstanceEndEditable -->
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<!-- InstanceBeginEditable name="body" -->
						<div id="viewMailDiv" style="display: none;">
							<div class="header">
								<a class="btn btn-xs btn-info" id="id_viewMailClose"><i
									class="fa fa-times"></i> 关闭</a>
							</div>
							<iframe name="viewMailFrame" src=""></iframe>
						</div>

						<%@include file="includes/_mailList.jsp"%>

					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="includes/_foot.jsp"%>

</body>
</html>
