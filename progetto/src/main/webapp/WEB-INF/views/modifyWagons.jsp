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
    <title>Modifica Vagoni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        form {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            max-width: 400px;
            margin: auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, select {
            width: 94%;
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
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
        h1, h2 {
            text-align: center;
        }
        ul {
            list-style-type: none;
            padding: 0;
            max-width: 400px;
            margin: 20px auto;
        }
        ul li {
            background-color: #49456d;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
        }
        a {
            color: #ffffff;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h1>Modifica Vagoni per Treno ID: <%= request.getAttribute("treno") != null ? ((Treno) request.getAttribute("treno")).getId() : "N/A" %></h1>

    <%
        // Recupera il treno dalla richiesta
        Treno treno = (Treno) request.getAttribute("treno");
        if (treno != null) {
    %>
        <h2>Vagoni Attuali</h2>
        <ul>
        <%
            // Recupera la lista dei vagoni del treno
            List<Vagone> listaVagoni = treno.getListaVagoni();
            for (Vagone vagone : listaVagoni) {
            	if(vagone.getTipo().equals("Locomotiva")){
            	%>
                    <li><%= vagone.getTipo() %> - PesoTrasportabile: <%= treno.getLocomotiva().getPesoTrainabile() %> - Peso: <%= vagone.getPeso() %> - Prezzo: <%= vagone.getPrezzo() %></li>
                <%
            	}else{
                %>
                    <li><%= vagone.getTipo() %> - Peso: <%= vagone.getPeso() %> - Prezzo: <%= vagone.getPrezzo() %></li>
                <%
            	}
            }
        %>
        </ul>

        <h2>Aggiungi Servizi a un Vagone</h2>
        <form method="post" action="addService">
            <!-- Campo nascosto per l'ID del treno -->
            <input type="hidden" name="idTreno" value="<%= treno.getId() %>" />

            <label for="vagoneId">Seleziona Vagone:</label>
            <select name="vagoneId" id="vagoneSelect" onchange="updateVagoneIndex()">
            <%
                // Genera le opzioni per selezionare i vagoni e salva anche l'indice
                int vagoneIndex = 0;
                for (Vagone vagone : listaVagoni) {
            %>
                <option value="<%= vagone.getId() %>" data-index="<%= vagoneIndex %>"><%= vagone.getTipo() %></option>
            <%
                    vagoneIndex++;
                }
            %>
            </select><br/>

            <!-- Campo nascosto per memorizzare l'indice del vagone -->
            <input type="hidden" name="vagoneIndex" id="vagoneIndex" />

            <label for="servizio">Seleziona Servizio:</label>
            <select name="servizio">
            <%
                // Recupera i servizi disponibili dalla richiesta
                List<Servizio> servizi = (List<Servizio>) request.getAttribute("servizi");
                if (servizi != null) {
                    for (Servizio servizio : servizi) {
            %>
                <option value="<%= servizio.getNome() %>"><%= servizio.getNome() %></option>
            <%
                    }
                }
            %>
            </select><br/>

            <button type="submit">Aggiungi Servizio</button>
        </form>

    <%
        } else {
    %>
        <p>Nessun treno trovato con l'ID specificato.</p>
    <%
        }
    %>

    <script>
        function updateVagoneIndex() {
            // Ottieni l'indice del vagone selezionato e impostalo nel campo nascosto
            var select = document.getElementById("vagoneSelect");
            var index = select.options[select.selectedIndex].getAttribute("data-index");
            document.getElementById("vagoneIndex").value = index;
        }

        // Imposta l'indice iniziale alla selezione corrente
        updateVagoneIndex();
    </script>

</body>
</html>

