<%@ page import="java.util.*, java.math.BigDecimal, com.demo.service.ProductService, com.demo.bean.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Cart</title>
  <!-- Bootstrap + Theme CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
</head>

<body class="container mt-4">
  <h2 class="mb-4 text-primary">🛒 Your Cart</h2>

  <% 
   Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("cart");
   if (cart == null || cart.isEmpty()) { %>
     <div class="alert alert-info">Your cart is empty.</div>
  <% } else { 
     ProductService ps = new ProductService();
     BigDecimal total = BigDecimal.ZERO;
  %>

  <table class="table table-striped table-hover table-bordered shadow-sm">
    <thead class="table-dark">
      <tr>
        <th>Name</th>
        <th>Qty</th>
        <th>Price</th>
        <th>Line total</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    <% for (Map.Entry<Integer,Integer> e : cart.entrySet()) {
         Product p = ps.get(e.getKey());
         BigDecimal line = p.getPrice().multiply(new BigDecimal(e.getValue()));
         total = total.add(line);
    %>
      <tr>
        <td><%= p.getName() %></td>
        <td><%= e.getValue() %></td>
        <td>₹<%= p.getPrice() %></td>
        <td>₹<%= line %></td>
        <td>
          <form method="post" action="cart" class="d-inline">
            <input type="hidden" name="action" value="remove"/>
            <input type="hidden" name="productId" value="<%= p.getId() %>"/>
            <button type="submit" class="btn btn-sm btn-danger">Remove</button>
          </form>
        </td>
      </tr>
    <% } %>
    </tbody>
  </table>

  <p class="fs-5"><b>Total:</b> ₹<%= total %></p>

  <form method="post" action="cart" class="mt-3">
    <input type="hidden" name="action" value="checkout"/>
    <button type="submit" class="btn btn-success btn-lg">Proceed to Checkout</button>
  </form>

  <% } %>

  <a href="products" class="btn btn-outline-primary mt-4">⬅ Back to Products</a>
</body>
</html>
