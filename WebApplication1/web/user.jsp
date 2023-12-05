<%@page import="java.util.List"%>
<%@page import="DTO.MobileDTO"%>
<%@page import="DTO.UserDTO"%>
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
        <link rel="stylesheet" href="css/user.css">
    </head>
    <body>
        <% session.setAttribute("url", "user.jsp");
            UserDTO loginUser = session.getAttribute("recent_user") == null ? new UserDTO("", "", "", 2) : (UserDTO) session.getAttribute("recent_user");
            Double min_val = session.getAttribute("min") == null ? 0 : (Double) session.getAttribute("min");
            Double max_val = session.getAttribute("max") == null ? 0 : (Double) session.getAttribute("max");
            boolean change = request.getAttribute("change") == null ? false : (boolean) request.getAttribute("change");
        %>
        <div class="toolbar">
            <div class="left-section">
                <h1 class="welcome-heading">Welcome: <%= loginUser.getFullName()%></h1>
            </div>
            <div class="right-section">
                <a href="MainController?userID=<%= loginUser.getUserID()%>&action=Cart" class="direct-btn">Cart</a>
                <form class="logout-form" action="MainController">
                    <input class="btn btn-danger logout-btn" type="submit" name="action" value="Logout" />
                </form>
            </div>
        </div>

        <div class="container">
            <form class="search-form" id="form" action="MainController">
                <label for="min" class="form-label">Min:</label>
                <input class="form-control search-input" id="min" type="number" name="min" value="<%=min_val%>" min ="0"/>
                <label for="max" class="form-label">Max:</label>
                <input class="form-control search-input" id="max" type="number" name="max" value="<%=max_val%>" max="1000000" />
                <input class="btn btn-primary search-btn" type="submit" name="action" value="Search" />
            </form>
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
                        <th></th>
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
                            <input type="text" value="<%= mobile.getDescription()%>" name="<%= mobile.getMobileId()%>_desc" required="" class="form-control" readonly="" />
                        </td>
                        <td>
                            <input type="text" value="<%= mobile.getPrice()%>" name="<%= mobile.getMobileId()%>_price" required="" class="form-control" readonly=""/>
                        </td>
                        <td>
                            <input type="text" value="<%= mobile.getMobileName()%>" name="<%= mobile.getMobileId()%>_name" required="" class="form-control" readonly=""/>
                        </td>
                        <td>
                            <input type="text" name="<%= mobile.getMobileId()%>_yop" value="<%= mobile.getYearOfProduction()%>" class="form-control" readonly=""/>
                        </td>                      
                        <td>
                            
                            <button type="button" class="btn btn-primary update-btn" onclick="buyPrompt('<%=mobile.getMobileId()%>')">
                                Buy
                            </button>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>  
                </tbody>
            </table>
            <%
                }
            %>
        </div>

        <script>
        function buyPrompt(mobileId) {
            var amount;
            while (amount === null || isNaN(amount)) {
                amount = prompt("Please enter the amount:");
            }
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "MainController");

            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", "mobileID");
            hiddenField.setAttribute("value", mobileId);
            form.appendChild(hiddenField);

            var amountField = document.createElement("input");
            amountField.setAttribute("type", "hidden");
            amountField.setAttribute("name", "amount");
            amountField.setAttribute("value", amount);
            form.appendChild(amountField);

            var action = document.createElement("input");
            action.setAttribute("type", "hidden");
            action.setAttribute("name", "action");
            action.setAttribute("value", "Buy");
            form.appendChild(action);

            var userID = document.createElement("input");
            userID.setAttribute("type", "hidden");
            userID.setAttribute("name", "userID");
            userID.setAttribute("value", "<%= loginUser.getUserID()%>");
            form.appendChild(userID);

            document.body.appendChild(form);
            form.submit();


        }
    </script>


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
