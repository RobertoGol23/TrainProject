<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <%@ include file="../../navbar.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Crea Treno</title>
    <style>
    	h2 {
        	color: #e1418b; /* Colore dei titoli */
        }
    
        .wagon-form {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #e8c3bc; /* arancione chiaro */
            height: 75px;
            width: 300px;
            color: black;
        }
        .wagon-form span {
            margin-left: 10px;
        }
        
        .add-button, .remove-button {
		  align-self: center;
		  background-color: #ffffff;
		  background-image: none;
		  background-position: 0 90%;
		  background-repeat: repeat no-repeat;
		  background-size: 4px 3px;
		  border-radius: 15px 225px 255px 15px 15px 255px 225px 15px;
		  border-style: solid;
		  border-width: 2px;
		  box-shadow: rgba(0, 0, 0, .2) 15px 28px 25px -18px;
		  box-sizing: border-box;
		  color: #41403e;
		  cursor: pointer;
		  display: inline-block;
		  font-family: Neucha, sans-serif;
		  font-size: 1rem;
		  line-height: 23px;
		  outline: none;
		  padding: .75rem;
		  text-decoration: none;
		  transition: all 235ms ease-in-out;
		  border-bottom-left-radius: 10px 255px;
		  border-bottom-right-radius: 225px 15px;
		  border-top-left-radius: 255px 15px;
		  border-top-right-radius: 15px 225px;
		  user-select: none;
		  -webkit-user-select: none;
		  touch-action: manipulation;
		  float: right;
		}
        .add-button:hover, .remove-button:hover {
            box-shadow: rgba(0, 0, 0, .3) 2px 8px 8px -5px;
            transform: translate3d(0, 2px, 0);
        }
        label, select {
            margin-top: 15px;
        }
        
        /* Altri stili */
        .label {
            display: block;
            margin-bottom: 8px;
            margin-top: 5px;
            color: ivory; 
            font-weight: bold;
            cursor: pointer;
        }
        
        .select-fabbrica {
        	margin-top: 5px;
            padding: 12px;
            margin-bottom: 50px;
            border-radius: 5px;
            border: none;
            font-size: 16px;
            width: 100%;
        }
        
        input {
		    width: 100%;
		    padding: 10px;
		    margin-bottom: 10px;
		    font-size: 15px;
		    border: none;
		    border-radius: 5px;
		}
       
        .main-container {
            display: flex; /* Usa Flexbox per disporre i div */
            justify-content: center; /* Centra orizzontalmente il contenuto */
            align-items: flex-start; /* Allinea gli elementi in alto */
            flex-wrap: wrap; /* Permette il wrapping degli elementi */
            width: 100%; /* Assicurati che occupi tutta la larghezza */
            margin: 0 auto; /* Margine automatico per centrare */
            max-width: 1300px; /* Larghezza massima per il contenitore principale */
        }
        
        .left-content,
        .right-content {
            width: 25%; /* Ridotto per consentire più spazio */
            padding: 20px; /* Padding per i div */
            background-color: #3b3a5d; /* Colore di sfondo */
            color: #ffffff; /* Colore del testo */
            border-radius: 10px; /* Bordi arrotondati */
            opacity: 0; /* Inizialmente invisibile */
            visibility: hidden; /* Non visibile */
            transition: opacity 0.5s ease, visibility 0.5s; /* Transizione per l'opacità e la visibilità */
        }
        
        .left-content {
            margin-right: 20px; /* Spazio tra left-content e container */
            width: 400px;
        }
        
        .right-content {
            margin-left: 20px; /* Spazio tra right-content e container */
            width: 400px;
        }
        
        .container {
            width: 350px; /* Larghezza fissa per il contenitore del modulo */
            background-color: #49456d; /* Colore di sfondo */
            padding: 0px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            position: relative; /* Posizionamento relativo per il contenitore */
        	margin-bottom: 150px;
        }
        form {
        	padding: 20px;
        	width: 340px;
        }
        
        .error-message {
            color: red;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Crea il tuo Treno</h1>
    
    
    <div class="main-container">
    	<div class="left-content" id="left-content" style="opacity: 0; visibility: hidden;">
       	 	<h2>Selezione della Fabbrica</h2>
       		<p style="margin-top: 50px">Attualmente in questo tool sono previste 3 fabbriche per la creazione di treni standard (modificabili successivamente). Le fabbriche attualmente disponibili sono: xFurryFast, RegionalGain, KargoModelz.</p>
    	</div>
    	<div class="container">
            <div >
            	<form id="trainForm" action="creaTrenoDinamico" method="POST">
                    <label for="nomeTreno" class="label">Nome del Treno</label>
                    <input type="text" maxlength="25" id="nomeTreno" name="nomeTreno" required>
                    <label for="fabbrica" class="label">Seleziona Fabbrica</label>
                    <select id="fabbrica" name="fabbrica" required class="select-fabbrica">
                        <option value="1">XFurryFast</option>
                        <option value="2">KargoModelz</option>
                        <option value="3">RegionalGain</option>
                    </select>
                    <!-- Locomotiva -->
				    <div class="wagon-form">
				        <label>Locomotiva:</label>
				        <input type="hidden" name="wagons[]" value="h">
				        <span>Locomotiva</span>
				        
				        <!-- Pulsante per aggiungere un vagone subito dopo la locomotiva -->
				        <span class="add-button" onclick="addWagonAfter(0)"> + </span>
				    </div>
				
				    <!-- Sezione per i vagoni aggiunti dinamicamente -->
				    <div id="wagonsContainer"></div>
				    <button type="submit">Crea Treno</button>
				</form>
				<%
		        String error = (String) request.getAttribute("error");
		        if (error != null && !error.isEmpty()) {
			    %>
			        <div class="error-message">
			            <p><%= error %></p>
			        </div>
			    <%
			        }
			    %>
            </div>
        </div>
    	<div class="right-content" id="right-content" style="opacity: 0; visibility: hidden;">
            <h2>Nome del Treno</h2>
            <p>Dare un nome ad un treno ti permetterà di distinguere più facilmente i treni all'interno delle tue collezioni.</p>
        </div>
    <!-- Pulsante per inviare il form -->
   </div>


<script>
    function addWagonAfter(position) {
        let div = document.createElement('div');
        div.classList.add('wagon-form');
        
        let select = document.createElement('select');
        select.name = 'wagons[]';

        select.innerHTML = `
            <option value="p">Vagone Passeggeri</option>
            <option value="c">Vagone Cargo</option>
            <option value="r">Vagone Ristorante</option>
            <option value="h">Locomotiva</option>
        `;
        
        let removeButton = document.createElement('span');
        removeButton.classList.add('remove-button');
        removeButton.innerText = ' - ';
        removeButton.addEventListener('click', function() {
            div.remove();
            updatePositions();
        });

        div.appendChild(select);
        div.appendChild(removeButton);
        
        let addButton = document.createElement('span');
        addButton.classList.add('add-button');
        addButton.innerText = ' + ';
        addButton.dataset.position = position + 1;
        addButton.addEventListener('click', function() {
            addWagonAfter(parseInt(this.dataset.position, 10));
        });

        div.appendChild(addButton);

        let allWagons = document.querySelectorAll('.wagon-form');
        if (position >= allWagons.length) {
            document.getElementById('wagonsContainer').appendChild(div);
        } else {
            allWagons[position].parentNode.insertBefore(div, allWagons[position].nextSibling);
        }

        updatePositions();
    }
    
    function updatePositions() {
        let allWagons = document.querySelectorAll('.wagon-form');
        allWagons.forEach((wagon, index) => {
            let addButton = wagon.querySelector('.add-button');
            if (addButton) {
                addButton.dataset.position = index;
            }
        });
    }
    
    const toggleInput = document.getElementById('nomeTreno');
    const sideDiv = document.getElementById('right-content');

    toggleInput.addEventListener('focus', () => {
        sideDiv.style.opacity = '1'; // Rendi visibile
        sideDiv.style.visibility = 'visible'; // Imposta visibilità a visible
    });

    toggleInput.addEventListener('blur', () => {
        sideDiv.style.opacity = '0'; // Rendi invisibile
        sideDiv.style.visibility = 'hidden'; // Imposta visibilità a hidden
    });

    const toggleInput3 = document.getElementById('fabbrica');
    const sideDiv3 = document.getElementById('left-content');

    toggleInput3.addEventListener('focus', () => {
        sideDiv3.style.opacity = '1'; // Rendi visibile
        sideDiv3.style.visibility = 'visible'; // Imposta visibilità a visible
    });

    toggleInput3.addEventListener('blur', () => {
        sideDiv3.style.opacity = '0'; // Rendi invisibile
        sideDiv3.style.visibility = 'hidden'; // Imposta visibilità a hidden
    });
</script>

</body>
</html>
