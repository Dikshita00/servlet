<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Register</title>
  <!-- Bootstrap + Theme CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
</head>

<body class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-7 col-lg-6">
      <div class="card shadow-sm p-4">
        <h2 class="mb-4 text-center text-primary">📝 Registration</h2>

        <% if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <form method="post" action="register">
          <!-- Name -->
          <div class="mb-3">
            <label for="name" class="form-label">Full Name</label>
            <input type="text" id="name" name="name" class="form-control" required/>
          </div>

          <!-- Email -->
          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" id="email" name="email" class="form-control" required/>
          </div>

          <!-- Gender -->
          <div class="mb-3">
            <label for="gender" class="form-label">Gender</label>
            <select id="gender" name="gender" class="form-select" required>
              <option>Male</option>
              <option>Female</option>
              <option>Other</option>
            </select>
          </div>

          <!-- Hobbies -->
          <div class="mb-3">
            <label class="form-label">Hobbies</label><br/>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="checkbox" name="hobbies" value="Reading" id="hobbyReading"/>
              <label class="form-check-label" for="hobbyReading">Reading</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="checkbox" name="hobbies" value="Sports" id="hobbySports"/>
              <label class="form-check-label" for="hobbySports">Sports</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="checkbox" name="hobbies" value="Music" id="hobbyMusic"/>
              <label class="form-check-label" for="hobbyMusic">Music</label>
            </div>
          </div>

          <!-- City -->
          <div class="mb-3">
            <label for="city" class="form-label">City</label>
            <input type="text" id="city" name="city" class="form-control" required/>
          </div>

          <!-- Password -->
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" required/>
          </div>

          <!-- Submit -->
          <button type="submit" class="btn btn-success w-100">Sign Up</button>
        </form>

        <!-- Link to login -->
        <div class="text-center mt-3">
          <a href="login" class="btn btn-link">Already have an account? Login</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
