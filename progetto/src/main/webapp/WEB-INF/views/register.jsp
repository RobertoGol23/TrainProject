<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
        }
        form {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
            height: 350px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #79c7e3;
        }
        input {
            width: 94%;
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
        }
        button {
            width: 100%;
            margin-top: 25px;
            padding: 10px;
            background-color: #79c7e3;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
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

    <h1 align="center">Registrazione</h1>

    <form action="register" method="POST">
        <label for="nome">Nome</label>
	     <%
		    String nome = (String) session.getAttribute("nome");
			if (nome != null && !nome.isEmpty())
			{
				%><input type="text" id="nome" value="<%= nome %>" name="nome" required><%
				session.removeAttribute("nome");
			}
			else
			{
				%><input type="text" id="nome" placeholder="Inserisci qui il tuo nome" name="nome" required><%
			}  
		%>
	        

        <label for="cognome">Cognome</label>
        <%
		    String cognome = (String) session.getAttribute("cognome");
			if (cognome != null && !cognome.isEmpty())
			{
				%><input type="text" id="cognome" value="<%= cognome %>" name="cognome" required><%
				session.removeAttribute("cognome");
			}
			else
			{
				%><input type="text" id="cognome" placeholder="Inserisci qui il tuo cognome" name="cognome" required><%
			}  
		%>


        <label for="email">E-mail</label>
        <%
		    String email = (String) session.getAttribute("email");
			if (email != null && !email.isEmpty())
			{
				%><input type="email" id="email" value="<%= email %>" name="email" required><%
				session.removeAttribute("email");
			}
			else
			{
				%><input type="email" id="email" placeholder="Inserisci qui la tua e-mail" name="email" required><%
			}  
		%>

        <label for="password">Password</label>
        <input type="password" id="password" placeholder="Inserisci qui la tua password" name="password" required>

        <button type="submit">Registrati</button>
    </form>
    
    <%
	    String errorMessage = (String) session.getAttribute("errorMessage");
        session.removeAttribute("errorMessage");
	    if (errorMessage != null && !errorMessage.isEmpty())
	    {
			%>
        	<p align="center" style="color: red"><%= errorMessage %></p>
			<%
	    }
	%>

    <p align="center">Hai già un account? <a href="login">Login</a></p>

</body>
</html>