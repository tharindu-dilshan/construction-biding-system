<%-- 
    Document   : home
    Created on : Apr 13, 2018, 7:50:53 AM
    Author     : HaShaN
--%>

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
    </head>
   
    <body>
        <header>
            <div class="container">
                <div id="branding">
                    <h1 color="white"><a href="home"><span class="highlight">CLICK</span>HOME</a></h1>
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
        <section id="showcase">
            <div class="w3-content w3-section" style="max-width:500px">
                
                <img class="mySlides" src="img/slide20.jpg" style="width:270%">
                <img class="mySlides" src="img/slide21.jpg" style="width:270%">
                <img class="mySlides" src="img/slide22.jpg" style="width:270%">
                <img class="mySlides" src="img/slide23.jpg" style="width:270%">
            </div>
        </section>
        
        <script>
            var myIndex = 0;
            carousel();

            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {myIndex = 1}
                x[myIndex-1].style.display = "block";
                setTimeout(carousel, 5000); // Change image every 2 seconds
            }
        </script>
        
        <section id="newsletter">
            <div class="container">
                <h1>Services</h1>
            </div>
        </section>
        
        <section id="boxes">
            <div class="container">
                <div class="box">
                    <a href="posts?filter=contracts"><img src="img/index/index02.png" style="width:60%"></a>
                    <h3>FIND CONTRACTS</h3>
                </div>
                
                <div class="box">
                    <a href="posts?filter=supplies"><img src="img/index/index01.png" style="width:60%" ></a>
                    <h3>FIND SUPPLIES</h3>
                </div>
                
                <div class="box">
                    <a href="users"><img src="img/index/index03.png" style="width:60%"></a>
                    <h3>
                    <%
                          if (request.getAttribute("roleName")!=null) {
                               out.print(request.getAttribute("roleName"));
                          }else{
                              out.print("SUPPLIERS/CONTRACTORS");
                          }
                    %>
                    </h3>
                </div>
            </div>
        </section>
        <footer>
            <p>Copyright © 2017. All Rights Reserved.</p>            
        </footer>
    </body>
</html>
