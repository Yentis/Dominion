/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".overwinningskaarten li img, .geldcurse li img, .actiekaarten li img").on("click" , zoomIn);
<<<<<<< HEAD
    $("body").on("click",zoomOut);
    $(".toonKaart li img").on("click", check);
    showPlayerName();
=======
 

>>>>>>> 431556444e38048a4eea33c1f5dbd1513333a6ad
});


var zoomIn = function () {
    if ($(".toonKaart li").has("img")){
        $(".toonKaart li img").remove();
    }
    $(this).clone().appendTo(".toonKaart li").click(function () {
        remove(this);
    });


};


<<<<<<< HEAD
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
=======

>>>>>>> 431556444e38048a4eea33c1f5dbd1513333a6ad
