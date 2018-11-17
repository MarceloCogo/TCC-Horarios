/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Cursos;
import MODEL.DiasSemana;
import MODEL.Periodos;
import MODEL.Professores;
import SERVICE.CursosService;
import SERVICE.PeriodosService;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marcelocogo
 */
public class CadPeriodoController implements Initializable {
    
    
    CursosService cursosService = new CursosService();
    
    private IntegerProperty codPeriodoInteger = new SimpleIntegerProperty();
    
    @FXML
    private TextField descPeriodo;

    @FXML
    private TextField codPeriodo;

    @FXML
    private TextField semestre;
     
    @FXML
    private ComboBox<Cursos> cursosComboBox;
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();
    
    public CadPeriodoController(Integer codPeriodo){
        if (codPeriodo != null) {
            codPeriodoInteger.set(codPeriodo);
        }else{
            codPeriodoInteger = null;
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        List<Cursos> cursos = cursosService.listar();
        cursosComboBox.setItems(FXCollections.observableArrayList(cursos));
        
        StringConverter<Cursos> converter = new StringConverter<Cursos>() {
            @Override
            public String toString(Cursos curso) {
                return curso.getNomeCurso();
            }

            @Override
            public Cursos fromString(String id) {
                return cursos.stream()
                        .filter(item -> item.getNomeCurso().equals(id))
                        .collect(Collectors.toList()).get(0);
            }
        };
        cursosComboBox.setConverter(converter);
        
        if(codPeriodoInteger != null){
            Periodos periodo = findPeriodo(codPeriodoInteger.get());
            codPeriodo.setText(periodo.getIdPeriodo().toString());
            descPeriodo.setText(periodo.getPeriodo());
            semestre.setText(periodo.getSemestre());
            //nomeProfessor.setText(prof.getNomeProfessor());
            cursosComboBox.getSelectionModel().select(periodo.getIdCurso());   
        }
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
    
    PeriodosService service = new PeriodosService();

    
    @FXML
    private void switchSavePeriodo(ActionEvent event) throws IOException {

        if (codPeriodoInteger != null) {
            service.updatePeriodo(descPeriodo.getText(), 
                    codPeriodoInteger.getValue(),
                    semestre.getText(),
                    cursosComboBox.getValue().getIdCurso());

        } else {
            service.addPeriodo(descPeriodo.getText(), semestre.getText(), cursosComboBox.getValue().getIdCurso());
            codPeriodo.setText(service.getIdLastInsert().toString()); 
        }
        refresh();

    }

    private void refresh() {
        service.listar(null, null);
    }

    
}
