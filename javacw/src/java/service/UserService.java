/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.User;

/**
 *
 * @author HaShaN
 */
public interface UserService {
    boolean addUser(User user) throws ClassNotFoundException,SQLException;
    
    boolean editUser(User user) throws ClassNotFoundException,SQLException;
    
    User getById(int id) throws ClassNotFoundException,SQLException;
    
    User getByUserName(String userName) throws ClassNotFoundException,SQLException;
    
    User getByEmail(String email) throws ClassNotFoundException,SQLException;
    
    List<User> getAllExcept(String role) throws ClassNotFoundException,SQLException;
}
