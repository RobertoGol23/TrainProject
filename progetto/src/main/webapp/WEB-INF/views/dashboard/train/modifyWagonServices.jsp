<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.classi_astratte.Vagone" %>
<%@ page import="utility.ServiziUtility" %>
<%@ page import="entity.servizi.Servizio" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.dao.VagoneDAO" %>
<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
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
        .disabilitato, .disabilitato:hover{
        	background-color: grey;
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
            	ServiziUtility su = new ServiziUtility();
            	Vagone vagone = (Vagone) request.getAttribute("vagone");
            	            	
            	int serviziDisponibili = 0;
                if (servizi != null) {
                    for (Servizio servizio : servizi) {
                    	
                    	if(!su.isServicePresent(vagone, servizio))
                    	{
                    		if(vagone.getTipo().equalsIgnoreCase("VagoneCargo"))
                    		{
                    			if(su.checkServiziCargo(servizio))
                    			{
                    				//stampo a schermo l'opzione del menu' a tendinda dei servizi
                    				%><option value="<%= servizio.getNome() %>"><%= servizio.getNome() %></option><%
                    				serviziDisponibili++;
                    			}
                    		}
                    		else
                    		{
                    			//stampo a schermo l'opzione del menu' a tendinda dei servizi
                				%><option value="<%= servizio.getNome() %>"><%= servizio.getNome() %></option><%
                				serviziDisponibili++;
                    		}
                    		
                    	}
                    }
                    if(serviziDisponibili==0)
                    {
                    	%><option> Nessun servizio disponibile </option><%
                    }
                    
                }
            %>
            </select><br/>
			<!-- Campo nascosto per memorizzare l'indice del vagone -->
            <input type="hidden"  name="idVagoneRel" id="idVagoneRel" value="${idVagoneRel}"></input>
            <input type="hidden" name="idTreno" id="idTreno" value="<%= (request.getAttribute("idTreno")) %>"></input>
            <% 
	            if(serviziDisponibili>0) //se non ci sono servizi da aggiungere tolgo il pulsante
	            {
	            	%><button type="submit">Aggiungi Servizio</button><%
	            }
	            else
	            {
	            	%><button disabled class="disabilitato" type="submit">Aggiungi Servizio</button><%
	            }
            
            %>            
            <a href="viewTrain?idTreno=<%= (request.getAttribute("idTreno")) %>" class="button">Torna indietro</a> 

        </form>

        <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
        
</body>
</html>