<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>CFido 邮件快照 - 用户</title>
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
					发帖狂人榜
					<!-- InstanceEndEditable -->
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<div class="page-content">
			<div class="container-fluid">

				<!-- InstanceBeginEditable name="pre-body" -->
				<!-- InstanceEndEditable -->

				<div class="row">
					<div class="col-md-12">
						<!-- InstanceBeginEditable name="body" -->
						<div class="portlet-body">
							<div class="row">

								<div class="col-md-6 col-sm-12">
									<div class="portlet light">
										<div class="portlet-title">
											<div class="row">
												<div class="col-md-6 col-sm-12">
													<form class="search-form open" action="/users?reset=1" method="GET">
														<div class="input-group">
															<input type="text" class="form-control "
																placeholder="请输入用户名.." name="username" value='<c:out value="${username}" />'> <span
																class="input-group-btn">
																<button type="submit" class="btn submit">
																	<i class="fa fa-search"></i>
																</button>
															</span>
														</div>
													</form>
												</div>
											</div>
										</div>
										<div class="portlet-body">
											<div class="area-list user">
												<ul class="nav nav-pills">
					<c:forEach items="${pageVo.list}" var="vo">
													<li><a href="/user/${vo.po.id}"> <span class="pos"> ${vo.rank }
														</span> <c:out value="${vo.po.userName }"/> <span class="badge badge-default"> ${vo.po.mailNum} </span>
													</a></li>
					</c:forEach>
												</ul>
											</div>
											
		<%@include file="includes/_pageBar.jsp"%>
											
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- InstanceEndEditable -->
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="includes/_foot.jsp"%>

</body>
<!-- InstanceEnd -->
</html>
