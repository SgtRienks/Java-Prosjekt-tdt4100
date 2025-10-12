package prosjekt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class appen {

    public static void main(String[] args) throws IOException {
        matvareliste matvareliste1 = new matvareliste();
        // matvareskriver matvareskriver1 = new matvareskriver("src/main/java/prosjekt/matvaredata.txt");
        // matvareliste1.addMatvareListener(matvareskriver1);
        matvare kylling = new matvare(23.0, 200.0, "Kylling");
        matvareliste1.LeggTilMatvare(kylling);
        matvare test = new matvare(20.0, 100.0, "test");
        matvareliste1.LeggTilMatvare(test);
        
        matvare Spagetti = new matvare(12.0, 300.0, "Spaghetti");
        matvareliste1.LeggTilMatvare(Spagetti);
        matvareliste1.notifyObservers(matvareliste1);
        // System.out.println(matvareliste1.getMatvarer().toString());
        

        List<String> liste = new ArrayList<>();
        liste.add(kylling.getMatvarenavn() + "," + kylling.getKaloriper100() + "," + kylling.getProteinper100());
        liste.add(Spagetti.getMatvarenavn()+ "," + Spagetti.getKaloriper100() + "," + Spagetti.getProteinper100());
        // FileHelper a = new FileHelper();

        // FileHelper.writeLines("matvaredata.txt", liste);

        // matvare løk = new matvare(10.0, 400.0, "løk");
        // matvare is = new matvare(11.0, 500.0, "is");

        // liste.add(løk.getMatvarenavn()+ "," + løk.getKaloriper100() + "," + løk.getProteinper100());

        // FileHelper.writeLines("src/main/java/prosjekt/matvaredata.txt", liste);

        matvareliste utvalgliste = new matvareliste();



        utvalgliste.lagUtvalgtListe(liste);
        System.out.println(utvalgliste.toString());
    



        List<String> ordliste = new ArrayList<>();
            
        
        String ord1 = "brød";
        String ord2 = "kylling";
        String ord3 = "havregryn";
        ordliste.add(ord1);
        ordliste.add(ord2);
        ordliste.add(ord3);

        matvareliste listenn = new matvareliste();
        System.out.println(listenn.lagUtvalgtListe(ordliste));
        

        


        
        
        
        
   

        
    




        
    }
    
}
