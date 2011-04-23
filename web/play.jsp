<%@page contentType="application/xhtml+xml" pageEncoding="UTF-8"%>
<%

	if (request.getParameter("player") != null)
	{
		response.addCookie(new Cookie("click-me-player", request.getParameter("player")));
	}
	else
	{

		boolean named = false;

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("click-me-player"))
			{
				if (cookie.getValue().length() > 2)
				{					
					named = true;
				}
			}
		}
		if (!named) response.sendRedirect("/");
		return;
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<link rel="stylesheet" type="text/stylesheet" href="clickme.css" />
		<script type="text/javascript" src="prototype/prototype.js"></script>
		<script type="text/javascript">
			var score = 0;
			var totalScore = 0;
			var playerScore = null;
			
			function buttonClicked()
			{
				score++;
				totalScore++;
				if (playerScore)
				{
					try
					{
						playerScore.innerText = totalScore;
					}
					catch (e)
					{
						playerScore.innerContent = totalScore;
					}
				}
			}
			
			// Be safe against multithraded JS engines :P
			function scoreDelta()
			{
				d = score;
				score = score - d;
				return d;
			}
			
			window.onload = function()
			{
				playerScore = document.getElementById('score');
			}
			
			window.setInterval(function() {
				new Ajax.Request('/RegisterServlet', {
					parameters: { score : scoreDelta() }
				})
			}, 1000);
			
			window.setInterval(function() {new Ajax.Updater('scoreboard', '/ScoreServlet', {method: 'get'})}, 1000);			
		</script>
		<title>Click Me Game</title>
	</head>
	<body style="-webkit-user-select: none; padding-top: 100px;">
		<div style="width: 50%; float: right; text-align: center;">
			<div id="clickme" onmouseup="buttonClicked();"><h1>CLICK<br />ME!</h1></div>
			<h1 id="score">Click to begin</h1>
		</div>
		<div style="width: 50%; text-align: center;">
			<div id="scoreboard">
				<h1>Please wait...Scoreboard Loading</h1>
			</div>
		</div>
	</body>
</html>

