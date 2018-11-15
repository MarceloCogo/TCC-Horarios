/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Cursos;
import SERVICE.CursosService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author marcelocogo
 */
public class ProfileCursoController implements Initializable {
    
    private IntegerProperty idCursoInteger = new SimpleIntegerProperty();
    
    @FXML
    private TextField nomeCurso;

    @FXML
    private TextField idCurso;

    @FXML
    private JFXButton btnSalvar;EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    CursosService service = new CursosService();

    public ProfileCursoController(Integer idCurso) {
        if (idCurso != null) {
            idCursoInteger.set(idCurso);
        }else{
            idCursoInteger = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (idCursoInteger != null) {

            Cursos curso = findCurso(idCursoInteger.get());

            idCurso.setText(curso.getIdCurso().toString());
            nomeCurso.setText(curso.getNomeCurso());
        }

    }

    public Cursos findCurso(Integer idCurso) {

        return service.findCurso(idCurso);
    }

    @FXML
    private void switchSaveCurso(ActionEvent event) throws IOException {

        if (idCursoInteger != null) {
            service.updateCurso(nomeCurso.getText(), idCursoInteger.getValue());

        } else {
            service.addCurso(nomeCurso.getText());
            idCurso.setText(service.getIdLastInsert().toString()); 
        }
        refresh();

    }

    private void refresh() {
        service.listar();
    }
    
}
