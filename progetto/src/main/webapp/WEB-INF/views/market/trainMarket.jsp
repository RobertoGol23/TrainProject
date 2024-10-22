<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="utility.TrenoUtility" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import="entity.dao.TrenoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

 

    <%@ include file="../navbar.jsp" %>

    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Market dei Treni</title>
    <style>
          body {
              font-family: Arial, sans-serif;
              background-color: #2e2b4f;
              color: #ffffff;
              margin: 0;
          }

          h1 {
              color: #8a79c7;
              text-align: center;
              margin: 20px 0;
          }
          
          .form-select[multiple] {
              height: auto; /* Imposta un'altezza fissa se necessario */
              min-height: 100px; /* Altezza minima per visualizzare più opzioni */
          }
          
          .container {
              display: flex;
              justify-content: flex-start; /* Allinea gli elementi a sinistra */
              align-items: flex-start; /* Allinea gli elementi all'inizio */
              max-width: 1200px;
              margin: 10px auto;
          }

          .container-left, .container-right {
              padding: 20px;
              background-color: #3b3a5d;
              border-radius: 10px;
          }

          .container-left {
              width: 30%; /* Mantieni la larghezza desiderata */
              margin-right: 20px; /* Margine per separare i due contenitori */
          }

          .container-right {
              width: 70%; /* Mantieni la larghezza desiderata */
              display: flex;
              flex-wrap: wrap;
              justify-content: flex-start;
          }

          
          input[type="number"] {
              width: 110px;
              height: 40px;
              padding: 5px;
              font-size: 14px;
              
              margin-top: 2%;
              margin-right: 2%;
          }
          

          .input-container{
              display: flex;
              align-items: center; /* Allinea verticalmente gli elementi */
              width: 70%; /* Utilizza tutta la larghezza disponibile */
              margin: -10%;
              margin-left: 10%;
          }
          
          .single-input{
              margin-left: 10%;
          }
          
          .text-area {
              flex: 1; /* Div flessibile e occupa spazio disponibile */
              font-size: 1.8rem;
          }

          .card {
              width: 30%;
              margin: 10px;
              background-color: #49456d;
              border-radius: 10px;
              overflow: hidden;
          }

          .card img {
              width: 100%;
              height: auto;
          }

          .buttons {
              display: flex;
              justify-content: flex-end;
              padding: 10px;
              margin-top: auto;
          }

          button {
              background-color: #8a79c7;
              border: none;
              color: white;
              padding: 10px;
              border-radius: 5px;
              cursor: pointer;
              margin-left: 5px;
              height: 40px;
          }

          button:hover {
              background-color: #79c7e3;
          }

          .search-bar-container {
              display: flex;
              justify-content: center;
              margin: 20px 0;
          }

          .search-bar {
              position: relative;
              width: 60%;
          }

          .search-bar input {
              width: 100%;
              height: 55px;
              border-radius: 10px;
              padding-left: 40px;
          }

          /* Stile di base per il form-select */
          .form-select {
            appearance: none; /* Rimuove il default del browser */
            background-color: #3b3a5d; /* Colore di sfondo */
            color: #ffffff; /* Colore del testo */
            border: 1px solid #8a79c7; /* Bordo */
            border-radius: 5px; /* Angoli arrotondati */
            padding: 10px 15px; /* Padding */
            width: 100%; /* Larghezza */
            transition: background-color 0.3s ease, border-color 0.3s ease; /* Transizione */
            font-size: 1rem; /* Dimensione del font */
            cursor: pointer; /* Cambia il cursore */
          }

          /* Stile quando il form-select è attivo */
          .form-select:focus {
            outline: none; /* Rimuove il contorno predefinito */
            background-color: #49456d; /* Colore di sfondo quando in focus */
            border-color: #79c7e3; /* Colore del bordo quando in focus */
          }

          /* Aggiunta di un'icona per la freccia del dropdown */
          .form-select::after {
            content: ''; /* Contenuto vuoto */
            position: absolute; /* Posizione assoluta */
            top: 50%; /* Centrato verticalmente */
            right: 15px; /* Distanza da destra */
            transform: translateY(-50%); /* Centra verticalmente */
            border: solid transparent; /* Bordo trasparente */
            border-width: 5px 5px 0; /* Freccia */
            border-top-color: #8a79c7; /* Colore della freccia */
            pointer-events: none; /* Ignora eventi sul pseudo-elemento */
          }

          /* Stile per opzioni quando vengono selezionate */
          .form-select option {
            background-color: #3b3a5d; /* Colore di sfondo delle opzioni */
            color: #ffffff; /* Colore del testo delle opzioni */
          }

          /* Stile per opzioni al passaggio del mouse */
          .form-select option:hover {
            background-color: #49456d; /* Colore di sfondo al passaggio del mouse */
          }


    </style>
