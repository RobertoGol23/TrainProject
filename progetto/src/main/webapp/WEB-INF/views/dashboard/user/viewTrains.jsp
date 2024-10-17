<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>I Miei Treni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #8a79c7;
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
            color: #ffffff;
        }
        
        table {
            width: 100%; /* Imposta la larghezza della tabella al 100% */
            background-color: #49456d;
            color: #ffffff;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #79c7e3;
            color: #2e2b4f;
        }
        div {
        	width: 50%;
        }
    </style>
</head>
<body>
    <h1>I Miei Treni</h1>

    <!-- Includiamo l'HTML generato dal controller -->
    <div>
        ${trainsTable}
    </div>

</body>
</html>
