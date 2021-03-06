package ejb;

import entities.Emailkey;
import java.util.List;
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

    public boolean checkEmailKey(Emailkey emailkey) {
        boolean check = false;
        Query query = em.createNamedQuery("Emailkey.findByUserEmail");
        query.setParameter("userEmail", emailkey.getUserEmail());
        List<Emailkey> emailkeyList = query.getResultList();
        for (Emailkey item : emailkeyList) {
            if (item.getKeyID().equals(emailkey.getKeyID())) {
                check = true;
            }
        }
        return check;
    }

}
