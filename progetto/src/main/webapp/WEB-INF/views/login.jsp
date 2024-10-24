<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/logo-icon.png?v=1" type="image/png">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userPagesStyle.css?v=1.x">
    <title>Login</title>

    <style>  
		.container {
             margin-top: -4%;
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

        <h1 align="center">Login</h1>

        <form action="login" method="POST">
            <label for="email">Email</label>
            <%
                String email = (String) session.getAttribute("email");
                if (email != null && !email.isEmpty()) {
                    %><input type="email" id="email" value="<%= email %>" name="email" required><%
                    session.removeAttribute("email");
                } else {
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
            if (errorMessage != null && !errorMessage.isEmpty()) {
                %>
                <p align="center" style="color: red"><%= errorMessage %></p>
                <%
            }
        %>

        <p align="center">Non hai un account? <a href="${pageContext.request.contextPath}/register">Registrati</a></p>

    </div>
    

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->

</body>
</html>
