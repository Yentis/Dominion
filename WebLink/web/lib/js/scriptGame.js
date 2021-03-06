"use strict";
var gekozenkaarten = [];
var masterkaart = "";
var troonzaalmode = false;
var troonzaalkaart;
var troonzaalkaartkeuze;

$(document).ready(function () {
    $(".actiekaarten, .overwinningskaarten, .geldcurse, .kaartOpVeld").on("click", "img", zoomIn);
    $(".toonKaart").on("click", function(){$(this).empty();});
    $("#gooigeld").on("click", gooiGeld);
    $("#eindigbeurt").on("click", eindigBeurt);
    $(".hand").on("click", "img", legKaartenWeg);
    $("#ok").on("click", confirmActieKaart);
    beginBeurtServlet();
    showActieKaarten();
    showPlayerName();
    showPlayerGegevens();
    showHand();
    showKoopAantal();
});

var confirmActieKaart = function (){
    $("#ok").addClass("hide");
    $("#log").empty();
    if(masterkaart == "Troonzaal"){
        troonzaalBehaviour();
    } else {
        speelActieKaart(masterkaart, 2, gekozenkaarten, false, !troonzaalmode);
    }
};

var legKaartenWeg = function () {
    var kaart = this.src;
    if ($("#ok").hasClass("hide")) {
        checkActiekaart(kaart, true);
    } else {
        voegKaartToe(kaart);
    }
};

function checkAantalActies() {
    $.ajax({
        type: "POST",
        url: "SpelerServlet",
        success: function (result) {
            return result[0] != 0;
        }
    });
}

var speelActieKaart = function (kaart, janee, lijstkaarten, speciaal, gebruikactie) {
    if(checkAantalActies() == false){
        $("#log").html("Je hebt geen acties meer over.");
    } else {
        $.ajax({
            type: "POST",
            dataType: "json",
            data: {kaart: kaart, janee: janee, lijstkaarten: lijstkaarten, speciaal: speciaal, gebruikactie:gebruikactie},
            url: "ActieKaartSpelenServlet",
            success: function (result) {
                specialeKaartCheck(result);
                normaleKaartCheck(result);
                showPlayerGegevens();
                showHand();
            }
        });
    }
};

function normaleKaartCheck(result){
    if (result[1] != "0") {
        $("#log").html("Kies een kaart om te kopen");
        showSpecializedKoopOpties(result[1]);
    } else if (troonzaalmode) {
        troonzaalBehaviour2();
    }
}

function specialeKaartCheck(result){
    if (typeof result[2][0] !== "undefined") {
        var tereturnen = [];
        var huidigekaart = "";
        var answer;
        
        switch (kaart) {
            case "Dief":
                diefBehaviour(result[2], huidigekaart, answer, tereturnen, kaart);
                break;
            case "Bibliotheek":
                bibliotheekBehaviour(result[2], huidigekaart, answer, tereturnen, kaart);
                break;
            case "Schutterij":
                schutterijBehaviour(result[2], kaart);
                break;
            case "Bureaucraat":
                bureaucraatBehaviour(result[2], kaart);
                break;
            case "Spion":
                spionBehaviour(result[2], huidigekaart, answer, tereturnen, kaart);
                break;
        }
    } else {
        $(".kaartOpVeld").append("<li class='" + result[0] 
            + "'><img src='lib/images/kaarten/" + result[0] 
            + ".png' title='" + result[0] + "'/></li>");
        $(".hand")
            .slice(1)
            .remove("." + result[0] + "");
    }
}

function troonzaalBehaviour(){
    $(".kaartOpVeld").empty();
    
    troonzaalmode = true;
    troonzaalkaart = masterkaart;
    troonzaalkaartkeuze = gekozenkaarten;
    
    checkActiekaart(troonzaalkaartkeuze[0], false);
}

