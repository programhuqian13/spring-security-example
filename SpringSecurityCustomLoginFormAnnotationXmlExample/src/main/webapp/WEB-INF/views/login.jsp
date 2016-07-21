<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/css/app.css'/>"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/font-awesome'/>"> 
</head>
<body>
	<div id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<c:url var="loginUrl" value="/login"></c:url>
					<form action="${loginUrl}" method="post" class="form-horizontal">
						<c:if test="${param.error != null }">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null }">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						
						<div class="input-group input-sm">
							<lable class="input-group-addon" for="username">
								<i class="fa fa-user"></i>
							</lable>
							<input type="text" class="form-control" id="username" name="ssoId" required="required" placeholder="Enter Username"/>
						</div>
						
						<div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                        </div>
                        
                        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
                        
                        <div class="form-actions">
                        	<input type="submit" class="btn btn-block btn-primary btn-default" value="Log In">
                        </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>