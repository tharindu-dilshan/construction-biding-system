/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import model.Proposal;

/**
 *
 * @author HaShaN
 */
public class PostProposalsDto {
    private List<Proposal> proposals;
    private long count;

    public List<Proposal> getProposals() {
        return proposals;
    }

    public long getCount() {
        return count;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PostCommentsDto{" + "comments=" + proposals + ", count=" + count + '}';
    }
}
