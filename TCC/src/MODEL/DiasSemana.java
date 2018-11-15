/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author marcelocogo
 */
public class DiasSemana {
    
    private String segunda;
    
    private String terca;
    
    private String quarta;
    
    private String quinta;
    
    private String sexta;

    public DiasSemana(String segunda, String terca, String quarta, String quinta, String sexta) {
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
    }
    
    

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public void setTerca(String terca) {
        this.terca = terca;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

    public void setSexta(String sexta) {
        this.sexta = sexta;
    }
    
    

    public String getSegunda() {
        return segunda;
    }

    public String getTerca() {
        return terca;
    }

    public String getQuarta() {
        return quarta;
    }

    public String getQuinta() {
        return quinta;
    }

    public String getSexta() {
        return sexta;
    }
    
    
    
}
