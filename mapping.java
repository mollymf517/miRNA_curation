import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class mapping {
    public static ArrayList<String> getSequences(String fastq){
        //read in miRNAs from fastq file
        //add them to an arraylist for alignment with each precursor sequence
        ArrayList<String> mi_RNAs = new ArrayList<>();
        try(BufferedReader read_seqs = new BufferedReader(new FileReader(fastq))){
            String line = null;
            int lineCount = 1;
            while ((line = read_seqs.readLine()) != null){
                //String[] data = line.split("\t");

                if (lineCount == 2){
                    //System.out.println("Sequence found: ");
                    mi_RNAs.add(line);
                    System.out.println(line);
                } 
                if (lineCount == 4){
                    lineCount = 0;
                }
                lineCount+=1;
            }
        } catch (Exception e) {
            System.out.println("Error.");
        }
        return mi_RNAs;
    }
    public static ArrayList<String> getPrecursors(String filename) {
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
    public static String[] getFlankingSeqs(String precursor, int seqSize){
        //when do we want to get flanking sequences? One at a time, or do we want
        //another list that stores the flanking sequences for each precursor
        String upstream = "";
        String downstream = "";
        String[] flankingSeqs = {upstream, downstream};
        //flankingSeqs = {upstream, downstream};
        //flankingSeqs[1] = downstream
        return flankingSeqs;
    }
    public static String map_precursors(ArrayList<String> precursors, ArrayList<String> sequences){
        String precursor = "";
        for (int i = 1; i<precursors.size(); i++){
            precursor = precursors.get(i);
            String [] flanks = getFlankingSeqs(precursor, 1);
            //code to align all miRNAs with the sequence - Bowtie 2; start off with distance of 0
        }
        return precursor;
    }
    
}