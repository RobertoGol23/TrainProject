<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <style>
    </style>

    <meta charset="UTF-8">
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
    <title>Train Bazaar</title>
	
    <!-- Inclusione di Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Inclusione di Font Awesome per le ICONE -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css">
</head>

<body>
    <div class="container-navbar">
        <nav class="navbar navbar-expand-xl navbar-light custom-navbar">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <!-- Aggiunta del logo -->
                <div class="left-navbar">
                        <img src="${pageContext.request.contextPath}/icons/logo.jpg" alt="Train Bazaar Logo"
                            class="logo-img">
                    
                    <!-- Link di navigazione -->
                    <a class="nav-link btn btn-delft-blue text-bluepx-3 font-weight-bold"
                        href="/train-bazaar/dashboard/home">
                        <i class="fas fa-home"></i> Home </a>

                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/dashboard/user/wallet">
                        <i class="fas fa-solid fa-wallet"></i> Wallet </a>

                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/trainMarket">
                        <i class="fa-solid fa-cart-shopping"></i> TrainMarket </a>
                </div>

                <div class="right-navbar">
                    <a class="nav-link btn px-3 rounded-2 font-weight-bold">
                        <i class="fa-solid fa-euro-sign"></i> ${user.wallet} </a>

                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/dashboard/user/editProfile">
                        <i class="fas fa-info-circle"></i> Profilo </a>

                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/dashboard/user/logout">
                        <i class="fa-solid fa-right-from-bracket"></i> Logout </a>
                </div>
            </div>
        </nav>
    </div>

    
</body>

</html>
