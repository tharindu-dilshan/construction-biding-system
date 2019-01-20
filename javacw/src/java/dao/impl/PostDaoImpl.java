/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao.impl;

import com.mysql.jdbc.JDBC4PreparedStatement;
import dao.PostDao;
import dao.UserDao;
import db.DBConnection;
import enums.RoleEnum;
import enums.StatusEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;

/**
 *
 * @author HaShaN
 */
public class PostDaoImpl implements PostDao{
    private static final Logger LOG = Logger.getLogger(PostDaoImpl.class.getName());
    
    private final UserDao userDao=new UserDaoImpl();
    
    @Override
    public int addPost(Post post) throws ClassNotFoundException,SQLException{
        LOG.log(Level.INFO, "@Add Post --> {0}",new Object[]{post});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
        
        ps.setInt(1, post.getUser().getId());
        ps.setString(2, post.getTitle());
        ps.setString(3, post.getDescription());
        ps.setDouble(4, post.getBudget());
        ps.setTimestamp(5, post.getTime() != null ? new Timestamp(post.getTime().getTime()) : null);
        ps.setString(6, post.getEmail());
        ps.setString(7, post.getContactNo());
        ps.setBlob(8, post.getAttachment());
        ps.setString(9, post.getAttachmentName());
        ps.setShort(10, post.getStatus());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        return ps.executeUpdate();
    }
    
    @Override
    public Post getById(int postId) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getById --> {0}",new Object[]{postId});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_ID_SQL);
        ps.setInt(1, postId);
        ps.setShort(2, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        if(result.next()){
            return new Post(result.getInt(1), userDao.getById(result.getInt(2)),
                    result.getString(3), result.getString(4), result.getDouble(5),
                    result.getTimestamp(6), result.getString(7), result.getString(8),
                    result.getBinaryStream(9),result.getString(10),result.getShort(11));
        }else{
            return null;
        }
    }
    
    @Override
    public List<Post> getByUser(int userId) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getByUser --> {0}",new Object[]{userId});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_USER_SQL);
        ps.setInt(1, userId);
        ps.setShort(2, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        List<Post> posts=new ArrayList<>();
        while(result.next()){
            posts.add(new Post(result.getInt(1), userDao.getById(result.getInt(2)),
                    result.getString(3), result.getString(4), result.getDouble(5),
                    result.getTimestamp(6), result.getString(7), result.getString(8),
                    result.getBinaryStream(9),result.getString(10),result.getShort(11)));
        }
        return posts;
    }
    
    @Override
    public List<Post> getAll() throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getAll ");
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_ALL_SQL);
        ps.setShort(1, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        List<Post> posts=new ArrayList<>();
        while(result.next()){
            posts.add(new Post(result.getInt(1), userDao.getById(result.getInt(2)),
                    result.getString(3), result.getString(4), result.getDouble(5),
                    result.getTimestamp(6), result.getString(7), result.getString(8),
                    result.getBinaryStream(9),result.getString(10),result.getShort(11)));
        }
        return posts;
    }

    @Override
    public int updatePostStatus(Post post) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@Update Post Status --> {0}",new Object[]{post});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(STATUS_UPDATE_SQL);
        
        ps.setShort(1, post.getStatus());
        ps.setInt(2, post.getId());
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        return ps.executeUpdate();
    }

    @Override
    public List<Post> getByRole(RoleEnum role) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getByRole --> {0}",new Object[]{role.name()});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_ROLE_SQL);
        ps.setString(1, role.name());
        ps.setShort(2, StatusEnum.DELETED.getStatusId());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        List<Post> posts=new ArrayList<>();
        while(result.next()){
            posts.add(new Post(result.getInt(1), userDao.getById(result.getInt(2)),
                    result.getString(3), result.getString(4), result.getDouble(5),
                    result.getTimestamp(6), result.getString(7), result.getString(8),
                    result.getBinaryStream(9),result.getString(10),result.getShort(11)));
        }
        return posts;
    }

    
    
}
