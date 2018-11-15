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
import MODEL.Periodos;
import MODEL.DiasSemana;
import java.io.IOException;
import java.util.Date;
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
public class PeriodosController implements Initializable {

     @FXML
    private TableView<Periodos> listPeriodos;

    @FXML
    private TableColumn<Periodos, Integer> idPeriodo;

    @FXML
    private TableColumn<Periodos, String> descPeriodo;

    @FXML
    private TableColumn<Periodos, Date> inicio;

    @FXML
    private TableColumn<Periodos, Date> termino;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnAdicionar;

    AnchorPane cadPeriodo;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        idProfessor.setCellValueFactory(
//                new PropertyValueFactory<>("idProfessor"));
//        nomeProfessor.setCellValueFactory(
//                new PropertyValueFactory<>("nomeProfessor"));
//
//        listProfessores.setItems(listaDeClientes());

    }

    private ObservableList<Periodos> listaDePeriodos() {

        List<Periodos> lista = listar();

        return FXCollections.observableArrayList(
                lista
        );
    }

    public List<Periodos> listar() {
        List<Periodos> periodos;
         periodos = null;
        try {
            periodos = em.createNamedQuery("Periodos.findAll")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodos;
    }

    @FXML
    private void switchEditPeriodo(ActionEvent event) throws IOException {
        //profileProfessor = FXMLLoader.load(getClass().getResource("profileProfessor.fxml"));
        createDatabaseItemModificationStage();
    }

    private void createDatabaseItemModificationStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadPeriodo.fxml"));
            Integer idPeriodo = 1;            
//Integer idPeriodo = listPeriodos.getSelectionModel().getSelectedItem().getIdPeriodo();
            CadPeriodoController controller = new CadPeriodoController(idPeriodo);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Períodos");
            newStage.setMaxHeight(400);
            newStage.setMaxWidth(550);
            newStage.setScene(new Scene(loader.load()));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
        } catch (IOException e) {
            //createErrorStage();
            e.printStackTrace();
        }
    }

}
