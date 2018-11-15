/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Professores;
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
public class ProfessoresService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    public Professores findProf(Integer idProf) {
        Professores prof = null;

        try {
            prof = (Professores) em.createNamedQuery("Professores.findByIdProfessor").setParameter("idProfessor", idProf).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prof;
    }

    public List<Professores> listar() {
        List<Professores> prof = null;
        try {

            em = factory.createEntityManager();

            prof = em.createNamedQuery("Professores.findAll").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prof;
    }

    public void deleteProfessor(Integer idProf) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("Delete from professores where ID_PROFESSOR=?").setParameter(1, idProf)
                .executeUpdate();
        tx.commit();

    }

    public void addProfessor(String nomeProfessor) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        BigInteger id = (BigInteger) em.createNativeQuery("SELECT IF(MAX(ID_PROFESSOR) IS NULL, 1, MAX(ID_PROFESSOR) + 1)\n"
                + "FROM professores").getSingleResult();

        em.createNativeQuery("INSERT INTO professores(ID_PROFESSOR, NOME_PROFESSOR)VALUES(?, ?)")
                .setParameter(1, id)
                .setParameter(2, nomeProfessor)
                .executeUpdate();
        em.close();
        tx.commit(); 
    }

    public void updateProfessor(String nomeProfessor, Integer idProfInteger) {
        Professores professor = new Professores();
        professor.setIdProfessor(idProfInteger);
        professor.setNomeProfessor(nomeProfessor);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("UPDATE professores\n"
                + "SET\n"
                + "NOME_PROFESSOR = ?\n"
                + "WHERE ID_PROFESSOR = ?")
                .setParameter(1, nomeProfessor)
                .setParameter(2, idProfInteger)
                .executeUpdate();
        em.close();
        tx.commit();
    }
    
    public Integer getIdLastInsert(){
        
        List <Professores> lista =  listar();
        Professores professor;
        professor = lista.get(lista.size() - 1);
        return professor.getIdProfessor();       
    }

}
