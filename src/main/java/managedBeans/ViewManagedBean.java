/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.OpportunityFacade;
import ejb.ProductsFacade;
import ejb.ViewFacade;
import entities.Opportunity;
import entities.Products;
import entities.Views;
import java.util.Date;
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
@Named(value = "viewManagedBean")
@RequestScoped
public class ViewManagedBean {
    
    @Inject
    private ViewFacade viewEJB;
    @Inject
    private OpportunityFacade opportunityEJB;
    @Inject
    private ProductsFacade productEJB;
    
    private Views view = new Views();
    private List<Views> searchList;
    private Date startDate;
    private Date endDate;
    private List<Opportunity> opportunityList;
   
    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    public ViewFacade getViewEJB() {
        return viewEJB;
    }

    public void setViewEJB(ViewFacade viewEJB) {
        this.viewEJB = viewEJB;
    }

    public Views getView() {
        return view;
    }

    public void setView(Views view) {
        this.view = view;
    }

    public List<Views> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Views> searchList) {
        this.searchList = searchList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    public List<Products> doFindAllProductList() {
        return productEJB.findAll();
    }

    public String doCreateView() {
        viewEJB.create(view);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
        view = new Views();
        return "addview.xhtml";
    }

    public List<Views> doFindAllView() {
        return viewEJB.findAll();
    }


    public String doDeleteView(Integer viewID) {
        viewEJB.remove(viewEJB.find(viewID));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Success", "Delete Success !"));
        return "listview.xhtml";
    }

    public String doFindOpportunitiesByView(Date startDate, Date endDate,Integer id) {
        opportunityList = opportunityEJB.doFindOppoByView(startDate, endDate, id);
        return "listopportunitiesforview.xhtml";
    }
}
