/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.LeadsFacade;
import entities.Leads;
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
    private Leads lead= new Leads();

    public Leads getLead() {
        return lead;
    }

    public void setLead(Leads lead) {
        this.lead = lead;
    }
    
    public LeadsManagedBean() {
    }
    
    public String doCreateLead(){
        leadEJB.create(lead);
        lead = new Leads();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                "Create Success !"));
        return "addlead.xhtml";
    }
}
