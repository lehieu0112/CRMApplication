/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.LeadsFacade;
import ejb.UsersFacade;
import entities.Leads;
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
@Named(value = "leadsManagedBean")
@RequestScoped
public class LeadsManagedBean {
    
    @Inject
    private LeadsFacade leadEJB;
    @Inject
    private UsersFacade userEJB;
    private Leads lead= new Leads();
    private List<Leads> searchList;

    public List<Leads> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Leads> searchList) {
        this.searchList = searchList;
    }
    public Leads getLead() {
        return lead;
    }

    public void setLead(Leads lead) {
        this.lead = lead;
    }
    
    public LeadsManagedBean() {
    }
    
    public String doCreateLead(){
        lead.setUserID(userEJB.find(1));
        leadEJB.create(lead);
        lead = new Leads();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success",
                "Create Success !"));
        return "addlead.xhtml";
    }
    
    public List<Leads> doFindAllLeads(){
        return leadEJB.findAll();
    }
}
