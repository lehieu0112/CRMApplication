package ejb;

import entities.Users;
import java.util.List;
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
    
    public boolean checkLogin(String username,String password){
        boolean login = false;
        Query q = getEntityManager().createNamedQuery("Users.findByLoginName");
        q.setParameter("loginName", username);  
        if(q.getResultList().size()>0){
            Users u = (Users)q.getResultList().get(0);
            if((u.getLoginPass().equals(password))&&(u.getIsActive())){
                login = true;
            }
        }
        return login;
    }
    
    public Users getUserByLoginName(String loginName){
        Users user = new Users();
        Query q = getEntityManager().createNamedQuery("Users.findByLoginName");
        q.setParameter("loginName", loginName);  
        if(q.getResultList().size()>0){
            user = (Users)q.getResultList().get(0);           
        }
        return user;
    }

    public void activeUser(Integer userID) {
        Query query = em.createQuery("update Users u set u.isActive = true where u.userID = ?1");
        query.setParameter(1, userID);
        query.executeUpdate();
    }

    public void deactiveUser(Integer userID) {
        Query query = em.createQuery("update Users u set u.isActive = false where u.userID = ?1");
        query.setParameter(1, userID);
        query.executeUpdate();
    }

    public boolean isExistUsers(Users user) {
        boolean isExist = false;
        Query query = em.createNamedQuery("Users.findByUserEmail");
        query.setParameter("userEmail", user.getUserEmail());
        if (query.getResultList().size() > 0) {
            isExist = true;
        }
        return isExist;
    }

    public void resetPass(String userEmail, String loginPass) {
        Query query = em.createQuery("UPDATE Users u SET u.loginPass=?1 WHERE u.userEmail=?2");
        query.setParameter(1, loginPass);
        query.setParameter(2, userEmail);
        query.executeUpdate();
    }

    public List<Users> doFindUserByEmail(Users user) {
        Query query = em.createNamedQuery("Users.findByUserEmail");
        query.setParameter("userEmail", user.getUserEmail());
        return query.getResultList();
    }

}
