<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%--<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">--%>
<%--    <a href="measurements" class="navbar-brand"> <spring:message code="app.title"/></a>--%>

<%--    <div class="collapse navbar-collapse" id="navbarCollapse">--%>

<%--        <form class="form-inline my-2">--%>
<%--            <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>--%>
<%--            <a class="btn btn-primary" href="">--%>
<%--                <span class="fa fa-sign-in"></span>--%>
<%--            </a>--%>
<%--        </form>--%>
<%--    </div>--%>


<%--</nav>--%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                         fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-home">
                        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                        <polyline points="9 22 9 12 15 12 15 22"></polyline>
                    </svg>
                    <spring:message code="app.home"/> <span class="sr-only">(current)</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="measurements">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                         fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-bar-chart-2">
                        <line x1="18" y1="20" x2="18" y2="10"></line>
                        <line x1="12" y1="20" x2="12" y2="4"></line>
                        <line x1="6" y1="20" x2="6" y2="14"></line>
                    </svg>
                    <spring:message code="common.reports"/>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="subscriptions">
                    <svg viewBox="0 0 24 24" width="24" height="24" stroke="currentColor" stroke-width="2" fill="none"
                         stroke-linecap="round" stroke-linejoin="round" class="feather css-i6dzq1">
                        <circle cx="11" cy="11" r="8"></circle>
                        <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                    </svg>
                    <spring:message code="common.subscriptions"/>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="users">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                         fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-users">
                        <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                        <circle cx="9" cy="7" r="4"></circle>
                        <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                        <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                    </svg>
                    <spring:message code="user.title"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                         fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-random">
                        <path d="M10 22H5a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h5"/>
                        <polyline points="17 16 21 12 17 8"/>
                        <line x1="21" y1="12" x2="9" y2="12"/>
                    </svg>
                    <spring:message code="common.logout"/>
                </a>
            </li>
        </ul>

    </div>
</nav>