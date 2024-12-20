<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.classi_astratte.Vagone" %>
<%@ page import="entity.servizi.Servizio" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.votazioni.Voto" %>
<%@ page import="utility.TrenoUtility" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablesStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    
    <title>Dettagli Treno</title>
    <style>
        .train-details, .wagon-list {
            background-color: #49456d;            
            border-radius: 10px;
            max-width: 70%;
            margin: 20px auto;
        }
        .train-details {
        	padding: 20px;
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
        
        button, a.button {
            width: 180px;
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
            display: inline-block;
            transition: background-color 0.3s, transform 0.3s; /* Transizione */
        }
        button:hover, a.button:hover {
            background-color: #f96737;
            color: #ffffff;
			transform: translateY(-2px); /* Effetto sollevato */
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
            max-width: 70%;
/*             border-style: solid; */
/* 			border-width: 2px; */
/* 			border-color: black; */
/* 			background-color: ivory; */
			color: ivory;
			padding: 20px;
/* 			border-radius: 5px; */
/* 			font-family: "Georgia"; */
        	font-size: 16px;
        }
        .train-details img {
            max-width: 35%;
            height: auto;
            border-radius: 10px;
            border-style: solid;
			border-width: 2px;
			border-color: black;
			border-radius: 5px;
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
        .confirm-popup {
		    display: none;
		    position: fixed;
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
		    background-color: #49456d;
		    color: white;
		    padding: 20px;
		    border-radius: 10px;
		    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
		    text-align: center;
		    z-index: 1000;
		}

        /* Stile del titolo nelle card */
        .elegant-title {
            font-family: 'Georgia', serif; /* Font elegante per il titolo */
            font-size: 2.8rem; /* Dimensione del titolo */
            color: #ffffff; /* Colore del titolo */
            margin-bottom: 10px; /* Margine sotto il titolo */
            text-align: center; /* Allinea il titolo al centro */
        }
        
        .elegant-text {
            font-family: 'Arial', sans-serif; /* Font elegante per il testo */
            font-size: 1.5rem; /* Dimensione del testo */
            color: ivory; /* Colore del testo */
            line-height: 1.5; /* Altezza della linea per migliorare la leggibilità */
            margin: 5px 0; /* Margine verticale */
            text-align: justify; /* Giustifica il testo */
        }
    </style>
</head>
<body>

    <h1>Dettagli del Treno</h1>
    
    <div id="ribaltaPopup" class="confirm-popup">
        <p>Il treno e' stato ribaltato con successo!</p>
        <button onclick="closeRibaltaPopup()">OK</button>
    </div>
    

    <%
    	String successMessage = (String) session.getAttribute("success");
    	System.out.println(successMessage);
        // Recupera il treno dalla richiesta
        Treno treno = (Treno) request.getAttribute("treno");
        if (treno != null) {
    
        TrenoUtility tu = new TrenoUtility();
     	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        VotoDAO votoDAO = context.getBean(VotoDAO.class);
    %>
    <div class="train-details">
        <div class="elegant-title">Treno: <%= treno.getNome() %></div>
        
        <div class="train-info-wrapper">
            <div class="train-info">
                <p class="elegant-text"> - Marca del treno: <%= treno.getMarca() %></p>
                <p class="elegant-text"> - Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
                <p class="elegant-text"> - Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>
                <p class="elegant-text"> - Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia((Long) treno.getId()) : 0 %></p>
                <p class="elegant-text"> - Passeggeri totali: <%= treno.getPasseggeriTotali() %> passeggeri</p>
                <p class="elegant-text"> - Id treno: <%= treno.getId() %></p>
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
                    <td><%= (vagone != null && tu.getDettagli(vagone) != null) ? tu.getDettagli(vagone) : "N/A" %></td>
                    <td>
                        <%
                            List<Servizio> servizi = vagone.getListaServizi();
                            if (servizi != null && !servizi.isEmpty()) {
                        %>
                            <ul>
                            <%
                                for (Servizio servizio : servizi) {
                                	//System.out.println(servizio.getNome() + " " + servizio);
                            %>
                                <li> 
                                    <a href="modifyWagonServices?idVagone=<%= vagone != null ? vagone.getId() : "" %>&idTreno=<%= treno != null ? treno.getId() : "" %>&idVagoneRel=<%= i %>" class="button">
                                        <%= servizio.getNome() %>
                                    </a>
                                    <a class="cestino" href="deleteService?idVagone=<%= vagone != null ? vagone.getId() : "" %>&idTreno=<%= treno != null ? treno.getId() : "" %>&idServizio=<%= servizio.getId() %>">
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
                //context.close();
            %>
            </tbody>
        </table>
    </div>

    <div align="center" style="margin-bottom: 150px">
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
            <p>Questa azione non piu' essere annullata.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="button" data-dismiss="modal">Annulla</button>
            <button type="button" class="button" onclick="confirmDelete()">Conferma</button>
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
    
    <script>
        function showRibaltaPopup() {
            document.getElementById("ribaltaPopup").style.display = "block";
        }

        function closeRibaltaPopup() {
            document.getElementById("ribaltaPopup").style.display = "none";
        }

        // Mostra il popup di conferma se successMessage � presente
        <% if (successMessage != null) { %>
            showRibaltaPopup();
        <% 
	        session.removeAttribute("success");
	        successMessage = null;
        } %>
    </script>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>
