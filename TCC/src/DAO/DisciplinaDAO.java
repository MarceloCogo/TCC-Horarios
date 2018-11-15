/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Disciplinas;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Hp Pavilion
 */
public class DisciplinaDAO {

    //Disciplinas disciplina = new Disciplinas();
    FabricaDeConexao fabrica = new FabricaDeConexao();

    public void atualizar(Disciplinas disciplina) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
        EntityManager em = factory.createEntityManager();

//        String nome = disciplina.getNomeDisciplina().toString();
//        String cargaHorariaSemanal = disciplina.getCargaHorariaSemanal().toString();
//        String cargaHorariaTotal = disciplina.getCargaHorariaTotal().toString();
//        int idDisciplina = disciplina.getIdDisciplina();
         
        
        em.getTransaction().begin();
        em.persist(disciplina);
        em.getTransaction().commit();

//        String sql = "INSERT INTO disciplinas VALUES(" + idDisciplina + " ,'" + nome + "');";
//        fabrica.atualizar(sql);

//        "INSERT INTO Colegiado VALUES ("
//                    + idColegiado + " ,'"
//                    + curso + "' ,'"
//                    + nomeCoordenador + "');");
//        try 
//        {
//            //stmt.executeUpdate(comando); 
//            return true;
//        } 
//        catch (Exception e) 
//        {
//            e.printStackTrace();
//            return false;
//        }
    }

//    public ResultSet consultar(String query) 
//    {   
//        try 
//        {
//            rs=stmt.executeQuery(query);
//        } catch (Exception e) 
//        {
//            e.printStackTrace();
//        }
//        return rs;
//    }
//    public void close()  
//    {
//        try 
//        {
//            stmt.close();
//        } 
//        catch (SQLException sqlEx) 
//        { 
//            stmt = null;
//        }
//        try
//        {
//        	conn.close(); 
//        } 
//        catch (SQLException sqlEx) 
//        { 
//        	conn = null;
//        }   
//    }
}
