<%-- 
    Document   : newpost.jsp
    Created on : Apr 13, 2018, 7:49:09 AM
    Author     : HaShaN
--%>

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
        if (loggedUserOb == null) {
            request.setAttribute("errorMsg", "You are not logged in.");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    %>
    <body>
        <header>
            <div class="container">
                <div id="branding">
                    <h1 style="color:white"><a href="home"><span class="highlight">CLICK</span>HOME</a></h1>
                </div>
                <nav>
                    <ul>
                        <li><a href="home">Home</a></li>
                        <li><a href="posts?filter=all">All Post</a></li>
                        <li class="current"><a href="newpost.jsp">New Post</a></li>
                        <li><a href="posts?filter=my">My post</a></li>
                        <li><a href="about.jsp">About</a></li>
                        <li id="logout"><a href="sign-out">LOGOUT</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="newsletter">
            <div class="container">
                <h1>New Post</h1>
            </div>
        </section>        
        <section id="form-wrapper">
            <form action="posts" method="POST"  enctype="multipart/form-data">
                <input type="text" name="title" placeholder="Title">
                <input type="text" name="description" placeholder="Description">
                <input type="text" name="budget" placeholder="Budget">
                <input type="text" name="time" placeholder="Time">
                <input type="email" name="email" placeholder="Email">
                <input type="text" name="contactNo" placeholder="Contact No">
                <label for="attachment">Completed Work:</label>
                <input id="attachment" type="file" name="attachment"/>
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
        String res = request.getParameter("Res");
        if (res!=null) {
            if (res.equals("true")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Post Added Successfully...');");
                out.println("</script>");
            }else if(res.equals("false")){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Post Add Fail...');");
                out.println("</script>");
            }
        }
    %>
</html>
