<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://myProgress/functions" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2><fmt:message key="measurements.title"/></h2>
    <form method="get" action="measurements">
        <input type="hidden" name="action" value="filter">
        <dl>
            <dt>From Date (inclusive):</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date (inclusive):</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>

        <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="measurements?action=create"><fmt:message key="measurements.add"/></a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="measurements.date"/></th>
            <th><fmt:message key="measurements.weight"/></th>
            <th><fmt:message key="measurements.waist"/></th>
            <th><fmt:message key="measurements.hips"/></th>
            <th><fmt:message key="measurements.shoulders"/></th>
            <th><fmt:message key="measurements.quad"/></th>
            <th><fmt:message key="measurements.bicep"/></th>
            <th><fmt:message key="measurements.avgCalories"/></th>
            <th><fmt:message key="measurements.trainingCount"/></th>
            <th><fmt:message key="measurements.avgSteps"/></th>
        </tr>
        </thead>
        <c:forEach items="${measurements}" var="measurement">
            <jsp:useBean id="measurement" type="myProgress.to.MeasurementTo"/>
            <tr>
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDate(measurement.date)}
                </td>
                <td>${measurement.weight}</td>
                <td>${measurement.waist}</td>
                <td>${measurement.hips}</td>
                <td>${measurement.shoulders}</td>
                <td>${measurement.quad}</td>
                <td>${measurement.bicep}</td>
                <td>${measurement.avgCalories}</td>
                <td>${measurement.trainingCount}</td>
                <td>${measurement.avgSteps}</td>
                <td><a href="measurements?action=update&id=${measurement.id}">Update</a></td>
                <td><a href="measurements?action=delete&id=${measurement.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>