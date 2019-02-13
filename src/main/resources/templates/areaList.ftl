<!doctype html>
<html>
<!-- InstanceBegin template="/Templates/common.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<title>所有信区 - CFido 邮件快照</title>
<#include "includes/_css.ftl"/>
</head>
<body>
	<#include "includes/_topMenu.ftl"/>

	<div class="page-container">
		<div class="page-head">
			<div class="container-fluid">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<!-- InstanceBeginEditable name="pageTitle" -->
					所有信区
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
						<div class="portlet light">
							<div class="portlet-body">
								<div class="area-list">
									<ul class="nav nav-pills">
										<#list areaList as vo>
											<li><a href="/area/${vo.po.id}"> <span
													class="badge badge-success"> ${vo.po.num} </span>
													${vo.po.name}
													<span class="desc">${vo.desc}</span>
											</a></li>
										</#list>
									</ul>
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
