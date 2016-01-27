<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-fixed-top" ng-controller="HeaderController">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/"/>"><spring:message code='header.message' /></a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">

			<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li>
							<a href="<c:url value="/management/products"/>"
							   title='<spring:message code="header.product_management"/>'>
								<p>
									<i class="fa fa-cogs"></i>&nbsp;<spring:message code="header.product_management" />
								</p>
							</a>
						</li>
						<li>
							<a href="<c:url value="/management/reports"/>"
							   title='<spring:message code="header.reports"/>'>
								<p>
									<i class="fa fa-area-chart"></i>&nbsp;<spring:message code="header.reports" />
								</p>
							</a>
						</li>
			</sec:authorize>


			</ul>
			<ul class="nav navbar-nav pull-right">
				<li>
					<a href="<c:url value="/shoppingcart"/>" style="text-decoration: none;" class="text-muted input-md"><i class="fa fa-shopping-basket"></i>&nbsp; Sepetiniz ( {{itemcount}} ürün: {{shoppingcart.totalPrice - shoppingcart.discountPrice}} TL )</a>
				</li>
				<c:choose>
					<c:when test="${user == null}">
						<li>
							<a href="<c:url value='/login' />"
							   title='<spring:message code="header.login"/>'>
								<i class="fa fa-sign-in"></i>&nbsp;<spring:message code="header.login" />
							</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#"><span><i class="fa fa-user"></i>&nbsp;${user.name}</span></a> </li>
					</c:otherwise>
				</c:choose>
				<c:if test="${user != null}">
					<li>
						<a href="<c:url value='/logout' />"
						   title='<spring:message code="header.logout"/>'>
							<i class="fa fa-sign-out"></i>
						</a>
					</li>
				</c:if>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>