</head>
<body>

  <h1><b>Train Market</b></h1>

  <div class="search-bar-container">
      <div class="search-bar">
          <input type="text" placeholder="Search anything...">
      </div>
  </div>

  <div class="container">

      <div class="container-left">

          <form action="/search" method="get">

                  <!-- Range Peso -->
                <div class="mb-4">
                    <label for="peso" class="form-label">Peso:</label>
                    <div class="d-flex">
                        <input type="number" id="peso-min" placeholder="Min" class="form-control me-2">
                        <input type="number" id="peso-max" placeholder="Max" class="form-control">
                    </div>
                </div>

                <!-- Range Lunghezza -->
                <div class="mb-4">
                    <label for="lunghezza" class="form-label">Lunghezza:</label>
                    <div class="d-flex">
                        <input type="number" id="lunghezza-min" placeholder="Min" class="form-control me-2">
                        <input type="number" id="lunghezza-max" placeholder="Max" class="form-control">
                    </div>
                </div>

                <!-- Range Prezzo -->
                <div class="mb-4">
                    <label for="prezzo" class="form-label">Prezzo:</label>
                    <div class="d-flex">
                        <input type="number" id="prezzo-min" placeholder="Min" class="form-control me-2">
                        <input type="number" id="prezzo-max" placeholder="Max" class="form-control">
                    </div>
                </div>

                <!-- Select Ordinamento -->
                <div class="mb-4">
                    <label for="ordinamento" class="form-label">Ordinamento:</label>
                    <select class="form-select" aria-label="Default select example">
                      <option value="voto">Per Voto</option>
                      <option value="prezzo">Per Prezzo</option>
                      <option value="nome">Per Nome</option>
                      <option value="peso">Per Peso</option>
                    </select>
                </div>

                
                <input type="submit" value="Cerca" class="btn btn-primary">

          </form>
          
        </div>

          

      <div class="container-right">
          <%
          List<Treno> treni = (List<Treno>) session.getAttribute("treni");
          AbstractApplicationContext context = null;
          TrenoUtility tu = new TrenoUtility();
          try {
              context = new AnnotationConfigApplicationContext(JpaConfig.class);
              VotoDAO votoDAO = context.getBean(VotoDAO.class);
              TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
              List<Treno> listaTreniOrderByVotazione = trenoDAO.getTreniOrderByVotazione();

              if (listaTreniOrderByVotazione != null && !listaTreniOrderByVotazione.isEmpty()) {
                  for (Treno treno : listaTreniOrderByVotazione) {
          %>

                  <div class="card">
                      <%if(treno.getMarca().equals("Treno RegionalGain")){%>
                      		<img src="${pageContext.request.contextPath}/images/treni/RG.jpg" class="card-img-top" alt="Treno">
                      <%}else{
                    	  if(treno.getMarca().equals("Treno xFurryFast")){%>
                    		  <img src="${pageContext.request.contextPath}/images/treni/FF.jpg" class="card-img-top" alt="Treno">
                      	<%}else{
                      		if(treno.getMarca().equals("Treno KargoModelz")){%>
                  		  <img src="${pageContext.request.contextPath}/images/treni/KM.jpg" class="card-img-top" alt="Treno">
                    	<%}
                      	}
                      }%>
                      <div class="card-body">
                          <h5 class="card-title"><%= (treno != null) ? treno.getNome() : "Treno non disponibile" %></h5>
                           	<p>Codice: <%= (treno != null) ? treno.getId() : "N/A" %></p>
                          <p class="card-text">Marca: <%= (treno != null) ? treno.getMarca() : "N/A" %></p>
                            <p>Sigla di composizione: <%= (treno != null) ? tu.getSigla(treno) : "N/A" %></p>
                          <p class="card-text">Prezzo: <%= (treno != null) ? treno.getPrezzoTotaleTreno() : 0 %> euro</p>
                          <p class="card-text">Peso: <%= (treno != null) ? treno.getPesoTotaleTreno() : 0 %> tonnellate</p>
                          <p class="card-text">Peso Trasportabile: <%= (treno != null && treno.getLocomotiva() != null) ? treno.getLocomotiva().getPesoTrainabile() : 0 %> tonnellate</p>
                          <p class="card-text">Numero di Persone: <%= (treno != null) ? treno.getPasseggeriTotali() : 0 %></p>
                            <p>Lunghezza del treno: <%= (treno != null) ? (tu.getSigla(treno)).length() : 0 %></p>
                          <p class="card-text">Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia((Long)treno.getId()) : 0 %></p>
                          <div class="buttons">
                              <form method="get" action="trainDetails">
                                  <input type="hidden" name="trenoId" value="<%= (treno != null) ? treno.getId() : 0 %>">
                                  <button type="submit" class="btn btn-info">Dettagli</button>
                              </form>
                          </div>
                      </div>
                  </div>

          <%
                  }
              } else {
          %>
                  <p>Nessun treno trovato.</p>
          <%
              }
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              if (context != null) {
                  context.close();
              }
          }
          %>
      </div>
  </div>   

</body>
</html>
