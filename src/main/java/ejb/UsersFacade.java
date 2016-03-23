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

    public void activeUser() {
        Query query = em.createQuery("update Users u set u.isActive = 1 where u.userID = ?1");
        query.setParameter(1, "userID");
        query.executeUpdate();
    }

    public void deactiveUser() {
        Query query = em.createQuery("update Users u set u.isActive = 0 where u.userID = ?1");
        query.setParameter(1, "userID");
        query.executeUpdate();
    }

    public void deleteUser() {
        Query query = em.createQuery("delete from Users u where u.userID = ?1");
        query.setParameter(1, "userID");
        query.executeUpdate();
    }
}
