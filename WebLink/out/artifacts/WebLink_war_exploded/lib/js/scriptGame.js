/**
 * Created by Renzie on 12/05/2016.
 */


$(document).ready(function () {
    $(".actiekaarten, .overwinningskaarten, .geldcurse").on("click", "img", zoomIn);
    beginBeurtServlet();
    showActieKaarten();
    showPlayerName();
    showPlayerGegevens();
    showHand();
    showKoopOpties();
    $("#gooigeld").on("click", gooiGeld);
    $("#eindigbeurt").on("click", eindigBeurt);
    $(".hand").on("click", "img", speelActieKaart);
    $("ul li .koopKaart").on("click", koopKaart);
});

var speelActieKaart = function(){
    var kaart = this.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".jpg","");
    
    $.ajax({
        type:"POST",
        url:"SpelerServlet",
        success: function(result){
            console.log(result[0]);
            if(result[0] == 0){
                $("#log").html("Je hebt geen acties meer over.");
            } else {
                var janee = checkActiekaart(kaart);
                console.log(janee);
                $.ajax({
                    type:"POST",
                    data:{kaart:kaart, janee:janee},
                    url:"ActieKaartSpelenServlet",
                    success: function(result){
                        $(".kaartOpVeld").append("<li class='"+result+"'><img src='lib/images/kaarten/" + result + ".jpg' title='" + result + "'/></li>");
                        $(".hand").slice(1).remove("." + result + "");
                    }
                });
            }
        }
    });
    showPlayerGegevens();
    showHand();
};

function checkActiekaart(kaart){
    switch(kaart){
        case "Kanselier":
            var answer = window.confirm("Wil je je deck op de aflegstapel plaatsen?");
            if(answer == true){
                return 1;
            } else {
                return 0;
            }
            break;
        default:
            return 2;
            break;
    }
}

var koopKaart = function () {
   
    console.log("koopkaart connected");

    var kaart = this.id;
    console.log(kaart + " var kek");

    $.ajax({
        type:"POST",
        data:{kaart:kaart},
        url:"KoopKaartServlet",
        success: function (result) {
            console.log("kaart"+ result + " is gekocht");
        }
    });
    showPlayerGegevens();
    showKoopOpties();
};


var showKoopOpties = function () {
    $(".koopKaart").remove();
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"KoopServlet",
        success: function (result) {
            for (i = 0; i<result.length; i++){
                $("#" + result[i] +"").append('<input type="button" value="koop" class ="koopKaart">').click(koopKaart);
            }
        }
    });
    showPlayerGegevens()
};

function beginBeurtServlet(){
    $.ajax({
        type:"POST",
        url:"BeurtServlet"
    })
}

var eindigBeurt = function(){
    $.ajax({
        type:"POST",
        url:"EindeBeurtServlet"
    });
    clearVeld();
    beginBeurtServlet();
    showPlayerName();
    showPlayerGegevens();
    showHand();
};

function clearVeld(){
    $(".kaartOpVeld").remove();
    $("#persoongegevens").after("<ul class='kaartOpVeld'></ul>")
    $("#log").html("");
}

var gooiGeld = function(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"GooiGeldServlet",
        success: function(result){
            for(i=0;i<result.length;i++){
                $(".kaartOpVeld").append("<li class='"+result[i]+"'><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + result[i] + "'/></li>");
                $(".hand").remove("."+result[i]+"");
            }
        }
    });
    showPlayerGegevens();
    showHand();
    showKoopOpties();
};

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

                $("#actiekaarten").prepend("<li id=" +result[i] +"><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + result[i] + "'/></li>");
            }
            for(i=result.length/2;i<result.length;i++){

                $("#actiekaarten").append("<li id=" +result[i] +"><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + result[i] + "'/></li>");
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
            $(".hand").html("<li class='" + result[0] + "'><img src='lib/images/kaarten/" + result[0] + ".jpg' title='" + "temp" + "'/></li>");
            console.log(result + " " + typeof result);
            if(result != null){
                for(i=1;i<result.length;i++){
                    $(".hand").append("<li class='" + result[i] + "'><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
                }
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