<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Utente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
        }
        .user-details {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
        }
        .user-details p {
            font-size: 18px;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h1>Dettagli Utente</h1>

    <div class="user-details">
        <p><strong>ID:</strong> ${user.id_user}</p>
        <p><strong>Nome:</strong> ${user.nome}</p>
        <p><strong>Cognome:</strong> ${user.cognome}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Wallet:</strong> ${user.wallet} €</p>
    </div>

    <a href="/users">Torna alla lista utenti</a>

</body>
</html>