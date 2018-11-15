/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import MODEL.Cursos;
import MODEL.Disciplinas;
import SERVICE.CursosService;
import SERVICE.DisciplinasService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author marcelocogo
 */
public class DisciplinasController implements Initializable {

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnAdicionar;
    @FXML
    private TableView<Disciplinas> listDisciplinas;

    @FXML
    private TableColumn<Disciplinas, Integer> idDisciplina;

    @FXML
    private TableColumn<Disciplinas, String> nomeDisciplina;

    @FXML
    private JFXComboBox<Cursos> cursosComboBox;

    DisciplinasService service = new DisciplinasService();
    CursosService cursosService = new CursosService();

    AnchorPane profileDisciplina;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("tcc");
    EntityManager em = factory.createEntityManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idDisciplina.setCellValueFactory(
                new PropertyValueFactory<>("idDisciplina"));
        nomeDisciplina.setCellValueFactory(
                new PropertyValueFactory<>("nomeDisciplina"));

        //getCursos();
        listDisciplinas.setItems(listaDisciplinas(null));

        if (listDisciplinas.getItems().isEmpty()) {
            btnEditar.setDisable(true);
            btnExcluir.setDisable(true);
        } else {

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
    
    
    @FXML
    private void pesquisar(ActionEvent event) throws IOException {
        Integer idCursoSelecionado = cursosComboBox.getValue().getIdCurso();
        listDisciplinas.setItems(listaDisciplinas(idCursoSelecionado));
    }

    //private void get cursos(){
    //}
    private void refresh() {

        listDisciplinas.getItems().clear();
        listDisciplinas.getColumns().get(0).setVisible(false);
        listDisciplinas.getColumns().get(0).setVisible(true);
        listDisciplinas.getItems().addAll(listaDisciplinas(cursosComboBox.getValue().getIdCurso()));
        listDisciplinas.setItems(listaDisciplinas(cursosComboBox.getValue().getIdCurso()));
        listDisciplinas.refresh();

        //System.out.println(lista);
        if (listDisciplinas.getItems().isEmpty()) {
            btnEditar.setDisable(true);
            btnExcluir.setDisable(true);
        } else {
            btnEditar.setDisable(false);
            btnExcluir.setDisable(false);
        }

    }

    private ObservableList<Disciplinas> listaDisciplinas(Integer idCurso) {

        List<Disciplinas> lista = new ArrayList<>();

        lista = listar(idCurso);

        return FXCollections.observableArrayList(
                lista
        );
    }

    public List<Disciplinas> listar(Integer idCurso) {

        return service.listar(idCurso);
    }

    @FXML
    private void switchEditDisc(ActionEvent event) throws IOException {
        createDatabaseItemModificationStage();
    }

    @FXML
    private void switchNewDis(ActionEvent event) throws IOException {
        createDatabaseItemNewStage();
    }

    public void deleteDisciplina() {

        Integer idDisc = listDisciplinas.getSelectionModel().getSelectedItem().getIdDisciplina();
        service.deleteDisciplina(idDisc);

        refresh();

    }

    private void createDatabaseItemModificationStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDisciplina.fxml"));
            Integer idDisc = listDisciplinas.getSelectionModel().getSelectedItem().getIdDisciplina();
            ProfileDisciplinaController controller = new ProfileDisciplinaController(idDisc);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Disciplinas"
                    + "");
            newStage.setScene(new Scene(loader.load()));
            newStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDisciplina.fxml"));
            ProfileDisciplinaController controller = new ProfileDisciplinaController(null);
            loader.setController(controller);
            Stage newStage = new Stage();
            newStage.setTitle("Cadastro de Disciplinas");
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
