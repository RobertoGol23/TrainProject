<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">

<head>

  <style>

      .navbar-container {
          display: flex; /* Usa Flexbox per centrare il contenuto */
          justify-content: space-around; /* Centra orizzontalmente */
      }
      
      .navbar-nav {
          list-style: none; /* Rimuovi i punti elenco */
          padding: 0; /* Rimuovi padding */
          margin: 0; /* Rimuovi margini */
          display: flex; /* Disporre gli elementi in una riga */
          justify-content: space-around; /* Spazia uniformemente gli elementi */
      }
    
      .nav-link {
          text-decoration: none; /* Rimuovi la sottolineatura */
          font-weight: bold; /* Grassetto per maggiore visibilità */
          padding: 10px 15px; /* Padding interno per i link */
          border-radius: 5px; /* Angoli arrotondati */
          transition: background-color 0.3s, transform 0.2s; /* Transizione per effetto hover */

          margin-right: 5px; /* Spazio tra l'icona e il testo */
          font-size: 1.2em; /* Dimensione dell'icona */
          font-size: 16px;
      }
      
      .nav-link:hover {
        background-color: #8a79c7; /* Colore di sfondo al passaggio del mouse */
        transform: scale(1.05);
      }

      .left-navbar {
          display: flex; /* Attiva il layout flex */
          gap: 10px; /* Spazio tra i div */
          padding: 0 15px;
      }

      .right-navbar {
        display: flex; /* Attiva il layout flex */
        gap: 10px; /* Spazio tra i div */
        position: absolute; /* Posiziona il div in modo assoluto */
        right: 50px; /* Massimo 50px dal lato destro */
        padding: 0 15px;
      }

      .custom-navbar {
        background-color: #49456d; 
        padding: 10px;  
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Ombra per profondità */
    }

    .btn {
      border: none; /* Rimuovi il bordo */
      background-color: transparent; /* Sfondo trasparente per i pulsanti */
    }
    
    .btn:hover {
        background-color: rgba(255, 255, 255, 0.1); /* Colore di sfondo al passaggio del mouse */
    }

      
  
  </style>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Train Bazaar</title>

    <!-- Inclusione di Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Inclusione di Font Awesome per le ICONE -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <style>
        .custom-navbar {
            background-color: #49456d; 
            padding: 10px;          
        }
    </style>
</head>

<body>
  <div class="container-navbar"> 
    <nav class="navbar navbar-expand-xl navbar-light custom-navbar"> 
        
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                
              <div class="left-navbar">
                        <a class="nav-link btn btn-delft-blue text-bluepx-3 font-weight-bold " href="/train-bazaar/dashboard/home">
                        <i class="fas fa-home"></i> Home </a>

                        <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold " href="user/wallet">
                        <i class="fas fa-money-bill-wave"></i> Wallet </a>
                </div>


                <div class="right-navbar">
                        <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold" href="user/editProfile">
                        <i class="fas fa-info-circle"></i> Profilo  </a>

                        <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold" href="/train-bazaar/dashboard/user/logout">
                        <i class="fa-solid fa-right-from-bracket"></i> Logout </a>
                </div>
            </div>
        
    </nav>
  </div>


    <!-- Inclusione di jQuery e Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
