<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>I Miei Treni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #8a79c7;
        }
        .accordion {
            background-color: #49456d;
            color: #ffffff;
            cursor: pointer;
            padding: 10px;
            border: none;
            text-align: left;
            outline: none;
            font-size: 15px;
            transition: background-color 0.2s ease;
            width: 80%; /* Imposta la larghezza per gli accordion */
            margin: 10px 0; /* Aggiungi margine tra gli accordion */
        }
        .accordion:hover {
            background-color: #3a3758;
        }
        .panel {
            padding: 0 10px;
            display: none;
            overflow: hidden;
            background-color: #333;
            width: 80%; /* Imposta la larghezza per i pannelli */
            margin-bottom: 10px; /* Aggiungi margine inferiore ai pannelli */
        }
        table {
            width: 100%; /* Imposta la larghezza della tabella al 100% */
            background-color: #49456d;
            color: #ffffff;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #79c7e3;
            color: #2e2b4f;
        }
    </style>
</head>
<body>
    <h1>I Miei Treni</h1>

    <!-- Includiamo l'HTML generato dal controller -->
    <div>
        ${trainsTable}
    </div>

    <script>
        // Funzione per gestire l'apertura e la chiusura delle tendine
        document.querySelectorAll('.accordion').forEach(button => {
            button.addEventListener('click', function() {
                this.classList.toggle('active');
                const panel = this.nextElementSibling;
                if (panel.style.display === "block") {
                    panel.style.display = "none";
                } else {
                    panel.style.display = "block";
                }
            });
        });
    </script>

</body>
</html>
