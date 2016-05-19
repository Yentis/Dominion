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
<body onload="init()">
<div id="wrapper">


    <ul class="overwinningskaarten">
        <li><img src="lib/images/kaarten/overwinningskaarten/provincePS.jpg" alt="province" title="province"/></li>
        <li><img src="lib/images/kaarten/overwinningskaarten/duchyPS.jpg" alt="duchy" title="duchy::"/></li>
        <li><img src="lib/images/kaarten/overwinningskaarten/estatePS.jpg" alt="estate" title="estate"/></li>
    </ul>


    <ul class="stapel">
        <li><img src="lib/images/kaarten/actiekaarten/dief.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/achterkant.jpg" alt="avonturier" title="avonturier"/></li>
    </ul>


    <ul class="actiekaarten">
        <li><img src="lib/images/kaarten/actiekaarten/avonturier.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/bibliotheek.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/bureaucraat.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/dief.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/dorp.jpg" alt="avonturier" title="avonturier"/></li>

        <li><span id="linebreaker"> </span></li>

        <li><img src="lib/images/kaarten/actiekaarten/feest.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/festival.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/geldverlener.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/gracht.jpg" alt="avonturier" title="avonturier"/></li>
        <li><img src="lib/images/kaarten/actiekaarten/houthakker.jpg" alt="avonturier" title="avonturier"/></li>
    </ul>



    <div id="buttons">
        <input type="button" value="Gooi alle geldkaarten">
        <input type="button" value="Eindig je beurt">
    </div>
    <div id="log">
        <h2>Een cursekaart werd toegevoegd aan je deck</h2>
    </div>

    <div id="persoongegevens">
        <ul>

            <li id="acties">|ACTIES:</li>
            <li id="buys">|BUYS:</li>
            <li id="geld">|GELD:</li>
        </ul>

    </div>

    <ul class="kaartOpVeld">
        <li><img src="lib/images/kaarten/geldkaarten/gold.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/geldkaarten/gold.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/geldkaarten/gold.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/geldkaarten/gold.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/geldkaarten/gold.jpg" alt="gold" title="gold"></li>
    </ul>


    <ul class="geldcurse">
        <li><img src="lib/images/kaarten/geldkaarten/gold.jpg" alt="gold" title="gold"></li>
        <li><img src="lib/images/kaarten/geldkaarten/silver.jpg" alt="silver" title="silver"></li>
        <li><img src="lib/images/kaarten/geldkaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/overwinningskaarten/curse.jpg" alt="curse" title="curse"></li>
    </ul>


    <div id="speler">
        <ul>
            <li><img src="lib/images/dank.png" id="dank" alt="dank" title="dank"></li>

            <li id="naamspeler"></li>
        </ul>
    </div>

    <ul class="hand">
        <li><p>Hand :</p></li>
        <li><img src="lib/images/kaarten/geldkaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/geldkaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/geldkaarten/copper.jpg" alt="copper" title="copper"></li>
        <li><img src="lib/images/kaarten/overwinningskaarten/estate.jpg" alt="estate" title="estate"></li>
        <li><img src="lib/images/kaarten/geldkaarten/copper.jpg" alt="copper" title="copper"></li>
    </ul>
    <ul class="toonKaart">
        <li></li>
    </ul>

</div>



<script type="text/javascript" src="lib/js/kaartGenereren.js"></script>
<script type="text/javascript" src="lib/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="lib/js/scriptGame.js"></script>
</body>
</html>
