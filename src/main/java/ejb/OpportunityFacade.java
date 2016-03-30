package ejb;

import entities.Opportunity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
    
    @Inject
    private ProductsFacade productEJB;
    public List<Opportunity> doFindOppoByProduct(Integer id){
        Query q = em.createQuery("select o from Opportunity o where o.productID=?1");
        q.setParameter(1, productEJB.find(id));
        return q.getResultList();
    }
  
    @Inject
    private CampaignFacade campaignEJB;
    public List<Opportunity> doFindOppoByCampaign(Integer id){
        Query q = em.createQuery("SELECT o FROM Opportunity o WHERE o.productID"
                + " in(SELECT p FROM Products p WHERE p.campaignID=?1)");
        q.setParameter(1, campaignEJB.find(id));
        return q.getResultList();
    }
    
    public List<Opportunity> doFindOppoByView(Date startDate, Date endDate,Integer id) {
        Query q = em.createQuery("SELECT o FROM Opportunity o WHERE (o.opportunityDate BETWEEN ?1 AND ?2) AND (o.productID =?3)");
        q.setParameter(1, startDate);
        q.setParameter(2, endDate);
        q.setParameter(3, productEJB.find(id));
        return q.getResultList();
    }
}
