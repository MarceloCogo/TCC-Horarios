/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.*;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class TurmasService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    public List<Turmas> findTurma(Integer idTurma) {
        List<Turmas> turmas = null;

        try {
            turmas = (List<Turmas>) em.createNamedQuery("Turmas.findByTurma").setParameter("idTurma", idTurma).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return turmas;
    }

    public List<Turmas> listar() {
        List<Turmas> turma = null;
        try {

            em = factory.createEntityManager();

            turma = em.createNamedQuery("Turmas.findAll").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return turma;
    }

    public void deleteTurma(Integer idTurma) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("Delete from turmas where ID_TURMA=?").setParameter(1, idTurma)
                .executeUpdate();
        tx.commit();

    }

    public void addTurma(String nomeTurma) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        BigInteger id = (BigInteger) em.createNativeQuery("SELECT IF(MAX(ID_TURMA) IS NULL, 1, MAX(ID_TURMA) + 1)\n"
                + "FROM turmas").getSingleResult();

        em.createNativeQuery("INSERT INTO turmas(ID_TURMA, NOME_TURMA)VALUES(?, ?)")
                .setParameter(1, id)
                .setParameter(2, nomeTurma)
                .executeUpdate();
        em.close();
        tx.commit();
    }

    public void updateTurma(String nomeTurma, Integer idTurmaInteger) {
        Turmas turma = new Turmas();
        turma.setIdTurma(idTurmaInteger);
        turma.setNome(nomeTurma);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("UPDATE turmas\n"
                + "SET\n"
                + "NOME_TURMA = ?\n"
                + "WHERE ID_TURMA = ?")
                .setParameter(1, nomeTurma)
                .setParameter(2, idTurmaInteger)
                .executeUpdate();
        em.close();
        tx.commit();
    }

    public Integer getIdLastInsert() {

        List<Turmas> lista = listar();
        Turmas turma;
        turma = lista.get(lista.size() - 1);
        return turma.getIdTurma();
    }

    public List<ProfessorTurmas> listarProfessoresTurmas(Integer idTurma) {

        List<Object[]> profTurmas = null;
        List<ProfessorTurmas> listaProfTurmasQuery = new ArrayList<>();
        try {

            em = factory.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            profTurmas = em.createNativeQuery("SELECT p.ID_PROFESSOR, p.NOME_PROFESSOR, d.ID_DISCIPLINA, d.NOME_DISCIPLINA, horas FROM tcc.professor_turmas as pt\n"
                    + "join professores as p\n"
                    + "on p.ID_PROFESSOR = pt.ID_PROFESSOR\n"
                    + "join disciplinas as d\n"
                    + "on d.ID_DISCIPLINA = pt.ID_DISCIPLINA where ID_TURMA =?\n")
                    .setParameter(1, idTurma)
                    .getResultList();

           
            if (profTurmas != null) {

                for (Object[] objectArray : profTurmas) {

                    ProfessorTurmas profTurmasee = new ProfessorTurmas((Integer) objectArray[0], (Integer) objectArray[2], (Integer) objectArray[4], (String) objectArray[1], (String) objectArray[3]);

                    listaProfTurmasQuery.add(profTurmasee);
                }
            }

            return listaProfTurmasQuery;

            //tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaProfTurmasQuery;
    }

}
