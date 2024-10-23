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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">

 

    <%@ include file="../navbar.jsp" %>

    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Market dei Treni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            margin: 0;
            padding-bottom: 80px; /* Spazio per i bottoni di paginazione */
        }

        h1 {
            color: #8a79c7;
            text-align: center;
            margin: 20px 0;
        }
   
        
        .container {
            display: flex;
            justify-content: flex-start; /* Allinea gli elementi a sinistra */
            align-items: flex-start; /* Allinea gli elementi all'inizio */
            max-width: 80%;
            margin: 10px auto;
        }

        .container-left, .container-right {
            padding: 20px;
            background-color: #3b3a5d;
            border-radius: 10px;
        }

        .container-left {
            width: 20%; /* Mantieni la larghezza desiderata */
            margin-right: 2%; /* Margine per separare i due contenitori */
            margin-left: 3%;
        }

        .container-right {
            width: 70%; /* Mantieni la larghezza desiderata */
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
        }
        

        .card {
            width: 30%;
            margin: 10px;
            background-color: #49456d;
            border-radius: 10px;
            overflow: hidden;
            position: relative; /* Aggiunto per il posizionamento del figlio */
        }

        .card img {
            width: 100%;
            height: auto;
        }


          .container-top{
            margin-bottom: 30%;
          }

        .container-bottom {
            width: 90%;
          
            position: absolute; /* Posizionamento assoluto */
            bottom: 10px; /* Margine dal fondo */
            right: 10px; /* Margine dal lato destro */
            display: flex; /* Abilita il Flexbox */
            align-items: center; /* Allinea verticalmente gli elementi al centro */
            
            justify-content: space-between; 
            padding: 10px;
         }  

        .star-container {
          margin-top: 10%;
          margin-right: 10%;
          padding: 10px;
        }
               

        .buttons {
            padding: 10px;
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



      .form-select[multiple] {
            height: auto; /* Imposta un'altezza fissa se necessario */
            min-height: 100px; /* Altezza minima per visualizzare piÃÂ¹ opzioni */
        }



        .search-bar-container {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }

        .search-bar {
            position: relative;
            width: 80%;
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

        /* Stile quando il form-select ÃÂ¨ attivo */
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

        .pagination-buttons {
    display: flex; /* Utilizza Flexbox per allineare i bottoni */
    justify-content: center; /* Centra i bottoni orizzontalmente */
    margin-top: 20px; /* Margine superiore per separare dai contenuti */
}

.page-button {
    background-color: #8a79c7; /* Colore di sfondo dei bottoni */
    color: #ffffff; /* Colore del testo */
    border: none; /* Rimuove il bordo predefinito */
    padding: 10px 15px; /* Padding per i bottoni */
    margin: 0 5px; /* Margine tra i bottoni */
    border-radius: 5px; /* Angoli arrotondati */
    cursor: pointer; /* Cambia il cursore al passaggio del mouse */
    transition: background-color 0.3s; /* Transizione per il colore di sfondo */
}

.page-button:hover {
    background-color: #79c7e3; /* Colore di sfondo al passaggio del mouse */
}

.page-button.active {
    background-color: #6a5fbf; /* Colore per il bottone attivo */
    font-weight: bold; /* Rende il testo del bottone attivo in grassetto */
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

          <form action="researchResults" method="post">

                <!-- Range Peso -->
                <div class="mb-4">
                    <label for="peso" class="form-label">Peso:</label>
                    <div class="d-flex">
                        <input type="number" name="peso-min" id="peso-min" placeholder="Min" min="0" max="1000" step="10" class="form-control me-2">
                        <input type="number" name="peso-max" id="peso-max" placeholder="Max" min="0" max="1000" step="10"  class="form-control">
                    </div>
                </div>

                <!-- Range Lunghezza -->
                <div class="mb-4">
                    <label for="lunghezza" class="form-label">Lunghezza:</label>
                    <div class="d-flex">
                        <input type="number" name="lunghezza-min" id="lunghezza-min" placeholder="Min" min="0" max="50" step="1" class="form-control me-2">
                        <input type="number" name="lunghezza-max" id="lunghezza-max" placeholder="Max" min="0" max="50" step="1" class="form-control">
                    </div>
                </div>

                <!-- Range Prezzo -->
                <div class="mb-4">
                    <label for="prezzo" class="form-label">Prezzo:</label>
                    <div class="d-flex">
                        <input type="number" name="prezzo-min" id="prezzo-min" placeholder="Min" min="0" max="9999999" step="100" class="form-control me-2">
                        <input type="number" name="prezzo-max" id="prezzo-max" placeholder="Max" min="0" max="9999999" step="100" class="form-control">
                    </div>
                </div>

                <!-- Select Ordinamento -->
                <div class="mb-4">
                    <label for="ordinamento" class="form-label">Ordinamento:</label>
                    <select class="form-select" name="ordinamento" id="ordinamento" aria-label="Default select example">
                        <option value="ordina_per_voto" selected>Per Voto</option>
                        <option value="ordina_per_prezzo">Per Prezzo</option>
                        <option value="ordina_per_nome">Per Nome</option>
                        <option value="ordina_per_peso">Per Peso</option>
                        <option value="ordina_per_lunghezza">Per Peso</option>
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
              if (treni != null && !treni.isEmpty()) {
                  for (Treno treno : treni) {
          %>

                  <div class="card">
                      <%if(treno.getMarca().equals("Treno RegionalGain")){%>
                      		<img src="${pageContext.request.contextPath}/treni/RG.jpg" class="card-img-top" alt="Treno">
                      <%}else{
                    	  if(treno.getMarca().equals("Treno xFurryFast")){%>
                    		  <img src="${pageContext.request.contextPath}/treni/FF.jpg" class="card-img-top" alt="Treno">
                      	<%}else{
                      		if(treno.getMarca().equals("Treno KargoModelz")){%>
                  		  <img src="${pageContext.request.contextPath}/treni/KM.jpg" class="card-img-top" alt="Treno">
                    	<%}
                      	}
                      }%>

                      <div class="card-body">
                            <div class="container-top">
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
                            </div>

                            <div class="container-bottom">
                                
                                <div class="star-container">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                </div>
                            
                                <div class="buttons">
                                    <form method="get" action="trainDetails">
                                        <input type="hidden" name="trenoId" value="<%= (treno != null) ? treno.getId() : 0 %>">
                                        <button type="submit" class="btn btn-info">Dettagli</button>
                                    </form>
                                </div>

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
    
<!-- Aggiungi i bottoni di navigazione -->
<div class="pagination-buttons">
    <% 
        Integer currentPage = (Integer) session.getAttribute("currentPage");
        Integer totalPages = (Integer) session.getAttribute("totalPages");
        
        if (currentPage == null) currentPage = 1;
        if (totalPages == null) totalPages = 1;
    %>
    
    <% for (int i = 1; i <= totalPages; i++) { %>
        <button 
            class="page-button <%= (i == currentPage) ? "active" : "" %>" 
            onclick="location.href='trainMarket?page=<%= i %>'">
            <%= i %>
        </button>
    <% } %>
</div>
    

</body>
</html>