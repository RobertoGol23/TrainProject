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
        .form-wrapper {
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        .form-container {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
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
    </style>
</head>
<body>

    <h1>Benvenuto, ${user.nome}!</h1>

    <div class="content">
        <p>Questa è la tua dashboard. Qui puoi vedere i tuoi treni, acquisti e voti.</p>
    </div>

    <!-- Involucro flessibile per i form container -->
    <div class="form-wrapper">
        <div class="form-container">
            <h2>Aggiungi Fondi al Tuo Wallet</h2>
            <form action="user/addFunds" method="get">
                <button type="submit">Vai alla pagina di aggiunta fondi</button>
            </form>
        </div>

        <div class="form-container">
            <h2>Modifica il tuo Profilo</h2>
            <form action="user/editProfile" method="get">
                <button type="submit">Modifica Profilo</button>
            </form>
        </div>

        <!-- Nuovo pulsante per creare un treno -->
        <div class="form-container">
            <h2>Crea un Nuovo Treno con sigla</h2>
            <form action="train/createTrain" method="get">
                <button type="submit">Crea Treno</button>
            </form>
        </div>
        
        <div class="form-container">
            <h2>Crea un Nuovo Treno dinamico</h2>
            <form action="train/creaTrenoProva" method="get">
                <button type="submit">Crea Treno</button>
            </form>
        </div>
    </div>
    
    <div class="form-container">
    	<h2>I Miei Treni Creati</h2>
   		<form action="user/viewTrains" method="get">
        	<button type="submit">Visualizza Treni</button>
    	</form>
	</div>
	<div class="form-container">
    	<h2>I Miei Treni Comprati</h2>
   		<form action="user/viewPurchasedTrains" method="get">
        	<button type="submit">Visualizza Treni</button>
    	</form>
	</div>
    

    <a href="/logout">Logout</a>

</body>
</html>