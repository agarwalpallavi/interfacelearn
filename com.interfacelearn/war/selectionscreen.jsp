<html>
<head>
<title>Learning</title>
</head>
<body>
<center>
<h2> You have scored in the ${total} percentile <br/>
You answered ${score} out of 10 questions correctly</h2><br/>
<h2>Do you want to compete to be on the leaderboard?</h2>
<table border=0 cellpadding=10px>
<tr><td>
<form action="/starttest" method="get">
<input type="radio" name="selection" value="leaderboard"> Yes
<br/>
<input type="radio" name="selection" value="projected"> No<br/>
<input type="hidden" name="id" value=${id}>
<input type="hidden" name="round" value=${round}>
<input type="hidden" name="source" value=${source}>
<input type="hidden" name="nq" value="1"><br/><br/>
  <input type="submit" value="Submit"/>
</form></td><td><img src="board.png" width="50%"/><br></td></tr></table>
</center>
</body>
</html>