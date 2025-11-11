<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Team Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box; 
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .submit-btn {
            background-color: #007BFF;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
        /* Style for the error message */
        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Team Registration</h2>
        <p>Please fill out the details for your team.</p>
        
        <%-- This is the JSP code to display the error message --%>
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
        <%
            }
        %>
        
        <form action="TeamRegisterServlet" method="POST">
            
            <div class="form-group">
                <label for="teamName">Team Name:</label>
                <input type="text" id="teamName" name="teamName" required>
            </div>
            
            <div class="form-group">
                <label for="iglName">IGL (In-Game Leader) Name:</label>
                <input type="text" id="iglName" name="iglName" required>
            </div>
            
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" placeholder="e.g., +91 12345 67890" required>
            </div>
            
            <hr>
            <h3>Player Details</h3>
            
            <div class="form-group">
                <label for="player1">Player 1 Name:</label>
                <input type="text" id="player1" name="player1" required>
            </div>
            
            <div class="form-group">
                <label for="player2">Player 2 Name:</label>
                <input type="text" id="player2" name="player2" required>
            </div>
            
            <div class="form-group">
                <label for="player3">Player 3 Name:</label>
                <input type="text" id="player3" name="player3" required>
            </div>
            
            <div class="form-group">
                <label for="player4">Player 4 Name:</label>
                <input type="text" id="player4" name="player4" required>
            </div>
            
            <button type="submit" class="submit-btn">Register Team</button>
            
        </form>
    </div>

</body>
</html>