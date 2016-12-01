var WEBPORTALAPP = WEBPORTALAPP || {};

var loader = (function () {

    var loader = '<div class="loader-wrapper" id="custom-loader" tabindex="-1">' +
        '<div class="sk-circle">' +
        '<div class="sk-circle1 sk-child"></div>' +
        '<div class="sk-circle2 sk-child"></div>' +
        '<div class="sk-circle3 sk-child"></div>' +
        '<div class="sk-circle4 sk-child"></div>' +
        '<div class="sk-circle5 sk-child"></div>' +
        '<div class="sk-circle6 sk-child"></div>' +
        '<div class="sk-circle7 sk-child"></div>' +
        '<div class="sk-circle8 sk-child"></div>' +
        '<div class="sk-circle9 sk-child"></div>' +
        '<div class="sk-circle10 sk-child"></div>' +
        '<div class="sk-circle11 sk-child"></div>' +
        '<div class="sk-circle12 sk-child"></div>' +
        '</div>' +
        '<div class="loader-mess-wrapper"><span id="loader-mess"></span></div>' +
        '</div>';

    var show = function (mess) {

            if ($('#custom-loader').length > 0) {
                $('#custom-loader').show();
                // $('#loader-mess').text(mess);
                $('#loader-mess').text(mess ? mess : "Выполняется ... ");

            } else {
                $('body').append(loader);
                // $('#loader-mess').text(mess);
                $('#loader-mess').text(mess ? mess : "Выполняется ... ");
            }
        },

        hide = function () {
            $('#custom-loader').hide();
            $('#loader-mess').text("");
        };

    return {
        show: show,
        hide: hide
    }
}());

WEBPORTALAPP.admin = (function () {
    function getPage(url) {
        $.ajax({
            url: url,
            type: "GET",
            cache: false,
            success: function (html) {
                $("#body").html(html);
            },
            error: function (msg) {

            }
        });
    }

    function postData(url, obj, result) {
        loader.show("loading")
        $.ajax({
            url: url,
            type: "POST",
            data: {id: $(obj).closest("div").attr("id"), result: result},
            cache: false,
            success: function (html) {
                $("#body").html(html);
                loader.hide();
            },
            error: function (msg) {
                loader.hide();
            }
        });
    }

    function checkPayOut(url, orderId, result) {
        $.ajax({
            url: url,
            type: "POST",
            data: {id: orderId, result: result},
            cache: false,
            success: function (html) {
                $("#body").html(html);
            },
            error: function (msg) {

            }
        });
    }

    return {
        getPage: getPage,
        postData: postData,
        checkPayOut: checkPayOut
    }

}());

$(function () {

    $('[data-event="find-by-phone"]').on("click", function() {
        WEBPORTALAPP.admin.postData(this.dataset.page, '', $("#phoneNumber").val())
    });
    $('[data-event="reset-password"]').on("click", function() {
        document.getElementById('uploadDocument').disabled = true;
        WEBPORTALAPP.admin.postData(this.dataset.page, '', $("#number").text())
    })
});
