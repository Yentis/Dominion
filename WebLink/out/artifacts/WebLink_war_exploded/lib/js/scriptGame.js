/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".actiekaarten, .overwinningskaarten, .geldcurse").on("click", "img", zoomIn);
    showPlayerName();
    toonActieKaarten();
    showPlayerGegevens();
});


var zoomIn = function () {
    console.log("hey");
    if ($(".toonKaart li").has("img")) {
        $(".toonKaart li img").remove();
    }
    $(this).clone().appendTo(".toonKaart li").click(function () {
        $(this).remove();
    });
};

function showPlayerName() {
    $('#naamspeler').html(QueryString.speler1);
}

function toonActieKaarten(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"ActieKaartServlet",
        success: function(result){
            for(i=0;i<result.length/2;i++){
                $("#actiekaarten").prepend("<li><img src='lib/images/kaarten/actiekaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
            }
            for(i=result.length/2;i<result.length;i++){
                $("#actiekaarten").append("<li><img src='lib/images/kaarten/actiekaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
            }
        }
    })
}

function showPlayerGegevens() {
        $.ajax({
            type: "POST",
            data: {speler1: speler1, speler2: speler2},
            URL: "SpelerServlet",
            success: function (result) {
                $('#acties').html(result);
            }
        });
}

var QueryString = function () {
    // This function is anonymous, is executed immediately and
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], decodeURIComponent(pair[1])];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
};