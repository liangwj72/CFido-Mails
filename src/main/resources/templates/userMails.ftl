<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${userVo.po.userName} - CFido 邮件快照 </title>
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

						<#include "includes/_mailList.ftl"/>

					</div>
				</div>
			</div>
		</div>
	</div>

	<#include "includes/_foot.ftl"/>

</body>
</html>
