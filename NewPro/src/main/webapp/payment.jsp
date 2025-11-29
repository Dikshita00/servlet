<!-- src/main/webapp/payment.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.math.BigDecimal" %>
<html>
<head><title>Payment</title></head>
<body>
<h2>Payment</h2>

<% if (request.getAttribute("error") != null) { %>
  <div style="color:red"><%= request.getAttribute("error") %></div>
<% } %>

<%
  Integer orderId = (Integer) session.getAttribute("orderId");
  BigDecimal amount = (BigDecimal) session.getAttribute("orderTotal");
%>

<% if (orderId == null || amount == null) { %>
  <p>No order to pay. <a href="products">Go to products</a></p>
<% } else { %>
  <p><b>Order ID:</b> <%= orderId %></p>
  <p><b>Amount:</b> <%= amount %></p>

  <form method="post" action="pay">
    <!-- amount is displayed from session; no user input -->
    <input type="hidden" name="amount" value="<%= amount %>"/>
    Method:
    <select name="method" required>
      <option value="CARD">Card</option>
      <option value="UPI">UPI</option>
      <option value="COD">Cash on Delivery</option>
    </select><br/><br/>
    <button type="submit">Pay</button>
  </form>
<% } %>
</body>
</html>
