/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.DiasSemana;
import MODEL.Periodos;
import MODEL.Professores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marcelocogo
 */
public class CadPeriodoController implements Initializable {
    
    private IntegerProperty codPeriodoInteger = new SimpleIntegerProperty();
    
    @FXML
    private TextField descPeriodo;

    @FXML
    private TextField codPeriodo;

    @FXML
    private TextField terminoPeriodo;

    @FXML
    private TextField inicioPeriodo;
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();
    
    public CadPeriodoController(Integer codPeriodo){
        codPeriodoInteger.set(codPeriodo);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        
        
        Periodos periodo = findPeriodo(codPeriodoInteger.get());
        //codPeriodo.setText(periodo.get ().toString());
        //nomeProfessor.setText(prof.getNomeProfessor());
    }
    
    public Periodos findPeriodo(Integer codPeriodo) {
        Periodos periodo = null;
        try {
            periodo = (Periodos) em.createNamedQuery("Periodos.findByIdPeriodo").setParameter("idPeriodo", codPeriodo).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodo;
    }

    
}
