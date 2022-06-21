package fr.exagone.demoflywaydb.repository;

import fr.exagone.demoflywaydb.model.Utilisateur;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private EntityManager em;

    public Utilisateur find(Long id) {
        return em.find(Utilisateur.class, id);
    }

}
