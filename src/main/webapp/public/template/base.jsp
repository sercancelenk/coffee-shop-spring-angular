<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<title><spring:message code="project.title" /></title>
<link href="<c:url value='/resources/js/bower/bootstrap/dist/css/bootstrap.min.css'  />"
	rel="stylesheet" />
<link href="<c:url value='/resources/css/project_style.css'  />"
	rel="stylesheet" />

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	<script src="<c:url value='/resources/js/jquery-1.9.1.min.js' />"></script>

	<!--[if IE]>
	<script src="<c:url value='/resources/js/bootstrap.min.ie.js' />"></script>
	<![endif]-->
	<!--[if !IE]><!-->
	<script src="<c:url value='/resources/js/bower/bootstrap/dist/js/bootstrap.min.js' />"></script>
	<!--<![endif]-->

	<script src="<c:url value='/resources/js/bower/angular/angular.min.js' />"></script>
	<link rel="stylesheet" href="<c:url value="/resources/js/bower/ngToast/dist/ngToast.min.css"/>">

	<script src="<c:url value="/resources/js/bower/angular-animate/angular-animate.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bower/angular-sanitize/angular-sanitize.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bower/ngToast/dist/ngToast.min.js"/>"></script>

	<script src="<c:url value='/resources/js/app/app.js' />"></script>
	<script src="<c:url value='/resources/js/app/service/services.js' />"></script>

	<script src="<c:url value='/resources/js/app/controller/header.js' />"></script>

	<!-- Onemli: Yukaridaki script dosyalari performans icin body icerisine yazilabilir -->

</head>
<body ng-app="app">
	<toast></toast>
	<div class="container">
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>