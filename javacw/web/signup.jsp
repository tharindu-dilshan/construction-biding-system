<%-- 
    Document   : signup
    Created on : Apr 13, 2018, 12:15:16 PM
    Author     : HaShaN
--%>

<%@page import="java.util.List"%>
<%@page import="enums.RoleEnum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width">
        <meta name="description" content="Affordable and professional web design">
        <meta name="keywords" content="web design, affordable web design, professional web design">
        <meta name="author" content="Brad Traversy">
        <title>CLICKHOME Official Website | Welcome</title>
        <link href="css/newcss.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        Object loggedUserOb = request.getSession().getAttribute("loggedUser");
        if (loggedUserOb != null) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    %>
    <body>
        <header>
            <div class="container">
                <div id="branding">
                    <h1 color="white"><a href="home"><span class="highlight">CLICK</span>HOME</a></h1>
                </div>
                <nav>
                    <ul>
                        <li><a href="home">Home</a></li>
                        <li><a href="posts?filter=all">All Post</a></li>
                        <li><a href="signin.jsp">SignIn</a></li>
                        <li class="current"><a href="signup.jsp">Signup</a></li>
                        <li><a href="about.jsp">About</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="newsletter">
            <div class="container">
                <h1>SignUp</h1>
            </div>
        </section>        
        <section id="form-wrapper">
            <form action="sign-up" method="POST">
                <input type="text" name="firstName" placeholder="First Name">
                <input type="text" name="lastName" placeholder="Last Name">
                <input type="text" name="country" placeholder="Country">
                <input type="text" name="contactNo" placeholder="Contact No">
                <input type="email" name="email" placeholder="Email">
                <input type="text" name="userName" placeholder="User Name">
                <input type="password" name="password" placeholder="Password">
                <input type="password" id="confirmPassword" placeholder="Confirm Password">
                <select style="width:194px" class="ob" name="ROLE"> 
                            <%                                 
                                List<RoleEnum> roleList = (List<RoleEnum>) application.getAttribute("roleList");
                                for (RoleEnum role : roleList) {
                                    out.print("<option value=\""+role.getRoleId()+"\">" + role.name() + "</option>");
                                }
                            %>
                        </select>
                <div>
                    <input type="submit" name="Submit" value="Submit">
                </div>
            </form>
        </section>
        <footer>
            <p>Copyright © 2017. All Rights Reserved.</p>
        </footer>
    </body>
    
    <%
            Object res = request.getAttribute("Res");
            Object msgOb = request.getAttribute("errorMsg");
            if (res != null) {
                if (res.equals(true)) {
                    if(msgOb != null){
                        out.print("<script type=\"text/javascript\">");
                        out.print("alert('"+msgOb+"');");
                        out.print("</script>");
                    }else{
                        out.print("<p>else</p>");
                        out.print("<script type=\"text/javascript\">");
                        out.print("alert('Registration Fail...');");
                        out.print("</script>");
                    }
                } else if (res.equals(false)) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert('You are Successfully Registered...');");
                    out.print("</script>");
                }
            }

        %>
</html>
