package prosjekt;

import javafx.scene.control.TextField;

public class matvare {

    private Double proteinper100;
    private Double kaloriper100;
    private String matvarenavn;
    
    
    public matvare(Double proteinper100, Double kaloriper100, String matvarenavn) {
        this.proteinper100 = proteinper100;
        this.kaloriper100 = kaloriper100;
        this.matvarenavn = matvarenavn;
    }


    public matvare(TextField proteinPer1002, TextField kaloriPer1002, TextField matvareNavn2) {
    }


    public Double getProteinper100() {
        return proteinper100;
    }


    public void setProteinper100(Double proteinper100) {
        this.proteinper100 = proteinper100;
    }


    public Double getKaloriper100() {
        return kaloriper100;
    }


    public void setKaloriper100(Double kaloriper100) {
        this.kaloriper100 = kaloriper100;
    }


    public String getMatvarenavn() {
        return matvarenavn;
    }


    public void setMatvarenavn(String matvarenavn) {
        this.matvarenavn = matvarenavn;
    }


    @Override
    public String toString() {
        return "matvare [proteinper100=" + proteinper100 + ", kaloriper100=" + kaloriper100 + ", matvarenavn="
                + matvarenavn + "]";
    }

    

    

    






    
}
