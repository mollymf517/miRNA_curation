import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import java.io.FileNotFoundException;

public class curate {
    public static void main(String args[]) throws IOException{
        //System.out.println("Performing miRNA Curation.");
        //mapping.formatName("hsa-mir-551a");
        ArrayList<String> precursors = new ArrayList<>();
        ArrayList<String> formattedIds = new ArrayList<>();
        precursors = mapping.getPrecursors("/global/home/hpc4982/resources/miRNA_master.tsv");
     
        formattedIds = formatIds(precursors);
        ArrayList<String> testList = new ArrayList<>();
        testList.add(formattedIds.get(0));
        testList.add(formattedIds.get(1));
        testList.add(formattedIds.get(2));
        //getTranscriptIds(formattedIds);
        getTranscriptIds(testList);
        String genome = "precursors.fa";
        String mirs = "miRNAs_1.fq";
        performAlignment(mirs, genome, 0);
       // String[] testList = {mapping.formatName(precursors.get(1))};
       // getIDs.getTranscriptIds(testList);
       // mapping.getPrecursors("miRNA_master.tsv");
        //change to miRNAs_1.fastq when adding to CAC
        //mapping.getSequences("test.fastq");
    }
    private static ArrayList<String> formatIds(ArrayList<String> precursors){
        ArrayList<String> formattedIds = new ArrayList<>();
        for(int i=0; i<precursors.size(); i++){
            String unformatted = precursors.get(i);
            String formatted = mapping.formatName(unformatted);
            formattedIds.add(formatted);
        }
        return formattedIds;
    }
    //create a fasta file with all of the precursor sequences
    private static void getTranscriptIds(ArrayList<String> formatted) throws IOException{
        //create a list of transcript ids for each precursor
        String[] ids = getIDs.getTranscriptIds(formatted);
       // for(int i=0; i<ids.length;i++){
           // String line = "Transcript ID number " + ids[i] + ":";
           // System.out.println(line);
           // System.out.println(ids[i]);
       // }
       //add try catch to file creation
        File precursorSequences = new File("/global/home/hpc4982/curation_test/precursors.fa");
        precursorSequences.createNewFile();
        FileWriter writeSequences = new FileWriter("/global/home/hpc4982/curation_test/precursors.fa");
        String header = ">";
        for(int j=0; j<ids.length;j++){
            header = header + ids[j] + "\n";
            String sequence = getSequences.getSequence(ids[j]);
            System.out.println(sequence);
            writeSequences.write(header);
            writeSequences.write(sequence);
            writeSequences.write("\n");
            header = ">";
        }
        writeSequences.close();

    }
    //perform alignment with HISAT
    private static void performAlignment(String toAlign, String genome, int minScore){

    }
    

}
