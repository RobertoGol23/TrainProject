<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/logo-icon.png?v=1" type="image/png">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userPagesStyle.css?v=1.x">    
    <title>Registrazione</title>
    <style type="text/css">
    	.container {
/* 		    margin-top: -2.4%; */
			}
		body {
		    display: flex; /* Usa flexbox per centrare */
		    justify-content: center; /* Allinea orizzontalmente al centro */
		    align-items: center; /* Allinea verticalmente al centro */
		}
    </style>
</head>
<body>
	<div class="container">
	    <div style="margin: 20px; text-align: center;">
	        <h1 style="margin: 0;">  
	            <img src="${pageContext.request.contextPath}/icons/logo2.jpg" alt="Train Bazaar Logo" style="width: 225px; height: auto;">
	        </h1>
	    </div>
	    
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
	

			<label for="password">Conferma password</label>
	        <input type="password" id="conferma_password" placeholder="Conferma password" name="conferma_password" required>

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
	</div>
</body>
</html>