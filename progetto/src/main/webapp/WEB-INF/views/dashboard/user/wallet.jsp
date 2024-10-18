<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Fondi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
        }

        .container-saldo{
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            
            margin-top: 20px;            
            background-color: #49456d;
            border-radius: 10px;
        }

        .form-container {
            margin-top: 20px;
            padding: 20px;
            background-color: #49456d;
            border-radius: 10px;
        }
        label {
            margin-right: 10px;
        }
        input[type="number"] {
            padding: 10px;
            border-radius: 5px;
            border: none;
        }
        button {
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #79c7e3;
        }
        .error {
            color: red;
        }

        .container-aggiungiFondi {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }



    </style>
</head>
<body>

    <div class="container-saldo">
        <h1>Saldo: ${wallet.balance}€</h1>
    </div>

    <div class="container-aggiungiFondi">
        <div class="form-container">
            <h1>Aggiungi Fondi al Tuo Wallet</h1>

            <form action="addFunds" method="post">
                <label for="amount">Importo da aggiungere:</label>
                <input type="number" id="amount" name="amount" required min="0" step="100">
                <button type="submit">Aggiungi Fondi</button>
            </form>


        </div>
    </div>
</body>
</html>