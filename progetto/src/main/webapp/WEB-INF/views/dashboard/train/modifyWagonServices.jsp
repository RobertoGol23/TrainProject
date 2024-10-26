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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Modifica Servizi Vagone</title>
    <style>
        
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
        }
        h1 {
            text-align: center;
            margin-top: 0px;
        }
        
        h2 {
        	text-align: center;
            margin-top: 50px;
            margin-bottom: 20px;
        }
        
        .container {
        	margin-top: 25px;
		    width: 25%;
/* 		    border: 3px solid #db7a4e; /* Colore e spessore del bordo */ */
		    border-radius: 10px;
		    padding: 45px; /* Spaziatura interna */
		}	
	
		form {
		    background-color: #49456d;
		    padding: 20px;
		    border-radius: 10px;
		    width: 100%;
		    height: auto; /* Permette al contenuto di determinare l'altezza */
		    margin: 0 auto; /* Centra orizzontalmente */
		}
	
		a.button {
			text-align: center;
			margin-top: 10px;
		    width: 100%;
		    padding: 10px;
		    background-color: #f5835e;
		    border: none;
		    border-radius: 5px;
		    color: #2e2b4f;
		    text-align: center;
		    cursor: pointer;
			padding: 10px 20px;
			transition: background-color 0.3s, transform 0.3s; /* Transizione */
		}
		a.button:hover {
		    background-color: #f96737;
		    text-decoration: none;
		    color: #ffffff;
			transform: translateY(-2px); /* Effetto sollevato */
		}
        
    </style>
</head>
<body>

    
    
  	<div class="container">
        
        <form method="post" action="addService" class="form">
		<h1>Modifica servizi  <%= request.getAttribute("vagone") != null ? ((Vagone) request.getAttribute("vagone")).getTipo() : "N/A" %></h1>
		<h2>Aggiungi servizi a un vagone</h2>
		
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
            <div class="form-button">
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
			</div>
			<input type="hidden"  name="idVagoneRel" id="idVagoneRel" value="${idVagoneRel}"></input>
        	<input type="hidden" name="idTreno" id="idTreno" value="<%= (request.getAttribute("idTreno")) %>"></input>
        </form>
     </div>
		
        <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
        
</body>
</html>