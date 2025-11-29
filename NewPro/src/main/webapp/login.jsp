<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Login</title>
  <!-- Bootstrap + Theme CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
</head>

<body class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-4">
      <div class="card shadow-sm p-4">
        <h2 class="mb-4 text-center text-primary">🔐 Login</h2>

        <!-- Messages -->
        <% if (request.getAttribute("msg") != null) { %>
          <div class="alert alert-success"><%= request.getAttribute("msg") %></div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <!-- Login form -->
        <form method="post" action="login">
          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" id="email" name="email" class="form-control" required/>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" required/>
          </div>

          <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <!-- Links -->
        <div class="text-center mt-3">
          <a href="register" class="btn btn-link">Sign Up</a> |
          <a href="forgot" class="btn btn-link">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
