<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.classi_astratte.Vagone" %>
<%@ page import="entity.servizi.Servizio" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.votazioni.Voto" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>
<%@ include file="../../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
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
            max-width: 70%;
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
/*             background-color: #6c6991; */
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
            border-width: 1px;
            border-style: solid;
            border-color: white;
            text-align:center;
            text-decoration: none;
            display: inline-block;
        }
        button:hover, a.button:hover {
            background-color: #79c7e3;
        }
        .cestino {
            padding: 10px;
            color: pink;
        }
        .cestino:hover {
            color: purple;
        }
        .modal-content {
		    background-color: #49456d; /* Colore di sfondo del modale */
		    color: #ffffff; /* Colore del testo */
		}
		.close {
			border-style: solid;
			border-width: 2px;
			border-color: black;
			width: 50px;
			height: 50px;
			text-align: center;
		}
		.modal-footer {
		  	margin: auto;
		  	
		}
		.modal-header {
			margin: auto;
			padding-left: 90px;
		  	padding-right: 90px;
		}
		.modal-body {
			margin: auto;
		}/* Flexbox layout for image and text */
        .train-info-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }
        .train-info {
            max-width: 60%;
        }
        .train-details img {
            max-width: 35%;
            height: auto;
            margin-left: 20px;
            border-radius: 10px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        /* Style for wagon images */
        .wagon-image {
            max-width: 100px;
            height: auto;
            border-radius: 5px;
        }
        @media (max-width: 768px) {
            /* Stack layout for smaller screens */
            .train-info-wrapper {
                flex-direction: column;
                 align-items: center; 
            }
            .train-details img {
                max-width: 80%;
                margin-left: 0;
                margin-top: 20px;
            }
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
        VotoDAO votoDAO = context.getBean(VotoDAO.class); %>
    <div class="train-details">
        <h2>Treno: <%= treno.getNome() %></h2>
        
        <div class="train-info-wrapper">
            <div class="train-info">
        <p>Id: <%= treno.getId() %></p>
        <p>Marca: <%= treno.getMarca() %></p>
        <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
        <p>Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>
        <p>Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia((Long) treno.getId()) : 0 %></p>
    </div>
            <!-- Immagine del treno a destra -->
            <% if(treno.getMarca().equals("Treno RegionalGain")) { %>
                <img src="${pageContext.request.contextPath}/treni/RG.jpg" class="card-img-top" alt="Treno">
            <% } else if(treno.getMarca().equals("Treno xFurryFast")) { %>
                <img src="${pageContext.request.contextPath}/treni/FF.jpg" class="card-img-top" alt="Treno">
            <% } else if(treno.getMarca().equals("Treno KargoModelz")) { %>
                <img src="${pageContext.request.contextPath}/treni/KM.jpg" class="card-img-top" alt="Treno">
            <% } %>
        </div>
    </div>
    
    <h2>Lista dei Vagoni</h2>
    <div class="wagon-list">
        <table>
            <thead>
                <tr>
                    <th>Tipo Vagone</th>
                    <th>Immagine del Vagone</th>
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
            	Vagone vagone;
                for (int i=0;i<listaVagoni.size();i++)
                {
                	vagone = listaVagoni.get(i);
                	
            %>
                <tr>
                    <td><%= vagone.getTipo() %></td>
                    <td>
                        <% if(treno.getMarca().equals("Treno RegionalGain")) { 
                            if(vagone.getTipo().equals("Locomotiva")) { %>
                                <img src="${pageContext.request.contextPath}/treni/LocomotivaRG.jpg" class="wagon-image" alt="LocomotivaRG">
                            <% } else if(vagone.getTipo().equals("VagonePasseggeri")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagonePasseggeriRG.jpg" class="wagon-image" alt="VagonePasseggeriRG">
                            <% } else if(vagone.getTipo().equals("VagoneRistorante")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagoneRistoranteRG.jpg" class="wagon-image" alt="VagoneRistoranteRG">
                            <% } else if(vagone.getTipo().equals("VagoneCargo")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagoneCargoRG.jpg" class="wagon-image" alt="VagoneCargoRG">
                            <% }
                        } else if(treno.getMarca().equals("Treno xFurryFast")) { 
                            if(vagone.getTipo().equals("Locomotiva")) { %>
                                <img src="${pageContext.request.contextPath}/treni/locomotivaFF.jpg" class="wagon-image" alt="LocomotivaFF">
                            <% } else if(vagone.getTipo().equals("VagonePasseggeri")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagonePasseggeriFF.jpg" class="wagon-image" alt="VagonePasseggeriFF">
                            <% } else if(vagone.getTipo().equals("VagoneRistorante")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagoneRistoranteFF.jpg" class="wagon-image" alt="VagoneRistoranteFF">
                            <% } else if(vagone.getTipo().equals("VagoneCargo")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagoneCargoFF.jpg" class="wagon-image" alt="VagoneCargoFF">
                            <% }
                        } else if(treno.getMarca().equals("Treno KargoModelz")) {
                            if(vagone.getTipo().equals("Locomotiva")) { %>
                                <img src="${pageContext.request.contextPath}/treni/locomotivaKM.jpg" class="wagon-image" alt="LocomotivaKM">
                            <% } else if(vagone.getTipo().equals("VagonePasseggeri")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagonePasseggeriKM.jpg" class="wagon-image" alt="VagonePasseggeriKM">
                            <% } else if(vagone.getTipo().equals("VagoneRistorante")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagoneRistoranteKM.jpg" class="wagon-image" alt="VagoneRistoranteKM">
                            <% } else if(vagone.getTipo().equals("VagoneCargo")) { %>
                                <img src="${pageContext.request.contextPath}/treni/VagoneCargoKM.jpg" class="wagon-image" alt="VagoneCargoKM">
                            <% } 
                        } %>
                    </td>
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
                                    <a href="modifyWagonServices?idVagone=<%= vagone != null ? vagone.getId() : "" %>&idTreno=<%= treno != null ? treno.getId() : "" %>&idVagoneRel=<%= i %>" class="button">
                                        <%= servizio.getNome() %>
                                    </a>
                                    <a class="cestino" href="deleteService?idVagone=<%= vagone != null ? vagone.getId() : "" %>&idTreno=<%= treno != null ? treno.getId() : "" %>&nomeServizio=<%= servizio.getNome() %>">
                                    <i class="fas fa-trash"></i></a>
                                </li>
                            <%
                                }
                            %>
                            </ul>
                        <%
                            } else if (!vagone.getTipo().equalsIgnoreCase("Locomotiva")) {
                        %>
                            <p> 
                                <a href="modifyWagonServices?idVagone=<%= vagone != null ? vagone.getId() : "" %>&idTreno=<%= treno != null ? treno.getId() : "" %>&idVagoneRel=<%= i %>" class="button">Aggiungi servizio</a>
                             </p>
                        <%
                            }
                        %>
                    </td>
                </tr>
            <%
                }
                context.close();
            %>
            </tbody>
        </table>
    </div>

    <div align="center">
        <a href="addWagons?idTreno=<%= treno != null ? treno.getId() : "" %>" class="button">Aggiungi vagoni</a>
        <a href="removeWagons?idTreno=<%= treno != null ? treno.getId() : "" %>" class="button">Rimuovi vagoni</a>
        <a href="cloneTrain?idTreno=${treno.id}" class="button">Clona il Treno</a>
        <a href="ribaltaTreno?idTreno=${treno.id}" class="button">Ribalta il Treno</a>
        <a href="javascript:void(0)" class="button" onclick="showDeleteModal()">Cancella</a>
    </div>

    <!-- Modale per la conferma della cancellazione -->
    <div id="confirmDeleteModal" class="modal" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Conferma Cancellazione</h5>
          </div>
          <div class="modal-body">
            <p>Sei sicuro di voler cancellare il treno con ID: <strong><%= treno.getId() %></strong>?</p>
            <p>Questa azione non può essere annullata.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
            <button type="button" class="btn btn-danger" onclick="confirmDelete()">Conferma</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function showDeleteModal() {
            $('#confirmDeleteModal').modal('show');
        }

        function confirmDelete() {
            window.location.href = 'deleteTrain?idTreno=<%= treno.getId() %>';
        }
    </script>

    <%
        }
    %>
</body>
</html>
