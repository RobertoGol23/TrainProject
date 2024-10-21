<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.classi_astratte.Vagone" %>
<%@ page import="entity.servizi.Servizio" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.votazioni.Voto" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import= "org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import= "org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import= "configuration.JpaConfig" %>
<%@ include file="../../navbar.jsp" %>

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
            max-width: 1050px;
            margin: 20px auto;
        }
        h1, h2 {
            text-align: center;
            padding-top:15px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            /*background-color: #6c6991;*/
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
         button, a.button {
         	width: 180px;
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            /*margin-top: 10px;*/
        }
        button:hover, a.button:hover {
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
    <% AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    		VotoDAO votoDAO = context.getBean(VotoDAO.class);%>
    <div class="train-details">
        <h2>Treno: <%= treno.getNome() %></h2>
        <p>Id: <%= treno.getId() %></p>
        <p>Marca: <%= treno.getMarca() %></p>
        <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
        <p>Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>
        <p>Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia((Long) treno.getId()) : 0 %></p>
    </div>

    <h2>Lista dei Vagoni</h2>
    <div class="wagon-list">
        <table>
            <thead>
                <tr>
                    <th>Tipo Vagone</th>
                    <th>Peso</th>
                    <th>Prezzo</th>
                    <th>Dettagli</th>
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
                    <td><%= vagone.getDettagli() %></td>
                    <td>
                        <%
                            List<Servizio> servizi = vagone.getListaServizi();
                            if (servizi != null && !servizi.isEmpty()) {
                        %>
                            <ul>
                            <%
                                for (Servizio servizio : servizi) {
                            %>
                                <li> 
								    <a href="modifyWagonServices?idVagone=<%= vagone != null ? vagone.getId() : "" %>&idTreno=<%= treno != null ? treno.getId() : "" %>" class="button">
								        <%= servizio.getNome() %>
								    </a>
								</li>
                            <%
                                }
                            %>
                            </ul>
                        <%
                            } else if(!vagone.getTipo().equalsIgnoreCase("Locomotiva"))
                            {
                        %>
                            <p> 
							    <a href="modifyWagonServices?idVagone=<%= vagone != null ? vagone.getId() : "" %>
							    	&idTreno=<%= treno != null ? treno.getId() : "" %>" class="button">Aggiungi servizio</a>
							</p>

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
	<div align="center">
        <a href="addWagons?idTreno=<%= treno != null ? treno.getId() : "" %>" class="button">Aggiungi vagoni</a>
        
        <a href="removeWagons?idTreno=<%= treno != null ? treno.getId() : "" %>" class="button">Rimuovi vagoni</a>
    
    	<a href="cloneTrain?trenoId=${treno.id}" class="button">Clona il Treno</a> <!-- Pulsante per clonare il treno -->
    </div>
	<% context.close(); %>
    <%
        } else {
    %>
        <p>Nessun treno trovato con l'ID specificato.</p>
    <%
        }
    %>
</body>
</html>