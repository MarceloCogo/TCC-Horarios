/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import MODEL.Cursos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.Disciplinas;
import java.util.ArrayList;
import java.util.Collection;
import MODEL.HorariosAulas;
import MODEL.Turmas;
import MODEL.Periodos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcelocogo
 */
public class CursosJpaController implements Serializable {

    public CursosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cursos cursos) throws PreexistingEntityException, Exception {
        if (cursos.getDisciplinasCollection() == null) {
            cursos.setDisciplinasCollection(new ArrayList<Disciplinas>());
        }
        if (cursos.getHorariosAulasCollection() == null) {
            cursos.setHorariosAulasCollection(new ArrayList<HorariosAulas>());
        }
        if (cursos.getTurmasCollection() == null) {
            cursos.setTurmasCollection(new ArrayList<Turmas>());
        }
        if (cursos.getPeriodosCollection() == null) {
            cursos.setPeriodosCollection(new ArrayList<Periodos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Disciplinas> attachedDisciplinasCollection = new ArrayList<Disciplinas>();
            for (Disciplinas disciplinasCollectionDisciplinasToAttach : cursos.getDisciplinasCollection()) {
                disciplinasCollectionDisciplinasToAttach = em.getReference(disciplinasCollectionDisciplinasToAttach.getClass(), disciplinasCollectionDisciplinasToAttach.getIdDisciplina());
                attachedDisciplinasCollection.add(disciplinasCollectionDisciplinasToAttach);
            }
            cursos.setDisciplinasCollection(attachedDisciplinasCollection);
            Collection<HorariosAulas> attachedHorariosAulasCollection = new ArrayList<HorariosAulas>();
            for (HorariosAulas horariosAulasCollectionHorariosAulasToAttach : cursos.getHorariosAulasCollection()) {
                horariosAulasCollectionHorariosAulasToAttach = em.getReference(horariosAulasCollectionHorariosAulasToAttach.getClass(), horariosAulasCollectionHorariosAulasToAttach.getHorariosAulasPK());
                attachedHorariosAulasCollection.add(horariosAulasCollectionHorariosAulasToAttach);
            }
            cursos.setHorariosAulasCollection(attachedHorariosAulasCollection);
            Collection<Turmas> attachedTurmasCollection = new ArrayList<Turmas>();
            for (Turmas turmasCollectionTurmasToAttach : cursos.getTurmasCollection()) {
                turmasCollectionTurmasToAttach = em.getReference(turmasCollectionTurmasToAttach.getClass(), turmasCollectionTurmasToAttach.getIdTurma());
                attachedTurmasCollection.add(turmasCollectionTurmasToAttach);
            }
            cursos.setTurmasCollection(attachedTurmasCollection);
            Collection<Periodos> attachedPeriodosCollection = new ArrayList<Periodos>();
            for (Periodos periodosCollectionPeriodosToAttach : cursos.getPeriodosCollection()) {
                periodosCollectionPeriodosToAttach = em.getReference(periodosCollectionPeriodosToAttach.getClass(), periodosCollectionPeriodosToAttach.getIdPeriodo());
                attachedPeriodosCollection.add(periodosCollectionPeriodosToAttach);
            }
            cursos.setPeriodosCollection(attachedPeriodosCollection);
            em.persist(cursos);
            for (Disciplinas disciplinasCollectionDisciplinas : cursos.getDisciplinasCollection()) {
                Cursos oldCursosIdCursoOfDisciplinasCollectionDisciplinas = disciplinasCollectionDisciplinas.getCursosIdCurso();
                disciplinasCollectionDisciplinas.setCursosIdCurso(cursos);
                disciplinasCollectionDisciplinas = em.merge(disciplinasCollectionDisciplinas);
                if (oldCursosIdCursoOfDisciplinasCollectionDisciplinas != null) {
                    oldCursosIdCursoOfDisciplinasCollectionDisciplinas.getDisciplinasCollection().remove(disciplinasCollectionDisciplinas);
                    oldCursosIdCursoOfDisciplinasCollectionDisciplinas = em.merge(oldCursosIdCursoOfDisciplinasCollectionDisciplinas);
                }
            }
            for (HorariosAulas horariosAulasCollectionHorariosAulas : cursos.getHorariosAulasCollection()) {
                Cursos oldIdCursoOfHorariosAulasCollectionHorariosAulas = horariosAulasCollectionHorariosAulas.getIdCurso();
                horariosAulasCollectionHorariosAulas.setIdCurso(cursos);
                horariosAulasCollectionHorariosAulas = em.merge(horariosAulasCollectionHorariosAulas);
                if (oldIdCursoOfHorariosAulasCollectionHorariosAulas != null) {
                    oldIdCursoOfHorariosAulasCollectionHorariosAulas.getHorariosAulasCollection().remove(horariosAulasCollectionHorariosAulas);
                    oldIdCursoOfHorariosAulasCollectionHorariosAulas = em.merge(oldIdCursoOfHorariosAulasCollectionHorariosAulas);
                }
            }
            for (Turmas turmasCollectionTurmas : cursos.getTurmasCollection()) {
                Cursos oldIdCursoOfTurmasCollectionTurmas = turmasCollectionTurmas.getIdCurso();
                turmasCollectionTurmas.setIdCurso(cursos);
                turmasCollectionTurmas = em.merge(turmasCollectionTurmas);
                if (oldIdCursoOfTurmasCollectionTurmas != null) {
                    oldIdCursoOfTurmasCollectionTurmas.getTurmasCollection().remove(turmasCollectionTurmas);
                    oldIdCursoOfTurmasCollectionTurmas = em.merge(oldIdCursoOfTurmasCollectionTurmas);
                }
            }
            for (Periodos periodosCollectionPeriodos : cursos.getPeriodosCollection()) {
                Cursos oldIdCursoOfPeriodosCollectionPeriodos = periodosCollectionPeriodos.getIdCurso();
                periodosCollectionPeriodos.setIdCurso(cursos);
                periodosCollectionPeriodos = em.merge(periodosCollectionPeriodos);
                if (oldIdCursoOfPeriodosCollectionPeriodos != null) {
                    oldIdCursoOfPeriodosCollectionPeriodos.getPeriodosCollection().remove(periodosCollectionPeriodos);
                    oldIdCursoOfPeriodosCollectionPeriodos = em.merge(oldIdCursoOfPeriodosCollectionPeriodos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCursos(cursos.getIdCurso()) != null) {
                throw new PreexistingEntityException("Cursos " + cursos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cursos cursos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos persistentCursos = em.find(Cursos.class, cursos.getIdCurso());
            Collection<Disciplinas> disciplinasCollectionOld = persistentCursos.getDisciplinasCollection();
            Collection<Disciplinas> disciplinasCollectionNew = cursos.getDisciplinasCollection();
            Collection<HorariosAulas> horariosAulasCollectionOld = persistentCursos.getHorariosAulasCollection();
            Collection<HorariosAulas> horariosAulasCollectionNew = cursos.getHorariosAulasCollection();
            Collection<Turmas> turmasCollectionOld = persistentCursos.getTurmasCollection();
            Collection<Turmas> turmasCollectionNew = cursos.getTurmasCollection();
            Collection<Periodos> periodosCollectionOld = persistentCursos.getPeriodosCollection();
            Collection<Periodos> periodosCollectionNew = cursos.getPeriodosCollection();
            List<String> illegalOrphanMessages = null;
            for (Disciplinas disciplinasCollectionOldDisciplinas : disciplinasCollectionOld) {
                if (!disciplinasCollectionNew.contains(disciplinasCollectionOldDisciplinas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Disciplinas " + disciplinasCollectionOldDisciplinas + " since its cursosIdCurso field is not nullable.");
                }
            }
            for (HorariosAulas horariosAulasCollectionOldHorariosAulas : horariosAulasCollectionOld) {
                if (!horariosAulasCollectionNew.contains(horariosAulasCollectionOldHorariosAulas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HorariosAulas " + horariosAulasCollectionOldHorariosAulas + " since its idCurso field is not nullable.");
                }
            }
            for (Turmas turmasCollectionOldTurmas : turmasCollectionOld) {
                if (!turmasCollectionNew.contains(turmasCollectionOldTurmas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Turmas " + turmasCollectionOldTurmas + " since its idCurso field is not nullable.");
                }
            }
            for (Periodos periodosCollectionOldPeriodos : periodosCollectionOld) {
                if (!periodosCollectionNew.contains(periodosCollectionOldPeriodos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Periodos " + periodosCollectionOldPeriodos + " since its idCurso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Disciplinas> attachedDisciplinasCollectionNew = new ArrayList<Disciplinas>();
            for (Disciplinas disciplinasCollectionNewDisciplinasToAttach : disciplinasCollectionNew) {
                disciplinasCollectionNewDisciplinasToAttach = em.getReference(disciplinasCollectionNewDisciplinasToAttach.getClass(), disciplinasCollectionNewDisciplinasToAttach.getIdDisciplina());
                attachedDisciplinasCollectionNew.add(disciplinasCollectionNewDisciplinasToAttach);
            }
            disciplinasCollectionNew = attachedDisciplinasCollectionNew;
            cursos.setDisciplinasCollection(disciplinasCollectionNew);
            Collection<HorariosAulas> attachedHorariosAulasCollectionNew = new ArrayList<HorariosAulas>();
            for (HorariosAulas horariosAulasCollectionNewHorariosAulasToAttach : horariosAulasCollectionNew) {
                horariosAulasCollectionNewHorariosAulasToAttach = em.getReference(horariosAulasCollectionNewHorariosAulasToAttach.getClass(), horariosAulasCollectionNewHorariosAulasToAttach.getHorariosAulasPK());
                attachedHorariosAulasCollectionNew.add(horariosAulasCollectionNewHorariosAulasToAttach);
            }
            horariosAulasCollectionNew = attachedHorariosAulasCollectionNew;
            cursos.setHorariosAulasCollection(horariosAulasCollectionNew);
            Collection<Turmas> attachedTurmasCollectionNew = new ArrayList<Turmas>();
            for (Turmas turmasCollectionNewTurmasToAttach : turmasCollectionNew) {
                turmasCollectionNewTurmasToAttach = em.getReference(turmasCollectionNewTurmasToAttach.getClass(), turmasCollectionNewTurmasToAttach.getIdTurma());
                attachedTurmasCollectionNew.add(turmasCollectionNewTurmasToAttach);
            }
            turmasCollectionNew = attachedTurmasCollectionNew;
            cursos.setTurmasCollection(turmasCollectionNew);
            Collection<Periodos> attachedPeriodosCollectionNew = new ArrayList<Periodos>();
            for (Periodos periodosCollectionNewPeriodosToAttach : periodosCollectionNew) {
                periodosCollectionNewPeriodosToAttach = em.getReference(periodosCollectionNewPeriodosToAttach.getClass(), periodosCollectionNewPeriodosToAttach.getIdPeriodo());
                attachedPeriodosCollectionNew.add(periodosCollectionNewPeriodosToAttach);
            }
            periodosCollectionNew = attachedPeriodosCollectionNew;
            cursos.setPeriodosCollection(periodosCollectionNew);
            cursos = em.merge(cursos);
            for (Disciplinas disciplinasCollectionNewDisciplinas : disciplinasCollectionNew) {
                if (!disciplinasCollectionOld.contains(disciplinasCollectionNewDisciplinas)) {
                    Cursos oldCursosIdCursoOfDisciplinasCollectionNewDisciplinas = disciplinasCollectionNewDisciplinas.getCursosIdCurso();
                    disciplinasCollectionNewDisciplinas.setCursosIdCurso(cursos);
                    disciplinasCollectionNewDisciplinas = em.merge(disciplinasCollectionNewDisciplinas);
                    if (oldCursosIdCursoOfDisciplinasCollectionNewDisciplinas != null && !oldCursosIdCursoOfDisciplinasCollectionNewDisciplinas.equals(cursos)) {
                        oldCursosIdCursoOfDisciplinasCollectionNewDisciplinas.getDisciplinasCollection().remove(disciplinasCollectionNewDisciplinas);
                        oldCursosIdCursoOfDisciplinasCollectionNewDisciplinas = em.merge(oldCursosIdCursoOfDisciplinasCollectionNewDisciplinas);
                    }
                }
            }
            for (HorariosAulas horariosAulasCollectionNewHorariosAulas : horariosAulasCollectionNew) {
                if (!horariosAulasCollectionOld.contains(horariosAulasCollectionNewHorariosAulas)) {
                    Cursos oldIdCursoOfHorariosAulasCollectionNewHorariosAulas = horariosAulasCollectionNewHorariosAulas.getIdCurso();
                    horariosAulasCollectionNewHorariosAulas.setIdCurso(cursos);
                    horariosAulasCollectionNewHorariosAulas = em.merge(horariosAulasCollectionNewHorariosAulas);
                    if (oldIdCursoOfHorariosAulasCollectionNewHorariosAulas != null && !oldIdCursoOfHorariosAulasCollectionNewHorariosAulas.equals(cursos)) {
                        oldIdCursoOfHorariosAulasCollectionNewHorariosAulas.getHorariosAulasCollection().remove(horariosAulasCollectionNewHorariosAulas);
                        oldIdCursoOfHorariosAulasCollectionNewHorariosAulas = em.merge(oldIdCursoOfHorariosAulasCollectionNewHorariosAulas);
                    }
                }
            }
            for (Turmas turmasCollectionNewTurmas : turmasCollectionNew) {
                if (!turmasCollectionOld.contains(turmasCollectionNewTurmas)) {
                    Cursos oldIdCursoOfTurmasCollectionNewTurmas = turmasCollectionNewTurmas.getIdCurso();
                    turmasCollectionNewTurmas.setIdCurso(cursos);
                    turmasCollectionNewTurmas = em.merge(turmasCollectionNewTurmas);
                    if (oldIdCursoOfTurmasCollectionNewTurmas != null && !oldIdCursoOfTurmasCollectionNewTurmas.equals(cursos)) {
                        oldIdCursoOfTurmasCollectionNewTurmas.getTurmasCollection().remove(turmasCollectionNewTurmas);
                        oldIdCursoOfTurmasCollectionNewTurmas = em.merge(oldIdCursoOfTurmasCollectionNewTurmas);
                    }
                }
            }
            for (Periodos periodosCollectionNewPeriodos : periodosCollectionNew) {
                if (!periodosCollectionOld.contains(periodosCollectionNewPeriodos)) {
                    Cursos oldIdCursoOfPeriodosCollectionNewPeriodos = periodosCollectionNewPeriodos.getIdCurso();
                    periodosCollectionNewPeriodos.setIdCurso(cursos);
                    periodosCollectionNewPeriodos = em.merge(periodosCollectionNewPeriodos);
                    if (oldIdCursoOfPeriodosCollectionNewPeriodos != null && !oldIdCursoOfPeriodosCollectionNewPeriodos.equals(cursos)) {
                        oldIdCursoOfPeriodosCollectionNewPeriodos.getPeriodosCollection().remove(periodosCollectionNewPeriodos);
                        oldIdCursoOfPeriodosCollectionNewPeriodos = em.merge(oldIdCursoOfPeriodosCollectionNewPeriodos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cursos.getIdCurso();
                if (findCursos(id) == null) {
                    throw new NonexistentEntityException("The cursos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos cursos;
            try {
                cursos = em.getReference(Cursos.class, id);
                cursos.getIdCurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Disciplinas> disciplinasCollectionOrphanCheck = cursos.getDisciplinasCollection();
            for (Disciplinas disciplinasCollectionOrphanCheckDisciplinas : disciplinasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cursos (" + cursos + ") cannot be destroyed since the Disciplinas " + disciplinasCollectionOrphanCheckDisciplinas + " in its disciplinasCollection field has a non-nullable cursosIdCurso field.");
            }
            Collection<HorariosAulas> horariosAulasCollectionOrphanCheck = cursos.getHorariosAulasCollection();
            for (HorariosAulas horariosAulasCollectionOrphanCheckHorariosAulas : horariosAulasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cursos (" + cursos + ") cannot be destroyed since the HorariosAulas " + horariosAulasCollectionOrphanCheckHorariosAulas + " in its horariosAulasCollection field has a non-nullable idCurso field.");
            }
            Collection<Turmas> turmasCollectionOrphanCheck = cursos.getTurmasCollection();
            for (Turmas turmasCollectionOrphanCheckTurmas : turmasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cursos (" + cursos + ") cannot be destroyed since the Turmas " + turmasCollectionOrphanCheckTurmas + " in its turmasCollection field has a non-nullable idCurso field.");
            }
            Collection<Periodos> periodosCollectionOrphanCheck = cursos.getPeriodosCollection();
            for (Periodos periodosCollectionOrphanCheckPeriodos : periodosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cursos (" + cursos + ") cannot be destroyed since the Periodos " + periodosCollectionOrphanCheckPeriodos + " in its periodosCollection field has a non-nullable idCurso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cursos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cursos> findCursosEntities() {
        return findCursosEntities(true, -1, -1);
    }

    public List<Cursos> findCursosEntities(int maxResults, int firstResult) {
        return findCursosEntities(false, maxResults, firstResult);
    }

    private List<Cursos> findCursosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cursos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cursos findCursos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cursos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cursos> rt = cq.from(Cursos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
