<html>
<head>
<title>Learning</title>
</head>
<body>
<center>
<h2> You answered ${score} out of 7 questions correctly</h2><br/><br/>
<img src="sokdi.jpg"/><br/><br/>
<h3>Almost done! Click to continue.</h3>
<form action="/starttest" method="get">
<input type="hidden" name="selection" value=${selection}>
<input type="hidden" name="id" value=${id}>
<input type="hidden" name="round" value="3">
<input type="hidden" name="source" value="test2">
<input type="hidden" name="nq" value="1">
  <input type="submit" value="Continue"/>
</form>
</center>
</body>
</html>