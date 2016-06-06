$(document).ready(function () {
    getInfo();
});

function getInfo(){
    $.ajax({
        type: "POST",
        dataType:"json",
        url: "ScoreBerekeningsServlet",
        success: function (result) {
            $("#spelers")
                .append("<li id='speler1'>" + result[0] + "</li>")
                .append("<li id='speler2'>" + result[1] + "</li>");
            $("#punten")
                .append("<li>" + result[2] + "</li>")
                .append("<li>" + result[3] + "</li>");
            $("#restartspel").on("click", restartSpel);
        }
    });
}

var restartSpel = function (){
    var speler1 = $("#speler1").html();
    var speler2 = $("#speler2").html();
    $.ajax({
        type: "POST",
        data:{speler1:speler1, speler2:speler2},
        url: "BuildServlet",
        success: function () {
            window.location = "gamepagina.jsp";
        }
    });
};