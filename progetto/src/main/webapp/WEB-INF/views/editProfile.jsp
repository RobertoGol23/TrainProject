<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Profilo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
        }
        .form-container {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
            margin: 0 auto;
        }
        .dashboard {
        	margin-top: 50px;
            padding: 20px;
            border-radius: 10px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #79c7e3;
        }
        input {
            width: 94%;
            padding: 8px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button {
            width: 100%;
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
        a {
            color: #79c7e3;
            text-decoration: none;
        }
        .delete-section {
        	width: 300px;
            margin: 0 auto;
            margin-top: 250px;
            text-align: center;
        }
        .delete-section button {
            background-color: #e37479;
            color: #ffffff;
        }
        .delete-section button:hover {
            background-color: #c74f53;
        }
    </style>
</head>
<body>

    <h1 align="center">Modifica Profilo</h1>

    <div class="form-container">
        <form action="updateProfile" method="post">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" value="${user.nome}" required>

            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" value="${user.cognome}" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${user.email}" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Aggiorna Profilo</button>
        </form>
        
        
    </div>
    

    <div class="delete-section">
        <h2>Elimina il tuo account</h2>
        <form action="deleteUser" method="post">
            <button type="submit" onclick="return confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')">
                Cancella il mio account
            </button>
        </form>
	</div>
    <div align="center" class="dashboard"><a href="dashboard">Torna alla Dashboard</a></div>

</body>
</html>