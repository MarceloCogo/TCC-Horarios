/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Hp Pavilion
 */
@Embeddable
public class HorariosAulasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_HORARIOS_AULAS")
    private int idHorariosAulas;
    @Basic(optional = false)
    @Column(name = "HORARIOS_AULAScol")
    private String hORARIOSAULAScol;
    @Basic(optional = false)
    @Column(name = "AULA")
    private String aula;

    public HorariosAulasPK() {
    }

    public HorariosAulasPK(int idHorariosAulas, String hORARIOSAULAScol, String aula) {
        this.idHorariosAulas = idHorariosAulas;
        this.hORARIOSAULAScol = hORARIOSAULAScol;
        this.aula = aula;
    }

    public int getIdHorariosAulas() {
        return idHorariosAulas;
    }

    public void setIdHorariosAulas(int idHorariosAulas) {
        this.idHorariosAulas = idHorariosAulas;
    }

    public String getHORARIOSAULAScol() {
        return hORARIOSAULAScol;
    }

    public void setHORARIOSAULAScol(String hORARIOSAULAScol) {
        this.hORARIOSAULAScol = hORARIOSAULAScol;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idHorariosAulas;
        hash += (hORARIOSAULAScol != null ? hORARIOSAULAScol.hashCode() : 0);
        hash += (aula != null ? aula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariosAulasPK)) {
            return false;
        }
        HorariosAulasPK other = (HorariosAulasPK) object;
        if (this.idHorariosAulas != other.idHorariosAulas) {
            return false;
        }
        if ((this.hORARIOSAULAScol == null && other.hORARIOSAULAScol != null) || (this.hORARIOSAULAScol != null && !this.hORARIOSAULAScol.equals(other.hORARIOSAULAScol))) {
            return false;
        }
        if ((this.aula == null && other.aula != null) || (this.aula != null && !this.aula.equals(other.aula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.HorariosAulasPK[ idHorariosAulas=" + idHorariosAulas + ", hORARIOSAULAScol=" + hORARIOSAULAScol + ", aula=" + aula + " ]";
    }
    
}
