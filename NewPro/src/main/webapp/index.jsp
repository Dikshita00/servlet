<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Home</title>
  <!-- Bootstrap + Theme CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
</head>

<body class="index-hero">
  <div class="page-content text-center">
    <img src="https://source.unsplash.com/1200x300/?shopping,ecommerce" 
         class="img-fluid rounded mb-4" alt="Shopping banner"/>
    <h2 class="mb-4">Welcome to <span class="text-primary">NewPro</span></h2>

    <% if (request.getAttribute("msg") != null) { %>
      <div class="alert alert-success"><%= request.getAttribute("msg") %></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <div class="d-flex justify-content-center gap-3 mt-4">
      <a href="${pageContext.request.contextPath}/register" class="btn btn-outline-success btn-lg">Sign Up</a>
      <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary btn-lg">Login</a>
      <a href="${pageContext.request.contextPath}/products" class="btn btn-outline-warning btn-lg">Browse Products</a>
      <a href="${pageContext.request.contextPath}/forgot" class="btn btn-outline-danger btn-lg">Forgot Password</a>
    </div>
  </div>
</body>
</html>
