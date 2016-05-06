<?php
require_once "ToonFuncties.php";
require_once "Gamepagina.php";

if (isset($_POST["jaknop"])) {
    if (empty($_POST["speler1"] && $_POST["speler2"])) {
        //misschien de hoofdmenu van spelen en afsluiten wegdoen en direct starten met de naamformulier

        echo("spelers niet geset");
    } else {
        gamepagina();
    }
} else {
    startpagina();
}
require_once "htmlstaart.php";
?>
