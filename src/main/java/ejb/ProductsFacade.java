/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Products;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> {
    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
    
    public List<Products> doFindProducts(){
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now);
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.campaignID IN"
                + " (SELECT c FROM Campaign c WHERE ((c.startDate<=?1) AND (c.endDate>=?2)))");
        q.setParameter(1, date);
        q.setParameter(2, date);     
        return q.getResultList();
    }
    
}
