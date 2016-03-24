<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<c:forEach items="${pageVo.list}" var="vo">
						<tr role="row">
							<td><a href="/mail/${vo.po.id }" target="viewMailFrame"
								class="js_viewMail"><c:out value="${vo.subject }" /></a> <c:if
									test="${vo.po.replays>0}">
									<span class="label label-sm label-primary" title="回复数">${vo.po.replays}</span>
								</c:if></td>
							<td><c:out value="${vo.po.mailFrom}" /> <i
								class="fa fa-long-arrow-right"></i> <c:out
									value="${vo.po.mailTo}" /></td>
							<td><c:out value="${vo.po.area}" /></td>
							<td><fmt:formatDate value="${vo.po.createDate}"
									pattern="yyyy-MM-dd HH:mm" /></td>
							<td>${vo.po.msgLen}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<%@include file="_pageBar.jsp"%>

	</div>
</div>

<!-- /mail list -->

