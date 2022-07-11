$(document).ready(function () {

    $('.single-date').daterangepicker({
        autoUpdateInput: false,
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });

    $('.single-date').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD'));
    });

    $('.single-datetime').daterangepicker({
        autoUpdateInput: false,
        singleDatePicker: true,
        timePicker: true,
        timePickerSeconds: true,
        timePicker24Hour: true,
        locale: {
            format: 'DD/MM/YYYY HH:mm:ss'
        }
    });

    $('.single-datetime').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY HH:mm:ss'));
    });

    $('.single-datetime2').daterangepicker({
        autoUpdateInput: false,
        singleDatePicker: true,
        timePicker: true,
        timePickerSeconds: true,
        timePicker24Hour: true,
        locale: {
            format: 'YYYY/MM/DD HH:mm:ss'
        }
    });

    $('.single-datetime2').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY/MM/DD HH:mm:ss'));
    });

    $('.single-date2').daterangepicker({
        autoUpdateInput: false,
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });

    $('.single-date2').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD'));
    });

    $('.daterange').daterangepicker({
        autoUpdateInput: false,
        locale: {
            format: 'DD/MM/YYYY'
        },
        ranges: {
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        alwaysShowCalendars: true,
    });

    $('.daterange').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
    });

    $('.daterange-time').daterangepicker({
        autoUpdateInput: false,
        timePicker: true,
        timePickerSeconds: true,
        timePicker24Hour: true,
        locale: {
            format: 'DD/MM/YYYY HH:mm:ss'
        },
        ranges: {
            'Today': [moment().set("hour",0).set("minute",0).set("seconds",0), moment().set("hour",23).set("minute",59).set("seconds",59)],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        alwaysShowCalendars: true,
    });

    $('.daterange-time').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY HH:mm:ss') + ' - ' + picker.endDate.format('DD/MM/YYYY HH:mm:ss'));
    });
});
