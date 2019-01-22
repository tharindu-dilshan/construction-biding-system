/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Proposal;
import utils.AdditionalData;
import utils.Pagination;

/**
 *
 * @author HaShaN
 */
public interface ProposalDao {
    public final String INSERT_SQL = "INSERT INTO proposal(post_id, user_id, budget, duration, attachment, attachment_name, description, date_time) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public final String GET_BY_POST_SQL = "SELECT * FROM proposal WHERE post_id = ? ORDER BY date_time DESC LIMIT ?, ?";
    public final String GET_BY_ID_SQL = "SELECT * FROM proposal WHERE id = ?";
    public final String COUNT_BY_POST_SQL = "SELECT COUNT(*) FROM proposal WHERE post_id = ?";
    int addProposal(Proposal proposal) throws ClassNotFoundException,SQLException;
    List<Proposal> getByPost(int postId, Pagination pagination, AdditionalData additionalData) throws ClassNotFoundException,SQLException;
    Proposal getById(int proposalId) throws ClassNotFoundException,SQLException;
}
