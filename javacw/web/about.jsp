<%-- 
    Document   : about
    Created on : Apr 13, 2018, 7:51:04 AM
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
                        <%
                            Object loggedUserOb = request.getSession().getAttribute("loggedUser");
                            if (loggedUserOb != null) {
                                out.print("<li><a href=\"newpost.jsp\">New Post</a></li><li><a href=\"posts?filter=my\">My Post</a></li>");
                            }else {
                                out.print("<li><a href=\"signin.jsp\">SignIn</a></li><li><a href=\"signup.jsp\">Signup</a></li>");
                            }
                        %>
                        <li class="current"><a href="about.jsp">About</a></li>
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
                <h1>About </h1>
            </div>
        </section>
        <section id="main">
            <div class="container">
                <article id="main-col">
                    <h1 class="page-title"></h1>
                    <ul id="services">
                        <li>
                            <h2>About Us</h2>
                            <p>It is a long established fact that a reader will be distracted by the 
                                readable content of a page when looking at its layout. The point of 
                                using Lorem Ipsum is that it has a more-or-less normal distribution 
                                of letters, as opposed to using 'Content here, content here', making 
                                it look like readable English. Many desktop publishing packages and 
                                web page editors now use Lorem Ipsum as their default model text, 
                                and a search for 'lorem ipsum' will uncover many web sites still 
                                in their infancy. Various versions have evolved over the years, 
                                sometimes by accident, sometimes on purpose (injected humour and the like).</p>
                        </li>
                        <li>
                            <h2>Contacts Information</h2>
                            <p><b>www.facebook.com/ClickHome</b></p><br>
                            <p><b>www.twitter.com/ClickHome</b></p><br>
                            <p><b>Email: clickhome@outlook.com</b></p><br>
                            <p><b>Mobile no: +94 (71) 123 5667</b></p>
                        </li>
                    </ul>
                </article>
            </div>
        </section>
        <footer>
            <p>Copyright © 2017. All Rights Reserved.</p>
        </footer>
    </body>
</html>
