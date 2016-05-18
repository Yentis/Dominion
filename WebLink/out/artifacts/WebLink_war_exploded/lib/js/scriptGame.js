/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".overwinningskaarten li img, .geldcurse li img, .actiekaarten li img").on("click" , zoomIn);
    $("body").on("click",zoomOut);
    $(".toonKaart li img").on("click", check);
    showPlayerName();
});


var zoomIn = function () {
    if ($(".toonKaart li").has("img")){
        $(".toonKaart li img").remove();
    }
    $(this).clone().appendTo(".toonKaart li");


};

var zoomOut = function () {
    if ($(".toonKaart ul li " + ":contains('img')").length !=0){
        console.log("derp");
        $(".toonKaart li img").remove();
    }


};

var check = function () {
    console.log("kek");
};

function showPlayerName(){
    $.ajax({
        type: "POST",
        data: 'NaamServlet='+ ' Speler1: ' + speler1 + ' Speler2: ' + speler2,
        url: url,
        success:function(content){
            $("naamspeler").html(content);
        }
    });
}