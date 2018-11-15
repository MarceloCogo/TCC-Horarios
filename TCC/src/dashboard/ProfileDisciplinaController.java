/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Disciplinas;
import MODEL.Cursos;
import SERVICE.DisciplinasService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author marcelocogo
 */
public class ProfileDisciplinaController implements Initializable {

    @FXML
    private TextField nomeDisciplina;

    @FXML
    private TextField idDisciplina;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private TextField cargaHorariaSemanal;

    @FXML
    private TextField cargaHorariaTotal;

    @FXML
    private TextField nomeCurso;
    
    @FXML
    private ComboBox<Cursos> idCurso;
    
    private IntegerProperty idDisciplinaInteger = new SimpleIntegerProperty();
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    DisciplinasService service = new DisciplinasService();

    public ProfileDisciplinaController(Integer idDisciplina) {
        if (idDisciplina != null) {
            idDisciplinaInteger.set(idDisciplina);
        }else{
            idDisciplinaInteger = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (idDisciplinaInteger != null) {

            Disciplinas disciplina = findDisciplina(idDisciplinaInteger.get());

            idDisciplina.setText(disciplina.getIdDisciplina().toString());
            nomeDisciplina.setText(disciplina.getNomeDisciplina());
                
            
            //idCurso.setText(curso.getIdCurso().toString());
            //nomeCurso.setText(curso.getNomeCurso());
        }

    }

    public Disciplinas findDisciplina(Integer idDisciplina) {

        return service.findDisc(idDisciplina);
    }

    @FXML
    private void switchSaveDisciplina(javafx.event.ActionEvent event) throws IOException {

        if (idDisciplinaInteger != null) {
            //String nomeDisc, Integer idDiscInteger, Integer idCurso
            //service.updateDisciplina( nomeDisciplina.getText(), idCursoInteger.getValue());

        } else {
            service.addDisciplina(nomeDisciplina.getText(), idCurso.getValue().getIdCurso());
            idDisciplina.setText(service.getIdLastInsert().toString()); 
        } 
        refresh();

    }

    private void refresh() {
        service.listar(null);
    }   
    
}
