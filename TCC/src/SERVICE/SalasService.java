/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Salas;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author marcelocogo
 */
public class SalasService {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    public Salas findSala(Integer idSala) {
        Salas sala = null;

        try {
            sala = (Salas) em.createNamedQuery("Salas.findByIdsala").setParameter("idsala", idSala).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sala;
    }

    public List<Salas> listar() {
        List<Salas> sala = null;
        try {

            em = factory.createEntityManager();

            sala = em.createNamedQuery("Salas.findAll").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sala;
    }

    public void deleteSala(Integer idSala) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("Delete from salas where idsala=?").setParameter(1, idSala)
                .executeUpdate();
        tx.commit();

    }

    public void addSala(String nomeSala) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //BigInteger id = (BigInteger) em.createNativeQuery("SELECT IF(MAX(idsala) IS NULL, 1, MAX(idsala) + 1)\n"
         //       + "FROM salas").getSingleResult();

        em.createNativeQuery("INSERT INTO salas(nome)VALUES(?)")
                
                .setParameter(1, nomeSala)
                .executeUpdate();
        em.close();
        tx.commit(); 
    }

    public void updateSala(String nomeSala, Integer idSalaInteger) {
        Salas sala = new Salas();
        sala.setNome(nomeSala);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("UPDATE salas\n"
                + "SET\n"
                + "nome = ?\n"
                + "WHERE idsala = ?")
                .setParameter(1, nomeSala)
                .setParameter(2, idSalaInteger)
                .executeUpdate();
        em.close();
        tx.commit();
    }
    
    public Integer getIdLastInsert(){
        
        List <Salas> lista =  listar();
        Salas sala;
        sala = lista.get(lista.size() - 1);
        return sala.getIdsala();
    }
    
}
