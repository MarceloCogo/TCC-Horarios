/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hp Pavilion
 */
@Entity
@Table(name = "professores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Professores.findAll", query = "SELECT p FROM Professores p"),
    @NamedQuery(name = "Professores.findByIdProfessor", query = "SELECT p FROM Professores p WHERE p.idProfessor = :idProfessor"),
    @NamedQuery(name = "Professores.findByNomeProfessor", query = "SELECT p FROM Professores p WHERE p.nomeProfessor = :nomeProfessor")})
public class Professores implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROFESSOR")
    private Integer idProfessor;
    @Column(name = "NOME_PROFESSOR")
    private String nomeProfessor;
   

    public Professores() {
    }

    public Professores(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        Integer oldIdProfessor = this.idProfessor;
        this.idProfessor = idProfessor;
        changeSupport.firePropertyChange("idProfessor", oldIdProfessor, idProfessor);
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        String oldNomeProfessor = this.nomeProfessor;
        this.nomeProfessor = nomeProfessor;
        changeSupport.firePropertyChange("nomeProfessor", oldNomeProfessor, nomeProfessor);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professores)) {
            return false;
        }
        Professores other = (Professores) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Professores[ idProfessor=" + idProfessor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
