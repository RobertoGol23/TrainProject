package entity.user;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


public class UserService {
    
    @PersistenceContext
	private EntityManager em;

    @Transactional
    public List<User> findUserByName(String nome) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        cq.select(root).where(cb.equal(root.get("nome"), nome));
        // è possibile anche fare select(root.get("nome attributo"))

        List<User> results = em.createQuery(cq).getResultList();
        
        return results;
    }


}
