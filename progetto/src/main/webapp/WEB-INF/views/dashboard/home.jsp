<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../navbar.jsp" %> <!-- con .. si va indietro di una cartella-->

<%@ page import="entity.user.User" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/logo-icon.png?v=1" type="image/png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/trainAnimatioStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=2.x">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
        @charset "UTF-8";
        body {
            background-color: #2e2b4f;
            color: #ffffff;
            font-family: Arial, sans-serif;
        }
        
        .hidden {
            display: none; /* Questa classe nasconde gli elementi */
        }
        
        
        	h1 {
                margin-top: 30px;
                font-size: 2.5rem; 
                color: #e1418b;
                text-align: center;
                font-weight: bold;
            }
            h2 {
        /*         color: #e1418b; */
                color: ivory;
                height: 80px;
            }
            
            h2.card {
            	margin-top: -7%;
            	margin-bottom: 4%;
            	text-align: center;
            }
            
            .content {
                background-color: #49456d;
                padding: 20px;
                border-radius: 10px;
                margin-bottom: 20px;
                text-align: center;
                font-size: 20px;
                color: ivory;
                width: 80%;
                margin: auto;
                margin-bottom: 150px;
            }
            
            .form-wrapper {
                display: flex;
                justify-content: center;
/*                 gap: 20px; */
            }
            .form-container {
                display: block;
                padding: 20px;
	            width: 320px;
                margin: 10px;
                flex: 1 0 320px; /* Dimensione minima dei contenitori */
                
            }
            .form-container:hover {
                transform: translateY(-5px); /* Effetto sollevato al passaggio del mouse */
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3); /* Ombra più profonda */
            }
            .form-button {
                display: flex;
                flex-wrap: wrap;
                justify-content: center; /* Centratura dei pulsanti */
                margin: 0px;
                margin-left: 0px;
        		text-decoration: none;
                vertical-align: bottom;
            }
           
         .container-benvenuto {
	            margin: 2%;
	            display: center;
	            flex-direction: column;
	            align-items: center;
                text-align: center;
                padding: 20px;
                background: rgba(255, 255, 255, 0.1);
                border-radius: 10px; /* Angoli arrotondati */
            }

        .card {
            width: 30%; /* Larghezza delle card */
            height: 40%; /* Altezza delle card */
            background-color: #2e2b4f;
            color: white;
            display: flex;
            flex-direction: column; /* Disposizione verticale */
            align-items: center; /* Centro orizzontale */
            justify-content: flex-start; /* Allinea gli elementi all'inizio */
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
            transition: transform 0.3s;
            cursor: pointer;
            padding: 20px; /* Aggiungi un padding per evitare che il testo tocchi i bordi */

            transform: scale(0.8); /* Scala al 80% della dimensione originale */
        }
        
        .card:hover {
            transform: scale(0.95);
        }
        
        /* Stile per il container delle card */
        #cardsContainer {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%; 
            height: 100%; 
            background-color: rgba(0, 0, 0, 0.4); 
            display: flex; /* Permette di centrare le card */
            justify-content: center; /* Centro orizzontale */
            align-items: center; /* Centro verticale */
            gap: 3%; /* Spaziatura tra le card */
            display: none; /* Nascondi per default */
        }
        
        .card-title {
            text-align: center; /* Centra il testo */
            font-size: 2.5em; /* Dimensione del testo */
            margin-top: 2%;
        }
        
        .card-body {
            text-align: left;
        }
    </style>
    
    <title>Dashboard</title>
