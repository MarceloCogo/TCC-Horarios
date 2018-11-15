/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Cursos;
import SERVICE.CursosService;
import com.jfoenix.controls.JFXButton;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
public class CursosController implements Initializable {
    
    
    @FXML
    private TableView<Cursos> listCursos;

    @FXML
    private TableColumn<Cursos, Integer> idCurso;

    @FXML
    private TableColumn<Cursos, String> nomeCurso;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnAdicionar;
    
    
    CursosService service = new CursosService();

    AnchorPane profileCurso;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();
    
    
    @FXML
    void deleteCurso(ActionEvent event) {

    }

    @FXML
    void switchEditCurso(ActionEvent event) {

    }

    @FXML
    void switchNewCurso(ActionEvent event) {

    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCurso.setCellValueFactory(
                new PropertyValueFactory<>("idCurso"));
        nomeCurso.setCellValueFactory(
                new PropertyValueFactory<>("nomeCurso"));
        
        //getCursos();

        listCursos.setItems(listaCursos());
        
       if (listCursos.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
           
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }
    } 
    
    
    private void refresh() {
        
        listCursos.getItems().clear();
        listCursos.getColumns().get(0).setVisible(false);
        listCursos.getColumns().get(0).setVisible(true);
        listCursos.getItems().addAll(listaCursos());
        listCursos.setItems(listaCursos());
        listCursos.refresh();
        

        //System.out.println(lista);
        
        if (listCursos.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }

        

    }

    private ObservableList<Cursos> listaCursos() {

        List<Cursos> lista = new ArrayList<>();

        lista = listar();
        
        return FXCollections.observableArrayList(
                lista
        );
    }

    public List<Cursos> listar() {

        return service.listar();
    } 

    @FXML
    private void switchEditCurso(javafx.event.ActionEvent event) throws IOException {
        createDatabaseItemModificationStage();
    }

    @FXML
    private void switchNewCurso(javafx.event.ActionEvent event) throws IOException {
        createDatabaseItemNewStage();
    }

    public void deleteCurso() {

        Integer idCurso = listCursos.getSelectionModel().getSelectedItem().getIdCurso();
        service.deleteCurso(idCurso);

        refresh();

    }

    private void createDatabaseItemModificationStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileCurso.fxml"));
            Integer idCurso = listCursos.getSelectionModel().getSelectedItem().getIdCurso();
            ProfileCursoController controller = new ProfileCursoController(idCurso);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("cadCurso");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileCurso.fxml"));
            ProfileCursoController controller = new ProfileCursoController(null);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Cursos");
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
