<%-- 
    Document   : mypost
    Created on : Apr 13, 2018, 3:44:39 PM
    Author     : HaShaN
--%>

<%@page import="service.impl.ProposalServiceImpl"%>
<%@page import="service.ProposalService"%>
<%@page import="model.Proposal"%>
<%@page import="utils.AdditionalData"%>
<%@page import="utils.Pagination"%>
<%@page import="java.util.Objects"%>
<%@page import="utils.DateUtil"%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>
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
        <link href="css/posts.css" rel="stylesheet" type="text/css"/>
        <link href="css/pagination.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.modal.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js"></script>
        <script src="js/pagination.js"></script>
        <script src="js/pagination.min.js"></script>
        <script src="js/jquery.modal.min.js"></script>
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
                        <li><a href="newpost.jsp">New Post</a></li>
                        <li class="current"><a href="posts?filter=my">My post</a></li>
                        <li><a href="about.jsp">About</a></li>
                        <li id="logout"><a href="sign-out">LOGOUT</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="newsletter">
            <div class="container">
                <h1>My Post</h1>
            </div>
        </section> 
        <section id="form-wrapper">
            <div id="post-container">
            <%          
                ProposalService proposalService = new ProposalServiceImpl();
                Object postListOb = request.getAttribute("posts");
                if (postListOb != null) {
                    List<Post> posts = (List<Post>) postListOb;
                    for (Post post : posts) {
                        out.print("<div class=\"post-div\"><div class=\"post-header\">"+post.getTitle());
                        out.print("</div><div class=\"post-desc\">");
                        out.print(post.getDescription()+"</div><div class=\"post-time\">"+DateUtil.format(post.getTime())+" | By "+post.getUser().getFirstName()+" "+post.getUser().getLastName()+"</div>");
                        if(Objects.nonNull(post.getAttachmentName()) && !post.getAttachmentName().isEmpty()){
                            out.print("<div class=\"post-attch\"><a class=\"downloadBtn\" href=\"download-attachment?id="+post.getId()+"&from=post\">Download</a></div>");
                        }
                        out.print("<div class=\"comment-div\">");
                        out.print("<div class=\"comments-div\" id=\"comments-div-"+post.getId()+"\">");
                        
                        Pagination pagination = new Pagination(1, 5);
                        AdditionalData additionalData = new AdditionalData();
                        //Get All Proposals
                        List<Proposal> proposals = proposalService.getByPost(post.getId(), pagination, additionalData);
                        
                        for(Proposal proposal : proposals){
                            out.print("<div class=\"comment\"><div class=\"comment-txt\">"+proposal.getDescription()+"</div>");
                            out.print("<div class=\"comment-desc\"> Budget - "+proposal.getBudget()+" | Duration - "+proposal.getDuration()+"</div>");
                            if(Objects.nonNull(proposal.getAttachmentName()) && !proposal.getAttachmentName().isEmpty()){
                                out.print("<a href=\"download-attachment?id="+proposal.getId()+"&from=prop\">Download Attachment</a>");
                            }
                            out.print("<div class=\"comment-desc\">"+proposal.getUser().getFirstName()+" "+proposal.getUser().getLastName()+" | "+proposal.getDateTime()+"</div>");
                            out.print("</div>");
                        }
                        out.print("</div>");
                        out.print("<div class=\"paginationjs paginationjs-theme-red\" style=\"float:right;margin-right:10px;margin-top:10px;\" id=\"paginationElm"+post.getId()+"\"></div>");
                        out.print("<div style=\"height:50px;width:100%\"><a href=\"#propModal\" class=\"comment-btn comment-elem\" rel=\"modal:open\" onclick=\"$('#postId').val('"+post.getId()+"');\">Send Proposal</a></div></div>");
                        
                        out.print("<script type=\"text/javascript\">");
                        
                        out.print("$('#paginationElm"+post.getId()+"').pagination({");
                            out.print("dataSource: '/javacw/proposals?postId="+post.getId()+"',");
                            out.print("locator: 'proposals',");
                            out.print("totalNumber: "+additionalData.getCount()+",");
                            out.print("pageSize: "+pagination.getCount()+",");
                            out.print("hideWhenLessThanOnePage : true,");
                            out.print("ajax: {beforeSend: function() {$('#comments-div-"+post.getId()+"').html('Loading proposals...');}},");
                            out.print("callback: function(data, pagination) {");
                                out.print("var htmlElm = '';");
                                out.print("$.each(data, function(key, value) {");
                                    out.print("htmlElm+='<div class=\"comment\"><div class=\"comment-txt\">'+value.description+'</div><div class=\"comment-desc\">Budget - '+value.budget+'  | Duration - '+value.duration+'</div>';");
                                    out.print("if(value.attachmentName){");
                                    out.print("htmlElm+='<a href=\"download-attachment?id='+value.id+'&from=prop\">Download Attachment</a>';");
                                    out.print("}");
                                    out.print("htmlElm+='<div class=\"comment-desc\">'+value.user.firstName+' '+value.user.lastName+' | '+value.dateTime+'</div>';");
                                    out.print("htmlElm+='</div>';");
                                out.print("});");
                                out.print("$('#comments-div-"+post.getId()+"').html(htmlElm);");
                            out.print("}");
                        out.print("});");
                        
                        out.print("</script>");
                        out.print("</div>");
                    }   
                }
            %>
            </div>
        </section>
        <footer>
            <p>Copyright © 2017. All Rights Reserved.</p>
        </footer>
        <div style="display: none">
            <div id="propModal" class="modal">
                <form id="frm" action="proposals?filter=my" method="post" enctype="multipart/form-data">
                    <br>
                    <input id="postId" name="postId" type="hidden">
                    <table>
                        <tr>
                            <td>Budget</td>
                            <td><input type="text" style="width: 330px;" name="budget"></td>
                        </tr>
                        <tr>
                            <td>Duration</td>
                            <td><input type="text" style="width: 330px;" name="duration"></td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><textarea style="width: 345px;height: 150px;resize: none;border: 2px solid #CD5C5C;" name="desciption"></textarea></td>
                        </tr>
                        <tr>
                            <td>Attachment</td>
                            <td><input id="attachment" type="file" name="attachment"/></td>
                        </tr>
                    </table>
                    <br>
                    <input class="comment-btn" style="width: 430px;background-color: #CD5C5C;" type="submit" value="Send Proposal">
            </form>
            </div>
        </div>   
    </body>
</html>
