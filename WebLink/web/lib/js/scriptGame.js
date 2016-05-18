/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".overwinningskaarten li img, .geldcurse li img, .actiekaarten li img").on("click" , zoomIn);
    showPlayerName();
});


var zoomIn = function () {
    
    $(this).clone().appendTo(".toonKaart li").click(function () {
        remove(this);
    });
};

function getNaam() {
    $("#naamspeler").getText();
}

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