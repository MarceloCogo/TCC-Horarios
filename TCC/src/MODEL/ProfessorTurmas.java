/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;

/**
 *
 * @author marcelocogo
 */
public class ProfessorTurmas implements Serializable{
    
    
    Integer ID_PROFESSOR;
    
    Integer ID_DISCIPLINA;
    
    Integer HORAS;
    
    String NOME_PROFESSOR;
    
    String NOME_DISCIPLINA;

    public Integer getID_PROFESSOR() {
        return ID_PROFESSOR;
    }

    public void setID_PROFESSOR(Integer ID_PROFESSOR) {
        this.ID_PROFESSOR = ID_PROFESSOR;
    }

    public Integer getID_DISCIPLINA() {
        return ID_DISCIPLINA;
    }

    public void setID_DISCIPLINA(Integer ID_DISCIPLINA) {
        this.ID_DISCIPLINA = ID_DISCIPLINA;
    }

    public Integer getHORAS() {
        return HORAS;
    }

    public void setHORAS(Integer HORAS) {
        this.HORAS = HORAS;
    }

    public String getNOME_PROFESSOR() {
        return NOME_PROFESSOR;
    }

    public void setNOME_PROFESSOR(String NOME_PROFESSOR) {
        this.NOME_PROFESSOR = NOME_PROFESSOR;
    }

    public String getNOME_DISCIPLINA() {
        return NOME_DISCIPLINA;
    }

    public void setNOME_DISCIPLINA(String NOME_DISCIPLINA) {
        this.NOME_DISCIPLINA = NOME_DISCIPLINA;
    }

    @Override
    public String toString() {
        return "ProfessorTurmas{" + "ID_PROFESSOR=" + ID_PROFESSOR + ", ID_DISCIPLINA=" + ID_DISCIPLINA + ", HORAS=" + HORAS + ", NOME_PROFESSOR=" + NOME_PROFESSOR + ", NOME_DISCIPLINA=" + NOME_DISCIPLINA + '}';
    }

    public ProfessorTurmas(Integer ID_PROFESSOR, Integer ID_DISCIPLINA, Integer HORAS, String NOME_PROFESSOR, String NOME_DISCIPLINA) {
        this.ID_PROFESSOR = ID_PROFESSOR;
        this.ID_DISCIPLINA = ID_DISCIPLINA;
        this.HORAS = HORAS;
        this.NOME_PROFESSOR = NOME_PROFESSOR;
        this.NOME_DISCIPLINA = NOME_DISCIPLINA;
    }
    
}
