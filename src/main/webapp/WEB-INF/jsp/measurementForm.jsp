<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="measurement" type="myProgress.model.Measurement" scope="request"/>
    <h3><spring:message code="${measurement.isNew() ? 'measurements.add' : 'measurements.edit'}"/></h3>
    <hr>
    <form method="post" action="measurements">
        <input type="hidden" name="id" value="${measurement.id}">
        <dl>
            <dt><spring:message code="measurements.date"/>:</dt>
            <dd><input type="date" value="${measurement.date}" name="date" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.weight"/>:</dt>
            <dd><input type="text" value="${measurement.weight}" size=40 name="weight" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.waist"/>:</dt>
            <dd><input type="text" value="${measurement.waist}" size=40 name="waist" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.hips"/>:</dt>
            <dd><input type="text" value="${measurement.hips}" size=40 name="hips" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.shoulders"/>:</dt>
            <dd><input type="text" value="${measurement.shoulders}" size=40 name="shoulders"></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.quad"/>:</dt>
            <dd><input type="text" value="${measurement.quad}" size=40 name="quad"></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.bicep"/>:</dt>
            <dd><input type="text" value="${measurement.bicep}" size=40 name="bicep"></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.avgCalories"/>:</dt>
            <dd><input type="text" value="${measurement.avgCalories}" size=40 name="avgCalories"></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.trainingCount"/>:</dt>
            <dd><input type="text" value="${measurement.trainingCount}" size=40 name="trainingCount"></dd>
        </dl>
        <dl>
            <dt><spring:message code="measurements.avgSteps"/>:</dt>
            <dd><input type="text" value="${measurement.avgSteps}" size=40 name="avgSteps"></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
