<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://myProgress/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="measurements.title"/></h3>
  
    <form method="get" action="measurements/filter">
        <dl>
            <dt><spring:message code="measurements.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>

        <button type="submit"><spring:message code="measurements.filter"/></button>
    </form>
    <hr/>
    <a href="measurements/create"><spring:message code="measurements.add"/></a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="measurements.date"/></th>
            <th><spring:message code="measurements.weight"/></th>
            <th><spring:message code="measurements.waist"/></th>
            <th><spring:message code="measurements.hips"/></th>
            <th><spring:message code="measurements.shoulders"/></th>
            <th><spring:message code="measurements.quad"/></th>
            <th><spring:message code="measurements.bicep"/></th>
            <th><spring:message code="measurements.avgCalories"/></th>
            <th><spring:message code="measurements.trainingCount"/></th>
            <th><spring:message code="measurements.avgSteps"/></th>
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
                <td><a href="measurements/update?id=${measurement.id}"><spring:message code="common.update"/></a></td>
                <td><a href="measurements/delete?id=${measurement.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>