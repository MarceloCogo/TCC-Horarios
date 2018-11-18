/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hp Pavilion
 */
@Entity
@Table(name = "turmas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turmas.findAll", query = "SELECT t FROM Turmas t"),
    @NamedQuery(name = "Turmas.findByIdTurma", query = "SELECT t FROM Turmas t WHERE t.idTurma = :idTurma"),
    @NamedQuery(name = "Turmas.findByTurno", query = "SELECT t FROM Turmas t WHERE t.turno = :turno"),
    @NamedQuery(name = "Turmas.findByCurso", query = "SELECT t FROM Turmas t WHERE t.idCurso = :idCurso")})
public class Turmas implements Serializable {

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @JoinColumn(name = "periodos_ID_PERIODO", referencedColumnName = "ID_PERIODO")
    @ManyToOne(optional = false)
    private Periodos periodosIDPERIODO;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turmasIDTURMA")
    private Collection<Salas> salasCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TURMA")
    private Integer idTurma;
    @Column(name = "TURNO")
    private String turno;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Cursos idCurso;

    public Turmas() {
    }
    
    public String getNomeCurso() {
        return idCurso.getNomeCurso();
    }
    
    public String getNomePeriodo() {
        return periodosIDPERIODO.getPeriodo();
    }
    
    public String getNomeSemestre() {
        return periodosIDPERIODO.getSemestre();
    }

    public Turmas(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurma != null ? idTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turmas)) {
            return false;
        }
        Turmas other = (Turmas) object;
        if ((this.idTurma == null && other.idTurma != null) || (this.idTurma != null && !this.idTurma.equals(other.idTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Turmas[ idTurma=" + idTurma + " ]";
    }

    @XmlTransient
    public Collection<Salas> getSalasCollection() {
        return salasCollection;
    }

    public void setSalasCollection(Collection<Salas> salasCollection) {
        this.salasCollection = salasCollection;
    }

    public Periodos getPeriodosIDPERIODO() {
        return periodosIDPERIODO;
    }

    public void setPeriodosIDPERIODO(Periodos periodosIDPERIODO) {
        this.periodosIDPERIODO = periodosIDPERIODO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
