package exampleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import prosjekt.matvare;
import prosjekt.matvareliste;
import prosjekt.matvareskriver;

public class GainsTellerTest {

    @Test
    void testRegnUt() {

        matvare matvare1 = new matvare(23.0, 111.0, "Kylling");
        matvareliste matvareliste1 = new matvareliste();
        List<String> matliste1 = new ArrayList<>();
        List<Double> vektliste1 = new ArrayList<>();

        String kylling = "kylling";
        Double vekt = 200.0;
        matvareliste1.LeggTilMatvare(matvare1);
        matliste1.add(kylling);
        vektliste1.add(vekt);
        matvareliste1.lagUtvalgtListe(matliste1);
        



        assertEquals(46.0, matvareliste1.ProteinUtregning(matvareliste1, vektliste1));
        assertEquals(222.0, matvareliste1.KaloriUtregning(matvareliste1, vektliste1));
       
    }

    @Test
    void testLeggTilMatvare(){

        matvare matvare2 = new matvare(12.0, 211.0, "brød");
        matvareliste matvareliste2 = new matvareliste();
        matvareliste2.LeggTilMatvare(matvare2);

        assertEquals(true, matvareliste2.getMatvarer().contains(matvare2));

    }


    @Test
    void testFjernMatvare(){

        matvare matvare2 = new matvare(12.0, 211.0, "brød");
        matvareliste matvareliste2 = new matvareliste();
        matvareliste2.LeggTilMatvare(matvare2);
        matvareliste2.FjernMatvare(matvare2);

        assertEquals(false, matvareliste2.getMatvarer().contains(matvare2));
        
    }

    @Test
    void testSjekkOmMatvareFinnes(){
        
        matvare matvare1 = new matvare(23.0, 111.0, "Kylling");
        matvareliste matvareliste2 = new matvareliste();
        matvareliste2.LeggTilMatvare(matvare1);
        matvareskriver matvareskriver1 = new matvareskriver("src/test/java/exampleproject/test.txt");
        matvareliste2.addMatvareListener(matvareskriver1);
        matvareliste2.notifyObservers(matvareliste2);
        assertEquals(true, matvareliste2.sjekkOmMatvareFinnes("Kylling"));
        
    }

    @Test
    void testAddMatvareListener(){

        matvareliste matvareliste2 = new matvareliste();
        matvareskriver matvareskriver1 = new matvareskriver("src/main/java/prosjekt/matvaredata.txt");
        matvareliste2.addMatvareListener(matvareskriver1);
        assertEquals(true, matvareliste2.getListeners().contains(matvareskriver1));


    }

    @Test
    //tester skriv til fil
    void testListChanged2(){
        
        matvare matvare1 = new matvare(23.0, 111.0, "Kylling");
        matvareliste matvareliste2 = new matvareliste();
        matvareliste2.LeggTilMatvare(matvare1);
        matvareskriver matvareskriver1 = new matvareskriver("src/test/java/exampleproject/test.txt");
        matvareliste2.addMatvareListener(matvareskriver1);
        matvareliste2.notifyObservers(matvareliste2);
        assertEquals(true, matvareliste2.sjekkOmMatvareFinnes("Kylling"));
        
    }

    //test at controller ikke slipper inn feil input

    @Test
    void testLagUtvalgtliste(){
        List<String> ordliste = new ArrayList<>();
            
        
        String ord1 = "brød";
        String ord2 = "kylling";
        String ord3 = "havregryn";
        ordliste.add(ord1);
        ordliste.add(ord2);
        ordliste.add(ord3);

        matvareliste liste = new matvareliste();
        matvareliste kult = liste.lagUtvalgtListe(ordliste);
        
        assertEquals(true , kult.sjekkOmMatvareFinnes(ord3));


    }


    







    



    
}
