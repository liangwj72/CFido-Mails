<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Mails</title>
<%@include file="includes/_css.jsp"%>

</head>
<body>
	<div class="read_mail">
		<div class="header">
			<div>
				<h4>
					<i class="fa fa-envelope-o"></i>
					<c:out value="${mail.po.subj }" />
				</h4>
			</div>

		</div>
		<div class="body">
			<ul>
				<li>From: <span class="desc"><c:out
							value="${mail.po.mailFrom}" /></span>
				</li>
				<li>To: <span class="desc"><c:out
							value="${mail.po.mailTo}" /></span>
				</li>
				<li>时间: <span class="desc"><fmt:formatDate
							value="${mail.po.createDate}" pattern="yyyy-MM-dd HH:mm" /></span>
				</li>
			</ul>
			<hr />
			${mail.html}
			<hr />
			<c:if test="${mail.hasReply}">
			其他跟帖：

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
						<c:forEach items="${mail.replyList}" var="vo">
							<tr role="row">
								<td><a href="/mail/${vo.po.id }" class="js_viewMail"><c:out
											value="${vo.subject }" /></a> <c:if test="${vo.po.replays>0}">
										<span class="label label-sm label-info">${vo.po.replays}</span>
									</c:if></td>
								<td><c:out value="${vo.po.mailFrom}" /> <i
									class="fa fa-long-arrow-right"></i> <c:out
										value="${vo.po.mailTo}" /></td>
								<td><fmt:formatDate value="${vo.po.createDate}"
										pattern="yy-MM-dd HH:mm" /></td>
								<td><c:out value="" />${vo.po.msgLen}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</c:if>

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
