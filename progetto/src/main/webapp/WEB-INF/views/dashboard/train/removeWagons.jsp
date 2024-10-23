<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Modifica Treno</title>
</head>
<body>
    <h1>Modifica Treno: ${trenoNome}</h1>

    <!-- Stampa direttamente l'HTML generato dal controller -->
    ${vagoniHtml}
     <input type="hidden" name="idTreno" value="${idTreno}">


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>

