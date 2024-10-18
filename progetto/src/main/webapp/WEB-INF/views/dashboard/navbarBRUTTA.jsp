<div%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">

<style>
    .navbar-container {
        background-color: #49456d; /* Colore di sfondo della navbar */
        padding: 10px 0; /* Padding verticale */
        width: 100%; /* Larghezza totale */
        display: flex; /* Usa Flexbox per centrare il contenuto */
        justify-content: space-around; /* Centra orizzontalmente */
    }
    
    .navbar {
        max-width: 1200px; /* Larghezza massima per contenere la navbar */
        width: 100%; /* Larghezza completa all'interno del contenitore */
    }
    
    .navbar-nav {
        list-style: none; /* Rimuovi i punti elenco */
        padding: 0; /* Rimuovi padding */
        margin: 0; /* Rimuovi margini */
        display: flex; /* Disporre gli elementi in una riga */
        justify-content: space-around; /* Spazia uniformemente gli elementi */
    }
    
    .nav-item {
        margin: 0 15px; /* Margine orizzontale tra gli elementi */
    }
    
    .nav-link {
        text-decoration: none; /* Rimuovi la sottolineatura */
        color: white; /* Colore del testo */
        font-weight: bold; /* Grassetto per maggiore visibilità */
        padding: 10px 15px; /* Padding interno per i link */
        border-radius: 5px; /* Angoli arrotondati */
        transition: background-color 0.3s; /* Transizione per effetto hover */
    }
    
    .nav-link:hover {
        background-color: #8a79c7; /* Colore di sfondo al passaggio del mouse */
    }
    
  

</style>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Train Bazaar</title>

    <!-- Inclusione di Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Inclusione di Font Awesome per le ICONE -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <!-- Stile personalizzato -->
    <!-- <link rel="stylesheet" href="style.css"> -->

</head>
<body>
   
    <div class="navbar-container">
        <nav class="navbar">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Profilo</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Add Funds</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Logout</a></li>
            </ul>
        </nav>
    </div>

</body>
  

      

<!-- Inclusione di jQuery e Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
