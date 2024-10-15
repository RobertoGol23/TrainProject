<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        form {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            max-width: 400px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input {
            width: 94%;
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
        }
        button {
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #79c7e3;
        }
        a{
            color: #ffffff;
        }
    </style>
</head>
<body>

    <h1>Login</h1>

    <form action="login" method="POST">
        <label for="email">Email</label>
        
        <%
		    String user = (String) session.getAttribute("user");
			if (user != null && !user.isEmpty())
			{
				%><input type="email" id="email" value="<%= user %>" name="email" required><%
				session.removeAttribute("user");
			}
			else
			{
				%><input type="email" id="email" placeholder="Inserisci qui il nome utente" name="email" required><%
			}  
		%>
        <label for="password">Password</label>
        <input type="password" id="password" placeholder="Inserisci qui la tua password" name="password" required>

        <button type="submit">Login</button>
    </form>

    <%
	    String errorMessage = (String) session.getAttribute("errorMessage");
        session.removeAttribute("errorMessage");
	    if (errorMessage != null && !errorMessage.isEmpty())
	    {
			%>
        	<p style="color: red"><%= errorMessage %></p>
			<%
	    }
	%>

    <p>Non hai un account? <a href="register">Registrati</a></p>

</body>
</html>