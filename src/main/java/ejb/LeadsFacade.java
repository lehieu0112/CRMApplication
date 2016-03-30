package ejb;

import entities.Leads;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public boolean isExistLeads(Leads lead) {
        boolean isExist = false;
        Query q = getEntityManager().createNamedQuery("Leads.findByLeadEmail");
        q.setParameter("leadEmail", lead.getLeadEmail());
        if (q.getResultList().size() > 0) {
            isExist = true;
        }
        return isExist;
    }
    
    @Inject
    private CampaignFacade campaignEJB;
    public List<Leads> doFindLeadsByCampaign(Integer id){
        Query q = em.createNativeQuery("select * from leads l where l.leadID"
                + " in (select o.leadID from opportunity o where o.productID"
                + " in (select p.productID from products p where p.campaignID ="+id+")"
                + " group by o.leadID )",Leads.class);     
        List<Leads> listLeads = q.getResultList();      
        return listLeads;
    }
    
    @Inject
    private ProductsFacade productsEJB;
    public List<Leads> doFindLeadsByProduct(Integer id){
        Query q = em.createNativeQuery("select * from leads l where l.leadID"
                + " in (select o.leadID from opportunity o"
                + " where o.productID="+id+" group by o.leadID)",Leads.class);
        List<Leads> listLeads = q.getResultList();      
        return listLeads;
    }
    
    public List<Leads> doFindLeadsByDate(Date date1,Date date2){
        Query q = em.createQuery("SELECT l FROM Leads l WHERE l.dateCreated BETWEEN ?1 AND ?2");
        q.setParameter(1, date1);
        q.setParameter(2, date2);
        return q.getResultList();
    }
}
