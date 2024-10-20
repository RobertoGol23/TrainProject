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
            margin: 0; /* Rimuovi il margine di default */
        }

        .content {
            padding: 5%; /* Circa 20px, a seconda della larghezza dello schermo */
            border-radius: 10px;
            text-align: center;
            display: flex;
            flex-direction: column; /* Dispone i figli in colonna */
            justify-content: center; /* Centra il contenuto orizzontalmente */
            align-items: center; /* Centra il contenuto verticalmente */
            margin-top: -2%; /* Circa 20px, a seconda della altezza dello schermo */
        }

        .showSaldo {
            background-color: #49456d;
            padding: 1%; /* Circa 20px, a seconda della larghezza dello schermo */
           
            border-radius: 10px;
            width: 30%; /* Larghezza del div interno */
            height: auto; /* Altezza automatica in base al contenuto */
            
            display: flex;
            flex-direction: column; /* Stack items vertically */
            justify-content: space-between; /* Spazia gli elementi verticalmente */
            align-items: flex-start; /* Allinea gli elementi a sinistra */
        }

        .transazioni {
            margin-top: 2%; /* Distanza dal div sopra */
            background-color: #49456d; /* Colore diverso per differenziare */
            padding: 2%; /* Circa 20px, a seconda della larghezza dello schermo */

            border-radius: 10px; /* Aggiungi bordi arrotondati */
            
            width: 30%; /* Larghezza del div interno */
            height: auto; /* Altezza automatica in base al contenuto */

            display: flex;
            flex-direction: column; /* Stack items vertically */
            justify-content: space-between; /* Spazia gli elementi verticalmente */
            align-items: flex-start; /* Allinea gli elementi a sinistra */
        }
      
    </style>
</head>
<body>

    <div class="content">

        <div class="showSaldo">
            <h1 style="margin-top: 2%; margin-bottom: 3%; font-size: 1.5rem;"> Saldo Train-Bazaar</h1>
            <h1 style="font-size: 2.5rem; margin: 0;" > ${user.wallet} €</h1>
            <h1 style="font-size: 1.0rem; margin-top: 1%;"> Disponibile </h1>
        </div>


        <div class="transazioni">
            <h1 style="font-size: 1.0rem;"> Attività recenti </h1>
        </div>


    </div>
    



</body>
</html>