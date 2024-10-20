<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Treno Creato con Successo</title>
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
        a {
            color: #79c7e3;
            text-decoration: none;
            margin-top: 20px;
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
    <h1>Treno modificato con successo!</h1>
    <div class="content">
        <p>Il tuo treno è stato modificato con successo!</p>
        <p>Puoi tornare alla <a href="/train-bazaar/dashboard/home">dashboard</a> o continua a modificare.</p>
        <form action="viewTrain" method="get">
 			<input type="hidden" name="idTreno" value="${idTreno}">
            <button type="submit">Continua a modificare</button>
        </form>
    </div>

</body>
</html>