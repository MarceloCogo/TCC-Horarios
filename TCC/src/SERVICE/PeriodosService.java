/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Disciplinas;
import MODEL.Periodos;
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
public class PeriodosService {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    public Periodos findPeriodo(Integer idPeriodo) {
        Periodos periodo = null;

        try {
            periodo = (Periodos) em.createNamedQuery("Periodos.findByIdPeriodo").setParameter("ID_PERIODO", idPeriodo).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodo;
    }

    public List<Periodos> listar(Integer idCurso, String semestre) {
        List<Periodos> periodo = null;
        try {

            //em = factory.createEntityManager();
            
            em = factory.createEntityManager();

            if (idCurso != null) {
                

                periodo = em.createNativeQuery("select * from periodos where ID_CURSO =? or SEMESTRE LIKE ?",
                        Periodos.class)
                        .setParameter(1, idCurso)
                        .setParameter(2, "'"+semestre+"'")
                        .getResultList();

            } else {
                periodo = em.createNamedQuery("Periodos.findAll").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getResultList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodo;
    }

    public void deletePeriodo(Integer idPeriodo) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("Delete from periodos where id_periodo=?").setParameter(1, idPeriodo)
                .executeUpdate();
        tx.commit();

    }

    public void addPeriodo(String nomePeriodo, String semestre, Integer idCurso) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        BigInteger id = (BigInteger) em.createNativeQuery("SELECT IF(MAX(ID_PERIODO) IS NULL, 1, MAX(ID_PERIODO) + 1)\n"
                + "FROM periodos").getSingleResult();

        em.createNativeQuery("INSERT INTO periodos(ID_PERIODO, SEMESTRE, ID_CURSO, PERIODO)VALUES(?, ?, ?, ?)")
                .setParameter(1, id)
                .setParameter(2, semestre)
                .setParameter(3, idCurso)
                .setParameter(4, nomePeriodo)
                .executeUpdate();
        em.close();
        tx.commit(); 
    }

    public void updatePeriodo(String nomePeriodo, Integer idPeriodoInteger, String semestre, Integer idCurso) {
        Periodos periodo = new Periodos();
        //periodo.set setNome(nomePeriodo);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("UPDATE periodos\n"
                + "SET\n"
                + "PERIODO = ?,\n"
                + "SEMESTRE = ?,\n"
                + "ID_CURSO = ?\n"
                + "WHERE ID_PERIODO = ?")
                .setParameter(1, nomePeriodo)
                .setParameter(2, semestre)
                .setParameter(3, idCurso)
                .setParameter(4, idPeriodoInteger)
                .executeUpdate();
        em.close();
        tx.commit();
    }
    
    public Integer getIdLastInsert(){
        
        List <Periodos> lista =  listar(null, null);
        Periodos periodo;
        periodo = lista.get(lista.size() - 1);
            return periodo.getIdPeriodo();
    }
    
}