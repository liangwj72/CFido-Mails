<!-- mail list -->

<div id="viewMailDiv" style="display: none;">
	<div class="container">
		<div class="header">
			<a class="btn btn-xs btn-info" id="id_viewMailClose"><i
				class="fa fa-times"></i> 关闭</a>
		</div>
		<iframe name="viewMailFrame" src=""></iframe>
	</div>
</div>

<div class="portlet light">
	<div class="portlet-body">

		<div class="table-scrollable">
			<table
				class="table table-striped table-bordered table-hover dataTable no-footer"
				role="grid" aria-describedby="sample_1_info">
				<thead>
					<tr role="row">
						<th>标题</th>
						<th>发件人</th>
						<th>信区</th>
						<th>时间</th>
						<th>长度</th>
					</tr>
				</thead>
				<tbody>
					<#list pageVo.list as vo>
						<tr role="row">
							<td><a href="/mail/${vo.po.id}" target="viewMailFrame"
								class="js_viewMail">${vo.subject}</a><#if vo.po.replays>
									<span class="label label-sm label-primary" title="回复数">${vo.po.replays}</span>
								</#if></td>
							<td>${vo.po.mailFrom}<i
								class="fa fa-long-arrow-right"></i>${vo.po.mailTo}</td>
							<td>${vo.po.area}</td>
							<td>${(vo.po.createDate?string("yyyy-MM-dd HH:mm"))!}</td>
							<td>${vo.po.msgLen}</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>

		<#include "includes/_pageBar.ftl"/>

	</div>
</div>

<!-- /mail list -->

