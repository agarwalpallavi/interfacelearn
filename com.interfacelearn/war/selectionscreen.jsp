<html>
<head>
<title>Learning</title>
</head>
<body>
<center>
<h2> You have scored in the ${total} percentile <br/>
You answered ${score} out of 10 questions correctly</h2><br/>
Do you want to compete to be on the leaderboard?<br/><br/><br/>
<form action="/starttest" method="get">
<input type="radio" name="selection" value="leaderboard"> Yes
<br/>
<input type="radio" name="selection" value="projected"> No<br/>
<input type="hidden" name="id" value=${id}>
  <input type="submit" value="Submit"/>
</form>
</center>
</body>
</html>