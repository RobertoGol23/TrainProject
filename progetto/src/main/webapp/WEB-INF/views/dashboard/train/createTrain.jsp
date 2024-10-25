<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.user.User" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">

    <title>Crea Treno</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            
        }
        
        .container{
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            color: #8a79c7;
            text-align: center;
        }

        .form-container {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
        }
        .form-container input, .form-container select {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: none;
        }
        select {
        	width: 100%;
        }
        input {
        	width: 93%;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #79c7e3;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #79c7e3;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #8a79c7;
        }
        .disabilitato, .disabilitato:hover{
        	background-color: grey;
        }
        .error-message {
            color: red;
            margin-top: 20px;
            text-align: center;
        }
        a {
            color: #79c7e3;
        }
    </style>
</head>
<body>

    <h1 style="margin-top: 5%;">Crea un Nuovo Treno</h1>

    <div class="container">
        <div class="form-container">
            <!-- action="createTrain" method="post" indica che chiama la pagina che restituisce (RequestMapping) /createTrain -->
            <form action="createTrain" method="post">
                <label for="nomeTreno">Nome del Treno</label>
                <input type="text" maxlength="25" id="nomeTreno" name="nomeTreno" required>

                <label for="sigla">Sigla del Treno (es. hrp)</label>
                <input type="text" id="sigla" name="sigla" required>

                <label for="fabbrica">Seleziona Fabbrica</label>
                <select id="fabbrica" name="fabbrica" required>
                    <option value="1">XFurryFast</option>
                    <option value="2">KargoModelz</option>
                    <option value="3">RegionalGain</option>
                </select>
<% 
				User user = (User) session.getAttribute("user");
                		
	            if(user!= null) //se non ci sono servizi da aggiungere tolgo il pulsante
	            {
	            	%><button type="submit">Crea Treno</button><%
	            }
	            else
	            {
	            	%><button onclick="window.location.href='/train-bazaar/register';" class="btn btn-primary">Crea il tuo profilo per salvare il treno</button><%
	            }
            
            %>  
            </form>
        </div>
    </div>
	
     <%
        String error = (String) request.getAttribute("error");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error-message">
            <p><%= error %></p>
        </div>
    <%
        }
    %>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>