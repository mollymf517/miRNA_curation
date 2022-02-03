import java.io.FileNotFoundException;

public class curate {
    public static void main(String args[]){
        System.out.println("Performing miRNA Curation.");
        try {
            mapping.precursor_mapping("miRNA_master.tsv");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
