/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao.impl;

import com.mysql.jdbc.JDBC4PreparedStatement;
import dao.RoleDao;
import dao.UserDao;
import db.DBConnection;
import enums.RoleEnum;
import enums.StatusEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author HaShaN
 */
public class UserDaoImpl implements UserDao{
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());
    
    private final RoleDao roleDao=new RoleDaoImpl();
    
    @Override
    public int addUser(User user) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@Add User --> {0}",new Object[]{user});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
        
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getCountry());
        ps.setString(4, user.getContactNo());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getUserName());
        ps.setString(7, user.getPassword());
        ps.setInt(8, user.getRole().getId());
        ps.setShort(9, user.getStatus());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        return ps.executeUpdate();
    }
    
    @Override
    public int editUser(User user) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@Edit User --> {0}",new Object[]{user});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(MODIFY_SQL);
        
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getCountry());
        ps.setString(4, user.getContactNo());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getPassword());
        ps.setShort(7, user.getStatus());
        ps.setString(8, user.getUserName());
        ps.setInt(9, StatusEnum.DELETED.getStatusId());
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        return ps.executeUpdate();
    }
    
    @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@ GetById --> {0}",new Object[]{id});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_ID_SQL);
        ps.setInt(1, id);
        ps.setShort(2, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        if(result.next()){
            return new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4),
                    result.getString(5), result.getString(6),
                    result.getString(7), result.getString(8),roleDao.getById(result.getInt(9)),result.getShort(10));
        }else{
            return null;
        }
    }
    
    @Override
    public User getByUserName(String userName) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@ getByUserName --> {0}",new Object[]{userName});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_USERNAME_SQL);
        ps.setString(1, userName);
        ps.setShort(2, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        if(result.next()){
            return new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4),
                    result.getString(5), result.getString(6),
                    result.getString(7), result.getString(8),roleDao.getById(result.getInt(9)),result.getShort(10));
        }else{
            return null;
        }
    }
    
    @Override
    public User getByEmail(String email) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@ getByEmail --> {0}",new Object[]{email});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_EMAIL_SQL);
        ps.setString(1, email);
        ps.setShort(2, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        if(result.next()){
            return new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4),
                    result.getString(5), result.getString(6),
                    result.getString(7), result.getString(8),roleDao.getById(result.getInt(9)),result.getShort(10));
        }else{
            return null;
        }
    }
    
    @Override
    public List<User> getAllExcept(String role) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getAllExcept ");
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_SQL);
        ps.setString(1, role.toLowerCase());
        ps.setString(2, RoleEnum.ADMIN.name().toLowerCase());
        ps.setShort(3, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        List<User> users=new ArrayList<>();
        while(result.next()){
            users.add(new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4),
                    result.getString(5), result.getString(6),
                    result.getString(7), result.getString(8),
                    roleDao.getById(result.getInt(9)),result.getShort(10)));
        }
        return users;
    }
    
    
    
}
