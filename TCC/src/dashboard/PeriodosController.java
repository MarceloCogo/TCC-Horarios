/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Cursos;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import MODEL.Periodos;
import MODEL.DiasSemana;
import SERVICE.CursosService;
import SERVICE.PeriodosService;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
    private TableColumn<Periodos, String> periodo;

    @FXML
    private TableColumn<Periodos, String> semestre;

    @FXML
    private TableColumn<Periodos, String> nomeCurso;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnAdicionar;
    
    @FXML
    private JFXComboBox<Cursos> cursosComboBox;
     
    @FXML
    private TextField semestreFiltro; 

    AnchorPane cadPeriodo;
    
    CursosService cursosService = new CursosService();
    PeriodosService service = new PeriodosService();

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idPeriodo.setCellValueFactory(
                new PropertyValueFactory<>("idPeriodo"));
        periodo.setCellValueFactory(
                new PropertyValueFactory<>("periodo"));
        semestre.setCellValueFactory(
                new PropertyValueFactory<>("semestre"));
        nomeCurso.setCellValueFactory(
                new PropertyValueFactory<>("nomeCurso"));

        listPeriodos.setItems(listaPeriodos(null, null));
        
        if (listPeriodos.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
           
            btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }
        
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

    }
    
    private ObservableList<Periodos> listaPeriodos(Integer idCurso, String semestre) {

        List<Periodos> lista = new ArrayList<>();

        lista = listar(idCurso, semestre);
        
        return FXCollections.observableArrayList(
                lista
        );
    }
    
    @FXML
    private void pesquisar(ActionEvent event) throws IOException {
        Integer idPeriodoSelecionado = null;
        String semestreFil;
        if(cursosComboBox.getValue() != null){
           idPeriodoSelecionado = cursosComboBox.getValue().getIdCurso();
        }
        
        semestreFil  = semestreFiltro.getText();
        listPeriodos.setItems(listaPeriodos(idPeriodoSelecionado, semestreFil));
    }


    public List<Periodos> listar(Integer idCurso, String semestre) {
        List<Periodos> periodos;
         periodos = null;
        try {
            
            periodos = service.listar(idCurso, semestre);
            
            //periodos = em.createNamedQuery("Periodos.findAll")
            //        .getResultList();
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
    
    @FXML
    private void switchNewPeriodo(ActionEvent event) throws IOException {
        //profileProfessor = FXMLLoader.load(getClass().getResource("profileProfessor.fxml"));
        createDatabaseItemNewStage();
    }
    
    

    private void createDatabaseItemModificationStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadPeriodo.fxml"));
            //Integer idPeriodo = 1;            
            Integer idPeriodo = listPeriodos.getSelectionModel().getSelectedItem().getIdPeriodo();
            CadPeriodoController controller = new CadPeriodoController(idPeriodo);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Períodos");
            //newStage.setMaxHeight(400);
            //newStage.setMaxWidth(550);
            newStage.setScene(new Scene(loader.load()));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();
        } catch (IOException e) {
            //createErrorStage();
            e.printStackTrace();
        }
    }
    
    private void createDatabaseItemNewStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadPeriodo.fxml"));
            CadPeriodoController controller = new CadPeriodoController(null);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Períodos");
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
    
    private void refresh() {
        
        //listSalas.getItems().clear();
        //listSalas.getColumns().get(0).setVisible(false);
        //listSalas.getColumns().get(0).setVisible(true);
        //listSalas.getItems().addAll(listaSalas());
        //listSalas.setItems(listaSalas());
        //listSalas.refresh();
        

        //System.out.println(lista);
        
        //if (listSalas.getItems().isEmpty()){
        //   btnEditar.setDisable(true);
        //   btnExcluir.setDisable(true);
       //}else{
       //     btnEditar.setDisable(false);
       //    btnExcluir.setDisable(false);
       // }

        

    }

}
