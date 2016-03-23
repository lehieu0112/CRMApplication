package ejb;

import entities.Emailkey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmailkeyFacade extends AbstractFacade<Emailkey> {

    @PersistenceContext(unitName = "ojt_CRMSystem_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailkeyFacade() {
        super(Emailkey.class);
    }

//    public boolean checkEmailKey(Emailkey emailkey) {
//        boolean check = false;
//        Query query = em.createNamedQuery("Emailkey.findByUserEmail");
//        query.setParameter("userEmail", emailkey.getUserEmail());
//        List<Emailkey> emailkeyList = query.getResultList();
//        for (int i = 0; i < emailkeyList.size(); i++) {
//            if ()
//        }
//        return check;
//    }

}
