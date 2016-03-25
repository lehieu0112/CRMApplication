/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.CampaignFacade;
import entities.Campaign;
import entities.Users;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Named(value = "campaignManagedBean")
@RequestScoped
public class CampaignManagedBean {

    @Inject
    private CampaignFacade campaignEJB;

    private HttpSession session = (HttpSession) FacesContext.
            getCurrentInstance().getExternalContext().getSession(false);
    private Users user = (Users) session.getAttribute("userlogin");
    private Campaign campaign = new Campaign();
    private List<Campaign> searchList;
 
    public List<Campaign> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Campaign> searchList) {
        this.searchList = searchList;
    }
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public CampaignManagedBean() {
    }
    
    public List<Campaign> doFindAllCampaign(){
        return campaignEJB.findAll();
    }

    public String doCreateCampaign() {
        campaign.setUserID(user);
        campaignEJB.create(campaign);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success",
                        "Create Success !"));
        campaign = new Campaign();
        return "addcampaign.xhtml";
    }
    
    public String doEditCampaign(Integer id) {
        campaign = campaignEJB.find(id);
        return "editcampaign.xhtml";
    }
    
    public String applyEditCampaign(){
        campaign.setUserID(user);
        campaignEJB.edit(campaign);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Edit Success", "Edit Success !"));
        return "editcampaign.xhtml";
    }
    
    public String doDeleteCampaign(Integer id) {
        campaignEJB.remove(campaignEJB.find(id));
        return "listcampaign.xhtml";
    }
    
    public String viewProducts(Integer id){
        campaign.setProductsList(campaignEJB.getProducts(campaignEJB.find(id)));
        return "viewproducts.xhtml";
    }
    
    
    
}
