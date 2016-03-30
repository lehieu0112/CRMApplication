/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.OpportunityFacade;
import ejb.OrdersFacade;
import entities.Orders;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "orderManagedBean")
@ViewScoped
public class OrderManagedBean implements Serializable {

    @Inject
    private OrdersFacade orderEJB;
    @Inject
    private OpportunityFacade opportunityEJB;

    private Orders order = new Orders();
    private List<Orders> searchList;
    private Integer pid;
    private Date date1;
    private Date date2;
    private List<Orders> listOrders;

    public List<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Orders> listOrders) {
        this.listOrders = listOrders;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Orders> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Orders> searchList) {
        this.searchList = searchList;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderManagedBean() {
    }

    public List<Orders> doFindOrders() {
        return orderEJB.findAll();
    }

    public void doAddOpportunity() {
        order.setOpportunityID(opportunityEJB.find(order.getOpportunityID().getOpportunityID()));
    }

    public String doCreateOrder() {
        order.setOpportunityID(opportunityEJB.find(order.getOpportunityID().getOpportunityID()));
        order.setTotalAmount(order.getOpportunityID().getProductID().getProductPrice() * order.getQuantity());
        orderEJB.create(order);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
        order = new Orders();
        return "makeorder.xhtml";
    }
    
    public List<Orders> doFindOrdersByProduct(){
        return orderEJB.doFindOrdersByProduct(pid);
    }
    
    public List<Orders> doFindOrdersByCampaign(){
        return orderEJB.doFindOrdersByCampaign(pid);
    }
    
    public void doFindOrdersByBate(){
        listOrders = orderEJB.doFindOrdersByDate(date1, date2);
    }
}
