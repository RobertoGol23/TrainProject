<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="entity.user.User" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Wallet</title>
    <style>
        body {
            font-family: 'Segoe UI', 'Helvetica Neue', sans-serif; /* Font più formale */
            background-color: #2e2b4f; /* Colore di sfondo */
            color: #ffffff; /* Colore del testo */
            margin: 0; /* Rimuovi margini di default */
        }

        h1 {
            color: #e1418b; /* Colore dei titoli */
            text-align: center;
            margin: 0;
            font-size: 1.5rem; /* Dimensione del titolo */
            font-weight: bold; /* Grassetto */
        }

        h2 {
            color: ivory; /* Colore per h2 */
            height: 80px;
        }

        .container {
            display: flex; /* Usa Flexbox per disporre i due div */
            justify-content: space-between; /* Spazia i contenuti */
            padding: 5%; /* Padding per il contenitore principale */
            gap: 20px; /* Spaziatura tra i div */
        }

        .content {
            flex: 1; /* Espandi il contenuto */
            display: flex;
            flex-direction: column; /* Imposta la disposizione verticale per il contenuto */
            width: 70%;
        }

        .showSaldo {
            background-color: #49456d; /* Colore di sfondo per il saldo */
            padding: 30px; /* Padding interno */
            border-radius: 10px; /* Angoli arrotondati */
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5); /* Ombra per effetto */
            text-align: center; /* Allinea il testo al centro */
            margin-bottom: 20px; /* Spazio sotto il saldo */
        }

        .saldo {
            font-size: 4rem; /* Dimensione grande per il saldo */
            color: #f5835e; /* Colore accattivante per il saldo */
            font-weight: bold; /* Grassetto */
        }

        .reload-button {
            padding: 10px 20px; /* Padding del pulsante */
            background-color: #f5835e; /* Colore del pulsante */
            border: none;
            border-radius: 5px; /* Angoli arrotondati */
            color: #2e2b4f; /* Colore del testo del pulsante */
            cursor: pointer;
            transition: background-color 0.3s; /* Transizione per hover */
            font-size: 16px; /* Dimensione del testo */
            margin-top: 10px; /* Spazio sopra il pulsante */
        }

        .reload-button:hover {
            background-color: #f96737; /* Colore al passaggio del mouse */
            color: #ffffff; /* Colore del testo al passaggio del mouse */
        }

        .transazioni {
            margin-top: 20px; /* Distanza dal div sopra */
            background-color: #49456d; /* Colore per differenziare */
            padding: 20px; /* Padding */
            border-radius: 10px; /* Angoli arrotondati */
            color: ivory; /* Colore del testo */
        }

        .container-aggiungiFondi {
            display: none; /* Nascondi il div inizialmente */
            justify-content: center;
            align-items: center;
            margin-top: 10px;
            opacity: 0; /* Nascondi inizialmente */
            transition: opacity 0.5s; /* Effetto di transizione */
        }

        .form-container {
            margin: 20px;
            padding: 20px;
            background-color: #49456d; /* Colore di sfondo */
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5); /* Ombra per effetto */
        }

        input[type="number"] {
            padding: 10px;
            border-radius: 5px;
            border: none;
            width: calc(100% - 20px); /* Larghezza totale meno il padding */
            margin-bottom: 10px; /* Margine inferiore */
            font-size: 16px; /* Dimensione del testo */
        }

        button {
            background-color: #f5835e; /* Colore del pulsante */
            color: #2e2b4f; /* Colore del testo del pulsante */
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s; /* Transizione */
        }

        button:hover {
            background-color: #f96737; /* Colore al passaggio del mouse */
            color: #ffffff; /* Colore del testo al passaggio del mouse */
            transform: translateY(-2px); /* Effetto sollevato */
        }

        .right-content {
            width: 30%; /* Larghezza del div destro */
            padding: 20px; /* Padding per il div destro */
            background-color: #3b3a5d; /* Colore di sfondo per il contenuto a destra */
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
            <h1>Saldo Train-Bazaar</h1>
            <div class="saldo" id="formatted-saldo"></div>
            <h1>Disponibile</h1>
            <button class="reload-button" onclick="showDiv()">Ricarica</button>
        </div>

        <div class="container-aggiungiFondi" id="container-aggiungiFondi">
            <div class="form-container">
                <h1 class="scritta-aggiungiFondi">Aggiungi Fondi al Tuo Wallet</h1>
                <form action="ricarica" method="post" onsubmit="showSuccessMessage();" style="margin-top: 4%;">
                    <input type="number" id="amount" name="amount" required min="0" step="100" placeholder="Importo da aggiungere">
                    <button type="submit">Aggiungi Fondi</button>
                </form>
            </div>
        </div>

        <div class="transazioni">
            <h1>Attività recenti</h1>
            <!-- Aggiungi qui le transazioni recenti -->
        </div>
    </div>

    <div class="right-content">
        <h2>Contenuto Aggiuntivo</h2>
        <p>Questo è un esempio di contenuto aggiuntivo che apparirà a destra.</p>
    </div>
</div>

<script>
    // Funzione per formattare il saldo con punto ogni tre cifre
    function formatSaldo(saldo) {
        return saldo.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + " €";
    }

    // Ottieni il saldo dal server e formatta
    const saldo = ${user.wallet}; // Assicurati che questo valore sia un numero
    document.getElementById('formatted-saldo').innerText = formatSaldo(saldo);

    function showDiv() {
        const myDiv = document.getElementById('container-aggiungiFondi');
        if (myDiv.style.display === 'flex') {
            myDiv.style.opacity = 0; // Rendi invisibile con transizione
            setTimeout(() => {
                myDiv.style.display = 'none';
            }, 500); // Cambiato il timeout per l'animazione
        } else {
            myDiv.style.display = 'flex';
            setTimeout(() => {
                myDiv.style.opacity = 1;
            }, 50);
        }
    }
</script>


<!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->

</body>
</html>
