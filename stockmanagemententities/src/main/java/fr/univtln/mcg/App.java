package fr.univtln.mcg;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CRoom room = CRoom.builder("e").build();
        CMateriel materiel = CMateriel.builder("test").mRoom(room).build();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.persist(room);
        transac.commit();
        em.close();
        emf.close();

    }
}
