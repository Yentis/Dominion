<%--
  Created by IntelliJ IDEA.
  User: Yentl-PC
  Date: 17/05/2016
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="UTF-8">
  <title>Dominion</title>
  <link rel="stylesheet" href="lib/css/reset.css"/>
  <link rel="stylesheet" href="lib/css/styleStart.css"/>
</head>
<body>
<audio controls autoplay loop id="muziek">
  <source src="music/RuneScape%20Login%20Themes%202001-2014.mp3" type="audio/mpeg">
</audio>
<input type="button" id="toggleMuziek" class="kleineKnop">
<input type="button" id="spelregelsKnop" class="kleineKnop" value="?">
<div id="spelRegels">
  <input type="button" id="volgendePagina" class="kleineKnop" value=">>">
  <input type="button" id="vorigePagina" class="kleineKnop" value="<<">
  <input type="button" id="sluitSpelregels" class="kleineKnop" value="X">
  <ul class="carousel">

  </ul>


</div>
<form id="start">
  <h1>Dominion</h1>
  <input type="submit" class="groteKnop" id="spelen" value="Spelen">

  <input type="button" class="groteKnop" value="Aflsuiten">
</form>

<form action="NaamServlet" method="post" class="hide" id="naamgeving">
  <input type="button" id="vorige" value="<< vorige">
  <h3>Kies uw naam</h3>

  <label for="speler1">Speler 1:</label>
  <input type="text" id="speler1" name="speler1" maxlength="15"/>


  <label for="speler2">Speler 2:</label>
  <input type="text" id="speler2" name="speler2" maxlength="15"/>

  <div>
    <!--<input type="submit" id="speelSpel" class="startp2" value="Spelen" name="jaknop">-->
    <input type="submit" value="Beginnen">
  </div>
</form>

<script type="text/javascript" src="lib/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="lib/js/script.js"></script>
</body>
</html>
