/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Cursos;
import MODEL.Salas;
import SERVICE.CursosService;
import SERVICE.SalasService;
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
public class ProfileSalaController implements Initializable {
    
    private IntegerProperty idSalaInteger = new SimpleIntegerProperty();
    
    @FXML
    private TextField nomeSala;

    @FXML
    private TextField idSala;

    @FXML
    private JFXButton btnSalvar;EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    SalasService service = new SalasService();

    public ProfileSalaController(Integer idSala) {
        if (idSala != null) {
            idSalaInteger.set(idSala);
        }else{
            idSalaInteger = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (idSalaInteger != null) {

            Salas sala = findSala(idSalaInteger.get());

            idSala.setText(sala.getIdsala().toString());
            nomeSala.setText(sala.getNome());
        }

    }

    public Salas findSala(Integer idSala) {

        return service.findSala(idSala);
    }

    @FXML
    private void switchSaveSala(ActionEvent event) throws IOException {

        if (idSalaInteger != null) {
            service.updateSala(nomeSala.getText(), idSalaInteger.getValue());

        } else {
            service.addSala(nomeSala.getText());
            idSala.setText(service.getIdLastInsert().toString()); 
        }
        refresh();

    }

    private void refresh() {
        service.listar();
    }
    
}
