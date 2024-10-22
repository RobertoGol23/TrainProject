<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import="entity.dao.TrenoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="../navbar.jsp" %>

    <title>Market dei Treni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #1d009c;
            margin: 0;
        }
        
        *,
        *:before,
        *:after {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
        }
        .wrapper {
        position: relative;
        width: 80%;
        background-color: #ff0000;
        padding: 50px 40px 20px 40px;
        border-radius: 10px;
        }
        .container {
        position: relative;
        width: 100%;
        height: 100px;
        margin-top: 30px;
        }
        input[type="range"] {
        -moz-appearance: none;
        appearance: none;
        width: 100%;
        outline: none;
        position: absolute;
        margin: auto;
        top: 0;
        bottom: 0;
        background-color: transparent;
        pointer-events: none;
        }
        .slider-track {
        width: 100%;
        height: 5px;
        position: absolute;
        margin: auto;
        top: 0;
        bottom: 0;
        border-radius: 5px;
        background-color: aquamarine;
        }
        input[type="range"]::-webkit-slider-runnable-track {
        -webkit-appearance: none;
        height: 5px;
        }
        input[type="range"]::-moz-range-track {
        -moz-appearance: none;
        height: 5px;
        }
        input[type="range"]::-ms-track {
        appearance: none;
        height: 5px;
        }
        input[type="range"]::-webkit-slider-thumb {
        -webkit-appearance: none;
        height: 1.7em;
        width: 1.7em;
        background-color: #3264fe;
        cursor: pointer;
        margin-top: -9px;
        pointer-events: auto;
        border-radius: 50%;
        }
        input[type="range"]::-moz-range-thumb {
        height: 1.7em;
        width: 1.7em;
        cursor: pointer;
        border-radius: 50%;
        background-color: #3264fe;
        pointer-events: auto;
        border: none;
        }
        input[type="range"]::-ms-thumb {
        appearance: none;
        height: 1.7em;
        width: 1.7em;
        cursor: pointer;
        border-radius: 50%;
        background-color: #3264fe;
        pointer-events: auto;
        }
        input[type="range"]:active::-webkit-slider-thumb {
        background-color: #ffffff;
        border: 1px solid #3264fe;
        }
        .values {
        background-color: #3264fe;
        width: 32%;
        position: relative;
        margin: auto;
        padding: 10px 0;
        border-radius: 5px;
        text-align: center;
        font-weight: 500;
        font-size: 25px;
        color: #ffffff;
        }
        .values:before {
        content: "";
        position: absolute;
        height: 0;
        width: 0;
        border-top: 15px solid #3264fe;
        border-left: 15px solid transparent;
        border-right: 15px solid transparent;
        margin: auto;
        bottom: -14px;
        left: 0;
        right: 0;
        }


    </style>
</head>
<body>

    <h1><b>Train Market</b></h1>

    <div class="search-bar-container">
        <div class="search-bar">
            <input type="text" placeholder="Search anything...">
        </div>
    </div>

    <div class="container">

        <div class="container-left">
            

                
          
          
          







                

        </div>

    </div>

</body>
</html>
