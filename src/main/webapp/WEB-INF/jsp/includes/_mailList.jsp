<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- mail list -->
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
						<th>收件人</th>
						<th>时间</th>
						<th>回复</th>
						<th>长度</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageVo.list}" var="vo">
						<tr role="row">
							<td><a href="/mail/${vo.po.id }" target="viewMailFrame"
								class="js_viewMail"><c:out value="${vo.subject }" /></a></td>
							<td><c:out value="${vo.po.mailFrom}" /></td>
							<td><c:out value="${vo.po.mailTo}" /></td>
							<td><fmt:formatDate value="${vo.po.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><c:out value="" />
								<c:if test="${vo.po.replays>0}">${vo.po.replays}</c:if></td>
							<td><c:out value="" />${vo.po.msgLen}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<%@include file="_pageBar.jsp"%>

	</div>
</div>

<!-- /mail list -->

