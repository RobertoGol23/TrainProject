<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crea Treno</title>
    <style>
        .wagon-form {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .wagon-form span {
            margin-left: 10px;
        }
        .add-button {
            display: inline-block;
            margin: 5px;
            cursor: pointer;
            color: blue;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Crea il tuo Treno</h1>

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
        <button type="submit">Crea Treno</button>
    </form>

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

            // Aggiungi le opzioni al menu a tendina
            select.appendChild(option1);
            select.appendChild(option2);
            select.appendChild(option3);

            // Pulsante per rimuovere il vagone
            let removeButton = document.createElement('button');
            removeButton.type = 'button';
            removeButton.innerText = 'Rimuovi';
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
            addButton.innerText = '+ Aggiungi dopo';
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
</body>
</html>
