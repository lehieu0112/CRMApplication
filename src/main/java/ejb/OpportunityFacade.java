package ejb;

import entities.Opportunity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OpportunityFacade extends AbstractFacade<Opportunity> {

    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpportunityFacade() {
        super(Opportunity.class);
    }

    public boolean isExistOpportunity(Opportunity opportunity) {
        boolean isExist = false;
        Query query = getEntityManager().createNamedQuery("Opportunity.findByOpportunityID");
        query.setParameter("opportunityID", opportunity.getOpportunityID());
        if (query.getResultList().size() > 0) {
            isExist = true;
        }
        return isExist;
    }
}
