<%@ page language="java" contentType="text/html;charset=UTF-8"
	    pageEncoding="UTF-8"%>

<%@ include file="../../navbar.jsp" %>



<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userPagesStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/editProfileStyle.css?v=1.x">
    <title>Modifica Profilo</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Script per gestire i popup di conferma -->
<script>
    // Funzione per mostrare il modale di cancellazione
    function showDeleteModal() {
        document.getElementById('confirmDeleteModal').style.display = 'block';
    }

    // Funzione per mostrare il modale di modifica
    function showUpdateModal() {
        document.getElementById('confirmUpdateModal').style.display = 'block';
    }

    // Funzione per chiudere il modale
    function closeModal(modalId) {
        document.getElementById(modalId).style.display = 'none';
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

    function confirmUpdate() {
    var updatePassword = document.getElementById('updatePassword').value;

    // Controlla se la password di conferma è stata inserita
    if (updatePassword) {
        // Copia la password nel campo nascosto del form principale
        document.getElementById('passwordConfirm').value = updatePassword;

        // Invia il form principale
        document.getElementById('editProfileForm').submit();
    } else {
        alert("Per favore, inserisci la tua password per confermare.");
    }
}

    // Funzione per chiudere i modali cliccando fuori dal contenuto
    window.onclick = function(event) {
        var deleteModal = document.getElementById('confirmDeleteModal');
        var updateModal = document.getElementById('confirmUpdateModal');

        if (event.target == deleteModal) {
            deleteModal.style.display = "none";
        } else if (event.target == updateModal) {
            updateModal.style.display = "none";
        }
    }
</script>
</head>
<body>
    <div class="container">
    
    
    <!-- Form di aggiornamento del profilo -->
    	<h1 align="center">Modifica Profilo</h1>
	    <form id="editProfileForm" action="editProfile" method="post">
	        <label for="nome">Nome</label>
	        <input type="text" id="nome" name="nome" value="${user.nome}" required>
	
	        <label for="cognome">Cognome</label>
	        <input type="text" id="cognome" name="cognome" value="${user.cognome}" required>
	
	        <label for="email">Email</label>
	        <input type="email" id="email" name="email" value="${user.email}" required>
	
	        <label for="password">Password</label>
	        <input type="password" id="password" name="password" placeholder="Inserisci qui la tua password"  required>
	
	        <!-- Campo nascosto per la conferma della password che viene popolato dal modale -->
	        <input type="hidden" id="passwordConfirm" name="passwordConfirm" required>
	
	        <!-- Aggiungi un pulsante che apre il modale per confermare l'aggiornamento -->
	        <button type="button" onclick="showUpdateModal()">Aggiorna Profilo</button>
	    </form>
	</div>

    <div class="delete-section">
        <button type="button" onclick="showDeleteModal()">Cancella Profilo</button>

        <%
            if(request.getAttribute("errorMessage")!=null) {
        %>
            <div class="errore">${errorMessage}</div>
        <%
            }
        %>
    </div>

    <!-- Modale per la conferma della cancellazione -->
    <div id="confirmDeleteModal" class="modal">
        <div class="modal-content">
            <h2>Conferma Cancellazione</h2>
            <p>Per favore, inserisci la tua password per confermare la cancellazione del profilo</p>
            <form id="confirmDeleteForm" style="padding: 0px; margin:0px; width: 100%">
                <label for="deletePassword">Password:</label>
                <input type="password" id="deletePassword" name="password" class="form-control" required>
            </form>
            <button type="button" class="btn-primary" onclick="confirmDelete()">Conferma</button>
        </div>
    </div>

    <!-- Modale per la conferma dell'aggiornamento del profilo -->
	<div id="confirmUpdateModal" class="modal">
	    <div class="modal-content">
	        <h2>Conferma Modifica</h2>
	        <p>Per favore, inserisci la tua password per confermare la modifica del profilo</p>
	        <label for="updatePassword">Password:</label>
	        <input type="password" id="updatePassword" class="form-control" required>
	        <button type="button" class="btn-primary" onclick="confirmUpdate()">Conferma</button>
	    </div>
	</div>

</body>

</html>
