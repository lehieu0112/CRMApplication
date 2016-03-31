/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.CampaignFacade;
import ejb.ProductsFacade;
import entities.Campaign;
import entities.Products;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "productManagedBean")
@RequestScoped
public class ProductManagedBean implements Serializable {

    @Inject
    private ProductsFacade productEJB;
    @Inject
    private CampaignFacade campaignEJB;
        
    private Products product = new Products();
    private List<Products> searchList;
    private Integer campaignID;

    public Integer getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(Integer campaignID) {
        this.campaignID = campaignID;
    }

    public CampaignFacade getCampaignEJB() {
        return campaignEJB;
    }

    public void setCampaignEJB(CampaignFacade campaignEJB) {
        this.campaignEJB = campaignEJB;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public List<Products> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Products> searchList) {
        this.searchList = searchList;
    }
    
    public ProductManagedBean() {
    }
    
    public List<Campaign> getlistCampaign(){
        return campaignEJB.findAll();
    }
    
    public String doCreateProduct() {
        product.setCampaignID(campaignEJB.find(campaignID));
        productEJB.create(product);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
        product = new Products();
        return "addproduct.xhtml";
    }

    public List<Products> doFindAllProducts() {
        return productEJB.findAll();
    }

    public void doEditProduct() {
        product = productEJB.find(product.getProductID());       
    }
    
    public String editProduct(Integer id){
        product = productEJB.find(id); 
        return "editproduct.xhtml";
    }

    public String applyEditProduct() {
        product.setCampaignID(campaignEJB.find(campaignID));
        productEJB.edit(product);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Edit Success", "Edit Success !"));
        return "editproduct.xhtml";
    }

    public String doDeleteProduct(Integer productID) {
        productEJB.remove(productEJB.find(productID));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Success", "Delete Success !"));
        return "listproducts.xhtml";
    }
}
