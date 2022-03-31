import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


//To do:
// Convert precursor name to transcript/gene id
// search in hg38 for other ID, add to file
// loop through file of precursor IDs
// find precursors in the blast database using the transcript and get their full sequence
// run bowtie2 with precursor sequence and miRNAs
//
//
public class mapping {
    public static ArrayList<Candidate> getCandidates(String fastq){
        //read in miRNAs from fastq file
        //add them to an arraylist for alignment with each precursor sequence
        ArrayList<Candidate> mi_RNAs = new ArrayList<>();
        try(BufferedReader read_seqs = new BufferedReader(new FileReader(fastq))){
            String line = null;
            int lineCount = 1;
            while ((line = read_seqs.readLine()) != null){
                //String[] data = line.split("\t");
                if (lineCount == 2){
                    //System.out.println("Sequence found: ");
                    Candidate c = new Candidate(line);
                    mi_RNAs.add(c);
                    //System.out.println(line);
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
    public static ArrayList<Precursor> getPrecursors(String filename) {
         //ArrayList<String> precursors = new ArrayList<>();
         ArrayList<Precursor> precursors = new ArrayList<>();
         try(BufferedReader read_precursors = new BufferedReader(new FileReader(filename))){
             String line = null;
             while ((line = read_precursors.readLine()) != null){
                 String[] data = line.split("\t");
                 String name = data[1];
                 Precursor p = new Precursor(name);
                 formatName(p);
                 //precursors.add(formatName(name));
                 precursors.add(p);
                 //precursors.add(data[2]);
                // System.out.println(data[1]);
             }
         } catch (Exception e) {
             System.out.println("Error.");
         }
        //remove the first item, which is the column name
        precursors.remove(0);
        
        return precursors;
    }

    public static void formatName(Precursor precursor){
        //181A2 and 181B2 look like 181a-2 and 181-a3
        String name = precursor.name;
        String formatted = "";
        formatted = name.replaceAll("hsa-", "");
        formatted = formatted.replaceAll("let-", "mirlet");
        formatted = formatted.replaceAll("mir-", "mir");
        formatted = formatted.replaceAll("-[1-9]SPLICE", "");
        formatted = formatted.replaceAll("-MIRTRON", "");
        formatted = formatted.replaceAll("-DICER", "");
        formatted = formatted.replaceAll("-PAT", "");
        formatted = formatted.replaceAll("-RNASEN", "");
        formatted = formatted.replaceAll("a-", "a");
        formatted = formatted.replaceAll("b-", "b");
        formatted = formatted.replaceAll("f-", "f");
        formatted = formatted.replaceAll("-A6G", "");
       // formatted = formatted.replace("-[^-]+$","");
        formatted = formatted.replaceAll("-$","");

        formatted = formatted.toUpperCase();
        precursor.geneId = formatted;
        //System.out.println("formatted: ");
        //System.out.println(formatted);
        //return formatted;
    }
    // public static String[] getFlankingSeqs(String precursor, int seqSize){
    //     //when do we want to get flanking sequences? One at a time, or do we want
    //     //another list that stores the flanking sequences for each precursor
    //     String upstream = "";
    //     String downstream = "";
    //     String[] flankingSeqs = {upstream, downstream};
    //     //flankingSeqs = {upstream, downstream};
    //     //flankingSeqs[1] = downstream
    //     return flankingSeqs;
    // }
    
    // public static String map_precursors(ArrayList<String> precursors, ArrayList<String> sequences){
    //     String precursor = "";
    //     for (int i = 1; i<precursors.size(); i++){
    //         precursor = precursors.get(i);
    //         String [] flanks = getFlankingSeqs(precursor, 1);
    //         System.out.println(flanks);
    //         //code to align all miRNAs with the sequence - Bowtie 2; start off with distance of 0
    //     }
    //     return precursor;
    // }
    
}