public class Candidate {
    public String sequence;
    public enum RnaType{
        miRNA, 
        snoRNA,
        tRNA
    }
    public RnaType type;


    public Candidate(String seq){
        this.sequence = seq;
        this.type = RnaType.miRNA;
    }
    public String getSequence(){
        return this.sequence;
    }
    public RnaType getType(){
        return this.type;
    }
   
}
