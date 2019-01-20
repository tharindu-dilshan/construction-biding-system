/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import enums.RoleEnum;
import enums.StatusEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 *
 * @author HaShaN
 */
@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(SignUpController.class.getName());
    
    private UserService userService=new UserServiceImpl();
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
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String country = request.getParameter("country");
            String contactNo = request.getParameter("contactNo");
            String email = request.getParameter("email");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            
            User userByEmail = userService.getByEmail(email);
            boolean hasError = true;
            //check whether username is already exist
            if (userByEmail == null) {
                User userByUserName = userService.getByUserName(userName);
                //check whether email is already exist
                if (userByUserName == null) {
                    LOG.log(Level.INFO, "Plain Password --> {0}",new Object[]{password});
                    
                    password = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    
                    LOG.log(Level.INFO, "BCrypt Password --> {0}",new Object[]{password});
                    
                    //If role id is null,set CONTRACTOR as default
                    int roleId = request.getParameter("role") != null ?
                            Integer.parseInt(request.getParameter("role"))
                            : RoleEnum.CONTRACTOR.getRoleId();
                    
                    boolean isUserAdded = userService.addUser(new User(firstName, lastName, country,
                            contactNo, email, userName, password, new Role(roleId),
                            StatusEnum.ACTIVE.getStatusId()));
                    
                    hasError = !isUserAdded;
                }else{
                    hasError = true;
                    request.setAttribute("errorMsg", "User name is already exist..");
                }
            }else{
                hasError = true;
                request.setAttribute("errorMsg", "Email is already exist..");
            }
            request.setAttribute("Res", hasError);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
