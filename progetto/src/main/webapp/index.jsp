<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina di Prova</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: purple;
            color: white;
            text-align: center;
            padding: 20px 0;
        }
        h1 {
            color: violet;
        }
        h2 {
            color: darkviolet;
        }
        .container {
            width: 80%;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .purple-box {
            background-color: violet;
            padding: 15px;
            margin-top: 20px;
            border-radius: 8px;
            color: white;
            text-align: center;
        }
        a {
            color: purple;
            text-decoration: none;
        }
        a:hover {
            color: darkviolet;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid purple;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: purple;
            color: white;
        }
        ul {
            list-style-type: square;
        }
        .image-box {
            text-align: center;
            margin-top: 20px;
        }
        .image-box img {
            width: 200px;
            border: 5px solid purple;
        }
        footer {
            background-color: purple;
            color: white;
            text-align: center;
            padding: 10px 0;
            margin-top: 40px;
        }
    </style>
</head>
<body>

<header>
    <h1>Benvenuto nella mia pagina di prova</h1>
</header>

<div class="container">
    <h2>Questa è una sezione con testo viola</h2>
    <p>Qui si possono provare diversi elementi HTML, come <strong>titoli</strong>, <em>paragrafi</em>, <u>testo sottolineato</u> e molto altro.</p>
    
    <h3>Una lista di cose:</h3>
    <ul>
        <li>Elemento 1</li>
        <li>Elemento 2</li>
        <li>Elemento 3</li>
    </ul>

    <h3>Un link utile</h3>

    <p>Puoi visitare <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley" target="_blank">questo sito</a> per maggiori informazioni.</p>

    <h3>Una tabella con il colore viola</h3>
    <table>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Età</th>
        </tr>
        <tr>
            <td>Mario</td>
            <td>Rossi</td>
            <td>30</td>
        </tr>
        <tr>
            <td>Luigi</td>
            <td>Bianchi</td>
            <td>25</td>
        </tr>
    </table>

    <div class="purple-box">
        Questo è un box con sfondo viola.
    </div>

    <div class="image-box">
        <h3>Un'immagine di prova</h3>
        <img src="https://via.placeholder.com/200x200.png?text=Immagine" alt="Immagine di esempio">
    </div>
</div>

<footer>
    <p>Copyright © 2024 - Tutti i diritti riservati</p>
</footer>

</body>
</html>