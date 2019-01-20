/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import enums.RoleEnum;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 *
 * @author HaShaN
 */
@WebServlet("/sign-in")
public class SignInController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SignInController.class.getName());
    
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
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            
            User byUserName = userService.getByUserName(userName);
            if (byUserName != null) {
                boolean isMatched = BCrypt.checkpw(password, byUserName.getPassword());
                if (isMatched) {
                    request.getSession().setAttribute("loggedUser", byUserName);
                    if (byUserName.getRole().getRoleName().equalsIgnoreCase(RoleEnum.SUPPLIER.name())) {
                    request.setAttribute("roleName", RoleEnum.CONTRACTOR.name().toUpperCase()+"S");
                    }else if (byUserName.getRole().getRoleName().equalsIgnoreCase(RoleEnum.CONTRACTOR.name())) {
                        request.setAttribute("roleName", RoleEnum.SUPPLIER.name().toUpperCase()+"S");
                    }else{
                        request.setAttribute("roleName", RoleEnum.CONTRACTOR.name().toUpperCase()+"S"
                                +" & "+RoleEnum.SUPPLIER.name().toUpperCase()+"S");
                    }
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }else{
                    request.setAttribute("errorMsg", "Bad login credentials.");
                    request.getRequestDispatcher("signin.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("errorMsg", "You are not a registed user..");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
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
