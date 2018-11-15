/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelocogo
 */
@Entity
@Table(name = "salas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salas.findAll", query = "SELECT s FROM Salas s")
    , @NamedQuery(name = "Salas.findByIdsalas", query = "SELECT s FROM Salas s WHERE s.idsalas = :idsalas")
    , @NamedQuery(name = "Salas.findByNome", query = "SELECT s FROM Salas s WHERE s.nome = :nome")})
public class Salas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsalas")
    private Integer idsalas;
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "turmas_ID_TURMA", referencedColumnName = "ID_TURMA")
    @ManyToOne(optional = false)
    private Turmas turmasIDTURMA;

    public Salas() {
    }

    public Salas(Integer idsalas) {
        this.idsalas = idsalas;
    }

    public Integer getIdsalas() {
        return idsalas;
    }

    public void setIdsalas(Integer idsalas) {
        this.idsalas = idsalas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turmas getTurmasIDTURMA() {
        return turmasIDTURMA;
    }

    public void setTurmasIDTURMA(Turmas turmasIDTURMA) {
        this.turmasIDTURMA = turmasIDTURMA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsalas != null ? idsalas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salas)) {
            return false;
        }
        Salas other = (Salas) object;
        if ((this.idsalas == null && other.idsalas != null) || (this.idsalas != null && !this.idsalas.equals(other.idsalas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.Salas[ idsalas=" + idsalas + " ]";
    }
    
}
