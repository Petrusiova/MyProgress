const measurementsAjaxUrl = "measurements/";

// // https://stackoverflow.com/a/5064235/548473
// const ctx = {
//     ajaxUrl: measurementsAjaxUrl,
//     updateTable: function () {
//         $.ajax({
//             type: "GET",
//             url: "measurements/filter",
//             data: $("#filter").serialize()
//         }).done(updateTableByData);
//     }
// }

function clearFilter() {
    $("#filter")[0].reset();
    // $.get("measurements/", updateTableByData);
    updateFilteredTable();
}


function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "measurements/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

// $(document).ready(function () {
$(function () {
    makeEditable({
        ajaxUrl: "measurements/",
        datatableApi: $("#datatable").DataTable({
            columnDefs: [{ }],
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "date"
                },
                {
                    "data": "weight"
                },
                {
                    "data": "waist"
                },
                {
                    "data": "hips"
                },
                {
                    "data": "shoulders", sDefaultContent: ""
                },
                {
                    "data": "quad", sDefaultContent: ""
                },
                {
                    "data": "bicep", sDefaultContent: ""
                },
                {
                    "data": "avgCalories", sDefaultContent: ""
                },
                {
                    "data": "trainingCount", sDefaultContent: ""
                },
                {
                    "data": "avgSteps", sDefaultContent: ""
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        }),
        updateTable: updateFilteredTable
    });
});