function troonzaalBehaviour2(){
    checkActiekaart(troonzaalkaartkeuze[0], false);
    speelActieKaart(troonzaalkaart, 2, troonzaalkaartkeuze, true, true);
    
    troonzaalmode = false;
}

function diefBehaviour(result, huidigekaart, answer, tereturnen, kaart){
    for (i = 0; i < result.length; i++) {
        huidigekaart = result[i];
        answer = window.confirm("Wil je " + huidigekaart + " stelen van de vijand?");
        if (answer == true) {
            tereturnen.push(huidigekaart);
        }
    }
    speelActieKaart(kaart, 2, tereturnen, true, true);
}

function bibliotheekBehaviour(result, huidigekaart, answer, tereturnen, kaart) {
    var janee = 2;
    
    huidigekaart = result[0];
    answer = window.confirm("Wil je " + huidigekaart + " aan de kant leggen?");
    if (answer == false) {
        tereturnen.push(huidigekaart);
    } else {
        tereturnen = "";
        janee = 0;
    }
    speelActieKaart(kaart, janee, tereturnen, true, true);
}

function schutterijBehaviour(result, kaart) {
    var i = 0;
    var toonSpecialeKaarten = $("#toonSpecialeKaarten");

    $(".kaartOpVeld").append("<li class='" + kaart 
        + "'><img src='lib/images/kaarten/" + kaart 
            + ".png' title='" + kaart + "'/></li>");
    $(".hand ." + kaart + ":first").remove();
    
    while (i < result.length) {
        if (result.length > 3) {
            toonSpecialeKaarten.append("<li class='" + result[i] 
                + "'><img src='lib/images/kaarten/" + result[i] 
                + ".png' title='" + result[i] + "'/></li>");
            i++;
        }
    }
    
    $("#log").html("Kies de kaarten die de tegenstander wilt afleggen");
    toonSpecialeKaarten.on("click", "img", function () {
        if (toonSpecialeKaarten.find("li").size() > 4) {
            $(this).parent().remove();
        } else if (toonSpecialeKaarten.find("li").size() < 5) {
            toonSpecialeKaarten.find("li").remove();
        }
    });
}

function bureaucraatBehaviour(result, kaart) {
    var toonSpecialeKaarten = $("#toonSpecialeKaarten");
    
    toonSpecialeKaarten
        .empty()
        .append("<h2>Kaarten van je vijand: </h2>");
    
    for (i = 0; i < result.length; i++) {
        toonSpecialeKaarten.append("<li class='" + result[i] 
            + "'><img src='lib/images/kaarten/" + result[i] 
            + ".png' title='" + result[i] + "'/></li>");
    }
    
    $(".kaartOpVeld").append("<li class='" + kaart 
        + "'><img src='lib/images/kaarten/" + kaart 
        + ".png' title='" + kaart + "'/></li>");
    
    $(".hand")
        .slice(1)
        .remove("." + kaart + "");
    
    toonSpecialeKaarten.on("click", function () {
        $(this).empty();
    });
}

function spionBehaviour(result, huidigekaart, answer, tereturnen, kaart) {
    var janee = 2;
    var naam = "";
    
    for (i = 0; i < result.length; i++) {
        huidigekaart = result[i];
        if ((i % 2) == 0 && huidigekaart == $("#naamspeler").html()) {
            naam = "jezelf";
        } else if ((i % 2) == 0) {
            naam = "de vijand";
        } else {
            answer = window.confirm("Wil je " + huidigekaart + " wegleggen van " + naam + "?");
            if (answer == true) {
                tereturnen.push(naam);
                tereturnen.push(huidigekaart);
            }
        }
    }
    speelActieKaart(kaart, 0, tereturnen, true, true);
}

