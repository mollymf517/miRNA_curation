

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Hashtable;


public class miRNA {
	
	public static File get_rna_types(String filename){
		//String file = "C:/Users/chloe/OneDrive/Documents/School/4th Year/WINTER/CISC 499/geneNames.txt";
		
		ArrayList<String> geneNamesArray = new ArrayList<String>();;

        try {
            File geneNames = new File(filename);
            Scanner read = new Scanner(geneNames);
            while (read.hasNextLine()){
                String line = read.nextLine();
                geneNamesArray.add(line);
                // append to array
            }
            read.close();
        } catch (FileNotFoundException e){
            //System.out.printlin("File " + file + " not found.");
            e.printStackTrace();
        }
        
        geneNamesArray.remove(0);


        Hashtable<String, String> ncRNAs = new Hashtable<String, String>();

        ncRNAs.put("MIR", "miRNA");
        ncRNAs.put("NMTR", "tRNA");
        ncRNAs.put("TR", "tRNA"); //seems too short, could get other genes
        ncRNAs.put("MT-T", "tRNA");
        ncRNAs.put("SNORD", "snoRNA");
        ncRNAs.put("SNORA", "snoRNA");
        ncRNAs.put("SCARN", "snoRNA");
        ncRNAs.put("MT-RNR", "rRNA");
        ncRNAs.put("RNA", "rRNA"); // should ensure other gene types don't start with RNA
        ncRNAs.put("RNU", "snRNA");
        ncRNAs.put("RNVU", "snRNA");
        ncRNAs.put("other", "other");

        // dictionary types is used to count how many there are of each type of ncRNA
        Hashtable<String, Integer> types = new Hashtable<String, Integer>();

        types.put("miRNA", 0);
        types.put("tRNA", 0);
        types.put("rRNA", 0);
        types.put("snRNA", 0);
        types.put("snoRNA", 0);
        types.put("other", 0);



        
        for(int i = 0; i < geneNamesArray.size(); i++){

            String geneType;
            geneType = "";

            while (geneType == ""){
                for (int j = 2; (j < 6) && (j  < geneNamesArray.get(i).length()); j++){
                    String gene = geneNamesArray.get(i).substring(0, j);
                    if (ncRNAs.containsKey(gene)){
                        geneType = ncRNAs.get(gene);
                    }
                }
                if (geneType == ""){
                    geneType  = "other";
                }
                types.put(geneType, types.get(geneType) + 1);
            }
        }

        int num_miRNA = types.get("miRNA");
        int num_tRNA = types.get("tRNA");
        int num_rRNA = types.get("rRNA");
        int num_snRNA = types.get("snRNA");
        int num_snoRNA = types.get("snoRNA");
        int num_other = types.get("other");

        int totalNum = num_miRNA + num_tRNA + num_rRNA + num_snRNA + num_snoRNA + num_other;

        double percent_miRNA = (num_miRNA * 100/ totalNum);
        double percent_tRNA = (num_tRNA * 100/ totalNum);
        double percent_rRNA = (num_rRNA * 100/ totalNum);
        double percent_snRNA = (num_snRNA * 100/ totalNum);
        double percent_snoRNA = (num_snoRNA * 100/ totalNum);
        double percent_other = (num_other * 100/ totalNum);

        	
        File fileOut = null;
        FileWriter filewriter = null;
        String heading = "Total Reads, rRNA, % rRNA, snoRNA, % snoRNA, snRNA, % snRNA, tRNA, %tRNA, other, % other, miRNA, % miRNA";
        String data = totalNum + "," + num_rRNA + "," + percent_rRNA +"," + num_snoRNA + "," + percent_snoRNA + "," + num_snRNA + "," + percent_snRNA + "," + num_tRNA + "," + percent_tRNA + "," + num_other + "," + percent_other + "," + num_miRNA + "," + percent_miRNA;
        
        
        try {
        	fileOut = new File("/global/home/hpc4982/submissionFiles/rnaAmt.csv");
        	filewriter = new FileWriter(fileOut);
        	
        	filewriter.write(heading);
        	filewriter.write("\r\n");
        	filewriter.write(data);
        	
        	filewriter.close();
        	
        	
        }
        catch (Exception e) {
        	e.printStackTrace();
        } 
        
        return fileOut;
        
        
        
        
		
	}
	// public static void main(String[] args) {
	// 	get_rna_types();
	// }
	
	
	

}

