<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://myProgress/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<%--<jsp:include page="fragments/bodyHeader.jsp"/>--%>

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
                <h3><spring:message code="measurements.title"/></h3>

                <form method="get" action="measurements/filter" data-color="#655f5f">
                    <dl>
                        <dt><spring:message code="measurements.startDate"/>:</dt>
                        <dd><input class="form-control" type="date" name="startDate" value="${param.startDate}"></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="measurements.endDate"/>:</dt>
                        <dd><input class="form-control" type="date" name="endDate" value="${param.endDate}"></dd>
                    </dl>
                    <dl>
                        <dt></dt>
                        <dd><button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="measurements.filter"/></button></dd>
                    </dl>

                </form>
                <hr/>
                <a href="measurements/create"><spring:message code="measurements.add"/></a>
                <br><br>
                <div class="table-responsive" data-color="#655f5f">
                    <table class="table table-striped table-sm">
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
                            <th><spring:message code="common.action"/></th>
                            <th><spring:message code="common.action"/></th>
                        </tr>
                        </thead>
                        <tbody>
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
                                <td><a href="measurements/update?id=${measurement.id}"><spring:message
                                        code="common.update"/></a>
                                </td>
                                <td><a href="measurements/delete?id=${measurement.id}"><spring:message
                                        code="common.delete"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
</div>
<%--<jsp:include page="graph.jsp"/>--%>
</body>
</html>