<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!doctype html>
<html><!-- InstanceBegin template="/Templates/common.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8">
<title>JConsole In Web</title>
<link rel="stylesheet" type="text/css" href="statics/bootstrap-3.3.5/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="statics/css/components-rounded.css">
<link rel="stylesheet" type="text/css" href="statics/bootstrap-toastr/toastr.min.css">
<link rel="stylesheet" type="text/css" href="statics/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="statics/css/default.css">
<link rel="stylesheet" type="text/css" href="statics/css/layout.css">
<link rel="stylesheet" type="text/css" href="statics/jmxInWeb/jw.common.css">
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
          <li class="active"><a href="/">MBean</a></li>
          <li><a href="#">内存</a></li>
          <li><a href="#">线程</a></li>
          <li><a href="#">VM概要</a></li>
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
      <div class="page-title">
		<!-- InstanceBeginEditable name="pageTitle" --> Page Title <!-- InstanceEndEditable -->
      </div>
      <!-- END PAGE TITLE --> 
    </div>
  </div>
  <div class="page-content">
    <div class="container-fluid">
	  <!-- InstanceBeginEditable name="pre-body" -->
      正文之上的代码
      <!-- InstanceEndEditable -->
      <div class="row">
        <div class="col-md-12">
          <!-- InstanceBeginEditable name="body" -->
          <div class="portlet light">
            <div class="portlet-title">
              <div class="caption"> <i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold ">JMImplementation</span> </div>
            </div>
            <div class="portlet-body">
              <div class="table">
                <table class="table table-light table-hover">
                  <thead>
                    <tr>
                      <th width="33.3%">ObjectName</th>
                      <th width="33.3%">Class</th>
                      <th width="33.3%">说明</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td><a href="s.mbeanInfo.html">JMImplementation:type=MBeanServerDelegate</a></td>
                      <td>javax.management.MBeanServerDelegate</td>
                      <td>Represents  the MBean server from the management point of view.</td>
                    </tr>
                    <tr>
                      <td>com.kunmingCoder.jcweb:name=MyTestMBean</td>
                      <td>com.kunmingCoder.jcweb.MyTestMBean</td>
                      <td>测试</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          
          <div class="portlet light">
            <div class="portlet-title">
              <div class="caption"> <i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold ">JMImplementation</span> </div>
            </div>
            <div class="portlet-body">
              <div class="table">
                <table class="table table-light table-hover">
                  <thead>
                    <tr>
                      <th width="33.3%">ObjectName</th>
                      <th width="33.3%">Class</th>
                      <th width="33.3%">说明</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>JMImplementation:type=MBeanServerDelegate</td>
                      <td>javax.management.MBeanServerDelegate</td>
                      <td>Represents  the MBean server from the management point of view.</td>
                    </tr>
                    <tr>
                      <td>com.kunmingCoder.jcweb:name=MyTestMBean</td>
                      <td>com.kunmingCoder.jcweb.MyTestMBean</td>
                      <td>测试</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <!-- InstanceEndEditable -->
        </div>
      </div>
    </div>
  </div>
</div>

<!-- PRE-FOOTER BEGIN -->
 <div class="page-prefooter">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3 col-sm-6 col-xs-12 footer-block">
				<h2>About</h2>
				<p>
					 在web界面上操作jmx.
				</p>
			</div>
			<div class="col-md-3 col-sm-6 col-xs12 footer-block">
				<h2>Documents</h2>
                <a href="#" >......</a>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12 footer-block">
				<h2>Contacts</h2>
				<address class="margin-bottom-40">
				 Email: <a href="mailto:liangwj@tom.com">liangwj@tom.com</a>
				</address>
			</div>
		</div>
	</div>
</div>
<!-- PRE-FOOTER END -->

<!-- FOOTER BEGIN -->
<div class="page-footer">
	<div class="container-fluid">
		 2015 
	</div>
</div>
<!-- FOOTER END -->

<div class="scroll-to-top" style="display: block;">
	<i class="fa fa-arrow-circle-up"></i>
</div>

<!-- InstanceBeginEditable name="body-end" -->
<!-- InstanceEndEditable -->

<!-- COMMON JAVASCRIPT BEGIN -->
<script src="statics/jquery.min.js"></script>
<script src="statics/bootstrap-3.3.5/js/bootstrap.js"></script>
<script src="statics/jquery.flot/jquery.flot.js"></script>
<script src="statics/bootstrap-toastr/toastr.min.js"></script>
<script src="statics/jmxInWeb/jw.common.js"></script>
<!-- COMMON JAVASCRIPT END -->

<!-- InstanceBeginEditable name="ex-script" -->
<!-- InstanceEndEditable -->

</body>
<!-- InstanceEnd --></html>
