/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".actiekaarten, .overwinningskaarten, .geldcurse").on("click", "img", zoomIn);
    showPlayerName();
    showPlayerGegevens();
    showActieKaarten();
    showHand();
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
    $.ajax({
        type:"POST",
        url:"NaamServlet",
        success: function(result){
            $("#naamspeler").html(result);
        }
    })
}

function showActieKaarten(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"ActieKaartServlet",
        success: function(result){
            for(i=0;i<result.length/2;i++){
                $("#actiekaarten").prepend("<li><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
            }
            for(i=result.length/2;i<result.length;i++){
                $("#actiekaarten").append("<li><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
            }
        }
    })
}
function showHand() {
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"HandServlet",
        success: function (result) {
            for(i=0;i<result.length;i++){
                console.log(result[i]);
                $(".hand").append("<li><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
            }
        }
    })
}

function showPlayerGegevens() {
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"SpelerServlet",
        success: function(result){
            $("#acties").append(result[0]);
            $("#buys").append(result[1]);
            $("#geld").append(result[2]);
        }
    })
}