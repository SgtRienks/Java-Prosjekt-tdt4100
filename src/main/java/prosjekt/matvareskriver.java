package prosjekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class matvareskriver implements MatvareListListener{

    private String fileName;



    public matvareskriver(String fileName){

        this.fileName = fileName;

    }

  

    public boolean sjekkOmMatvareFinnes(String navn){
        boolean finnes = false;

        try{
            File fil = new File("matvaredata.txt");
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


    @Override
    public void listChanged2(matvareliste list) {

        try {
            List<String> lines = new ArrayList<>();
        File file = new File(fileName);

        // leser ekisterende linjer i fila
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            scanner.close();
        }

        // sjekker om nye matvaren allerede finnes
        List<matvare> newMatvarer = list.getMatvarer();
        List<String> newLines = new ArrayList<>();
        for (matvare matvare : newMatvarer) {
            String line = matvare.getMatvarenavn() + "," + matvare.getProteinper100() + "," + matvare.getKaloriper100();
            if (!lines.contains(line)) {
                newLines.add(line);
            }
        }

        // legger til nye linjer i fila under de andre
        if (!newLines.isEmpty()) {
            FileWriter writer = new FileWriter(file, true);
            for (String line : newLines) {
                writer.write(line + "\n");
            }
            writer.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error writing to file.");
    }
}
}

    


//  List<String> liste = new ArrayList<>();
//             for (matvare matvare : list.getMatvarer()) {
//                 liste.add(matvare.getMatvarenavn() + "," + matvare.getProteinper100() + "," + matvare.getKaloriper100());
            
//             }
//             FileHelper.writeLines("src/main/java/prosjekt/matvaredata.txt", liste);
            
            
            
       
//         } catch (IOException e){
//             e.printStackTrace();
//             System.out.println("det ble feil");
//         }
    
//     }


 // @Override
    // public void listChanged(matvareliste list) {

    //     try {
    //         FileWriter minSkriver = new FileWriter(fileName, true);
    //         for (matvare matvare : list.getMatvarer()) {
    //             if(!list.sjekkOmMatvareFinnes(matvare.getMatvarenavn())){
                
                
    //             minSkriver.write(matvare.getMatvarenavn() + "," + matvare.getProteinper100() + "," + matvare.getKaloriper100() + "\n");
    //             System.out.println("ble det skrevet");
    //             }
 
    //         }
    //         minSkriver.flush();
    //         minSkriver.close();
            
            
            
       
    //     } catch (IOException e){
    //         e.printStackTrace();
    //         System.out.println("det ble feil");
    //     }

    // }







