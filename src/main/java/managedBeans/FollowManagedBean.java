/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.FollowFacade;
import ejb.OpportunityFacade;
import entities.Follow;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "followManagedBean")
@RequestScoped
public class FollowManagedBean {

    @Inject
    private FollowFacade followEJB;
    @Inject
    private OpportunityFacade opportunityEJB;
    
    private Follow follow = new Follow();
    private List<Follow> searchList;

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public List<Follow> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Follow> searchList) {
        this.searchList = searchList;
    }
    
    public FollowManagedBean() {
    }
    
    public List<Follow> doFindFollows() {
        return followEJB.findAll();
    }
    
    public void doAddOpportunity(){
        follow.setOpportunityID(opportunityEJB.find(follow.getOpportunityID().getOpportunityID()));
    }
    
    public String applyCreateFollow() {
        follow.setOpportunityID(opportunityEJB.find(follow.getOpportunityID().getOpportunityID()));        
        followEJB.create(follow);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
        follow = new Follow();
        return "addfollow.xhtml";
    }
}
