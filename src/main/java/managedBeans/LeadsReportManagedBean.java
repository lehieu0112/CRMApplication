/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.LeadsFacade;
import entities.Leads;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "leadsReportManagedBean")
@ViewScoped
public class LeadsReportManagedBean implements Serializable {

    @Inject
    private LeadsFacade leadEJB;   
    
    private List<Leads> searchList;
    private List<Leads> listLeads;
    private Date date1;
    private Date date2;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public LeadsReportManagedBean() {
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public List<Leads> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Leads> searchList) {
        this.searchList = searchList;
    }

    public List<Leads> getListLeads() {
        return listLeads;
    }

    public void setListLeads(List<Leads> listLeads) {
        this.listLeads = listLeads;
    }
    
    public void doFindLeadsByDate(){
        listLeads = leadEJB.doFindLeadsByDate(date1, date2);
    }
    
    public List<Leads> doFindLeadsByCampaign(){
        return leadEJB.doFindLeadsByCampaign(id);
    }
    
    public List<Leads> doFindLeadsByProduct(){
        return leadEJB.doFindLeadsByProduct(id);
    }
}
