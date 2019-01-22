/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.impl.ProposalDaoImpl;
import java.sql.SQLException;
import java.util.List;
import model.Proposal;
import utils.AdditionalData;
import utils.Pagination;
import dao.ProposalDao;
import service.ProposalService;

/**
 *
 * @author HaShaN
 */
public class ProposalServiceImpl implements ProposalService{

    private final ProposalDao proposalDao=new ProposalDaoImpl();
    
    @Override
    public boolean addProposal(Proposal proposal) throws ClassNotFoundException, SQLException {
        return proposalDao.addProposal(proposal) > 0;
    }

    @Override
    public List<Proposal> getByPost(int postId, Pagination pagination, AdditionalData additionalData) throws ClassNotFoundException, SQLException {
        return proposalDao.getByPost(postId, pagination, additionalData);
    }

    @Override
    public Proposal getById(int proposalId) throws ClassNotFoundException, SQLException {
        return proposalDao.getById(proposalId);
    }
    
}
