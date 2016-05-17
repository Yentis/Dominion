/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".overwinningskaarten li img, .geldcurse li img, .actiekaarten li img").on("click" , zoomIn);
   


});


var zoomIn = function () {
    if ($(".toonKaart li").has("img")){
        $(".toonKaart li img").remove();
    }
    $(this).clone().appendTo(".toonKaart li").click(function () {
        $(this).remove();
    });


};




