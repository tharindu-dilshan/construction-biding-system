/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import com.mysql.jdbc.JDBC4PreparedStatement;
import dao.RoleDao;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

/**
 *
 * @author HaShaN
 */
public class RoleDaoImpl implements RoleDao{
    private static final Logger LOG = Logger.getLogger(RoleDaoImpl.class.getName());

    @Override
    public Role getById(int id) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@ GetById --> {0}",new Object[]{id});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_ID_SQL);
        ps.setInt(1, id);
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        if(result.next()){
            return new Role(result.getInt(1), result.getString(2));
        }else{
            return null;
        }
    }
    
}
