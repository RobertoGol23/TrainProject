<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.acquisto.Acquisto" %>
<%@ page import="java.util.List" %>
<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Visualizza Acquisti</title>
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
    <h1>Lista di tutti gli Acquisti</h1>

    <div class="content">
        <p>Visualizza tutti gli acquisti effettuati dagli utenti.</p>

        <table>
            <tr>
                <th>ID Utente</th>
                <th>ID Treno</th>
                <th>Data Acquisto</th>
                <th>ID Acquisto</th>
            </tr>
            <%
                // Supponiamo che tu abbia una lista di acquisti chiamata "purchases"
                List<Acquisto> purchases = (List<Acquisto>) request.getAttribute("purchases");
                for (Acquisto acquisto : purchases) {
            %>
            <tr>
                <td><%= acquisto.getUser().getId_user() %></td>
                <td><%= acquisto.getTreno().getId() %></td>
                <td><%= acquisto.getDataAcquisto() %></td>
                <td><%= acquisto.getId() %></td>
            </tr>
            <% } %>
        </table>
    </div>

    <form action="/train-bazaar/dashboard/home" method="get">
        <button type="submit">Torna alla Dashboard</button>
    </form>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>