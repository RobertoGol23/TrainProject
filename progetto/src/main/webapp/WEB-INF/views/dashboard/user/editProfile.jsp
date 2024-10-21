<%@ page language="java" contentType="text/html;charset=UTF-8"
	    pageEncoding="UTF-8"%>

<%@ include file="../../navbar.jsp" %>



<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Modifica Profilo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
        }
        .form-container {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
            margin: 0 auto;
        }
        .dashboard {
            margin-top: 50px;
            padding: 20px;
            border-radius: 10px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #79c7e3;
        }
        input {
            width: 94%;
            padding: 8px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #79c7e3;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
            cursor: pointer;
        }
        button:hover {
            background-color: #8a79c7;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
        }
        .delete-section {
            width: 300px;
            margin: 0 auto;
            margin-top: 20px;
            text-align: center;
        }
        .confirmation-panel {
            display: none;
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
        }
        .modal {
            display: none; 
            position: fixed; 
            z-index: 1; 
            left: 0;
            top: 0;
            width: 100%; 
            height: 100%; 
            overflow: auto; 
            background-color: rgb(0,0,0); 
            background-color: rgba(0,0,0,0.4); 
            padding-top: 60px; 
        }
        .modal-content {
            background-color: #49456d;
            margin: 5% auto; 
            padding: 20px;
            border: 1px solid #888;
            width: 80%; 
            max-width: 400px;
            color: #ffffff;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: red;
            cursor: pointer;
        }
        
        <!-- Aggiungi il CSS per il modale -->

	    .modal {
	        display: none; 
	        position: fixed; 
	        z-index: 1; 
	        left: 0;
	        top: 0;
	        width: 100%; 
	        height: 100%; 
	        background-color: rgba(0,0,0,0.4); 
	        padding-top: 60px; 
	    }
	    .modal-content {
	        background-color: #49456d;
	        margin: 5% auto; 
	        padding: 20px;
	        border: 1px solid #888;
	        width: 80%; 
	        max-width: 400px;
	        color: #ffffff;
	    }
	    .errore {
	    	color: red;
	    }
	
        
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<!-- Script per gestire il popup di conferma -->
	<script>
	    // Funzione per mostrare il modale
	    function showDeleteModal() {
	        document.getElementById('confirmDeleteModal').style.display = 'block';
	    }
	
	    // Funzione per chiudere il modale
	    function closeDeleteModal() {
	        document.getElementById('confirmDeleteModal').style.display = 'none';
	    }
	
	    // Funzione per confermare la cancellazione del profilo
	    function confirmDelete() {
	        var password = document.getElementById('deletePassword').value;
	
	        // Controlla se la password è stata inserita
	        if (password) {
	            // Se la password è valida, procedi con la cancellazione
	            window.location.href = "deleteUser?password=" + password;
	        } else {
	            alert("Per favore, inserisci la tua password.");
	        }
	    }
	
	    // Funzione per chiudere il modale cliccando fuori dal contenuto
	    window.onclick = function(event) {
	        var modal = document.getElementById('confirmDeleteModal');
	        if (event.target == modal) {
	            modal.style.display = "none";
	        }
	    }
	</script>
</head>
<body>

    <h1 align="center">Modifica Profilo</h1>

    <div class="form-container">
        <form action="editProfile" method="post">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" value="${user.nome}" required>

            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" value="${user.cognome}" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${user.email}" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Aggiorna Profilo</button>
        </form>
    </div>
	<div class="delete-section">
   		<button type="button" onclick="showDeleteModal()">Cancella Profilo</button>

		<%
			if(request.getAttribute("errorMessage")!=null)
			{
		%>
		        <div class="errore">${errorMessage} </div>
		<%
		    };
		%>
	</div>
    
	<!-- Modale per la conferma della password -->
	<div id="confirmDeleteModal" class="modal">
	    <div class="modal-content">
	        
	        <h2>Conferma Cancellazione</h2>
	        <p>Per favore, inserisci la tua password per confermare la cancellazione del profilo</p>
	        <form id="confirmDeleteForm">
	            <label for="deletePassword">Password:</label>
	            <input type="password" id="deletePassword" name="password" class="form-control" required>
	        </form>
	        <button type="button" class="btn btn-danger" onclick="confirmDelete()">Conferma</button>
	    </div>
	</div>

</body>
</html>
