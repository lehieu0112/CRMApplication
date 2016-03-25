package ejb;

import entities.Campaign;
import entities.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CampaignFacade extends AbstractFacade<Campaign> {

    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampaignFacade() {
        super(Campaign.class);
    }
    
    public List<Products> getProducts(Campaign c){
        Query query = em.createQuery("select p from Products p where p.campaignID=:c");
        query.setParameter("c", c);
        return query.getResultList();
    }

}
