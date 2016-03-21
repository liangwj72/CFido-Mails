<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<!-- InstanceBegin template="/Templates/common.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<!-- InstanceBeginEditable name="ex-title" -->
<title>CFido 邮件快照 - 所有信区</title>
<%@include file="includes/_css.jsp" %>
<!-- <#setting url_escaping_charset="UTF-8"> -->
<!-- InstanceBeginEditable name="ex-sytle" -->
<!-- InstanceEndEditable -->
</head>
<body>
<!-- NAV BAR BEGIN-->
<div class="page-header"> 
  <!-- BEGIN HEADER MENU -->
  <div class="page-header-menu " style="display: block;">
    <div class="container-fluid">
      <div class="hor-menu">
        <ul class="nav navbar-nav">
			<%@include file="includes/_topMenu.jsp" %>
        </ul>
      </div>
    </div>
  </div>
  <!-- END HEADER MENU --> 
</div>
<!-- NAV BAR END-->
<div class="page-container">
  <div class="page-head">
    <div class="container-fluid"> 
      <!-- BEGIN PAGE TITLE -->
      <div class="page-title"> <!-- InstanceBeginEditable name="pageTitle" --> 所有信区 <!-- InstanceEndEditable --> </div>
      <!-- END PAGE TITLE --> 
    </div>
  </div>
  <div class="page-content">
    <div class="container-fluid"> <!-- InstanceBeginEditable name="pre-body" --> 
      <!-- InstanceEndEditable -->
      <div class="row">
        <div class="col-md-12"> <!-- InstanceBeginEditable name="body" -->
          <div class="portlet light">
            <div class="portlet-body">
              <div class="area-list">
                <ul class="nav nav-pills">
                  	<c:forEach items="${areaList}" var="vo">
                  <li>
                  	<a href="/area/${vo.encodeId}">
                  	<span class="badge badge-success"> ${vo.po.num} </span> ${vo.po.name} 
                    <div class="desc">${vo.desc}</div>
                    </a>
                  </li>
                  	</c:forEach>
                </ul>
              </div>
            </div>
          </div>
          <!-- InstanceEndEditable --> </div>
      </div>
    </div>
  </div>
</div>

<%@include file="includes/_foot.jsp" %>

</body>
<!-- InstanceEnd -->
</html>
