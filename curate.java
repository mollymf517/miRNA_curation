import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import java.io.FileNotFoundException;

public class curate {
    public static void main(String args[]) throws IOException{
        //System.out.println("Performing miRNA Curation.");
        //mapping.formatName("hsa-mir-551a");
        //String fastq = "fastqfilename";
        //ArrayList<Candidate> potential = new ArrayList<>();
        //potential = mapping.getCandidates(fastq);
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
        testList.add(precursors.get(3));
        testList.add(precursors.get(4));
        testList.add(precursors.get(5));
        testList.add(precursors.get(6));
        testList.add(precursors.get(7));
        testList.add(precursors.get(8));
        testList.add(precursors.get(9));
        testList.add(precursors.get(10));
        testList.add(precursors.get(11));
        testList.add(precursors.get(12));
       // getIDs.getTranscriptIds(precursors);
        //getIDs.getTranscriptIds(testList);
        // Thread transid = new Thread(()-> {
        //     try {
        //         getIDs.getTranscriptIds(precursors);
        //     } catch (IOException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // });
        // transid.start();
        //formatIds(precursors);
        
        //getTranscriptIds(formattedIds);
        //load all the neccessary modules:
       // loadModules();
        //getPrecursorSequences(testList);
        getPrecursorSequences(precursors);
      //  String genome = "precursors.fa";
      //  String mirs = "miRNAs_1.fq";
      //  String op = "alignment.sam";
       // performAlignment(mirs, genome, op);
       // performFolding("outputfile", "folder");
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
        File precursorSequences = new File("/global/home/hpc4982/curation_test/precursors_testing.fa");
        try {
            precursorSequences.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create precursor file.");
        }
       
        FileWriter writeSequences = new FileWriter("/global/home/hpc4982/curation_test/precursors_testing.fa");
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
        performAlignment();
        performFolding("/global/home/hpc4982/curation_test/precursors.fa");
        String ps_folder = "";
        ps2pdf(ps_folder);
    }
    //perform alignment with HISAT
    private static void performAlignment() throws IOException{
        //possible input: String toAlign, String genome, String out
        //maybe prompt user for minimum score: 'Would you like to specify a minimum alignment score [y/n]:'
        //"Enter minimum alignment score: "
        Runtime r = Runtime.getRuntime();
        
            String cmd = "/bin/sh /global/home/hpc4982/curation_test/alignment/alignment.sh" + "alignment.sh " + "precursors.fa " + "'precursorIdx '" + "artificialSeq.fastq " + "'test.sam '";
            //have a folder where alignment ps files will go, then go through each one, or, we can get a given precursor name
            //File alignment = folder with alignment post script files.. from these, we will get the folding
            //System.out.println("command:");
            //System.out.println(cmd);
            Process p = r.exec(cmd);
            p.destroy();
   }
    private static void performFolding(String to_fold){
        try{
            Runtime r = Runtime.getRuntime();
            //String cmd = "/bin/sh /global/home/hpc4982/resources/preseq.sh " + transcriptId;
            String cmd = "/bin/sh /global/home/hpc4982/folding/folding.sh " + to_fold;
            //System.out.println("command:");
            //System.out.println(cmd);
            Process p = r.exec(cmd);
            p.destroy();
            } catch (IOException e){
                System.out.println(e);
            }
          
        }
    private static void ps2pdf(String ps_folder){
        String to_convert = "";
        try{
            Runtime r = Runtime.getRuntime();
            //String cmd = "/bin/sh /global/home/hpc4982/resources/preseq.sh " + transcriptId;
            String cmd = "/bin/sh /global/home/hpc4982/folding/ps2pdf.sh " + to_convert;
            //System.out.println("command:");
            //System.out.println(cmd);
            Process p = r.exec(cmd);
            p.destroy();
            } catch (IOException e){
                System.out.println(e);
            }

    }
        //Possible input: String alignmentOut, String outFolder
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

