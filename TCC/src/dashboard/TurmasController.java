/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import MODEL.*;
import MODEL.Periodos;
import MODEL.Turmas;
import SERVICE.*;
import SERVICE.TurmasService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author marcelocogo
 */
public class TurmasController implements Initializable {

    
    @FXML
    private TableView<Turmas> listTurmas;

    @FXML
    private TableColumn<Turmas, Integer> periodo;

    @FXML
    private TableColumn<Turmas, String> semestre;

    @FXML
    private TableColumn<Turmas, String> nomeCurso;
    
    @FXML
    private TableColumn<Turmas, String> nomeNome;

    @FXML
    private TableColumn<Turmas, String> nomeTurno;

    @FXML
    private TextField nome;

    @FXML
    private TextField turno;

    @FXML
    private ComboBox<Cursos> cursosComboBoxModel;

    @FXML
    private ComboBox<Periodos> periodosComboBox;

    @FXML
    private JFXComboBox<Cursos> cursosComboBox;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private TextField semestreFiltro;
    
    @FXML
    private JFXButton btnAdicionar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;
    
    
    //FIM ABA
    
    TurmasService service = new TurmasService();
    
    CursosService cursosService = new CursosService();
    PeriodosService periodosService = new PeriodosService();
    DisciplinasService desciplinasService = new DisciplinasService();
    SalasService salasServie = new SalasService();
    ProfessoresService professoresService = new ProfessoresService();
    
    @FXML
    private ComboBox<Disciplinas> disciplinasComboBox;
    @FXML
    private ComboBox<Professores> professoresComboBox;
    @FXML
    private ComboBox<Salas> salasComboBox;
    @FXML
    private TableView<ProfessorTurmas> listProfTurmas;
    @FXML
    private TableView<?> listDisponibilidade;
    @FXML
    private TableColumn<?, ?> segunda;
    @FXML
    private TableColumn<?, ?> terca;
    @FXML
    private TableColumn<?, ?> quarta;
    @FXML
    private TableColumn<?, ?> quinta;
    @FXML
    private TableColumn<?, ?> sexta;
    @FXML
    private TextField turmaAbaProf;
    @FXML
    private TextField semestreAbaProf;
    @FXML
    private TextField turnoAbaProf;
    @FXML
    private TextField periodoAbaProf;
    @FXML
    private TableColumn<ProfessorTurmas, Integer> codDisciplinaProfTurma;
    @FXML
    private TableColumn<ProfessorTurmas, String> nomeDiscProfTurma;
    @FXML
    private TableColumn<ProfessorTurmas, Integer> codProfTurma;
    @FXML
    private TableColumn<ProfessorTurmas, String> nomeProfTurma;
    @FXML
    private TableColumn<ProfessorTurmas, Integer> horasProfTurma;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        periodo.setCellValueFactory(
                new PropertyValueFactory<>("nomePeriodo"));
        semestre.setCellValueFactory(
                new PropertyValueFactory<>("nomeSemestre"));
        //semestre.setCellValueFactory(
        //        new PropertyValueFactory<>("semestre"));
        nomeCurso.setCellValueFactory(
               new PropertyValueFactory<>("nomeCurso"));
        nomeNome.setCellValueFactory(
               new PropertyValueFactory<>("nome"));
        nomeTurno.setCellValueFactory(
               new PropertyValueFactory<>("turno"));

        listTurmas.setItems(listaTurmas(null, null));
        
        if (listTurmas.getItems().isEmpty()){
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
       }else{
           btnEditar.setDisable(false);
           btnExcluir.setDisable(false);
        }
        
        List<Cursos> cursos = cursosService.listar();
        List<Periodos> periodos = periodosService.listar(null, null);
        List<Disciplinas> disciplinas = desciplinasService.listar(null);
        List<Professores> professores = professoresService.listar();
        List<Salas> salas = salasServie.listar();

        cursosComboBox.setItems(FXCollections.observableArrayList(cursos));
        cursosComboBoxModel.setItems(FXCollections.observableArrayList(cursos));
        periodosComboBox.setItems(FXCollections.observableArrayList(periodos));
        
        disciplinasComboBox.setItems(FXCollections.observableArrayList(disciplinas));
        professoresComboBox.setItems(FXCollections.observableArrayList(professores));
        salasComboBox.setItems(FXCollections.observableArrayList(salas));
                
         StringConverter<Disciplinas> converterDiciplinas = new StringConverter<Disciplinas>() {
            
            @Override
            public String toString(Disciplinas disciplinas) {
                return disciplinas.getNomeDisciplina();
            }

            @Override
            public Disciplinas fromString(String id) {
                return disciplinas.stream()
                        .filter(item -> item.getIdDisciplina().equals(id))
                        .collect(Collectors.toList()).get(0);
            }  
        };
         
