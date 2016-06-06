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
<h1 class="scoretitel">Scorebord</h1>
<ul id="score">

    <li class="veld">Spelers</li>
    <li class="veld">Punten</li>

</ul>
<ul id="spelers">
</ul>

<ul id="punten">
</ul>

<form>
    <div id="endgame">
        <a id="restartspel" class="startp2 scorebutt">Opnieuw spelen</a>
        <a class="startp2 scorebutt" href="index.jsp">Doorgaan</a>
    </div>
</form>
<script type="text/javascript" src="lib/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="lib/js/scriptScore.js"></script>
</body>
</html>
