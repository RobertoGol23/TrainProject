<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="entity.user.User" %>
    <%@ include file="../../navbar.jsp" %> <!-- con .. si va indietro di una cartella-->

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wallet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }

        h1 {
            color: #8a79c7;
            text-align: center;
        }

        .content {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        
        
    </style>
</head>
<body>

    <h1>${user.nome}, ecco il tuo wallet!</h1>


</body>
</html>