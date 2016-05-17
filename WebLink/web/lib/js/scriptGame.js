/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".overwinningskaarten li img, .geldcurse li img, .actiekaarten li img").on("click" , zoomIn);
    $("body").on("click",zoomOut);
    $(".toonKaart li img").on("click", check);
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