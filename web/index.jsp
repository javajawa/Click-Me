<%-- 
    Document   : index
    Created on : 22-Apr-2011, 22:17:33
    Author     : Benedict
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Click Me Game</title>
	</head>
	<body>
		<h1>Click Me!</h1>
		<form action="play.jsp" method="post">
			<div>
				Player Name: <input name="player" value="<% %>" />
			</div>
			<div>
				<input type="submit" value="Play!" />
			</div>
		</form>
	</body>
</html>
