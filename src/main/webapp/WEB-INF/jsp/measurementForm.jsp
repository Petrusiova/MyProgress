<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://myProgress/functions" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="../../fragments/headTag.jsp"/>
<body>
<jsp:include page="../../fragments/bodyHeader.jsp"/>

<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2> <fmt:message key="measurements.update"/></h2>
    <jsp:useBean id="measurement" type="myProgress.model.Measurement" scope="request"/>
    <form method="post" action="measurements">
        <input type="hidden" name="id" value="${measurement.id}">
        <dl>
            <dt>Date:</dt>
            <dd><input type="date" value="${measurement.date}" name="date" required></dd>
        </dl>
        <dl>
            <dt>Weight:</dt>
            <dd><input type="text" value="${measurement.weight}" size=40 name="weight" required></dd>
        </dl>
        <dl>
            <dt>Waist:</dt>
            <dd><input type="text" value="${measurement.waist}" size=40 name="waist" required></dd>
        </dl>
        <dl>
            <dt>Hips:</dt>
            <dd><input type="text" value="${measurement.hips}" size=40 name="hips" required></dd>
        </dl>
        <dl>
            <dt>Shoulders:</dt>
            <dd><input type="text" value="${measurement.shoulders}" size=40 name="shoulders"></dd>
        </dl>
        <dl>
            <dt>Quad:</dt>
            <dd><input type="text" value="${measurement.quad}" size=40 name="quad"></dd>
        </dl>
        <dl>
            <dt>Bicep:</dt>
            <dd><input type="text" value="${measurement.bicep}" size=40 name="bicep"></dd>
        </dl>
        <dl>
            <dt>Average calories per day:</dt>
            <dd><input type="text" value="${measurement.avgCalories}" size=40 name="avgCalories"></dd>
        </dl>
        <dl>
            <dt>Training count per week:</dt>
            <dd><input type="text" value="${measurement.trainingCount}" size=40 name="trainingCount"></dd>
        </dl>
        <dl>
            <dt>Average steps per week:</dt>
            <dd><input type="text" value="${measurement.avgSteps}" size=40 name="avgSteps"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
