/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import java.sql.SQLException;
import java.util.List;
import model.User;

/**
 *
 * @author HaShaN
 */
public interface UserDao {
    public final String GET_BY_ID_SQL = "SELECT * from user WHERE id=? AND status<>?";
    public final String INSERT_SQL = "INSERT INTO user(first_name, last_name,"
            + " country, contact_no, email, user_name, password, role_id,"
            + " status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final String MODIFY_SQL = "UPDATE user SET first_name=?, last_name=?, country=?, contact_no=?, email=?, password=?, status=? WHERE user_name=? AND status<>?";
    public final String GET_BY_USERNAME_SQL = "SELECT * from user WHERE user_name=? AND status<>?";
    public final String GET_BY_EMAIL_SQL = "SELECT * from user WHERE LOWER(email)=LOWER(?) AND status<>?";
    public final String GET_LIST_SQL = "SELECT u.* from user u INNER JOIN role r ON u.role_id = r.id WHERE LOWER(r.role_name) NOT IN (?,?) AND status<>?";
    int addUser(User user) throws ClassNotFoundException,SQLException;
    
    int editUser(User user) throws ClassNotFoundException,SQLException;
    
    User getById(int id) throws ClassNotFoundException,SQLException;
    
    User getByUserName(String userName) throws ClassNotFoundException,SQLException;
    
    User getByEmail(String email) throws ClassNotFoundException,SQLException;
    
    List<User> getAllExcept(String role) throws ClassNotFoundException,SQLException;
}
