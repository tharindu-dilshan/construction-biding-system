/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import enums.RoleEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.apache.commons.lang3.text.WordUtils;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 *
 * @author HaShaN
 */
@WebServlet("/users")
public class UserController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
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
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
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
        Object loggedUserOb = request.getSession().getAttribute("loggedUser");
        if (loggedUserOb != null) {
            try{
                User loggedUser = (User)loggedUserOb;
                try {
                    List<User> users = userService.getAllExcept(loggedUser.getRole().getRoleName());
                    request.setAttribute("userList", users);
                    if (loggedUser.getRole().getRoleName().equalsIgnoreCase(RoleEnum.SUPPLIER.name())) {
                        request.setAttribute("roleName", WordUtils.capitalizeFully(RoleEnum.CONTRACTOR.name())+"s");
                    }else if (loggedUser.getRole().getRoleName().equalsIgnoreCase(RoleEnum.CONTRACTOR.name())) {
                        request.setAttribute("roleName", WordUtils.capitalizeFully(RoleEnum.SUPPLIER.name())+"s");
                    }else{
                        request.setAttribute("isAdmin",true);
                        request.setAttribute("roleName", WordUtils.capitalizeFully(RoleEnum.CONTRACTOR.name())+"s"
                                +" & "+WordUtils.capitalizeFully(RoleEnum
                                        .SUPPLIER.name())+"s");
                    }
                    request.getRequestDispatcher("userlist.jsp").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassCastException e){
                request.setAttribute("errorMsg", "You are not logged in.");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("errorMsg", "You are not logged in.");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
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
