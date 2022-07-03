<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Partner Exercise</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
<script>

</script>
	<h1>${message}</h1>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="list" method="post">
    Available Players:&nbsp;
    <select name="player" id="id1">
        <c:forEach items="${playerMap}" var="player">
            <option value="playerKey">
            ${player.key}</option>
        </c:forEach>
    </select>
    <br/>
</form>
    <br/>
<form id="getPlayer" action="getPlayer" method="post">
    <label>Show player data by ID: </label>
    <input type="text" name="id">
    <button>Submit</button>
</form>
<br/>
<form id="deletePlayer" action="deletePlayer" method="post">
    <label>Delete player by ID: </label>
    <input type="text" name="id">
    <button>Submit</button>
</form>
<br/>
<form id="addCommentToPlayer" action="addCommentToPlayer" method="post">
    <label>Add comment: </label>
    <input type="text" name="data">
    <button>Submit</button>
</form>

</body>
</html>