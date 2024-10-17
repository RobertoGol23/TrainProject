<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </style>
</head>
<body>
    <h1>Crea il tuo Treno</h1>
    
    <!-- Form per inviare i dati -->
    <form id="trainForm" action="creaTrenoProva" method="POST">
        <!-- Locomotiva -->
        <div class="wagon-form">
            <label>Locomotiva:</label>
            <input type="hidden" name="wagons[]" value="h">
            <span>Locomotiva</span>
        </div>

        <!-- Sezione per i vagoni aggiunti dinamicamente -->
        <div id="wagonsContainer"></div>

        <!-- Pulsante per aggiungere nuovi vagoni -->
        <button type="button" id="addWagon">+ Aggiungi Vagone</button>
        
        <!-- Pulsante per inviare il form -->
        <button type="submit">Crea Treno</button>
    </form>

    <script>
        document.getElementById('addWagon').addEventListener('click', function() {
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
            });

            // Aggiungi il menu a tendina e il pulsante al div
            div.appendChild(select);
            div.appendChild(removeButton);

            // Aggiungi il nuovo div al container dei vagoni
            document.getElementById('wagonsContainer').appendChild(div);
        });
    </script>
</body>
</html>