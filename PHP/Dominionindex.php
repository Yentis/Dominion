
<?php

require_once "ToonFuncties.php";
require_once "Gamepagina.php";

if(!isset($_GET["speler1"]) && !isset($_GET["speler2"])){
    startpagina();
}
else {
    gamepagina();
}













require_once "htmlstaart.php";
?>
