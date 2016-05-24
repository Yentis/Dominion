/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    gekozenkaarten = [];
    masterkaart = "";
    $(".actiekaarten, .overwinningskaarten, .geldcurse, .kaartOpVeld").on("click", "img", zoomIn);
    beginBeurtServlet();
    showActieKaarten();
    showPlayerName();
    showPlayerGegevens();
    showHand();
    //showKoopAantal();
    $(".toonKaart").on("click", function(){
        $(".toonKaart").empty();
    })
    $("#gooigeld").on("click", gooiGeld);
    $("#eindigbeurt").on("click", eindigBeurt);
    $(".hand").on("click", "img", function(){
        var kaart = this;
        if($("#ok").hasClass("hide")) {
            console.log("yes");
            checkActiekaart(kaart);
        } else {
            console.log("no");
            voegKaartToe(kaart);
        }
    });
    $("#ok").on("click", function(){
        speelActieKaart(masterkaart, 2, gekozenkaarten, false);
        $("#ok").addClass("hide");
        $("#log").html("");
    });showScorebord(); showSpelerNaamScorePagina();
});

var speelActieKaart = function(kaart, janee, lijstkaarten, speciaal){
    console.log("Speciaal? " + speciaal);
    console.log("Actual kaart: " + kaart);
    $.ajax({
        type:"POST",
        url:"SpelerServlet",
        success: function(result){
            if(result[0] == 0){
                $("#log").html("Je hebt geen acties meer over.");
            } else {
                console.log("start ajax");
                $.ajax({
                    type:"POST",
                    dataType:"json",
                    data:{kaart:kaart, janee:janee, lijstkaarten:lijstkaarten, speciaal:speciaal},
                    url:"ActieKaartSpelenServlet",
                    success: function(result){
                        console.log("Kaart: " + result[0]);
                        for(i=0;i<result[2].length;i++){
                            console.log("Return: " + result[2][i]);
                        }
                        if(typeof result[2][0] !== "undefined"){
                            var tereturnen = [];
                            var huidigekaart = "";
                            var answer;
                            switch(kaart){
                                case "Dief":
                                    for(i=0;i<result[2].length;i++){
                                        huidigekaart = result[2][i];
                                        answer = window.confirm("Wil je " + huidigekaart + " stelen?");
                                        if(answer == true){
                                            tereturnen.push(huidigekaart);
                                        }
                                    }
                                    speelActieKaart(kaart, 2, tereturnen, true);
                                    break;
                                case "Bibliotheek":
                                    var janee = 2;
                                    huidigekaart = result[2][0];
                                    answer = window.confirm("Wil je " + huidigekaart + " aan de kant leggen?");
                                    if(answer == false){
                                        tereturnen.push(huidigekaart);
                                    } else {
                                        tereturnen = "";
                                        janee = 0;
                                    }
                                    console.log("Kaart is: " + kaart + " terug te sturen: " + tereturnen + " janee: " + janee);
                                    speelActieKaart(kaart, janee, tereturnen, true);
                                    break;

                                case "Schutterij":
                                    console.log("verbonden aan shitterij");

                                    console.log(result[2]);
                                    
                                        if (result[2].length > 3){
                                             $("#toonSpecialeKaarten").append("<li class='"+huidigekaart+"'><img src='lib/images/kaarten/" + huidigekaart + ".png' title='" + huidigekaart + "'/></li>");
                                            console.log("dus er zou iets mis moeten zijn met de loop")
                                        }

                                    break;
                                case "Bureaucraat":
                                    for (i = 0; i<result[2].length; i++){
                                        $("#toonSpecialeKaarten").append("<li class='"+result[2][i]+"'><img src='lib/images/kaarten/" + result[2][i] + ".png' title='" + result[2][i] + "'/></li>");
                                    }
                                    $(".kaartOpVeld").append("<li class='"+"Bureaucraat"+"'><img src='lib/images/kaarten/" + kaart + ".png' title='" + kaart + "'/></li>");
                                    $(".hand").slice(1).remove("." + kaart + "");
                                    break;
                                case "Spion":
                                    var janee = 2;
                                    var naam = "";
                                    for(i = 0;i<result[2].length;i++){
                                        huidigekaart = result[2][i];
                                        if ((i % 2) == 0 && huidigekaart == $("#naamspeler").html()){
                                            naam = "jezelf";
                                        } else if ((i % 2) == 0){
                                            naam = "de vijand";
                                        } else {
                                            answer = window.confirm("Wil je " + huidigekaart + " wegleggen van " + naam + "?");
                                            if(answer == true){
                                                tereturnen.push(naam);
                                                tereturnen.push(huidigekaart);
                                            }
                                        }
                                    }

                                    console.log("Kaart is: " + kaart + " terug te sturen: " + tereturnen + " janee: " + janee);
                                    speelActieKaart(kaart, 0, tereturnen, true);
                                    break;
                                }
                        } else {
                            $(".kaartOpVeld").append("<li class='"+result[0]+"'><img src='lib/images/kaarten/" + result[0] + ".png' title='" + result[0] + "'/></li>");
                            $(".hand").slice(1).remove("." + result[0] + "");
                        }
                        if(result[1] != "0" && result[1] != ""){
                            $("#log").html("Kies een kaart om te kopen");
                            console.log("results: " + result[1]);
                            showSpecializedKoopOpties(result[1]);
                        }
                        showPlayerGegevens();
                        showHand();
                    }
                });
            }
        }
    });
};

