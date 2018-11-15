/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Cursos;
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
public class CursosService {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    public Cursos findCurso(Integer idCurso) {
        Cursos curso = null;

        try {
            curso = (Cursos) em.createNamedQuery("Cursos.findByIdCurso").setParameter("idCurso", idCurso).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curso;
    }

    public List<Cursos> listar() {
        List<Cursos> curso = null;
        try {

            em = factory.createEntityManager();

            curso = em.createNamedQuery("Cursos.findAll").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return curso;
    }

    public void deleteCurso(Integer idCurso) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("Delete from cursos where ID_CURSO=?").setParameter(1, idCurso)
                .executeUpdate();
        tx.commit();

    }

    public void addCurso(String nomeCurso) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        BigInteger id = (BigInteger) em.createNativeQuery("SELECT IF(MAX(ID_CURSO) IS NULL, 1, MAX(ID_CURSO) + 1)\n"
                + "FROM cursos").getSingleResult();

        em.createNativeQuery("INSERT INTO cursos(ID_CURSO, NOME_CURSO)VALUES(?, ?)")
                .setParameter(1, id)
                .setParameter(2, nomeCurso)
                .executeUpdate();
        em.close();
        tx.commit(); 
    }

    public void updateCurso(String nomeCurso, Integer idCursoInteger) {
        Cursos curso = new Cursos();
        curso.setIdCurso(idCursoInteger); 
        curso.setNomeCurso(nomeCurso);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("UPDATE cursos\n"
                + "SET\n"
                + "NOME_CURSO = ?\n"
                + "WHERE ID_CURSO = ?")
                .setParameter(1, nomeCurso)
                .setParameter(2, idCursoInteger)
                .executeUpdate();
        em.close();
        tx.commit();
    }
    
    public Integer getIdLastInsert(){
        
        List <Cursos> lista =  listar();
        Cursos curso;
        curso = lista.get(lista.size() - 1);
        return curso.getIdCurso();       
    }
    
}
