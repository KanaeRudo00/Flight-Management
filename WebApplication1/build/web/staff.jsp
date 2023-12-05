<%@page import="DTO.UserDTO"%>
<%@page import="DTO.MobileDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.MobileDTO"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/staff.css">
    </head>
    <body>
        <%
            session.setAttribute("url", "staff.jsp");
            UserDTO loginUser = session.getAttribute("recent_user") == null ? new UserDTO("", "", "", 2) : (UserDTO) session.getAttribute("recent_user");
            String search_val = session.getAttribute("search") == null ? "" : (String) session.getAttribute("search");
            boolean change = request.getAttribute("change") == null ? false : (boolean) request.getAttribute("change");
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
                List<MobileDTO> listMobile = (List<MobileDTO>) request.getAttribute("list_search");
                if (listMobile != null && !listMobile.isEmpty()) {
            %>
            <form class="sort-form form-inline" action="MainController" method="POST">
                <div class="form-group mb-2">
                    <label for="sort">Sort By:</label>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                    <select name="sort" id="sort" class="form-control">
                        <option value="mobileID_asc">Mobile ID</option>
                        <option value="mobileName_asc">Mobile Name</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary sort-btn mb-2" name="action" value="Sort">Sort</button>
            </form>
            <table class="table table-striped user-table">
                <thead>
                    <tr>
                        <th>Mobile ID</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Mobile Name</th>
                        <th>Year of Production</th>
                        <th>Quantity</th>
                        <th>Sale</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        int count = 0;
                        for (MobileDTO mobile : listMobile) {
                    %>
                <form action="MainController" method="POST" class="update-form">
                    <tr>
                        <td>
                            <input type="text" value="<%=mobile.getMobileId()%>" name="mobileID" readonly="" class="form-control" />
                        </td>
                        <td>
                            <input type="text" value="<%= mobile.getDescription()%>" name="<%= mobile.getMobileId()%>_desc" required="" class="form-control" />
                        </td>
                        <td>
                            <input type="text" value="<%= mobile.getPrice()%>" name="<%= mobile.getMobileId()%>_price" required="" class="form-control" />
                        </td>
                        <td>
                            <input type="text" value="<%= mobile.getMobileName()%>" name="<%= mobile.getMobileId()%>_name" required="" class="form-control" />
                        </td>
                        <td>
                            <input type="text" name="<%= mobile.getMobileId()%>_yop" value="<%= mobile.getYearOfProduction()%>" class="form-control" />
                        </td>
                        <td>
                            <input type="text" name="<%= mobile.getMobileId()%>_quan" value="<%= mobile.getQuantity()%>" class="form-control" />
                        </td>
                        <td>
                            <%
                                String sale = mobile.getSale() == 1 ? "True" : "False";
                            %>
                            <select name="<%= mobile.getMobileId()%>_sale" class="form-control">
                                <option value="True" <%= sale.equals("True") ? "selected" : ""%>>True</option>
                                <option value="False" <%= sale.equals("False") ? "selected" : ""%>>False</option>
                            </select>
                        </td>
                        <td>
                            <a href="MainController?mobileID=<%= mobile.getMobileId()%>&action=Remove" class="delete-link">Delete</a>
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
                <form>
                    <tr class="insertRow"     style="background-color : #007bff">
                        <td>
                            <input type="text" name="mobileID" class="form-control" >
                        </td>
                        <td>
                            <input type="text" name="description" class="form-control" >
                        </td>
                        <td>
                            <input type="text" name="price" class="form-control" >
                        </td>
                        <td>
                            <input type="text" name="mobileName" class="form-control" >
                        </td>
                        <td>
                            <input type="text" name="yearOfProduction" class="form-control" >
                        </td>
                        <td>
                            <input type="text" name="quantity" class="form-control" >
                        </td>
                        <td>
                            <select name="sale" class="form-control">
                                <option value="1" >True</option>
                                <option value="0">False</option>
                            </select>
                        </td>
                        <td></td>
                        <td>
                            <button type="submit" class="btn btn-primary add-btn" name="action"  value="Add">
                                Add
                            </button>
                        </td>
                    </tr>
                </form>
                </tbody>
            </table>
            <%
                }
            %>
        </div>
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
    </body>
</html>
