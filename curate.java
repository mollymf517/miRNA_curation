import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//import java.io.FileNotFoundException;

public class curate {
    public static void main(String args[]) throws IOException{
        //System.out.println("Performing miRNA Curation.");
        //String fastq = "fastqfilename";
        //ArrayList<Candidate> potential = new ArrayList<>();
        //potential = mapping.getCandidates(fastq);
        //load all the neccessary modules:
       // loadModules();
        String hgpath = "/global/home/hpc4982/resources/hg38.fa";
        Scanner dec = new Scanner(System.in);
        String decision;
        boolean yn = true;
        String prec_path = "/global/home/hpc4982/curation_test/precursors.fa";
        
        System.out.println("Generate FASTA for precursor sequences: yes or no");
        decision = dec.nextLine();
       
        switch(decision){
        case "yes":
            yn = true;
            System.out.println("Please specify the name of the fasta file to write the precursors to:");
            prec_path = "/global/home/hpc4982/curation_test/" + dec.nextLine();
            break;
        case "no": 
            System.out.println("Please specify the name of the fasta file containing the precursors:");
            prec_path = "/global/home/hpc4982/curation_test/" + dec.nextLine();
            yn = false;
            break;
        
    }
    //    dec.close();
        //if the precursor sequences need to be generated
        if(yn){
        //getPrecursorSequences(testList);
        ArrayList<Precursor> precursors = new ArrayList<>();
        //ArrayList<String> formattedIds = new ArrayList<>();
        precursors = mapping.getPrecursors("/global/home/hpc4982/resources/miRNA_master.tsv");
        //set transcriptId for each precursor
        getIDs.getTranscriptIds(precursors);
        ArrayList<Precursor> testList = new ArrayList<>();
        testList.add(precursors.get(0));
        testList.add(precursors.get(1));
        testList.add(precursors.get(2));
        testList.add(precursors.get(3));
        testList.add(precursors.get(4));
        testList.add(precursors.get(5));
        //getPrecursorSequences(precursors);
        getPrecursorSequences(testList, prec_path);
        }
        boolean build = false;
        System.out.println("Run build on human genome: yes or no");
        String buildchoice = "no";
       // Scanner build = new Scanner(System.in);
      
        buildchoice = dec.nextLine();
    
        switch(buildchoice){
        case "yes":
            build = true;
            break;
        case "no": 
            build = false;
            break;
        }
        
        dec.close();
        System.out.println("Scanner closed.");
        if(build){
            System.out.println("Running Build...");
            runBuild();
        }
        performAlignment(prec_path);
        performAlignment(hgpath);
        performFolding(prec_path);
        String ps_folder = "/global/home/hpc4982/folding";
        ps2pdf(ps_folder);
    }
    

    //create a fasta file with all of the precursor sequences
    private static void runBuild(){
        //code here to do alignment build
    }
    private static void getPrecursorSequences(ArrayList<Precursor> precursors, String precpath) throws IOException{
        
       //add try catch to file creation
      //  File precursorSequences = new File("/global/home/hpc4982/curation_test/precursors_testing.fa");
        //File precursorSequences = new File("/global/home/hpc4982/curation_test/precursors.fa");
        File precursorSequences = new File(precpath);
        try {
            precursorSequences.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create precursor file.");
        }
       
        //FileWriter writeSequences = new FileWriter("/global/home/hpc4982/curation_test/precursors_testing.fa");
        FileWriter writeSequences = new FileWriter(precpath);
        String header = ">";
        for(int j=0; j<precursors.size();j++){
            String transcript = (precursors.get(j)).transcriptId;
            if(j%50 == 0){
                String progress = "Getting trancript for precursor number ";
                progress = progress + j + ": " + transcript;
                System.out.println(progress);
                //System.out.println(transcript);
            }
            header = header + transcript + "\n";
            String sequence = getSequences.getSequence(transcript);
           // System.out.println(sequence.length());
            if(!transcript.isEmpty() && sequence.length()<200){
             //   System.out.println(sequence.length());
                writeSequences.write(header);
                writeSequences.write(sequence);
                writeSequences.write("\n");
            }
            header = ">";
            //System.out.println(sequence);
        }
        writeSequences.close();
        
    }
    //perform alignment with HISAT
    private static void performAlignment(String prec_file) throws IOException{
        try{
            Runtime r = Runtime.getRuntime();
            String cmd = "/bin/sh /global/home/hpc4982/curation_test/alignment/alignment.sh " + prec_file + " " + "'precursorIdx '" + "artificialSeq.fastq " + "'test.sam '";
            Process p = r.exec(cmd);
            System.out.println("Performing Alignment...");
            p.destroy();
        } catch (IOException e){
            System.out.println(e);
        }
   }
    private static void performFolding(String to_fold){
        try{
            Runtime r = Runtime.getRuntime();
            String cmd = "/bin/sh /global/home/hpc4982/folding/folding.sh " + to_fold;
            System.out.println("Folding precursors...");
            Process p = r.exec(cmd);
            p.destroy();
            } catch (IOException e){
                System.out.println(e);
            }
        }
    private static void ps2pdf(String ps_folder){
        File dir = new File(ps_folder);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File f : directoryListing) {
                String to_convert = f.getAbsolutePath();
                int lenConv = to_convert.length();
                String end = to_convert.substring(lenConv-3, lenConv-1);
                if(end.equals(".ps")){
                    try{
                        Runtime r = Runtime.getRuntime();
                        //String cmd = "/bin/sh /global/home/hpc4982/resources/preseq.sh " + transcriptId;
                        //String cmd = "/bin/sh /global/home/hpc4982/folding/ps2pdf.sh " + to_convert;
                        String cmd = "/bin/sh /global/home/hpc4982/folding/ps2pdf.sh " + to_convert;
                        //System.out.println("command:");
                        //System.out.println(cmd);
                        Process p = r.exec(cmd);
                        p.destroy();
                        } catch (IOException e){
                            System.out.println(e);
                        }
                }
            }
        }
    }
}
    
    //     String to_convert = "";
    //     try{
    //         Runtime r = Runtime.getRuntime();
    //         //String cmd = "/bin/sh /global/home/hpc4982/resources/preseq.sh " + transcriptId;
    //         String cmd = "/bin/sh /global/home/hpc4982/folding/ps2pdf.sh " + to_convert;
    //         //System.out.println("command:");
    //         //System.out.println(cmd);
    //         Process p = r.exec(cmd);
    //         p.destroy();
    //         } catch (IOException e){
    //             System.out.println(e);
    //         }
    // }

    // private static void loadModules(){
    //     try{
    //         Runtime r = Runtime.getRuntime();
    //         String cmd = "/bin/sh /global/home/hpc4982/resources/loadModules.sh";
    //         //System.out.println("command:");
    //         //System.out.println(cmd);
    //         Process p = r.exec(cmd);
    //         p.destroy();
    //         //System.out.println(p.exitValue());
            
    //     } catch (IOException e){
    //             System.out.println(e);
    //         }
    //     }

