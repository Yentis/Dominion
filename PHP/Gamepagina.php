<?php

function gamepagina(){
    require_once "htmlkopGame.php";
    ?>

    <h2>Speler 1: <?php echo  $_GET["speler1"]?></h2>
    <h2>Speler 2: <?php echo  $_GET["speler2"]?></h2>

    <h1>Het is <?php echo  $_GET["speler1"]?> zijn beurt</h1>  <!--moet nog aangepast worden met java-->
    <ul id="Overwinningskaart">
        <li></li>
        <li></li>
        <li></li>
        <!--voeg hier de veldkaartenkaarten toe-->
    </ul> <ul id="Actiekaart">
        <!--voeg hier de veldkaartenkaarten toe-->
    </ul>
    <ul id="Geldkaart">
        <!--voeg hier de veldkaartenkaarten toe-->
    </ul>


    <?php
}


/**
 * Created by PhpStorm.
 * User: Renzie
 * Date: 29/04/2016
 * Time: 14:53
 */
