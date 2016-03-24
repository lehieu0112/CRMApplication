/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
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
            if(u.getLoginPass().equals(password)){
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
}
