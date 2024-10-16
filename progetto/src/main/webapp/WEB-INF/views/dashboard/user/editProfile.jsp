<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    </style>
    <script>
        function showConfirmationPanel() {
            document.getElementById('confirmationPanel').style.display = 'block';
        }
        function hideConfirmationPanel() {
            document.getElementById('confirmationPanel').style.display = 'none';
        }
        function showErrorModal(message) {
            document.getElementById('errorModal').style.display = 'block';
            document.getElementById('errorMessage').innerText = message;
        }
        function closeErrorModal() {
            document.getElementById('errorModal').style.display = 'none';
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
        <h2>Elimina il tuo account</h2>
        <button type="button" onclick="showConfirmationPanel()">Cancella il mio account</button>

        <div id="confirmationPanel" class="confirmation-panel">
            <h3>Conferma cancellazione account</h3>
            <form action="deleteUser" method="post">
                <label for="deletePassword">Inserisci la tua password:</label>
                <input type="password" id="deletePassword" name="password" required>

                //TODO: DA SISTEMARE TUTTO
                <%
                    String errorMessage = (String) session.getAttribute("errorMessage");
                    session.removeAttribute("errorMessage");
                    if (errorMessage != null && !errorMessage.isEmpty())
                    {
                        %>
                        <p style="color: red"><%= errorMessage %></p>
                        <%
                    }
                %>
                
                <button type="submit">Conferma Cancellazione</button>
                <button type="button" onclick="hideConfirmationPanel()">Annulla</button>
            </form>
        </div>
    </div>


    <!-- //TODO: Controllo password -->

    <div align="center" class="dashboard"><a href="/train-bazar/dashboard/home">Torna alla Dashboard</a></div>


</body>
</html>
