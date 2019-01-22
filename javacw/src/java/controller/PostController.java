/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import enums.RoleEnum;
import enums.StatusEnum;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Post;
import model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import service.PostService;
import service.impl.PostServiceImpl;
import utils.DateUtil;

/**
 *
 * @author HaShaN
 */
@WebServlet("/posts")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PostController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(PostController.class.getName());
    
    private PostService postService = new PostServiceImpl();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String filter = request.getParameter("filter");
        LOG.log(Level.INFO, "Filter --> {0}",new Object[]{filter});
        
        if (Objects.equals(filter, "all")) {
            LOG.log(Level.INFO, "@all");
            try {
                List<Post> allPost = postService.getAll();
                request.setAttribute("posts", allPost);
                
                request.getRequestDispatcher("allpost.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(Objects.equals(filter, "my")){
            LOG.log(Level.INFO, "@my");
            Object loggedUserOb = request.getSession().getAttribute("loggedUser");
            if (loggedUserOb != null) {
                try{
                    User loggedUser = (User)loggedUserOb;
                    try {
                        List<Post> posts = postService.getByUser(loggedUser.getId());
                        request.setAttribute("posts", posts);
                        
                        request.getRequestDispatcher("mypost.jsp").forward(request, response);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }catch(ClassCastException e){
                    request.setAttribute("errorMsg", "You are not logged in.");
                    request.getRequestDispatcher("signin.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("errorMsg", "You are not logged in.");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        }else if(Objects.equals(filter, "contracts")){
            try {
                List<Post> posts = postService.getByRole(RoleEnum.CONTRACTOR);
                request.setAttribute("posts", posts);
                
                request.getRequestDispatcher("allpost.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(Objects.equals(filter, "supplies")){
            try {
                List<Post> posts = postService.getByRole(RoleEnum.SUPPLIER);
                request.setAttribute("posts", posts);
                
                request.getRequestDispatcher("allpost.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            LOG.log(Level.INFO, "Invalid Filter --> {0}",new Object[]{filter});
        }
    }
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Object loggedUserOb = request.getSession().getAttribute("loggedUser");
            if (loggedUserOb != null) {
                try{
                    User loggedUser = (User)loggedUserOb;
                    
                    String title = request.getParameter("title");
                    String description = request.getParameter("description");
                    Object budgetOb=request.getParameter("budget");
                    double budget = Double.parseDouble(budgetOb != null && !budgetOb.toString().equals("") ? budgetOb.toString() : "0.00");
                    Date time = DateUtil.parse(request.getParameter("time") != null ? request.getParameter("time") : null);
                    String email = request.getParameter("email");
                    String conactNo = request.getParameter("contactNo");
                    
                    //Get uploaded File
                    Part attachmentPart = request.getPart("attachment");
                    InputStream attachment = attachmentPart.getInputStream();
                    LOG.log(Level.INFO, "attachment --> {0}",new Object[]{attachment});
                    String fileName = getFileName(attachmentPart);
                    LOG.log(Level.INFO, "fileName --> {0}",new Object[]{fileName});
                    
                    boolean isAdded = postService.addPost(new Post(loggedUser,title, description, budget, time, email, conactNo, attachment, fileName, StatusEnum.ACTIVE.getStatusId()));
                    
                    LOG.log(Level.INFO, "isPostAdded --> {0}",isAdded);
                    request.getRequestDispatcher("newpost.jsp?Res="+isAdded).forward(request, response);
                    
                }catch(ClassCastException e){
                    request.setAttribute("errorMsg", "You are not logged in.");
                    request.getRequestDispatcher("signin.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("errorMsg", "You are not logged in.");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        } catch (ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        try {
            Object loggedUserOb = request.getSession().getAttribute("loggedUser");
            if (loggedUserOb != null) {
                try{
                    User loggedUser = (User)loggedUserOb;
                    
                    if(!loggedUser.getRole().getRoleName().equalsIgnoreCase(RoleEnum.ADMIN.name())){
                        request.setAttribute("errorMsg", "Unauthorized Access.");
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    }
                    
                    Post post = postService.getById(postId);
                    if (post != null) {
                        post.setStatus(StatusEnum.DELETED.getStatusId());
                        if(postService.deletePost(post)){
                            mapper.writeValue(response.getOutputStream(), "Post has been deteled successfully.");
                        }else{
                            mapper.writeValue(response.getOutputStream(), "Error occurred while deleting post.");
                        }
                    }else{
                        mapper.writeValue(response.getOutputStream(), "Post not found.");
                    }
                }catch(ClassCastException e){
                    mapper.writeValue(response.getOutputStream(), "You are not logged in.");
                }
            }else{
                mapper.writeValue(response.getOutputStream(), "You are not logged in.");
            }
            response.setContentType("text/plain");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
