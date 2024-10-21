<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="entity.user.User" %>
<%@ include file="../../navbar.jsp" %> <!-- con .. si va indietro di una cartella-->

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wallet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }

        h1 {
            color: #8a79c7;
            text-align: center;
            margin: 0; /* Rimuovi il margine di default */
        }

        .container {
            display: flex; /* Usa Flexbox per disporre i due div */
            justify-content: space-between; /* Spazia i contenuti */
            padding: 5%; /* Padding per il contenitore principale */
        }

        .content {
            display: flex;
            flex-direction: column; /* Imposta la disposizione verticale per il contenuto */
            width: 70%; /* Riduci la larghezza del contenitore principale */
        }

        .showSaldo {
            background-color: #49456d;
            padding: 1%; /* Circa 20px, a seconda della larghezza dello schermo */
            border-radius: 10px;
            height: auto; /* Altezza automatica in base al contenuto */
            display: flex;
            flex-direction: column; /* Stack items vertically */
            justify-content: space-between; /* Spazia gli elementi verticalmente */
            align-items: flex-start; /* Allinea gli elementi a sinistra */
            position: relative; /* Necessario per posizionare il pulsante in basso a destra */
        }

        .reload-button {
            width: 20%; /* Riduci la larghezza del pulsante */
            padding: 5px; /* Riduci il padding */
            position: absolute; /* Posiziona il pulsante */
            bottom: 10px; /* Posiziona il pulsante in basso */
            right: 5px; /* Posiziona il pulsante a destra */
            margin-right: 1%;
        }

        .transazioni {
            margin-top: 2%; /* Distanza dal div sopra */
            background-color: #49456d; /* Colore diverso per differenziare */
            padding: 2%; /* Circa 20px, a seconda della larghezza dello schermo */
            border-radius: 10px; /* Aggiungi bordi arrotondati */
            height: auto; /* Altezza automatica in base al contenuto */
        }

        .container-aggiungiFondi {
            display: none; /* Nascondi il div inizialmente */
            justify-content: center;
            align-items: center;
            margin-top: 10px;

            opacity: 0; /* Nascondi inizialmente */
            transition: opacity 0.5s; /* Effetto di transizione */
        }

        .scritta-aggiungiFondi {
            font-size: 20px;

            color: #8a79c7;
            text-align: center;
            margin: 0; /* Rimuovi il margine di default */
        }

        .form-container {
            margin: 20px;
            padding: 20px;
            background-color: #49456d;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Aggiungi ombra per effetto */
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
            transition: background-color 0.3s;
        }
        .error {
            color: red;
        }

        .right-content {
            width: 30%; /* Larghezza del div destro */
            padding: 20px; /* Padding per il div destro */
            background-color: #3b3a5d; /* Cambia il colore di sfondo se necessario */
            color: #ffffff; /* Colore del testo */
            border-radius: 10px; /* Bordi arrotondati */
            margin-left: 20px; /* Spazio tra i due div */
        }
    </style>
</head>
<body>

<div class="container">
    <div class="content">
        <div class="showSaldo">
            <h1 style="margin-top: 2%; margin-bottom: 3%; margin-left: 1%; font-size: 1.5rem;">Saldo Train-Bazaar</h1>
            <h1 style="font-size: 2.5rem; margin: 0;  margin-left: 1%;">${user.wallet} €</h1>
            <h1 style="font-size: 1.0rem; margin-top: 1%;  margin-left: 1%;">Disponibile</h1>
            <button class="reload-button" onclick="showDiv()">Ricarica</button>
        </div>

        <div class="container-aggiungiFondi" id="container-aggiungiFondi">
            <div class="form-container">

                <h1 class="scritta-aggiungiFondi">Aggiungi Fondi al Tuo Wallet</h1>
                <form action="ricarica" method="post" onsubmit="showSuccessMessage();"" style=" margin-top: 4%;">
                    <input type="number" id="amount" name="amount" required min="0" step="100" placeholder="Importo da aggiungere" value="">
                    <button type="submit">Aggiungi Fondi</button>
                </form>

            </div>
        </div>

        <div class="transazioni">
            <h1 style="font-size: 1.0rem;">Attività recenti</h1>
        </div>
    </div>

    <div class="right-content">
        <h2>Contenuto Aggiuntivo</h2>
        <p>Questo è un esempio di contenuto aggiuntivo che apparirà a destra.</p>
    </div>
</div>

<script>
    function showDiv() {
        const myDiv = document.getElementById('container-aggiungiFondi');
        if (myDiv.style.display === 'flex') {
            myDiv.style.opacity = 0; // Rendi invisibile con transizione
            setTimeout(() => {
                myDiv.style.display = 'none';
            }, 50);
        } else {
            myDiv.style.display = 'flex';
            setTimeout(() => {
                myDiv.style.opacity = 1;
            }, 50);
        }
    }
</script>

</body>
</html>