function checkActiekaart(kaart, gebruikacties){
    gekozenkaarten = [];
    var log = $("#log");
    if(kaart.indexOf("png") > -1){
        kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/", "");
        kaart = kaart.replace(".png", "");
    }

    switch (kaart) {
        case "Kanselier":
            var answer = window.confirm("Wil je je deck op de aflegstapel plaatsen?");
            if (answer == true) {
                speelActieKaart(kaart, 1, "", false, gebruikacties);
            } else {
                speelActieKaart(kaart, 0, "", false, gebruikacties);
            }
            break;
        case "Kelder":
            log.html("Kies de kaarten die je wilt afleggen");
            setMasterkaartenToonOk(kaart);
            break;
        case "Kerk":
            log.html("Kies de kaarten die je wilt wegsmijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Geldverlener":
            log.html("Kies een koperkaart om weg te smijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Ombouwen":
            log.html("Kies een kaart om weg te smijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Mijn":
            log.html("Kies een geldkaart om weg te smijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Troonzaal":
            log.html("Kies een actiekaart om tweemaal te spelen");
            setMasterkaartenToonOk(kaart);
            break;
        case "Dief":
        case "Bibliotheek":
        case "Schutterij":
        case "Spion":
            speelActieKaart(kaart, 2, "", true, false);
            break;
        case "Bureaucraat":
            speelActieKaart(kaart, 2, "", true, gebruikacties);
            break;
        default:
            speelActieKaart(kaart, 2, "", false, gebruikacties);
            break;
    }
}

function setMasterkaartenToonOk(kaart) {
    masterkaart = kaart;
    $("#ok").removeClass("hide");
    $(".hand ." + kaart + ":first").hide();
}

function voegKaartToe(kaart) {
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/", "");
    kaart = kaart.replace(".png", "");
    
    $(".kaartOpVeld").append("<li class='" + kaart 
        + "'><img src='lib/images/kaarten/" + kaart 
        + ".png' title='" + kaart + "'/></li>");
    
    $(".hand ." + kaart + ":first").remove();
    gekozenkaarten.push(kaart);
    showPlayerGegevens();
}

var koopKaart = function () {
    var kaart = $(this).parent().attr("id");
    $.ajax({
        type: "POST",
        data: {kaart: kaart, speciaal: false},
        url: "KoopKaartServlet",
        success: function () {
            showKoopOpties();
            showPlayerGegevens();
            showTopAflegstapel();
            showKoopAantal();
            checkGameStatus();
        }
    });
};

function specializedKoopKaart(kaart) {
    var gekozenkaart = $(kaart).parent().attr("id");
    $.ajax({
        type: "POST",
        data: {kaart: gekozenkaart, speciaal: true},
        url: "KoopKaartServlet",
        success: function () {
            showKoopOpties();
            showPlayerGegevens();
            showTopAflegstapel();
            if(troonzaalmode){
                troonzaalBehaviour2();
            }
            checkGameStatus();
        }
    });
}

function checkGameStatus(){
    $.ajax({
        type: "POST",
        url: "EindeGameServlet",
        success: function(result){
            if(result !== ""){
                window.location = result;
            }
        }
    });
}

var showKoopOpties = function () {
    $(".koopKaart").remove();
    $.ajax({
        type: "POST",
        data: {speciaal: false, limits: ""},
        dataType: "json",
        url: "KoopServlet",
        success: function (result) {
            for (i = 0; i < result.length; i++) {
                $("#" + result[i]).append('<input type="button" class="koopKaart">');
            }
            $(".koopKaart").on("click", koopKaart);
        }
    });
};

function showSpecializedKoopOpties(limits) {
    $(".koopKaart").remove();
    $.ajax({
        type: "POST",
        data: {speciaal: true, limits: limits},
        dataType: "json",
        url: "KoopServlet",
        success: function (result) {
            for (i = 0; i < result.length; i++) {
                $("#" + result[i]).append('<input type="button" class="koopKaart">');
            }
            $(".koopKaart").on("click", function () {
                specializedKoopKaart(this);
            });
        }
    });
}

function beginBeurtServlet() {
    $.ajax({
        type: "POST",
        url: "BeurtServlet",
        success: function(){
            showKoopOpties();
        }
    });
}

var eindigBeurt = function () {
    $.ajax({
        type: "POST",
        url: "EindeBeurtServlet",
        success: function(){
            clearVeld();
            beginBeurtServlet();
            showTopAflegstapel();
            showPlayerName();
            showPlayerGegevens();
            showHand();
        }
    });
};

function clearVeld() {
    $(".kaartOpVeld").remove();
    $("#persoongegevens").after("<ul class='kaartOpVeld'></ul>");
    $("#log").html("");
}

var gooiGeld = function () {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "GooiGeldServlet",
        success: function (result) {
            for (i = 0; i < result.length; i++) {
                $(".kaartOpVeld").append("<li class='" + result[i] 
                    + "'><img src='lib/images/kaarten/" + result[i] 
                    + ".png' title='" + result[i] + "'/></li>");
                
                $(".hand").remove("." + result[i] + "");
                showPlayerGegevens();
                showHand();
                showKoopOpties();
            }
        }
    });
};

var zoomIn = function () {
    var toonKaart = $(".toonKaart");
    if (toonKaart.has("li")) {
        toonKaart.find("li").remove();
    }
    toonKaart.append("<li></li>");
    $(this).clone().appendTo(".toonKaart li");
};

function showPlayerName() {
    var naamspeler = $("#naamspeler");
    $.ajax({
        type: "POST",
        url: "NaamServlet",
        success: function (result) {
            naamspeler.html(result);
            $("#log").html("Het is " + naamspeler.html() + " zijn beurt");
        }
    })
}

function showActieKaarten() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "ActieKaartServlet",
        success: function (result) {
            for (i = 0; i < result.length / 2; i++) {
                $("#actiekaarten").prepend("<li id=" + result[i] 
                    + "><img src='lib/images/kaarten/" + result[i] 
                    + ".png' title='" + result[i] + "'/></li>");
            }
            for (i = result.length / 2; i < result.length; i++) {

                $("#actiekaarten").append("<li id=" + result[i] 
                    + "><img src='lib/images/kaarten/" + result[i] 
                    + ".png' title='" + result[i] + "'/></li>");
            }
        }
    })

}

