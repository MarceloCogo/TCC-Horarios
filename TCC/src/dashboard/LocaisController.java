/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Salas;
import SERVICE.SalasService;
import com.jfoenix.controls.JFXButton;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author marcelocogo
 */
public class LocaisController implements Initializable {
    
    
    @FXML
    private TableView<Salas> listSalas;

    @FXML
    private TableColumn<Salas, Integer> idSala;

    @FXML
    private TableColumn<Salas, String> nome;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnAdicionar;
    
    
    SalasService service = new SalasService();

    AnchorPane profileSala;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();
    
    
    @FXML
    void deleteSala(ActionEvent event) {

    }

    @FXML
    void switchEditSala(ActionEvent event) {

    }

    @FXML
    void switchNewSala(ActionEvent event) {

    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idSala.setCellValueFactory(
                new PropertyValueFactory<>("idsala"));
        nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        
        //getCursos();

        listSalas.setItems(listaSalas());
        
       if (listSalas.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
           
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }
    } 
    
    
    private void refresh() {
        
        listSalas.getItems().clear();
        listSalas.getColumns().get(0).setVisible(false);
        listSalas.getColumns().get(0).setVisible(true);
        listSalas.getItems().addAll(listaSalas());
        listSalas.setItems(listaSalas());
        listSalas.refresh();
        

        //System.out.println(lista);
        
        if (listSalas.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }

        

    }

    private ObservableList<Salas> listaSalas() {

        List<Salas> lista = new ArrayList<>();

        lista = listar();
        
        return FXCollections.observableArrayList(
                lista
        );
    }

    public List<Salas> listar() {

        return service.listar();
    }
    
    

    @FXML
    private void switchVisualizarAulas(javafx.event.ActionEvent event) throws IOException {
     //Application. launch(JavaFXCSVTableView.class);
     String[] arguments = new String[] {""};
     JavaFXCSVTableView.main(arguments);
    }
    
    @FXML
    private void switchEditSala(javafx.event.ActionEvent event) throws IOException {
        createDatabaseItemModificationStage();
    }

    @FXML
    private void switchNewSala(javafx.event.ActionEvent event) throws IOException {
        createDatabaseItemNewStage();
    }

    public void deleteSala() {

        Integer idSala = listSalas.getSelectionModel().getSelectedItem().getIdsala();
        service.deleteSala(idSala);

        refresh();

    }

    private void createDatabaseItemModificationStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileSala.fxml"));
            Integer idSala = listSalas.getSelectionModel().getSelectedItem().getIdsala();
            ProfileSalaController controller = new ProfileSalaController(idSala);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("cadSala");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileSala.fxml"));
            ProfileSalaController controller = new ProfileSalaController(null);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Salas");
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
