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
});

var speelActieKaart = function(){
    var kaart = this.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".jpg","");

    $.ajax({
        type:"POST",
        data:{kaart:kaart},
        url:"ActieKaartSpelenServlet",
        success: function(result){
            alert(result);
            $(".kaartOpVeld").append("<li class='"+result+"'><img src='lib/images/kaarten/" + result + ".jpg' title='" + result + "'/></li>");
            $(".hand").remove("."+result+"");
        }
    });
    showPlayerGegevens();
    showHand();
};

var koopKaart = function () {
    var kaart = this.src;
    kaart = kaart.replace("http://localhost:8081/lib/images/kaarten/","");
    kaart = kaart.replace(".jpg","");
    $.ajax({
        type:"POST",
        data: {kaart:kaart},
        url:"KoopKaartServlet"
        
    })
    
};


var showKoopOpties = function () {
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"KoopServlet",
        success: function (result) {
            for (i = 0; i<result.length; i++){
                console.log(result[i]);
                $("#" + result[i] +"").append('<input type="button" value="koop" class ="koopKaart">');
            }
        }
    })
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
            for(i=1;i<result.length;i++){
                $(".hand").append("<li class='" + result[i] + "'><img src='lib/images/kaarten/" + result[i] + ".jpg' title='" + "temp" + "'/></li>");
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
            $("#acties").html(result[0]);
            $("#buys").html(result[1]);
            $("#geld").html(result[2]);
        }
    })
}