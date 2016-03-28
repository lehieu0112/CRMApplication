/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.OpportunityFacade;
import ejb.OrdersFacade;
import entities.Orders;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "orderManagedBean")
@RequestScoped
public class OrderManagedBean {

    @Inject
    private OrdersFacade orderEJB;
    @Inject
    private OpportunityFacade opportunityEJB;

    private Orders order = new Orders();
    private List<Orders> searchList;

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
}
