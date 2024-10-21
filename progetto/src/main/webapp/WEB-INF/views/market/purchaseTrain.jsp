<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="java.util.List" %>
<%@ include file="../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conferma Acquisto Treno</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }

        h1 {
            color: #8a79c7;
            text-align: center;
            margin: 0;
        }

        .content {
            width: 80%;
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
            margin-bottom: 10px;
            display: flex;
            align-items: center;
        }

        .treno img {
            width: 150px;
            height: auto;
            border-radius: 5px;
            margin-right: 20px;
        }

        .details {
            flex: 1;
        }

        button {
            background-color: #8a79c7;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #79c7e3;
        }

        .back-button {
            display: block;
            margin-top: 20px;
            background-color: #d45a5a;
        }
    </style>
</head>
<body>

<h1 style="margin-top: 20px; margin-bottom: 20px">Procedi con l'acquisto</h1>

<div class="content">
    
   <!--  <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="..." class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="..." class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="..." class="d-block w-100" alt="...">
          </div>
        </div>
    </div> -->

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
        <p>Treno non trovato.</p>
    <% } %>
</div>

</body>
</html>
