/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MODEL.Cursos;
import MODEL.Disciplinas;
import MODEL.HorariosAulas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcelocogo
 */
public class DisciplinasJpaController implements Serializable {

    public DisciplinasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Disciplinas disciplinas) throws PreexistingEntityException, Exception {
        if (disciplinas.getHorariosAulasCollection() == null) {
            disciplinas.setHorariosAulasCollection(new ArrayList<HorariosAulas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursos cursosIdCurso = disciplinas.getCursosIdCurso();
            if (cursosIdCurso != null) {
                cursosIdCurso = em.getReference(cursosIdCurso.getClass(), cursosIdCurso.getIdCurso());
                disciplinas.setCursosIdCurso(cursosIdCurso);
            }
            Collection<HorariosAulas> attachedHorariosAulasCollection = new ArrayList<HorariosAulas>();
            for (HorariosAulas horariosAulasCollectionHorariosAulasToAttach : disciplinas.getHorariosAulasCollection()) {
                horariosAulasCollectionHorariosAulasToAttach = em.getReference(horariosAulasCollectionHorariosAulasToAttach.getClass(), horariosAulasCollectionHorariosAulasToAttach.getHorariosAulasPK());
                attachedHorariosAulasCollection.add(horariosAulasCollectionHorariosAulasToAttach);
            }
            disciplinas.setHorariosAulasCollection(attachedHorariosAulasCollection);
            em.persist(disciplinas);
            if (cursosIdCurso != null) {
                cursosIdCurso.getDisciplinasCollection().add(disciplinas);
                cursosIdCurso = em.merge(cursosIdCurso);
            }
            for (HorariosAulas horariosAulasCollectionHorariosAulas : disciplinas.getHorariosAulasCollection()) {
                Disciplinas oldIdDisciplinaOfHorariosAulasCollectionHorariosAulas = horariosAulasCollectionHorariosAulas.getIdDisciplina();
                horariosAulasCollectionHorariosAulas.setIdDisciplina(disciplinas);
                horariosAulasCollectionHorariosAulas = em.merge(horariosAulasCollectionHorariosAulas);
                if (oldIdDisciplinaOfHorariosAulasCollectionHorariosAulas != null) {
                    oldIdDisciplinaOfHorariosAulasCollectionHorariosAulas.getHorariosAulasCollection().remove(horariosAulasCollectionHorariosAulas);
                    oldIdDisciplinaOfHorariosAulasCollectionHorariosAulas = em.merge(oldIdDisciplinaOfHorariosAulasCollectionHorariosAulas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDisciplinas(disciplinas.getIdDisciplina()) != null) {
                throw new PreexistingEntityException("Disciplinas " + disciplinas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Disciplinas disciplinas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Disciplinas persistentDisciplinas = em.find(Disciplinas.class, disciplinas.getIdDisciplina());
            Cursos cursosIdCursoOld = persistentDisciplinas.getCursosIdCurso();
            Cursos cursosIdCursoNew = disciplinas.getCursosIdCurso();
            Collection<HorariosAulas> horariosAulasCollectionOld = persistentDisciplinas.getHorariosAulasCollection();
            Collection<HorariosAulas> horariosAulasCollectionNew = disciplinas.getHorariosAulasCollection();
            List<String> illegalOrphanMessages = null;
            for (HorariosAulas horariosAulasCollectionOldHorariosAulas : horariosAulasCollectionOld) {
                if (!horariosAulasCollectionNew.contains(horariosAulasCollectionOldHorariosAulas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HorariosAulas " + horariosAulasCollectionOldHorariosAulas + " since its idDisciplina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cursosIdCursoNew != null) {
                cursosIdCursoNew = em.getReference(cursosIdCursoNew.getClass(), cursosIdCursoNew.getIdCurso());
                disciplinas.setCursosIdCurso(cursosIdCursoNew);
            }
            Collection<HorariosAulas> attachedHorariosAulasCollectionNew = new ArrayList<HorariosAulas>();
            for (HorariosAulas horariosAulasCollectionNewHorariosAulasToAttach : horariosAulasCollectionNew) {
                horariosAulasCollectionNewHorariosAulasToAttach = em.getReference(horariosAulasCollectionNewHorariosAulasToAttach.getClass(), horariosAulasCollectionNewHorariosAulasToAttach.getHorariosAulasPK());
                attachedHorariosAulasCollectionNew.add(horariosAulasCollectionNewHorariosAulasToAttach);
            }
            horariosAulasCollectionNew = attachedHorariosAulasCollectionNew;
            disciplinas.setHorariosAulasCollection(horariosAulasCollectionNew);
            disciplinas = em.merge(disciplinas);
            if (cursosIdCursoOld != null && !cursosIdCursoOld.equals(cursosIdCursoNew)) {
                cursosIdCursoOld.getDisciplinasCollection().remove(disciplinas);
                cursosIdCursoOld = em.merge(cursosIdCursoOld);
            }
            if (cursosIdCursoNew != null && !cursosIdCursoNew.equals(cursosIdCursoOld)) {
                cursosIdCursoNew.getDisciplinasCollection().add(disciplinas);
                cursosIdCursoNew = em.merge(cursosIdCursoNew);
            }
            for (HorariosAulas horariosAulasCollectionNewHorariosAulas : horariosAulasCollectionNew) {
                if (!horariosAulasCollectionOld.contains(horariosAulasCollectionNewHorariosAulas)) {
                    Disciplinas oldIdDisciplinaOfHorariosAulasCollectionNewHorariosAulas = horariosAulasCollectionNewHorariosAulas.getIdDisciplina();
                    horariosAulasCollectionNewHorariosAulas.setIdDisciplina(disciplinas);
                    horariosAulasCollectionNewHorariosAulas = em.merge(horariosAulasCollectionNewHorariosAulas);
                    if (oldIdDisciplinaOfHorariosAulasCollectionNewHorariosAulas != null && !oldIdDisciplinaOfHorariosAulasCollectionNewHorariosAulas.equals(disciplinas)) {
                        oldIdDisciplinaOfHorariosAulasCollectionNewHorariosAulas.getHorariosAulasCollection().remove(horariosAulasCollectionNewHorariosAulas);
                        oldIdDisciplinaOfHorariosAulasCollectionNewHorariosAulas = em.merge(oldIdDisciplinaOfHorariosAulasCollectionNewHorariosAulas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = disciplinas.getIdDisciplina();
                if (findDisciplinas(id) == null) {
                    throw new NonexistentEntityException("The disciplinas with id " + id + " no longer exists.");
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
            Disciplinas disciplinas;
            try {
                disciplinas = em.getReference(Disciplinas.class, id);
                disciplinas.getIdDisciplina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplinas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<HorariosAulas> horariosAulasCollectionOrphanCheck = disciplinas.getHorariosAulasCollection();
            for (HorariosAulas horariosAulasCollectionOrphanCheckHorariosAulas : horariosAulasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Disciplinas (" + disciplinas + ") cannot be destroyed since the HorariosAulas " + horariosAulasCollectionOrphanCheckHorariosAulas + " in its horariosAulasCollection field has a non-nullable idDisciplina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cursos cursosIdCurso = disciplinas.getCursosIdCurso();
            if (cursosIdCurso != null) {
                cursosIdCurso.getDisciplinasCollection().remove(disciplinas);
                cursosIdCurso = em.merge(cursosIdCurso);
            }
            em.remove(disciplinas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Disciplinas> findDisciplinasEntities() {
        return findDisciplinasEntities(true, -1, -1);
    }

    public List<Disciplinas> findDisciplinasEntities(int maxResults, int firstResult) {
        return findDisciplinasEntities(false, maxResults, firstResult);
    }

    private List<Disciplinas> findDisciplinasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Disciplinas.class));
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

    public Disciplinas findDisciplinas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disciplinas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disciplinas> rt = cq.from(Disciplinas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
