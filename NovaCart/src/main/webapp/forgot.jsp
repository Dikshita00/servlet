<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Forgot Password</title>
  <!-- Bootstrap + Theme CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
</head>

<body class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
      <div class="card shadow-sm p-4">
        <h2 class="mb-4 text-center text-primary">🔑 Reset Password</h2>

        <% if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <form method="post" action="forgot">
          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" id="email" name="email" class="form-control" required/>
          </div>

          <div class="mb-3">
            <label for="newPassword" class="form-label">New Password</label>
            <input type="password" id="newPassword" name="newPassword" class="form-control" required/>
          </div>

          <button type="submit" class="btn btn-success w-100">Reset Password</button>
        </form>

        <div class="text-center mt-3">
          <a href="login" class="btn btn-link">Back to Login</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
