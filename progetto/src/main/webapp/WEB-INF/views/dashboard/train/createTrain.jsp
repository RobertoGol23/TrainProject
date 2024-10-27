<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.user.User" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">

    <title>Crea Treno</title>
    <style>
        body {
            font-family: 'Segoe UI', 'Helvetica Neue', sans-serif; /* Font più formale */
            background-color: #2e2b4f; /* Colore di sfondo */
            color: #ffffff; /* Colore del testo */
        }
        
        .title-container {
            margin-bottom: 20px; /* Spazio tra il titolo e il contenitore principale */
            text-align: center; /* Centra il testo all'interno del contenitore del titolo */
        }
        
        h1 {
            color: #e1418b; /* Colore dei titoli */
            margin: 0;
            font-size: 35px; /* Dimensione del titolo */
            font-weight: bold; /* Grassetto */
        }
        
        h2 {
        	color: #e1418b; /* Colore dei titoli */
        }
        
        .main-container {
            display: flex; /* Usa Flexbox per disporre i div */
            justify-content: center; /* Centra orizzontalmente il contenuto */
            align-items: flex-start; /* Allinea gli elementi in alto */
            flex-wrap: wrap; /* Permette il wrapping degli elementi */
            width: 100%; /* Assicurati che occupi tutta la larghezza */
            margin: 0 auto; /* Margine automatico per centrare */
            max-width: 800px; /* Larghezza massima per il contenitore principale */
        }
        
        .container {
            width: 300px; /* Larghezza fissa per il contenitore del modulo */
            background-color: #49456d; /* Colore di sfondo */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            position: relative; /* Posizionamento relativo per il contenitore */
        }
        
        .left-content,
        .right-content {
            width: 25%; /* Ridotto per consentire più spazio */
            padding: 20px; /* Padding per i div */
            background-color: #3b3a5d; /* Colore di sfondo */
            color: #ffffff; /* Colore del testo */
            border-radius: 10px; /* Bordi arrotondati */
            opacity: 0; /* Inizialmente invisibile */
            visibility: hidden; /* Non visibile */
            transition: opacity 0.5s ease, visibility 0.5s; /* Transizione per l'opacità e la visibilità */
        }
        
        .bottom-content {
            margin-top: 20px; /* Spazio sopra il bottom-content */
            width: 100%; /* Larghezza totale per il div */
            padding: 20px; /* Padding interno */
            background-color: #3b3a5d; /* Colore di sfondo */
            color: #ffffff; /* Colore del testo */
            border-radius: 10px; /* Bordi arrotondati */
            opacity: 0; /* Inizialmente invisibile */
            visibility: hidden; /* Non visibile */
            transition: opacity 0.5s ease, visibility 0.5s; /* Transizione per l'opacità e la visibilità */
        }
        
        .left-content {
            margin-right: 20px; /* Spazio tra left-content e container */
        }
        
        .right-content {
            margin-left: 20px; /* Spazio tra right-content e container */
        }
        
        
        
        /* Altri stili */
        label {
            display: block;
            margin-bottom: 8px;
            color: ivory; 
            font-weight: bold;
            cursor: pointer;
        }
        
        input[type="number"], 
        input[type="text"], 
        select {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: none;
            font-size: 16px;
            width: calc(100% - 20px); /* Larghezza totale meno il padding */
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
        
        .error-message {
            color: red;
            margin-top: 20px;
            text-align: center;
        }
        a {
            color: #79c7e3;
        }
    </style>
</head>
<body>

    <div class="title-container" style="font-size: 3rem; margin-top: 5%;";>
        <h1> Crea il tuo treno </h1>
      </div>
      

      <div class="main-container">
        <div class="left-content" id="left-content" style="opacity: 0; visibility: hidden;">
            <h2>Sigla del treno</h2>
            <p>È una stringa che descrive in pochi semplici caratteri da quali vagoni sarà costruito il tuo treno.
                La locomotiva è indicata dalla lettera h, mentre gli altri vagoni (passeggeri, ristorante e cargo) sono 
                descritti dalla loro prima lettera (p, r, c).</p>
        </div>
    
        <div class="container">
            <div class="form-container" style="margin-top: 2%;">
                <form action="createTrain" method="post">
                    <label for="nomeTreno">Nome del Treno</label>
                    <input type="text" maxlength="25" id="nomeTreno" name="nomeTreno" required>
                    <label for="sigla">Sigla del Treno (es. hrp)</label>
                    <input type="text" id="sigla" name="sigla" required>
                    <label for="fabbrica">Seleziona Fabbrica</label>
                    <select id="fabbrica" name="fabbrica" required>
                        <option value="1">XFurryFast</option>
                        <option value="2">KargoModelz</option>
                        <option value="3">RegionalGain</option>
                    </select>
                    <button type="submit">Crea Treno</button>
                </form>
            </div>
        </div>
    
        <div class="right-content" id="right-content" style="opacity: 0; visibility: hidden;">
            <h2>Nome del Treno</h2>
            <p>Dare un nome ad un treno ti permetterà di distinguere più facilmente i treni all'interno delle tue collezioni.</p>
        </div>
    
        <div class="bottom-content" id="bottom-content" style="opacity: 0; visibility: hidden;">
            <h2>Selezione della Fabbrica</h2>
            <p>Attualmente in questo tool sono previste 3 fabbriche per la creazione di treni standard (modificabili successivamente). Le fabbriche attualmente disponibili sono: xFurryFast, RegionalGain, KargoModelz.</p>
        </div>
    </div>
    

	
     <%
        String error = (String) request.getAttribute("error");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error-message">
            <p><%= error %></p>
        </div>
    <%
        }
    %>


        <script>
            const toggleInput = document.getElementById('nomeTreno');
            const sideDiv = document.getElementById('right-content');

            toggleInput.addEventListener('focus', () => {
                sideDiv.style.opacity = '1'; // Rendi visibile
                sideDiv.style.visibility = 'visible'; // Imposta visibilità a visible
            });

            toggleInput.addEventListener('blur', () => {
                sideDiv.style.opacity = '0'; // Rendi invisibile
                sideDiv.style.visibility = 'hidden'; // Imposta visibilità a hidden
            });


            const toggleInput2 = document.getElementById('sigla');
            const sideDiv2 = document.getElementById('left-content');

            toggleInput2.addEventListener('focus', () => {
                sideDiv2.style.opacity = '1'; // Rendi visibile
                sideDiv2.style.visibility = 'visible'; // Imposta visibilità a visible
            });

            toggleInput2.addEventListener('blur', () => {
                sideDiv2.style.opacity = '0'; // Rendi invisibile
                sideDiv2.style.visibility = 'hidden'; // Imposta visibilità a hidden
            });


            const toggleInput3 = document.getElementById('fabbrica');
            const sideDiv3 = document.getElementById('bottom-content');

            toggleInput3.addEventListener('focus', () => {
                sideDiv3.style.opacity = '1'; // Rendi visibile
                sideDiv3.style.visibility = 'visible'; // Imposta visibilità a visible
            });

            toggleInput3.addEventListener('blur', () => {
                sideDiv3.style.opacity = '0'; // Rendi invisibile
                sideDiv3.style.visibility = 'hidden'; // Imposta visibilità a hidden
            });

        </script>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>