<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- TODO: Controllare sti import -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">

	<title>Aggiungi Fondi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            margin: 0; /* Remove default margin */
        }
    
        .container {
            display: flex; /* Use flexbox */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            margin-top: 80px; /* Full viewport height */
            flex-direction: column; /* Stack items vertically */
        }
    
        h1 {
            color: #8a79c7;
        }
    
        /* Stile per la carta di credito */
        .credit-card {
            width: 750px; 
            height: 350px; 
            background-color: #49456d;
            border-radius: 10px;
            padding: 20px; 
            position: relative; 
            margin-bottom: 40px; /* Space below the credit card */
            display: flex;
            flex-direction: column; /* Stack items vertically */
            justify-content: center; /* Center items vertically */
            align-items: center; /* Center items horizontally */
        }
    
        .credit-card h1 {
            margin: 0; 
            padding: 0; 
            font-size: 3.5rem; 
        }
        .credit-card h4 {
            color: #8a79c7;
            margin: 0; 
            padding: 0; 
            font-size: 1.5rem;
        }
        .credit-card h6 {
            margin: 0; 
            padding: 0; 
        }
    
        /* Stile del pulsante della carta di credito */
        .reload-button {
            position: absolute; 
            bottom: 15px; 
            right: 15px; 
            width: 100px;
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        
        .reload-button:hover {
            background-color: #79c7e3;
            transition: background-color 0.3s; /* Smooth background color change */
        }

        /* Container del form aggiungi fondi */
        .container-aggiungiFondi {
            display: none; /* Nascondi il div inizialmente */
            justify-content: center;
            align-items: center;
            margin-top: 20px;
            opacity: 0; /* Nascondi inizialmente */
            transition: opacity 0.5s; /* Effetto di transizione */
        }

        /* Stile del form per aggiungere fondi */
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

    </style>
    
</head>
<body>

    <div class="container">

        <div class="credit-card">
            <h4> Saldo Train-Bazaar </h4>
            <h1>${user.wallet}€ EUR</h1>
            <h6> Disponibile </h6>
            <button class="reload-button" onclick="showDiv()">Ricarica</button>
        </div>
    

        <div class="container-aggiungiFondi" id="container-aggiungiFondi">
            <div class="form-container">
                <h1>Aggiungi Fondi al Tuo Wallet</h1>

                <form action="ricarica" method="post" onsubmit="showSuccessMessage();">
                    <label for="amount">Importo da aggiungere:</label>
                    <input type="number" id="amount" name="amount" required min="0" step="100" value="">
                    <button type="submit">Aggiungi Fondi</button>
                </form>
            </div>
        </div>

    </div>

    <script>
        function showDiv() {
            const myDiv = document.getElementById('container-aggiungiFondi');
            if (myDiv.style.display === 'flex') {
                myDiv.style.opacity = 0; // Rendi invisibile con transizione
                setTimeout(() => {
                    myDiv.style.display = 'none';
                }, 500);
            } else {
                myDiv.style.display = 'flex';
                setTimeout(() => {
                    myDiv.style.opacity = 1;
                }, 10);
            }
        }
    </script>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>
