/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.LeadsFacade;
import ejb.OpportunityFacade;
import ejb.OrdersFacade;
import ejb.ProductsFacade;
import entities.Opportunity;
import entities.Orders;
import entities.Products;
import entities.Users;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;


@Named(value = "opportunityManagedBean")
@RequestScoped
public class OpportunityManagedBean {

    @Inject
    private OpportunityFacade opportunityEJB;
    @Inject
    private ProductsFacade productEJB;
    @Inject
    private LeadsFacade leadEJB;   
    @Inject
    private OrdersFacade orderEJB;
    

    private Opportunity opportunity = new Opportunity();
    private HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
    private Users user = (Users)session.getAttribute("userlogin");
    private List<Opportunity> searchList;
     

    public List<Opportunity> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Opportunity> searchList) {
        this.searchList = searchList;
    }
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public OpportunityManagedBean() {
    }
    
    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }
    
    public void doAddLeadID(){
        opportunity.setLeadID(leadEJB.find(opportunity.getLeadID().getLeadID()));
    }
    
    public List<Opportunity> doFindAllOpportunity(){
        return opportunityEJB.findAll();
    }
    
    public List<Products> getProductsList(){
        return productEJB.findAll();
    }
    
    public String doCreateOpportunity() {
        opportunity.setLeadID(leadEJB.find(opportunity.getLeadID().getLeadID()));
        opportunity.setUserID(user);
        opportunityEJB.create(opportunity);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
        opportunity = new Opportunity();
        return "addopportunity.xhtml";
    }
    
    
}
