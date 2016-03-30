package ejb;

import entities.Follow;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FollowFacade extends AbstractFacade<Follow> {

    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FollowFacade() {
        super(Follow.class);
    }
    
    public List<Follow> doFindFollowNow(){
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now);
        Query q = em.createQuery("SELECT f FROM Follow f WHERE f.nextDate = ?1");
        q.setParameter(1, date);
        return q.getResultList();
    }
    
    public List<Follow> doFindFollowNext(){
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now);
        Query q = em.createQuery("SELECT f FROM Follow f WHERE f.nextDate > ?1");
        q.setParameter(1, date);
        return q.getResultList();
    }

}
