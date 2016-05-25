<%--
  Created by IntelliJ IDEA.
  User: Yentl-PC
  Date: 17/05/2016
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dominion</title>
    <link rel="stylesheet" href="lib/css/reset.css"/>
    <link rel="stylesheet" href="lib/css/styleStart.css"/>
</head>
<body>
<h1 class="scoretitel">Game over</h1>
<ul id="score">
    <li class="veld">Punten</li>

</ul>
<ul id="spelers">
    <li class="veld">Spelers</li>
</ul>



<form>
    <!--<input type="button" class="scorebutt" value="Opnieuw spelen">-->
    <div id="endgame">
        <a class="startp2 scorebutt"  href="gamepagina.jsp">Opnieuw spelen</a>
        <a class="startp2 scorebutt" href="index.jsp">Doorgaan</a>
    </div>
    <!-- <input type="button" class="scorebutt" value="Doorgaan">-->



</form>
<script type="text/javascript" src="lib/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="lib/js/scriptGame.js"></script>
</body>
</html>
