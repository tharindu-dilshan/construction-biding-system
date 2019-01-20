<%-- 
    Document   : about
    Created on : Apr 13, 2018, 7:51:04 AM
    Author     : HaShaN
--%>

<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width">
        <meta name="description" content="Affordable and professional web design">
        <meta name="keywords" content="web design, affordable web design, professional web design">
        <meta name="author" content="Brad Traversy">
        <title>CLICKHOME Official Website | Welcome</title>
        <link href="css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="css/userlist.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    </head>
    <body>
        <header>
            <div class="container">
                <div id="branding">
                    <h1><a href="home"><span class="highlight">CLICK</span>HOME</a></h1>
                </div>
                <nav>
                    <ul>
                        <li class="current"><a href="home">Home</a></li>
                        <li><a href="posts?filter=all">All Post</a></li>
                        <%
                            Object loggedUserOb = request.getSession().getAttribute("loggedUser");
                            if (loggedUserOb != null) {
                                out.print("<li><a href=\"newpost.jsp\">New Post</a></li><li><a href=\"posts?filter=my\">My Post</a></li>");
                            }else {
                                out.print("<li><a href=\"signin.jsp\">SignIn</a></li><li><a href=\"signup.jsp\">Signup</a></li>");
                            }
                        %>
                        <li><a href="about.jsp">About</a></li>
                        <%
                            if (loggedUserOb != null) {
                                out.print("<li id=\"logout\"><a href=\"sign-out\">LOGOUT</a></li>");
                            }
                        %>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="newsletter">
            <div class="container">
                <h1>${roleName}</h1>
            </div>
        </section> 
        <section id="form-wrapper">
            <div class="user-container">
                <%
                    Object userListOb = request.getAttribute("userList");
                    if (userListOb != null) {
                        List<User> users = (List<User>) userListOb;
                        for(User user : users){
                            out.print("<div class=\"user-record\">");
                            out.print("<div class=\"fullName\">"+user.getFirstName()+" "+user.getLastName());
                            Object isAdminOb = request.getAttribute("isAdmin");
                            if (isAdminOb != null && (Boolean)isAdminOb == true) {
                                out.print(" | "+user.getRole().getRoleName());
                            }
                            out.print("</div>");
                            out.print("<div class=\"username-div\"><span class=\"material-icons\">account_circle</span><span class=\"username\">"+user.getUserName()+"</span></div>");
                            out.print("<span style=\"margin-left:10px;\" class=\"material-icons\">email</span><span class=\"email\">"+user.getEmail()+"</span>");
                            out.print("<div class=\"message-div\"><i style=\"color:#2ECC71\" class=\"material-icons\">chat</i></div>");
                            out.print("</div><br>");
                        }
                    }
                %>
            </div>
        </section>
    </body>
</html>
