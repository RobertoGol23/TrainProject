<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="utility.TrenoUtility" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import="entity.dao.TrenoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>
<%@ include file="../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

        .container {
            display: flex;
            margin: 10px auto;
            justify-content: center;
            align-items: flex-start; /* Allinea i contenuti in cima */
            max-width: 1200px; /* Limita la larghezza massima */
        }

        .container-left {
            width: 30%;
            padding: 20px;
            background-color: #3b3a5d;
            border-radius: 10px;
            margin-right: 20px; /* Spazio tra i due contenitori */
        }

        .container-right {
            width: 70%;
            padding: 20px;
            background-color: #3b3a5d;
            border-radius: 10px;
            display: flex;
            flex-wrap: wrap; /* Permette di andare a capo */
            justify-content: flex-start; /* Allinea le card a sinistra */
        }

        
        .card {
            width: 30%; /* Larghezza fissa per le card */
            margin: 10px; /* Spazio tra le card */
            background-color: #49456d;
            border-radius: 10px;
            overflow: hidden; /* Arrotonda gli angoli delle card */
        }
        .card img {
            width: 100%; /* Larghezza piena dell'immagine */
            height: auto; /* Mantiene l'aspetto */
        }

        .buttons {
            display: flex;
            justify-content: flex-end; /* Allinea i pulsanti a destra */
            padding: 10px; /* Padding attorno ai pulsanti */
            margin-top: auto; /* Spinge i pulsanti verso il basso */
        }

        
        button {
            background-color: #8a79c7;
            border: none;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 5px; /* Spazio tra i pulsanti */
            height: 40px; /* Altezza fissa per i pulsanti */
        }

        button:hover {
            background-color: #79c7e3;
        }

        .search-bar-container {
            display: flex;
            justify-content: center;
            margin: 20px 0; /* Margine superiore per separare dal titolo */
        }

        .search-bar {
            position: relative;
            width: 60%; /* Imposta una larghezza per la barra di ricerca */
        }

        .search-bar input {
            width: 100%; /* Larghezza piena dell'input */
            height: 55px;
            border-radius: 10px;
            padding-left: 40px; /* Spazio per l'icona di ricerca */
        }

        .search-bar .fa-search {
            position: absolute;
            top: 15px;
            left: 15px;
            color: #9ca3af;
        }




        .form-range {
            -webkit-appearance: none; /* Rimuove lo stile predefinito in Safari */
            appearance: none; /* Rimuove lo stile predefinito */
            height: 10px; /* Altezza della barra */
            border-radius: 5px; /* Bordo arrotondato */
            background: #ddd; /* Colore della barra */
            outline: none; /* Rimuove il contorno al focus */
        }
    
        .form-range::-webkit-slider-thumb {
            -webkit-appearance: none; /* Rimuove lo stile predefinito in Safari */
            appearance: none; /* Rimuove lo stile predefinito */
            width: 20px; /* Larghezza del cursore */
            height: 20px; /* Altezza del cursore */
            border-radius: 50%; /* Cursore arrotondato */
            background: #007bff; /* Colore del cursore */
            cursor: pointer; /* Cambia il cursore al passaggio del mouse */
        }
    
        .form-range::-moz-range-thumb {
            width: 20px; /* Larghezza del cursore */
            height: 20px; /* Altezza del cursore */
            border-radius: 50%; /* Cursore arrotondato */
            background: #007bff; /* Colore del cursore */
            cursor: pointer; /* Cambia il cursore al passaggio del mouse */
        }
    
        .form-range::-ms-thumb {
            width: 20px; /* Larghezza del cursore */
            height: 20px; /* Altezza del cursore */
            border-radius: 50%; /* Cursore arrotondato */
            background: #007bff; /* Colore del cursore */
            cursor: pointer; /* Cambia il cursore al passaggio del mouse */
        }
    </style>
