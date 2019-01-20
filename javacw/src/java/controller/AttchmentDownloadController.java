/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.Proposal;
import service.PostService;
import service.ProposalService;
import service.impl.PostServiceImpl;
import service.impl.ProposalServiceImpl;

/**
 *
 * @author HaShaN
 */
@WebServlet("/download-attachment")
public class AttchmentDownloadController extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(AttchmentDownloadController.class.getName());
    
    private final PostService postService = new PostServiceImpl();
    private final ProposalService proposalService = new ProposalServiceImpl();
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
            out.println("<title>Servlet AttchmentDownloadController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttchmentDownloadController at " + request.getContextPath() + "</h1>");
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
        
        Object IdOb = request.getParameter("id");
        
        
        LOG.log(Level.INFO, "postIdOb --> {0}",new Object[]{IdOb});
        if (IdOb != null) {
            String from = request.getParameter("from");
            try {
                int Id = Integer.parseInt(IdOb.toString());
                LOG.log(Level.INFO, "Id --> {0}",new Object[]{Id});
                
                if ("post".equals(from)) {
                    Post post = postService.getById(Id);
                    
                    LOG.log(Level.INFO, "post --> {0}",new Object[]{post});
                    if (post != null) {
                        InputStream attachment = post.getAttachment();
                        String attachmentName = post.getAttachmentName();
                        
                        LOG.log(Level.INFO, "attachmentName --> {0}",new Object[]{attachmentName});
                        
                        ServletContext ctx = getServletContext();
                        String mimeType = ctx.getMimeType(attachmentName);
                        
                        LOG.log(Level.INFO, "mimeType --> {0}",new Object[]{mimeType});
                        
                        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
                        response.setContentLength((int) attachment.available());
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + attachmentName + "\"");
                        
                        ServletOutputStream os = response.getOutputStream();
                        byte[] bufferData = new byte[1024];
                        int read=0;
                        while((read = attachment.read(bufferData))!= -1){
                            os.write(bufferData, 0, read);
                        }
                        os.flush();
                        os.close();
                        attachment.close();
                    }else{
                        LOG.log(Level.INFO, "Post is Null");
                    }
                }else if ("prop".equals(from)) {
                    Proposal proposal = proposalService.getById(Id);
                    
                    LOG.log(Level.INFO, "post --> {0}",new Object[]{proposal});
                    if (proposal != null) {
                        InputStream attachment = proposal.getAttachment();
                        String attachmentName = proposal.getAttachmentName();
                        
                        LOG.log(Level.INFO, "attachmentName --> {0}",new Object[]{attachmentName});
                        
                        ServletContext ctx = getServletContext();
                        String mimeType = ctx.getMimeType(attachmentName);
                        
                        LOG.log(Level.INFO, "mimeType --> {0}",new Object[]{mimeType});
                        
                        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
                        response.setContentLength((int) attachment.available());
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + attachmentName + "\"");
                        
                        ServletOutputStream os = response.getOutputStream();
                        byte[] bufferData = new byte[1024];
                        int read=0;
                        while((read = attachment.read(bufferData))!= -1){
                            os.write(bufferData, 0, read);
                        }
                        os.flush();
                        os.close();
                        attachment.close();
                    }else{
                        LOG.log(Level.INFO, "Proposal is Null");
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AttchmentDownloadController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        processRequest(request, response);
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
