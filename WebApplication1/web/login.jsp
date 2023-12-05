<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <%
                String ssUser = session.getAttribute("username") == null ? "" : (String) session.getAttribute("username");
                String ssPass = session.getAttribute("password") == null ? "" : (String) session.getAttribute("password");
            %>

            <form class="sm-pt-10" id="form" action="MainController" method="POST">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username" name="userID" value="<%=ssUser%>">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password" value="<%=ssPass%>">

                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="keepLoggedIn" name="keepLoggedIn">
                    <label class="form-check-label" for="keepLoggedIn">Keep me logged in</label>
                </div>

                <% String msg = (String) request.getAttribute("ERROR");
                    if (msg == null) {
                        msg = "";
                    }
                %>
                
                
                <h6><%=msg%></h6>
                <% String message = (String) request.getAttribute("alert");
                    if (message != null) {%>
                <script>
                    alert("<%= message%>");
                </script>
                <% }
                    message = "";
                %>
                <div class="form-footer">
                    <a href="register.jsp">Create new account</a>
                    <input class="login-btn" type="submit" name="action" value="Login">
                </div>
            </form>

        </div>
    </body>
</html>
<% if (!ssUser.isEmpty() && !ssPass.isEmpty()) {
        String contextPath = request.getContextPath();
        String servletPath = "/LoginController";
        String actionUrl = contextPath + servletPath;
%>
<script>
    document.getElementById("form").setAttribute("action", "<%= actionUrl%>");
    document.getElementById("form").submit();
</script>
<%    }
%>
