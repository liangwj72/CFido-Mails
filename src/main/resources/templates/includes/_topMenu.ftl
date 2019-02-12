<!-- NAV BAR BEGIN-->
<div class="page-header">
	<!-- BEGIN HEADER MENU -->
	<div class="page-header-menu " style="display: block;">
		<div class="container-fluid">
			<div class="hor-menu">
				<ul class="nav navbar-nav">
					<#list menuList as menu>
						<li ${menu.classStr}><a href="${menu.href}">${menu.name}</a></li>
					</#list>
				</ul>
			</div>
		</div>
	</div>
	<!-- END HEADER MENU -->
</div>
<!-- NAV BAR END-->

