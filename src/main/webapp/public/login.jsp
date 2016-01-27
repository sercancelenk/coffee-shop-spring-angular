<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row-fluid">
	<div class="jumbotron alert-warning">
		<h3 class="text-center">
			<span class="text text-warning"><spring:message
					code='project.name' /></span>
		</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-4 col-md-offset-4 well" ng-controller="loginController">
		<h4 class="text-center">
			<span class="text text-info"><spring:message
					code="login.header" /></span>
		</h4>
		<div class="alert alert-error"
			ng-class="{'': displayLoginError == true, 'none': displayLoginError == false}">
			<span class="text-danger"><i class="fa fa-warning"></i>&nbsp;<spring:message code="login.error" /></span>
		</div>
		<form class="form-signin" method="post" action="j_spring_security_check">
			<label for="j_username" class="sr-only"><spring:message code='sample.email' /></label>
			<input type="email" id="j_username" name="j_username" class="form-control" placeholder="<spring:message code='sample.email' />" required="" autofocus="">
			<label for="j_password" class="sr-only">Password</label>
			<input type="password" id="j_password" name="j_password" class="form-control" placeholder="Password" required="">
			<br/>
			<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.signIn" /></button>
		</form>

	</div>
</div>
