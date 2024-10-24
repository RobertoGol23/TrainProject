<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="java.util.List" %>
<%@ include file="../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Conferma Acquisto Treno</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #8a79c7;
            text-align: center;
            margin: 20px 0;
            font-size: 2.5rem; /* Dimensione del titolo */
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
        }

        .content {
            width: 90%;
            max-width: 800px;
            margin: 0 auto;
            text-align: center;
        }

        .carousel {
            display: flex;
            overflow: hidden;
            margin-bottom: 20px;
        }

        .carousel img {
            width: 100%;
            height: auto;
            border-radius: 10px;
            transition: transform 0.5s ease;
        }

        .treno {
            background-color: #49456d;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5);
            transition: transform 0.3s;
        }

        .treno:hover {
            transform: scale(1.02); /* Leggera animazione al passaggio del mouse */
        }

        .treno img {
            width: 150px;
            height: auto;
            border-radius: 5px;
            margin-right: 20px;
        }

        .details {
            flex: 1;
            text-align: left; /* Allinea il testo a sinistra */
        }

        button {
            background-color: #8a79c7;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s, transform 0.3s; /* Transizione */
            margin-top: 20px;
        }

        button:hover {
            background-color: #79c7e3;
            transform: translateY(-2px); /* Effetto sollevato */
        }

        .back-button {
            display: block;
            margin-top: 20px;
            background-color: #d45a5a;
        }

        .error-message {
            color: #ff4c4c; /* Rosso per messaggi di errore */
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h1>Procedi con l'acquisto</h1>

<div class="content">
    
    <% Treno treno = (Treno) session.getAttribute("treno"); %>

    <% if (treno != null) { %>
        <div class="treno">
            <img src="${pageContext.request.contextPath}/images/treni/locomotivaFurryFast.jpg" alt="<%= treno.getNome() %>">
            <div class="details">
                <h2><%= treno.getNome() %></h2>
                <p>Marca: <%= treno.getMarca() %></p>
                <p>Prezzo: <%= treno.getPrezzoTotaleTreno() %> €</p>
                <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> kg</p>
                <p>Peso Trasportabile: <%= treno.getLocomotiva() != null ? treno.getLocomotiva().getPesoTrainabile() : 0 %> kg</p>
                <p>Numero di Passeggeri: <%= treno.getPasseggeriTotali() %></p>
            </div>
        </div>

        <form method="post" action="confirmPurchase">
            <input type="hidden" name="trenoId" value="<%= treno.getId() %>">
            <button type="submit">Conferma Acquisto</button>
        </form>

    <% } else { %>
        <p class="error-message">Treno non trovato.</p>
    <% } %>
</div>


<!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->

</body>
</html>
