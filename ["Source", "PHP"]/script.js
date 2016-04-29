/**
 * Created by niels on 14/04/2016.
 */
/**
 * Created by Renzie on 21/04/2016.
 */
$(document).ready(function () {

    console.log('derp');
    $("#spelregelsKnop").on("click",toonSpelRegels);
    $("#spelen").on("click",toonNaamGeving);
    $("#vorige").on("click",toonStartpagina);
    $("#toggleMuziek").on("click",toggleMuziek);
    $("#volgendePagina").on("click",next);

});

var huidigeAfbeelding = 1;
var i = 2;
function addImages(){
    for (var i = 1; i<9 ;i++){
        html = '<li><img src=lib/images/Pagina';
            html+=i;
        html+='.PNG></li>';

        $(".carousel").append(html);
    }
    $(".carousel li:first").show();
}


var next = function (e) {
    e.preventDefault();
    if (huidigeAfbeelding < 9) {
        huidigeAfbeelding++;
        $('.carousel li:nth-child(' + (huidigeAfbeelding - 1) + ')').fadeOut(function(){
            $('.carousel li:nth-child(' + huidigeAfbeelding + ')').fadeIn();
        });
    }
};

var audio = document.getElementById('muziek');

function toggleMuziek(e){
    e.preventDefault();
    audio.muted = !audio.muted;
    $(this).toggleClass('mute');
}
function toonSpelRegels(){


    $("#spelRegels").slideToggle("slow");
    addImages();
    $("#spelregelsKnop:after").css("content","X");


}

function toonNaamGeving(e){
    e.preventDefault();

    $("#start").slideUp("slow");
    $("#naamgeving").slideDown("slow");
}

function toonStartpagina(e){
    e.preventDefault();
    $("#start").slideDown("slow");
    $("#naamgeving").slideUp("slow");
}
