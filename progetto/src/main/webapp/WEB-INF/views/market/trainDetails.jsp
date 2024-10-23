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
<%@ include file="../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
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
            max-width: 950px;
            margin: 20px auto;
        }
        h1 {
            text-align: center;
            padding-top: 15px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            background-color: #6c6991;
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
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        button:hover {
            background-color: #79c7e3;
        }
        /* Flexbox layout for image and text */
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

    <h1>Dettagli del Treno</h1> <!-- Titolo generale della pagina -->

    <%
        // Recupera il treno dalla richiesta
        Treno treno = (Treno) request.getAttribute("treno");
        if (treno != null) {
    %>
    <% AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    		VotoDAO votoDAO = context.getBean(VotoDAO.class);%>
    
    <div class="train-details">
        <!-- Nome del treno come titolo principale sopra tutto -->
        <h2>Treno: <%= treno.getNome() %></h2>

        <div class="train-info-wrapper">
            <div class="train-info">
                <!-- Dettagli testuali del treno a sinistra -->
                <p>Marca: <%= treno.getMarca() %></p>
                <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
                <p>Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>
                <p>Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia(treno.getId()) : 0 %></p>
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
                for (Vagone vagone : listaVagoni) {
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
                                <li><%= servizio.getNome() %></li>
                            <%
                                }
                            %>
                            </ul>
                        <%
                            } else {
                        %>
                            <p>Nessun servizio associato</p>
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
        <a href="purchaseTrain?trenoId=${treno.id}" class="button">Compra il treno</a>
        <a href="voteTrain?trenoId=${treno.id}" class="button">Vota il treno</a>
    </div>
    <% context.close(); %>
    <%
        } else {
    %>
        <p>Nessun treno trovato con l'ID specificato.</p>
    <%
        }
    %>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>
