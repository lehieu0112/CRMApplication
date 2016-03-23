/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Leads;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
public class LeadsFacade extends AbstractFacade<Leads> {
    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LeadsFacade() {
        super(Leads.class);
    }
    
    public boolean isExistLeads(Leads lead){
        boolean isExist = false;
        Query q = getEntityManager().createNamedQuery("Leads.findByLeadEmail");
        q.setParameter("leadEmail", lead.getLeadEmail());       
        if(q.getResultList().size()>0){
            isExist = true;           
        }
        return isExist;
    }
}
