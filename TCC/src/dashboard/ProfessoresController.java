/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import MODEL.Professores;
import MODEL.DiasSemana;
import SERVICE.ProfessoresService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marcelocogo
 */
public class ProfessoresController implements Initializable {

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnAdicionar;
    @FXML
    private TableView<Professores> listProfessores;

    @FXML
    private TableColumn<Professores, Integer> idProfessor;

    @FXML
    private TableColumn<Professores, String> nomeProfessor;

    ProfessoresService service = new ProfessoresService();

    AnchorPane profileProfessor;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idProfessor.setCellValueFactory(
                new PropertyValueFactory<>("idProfessor"));
        nomeProfessor.setCellValueFactory(
                new PropertyValueFactory<>("nomeProfessor"));

        listProfessores.setItems(listaProfessores());
        
       if (listProfessores.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
           
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }

    }

    private void refresh() {
        
        listProfessores.getItems().clear();
        listProfessores.getColumns().get(0).setVisible(false);
        listProfessores.getColumns().get(0).setVisible(true);
        listProfessores.getItems().addAll(listaProfessores());
        listProfessores.setItems(listaProfessores());
        listProfessores.refresh();
        

        //System.out.println(lista);
        
        if (listProfessores.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }

        

    }

    private ObservableList<Professores> listaProfessores() {

        List<Professores> lista = new ArrayList<>();

        lista = listar();
        
        return FXCollections.observableArrayList(
                lista
        );
    }

    public List<Professores> listar() {

        return service.listar();
    }

    @FXML
    private void switchEditProf(ActionEvent event) throws IOException {
        //profileProfessor = FXMLLoader.load(getClass().getResource("profileProfessor.fxml"));
        createDatabaseItemModificationStage();
    }

    @FXML
    private void switchNewProf(ActionEvent event) throws IOException {
        //profileProfessor = FXMLLoader.load(getClass().getResource("profileProfessor.fxml"));
        createDatabaseItemNewStage();
    }

    public void deleteProfessor() {

        Integer idProf = listProfessores.getSelectionModel().getSelectedItem().getIdProfessor();
        service.deleteProfessor(idProf);

        refresh();

    }

    private void createDatabaseItemModificationStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileProfessor.fxml"));
            Integer idProf = listProfessores.getSelectionModel().getSelectedItem().getIdProfessor();
            ProfileProfessorController controller = new ProfileProfessorController(idProf);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Professores");
            newStage.setScene(new Scene(loader.load()));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();

            newStage.setOnCloseRequest((event) -> {

                refresh();

            });
        } catch (IOException e) {
            //createErrorStage();
            e.printStackTrace();
        }
    }

    private void createDatabaseItemNewStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileProfessor.fxml"));
            ProfileProfessorController controller = new ProfileProfessorController(null);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Professores");
            newStage.setScene(new Scene(loader.load()));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
            
            newStage.setOnCloseRequest((event) -> {
                refresh();
            });
        } catch (IOException e) {
            //createErrorStage();
            e.printStackTrace();
        }
    }

}
