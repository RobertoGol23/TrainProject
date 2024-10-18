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

    <!-- <a class="navbar-brand me-4" href="/">
        <img src="/storage/images/logo.png" class="bi img-fluid" alt="Responsive image" width="140">
    </a> -->
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="{{ __('Toggle navigation') }}"> <!-- aria-label="Toggle navigation -->
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- Left Side Of Navbar -->
        <ul class="navbar-nav me-auto gap-1">
            <li class="nav-item">
                <a class="nav-link btn btn-delft-blue text-sea-salt px-3 rounded-2" href="/"> <i class="bi bi-house"></i> Home</a>
            </li>

            <li class="nav-item">
                <a class="nav-link btn btn-air-sup-blue text-dark px-3 rounded-2" href="user/addFunds"><i class="bi bi-house-door"></i> AddFounds</a>
            </li>
           <!--  @if (!Auth::check() || Auth::check() && Auth::user()->ruolo != 'nonstudente')
                <li class="nav-item">
                    <a class="nav-link btn btn-air-sup-blue text-sea-salt px-3 rounded-2" @auth href="{{ route('marketplace.index') }}" @endauth href="{{ route('login') }}"><i class="bi bi-book"></i> Marketplace</a>
                </li>
            @endif -->

            <li class="nav-item">
                <a class="nav-link btn btn-air-sup-blue text-dark px-3 rounded-2" href="{{ route('board.index') }}"><i class="bi bi-megaphone"></i> Bacheca</a>
            </li>
            <li class="nav-item">
                <a class="nav-link btn btn-air-sup-blue text-sea-salt px-3 rounded-2" href="{{ route('findplaces.index')}} "><i class="bi bi-pin-map"></i> FindPlaces</a>
            </li>
            <!-- @if (Auth::check() && Auth::user()->ruolo == 'admin')
                <li class="nav-item">
                    <a class="nav-link btn btn-air-sup-blue text-dark px-3 rounded-2" href="{{ route('admin.index')}}"><i class="bi bi-gear"></i> Admin</a>
                </li>
            @endif -->
        </ul>

        <!-- Right Side Of Navbar -->
        <ul class="navbar-nav mt-1 ms-auto gap-1">
            <!-- Authentication Links -->
            @guest
                @if (Route::has('register'))
                    <li class="nav-item">
                        <a class="nav-link btn btn-orange-peel text-dark px-3 rounded-2" href="{{ route('auth.roleselect') }}"><i class="bi bi-person"></i> REGISTRATI</a>
                    </li>
                @endif
                @if (Route::has('login'))
                    <li class="nav-item">
                        <a class="nav-link btn btn-hunyadi-yellow text-dark px-3 rounded-2" href="{{ route('login') }}"><i class="bi bi-box-arrow-in-right"></i> LOGIN</a>
                    </li>
                @endif
            @else
                <!-- controlla se l'utente è loggato, se lo è e si è in /houses, mostra il bottone per gestire le case
                altrimenti no -->

                @if (Request::is('houses') || Request::is('houses/*'))
                    <li class="nav-item">
                        <a class="nav-link btn btn-delft-blue text-sea-salt px-3 rounded-2" href="/houses/manage/{{auth()->user()->id}}"><i class="bi bi-house-gear"></i> Gestisci case</a>
                    </li>
                @elseif (Request::is('marketplace') || Request::is('marketplace/*'))
                    <li class="nav-item">
                        <a class="nav-link btn btn-delft-blue text-sea-salt px-3 rounded-2" href="/marketplace/manage/{{auth()->user()->id}}"><i class="bi bi-file-earmark-diff"></i> I tuoi annunci</a>
                    </li>
                @elseif (Request::is('board') || Request::is('board/*'))
                    <li class="nav-item">
                        <a class="nav-link btn btn-delft-blue text-sea-salt px-3 rounded-2" href="{{ route('board.create') }}"><i class="bi bi-plus-lg"></i> Crea un post</a>
                    </li>
                @endif

                <li class="nav-item dropdown">
                    <a id="navbarDropdown" class="nav-link btn btn-orange-peel text-dark px-3 rounded-2 dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-person"></i>
                        {{ Auth::user()->name }}
                    </a>

                    <div class="dropdown-menu dropdown-menu-end bg-hunyadi-yellow rounded-2" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item bg-hunyadi-yellow" href="{{ route('settings') }}">
                            <i class="bi bi-gear"></i>
                            Impostazioni
                        </a>
                        <a class="dropdown-item bg-hunyadi-yellow" href="{{ route('logout') }}"
                           onclick="event.preventDefault();
                                         document.getElementById('logout-form').submit();">
                            <i class="bi bi-box-arrow-right"></i>
                            {{ __('Logout') }}
                        </a>

                        <form id="logout-form" action="{{ route('logout') }}" method="POST" class="d-none">
                            @csrf
                        </form>
                    </div>
                </li>
            @endguest
        </ul>
    </div>

</nav>
  

      

<!-- Inclusione di jQuery e Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
