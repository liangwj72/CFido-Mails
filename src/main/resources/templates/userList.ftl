<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>CFido 邮件快照 - 用户</title>
<#include "includes/_css.ftl"/>
</head>

<body>
	<#include "includes/_topMenu.ftl"/>

	<div class="page-container">
		<div class="page-head">
			<div class="container-fluid">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					发帖狂人榜
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
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
																placeholder="请输入用户名.." name="username" value='${username}'> <span
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
					<#list pageVo.list as vo>
													<li><a href="/user/${vo.po.id}"> <span class="pos"> ${vo.rank }
														</span>${vo.po.userName}<span class="badge badge-default"> ${vo.po.mailNum} </span>
													</a></li>
					</#list>
												</ul>
											</div>
											
		<#include "includes/_pageBar.ftl"/>
											
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

	<#include "includes/_foot.ftl"/>

</body>
<!-- InstanceEnd -->
</html>
