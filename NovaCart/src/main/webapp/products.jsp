<%@ page import="java.util.*,com.demo.bean.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Products</title>
  <!-- Bootstrap + Theme CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
</head>

<body class="container mt-5">
  <h2 class="mb-4 text-primary">🛍️ Products</h2>

  <!-- Success/Error messages -->
  <% if (request.getAttribute("msg") != null) { %>
    <div class="alert alert-success"><%= request.getAttribute("msg") %></div>
  <% } %>
  <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
  <% } %>

  <%
    List<Product> products = (List<Product>) request.getAttribute("products"); 
    if (products == null || products.isEmpty()) { 
  %>
    <div class="alert alert-info">No products available.</div>
  <%
    } else { 
  %>

  <table class="table table-striped table-hover table-bordered shadow-sm">
    <thead class="table-dark">
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    <% for (Product p : products) { %>
      <tr>
        <td><%= p.getName() %></td>
        <td><%= p.getDescription() %></td>
        <td>₹<%= p.getPrice() %></td>
        <td><%= p.getStock() %></td>
        <td>
          <form method="post" action="${pageContext.request.contextPath}/cart" class="d-flex align-items-center">
            <input type="hidden" name="action" value="add"/>
            <input type="hidden" name="productId" value="<%= p.getId() %>"/>
            <input type="number" name="qty" min="1" value="1" class="form-control me-2" style="width:80px"/>
            <button type="submit" class="btn btn-sm btn-primary">Add to Cart</button>
          </form>
        </td>
      </tr>
    <% } %>
    </tbody>
  </table>

  <a href="${pageContext.request.contextPath}/cart" class="btn btn-success mt-3">View Cart</a>

  <% } %>
</body>
</html>
