import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import java.io.FileNotFoundException;

public class curate {
    public static void main(String args[]) throws IOException{
        //System.out.println("Performing miRNA Curation.");
        //mapping.formatName("hsa-mir-551a");
        ArrayList<Precursor> precursors = new ArrayList<>();
        //ArrayList<String> formattedIds = new ArrayList<>();
        precursors = mapping.getPrecursors("/global/home/hpc4982/resources/miRNA_master.tsv");
        //set transcriptId for each precursor
        getIDs.getTranscriptIds(precursors);
        //formatIds(precursors);
        ArrayList<Precursor> testList = new ArrayList<>();
        testList.add(precursors.get(0));
        testList.add(precursors.get(1));
        testList.add(precursors.get(2));
        //getTranscriptIds(formattedIds);
        getPrecursorSequences(testList);
        String genome = "precursors.fa";
        String mirs = "miRNAs_1.fq";
        performAlignment(mirs, genome, 0);
        performFolding("outputfile", "folder");
       // String[] testList = {mapping.formatName(precursors.get(1))};
       // getIDs.getTranscriptIds(testList);
       // mapping.getPrecursors("miRNA_master.tsv");
        //change to miRNAs_1.fastq when adding to CAC
        //mapping.getSequences("test.fastq");
    }
   // private static ArrayList<String> formatIds(ArrayList<Precursor> precursors){
    //    ArrayList<String> formattedIds = new ArrayList<>();
     //   for(int i=0; i<precursors.size(); i++){
     //       Precursor p = (precursors.get(i));
     //       mapping.formatName(p);
           // formattedIds.add(formatted);
     //   }
     //   return formattedIds;
  //  }
    //create a fasta file with all of the precursor sequences
    private static void getPrecursorSequences(ArrayList<Precursor> precursors) throws IOException{
        //create a list of transcript ids for each precursor
        //String[] ids = getIDs.getTranscriptIds(precursors);
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
        for(int j=0; j<precursors.size();j++){
            String transcript = (precursors.get(j)).transcriptId;
            header = header + transcript + "\n";
            String sequence = getSequences.getSequence(transcript);
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
       // Runtime r = Runtime.getRuntime();
            //String cmd = "/bin/sh /global/home/hpc4982/resources/path to script.sh " + script arguments;
            //have a folder where alignment ps files will go, then go through each one, or, we can get a given precursor name
            //File alignment = folder with alignment post script files.. from these, we will get the folding
            //System.out.println("command:");
            //System.out.println(cmd);
           // Process p = r.exec(cmd);
    }
    private static void performFolding(String alignmentOut, String outFolder){
        //run script that does RNAfold on the alignment file
        //for each ps file, convert ps to pdf
        //add try-catch for file not found exception
       // Runtime plot = Runtime.getRuntime();
       //String pltCmd= "Rnafold + args"
       //Process getPlot = plot.exec(pltCmd);

       //Runtime pdf = Runtime.getruntime();
       //loop through files in 
       //for each file
            // String pdfCmd = "ps2pdf " + "alignmentOut" + script arguments;
            // Process getPdf = pdf.exec(pdfCmd);
    }

}
