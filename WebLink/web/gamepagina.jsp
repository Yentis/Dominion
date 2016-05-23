<%--
  Created by IntelliJ IDEA.
  User: Yentl-PC
  Date: 17/05/2016
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dominion</title>
    <meta name="author" content="Laurens Visser"/>
    <link rel="stylesheet" type="text/css" href="lib/css/reset.css"/>
    <link rel="stylesheet" href="lib/css/styleGame.css" type="text/css"/>
</head>
<body>
<div id="wrapper">


    <ul class="overwinningskaarten">
        <li id="Provincie"><img src="lib/images/kaarten/ProvinciePS.png" alt="province" title="province"/></li>
        <li id="Hertogdom"><img src="lib/images/kaarten/HertogdomPS.png" alt="duchy" title="duchy::"/></li>
        <li id="Landgoed"><img src="lib/images/kaarten/LandgoedPS.png" alt="estate" title="estate"/></li>
    </ul>


    <ul class="stapel">
        <li><img src="lib/images/kaarten/undefined.png" id="top" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/achterkant.png" alt="avonturier" title="avonturier"/></li>
    </ul>


    <ul class="actiekaarten" id="actiekaarten">
        <li><span id="linebreaker"> </span></li>
    </ul>

    <div id="buttons">
        <input type="button" value="Gooi alle geldkaarten" id="gooigeld">
        <input type="button" value="Eindig je beurt" id="eindigbeurt">
        <input type="button" value="OK" id="ok" class="hide">
    </div>
    <div>
        <h2 id="log"></h2>
    </div>

    <div id="persoongegevens">
        <ul>

            <li >|ACTIES:<span id="acties"></span></li>
            <li>|BUYS:<span id="buys"></span></li>
            <li>|GELD:<span id="geld"></span></li>
        </ul>

    </div>

    <ul class="kaartOpVeld">
    </ul>


    <ul class="geldcurse">
        <li id="Goud"><img src="lib/images/kaarten/Goud.png" alt="gold" title="gold"></li>
        <li id="Zilver"><img  src="lib/images/kaarten/Zilver.png" alt="silver" title="silver"></li>
        <li id="Koper"><img  src="lib/images/kaarten/Koper.png" alt="copper" title="copper"></li>
        <li id="Vloek"><img  src="lib/images/kaarten/Vloek.png" alt="curse" title="curse"></li>
    </ul>


    <div id="speler">
        <ul>
            <li><img src="lib/images/dank.png" id="dank" alt="dank" title="dank"></li>

            <li id="naamspeler"></li>
        </ul>
    </div>

    <ul class="hand">
        <li><p>Hand :</p></li>
    </ul>
    <ul class="toonKaart">
        <li></li>
    </ul>

</div>




<script type="text/javascript" src="lib/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="lib/js/scriptGame.js"></script>
</body>
</html>
