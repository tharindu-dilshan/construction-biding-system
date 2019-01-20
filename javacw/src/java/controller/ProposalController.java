/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import controller.adapter.AddableHttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.Proposal;
import model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import service.impl.ProposalServiceImpl;
import utils.AdditionalData;
import utils.DateUtil;
import utils.Pagination;
import service.ProposalService;

/**
 *
 * @author HaShaN
 */
@WebServlet("/proposals")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProposalController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ProposalController.class.getName());
    
    private final ProposalService proposalService=new ProposalServiceImpl();
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
            out.println("<title>Servlet CommentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentController at " + request.getContextPath() + "</h1>");
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
        try {
            int postId = Integer.parseInt(request.getParameter("postId"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            
            Pagination pagination = new Pagination(pageNumber, pageSize);
            AdditionalData additionalData = new AdditionalData();
            
            //Get Comments
            List<Proposal> proposals = proposalService.getByPost(postId, pagination, additionalData);
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setDateFormat(DateUtil.sdf);
            String str = mapper.writeValueAsString(proposals);
            
            LOG.log(Level.INFO, "Proposals JSON String --> {0}",new Object[]{str});
            
            mapper.writeValue(response.getOutputStream(), proposals);
            response.setContentType("application/json");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProposalController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                    
                    Object postIdOb = request.getParameter("postId");
                    int postId = Integer.parseInt(postIdOb != null ? postIdOb.toString() : "0");
                    String budget = request.getParameter("budget");
                    String duration=request.getParameter("duration");
                    String desciption=request.getParameter("desciption");
                    String filter=request.getParameter("filter");
                    
                    //Get uploaded File
                    Part attachmentPart = request.getPart("attachment");
                    InputStream attachment = attachmentPart.getInputStream();
                    LOG.log(Level.INFO, "attachment --> {0}",new Object[]{attachment});
                    String fileName = getFileName(attachmentPart);
                    LOG.log(Level.INFO, "fileName --> {0}",new Object[]{fileName});
                    
                    boolean isAdded = proposalService.addProposal(new Proposal(new Post(postId),loggedUser, budget, duration, attachment, fileName, desciption, new Date()));
                    
                    LOG.log(Level.INFO, "isAdded --> {0}",isAdded);
                    
                    PostController postController=new PostController();
                    AddableHttpRequest httpRequest=new AddableHttpRequest(request);
                    httpRequest.addParameter("filter", filter);
                    postController.doGet(httpRequest, response);
                }catch(ClassCastException e){
                    request.setAttribute("errorMsg", "You are not logged in.");
                    request.getRequestDispatcher("signin.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("errorMsg", "You are not logged in.");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
