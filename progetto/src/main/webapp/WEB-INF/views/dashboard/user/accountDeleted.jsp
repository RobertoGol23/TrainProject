<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Cancellato</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
        }
        h1 {
            color: #e37479;
        }
        p {
            color: #79c7e3;
            margin-bottom: 20px;
        }
        button {
            padding: 10px 20px;
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
            color: #2e2b4f;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h1>Ci mancherai!</h1>
    <p>Il tuo account è stato cancellato con successo.</p>
    <p>Ci piacerebbe rivederti presto, ti invitiamo a iscriverti nuovamente.</p>
    
    <!-- TODO: /train-baazar/register -->
    <form action="/train-bazaar/register" method="get">
        <button type="submit">Registrati</button>
    </form>

</body>
</html>