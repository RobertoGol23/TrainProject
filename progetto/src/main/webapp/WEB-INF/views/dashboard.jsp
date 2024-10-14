<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
        }
        .content {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h1>Benvenuto, ${user.nome}!</h1>

    <div class="content">
        <p>Questa è la tua dashboard. Qui puoi vedere i tuoi treni, acquisti e voti.</p>
    </div>

    <a href="/logout">Logout</a>

</body>
</html>