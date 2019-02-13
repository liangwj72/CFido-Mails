<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Mails</title>
<#include "includes/_css.ftl"/>

</head>
<body>
	<div class="read_mail">
		<div class="header">
			<div>
				<h4>
					<i class="fa fa-envelope-o"></i> ${mail.po.subj }
				</h4>
			</div>

		</div>
		<div class="body">
			<ul>
				<li>From: <span class="desc">${mail.po.mailFrom}</span>
				</li>
				<li>To: <span class="desc">${mail.po.mailTo}</span>
				</li>
				<li>时间: <span class="desc">${(mail.po.createDate?string("yyyy-MM-dd
						HH:mm"))!}</span>
				</li>
				<li>信区: ${mail.po.area}</li>
				<#if mail.hasOriginMail>
				<li>原帖: <a href="/mail/${mail.origin.po.id }"
					class="js_viewMail">${mail.origin.subject}</a>
				</li>
				</#if>
			</ul>
			<hr />
			${mail.html}
			<hr />
			<#if mail.hasReply> 其他跟帖：

			<table
				class="table table-striped table-bordered table-hover dataTable no-footer"
				role="grid" aria-describedby="sample_1_info">
				<thead>
					<tr role="row">
						<th>标题</th>
						<th>发件人</th>
						<th>时间</th>
						<th>长度</th>
					</tr>
				</thead>
				<tbody>
					<#list mail.replyList as vo>
					<tr role="row">
						<td><a href="/mail/${vo.po.id }" class="js_viewMail">${vo.subject}</a>
							<#if vo.po.replays>0 > <span
								class="label label-sm label-info">${vo.po.replays}</span> </#if></td>
						<td>${vo.po.mailFrom} <i class="fa fa-long-arrow-right"></i>
							${vo.po.mailTo}
						</td>
						<td>${(vo.po.createDate?string("yyyy-MM-dd HH:mm"))!}</td>
						<td>${vo.po.msgLen}</td>
					</tr>
					</#list>
				</tbody>
			</table>

			</#if>

			<hr />
			<!-- UY BEGIN -->
			<div id="uyan_frame"></div>
			<script type="text/javascript"
				src="http://v2.uyan.cc/code/uyan.js?uid=2090355"></script>
			<!-- UY END -->
		</div>
	</div>
</body>
<!-- InstanceEnd -->
</html>
