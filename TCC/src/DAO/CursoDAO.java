/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Cursos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Hp Pavilion
 */
public class CursoDAO {
     public void atualizar(Cursos curso) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
        EntityManager em = factory.createEntityManager();       
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
    }    
}
