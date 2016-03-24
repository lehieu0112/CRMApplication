package managedBeans;

import ejb.OpportunityFacade;
import entities.Opportunity;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "opportunityManagedBean")
@RequestScoped
public class OpportunityManagedBean {

    @Inject
    private OpportunityFacade opportunityEJB;
    private Opportunity opportunity = new Opportunity();
    private List<Opportunity> opportunityList;

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public String doCreateUser() {
        if (opportunityEJB.isExistOpportunity(opportunity)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "User Email is Existed", "User Email is Existed"));
        } else {
            opportunityEJB.create(opportunity);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
            opportunity = new Opportunity();
        }
        return "addopportunity.xhtml";
    }

}
