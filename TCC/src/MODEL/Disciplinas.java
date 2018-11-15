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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "disciplinas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplinas.findAll", query = "SELECT d FROM Disciplinas d"),
    @NamedQuery(name = "Disciplinas.findByIdDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.idDisciplina = :idDisciplina"),
    @NamedQuery(name = "Disciplinas.findByNomeDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.nomeDisciplina = :nomeDisciplina"),
    @NamedQuery(name = "Disciplinas.findByCargaHorariaTotal", query = "SELECT d FROM Disciplinas d WHERE d.cargaHorariaTotal = :cargaHorariaTotal"),
    @NamedQuery(name = "Disciplinas.findByCargaHorariaSemanal", query = "SELECT d FROM Disciplinas d WHERE d.cargaHorariaSemanal = :cargaHorariaSemanal"),
    @NamedQuery(name = "Disciplinas.findByDISCIPLINAScol", query = "SELECT d FROM Disciplinas d WHERE d.dISCIPLINAScol = :dISCIPLINAScol"),
    @NamedQuery(name = "Disciplinas.findByIdCurso", query="SELECT d FROM Disciplinas d WHERE d.idCurso = :idCurso")})
public class Disciplinas implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DISCIPLINA")
    private Integer idDisciplina;
    @Column(name = "NOME_DISCIPLINA")
    private String nomeDisciplina;
    @Column(name = "CARGA_HORARIA_TOTAL")
    private Integer cargaHorariaTotal;
    @Column(name = "CARGA_HORARIA_SEMANAL")
    private Integer cargaHorariaSemanal;
    @Column(name = "DISCIPLINAScol")
    private String dISCIPLINAScol;
    @JoinColumn(name = "CURSOS_ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Cursos cursosIdCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDisciplina")
    private Collection<HorariosAulas> horariosAulasCollection;
    private Integer idCurso;

    public Disciplinas() {
    }

    public Disciplinas(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        Integer oldIdDisciplina = this.idDisciplina;
        this.idDisciplina = idDisciplina;
        changeSupport.firePropertyChange("idDisciplina", oldIdDisciplina, idDisciplina);
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        String oldNomeDisciplina = this.nomeDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        changeSupport.firePropertyChange("nomeDisciplina", oldNomeDisciplina, nomeDisciplina);
    }

    public Integer getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
        Integer oldCargaHorariaTotal = this.cargaHorariaTotal;
        this.cargaHorariaTotal = cargaHorariaTotal;
        changeSupport.firePropertyChange("cargaHorariaTotal", oldCargaHorariaTotal, cargaHorariaTotal);
    }

    public Integer getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(Integer cargaHorariaSemanal) {
        Integer oldCargaHorariaSemanal = this.cargaHorariaSemanal;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        changeSupport.firePropertyChange("cargaHorariaSemanal", oldCargaHorariaSemanal, cargaHorariaSemanal);
    }

    public String getDISCIPLINAScol() {
        return dISCIPLINAScol;
    }

    public void setDISCIPLINAScol(String dISCIPLINAScol) {
        String oldDISCIPLINAScol = this.dISCIPLINAScol;
        this.dISCIPLINAScol = dISCIPLINAScol;
        changeSupport.firePropertyChange("DISCIPLINAScol", oldDISCIPLINAScol, dISCIPLINAScol);
    }

    public Cursos getCursosIdCurso() {
        return cursosIdCurso;
    }

    public void setCursosIdCurso(Cursos cursosIdCurso) {
        Cursos oldCursosIdCurso = this.cursosIdCurso;
        this.cursosIdCurso = cursosIdCurso;
        changeSupport.firePropertyChange("cursosIdCurso", oldCursosIdCurso, cursosIdCurso);
    }

    @XmlTransient
    public Collection<HorariosAulas> getHorariosAulasCollection() {
        return horariosAulasCollection;
    }

    public void setHorariosAulasCollection(Collection<HorariosAulas> horariosAulasCollection) {
        this.horariosAulasCollection = horariosAulasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplinas)) {
            return false;
        }
        Disciplinas other = (Disciplinas) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Disciplinas[ idDisciplina=" + idDisciplina + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }
    
    
    
}
