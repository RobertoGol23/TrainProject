<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <style>
        .navbar-container {
            display: flex;
            justify-content: space-around;
        }

        .navbar-nav {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: space-around;
        }

        .nav-link {
            text-decoration: none;
            font-weight: bold;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background-color 0.3s, transform 0.2s;
            margin-right: 5px;
            font-size: 16px;
        }

        .nav-link:hover {
            background-color: #8a79c7;
            transform: scale(1.05);
        }

        .left-navbar {
            display: flex;
            align-items: center; /* Allinea gli elementi verticalmente */
            gap: 10px;
            padding: 0 15px;
        }

        .right-navbar {
            display: flex;
            gap: 10px;
            position: absolute;
            right: 50px;
            padding: 0 15px;
        }

        .custom-navbar {
            background-color: #49456d;
            padding: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .btn {
            border: none;
            background-color: transparent;
        }

        .btn:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }

        /* Stile per il logo */
        .logo-img {
            width: 50px; /* Dimensioni ridotte del logo */
            height: auto; /* Mantieni le proporzioni */
        }
    </style>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Train Bazaar</title>

    <!-- Inclusione di Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Inclusione di Font Awesome per le ICONE -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                        <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="Train Bazaar Logo"
                            class="logo-img">
                    
                    <!-- Link di navigazione -->
                    <a class="nav-link btn btn-delft-blue text-bluepx-3 font-weight-bold"
                        href="/train-bazaar/dashboard/home">
                        <i class="fas fa-home"></i> Home </a>

                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/trainMarket">
                        <i class="fa-solid fa-cart-shopping"></i> TrainMarket </a>
                </div>

                <div class="right-navbar">
                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/dashboard/user/wallet">
                        <i class="fas fa-solid fa-wallet"></i> Wallet </a>

                    <a class="nav-link btn btn-air-sup-blue text-blue px-3 rounded-2 font-weight-bold"
                        href="/train-bazaar/dashboard/user/ricarica">
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

    <!-- Inclusione di jQuery e Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
