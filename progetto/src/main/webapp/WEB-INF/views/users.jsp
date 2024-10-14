<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista Utenti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f; /* Viola scuro */
            color: #ffffff;
        }
        h1 {
            color: #8a79c7; /* Viola chiaro */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ffffff;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #49456d; /* Viola intermedio */
        }
        a {
            color: #79c7e3; /* Blu chiaro */
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <h1>Lista Utenti</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Azioni</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id_user}</td>
                <td>${user.nome}</td>
                <td>${user.cognome}</td>
                <td>${user.email}</td>
                <td>
                    <a href="/users/${user.id_user}">Dettagli</a> |
                    <a href="/users/edit/${user.id_user}">Modifica</a> |
                    <form action="/users/delete/${user.id_user}" method="POST" style="display:inline;">
                        <button type="submit">Elimina</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>