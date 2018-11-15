/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import MODEL.Disciplinas;
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
public class DisciplinasService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    public Disciplinas findDisc(Integer idDisc) {
        Disciplinas disc = null;

        try {
            disc = (Disciplinas) em.createNamedQuery("Disciplinas.findByIdDisciplina").setParameter("idDisciplina", idDisc).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disc;
    }

    public List<Disciplinas> listar(Integer idCurso) {

        CursosService curso = new CursosService();
        Cursos cursoCopy = curso.findCurso(idCurso);

        List<Disciplinas> disc = null;
        try {
            em = factory.createEntityManager();

            if (idCurso != null) {

                disc = em.createNativeQuery("select * from disciplinas where CURSOS_ID_CURSO =?",
                        Disciplinas.class).setParameter(1, idCurso).getResultList();

            } else {
                disc = em.createNamedQuery("Disciplinas.findAll").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                        .setHint(QueryHints.REFRESH, HintValues.TRUE)
                        .getResultList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return disc;
    }

    public void deleteDisciplina(Integer idDisc) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("Delete from disciplinas where ID_DISCIPLINA=?").setParameter(1, idDisc)
                .executeUpdate();
        tx.commit();

    }

    public void addDisciplina(String nomeDisciplina, Integer idCurso) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        BigInteger id = (BigInteger) em.createNativeQuery("SELECT IF(MAX(ID_DISCIPLINA) IS NULL, 1, MAX(ID_DISCIPLINA) + 1)\n"
                + "FROM disciplinas").getSingleResult();

        em.createNativeQuery("INSERT INTO disciplinas(ID_DISCIPLINA, NOME_DISCIPLINA, ID_CURSO)VALUES(?, ?, ?)")
                .setParameter(1, id)
                .setParameter(2, nomeDisciplina)
                .setParameter(3, idCurso)
                .executeUpdate();
        em.close();
        tx.commit();
    }

    public void updateDisciplina(String nomeDisc, Integer idDiscInteger, Integer idCurso) {
        Disciplinas disciplina = new Disciplinas();
        disciplina.setIdDisciplina(idDiscInteger);
        disciplina.setNomeDisciplina(nomeDisc);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("UPDATE disciplinas\n"
                + "SET\n"
                + "NOME_DISCIPLINA = ?\n"
                + "WHERE ID_DISCIPLINA = ?")
                .setParameter(1, nomeDisc)
                .setParameter(2, idDiscInteger)
                .executeUpdate();
        em.close();
        tx.commit();
    }
    public Integer getIdLastInsert() {

        List<Disciplinas> lista = listar(null);
        Disciplinas disciplina;
        disciplina = lista.get(lista.size() - 1);
        return disciplina.getIdDisciplina();
    }

}
