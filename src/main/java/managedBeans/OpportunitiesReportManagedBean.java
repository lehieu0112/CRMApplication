/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.OpportunityFacade;
import entities.Opportunity;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "opportunitiesReportManagedBean")
@ViewScoped
public class OpportunitiesReportManagedBean implements Serializable {

    @Inject
    private OpportunityFacade opportunityEJB;
    private Integer id;
    private List<Opportunity> searchList;
    
    public List<Opportunity> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Opportunity> searchList) {
        this.searchList = searchList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public OpportunitiesReportManagedBean() {
    }
    
    public List<Opportunity> doFindOpportunitiesByProduct() {
        return opportunityEJB.doFindOppoByProduct(id);
    }

    public List<Opportunity> doFindOpportunitiesByCampaign() {
        return opportunityEJB.doFindOppoByCampaign(id);
    }

}