function checkActiekaart(kaart){
    gekozenkaarten = [];
    kaart = kaart.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".png","");
    console.log(kaart);
    
    switch(kaart){
        case "Kanselier":
            var answer = window.confirm("Wil je je deck op de aflegstapel plaatsen?");
            if(answer == true){
                speelActieKaart(kaart, 1, "", false);
            } else {
                speelActieKaart(kaart, 0, "", false);
            }
            break;
        case "Kelder":
            $("#log").html("Kies de kaarten die je wilt afleggen");
            setMasterkaartenToonOk(kaart);
            break;
        case "Kerk":
            $("#log").html("Kies de kaarten die je wilt wegsmijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Geldverlener":
            $("#log").html("Kies een koperkaart om weg te smijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Ombouwen":
            $("#log").html("Kies een kaart om weg te smijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Mijn":
            $("#log").html("Kies een geldkaart om weg te smijten");
            setMasterkaartenToonOk(kaart);
            break;
        case "Dief":
        case "Bibliotheek":
        case "Bureaucraat":
        case "Schutterij":
            $("#log").html("Kies de kaarten die je wilt afleggen");
            break;
        case "Spion":
            speelActieKaart(kaart, 2, "", true);
            break;
        default:
            speelActieKaart(kaart, 2, "", false);
            break;
    }
}

function setMasterkaartenToonOk(kaart){
    masterkaart = kaart;
    $("#ok").removeClass("hide");
    $(".hand ." + kaart + ":first").hide();
}

function voegKaartToe(kaart){
    kaart = kaart.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".png","");
    $(".kaartOpVeld").append("<li class='"+kaart+"'><img src='lib/images/kaarten/" + kaart + ".png' title='" + kaart + "'/></li>");
    $(".hand ." + kaart + ":first").remove();
    gekozenkaarten.push(kaart);
}

var koopKaart = function () {
    var kaart = $(this).parent().attr("id");
    $.ajax({
        type:"POST",
        data:{kaart:kaart, speciaal:false},
        url:"KoopKaartServlet",
        success: function () {
            showKoopOpties();
            showPlayerGegevens();
            showTopAflegstapel();
        }
    });
};

function specializedKoopKaart(kaart){
    var gekozenkaart = $(kaart).parent().attr("id");
    $.ajax({
        type: "POST",
        data: {kaart: gekozenkaart, speciaal: true},
        url: "KoopKaartServlet",
        success: function () {
            showKoopOpties();
            showPlayerGegevens();
            showTopAflegstapel();
        }
    });
};

var showKoopOpties = function () {
    $(".koopKaart").remove();
    $.ajax({
        type:"POST",
        data:{speciaal:false, limits:""},
        dataType:"json",
        url:"KoopServlet",
        success: function(result) {
            for (i = 0; i<result.length; i++){
                $("#" + result[i]).append('<input type="button" class="koopKaart">');
            }
            $(".koopKaart").on("click",koopKaart);
        }
    });
};

function showSpecializedKoopOpties(limits){
    $(".koopKaart").remove();
    $.ajax({
        type:"POST",
        data:{speciaal:true, limits:limits},
        dataType:"json",
        url:"KoopServlet",
        success: function(result) {
            for (i = 0; i<result.length; i++){
                $("#" + result[i]).append('<input type="button" class="koopKaart">');
            }
            $(".koopKaart").on("click",function(){
                
                specializedKoopKaart(this);
            });
        }
    });
}

function beginBeurtServlet(){
    $.ajax({
        type:"POST",
        url:"BeurtServlet"
    });
    showKoopOpties();

}

var eindigBeurt = function(){
    $.ajax({
        type:"POST",
        url:"EindeBeurtServlet"
    });
    clearVeld();
    beginBeurtServlet();
    showTopAflegstapel();
    showPlayerName();
    showPlayerGegevens();
    showHand();
};

function clearVeld(){
    $(".kaartOpVeld").remove();
    $("#persoongegevens").after("<ul class='kaartOpVeld'></ul>");
    $("#log").html("");
}

var gooiGeld = function(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"GooiGeldServlet",
        success: function(result){
            for(i=0;i<result.length;i++){
                $(".kaartOpVeld").append("<li class='"+result[i]+"'><img src='lib/images/kaarten/" + result[i] + ".png' title='" + result[i] + "'/></li>");
                $(".hand").remove("."+result[i]+"");
                showPlayerGegevens();
                showHand();
                showKoopOpties();
            }
        }
    });
};

