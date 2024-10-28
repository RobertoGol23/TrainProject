<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.acquisto.Acquisto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablesStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Visualizza Acquisti</title>
    <style>
 
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
        }
        
        button, a.button {
            width: 220px;
            background-color: #f5835e;
            color: black;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            border-width: 1px;
            border-style: solid;
            border-color: white;
            text-align:center;
            text-decoration: none;
            font-size: 18px;
            display: inline-block;
            transition: background-color 0.3s, transform 0.3s; /* Transizione */
        }
        button:hover, a.button:hover {
            background-color: #f96737;
            color: #ffffff;
			transform: translateY(-2px); /* Effetto sollevato */
        }

        a.button.btn-pages {
        	width: 100px;
        	height: 30px;
        	padding: 0px;
        }
        
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
        	padding: 0px;
        }

        .search-bar-container {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }
        
        .search-bar {
            position: relative;
            width: 100%;
        }
        
        .search-bar input {
            width: 100%;
            height: 55px;
            border-radius: 10px;
            padding-left: 50px; /* Padding per lasciare spazio all'icona */
            border: 1px solid #a29dcf; /* Bordo chiaro */
            background-color: #4b4a72; /* Colore di sfondo moderno */
            color: #ffffff; /* Colore del testo */
            font-size: 16px; /* Dimensione del font */
            transition: border-color 0.3s ease, box-shadow 0.3s ease; /* Transizioni */
        }
        
        
        /* Stile quando il campo è attivo */
        .search-bar input:focus {
            outline: none; /* Rimuove il contorno predefinito */
            border-color: #79c7e3; /* Colore del bordo in focus */
            box-shadow: 0 0 5px rgba(121, 199, 227, 0.5); /* Ombra in focus */
        }
        
        /* Icona della ricerca */
        .search-bar::before {
            position: absolute;
            left: 15px; /* Distanza dalla sinistra */
            top: 50%; /* Centrata verticalmente */
            transform: translateY(-50%); /* Centra verticalmente */
            font-size: 20px; /* Dimensione dell'icona */
            color: #ffffff; /* Colore dell'icona */
        }

        .search-icon {
            position: absolute;
            left: 15px; /* Distanza dalla sinistra */
            top: 50%; /* Centrata verticalmente */
            transform: translateY(-50%); /* Centra verticalmente */
            font-size: 20px; /* Dimensione dell'icona */
            color: #ffffff; /* Colore dell'icona */
        }
    </style>
</head>
<body>
    <h1>Lista di tutti gli Acquisti</h1>

    <div class="container">
        <p>Visualizza tutti gli acquisti effettuati dagli utenti.</p>

        <!-- Form di ricerca per ID Utente -->
         
        <div class="search-bar-container">
            <div class="search-bar">
                <form action="showPurchases" method="get" style="width:100%">
                    <label for="userIdSearch" style="display:none;">Cerca per ID Utente:</label>
                    <i class="fas fa-search search-icon"></i> <!-- Aggiungi l'icona qui -->
                    <input type="text" id="userIdSearch" name="userIdSearch" placeholder="Inserisci ID utente">
                </form>
            </div>
        </div>


        <table>
            <tr>
                <th>ID Utente</th>
                <th>ID Treno</th>
                <th>Data Acquisto</th>
                <th>ID Acquisto</th>
            </tr>
            <%
                // Supponiamo che tu abbia una lista di acquisti chiamata "purchases"
                List<Acquisto> purchases = (List<Acquisto>) request.getAttribute("purchases");
                for (Acquisto acquisto : purchases) {
            %>
            <tr>
                <td><%= acquisto.getUser().getId_user() %></td>
                <td><%= acquisto.getTreno().getId() %></td>
                <td><%= acquisto.getDataAcquisto() %></td>
                <td><%= acquisto.getId() %></td>
            </tr>
            <% } %>
        </table>
    </div>

    <!-- Paginazione -->
    <div align="center" style="margin-top: 20px; margin-bottom: 150px">
        <%
            Integer currentPage = (Integer) request.getAttribute("currentPage");
            Integer totalPages = (Integer) request.getAttribute("totalPages");

            if (currentPage != null && totalPages != null) {
                if (currentPage > 1) {
        %>
                    <a class="button btn-pages" style="margin-right: 20px" href="showPurchases?page=<%= currentPage - 1 %>">Precedente</a>
        <%
                }
        %>
                <span>Pagina <%= currentPage %> di <%= totalPages %></span>
        <%
                if (currentPage < totalPages) {
        %>
                    <a class="button btn-pages" style="margin-left: 20px" href="showPurchases?page=<%= currentPage + 1 %>">Successiva</a>
        <%
                }
            }
        %>
    </div>
    
</body>
</html>