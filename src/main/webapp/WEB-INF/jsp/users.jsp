<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="fragments/bodyHeader.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="chartjs-size-monitor"
                 style="position: absolute; inset: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                <div class="chartjs-size-monitor-expand"
                     style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0">
                    </div>
                </div>
                <div class="chartjs-size-monitor-shrink"
                     style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:200%;height:200%;left:0; top:0">
                    </div>
                </div>
            </div>
            <section>
                <h3><spring:message code="user.title"/></h3>
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th><spring:message code="user.name"/></th>
                        <th><spring:message code="user.email"/></th>
                        <th><spring:message code="user.roles"/></th>
                        <th><spring:message code="user.active"/></th>
                        <th><spring:message code="user.registered"/></th>
                    </tr>
                    </thead>
                    <c:forEach items="${users}" var="user">
                        <jsp:useBean id="user" scope="page" type="myProgress.model.User"/>
                        <tr>
                            <td><c:out value="${user.name}"/></td>
                            <td><a href="mailto:${user.email}">${user.email}</a></td>
                            <td>${user.roles}</td>
                            <td><%=user.isEnabled()%>
                            </td>
                            <td><fmt:formatDate value="${user.registered}" pattern="dd-MM-yyyy"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </main>
    </div>
</div>
</body>
</html>