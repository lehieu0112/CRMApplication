/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Plan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class PlanFacade extends AbstractFacade<Plan> {
    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanFacade() {
        super(Plan.class);
    }
    
}
