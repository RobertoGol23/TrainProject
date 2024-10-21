<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.classi_astratte.Vagone" %>
<%@ page import="utility.TrenoUtility" %>
<%@ page import="entity.servizi.Servizio" %>
<%@ page import="java.util.List" %>
<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica servizi vagone</title>
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
        button, a.button {
            background-color: #8a79c7;
            width: 170px;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            
        }
        button:hover, a.button:hover {
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
    </style>
</head>
<body>

    <h1>Modifica servizi  <%= request.getAttribute("vagone") != null ? ((Vagone) request.getAttribute("vagone")).getTipo() : "N/A" %></h1>
    
  
        <h2>Aggiungi servizi a un vagone</h2>
        <form method="post" action="addService">


            <label for="servizio">Seleziona Servizio:</label>
            <select name="servizio">
            <%
                // Recupera i servizi disponibili dalla richiesta
                List<Servizio> servizi = (List<Servizio>) request.getAttribute("servizi");
            	TrenoUtility tu = new TrenoUtility();
            	Vagone vagone = (Vagone) request.getAttribute("vagone");
            	
            	
                if (servizi != null) {
                    for (Servizio servizio : servizi) {
                    	
                    	if(!tu.isServicePresent(vagone, servizio))
                    	{
                    		//stampo a schermo l'opzione del menu' a tendinda dei servizi
                    		%><option value="<%= servizio.getNome() %>"><%= servizio.getNome() %></option><%
                    	}
                    }
                    
                }
            %>
            </select><br/>
			<!-- Campo nascosto per memorizzare l'indice del vagone -->
            <input type="hidden" name="vagoneId" id="vagoneId" value="<%= vagone.getId()%>"></input>
            <input type="hidden" name="idTreno" id="idTreno" value="<%= (request.getAttribute("idTreno")) %>"></input>
            <button type="submit">Aggiungi Servizio</button>
            <a href="viewTrain?idTreno=<%= (request.getAttribute("idTreno")) %>" class="button">Torna indietro</a> 

        </form>

</body>
</html>