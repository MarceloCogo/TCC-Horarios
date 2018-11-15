/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hp Pavilion
 */
@Entity
@Table(name = "cursos")
@XmlRootElement
@NamedQueries({
    
//    @NamedQueries({
//    @NamedQuery(name = "Disciplinas.findAll", query = "SELECT d FROM Disciplinas d"),
//    @NamedQuery(name = "Disciplinas.findByIdDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.idDisciplina = :idDisciplina"),
//    @NamedQuery(name = "Disciplinas.findByNomeDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.nomeDisciplina = :nomeDisciplina"),
//    @NamedQuery(name = "Disciplinas.findByCargaHorariaTotal", query = "SELECT d FROM Disciplinas d WHERE d.cargaHorariaTotal = :cargaHorariaTotal"),
//    @NamedQuery(name = "Disciplinas.findByCargaHorariaSemanal", query = "SELECT d FROM Disciplinas d WHERE d.cargaHorariaSemanal = :cargaHorariaSemanal"),
//    @NamedQuery(name = "Disciplinas.findByDISCIPLINAScol", query = "SELECT d FROM Disciplinas d WHERE d.dISCIPLINAScol = :dISCIPLINAScol")})
    
    @NamedQuery(name = "Cursos.findAll", query = "SELECT c FROM Cursos c"),
    @NamedQuery(name = "Cursos.findByIdCurso", query = "SELECT c FROM Cursos c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Cursos.findByNomeCurso", query = "SELECT c FROM Cursos c WHERE c.nomeCurso = :nomeCurso")})
public class Cursos implements Serializable {
    @Basic(optional = false)
    @Column(name = "NOME_CURSO")
    private String nomeCurso;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CURSO")
    private Integer idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursosIdCurso")
    private Collection<Disciplinas> disciplinasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<HorariosAulas> horariosAulasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<Turmas> turmasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<Periodos> periodosCollection;

    public Cursos(int id) {
        this.setIdCurso(id); //somente para testar
    }

    public Cursos(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        Integer oldIdCurso = this.idCurso;
        this.idCurso = idCurso;
        changeSupport.firePropertyChange("idCurso", oldIdCurso, idCurso);
    }

    

    @XmlTransient
    public Collection<Disciplinas> getDisciplinasCollection() {
        return disciplinasCollection;
    }

    public void setDisciplinasCollection(Collection<Disciplinas> disciplinasCollection) {
        this.disciplinasCollection = disciplinasCollection;
    }

    @XmlTransient
    public Collection<HorariosAulas> getHorariosAulasCollection() {
        return horariosAulasCollection;
    }

    public void setHorariosAulasCollection(Collection<HorariosAulas> horariosAulasCollection) {
        this.horariosAulasCollection = horariosAulasCollection;
    }

    @XmlTransient
    public Collection<Turmas> getTurmasCollection() {
        return turmasCollection;
    }

    public void setTurmasCollection(Collection<Turmas> turmasCollection) {
        this.turmasCollection = turmasCollection;
    }

    @XmlTransient
    public Collection<Periodos> getPeriodosCollection() {
        return periodosCollection;
    }

    public void setPeriodosCollection(Collection<Periodos> periodosCollection) {
        this.periodosCollection = periodosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursos)) {
            return false;
        }
        Cursos other = (Cursos) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Cursos[ idCurso=" + idCurso + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Cursos() {
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        String oldNomeCurso = this.nomeCurso;
        this.nomeCurso = nomeCurso;
        changeSupport.firePropertyChange("nomeCurso", oldNomeCurso, nomeCurso);
    }
    
}
