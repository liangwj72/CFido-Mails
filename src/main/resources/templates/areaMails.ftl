<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title><c:out value="${areaVo.po.name}"/> - CFido 邮件快照</title>
<#include "includes/_css.ftl"/>
</head>

<body>
	<#include "includes/_topMenu.ftl"/>

	<div class="page-container">
		<div class="page-head">
			<div class="container-fluid">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					当前信区: ${areaVo.po.name}
				</div>
			</div>
		</div>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<#include "includes/_mailList.ftl"/>
					</div>
				</div>
			</div>
		</div>
	</div>

	<#include "includes/_foot.ftl"/>

</body>
</html>
