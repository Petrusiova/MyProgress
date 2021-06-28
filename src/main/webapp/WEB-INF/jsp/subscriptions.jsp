<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://myProgress/functions" %>


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

                <div class="table-responsive" data-color="#655f5f">
                    <table class="table table-striped table-sm" id="datatable">
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
                        <c:forEach items="${subscriptions}" var="subscription">

                            <h2>${subscription.key}</h2>

                            <c:forEach items="${subscription.value}" var="measurements">
                                    <tr>
                                        <td>
                                                ${fn:formatDate(measurements.date)}
                                        </td>
                                        <td>${measurements.weight}</td>
                                        <td>${measurements.waist}</td>
                                        <td>${measurements.hips}</td>
                                        <td>${measurements.shoulders}</td>
                                        <td>${measurements.quad}</td>
                                        <td>${measurements.bicep}</td>
                                        <td>${measurements.avgCalories}</td>
                                        <td>${measurements.trainingCount}</td>
                                        <td>${measurements.avgSteps}</td>
                                    </tr>
                            </c:forEach>
                        </c:forEach>
                    </table>
                </div>
            </section>
        </main>
    </div>
</div>

</body>
</html>