<?php
function startpagina()
{
    require_once "htmlkopstartpagina.php";
    ?>


    <audio controls autoplay loop id="muziek">
        <source src="music/RuneScape%20Login%20Themes%202001-2014.mp3" type="audio/mpeg">
    </audio>
    <input type="button" id="toggleMuziek" class="kleineKnop">
    <input type="button" id="spelregelsKnop" class="kleineKnop" value="?">
    <div id="spelRegels">
        <input type="button" id="volgendePagina" class="kleineKnop" value=">>">
        <ul class="carousel">

        </ul>


    </div>


    <form id="start">
        <h1>Dominion</h1>
        <input type="submit" class="groteKnop" id="spelen" value="Spelen">

        <input type="button" class="groteKnop" value="Aflsuiten">
    </form>

    <!--bovenstaande form in commentaar want anders ist fucked up-->

    <form method="post" class="hide" id="naamgeving" action="dominionindex.php?actie=GamePagina">
        <input type="button" id="vorige" value="<< vorige">
        <h3>Kies uw naam</h3>

        <label for="speler1">Speler 1:</label>
        <input type="text" id="speler1" name="speler1" maxlength="15"/>


        <label for="speler2">Speler 2:</label>
        <input type="text" id="speler2" name="speler2" maxlength="15"/>

        <div>
            <input type="submit" id="speelSpel" class="startp2" value="Spelen" name="jaknop">
        </div>
    </form>

    <?php
}



function gamepagina(){
    require_once "htmlkopGame.php";
    ?>

    <h2>Speler 1: <?php echo  $_POST["speler1"]?></h2>
    <h2>Speler 2: <?php echo  $_POST["speler2"]?></h2>

    <h1>Het is <?php echo  $_POST["speler1"]?> zijn beurt</h1>  <!--moet nog aangepast worden met java-->
    <ul id="Overwinningskaart" class="veld">
        <li><img src="lib/Kaarten/province.jpg"> </li>
        <li><img src="lib/Kaarten/duchy.jpg"></li>
        <li><img src="lib/Kaarten/estate.jpg"></li>
        <!--voeg hier de veldkaartenkaarten toe-->
    </ul>
    <ul id="Actiekaart" class="veld">

        <!--voeg hier de veldkaartenkaarten toe-->
    </ul>
    <ul id="Geldkaart" class="veld">
        <!--voeg hier de veldkaartenkaarten toe-->
    </ul>

    <ul id="hand">

    </ul>
    <?php
}


