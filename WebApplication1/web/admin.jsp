<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/admin.css">
    </head>
    <body>
        <%
            UserDTO loginUser = session.getAttribute("recent_user") == null ? new UserDTO("", "", "", 1) : (UserDTO) session.getAttribute("recent_user");
            session.setAttribute("url", "admin.jsp");
            String search_val = session.getAttribute("search") == null ? "" : (String) session.getAttribute("search");
            boolean change = request.getAttribute("change") == null ? true : (boolean) request.getAttribute("change");
        %>
        <div class="toolbar">
            <div class="left-section">
                <h1 class="welcome-heading">Welcome: <%= loginUser.getFullName()%></h1>
                <form class="search-form" id="form" action="MainController">
                    <input class="form-control search-input" type="text" name="search" value="<%=search_val%>" />
                    <input class="btn btn-primary search-btn" type="submit" name="action" value="Search" />
                </form>
            </div>
            <div class="right-section">
                <form class="logout-form" action="MainController">
                    <input class="btn btn-danger logout-btn" type="submit" name="action" value="Logout" />
                </form>
            </div>
        </div>
        <div class="container">
            <%
                List<UserDTO> listUser = (List<UserDTO>) request.getAttribute("list_search");
                if (listUser != null && !listUser.isEmpty()) {
            %>
            <table class="table table-striped user-table">
                <<thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Role ID</th>
                        <th>Password</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (UserDTO user : listUser) {
                    %>
                <form action="MainController" method="POST" class="update-form">
                    <tr>
                        <td><%= ++count%></td>
                        <td>
                            <input type="text" value="<%= user.getUserID()%>" name="userID" readonly="" class="form-control" />
                        </td>
                        <td>
                            <input type="text" value="<%= user.getFullName()%>" name="<%= user.getUserID()%>_fullName" required="" class="form-control" />
                        </td>
                        <td>
                            <select name="<%= user.getUserID()%>_role" class="form-control">
                                <option value="2" <%= user.getRoleID() == 2 ? "selected" : ""%>>ST</option>
                                <option value="1" <%= user.getRoleID() == 1 ? "selected" : ""%>>AD</option>
                                <option value="0" <%= user.getRoleID() == 0 ? "selected" : ""%>>US</option>
                            </select>

                        </td>
                        <td>
                            <input type="hidden" name="<%= user.getUserID()%>_password" value="<%= user.getPassword()%>" />
                            <input type="password" value="********" name="password" required="" class="form-control" />

                        </td>
                        <td>
                            <a href="MainController?userID=<%= user.getUserID()%>&action=Remove" class="delete-link">Delete</a>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary update-btn" name="action"  value="Update">
                                Update
                            </button>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody
            </table>
            <%
                }
            %>
        </div>
    </body>
</html>
<% if (change == true) {
        String contextPath = request.getContextPath();
        String servletPath = "/SearchController";
        String actionUrl = contextPath + servletPath;
%>
<script>
    document.getElementById("form").setAttribute("action", "<%= actionUrl%>");
    document.getElementById("form").submit();
</script>
<%
        change = false;
    }
%>