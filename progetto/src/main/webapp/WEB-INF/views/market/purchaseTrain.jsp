<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Conferma Acquisto Treno</title>
    <style>
        

        h1 {
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
            margin-top: 40px;
        }
		
        h2 {
        	height: auto;
        	text-align: left;
        	font-weight: bold;
        }
        
        form, .form-button {
		    padding: 0px;
		    border-radius: 10px;
		    width: 15%;
		    height: auto; /* Permette al contenuto di determinare l'altezza */
		    margin-top: 10px;
		    margin: 0 auto; /* Centra orizzontalmente */
		    font-weight: normal;
		}
		
		button, a.button {
			margin-top: 10px;
		}

        .treno {
            background-color: #49456d;
            border-radius: 10px;
            padding: 30px;
            margin: 20px auto;
            min-width: 30%;
            max-width: 40%;
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
            margin-left: 6%;
        }

        .error-message {
            color: #ff4c4c; /* Rosso per messaggi di errore */
            margin-top: 20px;
        }
        
    </style>
</head>
<body>



<div>
    
    <% Treno treno = (Treno) session.getAttribute("treno"); %>
	<h1>Procedi con l'acquisto</h1>
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
        <form method="post" action="confirmPurchase" class="form-button">
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