</head>
<body>

    <h1><b>Train Market</b></h1>

    <!-- Container barra di ricerca -->
    <div class="search-bar-container">
        <div class="search-bar">
            <i class="fa fa-search"></i>
            <input type="text" placeholder="Search anything...">
        </div>
    </div>

    <!-- Container principale -->
    <div class="container">

        <!-- Container del FORM a sinistra -->
        <div class="container-left">
            

            <!-- 

            RANGE: prezzo, peso, lunghezza
            SEARCH BAR: sigla, nome
            ORDINAMENTO: per prezzo, lunghezza, peso, 
            
             -->

            <form action="/search" method="get">
                <label for="tipo-treno"> Marca del treno </label>
                <select id="tipo-treno" name="tipo-treno">
                    <option value="KargoModelz">KargoModelz</option>
                    <option value="RegionalGain">RegionalGain</option>
                    <option value="xFurryFast">xFurryFast</option>
                </select>

                <br><br>

                <!-- <label for="prezzo-min">Prezzo Min:</label>
                <input type="number" id="prezzo-min" name="prezzo-min" placeholder="Min" value="0">
                <label for="prezzo-max">Prezzo Max:</label>
                <input type="number" id="prezzo-max" name="prezzo-max" placeholder="Max">
                <br><br> -->

                <div class="d-flex flex-column align-items-center" style="width: 80%; margin: 0 auto;">
                    <label for="range" class="form-label"> Prezzo </label>
                    <input type="range" class="form-range" min="0.0" max="1000000" step="10000" id="range" oninput="updateValue(this.value)" style="width: 100%;">
                    <span id="rangeValue"> 0 </span> <!-- Valore iniziale -->
                </div>
                
                <script>
                    // Funzione per aggiornare il valore visualizzato con formattazione
                    function updateValue(val) {
                        const formattedValue = new Intl.NumberFormat('it-IT', { style: 'currency', currency: 'EUR' }).format(val);
                        document.getElementById('rangeValue').textContent = formattedValue;
                    }
                </script>


                <label for="dimensioni-min">Dimensioni Min:</label>
                <input type="number" id="dimensioni-min" name="dimensioni-min" placeholder="Min">
                <label for="dimensioni-max">Dimensioni Max:</label>
                <input type="number" id="dimensioni-max" name="dimensioni-max" placeholder="Max">
                <br><br>

                <label for="tipo-casa">Tipo di casa</label><br>
                <input type="checkbox" id="appartamenti" name="tipo-casa" value="appartamenti">
                <label for="appartamenti">Appartamenti, attici e duplex</label><br>
                <input type="checkbox" id="ville" name="tipo-casa" value="ville">
                <label for="ville">Case, ville e villette</label><br><br>

                <input type="submit" value="Cerca">
            </form>
        </div>

        <!-- Container delle card -->
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
                        <img src="${pageContext.request.contextPath}/images/treni/locomotivaFurryFast.jpg" class="card-img-top" alt="...">
                        
                        <div class="card-body">
                            <h5><%= (treno != null) ? treno.getNome() : "Treno non disponibile" %></h5>
                           	<p>Codice: <%= (treno != null) ? treno.getId() : "N/A" %></p>
                            <p>Marca: <%= (treno != null) ? treno.getMarca() : "N/A" %></p>
                            <p>Sigla di composizione: <%= (treno != null) ? tu.getSigla(treno) : "N/A" %></p>
                            <p>Prezzo: <%= (treno != null) ? treno.getPrezzoTotaleTreno() : 0 %> euro</p>
                            <p>Peso: <%= (treno != null) ? treno.getPesoTotaleTreno() : 0 %> tonnellate</p>
                            <p>Peso Trasportabile: <%= (treno != null && treno.getLocomotiva() != null) ? treno.getLocomotiva().getPesoTrainabile() : 0 %> tonnellate</p>
                            <p>Numero di Persone: <%= (treno != null) ? treno.getPasseggeriTotali() : 0 %></p>
                            <p>Lunghezza del treno: <%= (treno != null) ? (tu.getSigla(treno)).length() : 0 %></p>
                            <p>Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia((Long)treno.getId()) : 0 %></p>
                            <div class="buttons">
                                
                                <form method="get" action="trainDetails">
                                    <input type="hidden" name="trenoId" value="<%= (treno != null) ? treno.getId() : 0 %>">
                                    <button type="submit">Dettagli</button>
                                </form>

                            </div>
                        </div>
                    </div>
            <%
                    }
                } else {
            %>
                    <h1 style="margin-top: 2%; font-size: 1.5rem;">Ci dispiace, non ci sono treni disponibili al momento.</h1>
            <%
                }
            } catch (Exception e) {
                e.printStackTrace(); // Gestisci eventuali eccezioni
            } finally {
                if (context != null) {
                    context.close(); // Chiudi il contesto
                }
            }
            %>
        </div> 
    </div>
</body>
</html>
