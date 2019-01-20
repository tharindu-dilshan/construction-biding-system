/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import com.mysql.jdbc.JDBC4PreparedStatement;
import dao.UserDao;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Proposal;
import model.Post;
import utils.AdditionalData;
import utils.Pagination;
import dao.ProposalDao;

/**
 *
 * @author HaShaN
 */
public class ProposalDaoImpl implements ProposalDao{
    private static final Logger LOG = Logger.getLogger(ProposalDaoImpl.class.getName());
       
    private final UserDao userDao=new UserDaoImpl();
    
    @Override
    public int addProposal(Proposal proposal) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@Add Proposal --> {0}",new Object[]{proposal});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
        
        ps.setInt(1, proposal.getPost().getId());
        ps.setInt(2, proposal.getUser().getId());
        ps.setString(3, proposal.getBudget());
        ps.setString(4, proposal.getDuration());
        ps.setBlob(5, proposal.getAttachment());
        ps.setString(6, proposal.getAttachmentName());
        ps.setString(7, proposal.getDescription());
        ps.setTimestamp(8, proposal.getDateTime()!= null ? new Timestamp(proposal.getDateTime().getTime()) : null);
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        return ps.executeUpdate();
    }

    @Override
    public List<Proposal> getByPost(int postId, Pagination pagination, AdditionalData additionalData) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getByPost --> {0}",new Object[]{postId});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_POST_SQL);
        ps.setInt(1, postId);
        ps.setInt(2, pagination.getOffset());
        ps.setInt(3, pagination.getCount());
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        List<Proposal> proposals=new ArrayList<>();
        while(result.next()){
            proposals.add(new Proposal(result.getInt(1),new Post(result.getInt(2)),
                    userDao.getById(result.getInt(3)), result.getString(4),
                    result.getString(5),result.getBinaryStream(6),
                    result.getString(7),result.getString(8),result.getTimestamp(9)));
        }
        
        PreparedStatement countPs = conn.prepareStatement(COUNT_BY_POST_SQL);
        countPs.setInt(1, postId);
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet count = countPs.executeQuery();
        if(count.next()){
            additionalData.setCount(count.getLong(1));
        }
        
        return proposals;
    }

    @Override
    public Proposal getById(int proposalId) throws ClassNotFoundException, SQLException {
        LOG.log(Level.INFO, "@getById --> {0}",new Object[]{proposalId});
        
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_ID_SQL);
        ps.setInt(1, proposalId);
        
        LOG.log(Level.INFO, "@SQL to be execute {0}",new Object[]{((JDBC4PreparedStatement)ps).asSql()});
        
        ResultSet result = ps.executeQuery();
        if(result.next()){
            return new Proposal(result.getInt(1),new Post(result.getInt(2)),
                    userDao.getById(result.getInt(3)), result.getString(4),
                    result.getString(5),result.getBinaryStream(6),
                    result.getString(7),result.getString(8),result.getTimestamp(9));
        }else{
            return null;
        }
    }
    
}
