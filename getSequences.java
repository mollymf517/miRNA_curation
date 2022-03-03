import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class getSequences {
    public static String getSequence(String transcriptId){
        String seq = "";
        //System.out.println("Transcript ID: ");
        System.out.println(transcriptId);
       
        try{
            Runtime r = Runtime.getRuntime();
            String cmd = "/bin/sh /global/home/hpc4982/resources/preseq.sh " + transcriptId;
            //System.out.println("command:");
            //System.out.println(cmd);
            Process p = r.exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine = "";

            while((inputLine = in.readLine()) != null){
                if(inputLine.isEmpty()){
                    System.out.println("empty");
                }
                else
                 //   System.out.println("Inputline: ");
                    //System.out.println(inputLine);
                    seq = seq + inputLine;
                   // System.out.println("Sequence: ");
                  //  System.out.println(seq);
             }


                in.close();
            } catch (IOException e){
                System.out.println(e);
            }

        return seq;
    }
}
