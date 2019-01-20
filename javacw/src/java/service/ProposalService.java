/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package service;

import java.sql.SQLException;
import java.util.List;
import model.Proposal;
import utils.AdditionalData;
import utils.Pagination;

/**
 *
 * @author HaShaN
 */
public interface ProposalService {
    boolean addProposal(Proposal proposal) throws ClassNotFoundException,SQLException;
    List<Proposal> getByPost(int postId, Pagination pagination, AdditionalData additionalData) throws ClassNotFoundException,SQLException;
    Proposal getById(int proposalId) throws ClassNotFoundException,SQLException;
}
