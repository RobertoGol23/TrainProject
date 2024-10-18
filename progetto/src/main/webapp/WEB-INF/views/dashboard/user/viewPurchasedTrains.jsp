<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Treni Acquistati</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            padding: 20px;
            text-align: center;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #49456d;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ffffff;
            text-align: center;
        }
        th {
            background-color: #8a79c7;
        }
        td {
            background-color: #3e3b5c;
        }
        .back-btn {
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .back-btn:hover {
            background-color: #45a049;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h1>I Tuoi Treni Acquistati</h1>
    
    <table>
        <thead>
            <tr>
                <th>Nome Treno</th>
                <th>Prezzo (€)</th>
                <th>Peso Totale (ton)</th>
                <th>Marca</th>
                <th>Data Acquisto</th> <!-- Colonna per la data di acquisto -->
            </tr>
        </thead>
        <tbody>
            <% 
                // Ottieni la lista di acquisti dal modello passato dal controller
                List<entity.acquisto.Acquisto> acquisti = (List<entity.acquisto.Acquisto>) request.getAttribute("acquisti");

                // Itera su ogni acquisto e stampa i dettagli nella tabella
                if (acquisti != null && !acquisti.isEmpty()) {
                    for (entity.acquisto.Acquisto acquisto : acquisti) {
                        entity.treno.Treno treno = acquisto.getTreno();
            %>
                        <tr>
                            <td><%= treno.getNome() %></td>
                            <td><%= treno.getPrezzoTotaleTreno() %></td>
                            <td><%= treno.getPesoTotaleTreno() %></td>
                            <td><%= treno.getMarca() %></td>
                            <td><%= acquisto.getDataAcquisto() != null ? new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(acquisto.getDataAcquisto()) : "N/A" %></td> <!-- Formattazione della data -->
                        </tr>
            <% 
                    }
                } else { 
            %>
                <tr>
                    <td colspan="5">Non hai acquistato alcun treno.</td> <!-- Modificato per adattarsi al numero di colonne -->
                </tr>
            <% } %>
        </tbody>
    </table>

    <p align="center"><a href="/train-bazaar/dashboard/home">Torna alla Dashboard</a></p>

</body>
</html>