<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.classi_astratte.Vagone" %>
<%@ page import="entity.servizi.Servizio" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Treno</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        .train-details, .wagon-list {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            max-width: 800px;
            margin: 20px auto;
        }
        h1, h2 {
            text-align: center;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            background-color: #6c6991;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #6c6991;
        }
        td {
            background-color: #49456d;
        }
        button {
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #79c7e3;
        }
    </style>
</head>
<body>

    <h1>Dettagli del Treno</h1>

    <%
        // Recupera il treno dalla richiesta
        Treno treno = (Treno) request.getAttribute("treno");
        if (treno != null) {
    %>
    <div class="train-details">
        <h2>Treno: <%= treno.getNome() %></h2>
        <p>Id: <%= treno.getId() %></p>
        <p>Id: <%= treno.getMarca() %></p>
        <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
        <p>Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>
    </div>

    <h2>Lista dei Vagoni</h2>
    <div class="wagon-list">
        <table>
            <thead>
                <tr>
                    <th>Tipo Vagone</th>
                    <th>Peso</th>
                    <th>Prezzo</th>
                    <th>Servizi</th>
                </tr>
            </thead>
            <tbody>
            <%
                // Recupera la lista dei vagoni del treno
                List<Vagone> listaVagoni = treno.getListaVagoni();
                for (Vagone vagone : listaVagoni) {
            %>
                <tr>
                    <td><%= vagone.getTipo() %></td>
                    <td><%= vagone.getPeso() %> tonnellate</td>
                    <td><%= vagone.getPrezzo() %> euro</td>
                    <td>
                        <%
                            List<Servizio> servizi = vagone.getListaServizi();
                            if (servizi != null && !servizi.isEmpty()) {
                        %>
                            <ul>
                            <%
                                for (Servizio servizio : servizi) {
                            %>
                                <li><%= servizio.getNome() %></li>
                            <%
                                }
                            %>
                            </ul>
                        <%
                            } else {
                        %>
                            <p>Nessun servizio associato</p>
                        <%
                            }
                        %>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <%
        } else {
    %>
        <p>Nessun treno trovato con l'ID specificato.</p>
    <%
        }
    %>

</body>
</html>