</head>
<body>
    <audio id="fischioAudio">
        <source src="${pageContext.request.contextPath}/resources/fischio.mp3" type="audio/mpeg">
    </audio>
    <div>
        <h1>Benvenuto, ${user.nome}!</h1>
        <div class="content">
            <p>Questa è la tua dashboard. Qui puoi vedere i tuoi treni, acquisti e voti.</p>
        </div>
    </div>

    <div class="container-pulsanti">
        <!-- Sezione visibile a tutti gli utenti -->
        <div class="form-wrapper">
        
            <!-- <div class="form-container">
                <h2>I Miei Treni Comprati</h2>
                <form action="user/viewPurchasedTrains" method="get">
                    <button type="submit">Visualizza Treni</button>
                </form>
            </div> -->
            <div class="form-container">
                <h2>Crea un nuovo treno</h2>
                <form>
                    <button id="showCardsButton" onclick="showDiv()">Crea Treno</button>
                </form>
                
            </div>


            <div id="overlay" class="hidden" onclick="hideDiv()"></div>
            <div id="cardsContainer" style="display: none;">

                <div class="card">
                    <div class="card-title">
                        <h1> Creazione statica </h1>
                    </div>  
                    
                    <div class="card-body">
                        <h2> Per coloro che sono alle prime armi. </h2>
                        <h3>
                        	<a href="train/createTrain"> Strumento che offre la possibilità di creare un treno in pochi semplicissimi passi, inserendo pochi caratteri otterrai il treno che desideri. </a>
                        </h3>
                    </div>
               </div>
               <div class="card">
                    <div class="card-title">
                        <h1> Creazione dinamica </h1>
                    </div>  
                    
                    <div class="card-body">
                        <h2> Per chi ha già esperienza con questo strumento </h2>
                        <h3>
                        	<a href="train/creaTrenoDinamico">	Pensato per chi ha dimestichezza con la creazione dei trei, permette di definire sin da subito più dettagli durante la creazione del treno. </a>
                        </h3>
                    </div>
               </div>
            </div>

            <script>

                function showUpdateModal() {
                    document.getElementById('cardsContainer').style.display = 'block';
                }
            
                function showDiv() {
                        const myDiv = document.getElementById('cardsContainer');
                        const overlay = document.getElementById("overlay");
                        cardsContainer.classList.toggle("hidden");
                        overlay.classList.toggle("hidden");
                
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
                
                
                function hideDiv() {
                        const myDiv = document.getElementById('cardsContainer');
                        const overlay = document.getElementById("overlay");
                        cardsContainer.classList.toggle("hidden");
                        overlay.classList.toggle("hidden");
                
                        if (myDiv.style.display === 'flex') {
                            myDiv.style.opacity = 0; // Rendi invisibile con transizione
                            setTimeout(() => {
                                myDiv.style.display = 'none';
                            }, 500); // Cambiato il timeout per l'animazione
                        } 
                    }
                
                
                window.onclick = function(event) {
                        var deleteModal = document.getElementById('cardsContainer');
                
                        if (event.target == cardsContainer) {
                            cardsContainer.style.display = "none";
                        } 
                }

            </script>


            
            <!-- Visualizzazione treni creati e acquistati -->
            <div class="form-container">
                <div><h2>I Miei Treni Creati</h2></div>
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
        
            <!-- Sezione visibile solo agli admin -->
            <% 
                User user = (User) session.getAttribute("user");
                if (user != null && user.isSuperAdmin()) { 
            %>
                <div class="form-container">
                    <h2>Gestione Admin</h2>
	                <form class="form-button">
                        <a href="admin/showPurchases" class="button"> Mostra Tutti gli Acquisti </a>
                		<a href="admin/manageUsers" class="button"> Gestisci Utenti </a>
                    </form>
                </div>
            <% } %>
        </div>
    </div>
    
    <div class="container text-center my-5">
        <div class="train-container" style="margin-top: 15%;">
            <div class="train">
                <img id="locomotiva" class="wagon" src="${pageContext.request.contextPath}/images/modellini/testa.png" alt="Vagone Testa" style="cursor: pointer;">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_rosa.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_verde.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_giallo.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_blu.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_arancione.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_viola.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/vagone_azzurro.png" alt="Vagone">
                <img class="wagon" src="${pageContext.request.contextPath}/modellini/coda.png" alt="Vagone Coda">
            </div>
        </div>
    </div>

    <script>
        // Ottieni l'elemento dell'immagine
        const locomotiva = document.getElementById('locomotiva');

        // Ottieni l'elemento audio
        const fischioAudio = document.getElementById('fischioAudio');

        // Aggiungi un gestore di eventi per il click sull'immagine
        locomotiva.onclick = function() {
            fischioAudio.play(); // Riproduci il suono
        };
    </script>

     <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->

    
</body>
</html>
