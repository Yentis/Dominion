<?php
require_once "ToonFuncties.php";
require_once "Gamepagina.php";

if (isset($_GET["jaknop"])) {
    if (empty($_GET["speler1"] && $_GET["speler2"])) {
        //misschien de hoofdmenu van spelen en afsluiten wegdoen en direct starten met de naamformulier
        startpagina();
        echo("spelers niet geset");
    } else {
        gamepagina();
    }
} else {
    startpagina();

}
require_once "htmlstaart.php";
?>
