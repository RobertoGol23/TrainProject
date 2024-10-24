<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../navbar.jsp" %> <!-- con .. si va indietro di una cartella-->

<%@ page import="entity.user.User" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/logo-icon.png?v=1" type="image/png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/trainAnimatioStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
        @charset "UTF-8";
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
    
	body {
	    font-family: Arial, sans-serif;
	    background-color: #2e2b4f;
	    color: #ffffff;
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
	    gap: 20px;
	}
	.form-container {
		display: block;
	    background-color: #49456d;
	    padding: 20px;
	    border-radius: 10px;
	    width: 280px;
	    text-align: center;

		margin: 10px;
		padding: 20px;
		flex: 1 0 300px; /* Dimensione minima dei contenitori */
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Ombra */
		transition: transform 0.3s, box-shadow 0.3s; /* Transizione per hover */
	}
	.form-container:hover {
		transform: translateY(-5px); /* Effetto sollevato al passaggio del mouse */
		box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3); /* Ombra più profonda */
	}
	.form-button {
		display: flex;
		flex-wrap: wrap;
		justify-content: center; /* Centratura dei pulsanti */
		margin: 20px 0;

		vertical-align: bottom;
	}
	a {
	    color: #79c7e3;
	    text-decoration: none;
	}
	button {
	    width: 100%;
	    margin-top: 25px;
	    padding: 10px;
	    background-color: #f5835e;
	    border: none;
	    border-radius: 5px;
	    color: #2e2b4f;
	    cursor: pointer;
		padding: 10px 20px;
		transition: background-color 0.3s, transform 0.3s; /* Transizione */
	}
	button:hover {
	    background-color: #f96737;
	    color: #ffffff;
		transform: translateY(-2px); /* Effetto sollevato */
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
    </style>
    
    <title>Dashboard</title>
</head>
<body>
    <audio id="fischioAudio">
        <source src="${pageContext.request.contextPath}/resources/fischio.mp3" type="audio/mpeg">
    </audio>
    <div class="container-benvenuto">
        <h1>Benvenuto, ${user.nome}!</h1>
        <div class="content">
            <p>Questa è la tua dashboard. Qui puoi vedere i tuoi treni, acquisti e voti.</p>
        </div>
    </div>

    <div class="container-pulsanti">
        <!-- Sezione visibile a tutti gli utenti -->
        <div class="form-wrapper">

            <!-- TODO: cancellare se non piu necessario-->
           <!--  <div class="form-container">
                <h2>Modifica il tuo Profilo</h2>
                <form action="user/editProfile" method="get">
                    <button type="submit">Modifica Profilo</button>
                </form>
            </div> -->


            <div class="form-container">
                <h2>Crea un Nuovo Treno</h2>
                <form action="train/createTrain" method="get">
                    <button type="submit">Crea Treno</button>
                </form>
            </div>


            <!-- TODO: cancellare se non piu necessario-->
           <!--  <div class="form-container">
                <h2>Crea un Nuovo Treno con sigla</h2>
                <form action="train/createTrain" method="get">
                    <button type="submit">Crea Treno</button>
                </form>
            </div>
            
            <div class="form-container">
                <h2>Crea un Nuovo Treno dinamico</h2>
                <form action="train/creaTrenoDinamico" method="get">
                    <button type="submit">Crea Treno</button>
                </form>
            </div> -->

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
                    <form action="admin/showPurchases" method="get">
                        <button type="submit">Mostra Tutti gli Acquisti</button>
                    </form>
                    <form action="admin/manageUsers" method="get">
                        <button type="submit">Gestisci Utenti</button>
                    </form>
                </div>
            <% } %>
        </div>
    </div>
    
    <div class="container text-center my-5">
        <div class="train-container">
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
