<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Stranger Things Promo</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Creepster&display=swap" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Creepster', cursive;
            color: #fff;
        }
        /* Background GIF */
        body {
            background: url('https://media.giphy.com/media/3o7qj3mwYZaGck0NoI/giphy.gif') no-repeat center center fixed;
            background-size: cover;
        }
        .overlay {
            background-color: rgba(0,0,0,0.6); /* dark overlay for readability */
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            flex-direction: column;
            padding: 20px;
        }
        h1 {
            font-size: 3rem;
            color: #e50914; /* Netflix red */
            margin-bottom: 20px;
        }
        h2 {
            font-size: 2rem;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="overlay">
        <h1>Hello Hawkins!!!!</h1>
        <h2>NOW YOU WILL EXPERIENCE STRANGER THINGS!!!!</h2>
    </div>
</body>
</html>
