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
    , @NamedQuery(name = "Salas.findByIdsala", query = "SELECT s FROM Salas s WHERE s.idsala = :idsala")
    , @NamedQuery(name = "Salas.findByNome", query = "SELECT s FROM Salas s WHERE s.nome = :nome")})
public class Salas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsala")
    private Integer idsala;
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "turmas_ID_TURMA", referencedColumnName = "ID_TURMA")
    @ManyToOne(optional = false)
    private Turmas turmasIDTURMA;

    public Salas() {
    }

    public Salas(Integer idsala) {
        this.idsala = idsala;
    }

    public Integer getIdsala() {
        return idsala;
    }

    public void setIdsala(Integer idsala) {
        this.idsala = idsala;
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
        hash += (idsala != null ? idsala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salas)) {
            return false;
        }
        Salas other = (Salas) object;
        if ((this.idsala == null && other.idsala != null) || (this.idsala != null && !this.idsala.equals(other.idsala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODEL.Salas[ idsala=" + idsala + " ]";
    }
    
}
