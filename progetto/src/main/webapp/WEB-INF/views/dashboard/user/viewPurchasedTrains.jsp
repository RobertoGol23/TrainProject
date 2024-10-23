<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Treni Acquistati</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            text-align: center;
            margin: 0;
        }

        h1 {
            color: #8a79c7;
            margin-bottom: 30px;
            font-size: 2.5rem;
            text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #49456d;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
        }

        th, td {
            padding: 15px;
            border: 1px solid #ffffff;
            text-align: center;
            transition: background-color 0.3s, transform 0.2s;
        }

        th {
            background-color: #8a79c7;
            font-size: 1.2rem;
        }

        td {
            background-color: #3e3b5c;
            font-size: 1rem;
        }

        tr:hover {
            background-color: #5a547c;
            transform: scale(1.02); /* Leggera animazione al passaggio del mouse */
        }

        .no-data {
            color: #ff4c4c;
            font-size: 1.2rem;
            padding: 20px;
        }

        .back-btn {
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 30px;
            transition: background-color 0.3s, transform 0.3s;
            font-size: 1rem;
        }

        .back-btn:hover {
            background-color: #45a049;
            transform: translateY(-2px);
        }

        footer {
            margin-top: 40px;
            font-size: 0.9rem;
            color: #cccccc;
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
                <th>Data Acquisto</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<entity.acquisto.Acquisto> acquisti = (List<entity.acquisto.Acquisto>) request.getAttribute("acquisti");
                if (acquisti != null && !acquisti.isEmpty()) {
                    for (entity.acquisto.Acquisto acquisto : acquisti) {
                        entity.treno.Treno treno = acquisto.getTreno();
            %>
                        <tr>
                            <td><%= treno.getNome() %></td>
                            <td><%= treno.getPrezzoTotaleTreno() %></td>
                            <td><%= treno.getPesoTotaleTreno() %></td>
                            <td><%= treno.getMarca() %></td>
                            <td><%= acquisto.getDataAcquisto() != null ? new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(acquisto.getDataAcquisto()) : "N/A" %></td>
                        </tr>
            <% 
                    }
                } else { 
            %>
                <tr>
                    <td colspan="5" class="no-data">Non hai acquistato alcun treno.</td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <button class="back-btn" onclick="window.history.back();">Torna Indietro</button>

   <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->

</body>
</html>
