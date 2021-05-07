<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<%--<jsp:include page="fragments/headTag.jsp"/>--%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

<%--    <title>Signin Template for Bootstrap</title>--%>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="webjars/bootstrap/4.6.0-1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="resources/css/signin.css">
</head>

<body class="text-center">
<form class="form-signin" method="post" action="users">
    <h1 class="h3 mb-3 font-weight-normal">Login</h1>
<%--    <form method="post" action="users">--%>
    Login: <select name="userId">
        <option value="100000" selected>User</option>
        <option value="100001">Admin</option>
    </select>
<%--        <button type="submit"><spring:message code="common.select"/></button>--%>
<%--    </form>--%>

<%--    <label for="inputEmail" class="sr-only">Email address</label>--%>
<%--    <input type="email" id="inputEmail" class="form-control" placeholder=<spring:message code="user.email"/> required="" autofocus="">--%>
<%--    <label for="inputPassword" class="sr-only">Password</label>--%>
<%--    <input type="password" id="inputPassword" class="form-control" placeholder=<spring:message code="user.password"/> required="">--%>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="common.select"/></button>
</form>
</body>
</html>


<%--<html>--%>
<%--<jsp:include page="fragments/headTag.jsp"/>--%>
<%--<body>--%>

<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <jsp:include page="fragments/bodyHeader.jsp"/>--%>
<%--        <section>--%>
<%--            <jsp:include page="fragments/bodyHeader.jsp"/>--%>
<%--            <form method="post" action="users">--%>
<%--                <spring:message code="app.login"/>: <select name="userId">--%>
<%--                <option value="100000" selected>User</option>--%>
<%--                <option value="100001">Admin</option>--%>
<%--            </select>--%>
<%--                <button type="submit"><spring:message code="common.select"/></button>--%>
<%--            </form>--%>
<%--        </section>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
<%--</body>--%>
<%--</html>--%>