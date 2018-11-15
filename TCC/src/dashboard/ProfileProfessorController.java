/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.DiasSemana;
import MODEL.Professores;
import SERVICE.ProfessoresService;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marcelocogo
 */
public class ProfileProfessorController implements Initializable {

    private IntegerProperty idProfInteger = new SimpleIntegerProperty();

    @FXML
    TextField idProfessor;

    @FXML
    TextField nomeProfessor;

    @FXML
    private TableView<DiasSemana> listDisponibilidade;

    @FXML
    private TableColumn<DiasSemana, String> segunda;
    @FXML
    private TableColumn<DiasSemana, String> terca;
    @FXML
    private TableColumn<DiasSemana, String> quarta;
    @FXML
    private TableColumn<DiasSemana, String> quinta;
    @FXML
    private TableColumn<DiasSemana, String> sexta;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    ProfessoresService service = new ProfessoresService();

    public ProfileProfessorController(Integer idProf) {
        if (idProf != null) {
            idProfInteger.set(idProf);
        }else{
            idProfInteger = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (idProfInteger != null) {

            DiasSemana dias = new DiasSemana(" ", " ", " ", "Teste", " ");
            DiasSemana dias2 = new DiasSemana(" ", " ", " ", "Teste", " ");
            DiasSemana dias3 = new DiasSemana(" ", " ", "", "", "teste");
            System.out.println(FXCollections.observableArrayList(dias));

            segunda.setCellValueFactory(
                    new PropertyValueFactory<>("segunda"));
            terca.setCellValueFactory(
                    new PropertyValueFactory<>("terca"));
            quarta.setCellValueFactory(
                    new PropertyValueFactory<>("quarta"));
            quinta.setCellValueFactory(
                    new PropertyValueFactory<>("quinta"));
            sexta.setCellValueFactory(
                    new PropertyValueFactory<>("sexta"));

            listDisponibilidade.setItems(FXCollections.observableArrayList(dias, dias2, dias3));
            
            

            Professores prof = findProf(idProfInteger.get());

            idProfessor.setText(prof.getIdProfessor().toString());
            nomeProfessor.setText(prof.getNomeProfessor());
        }

    }

    public Professores findProf(Integer idProf) {

        return service.findProf(idProf);
    }

    @FXML
    private void switchSaveProf(ActionEvent event) throws IOException {

        if (idProfInteger != null) {
            service.updateProfessor(nomeProfessor.getText(), idProfInteger.getValue());

        } else {
            service.addProfessor(nomeProfessor.getText());
            idProfessor.setText(service.getIdLastInsert().toString()); 
        }
        refresh();

    }

    private void refresh() {
        service.listar();
    }

}
