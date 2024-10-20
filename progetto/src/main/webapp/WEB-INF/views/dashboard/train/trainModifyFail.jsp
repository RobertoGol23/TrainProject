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
        .error {
        	color: #fc032c;
        }
    </style>
</head>
<body>

    <h1>Il tuo treno NON è stato modificato!</h1>

    <div class="content">
        <p>Si è verificato un errore durante la modifica</p>
        <p class="error"> ${error} </p>
        <p>Puoi tornare alla <a href="/train-bazaar/dashboard/home">dashboard</a> o riprovare a modificarlo</p>
        <form action="viewTrain" method="get">
        	<input type="hidden" name="idTreno" value="${idTreno}">
            <button type="submit">Riprova a modificare</button>
        </form>
    </div>

</body>
</html>