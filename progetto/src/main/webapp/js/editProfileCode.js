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