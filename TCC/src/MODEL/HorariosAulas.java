/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hp Pavilion
 */
@Entity
@Table(name = "horarios_aulas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorariosAulas.findAll", query = "SELECT h FROM HorariosAulas h"),
    @NamedQuery(name = "HorariosAulas.findByIdHorariosAulas", query = "SELECT h FROM HorariosAulas h WHERE h.horariosAulasPK.idHorariosAulas = :idHorariosAulas"),
    @NamedQuery(name = "HorariosAulas.findByPeriodo", query = "SELECT h FROM HorariosAulas h WHERE h.periodo = :periodo"),
    @NamedQuery(name = "HorariosAulas.findByIdTurma", query = "SELECT h FROM HorariosAulas h WHERE h.idTurma = :idTurma"),
    @NamedQuery(name = "HorariosAulas.findBySemestre", query = "SELECT h FROM HorariosAulas h WHERE h.semestre = :semestre"),
    @NamedQuery(name = "HorariosAulas.findByHORARIOSAULAScol", query = "SELECT h FROM HorariosAulas h WHERE h.horariosAulasPK.hORARIOSAULAScol = :hORARIOSAULAScol"),
    @NamedQuery(name = "HorariosAulas.findByTurno", query = "SELECT h FROM HorariosAulas h WHERE h.turno = :turno"),
    @NamedQuery(name = "HorariosAulas.findByAula", query = "SELECT h FROM HorariosAulas h WHERE h.horariosAulasPK.aula = :aula")})
public class HorariosAulas implements Serializable {

    @JoinColumn(name = "salas_idsala", referencedColumnName = "idsala")
    @ManyToOne(optional = false)
    private Salas salasIdsala;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorariosAulasPK horariosAulasPK;
    @Basic(optional = false)
    @Column(name = "PERIODO")
    private String periodo;
    @Basic(optional = false)
    @Column(name = "ID_TURMA")
    private int idTurma;
    @Basic(optional = false)
    @Column(name = "SEMESTRE")
    private String semestre;
    @Basic(optional = false)
    @Column(name = "TURNO")
    private String turno;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Cursos idCurso;
    @JoinColumn(name = "ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA")
    @ManyToOne(optional = false)
    private Disciplinas idDisciplina;

    public HorariosAulas() {
    }

    public HorariosAulas(HorariosAulasPK horariosAulasPK) {
        this.horariosAulasPK = horariosAulasPK;
    }

    public HorariosAulas(HorariosAulasPK horariosAulasPK, String periodo, int idTurma, String semestre, String turno) {
        this.horariosAulasPK = horariosAulasPK;
        this.periodo = periodo;
        this.idTurma = idTurma;
        this.semestre = semestre;
        this.turno = turno;
    }

    public HorariosAulas(int idHorariosAulas, String hORARIOSAULAScol, String aula) {
        this.horariosAulasPK = new HorariosAulasPK(idHorariosAulas, hORARIOSAULAScol, aula);
    }

    public HorariosAulasPK getHorariosAulasPK() {
        return horariosAulasPK;
    }

    public void setHorariosAulasPK(HorariosAulasPK horariosAulasPK) {
        this.horariosAulasPK = horariosAulasPK;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Cursos getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Cursos idCurso) {
        this.idCurso = idCurso;
    }

    public Disciplinas getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Disciplinas idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horariosAulasPK != null ? horariosAulasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariosAulas)) {
            return false;
        }
        HorariosAulas other = (HorariosAulas) object;
        if ((this.horariosAulasPK == null && other.horariosAulasPK != null) || (this.horariosAulasPK != null && !this.horariosAulasPK.equals(other.horariosAulasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.HorariosAulas[ horariosAulasPK=" + horariosAulasPK + " ]";
    }

    public Salas getSalasIdsala() {
        return salasIdsala;
    }

    public void setSalasIdsala(Salas salasIdsala) {
        this.salasIdsala = salasIdsala;
    }
    
}
