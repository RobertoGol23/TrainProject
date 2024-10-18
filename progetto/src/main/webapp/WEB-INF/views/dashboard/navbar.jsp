<div%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">

<style>
  .custom-navbar {
    display: flex;           /* Attiva il layout flex */
    background-color: #49456d;; /* Scegli il colore che preferisci */
    justify-content: space-between; /* Distribuisce gli elementi con spazio tra di loro */
    align-items: center;    /* Allinea gli elementi verticalmente al centro */
    padding: 10px;          /* Padding interno per la navbar */
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
  <nav class="navbar navbar-expand-md navbar-light bg-body-sea-salt border-bottom">
    <!-- <div class="container-fluid"> -->
        <!-- <a class="navbar-brand" href="#">Train Bazaar</a> -->

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="{{ __('Toggle navigation') }}"> <!-- aria-label="Toggle navigation -->
            <span class="navbar-toggler-icon"></span>
        </button>
  
        <div class="collapse navbar-collapse" id="navbarNav">

          <!-- Left Side Of Navbar -->
          <ul class="navbar-nav me-auto gap-1"> 
              <li class="nav-item"> 
                  <a class="nav-link btn btn-delft-blue text-sea-salt px-3 rounded-2" href="/train-bazaar/dashboard/home">  <i class="fas fa-home"></i> Home </a>
              </li>

              <li class="nav-item">
                <a class="nav-link btn btn-air-sup-blue text-dark px-3 rounded-2" href="user/editProfile"> <i class="fas fa-info-circle"></i> Profilo</a>
              </li>

              <li class="nav-item">
                <a class="nav-link btn btn-air-sup-blue text-dark px-3 rounded-2" href="user/addFunds"> <i class="user/addFunds"></i> AddFounds</a>
              </li>
          </ul>
  
          <!-- Right Side Of Navbar -->
          <ul class="navbar-nav mt-1 ms-auto gap-1">

<!--             fare uno script per vedere se mostrare "profilo" e "log out" oppure "login" e "register" qualora non si è loggati
 -->
              <li class="nav-item mr-auto"> 
                  <a class="nav-link" href="/logout"><i class="fa-solid fa-right-from-bracket"></i> Logout</a>
              </li>
              
          </ul>

        </div>
    <!-- </div> -->
  </nav>
     

<!-- Inclusione di jQuery e Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>