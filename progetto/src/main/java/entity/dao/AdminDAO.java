package entity.dao;

import entity.user.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class AdminDAO {

	@PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvaAdmin(Admin a) {
        em.persist(a);
    }

    @Transactional
    public void eliminaAdminById(Long id) {
    	Admin a = getAdminById(id);
        if (a != null) {
            em.remove(a);
        }
    }
    
    @Transactional
    public void deleteAdmin(Admin a) {
        em.remove(em.contains(a) ? a : em.merge(a));
    }

    public Admin getAdminById(Long id) {
        return em.find(Admin.class, id);
    }

    @Transactional
    public void updateUser(Admin a) {
        em.merge(a);
    }
    
    
	//stampa lista utenti
	//stampa tutti gli acquisti
}
