/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".overwinningskaarten li img, .geldcurse li img, .actiekaarten li img").on("click" , zoomIn);
    showPlayerName();
    toonActieKaarten();
});


var zoomIn = function () {
    
    $(this).clone().appendTo(".toonKaart li").click(function () {
        remove(this);
    });
};

function showPlayerName(){

    
    
}
function showPlayerGegevens() {
    var myVar = 
    $.ajax({
        type:"POST",
        data: {speler1:speler1, speler2:speler2},
        URL: "NaamServlet",
        success: function () {
            $('acties').html(json.speler1.getA)
        }
    });
    
}


function toonActieKaarten(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"ActieKaartServlet",
        success: function(result){
            var random = ["woord1", "woord2"];
            alert(result);
            alert(typeof random);
            for(i=0;i<result.size;i++)
                $("#actiekaarten").html("<li><img src='lib/images/kaarten/actiekaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
        },
        error: function(e){
            alert(JSON.stringify(e))
        }
    })
}

var QueryString = function () {
    // This function is anonymous, is executed immediately and
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();