<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="../navbar.jsp" %> <!-- con .. si va indietro di una cartella-->

<%@ page import="entity.user.User" %>
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
            text-align: center;
            margin: 0;
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
            color: #ffffff;
            transition: background-color 0.3s, transform 0.2s; /* Transizione per effetto hover */
        }

        .container-benvenuto {
            margin: 2%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .container-pulsanti {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
    </style>
</head>
<body>

    <div class="container-benvenuto">
        <h1>Benvenuto, ${user.nome}!</h1>

            <div class="content">
                <p>Questa è la tua dashboard. Qui puoi vedere i tuoi treni, acquisti e voti.</p>
            </div>
    </div>

    <div class="container-pulsanti">
        <!-- Sezione visibile a tutti gli utenti -->
        <div class="form-wrapper">
            <!-- <div class="form-container">
                <h2>Aggiungi Fondi al Tuo Wallet</h2>
                <form action="user/addFunds" method="get">
                    <button type="submit">Vai alla pagina di aggiunta fondi</button>
                </form>
            </div> -->

            <div class="form-container">
                <h2>Modifica il tuo Profilo</h2>
                <form action="user/editProfile" method="get">
                    <button type="submit">Modifica Profilo</button>
                </form>
            </div>

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
        

            <!-- Visualizzazione treni creati e acquistati -->
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
                       

        </div>

        <!-- Sezione visibile solo agli admin -->
        <% 
            User user = (User) session.getAttribute("user");
            if (user != null && user.isSuperAdmin()) { 
        %>
        <div class="form-container">
            <h2>Gestione Admin</h2>
            <form action="admin/showPurchases" method="get">
                <button type="submit">Mostra Tutti gli Acquisti</button>
            </form>
            <form action="admin/manageUsers" method="get">
                <button type="submit">Gestisci Utenti</button>
            </form>
        </div>
        <% } %>
    </div>
</body>
</html>