var showKoopAantal = function () {

    $.ajax({
        type: "POST",
        dataTYpe: "json",
        url: "AantalActiekaartenServlet",
        success: function (result) {
            $(".aantal").remove();
            for (i = 0; i < result[0].length; i++) {
                $("#" + result[0][i]).prepend("<p class='aantal'>" + result[1][i] + "</p>");
            }
        }
    })
};


function showHand() {
    var hand = $(".hand");
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "HandServlet",
        success: function (result) {
            hand.html("<li class='" + result[0]
                + "'><img src='lib/images/kaarten/" + result[0]
                + ".png' title='" + result[0] + "'/></li>");
            for (i = 1; i < result.length; i++) {
                hand.append("<li class='" + result[i]
                    + "'><img src='lib/images/kaarten/" + result[i]
                    + ".png' title='" + result[i] + "'/></li>");
            }
        }
    })
}

function showPlayerGegevens() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "SpelerServlet",
        success: function (result) {
            $("#acties").html(result[0]);
            $("#buys").html(result[1]);
            $("#geld").html(result[2]);
        }
    })
}

function showTopAflegstapel() {
    var top = $("#top");
    $.ajax({
        type: "POST",
        url: "AflegstapelServlet",
        success: function (result) {
            if (result == "") {
                top
                    .attr("src", "lib/images/kaarten/undefined.png")
                    .attr("alt", "undefined")
                    .attr("title", "undefined");
            }
            else {
                top
                    .attr("src", "lib/images/kaarten/" + result + ".png")
                    .attr("alt", result)
                    .attr("title", result);
            }
        }
    });
}