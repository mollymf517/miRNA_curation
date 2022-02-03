import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class mapping {
    public static ArrayList<String> align_miRNA(){
        //read in miRNAs from fastq file
        //add them to an arraylist for alignment with each precursor sequence
        ArrayList<String> mi_RNAs = new ArrayList<>();
        return mi_RNAs;
    }
    public static ArrayList<String> get_precursors(String filename) throws FileNotFoundException{
         ArrayList<String> precursors = new ArrayList<>();
         try(BufferedReader read_precursors = new BufferedReader(new FileReader(filename))){
             String line = null;
             while ((line = read_precursors.readLine()) != null){
                 String[] data = line.split("\t");
                 precursors.add(data[2]);
                 System.out.println(data[2]);
             }
         } catch (Exception e) {
             System.out.println("Error.");
         }
        
        return precursors;
    }
    public static boolean map_precursors(ArrayList<String> precursors, ArrayList<String> sequences){
        for (int i = 1; i<precursors.size(); i++){
            String precursor = precursors.get(i);
            //code to align all miRNAs with the sequence - Bowtie 2; start off with distance of 0
        }
        return true;
    }
}