var zoomIn = function () {
    console.log("hey");
    if ($(".toonKaart").has("li")) {
        $(".toonKaart li").remove();
    }
    $(".toonKaart").append("<li></li>");
    $(this).clone().appendTo(".toonKaart li");
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

                $("#actiekaarten").prepend("<li id=" +result[i] +"><img src='lib/images/kaarten/" + result[i] + ".png' title='" + result[i] + "'/></li>");
            }
            for(i=result.length/2;i<result.length;i++){

                $("#actiekaarten").append("<li id=" +result[i] +"><img src='lib/images/kaarten/" + result[i] + ".png' title='" + result[i] + "'/></li>");
            }
        }
    })
    
}
/*
var showKoopAantal = function () {
    $.ajax({
        type:"POST",
        dataTYpe:"json",
        url:"AantalActiekaartenServlet",
        success: function(result){
            for(i=0;i<result.length;i++){
                $("#" + result[i]).prepend("<h2>" +result[i]+"</h2>");
            }
        }
    })
};
*/

function showHand() {
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"HandServlet",
        success: function (result) {
            $(".hand").html("<li class='" + result[0] + "'><img src='lib/images/kaarten/" + result[0] + ".png' title='" + "temp" + "'/></li>");
                for(i=1;i<result.length;i++){
                    $(".hand").append("<li class='" + result[i] + "'><img src='lib/images/kaarten/" + result[i] + ".png' title='" + "temp" + "'/></li>");
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

function showScorebord() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "EindeGameServlet",
        success: function (result) {
            for (i = 0; i < result.length; i++) {
                $("#score").append("<li>" + result[i] + "</li>");
            }
        }
    });
}
function showTopAflegstapel() {
    $.ajax({
        type: "POST",
        url: "AflegstapelServlet",
        success: function (result) {
            if (result == ""){
                $("#top").attr("src", "lib/images/kaarten/undefined.png");
                $("#top").attr("alt", "undefined");
                $("#top").attr("title", "undefined");
            }
            else{
                $("#top").attr("src", "lib/images/kaarten/" + result + ".png");
                $("#top").attr("alt", result);
                $("#top").attr("title", result);
            }

        }
    });
}
function showSpelerNaamScorePagina() {
    $.ajax({
        type : "POST",
        dataType: "json",
        url: "SpelerNaamWeergevenServlet",
        success: function (result) {
            for(i=0;i<result.length;i++){
                $("#spelers").append ("<li>" + result[i] + "</li>");
            }
        }
    })

}
