<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            border-radius: 8px;
        }
        .login-container h2 {
            color: purple;
            text-align: center;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container input[type="submit"] {
            background-color: purple;
            color: white;
            padding: 10px;
            border: none;
            width: 100%;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: darkviolet;
        }
        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2>Login</h2>

        <!-- Messaggio di errore -->
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) { 
        %>
            <p class="error"><%= errorMessage %></p>
        <% 
            } 
        %>

        <!-- Form di login -->
        <form action="login" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="Inserisci la tua email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Inserisci la tua password" required>

            <input type="submit" value="Login">
        </form>
    </div>

</body>
</html>