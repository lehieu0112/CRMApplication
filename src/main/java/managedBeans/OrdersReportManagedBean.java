/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.OrdersFacade;
import entities.Orders;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "ordersReportManagedBean")
@ViewScoped
public class OrdersReportManagedBean implements Serializable {

    @Inject
    private OrdersFacade orderEJB;
    
    private Date date1;
    private Date date2;
    private List<Orders> listOrders;
    private List<Orders> searchList;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<Orders> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Orders> searchList) {
        this.searchList = searchList;
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

    public List<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Orders> listOrders) {
        this.listOrders = listOrders;
    }
    
    public OrdersReportManagedBean() {
    }
    
    public void doFindOrdersByBate(){
        listOrders = orderEJB.doFindOrdersByDate(date1, date2);
    }
    
    public List<Orders> doFindOrdersByProduct(){
        return orderEJB.doFindOrdersByProduct(id);
    }
    
    public List<Orders> doFindOrdersByCampaign(){
        return orderEJB.doFindOrdersByCampaign(id);
    }
     
}
