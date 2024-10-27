<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.user.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablesStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Gestione Utenti</title>
    <style>
 
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
/*             background-color: #6c6991; */
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
        }
        
        button, a.button {
            width: 220px;
            background-color: #f5835e;
            color: black;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            border-width: 1px;
            border-style: solid;
            border-color: white;
            text-align:center;
            text-decoration: none;
            font-size: 18px;
            display: inline-block;
            transition: background-color 0.3s, transform 0.3s; /* Transizione */
        }
        button:hover, a.button:hover {
            background-color: #f96737;
            color: #ffffff;
			transform: translateY(-2px); /* Effetto sollevato */
        }
        
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
        	padding: 0px;
        }
    </style>
</head>
<body>
    <h1>Gestione Utenti</h1>

    <div class="container">
        <p>Gestisci gli utenti iscritti e blocca/sblocca profili.</p>

        <table>
            <tr>
                <th>ID Utente</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Stato</th>
                <th>Azione</th>
            </tr>
            <%
                // Supponiamo che tu abbia una lista di utenti chiamata "users"
                List<User> users = (List<User>) request.getAttribute("users");
                for (User user : users) {
            %>
            <tr>
                <td><%= user.getId_user() %></td>
                <td><%= user.getNome() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.isBloccato() ? "Bloccato" : "Attivo" %></td>
                <td>
                    <form action="toggleBlockUser" method="post">
                        <input type="hidden" name="userId" value="<%= user.getId_user() %>" />
                        <button type="submit"><%= user.isBloccato() ? "Sblocca" : "Blocca" %></button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>

    <div align="center">
        <a class="button" style=" margin-top: 20px" href="/train-bazaar/dashboard/home" type="submit">Torna alla Dashboard</a>
    </div>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>