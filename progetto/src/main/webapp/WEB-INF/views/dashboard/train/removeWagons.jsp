<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablesStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <style>
    	form {
    		width: 450px;
    	}

    	td {
    		border-top: 1px solid white;
    		border-bottom: 1px solid white;
    	}
    	
    	table {
    		border-bottom: 1px solid white;
    	}
    	
	
		  /* Stile per il checkbox nascosto */
		  .custom-checkbox {
		    display: inline-block;
		    width: 20px;
		    height: 20px;
		    position: relative;
		    appearance: none;
		    border: 2px solid #000;
		    cursor: pointer;
		    vertical-align: middle;
		  }
		
		  /* Effetto X rosso sopra il checkbox selezionato */
		  .custom-checkbox:checked::after {
		    content: "";
		    position: absolute;
		    top: 2px;
		    left: 2px;
		    width: 16px;
		    height: 16px;
		    background: transparent;
		    display: block;
		  }
		
		  /* Linea diagonale della X */
		  .custom-checkbox:checked::before,
		  .custom-checkbox:checked::after {
		    position: absolute;
		    content: "";
		    width: 100%;
		    height: 2px;
		    background-color: red;
		    top: 50%;
		    left: 0;
		  }
		
		  /* Rotazioni per formare la X */
		  .custom-checkbox:checked::before {
		    transform: rotate(45deg);
		  }
		
		  .custom-checkbox:checked::after {
		    transform: rotate(-45deg);
  		}
		
    </style>
    <title>Rimuovi Vagoni</title>
</head>
<body>
    <h1>Rimuovi vagoni dal tuo Treno <br> ${trenoNome}</h1>

    <!-- Stampa direttamente l'HTML generato dal controller -->
    ${vagoniHtml}
    
    
</body>
</html>

