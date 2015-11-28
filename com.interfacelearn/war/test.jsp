<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Learning</title>
    </head>
    <body>
    <form action="/selection" method="get">
		<input type="radio" name="sex" value="male" checked> Male
  <br>
  <input type="radio" name="sex" value="female"> Female<br/>
  <input type="hidden" name="id" value=${message}/>
  <input type="submit" value="Submit"/>
	</form>
     
    </body>
</html>