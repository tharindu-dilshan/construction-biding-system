<%-- 
    Document   : login.jsp
    Created on : Apr 13, 2018, 10:46:25 AM
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
        <style>
            #frm{
                background-color: rgba(255,255,255,0.8);
                width: 350px;
                margin-top: 105px;
                margin-bottom: 104px;
            }
            
            
            
            #btn{
                width: 350px;
                height: 30px;
                background-color: #0078D7;
                border: 0px;
                color: white;
                font-size: 18px;
                
            }
            
            .ob{
                border: 1px solid #0078D7;
                height: 25px;
                font-family: Arial;
                font-size: 15px;
                background-color: white;
            }
            
            .lbl{
                color:  #0078D7;
                font-family: Arial;
            }
            
            input {
                padding: 0px;
                font-size: inherit;
            }

            input[type="text"] {
              margin-bottom: 0px;
              border: 1px solid #0078D7;
            }
            
            .ob{
                border: 1px solid #0078D7;
                height: 25px;
                font-family: Arial;
                font-size: 15px;
                background-color: white;
            }
            
        </style>
    </head>
    <%
        Object errorMsg = request.getAttribute("errorMsg");
        if (errorMsg != null) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert('"+errorMsg+"');");
            out.print("</script>");
        }
    %>
    <body background="img/login-background.png">
        <header>
            <div class="container">
                <div id="branding">
                    <h1 color="white"><a href="home"><span class="highlight">CLICK</span>HOME</a></h1>
                </div>
                <nav>
                    <ul>
                        <li><a href="home">Home</a></li>
                        <li><a href="posts?filter=all">All Post</a></li>
                        <li class="current"><a href="signin.jsp">SignIn</a></li>
                        <li><a href="signup.jsp">Signup</a></li>
                        <li><a href="about.jsp">About</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="newsletter">
            <div class="container">
                <h1>SignIn</h1>
            </div>
        </section>
        <center>
            <form id="frm" action="sign-in">
                <br><br>
                <table >
                    <tr>
                        <td class="lbl">User Name :</td>
                        <td> <input type="text" class="ob" name="userName"></td>
                    </tr>
                    <tr>
                        <td class="lbl">Password :</td>
                        <td> <input type="password" class="ob" name="password"></td>
                    </tr>
                </table>
                <br>
                <input id="btn" type="submit" value="Login">
            </form>
        </center>
        <footer>
            <p>Copyright © 2017. All Rights Reserved.</p>
        </footer>
    </body>
</html>
