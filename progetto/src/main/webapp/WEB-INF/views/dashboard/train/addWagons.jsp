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
	    background-color: #f9f9f9;
	    height: 100px;
	    width: 350px;
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
		
		
		.container {
			width: 20%;
		}
		
		/* Style for wagon images */
        .wagon-image {
            max-width: 100px;
            height: auto;
            border-radius: 5px;
        }
        
    </style>
</head>
<body>
    <h1>Aggiungi vagoni al tuo Treno</h1>
	<div class="container">
    <!-- Form per inviare i dati -->
    <form id="trainForm" action="addWagons" method="POST">
    	<input type="hidden" name="idTreno" value="${idTreno}">

        <!-- Sezione per i vagoni preesistenti -->
        <div id="preexistingWagons">
           	${vagoniHtml}
        </div>

        <!-- Sezione per i vagoni aggiunti dinamicamente -->
        <div id="wagonsContainer"></div>

        <!-- Pulsante per inviare il form -->
        <button type="submit">Modifica treno</button>
    </form>
	</div>
    <script>
        // Funzione per aggiungere un nuovo vagone dopo la posizione specificata
        function addWagonAfter(position) {
            // Crea un nuovo div per il vagone
            let div = document.createElement('div');
            div.classList.add('wagon-form');
            
            // Crea il menu a tendina per la selezione del vagone
            let select = document.createElement('select');
            select.name = 'wagons[]';  // Associa il selettore al nome dell'array wagons
            
            // Opzione Vagone Passeggero
            let option1 = document.createElement('option');
            option1.value = 'p';
            option1.text = 'Vagone Passeggero';
            
            // Opzione Vagone Cargo
            let option2 = document.createElement('option');
            option2.value = 'c';
            option2.text = 'Vagone Cargo';
            
            // Opzione Vagone Ristorante
            let option3 = document.createElement('option');
            option3.value = 'r';
            option3.text = 'Vagone Ristorante';
            
         	// Opzione Locomotiva
            let option4 = document.createElement('option');
            option4.value = 'h';
            option4.text = 'Locomotiva';

            // Aggiungi le opzioni al menu a tendina
            select.appendChild(option1);
            select.appendChild(option2);
            select.appendChild(option3);
            select.appendChild(option4);

            // Pulsante per rimuovere il vagone
            let removeButton = document.createElement('span');
            removeButton.type = 'span';
            removeButton.classList.add('remove-button');
            removeButton.innerText = ' - ';
            removeButton.addEventListener('click', function() {
                div.remove();
                updatePositions();
            });

            // Aggiungi il menu a tendina e il pulsante al div
            div.appendChild(select);
            div.appendChild(removeButton);

            // Aggiungi un pulsante "Aggiungi dopo" anche per il nuovo vagone
            let addButton = document.createElement('span');
            addButton.classList.add('add-button');
            addButton.innerText = ' + ';
            addButton.dataset.position = position + 1;
            addButton.addEventListener('click', function() {
                addWagonAfter(parseInt(this.dataset.position, 10));
            });

            // Aggiungi il pulsante al div
            div.appendChild(addButton);

            // Trova la posizione in cui aggiungere il nuovo vagone
            let allWagons = document.querySelectorAll('.wagon-form');
            if (position >= allWagons.length) {
                document.getElementById('wagonsContainer').appendChild(div);
            } else {
                allWagons[position].parentNode.insertBefore(div, allWagons[position].nextSibling);
            }

            // Aggiorna le posizioni dopo l'aggiunta
            updatePositions();
        }

        // Funzione per aggiornare le posizioni dei pulsanti "Aggiungi dopo"
        function updatePositions() {
            let allWagons = document.querySelectorAll('.wagon-form');
            allWagons.forEach((wagon, index) => {
                let addButton = wagon.querySelector('.add-button');
                if (addButton) {
                    addButton.dataset.position = index;
                }
            });
        }

        // Aggiungi i listener per i pulsanti "Aggiungi dopo" esistenti
        document.querySelectorAll('.add-button').forEach(button => {
            button.addEventListener('click', function() {
                const position = parseInt(this.dataset.position, 10);
                addWagonAfter(position);
            });
        });

        // Inizializza le posizioni al caricamento della pagina
        updatePositions();
    </script>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>
