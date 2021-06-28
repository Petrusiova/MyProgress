<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://myProgress/functions" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<script src="resources/js/common.js" defer></script>
<script src="resources/js/measurements.js" defer></script>

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
                <div class="card border-left">
                    <div class="card-body pb-0">
                        <form id="filter">
                            <%--                            <div class="row">--%>
                            <div class="col-2">
                                <label for="startDate"><spring:message code="measurements.startDate"/></label>
                                <input class="form-control" type="date" name="startDate" id="startDate">
                            </div>
                            <div class="col-2">
                                <label for="endDate"><spring:message code="measurements.endDate"/></label>
                                <input class="form-control" type="date" name="endDate" id="endDate">
                            </div>
                            <%--                            </div>--%>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-danger" onclick="clearFilter()">
                            <span class="fa fa-remove"></span>
                            <spring:message code="common.cancel"/>
                        </button>
                        <button class="btn btn-primary" onclick="updateFilteredTable()">
                            <span class="fa fa-filter"></span>
                            <spring:message code="measurements.filter"/>
                        </button>
                    </div>
                </div>
                <br/>
                <br/>
                <button class="btn btn-primary  text-lg-right" onclick="add()">
                    <span class="fa fa-plus"></span>
                    <spring:message code="measurements.add"/>
                </button>
                <br><br>
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
                            <th><spring:message code="common.action"/></th>
                            <th><spring:message code="common.action"/></th>
                        </tr>
                        </thead>
                        <%--                        <tbody>--%>
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
                                <td><a><span class="fa fa-pencil"></span></a></td>
                                <td><a onclick="deleteRow(${measurement.id})"><span class="fa fa-remove"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                        <%--                        </tbody>--%>
                    </table>
                </div>
            </section>
        </main>
    </div>
</div>

<%--всплывашка добавления записи замера начало--%>
<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"><spring:message code="measurements.add"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="date" class="col-form-label"><spring:message code="measurements.date"/></label>
                        <input type="date" class="form-control" id="date" name="date"
                               placeholder="<spring:message code="measurements.date"/>">
                    </div>

                    <div class="form-group">
                        <label for="weight" class="col-form-label"><spring:message code="measurements.weight"/></label>
                        <input type="text" class="form-control" id="weight" name="weight"
                               placeholder="<spring:message code="measurements.weight"/>">
                    </div>

                    <div class="form-group">
                        <label for="waist" class="col-form-label"><spring:message code="measurements.waist"/></label>
                        <input type="text" class="form-control" id="waist" name="waist"
                               placeholder="<spring:message code="measurements.waist"/>">
                    </div>

                    <div class="form-group">
                        <label for="hips" class="col-form-label"><spring:message code="measurements.hips"/></label>
                        <input type="text" class="form-control" id="hips" name="hips"
                               placeholder="<spring:message code="measurements.hips"/>">
                    </div>

                    <div class="form-group">
                        <label for="shoulders" class="col-form-label"><spring:message
                                code="measurements.shoulders"/></label>
                        <input type="text" class="form-control" id="shoulders" name="shoulders"
                               placeholder="<spring:message code="measurements.shoulders"/>">
                    </div>

                    <div class="form-group">
                        <label for="quad" class="col-form-label"><spring:message code="measurements.quad"/></label>
                        <input type="text" class="form-control" id="quad" name="quad"
                               placeholder="<spring:message code="measurements.quad"/>">
                    </div>

                    <div class="form-group">
                        <label for="bicep" class="col-form-label"><spring:message code="measurements.bicep"/></label>
                        <input type="text" class="form-control" id="bicep" name="bicep"
                               placeholder="<spring:message code="measurements.bicep"/>">
                    </div>

                    <div class="form-group">
                        <label for="avgCalories" class="col-form-label"><spring:message
                                code="measurements.avgCalories"/></label>
                        <input type="text" class="form-control" id="avgCalories" name="avgCalories"
                               placeholder="<spring:message code="measurements.avgCalories"/>">
                    </div>

                    <div class="form-group">
                        <label for="trainingCount" class="col-form-label"><spring:message
                                code="measurements.trainingCount"/></label>
                        <input type="number" class="form-control" id="trainingCount" name="trainingCount"
                               placeholder="<spring:message code="measurements.trainingCount"/>">
                    </div>

                    <div class="form-group">
                        <label for="avgSteps" class="col-form-label"><spring:message
                                code="measurements.avgSteps"/></label>
                        <input type="text" class="form-control" id="avgSteps" name="avgSteps"
                               placeholder="<spring:message code="measurements.avgSteps"/>">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<%--всплывашка добавления записи замера заканчивается--%>

</body>
</html>