<?php
function startpagina()
{
    require_once "htmlkopStart.php";
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

    <form method="get" class="hide" id="naamgeving" action="dominionindex.php?actie=Gamepagina">
        <input type="button" id="vorige" value="<< vorige">
    <h3>Kies uw naam</h3>

        <label for="speler1">Speler 1:</label>
        <input type="text" id="speler1" name="speler1" maxlength="15"/>


        <label for="speler2">Speler 2:</label>
        <input type="text" id="speler2" name="speler2" maxlength="15"/>

        <div>
        <input type="submit" class="startp2" value="Spelen" name="jaknop">
        </div>
    </form>

    <?php

}





