//import java.io.FileNotFoundException;

public class curate {
    public static void main(String args[]){
        System.out.println("Performing miRNA Curation.");
        mapping.getPrecursors("miRNA_master.tsv");
        //change to miRNAs_1.fastq when adding to CAC
        mapping.getSequences("test.fastq");
    }
}
