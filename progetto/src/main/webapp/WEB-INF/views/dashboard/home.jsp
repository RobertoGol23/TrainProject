<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="../navbar.jsp" %> <!-- con .. si va indietro di una cartella-->

<%@ page import="entity.user.User" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/logo-icon.png?v=1" type="image/png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/trainAnimatioStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
    	body {
	    background-color: #2e2b4f;
	}
    </style>
    <!-- Audio del fischio del treno -->
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
                <form action="train/creaTrenoDinamico" method="get">
                    <button type="submit">Crea Treno</button>
                </form>
            </div>
        

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
	            <!-- Immagini dei vagoni -->
<!-- 	            <img src="https://via.placeholder.com/150x100.png?text=Vagone+1" alt="Vagone Testa"> -->
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
</body>
</html>