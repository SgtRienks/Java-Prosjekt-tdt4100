package prosjekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class GainsTellerController {

    @FXML
    private Button leggTilNyMatvareKnapp;

    @FXML
    private Button leggTilUtvalgtKnapp;

    @FXML
    private Button RegnUtKnapp;

    @FXML
    private Button FjernMatvareKnapp;

    @FXML
    private Button TømKnapp;

    @FXML
    private TextField proteinPer100;

    @FXML
    private TextField KaloriPer100;

    @FXML
    private TextField matvareNavn;

    @FXML
    private TextField leggTilMatvareUtvalgt;

    @FXML
    private TextField vekt;

    @FXML
    private Label resultatlLabel;

    @FXML
    private Label finnesfrafør;

    @FXML
    private Label matvarefinnesikke;

    @FXML
    private Label utvlagtlsitLabel;

    @FXML
    private Label listetallLabel;











    private matvareliste matvareliste;

    // private ObservableList<String> utvalgtMatvareListe = FXCollections.observableArrayList();

    private List<String> utvalgliste = new ArrayList<>();
    private List<Double> vektliste = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("#.##");


    

    
    public GainsTellerController(){
        this.matvareliste = new matvareliste();
    }

    public void initialize(URL location, ResourceBundle resources){

        leggTilNyMatvareKnapp.setOnAction(event -> leggtilnymatvare());
        leggTilUtvalgtKnapp.setOnAction(event -> leggTilUtvalgtListe());
        RegnUtKnapp.setOnAction(event -> regnut());
        TømKnapp.setOnAction(event -> tømUtvalgtListe());
        FjernMatvareKnapp.setOnAction(event -> fjernUtvalgtMat());

  

    }
    @FXML
    private void leggtilnymatvare() {
        // opprett ny matvare utifra oppgitt i feltene
        
        String navn = matvareNavn.getText().toLowerCase();
        Double protein = Double.parseDouble(proteinPer100.getText().strip());
        Double kalorier = Double.parseDouble(KaloriPer100.getText().strip());
        matvareskriver listener = new matvareskriver("src/main/java/prosjekt/matvaredata.txt");
        if(!sjekkOmMatvareFinnes(navn)){
            matvare nymatvare = new matvare(protein, kalorier, navn);
            matvareliste.LeggTilMatvare(nymatvare);
            matvareliste.addMatvareListener(listener);
            matvareliste.notifyObservers(matvareliste);
            finnesfrafør.setText(navn+ " er lagt til");
            matvareNavn.setText("");
            proteinPer100.setText("");
            KaloriPer100.setText("");
        }
        else{
            // throw new IllegalArgumentException("matvarefinnes fra før av");
            finnesfrafør.setText("matvaren finnes fra før");
        }
        

    }
    @FXML
    private void leggTilUtvalgtListe(){
        // iterer gjennom matvaredata.txt og finner matvare for navn
        // eller legger til navn som string også bruke lagutvalgtliste
        // legger til matvare i utvalgt liste, som senere skal regnes ut

        
        String navnSøkes = leggTilMatvareUtvalgt.getText();
       
            if(sjekkOmMatvareFinnes(navnSøkes.toLowerCase())){

                if(navnSøkes.isEmpty() || vekt.getText().isEmpty() || vekt.getText().isBlank()){
                    matvarefinnesikke.setText("navn/vekt må ha tekst");
                    return;
                    
                }
                if(Double.parseDouble(vekt.getText())<0.0){
                    matvarefinnesikke.setText("ikke negativ vekt");
                    return;

                }
                
                if(!(navnSøkes.isBlank()) || !(navnSøkes.isEmpty()) || !(vekt.getText().isEmpty()) || !(vekt.getText().isBlank()) || navnSøkes!= " "){
                    utvalgliste.add(navnSøkes.toLowerCase().strip());
                    vektliste.add(Double.parseDouble(vekt.getText().strip()));
                    leggTilMatvareUtvalgt.setText("");
                    matvarefinnesikke.setText("");
                    utvlagtlsitLabel.setText(utvalgliste.toString());
                    listetallLabel.setText(vektliste.toString());    

                    System.out.println(this.utvalgliste);
                    System.out.println(vektliste);
                
                

            }
            else{
                matvarefinnesikke.setText("navn/vekt må ha tekst");
                throw new IllegalArgumentException("tom tekst navn/vekt");
            }
        }
    
        else {
            
            matvarefinnesikke.setText("matvaren finnes ikke");
            throw new IllegalArgumentException("matvaren finnes ikke");

    
    }
}

        

        
    

    
    @FXML
    private void regnut(){

        matvareliste a = new matvareliste().lagUtvalgtListe(utvalgliste);

        // a.lagUtvalgtListe(this.utvalgliste);
        //System.out.println(a.lagUtvalgtListe(this.utvalgliste));
        
        Double totalProtein = matvareliste.ProteinUtregning(a, vektliste);
        Double totalKalorier = matvareliste.KaloriUtregning(a, vektliste);

        String resultat = "Kalorier: " + df.format(totalKalorier) + " " + "Proteiner: " + df.format(totalProtein);
        resultatlLabel.setText(resultat);
        

 
    }
    @FXML
    public void tømUtvalgtListe(){
        utvalgliste.clear();
        vektliste.clear();
    }

    @FXML
    private void fjernUtvalgtMat(){
        String fjernmatvare = leggTilMatvareUtvalgt.getText();
        fjernUtvalgtNavn(utvalgliste, vektliste, fjernmatvare);
        utvlagtlsitLabel.setText(utvalgliste.toString());
        listetallLabel.setText(vektliste.toString());   
                    
        System.out.println(this.utvalgliste);
        System.out.println(vektliste);

        
    }

    public void fjernUtvalgtNavn(List<String> mat, List<Double> tall, String matnavn){
        for(String streng : mat){
            if(streng.toLowerCase().equals(matnavn)){
                int index = mat.indexOf(matnavn);
                tall.remove(index);
                System.out.println(mat.indexOf(matnavn));
                mat.remove(streng);
                
                

                break;
            
                }
                
                
            }
       
        }
    
   
   


    



    public boolean sjekkOmMatvareFinnes(String navn){
        boolean finnes = false;

        try{
            File fil = new File("src/main/java/prosjekt/matvaredata.txt");
            Scanner scanner = new Scanner(fil);
            while(scanner.hasNextLine()){
                List<String> liste = new ArrayList<>();
                String ord = scanner.nextLine();
                liste.add(ord);
                for(String or : liste){
                    String[] name = or.split(",");
                    String navnavn = name[0];
                
                
                 
                // String[] ords = linje.split("\\s+");
                // for(String ord: ords){
                    if(navnavn.equalsIgnoreCase(navn.toLowerCase())){
                        finnes=true;
                        break;
                    }

                // }
                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return finnes;
    }

    public static void main(String[] args) {

        matvareliste test = new matvareliste();
        
        List<String> hest = new ArrayList<>();
        String navn = "kylling";
        String kesam = "kesam";
        hest.add(navn);
        hest.add(kesam);
        System.out.println(test);
        System.out.println(hest);
        System.out.println(test.lagUtvalgtListe(hest));
        


        
        
        
    }
    

    
}
