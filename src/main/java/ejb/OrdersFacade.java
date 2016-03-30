/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Orders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;
    @Inject
    private OpportunityFacade oppoEJB;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    public List<Orders> findOrdersByOppoID(Integer id) {
        Query query = em.createQuery("select o from Orders o where o.opportunityID=?1");
        query.setParameter(1, oppoEJB.find(id));
        return query.getResultList();
    }

    @Inject
    private ProductsFacade productEJB;

    public List<Orders> doFindOrdersByProduct(Integer pid) {
        Query q = em.createQuery("SELECT o from Orders o WHERE o.opportunityID IN "
                + "(SELECT op FROM Opportunity op WHERE op.productID=?1)");
        q.setParameter(1, productEJB.find(pid));
        return q.getResultList();
    }

    @Inject
    private CampaignFacade campaignEJB;

    public List<Orders> doFindOrdersByCampaign(Integer id) {
        Query q = em.createQuery("SELECT o from Orders o WHERE o.opportunityID"
                + " in (SELECT op FROM Opportunity op WHERE op.productID"
                + " in(SELECT p FROM Products p WHERE p.campaignID=?1))");
        q.setParameter(1, campaignEJB.find(id));
        return q.getResultList();
    }

    public List<Orders> doFindOrdersByDate(Date date1, Date date2) {
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.orderDate BETWEEN ?1 AND ?2");
        q.setParameter(1, date1);
        q.setParameter(2, date2);
        return q.getResultList();
    }

    public ArrayList<Double> doReportOrders(Integer year) {
        ArrayList<Double> list = new ArrayList<>();
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.orderDate BETWEEN ?1 AND ?2");
        for (int i = 0; i < 12; i++) {
            int y = year;
            int j=i+1;
            int k = 28;
            int m = y%4;
            if(m==0){
                k = 29;
            }
            if((j==1)||(j==3)||(j==5)||(j==7)||(j==8)||(j==10)||(j==12)){
                k=31;
            }else if((j==4)||(j==6)||(j==9)||(j==11)){
                k=30;
            }
            Date date1 = new Date(y-1900, i, 1);
            Date date2 = new Date(y-1900, i, k);
            q.setParameter(1, date1);
            q.setParameter(2, date2);
            List<Orders> listOrders = q.getResultList();
            Double sum = 0.0;
            for (Orders o: listOrders) {
                sum = sum + o.getTotalAmount();
            }
            list.add(sum);
        }
        return list;
    }
}
