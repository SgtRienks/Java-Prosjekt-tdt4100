package prosjekt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;



public class matvareliste{

    private ArrayList<matvare> matvarer = new ArrayList<>();
    private Collection<MatvareListListener> listListeners = new ArrayList<MatvareListListener>();


    public void LeggTilMatvare(matvare matvare){
        if(!matvarer.contains(matvare)){
        matvarer.add(matvare);
        matvareliste oppdatertliste = new matvareliste();
        oppdatertliste.matvarer = matvarer;
        // System.out.println(oppdatertliste.getMatvarer());
        notifyObservers(oppdatertliste);

        }
        else{
            throw new IllegalArgumentException("kan ikke legge til matvare som allerede finnes i listen");
        }
        
    }


    public ArrayList<matvare> getMatvarer() {
        return matvarer;
    }

    public void FjernMatvare(matvare matvare){
        matvarer.remove(matvare);
        matvareliste oppdatertliste = new matvareliste();
        oppdatertliste.matvarer = matvarer;
        notifyObservers(oppdatertliste);
        

        
    }

    
    public void fjernUtvalgtNavn(List<String> mat, List<Double> tall, String matnavn){
        for(String streng : mat){
            if(streng.toLowerCase().equals(matnavn)){
                mat.remove(streng);
                int tal = mat.indexOf(streng);
                tall.remove(tall.get(tal));

                    
            
                }

            }
       
        }
        
        


    

    public void addMatvareListener(MatvareListListener listListener) {
        if (!listListeners.contains(listListener)) {
            listListeners.add(listListener);
        }
    }

    public void removeMatvareListener(MatvareListListener listListener) {
        listListeners.remove(listListener);

    }

    public Collection<MatvareListListener> getListeners(){
        return listListeners;

    }

    public void notifyObservers(matvareliste oppdatertliste){
        for (MatvareListListener listListener : listListeners) {
            listListener.listChanged2(oppdatertliste);
        }
    }

    

    public matvareliste lagUtvalgtListe(List<String> utvalgteMatvareNavn) {

        Map<String, matvare> matvareMap = new HashMap<>();
        try {

            BufferedReader leser = new BufferedReader(new FileReader("src/main/java/prosjekt/matvaredata.txt"));
            String linje;
            while((linje = leser.readLine()) != null) {
                
                String[] deler = linje.split(",");
                String navn = deler[0];
                Double protein = Double.parseDouble(deler[1]);
                Double kalorier = Double.parseDouble(deler[2]);
                matvare matvare = new matvare(protein, kalorier, navn);
                matvareMap.put(navn, matvare);
            }
            leser.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        matvareliste utvalgteMatvarer = new matvareliste();
        for(String navn : utvalgteMatvareNavn) {
            matvare matvare = matvareMap.get(navn);
            if (matvare != null) {
                utvalgteMatvarer.LeggTilMatvare(matvare);
            }
        }
        return utvalgteMatvarer;
        
        
    }

    public boolean sjekkOmMatvareFinnes(String navn){
        boolean finnes = false;

        try{
            File fil = new File("src/main/java/prosjekt/matvaredata.txt");
            Scanner scanner = new Scanner(fil);
            while(scanner.hasNextLine()){
                String linje = scanner.nextLine().toLowerCase();
                if(linje.contains(navn.toLowerCase())){
                    finnes=true;
                    break;

                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return finnes;
    }

    public Double ProteinUtregning(matvareliste utvalgtvarer, List<Double> vekt){

        double[] proteingram = {0.0};

            IntStream.range(0, Math.min(utvalgtvarer.size(), vekt.size())).forEach(i -> {

                matvare matvare = utvalgtvarer.getMatvareNumber(i);
                Double vektgram = vekt.get(i);
                proteingram[0] += vektgram * matvare.getProteinper100() / 100.0;



            });
        return proteingram[0];

    }



    public Double KaloriUtregning(matvareliste utvalgtvarer, List<Double> vekt){

        double[] kaloriertot={0.0};

        IntStream.range(0, Math.min(utvalgtvarer.size(), vekt.size())).forEach(i -> {
            matvare matvare = utvalgtvarer.getMatvareNumber(i);
            Double vektgram = vekt.get(i);
            // do something with item1 and item2
            kaloriertot[0] += vektgram * matvare.getKaloriper100() / 100.0;
            
            
             
            
        });
        

        return kaloriertot[0];

    }

    private matvare getMatvareNumber(int i) {
        if (i < 0 || i >= matvarer.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        return matvarer.get(i);
    }




    private int size() {
        return matvarer.size();
    }


   


    @Override
    public String toString() {
        return "matvarelisten [matvarer=" + matvarer + ", listListeners=" + listListeners + "]";
    }

    public static void main(String[] args) {
        List<String> liste = new ArrayList<>();
        String kul = "hei";
        String kul1 = "heien";
        String kul2 = "heiene";
        liste.add(kul);
        liste.add(kul1);
        liste.add(kul2);
        System.out.println(liste.indexOf(kul2));

        List<Double> liste0 = new ArrayList<>();
        Double yeah = 1.1;
        Double yeah1 = 1.2;
        Double yeah2 = 1.3;
        liste0.add(yeah);
        liste0.add(yeah1);
        liste0.add(yeah2);
        System.out.println(liste0);
        liste0.remove(liste.indexOf(kul1));
        System.out.println(liste0);
        



    }

    

   
    // gammel regne ut metode for kalorier (tilsvarende for protein):

    // for(matvare matvare : utvalgtvarer.getMatvarer()){

        //     for(Double k : vekt) {

        //     Double kalorier = k * matvare.getKaloriper100() / 100.0;
        //     kaloriertot += kalorier;   
        //     }
        // }


    

    

    

    

    
}
