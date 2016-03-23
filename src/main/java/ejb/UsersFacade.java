package ejb;

import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public void activeUser(Integer userID) {
        Query query = em.createQuery("update Users u set u.isActive = 'true' where u.userID = ?1");
        query.setParameter(1, userID);
        query.executeUpdate();
    }

    public void deactiveUser(Integer userID) {
        Query query = em.createQuery("update Users u set u.isActive = 'false' where u.userID = ?1");
        query.setParameter(1, userID);
        query.executeUpdate();
    }

    public boolean isExistLeads(Users user) {
        boolean isExist = false;
        Query q = getEntityManager().createNamedQuery("Users.findByUserEmail");
        q.setParameter("userEmail", user.getUserEmail());
        if (q.getResultList().size() > 0) {
            isExist = true;
        }
        return isExist;
    }
}
