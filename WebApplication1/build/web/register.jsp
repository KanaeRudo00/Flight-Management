<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/register.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <form class="sm-pt-10" action="MainController" method="POST">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username" name="userID">
                <label for="fullName" class="form-label">Full name</label>
                <input type="text" class="form-control" id="fullName" placeholder="Full name" name="fullName">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                <label for="repassword" class="form-label">Confirm Password</label>
                <input type="password" class="form-control" id="repassword" placeholder="Confirm password" name="repassword">
                <label for="role" class="form-label">RoleID</label>
                <select name="role" class="form-control">
                    <option value="2" >ST</option>
                    <option value="1" >AD</option>
                    <option value="0" >US</option>
                </select>

                <%
                    String msg = (String) request.getAttribute("ERROR");
                    if (msg == null) {
                        msg = "";
                    }
                %>
                <h6><%=msg%></h6>

                <div class="form-footer">
                    <a href="login.jsp">Login</a>
                    <input class="register-btn" type="submit" name="action" value="Register">
                </div>
            </form>
        </div>
    </body>
</html>
