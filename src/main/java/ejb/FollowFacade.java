package ejb;

import entities.Follow;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
