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
            display: flex; /* Usa flexbox per centrare */
            justify-content: center; /* Allinea orizzontalmente al centro */
            align-items: center; /* Allinea verticalmente al centro */
            height: 100vh; /* Altezza della pagina */
            margin: 0; /* Rimuovi margine di default */
        }
        h1 {
            color: #e1418b;
        }
        form {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
            height: auto; /* Permette al contenuto di determinare l'altezza */
            margin: 0 auto; /* Centra orizzontalmente */
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #e1418b;
            font-weight: bold;
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
            background-color: #f5835e;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
            cursor: pointer;
        }
        button:hover {
            background-color: #f96737;
            color: #ffffff;
        }
        a {
            color: #ffffff;
        }


        .container {
            width: 25%;
            border: 3px solid #db7a4e; /* Colore e spessore del bordo */
            border-radius: 10px;
            padding: 20px; /* Spaziatura interna */
            margin-top: -12%;
        }
    </style>
</head>
<body>

    <div class="container">

        <div style="margin: 20px; text-align: center;">
            <h1 style="margin: 0;">  
                <img src="${pageContext.request.contextPath}/images/logo2.jpg" alt="Train Bazaar Logo" style="width: 225px; height: auto;">
            </h1>
        </div>

        <h1 align="center" style="font-size: 3.0rem; margin-bottom: 5px;">Login</h1>

        <form action="login" method="POST" style="margin-top: 0;">
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

            <button type="submit"><b>Login</b></button>
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
    

</body>
</html>