         StringConverter<Salas> converterSalas = new StringConverter<Salas>() {
            
            @Override
            public String toString(Salas salas) {
                return salas.getNome();
            }

            @Override
            public Salas fromString(String id) {
                return salas.stream()
                        .filter(item -> item.getIdsala().equals(id))
                        .collect(Collectors.toList()).get(0);
            }  
        };
         
         StringConverter<Professores> converterProfessores = new StringConverter<Professores>() {
            
            @Override
            public String toString(Professores professores) {
                return professores.getNomeProfessor();
            }

            @Override
            public Professores fromString(String id) {
                return professores.stream()
                        .filter(item -> item.getIdProfessor().equals(id))
                        .collect(Collectors.toList()).get(0);
            }  
        };
        
        StringConverter<Periodos> converterPeriodos = new StringConverter<Periodos>() {
            
            @Override
            public String toString(Periodos periodo) {
                return periodo.getPeriodo();
            }

            @Override
            public Periodos fromString(String id) {
                return periodos.stream()
                        .filter(item -> item.getPeriodo().equals(id))
                        .collect(Collectors.toList()).get(0);
            }
            
        };

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
        cursosComboBoxModel.setConverter(converter);
        periodosComboBox.setConverter(converterPeriodos);
        disciplinasComboBox.setConverter(converterDiciplinas);
        professoresComboBox.setConverter(converterProfessores);
        salasComboBox.setConverter(converterSalas);
    }
    
    private ObservableList<Turmas> listaTurmas(Integer idCurso, String semestre) {

        List<Turmas> lista = new ArrayList<>();

        lista = listar(idCurso, semestre);
        
        return FXCollections.observableArrayList(
                lista
        );
    }
    
     public List<Turmas> listar(Integer idCurso, String semestre) {
        List<Turmas> turmas;
         turmas = null;
        try {
            
            turmas = service.listar();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return turmas;
    }
     
     @FXML
    private void switchEditTurma(ActionEvent event) throws IOException {
        //profileProfessor = FXMLLoader.load(getClass().getResource("profileProfessor.fxml"));
        //createDatabaseItemModificationStage();
    }
    
    @FXML
    private void switchNewTurma(ActionEvent event) throws IOException {
        //profileProfessor = FXMLLoader.load(getClass().getResource("profileProfessor.fxml"));
        //createDatabaseItemNewStage();
    }

    @FXML
    private void pesquisar(ActionEvent event) throws IOException {
        Integer idTurmaSelecionado = null;
        String semestreFil;
        if(cursosComboBox.getValue() != null){
           idTurmaSelecionado = cursosComboBox.getValue().getIdCurso();
        }
        
        semestreFil  = semestreFiltro.getText();
        listTurmas.setItems(listaTurmas(idTurmaSelecionado, semestreFil));
    } 
    
    @FXML
    private Tab abaProfessores;

    @FXML
    public void change1(javafx.event.Event event) {
        if (!abaProfessores.isSelected()) {
            
            turmaAbaProf.setText(listTurmas.getSelectionModel().getSelectedItem().getNome());
            semestreAbaProf.setText(listTurmas.getSelectionModel().getSelectedItem().getNomeSemestre());
            turnoAbaProf.setText(listTurmas.getSelectionModel().getSelectedItem().getTurno());
            periodoAbaProf.setText(listTurmas.getSelectionModel().getSelectedItem().getNomePeriodo());
          }
        
        
        codDisciplinaProfTurma.setCellValueFactory(
                new PropertyValueFactory<>("ID_DISCIPLINA"));
        nomeDiscProfTurma.setCellValueFactory(
                new PropertyValueFactory<>("NOME_DISCIPLINA"));
        codProfTurma.setCellValueFactory(
                new PropertyValueFactory<>("ID_PROFESSOR"));
        nomeProfTurma.setCellValueFactory(
                new PropertyValueFactory<>("NOME_PROFESSOR"));
        horasProfTurma.setCellValueFactory(
                new PropertyValueFactory<>("HORAS"));
        
        //fixo para teste
        listaProfTurmas(1);
        
    }
    
        
    public void listaProfTurmas(Integer idTurma){
        
        listProfTurmas.setItems(listarProfTurmas(idTurma));    
    }
    
    public ObservableList<ProfessorTurmas> listarProfTurmas(Integer idTurma){
        List<ProfessorTurmas> lista = new ArrayList<>();

        lista = service.listarProfessoresTurmas(idTurma);
        
        return FXCollections.observableArrayList(
                lista
        );
    }
    
}
