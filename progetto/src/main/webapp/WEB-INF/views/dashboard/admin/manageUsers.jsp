<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.user.User" %>
<%@ page import="java.util.List" %>
<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Utenti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #8a79c7;
            text-align: center;
        }
        .content {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #8a79c7;
        }
        th {
            background-color: #79c7e3;
            color: #2e2b4f;
        }
        td {
            background-color: #6b678d;
        }
        button {
            padding: 10px;
            background-color: #79c7e3;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
            cursor: pointer;
        }
        button:hover {
            background-color: #8a79c7;
        }
    </style>
</head>
<body>
    <h1>Gestione Utenti</h1>

    <div class="content">
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
                <td><%= user.getId_User() %></td>
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

    <form action="/train-bazaar/dashboard/home" method="get">
        <button type="submit">Torna alla Dashboard</button>
    </form>
</body>
</html>