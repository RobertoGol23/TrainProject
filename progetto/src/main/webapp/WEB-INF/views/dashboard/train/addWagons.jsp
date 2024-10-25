<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ include file="../../navbar.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Aggiungi vagoni</title>
    <style>
        .wagon-form {
		    margin-bottom: 10px;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
 		    background-color: #e8c3bc; /*arancione chiaro*/
/* 			background-color: white; */
		    height: 80px;
		    width: 430px;
		    color: black;
		}
        .wagon-form span {
            margin-left: 20px;
        }

		.add-button {
		  align-self: center;
		  background-color: #fff;
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
		  border-bottom-left-radius: 15px 255px;
		  border-bottom-right-radius: 225px 15px;
		  border-top-left-radius: 255px 15px;
		  border-top-right-radius: 15px 225px;
		  user-select: none;
		  -webkit-user-select: none;
		  touch-action: manipulation;
		  float: right;
		}
		
		.add-button:hover {
		  box-shadow: rgba(0, 0, 0, .3) 2px 8px 8px -5px;
		  transform: translate3d(0, 2px, 0);
		}
		
		.add-button:focus {
		  box-shadow: rgba(0, 0, 0, .3) 2px 8px 4px -6px;
		}
		
		
		.remove-button {
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
		  border-bottom-left-radius: 15px 255px;
		  border-bottom-right-radius: 225px 15px;
		  border-top-left-radius: 255px 15px;
		  border-top-right-radius: 15px 225px;
		  user-select: none;
		  -webkit-user-select: none;
		  touch-action: manipulation;
		  float: right;
		}
		
		.remove-button:hover {
		  box-shadow: rgba(0, 0, 0, .3) 2px 8px 8px -5px;
		  transform: translate3d(0, 2px, 0);
		}
		
		.remove-button:focus {
		  box-shadow: rgba(0, 0, 0, .3) 2px 8px 4px -6px;
		}
		
		/* Stile per la foto del vagone */
        .wagon-image {
            max-width: 100px;
            height: auto;
            border-radius: 5px;
            border-style: solid;
		  	border-width: 2px;
		  	border-color: black;
            
        }
        
        label, select {
        	margin-left: 20px;
        }
        
        .container {
        	width: 450px;
        	padding: 0px;
        }
        
        form {
        	width: 470px;
        }
    </style>
</head>
<body>
    <h1>Aggiungi vagoni al tuo Treno</h1>
	<div class="container">
    <form id="trainForm" action="addWagons" method="POST">
        <input type="hidden" name="idTreno" value="${idTreno}">
        
        <div id="preexistingWagons">
            ${vagoniHtml}
        </div>
        
        <div id="wagonsContainer"></div>
        
        <button type="submit">Modifica treno</button>
    </form>
</div>

<script>
 
    // Array di immagini associate a ciascun tipo di vagone
    const wagonImages = {
    		
    		
        'p': '${imgVagonePasseggeriPath}',
        'c': '${imgVagoneCargoPath}',
        'r': '${imgVagoneRistorantePath}',
        'h': '${imgLocomotivaPath}'
    };

    // Funzione per aggiungere un nuovo vagone con l'anteprima
    function addWagonAfter(position) {
        let div = document.createElement('div');
        div.classList.add('wagon-form');
        
        let select = document.createElement('select');
        select.name = 'wagons[]';

        // Aggiungi le opzioni
        select.innerHTML = `
            <option value="p">Vagone Passeggeri</option>
            <option value="c">Vagone Cargo</option>
            <option value="r">Vagone Ristorante</option>
            <option value="h">Locomotiva</option>
        `;
        
        // Aggiungi il div dell'anteprima immagine
        let imagePreview = document.createElement('img');
        imagePreview.classList.add('wagon-image');
        imagePreview.src = wagonImages[select.value];  // Imposta l'immagine iniziale
        div.appendChild(imagePreview);

        // Cambia immagine in base alla selezione
        select.addEventListener('change', function() {
            imagePreview.src = wagonImages[select.value];
        });

        // Pulsante per rimuovere
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

    document.querySelectorAll('.add-button').forEach(button => {
        button.addEventListener('click', function() {
            const position = parseInt(this.dataset.position, 10);
            addWagonAfter(position);
        });
    });

    updatePositions();
</script>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>
