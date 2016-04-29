<?php
function startpagina()
{
    ?>

    <audio controls autoplay loop id="muziek">
        <source src="music/RuneScape%20Login%20Themes%202001-2014.mp3" type="audio/mpeg">
    </audio>
    <button id="toggleMuziek" class="kleineKnop"></button>
    <button id="spelregelsKnop" class="kleineKnop"></button>
    <div id="spelRegels">
        <button id="volgendePagina" class="kleineKnop">>></button>
        <ul class="carousel">

        </ul>


    </div>
    <h1>Dominion</h1>

    <form id="start">
        <button type="submit" class="groteKnop" id="spelen"> Spelen</button>

        <button type="button" class="groteKnop"> Afsluiten</button>
    </form>

    <form method="POST" class="hide" id="naamgeving">
        <button id="vorige"><strong><<</strong>Vorige</button>
    <h3>Kies uw naam</h3>
    <div>
        <label for="speler1">Speler 1:</label>
        <input type="text" id="speler1" name="speler1"/>
    </div>
    <div>
        <label for="speler2">Speler 2:</label>
        <input type="text" id="speler2" name="speler2"/>
    </div>
    <button type="submit" class="startp2">Starten</button>
    </form>

    <?php
}

?>




