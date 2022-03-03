import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getIDs{
    public static String[] getTranscriptIds(ArrayList<String> precursors) throws IOException{
        //takes list of precursors and gets all transcript ids adding them to a list
        //boolean windows = System.getProperty("os.name").toLowerCase().startsWith("windows");
       //change it to return string [] after test
       // String testid = "failed";
        int numPrecursors = precursors.size();
        String[] ids = new String[numPrecursors];
        //Pattern pattern = Pattern.compile("\".*\"");
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        for(int i=0; i<numPrecursors;i++){
            String geneName = precursors.get(i);
           // System.out.println("gene name: ");
           // System.out.println(geneName);
            String id_o = null;
            try{
                Runtime r = Runtime.getRuntime();
                String cmd = "/bin/sh /global/home/hpc4982/resources/transcriptid.sh " + geneName;
               // System.out.println("command:");
               // System.out.println(cmd);
                Process p = r.exec(cmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String inputLine = null;
               // int lineCount = 1;
                while((inputLine = in.readLine()) != null){
                   // String parts = inputLine.split(";")
                   // System.out.println("entering while.");
                   // System.out.println("Line number: ");
                  //  System.out.println(lineCount);
                   // System.out.println(inputLine);
                   //if(inputLine.equals("")){
                       // String testop = "empty: " + inputLine + " .";
                        // System.out.println("empty: testline: ");
                        // System.out.println(inputLine);
                        // id="error";
                        // break;
                   //}
                    // else{
                    //     id = inputLine;
                    // }
                    id_o = inputLine;
                    //System.out.println("id: ");
                   // System.out.println(ids);
                   // lineCount+=1;
                }
                //ids[i] = id;
                Matcher matcher = pattern.matcher(id_o);
                //System.out.println("id: ");
               // System.out.println(ids);
               // testid = "error.";
                if(matcher.find()) {
             //       System.out.println("Match:");
                    String id = matcher.group(1);
              //      System.out.println(matcher.group(1));
                    ids[i] = id;
                }
                else{
                    System.out.println("Match not found.");
                }
                in.close();
            } catch (IOException e){
                System.out.println(e);
            }
           // String [] cmd = new String[]{"/bin/sh", "/global/home/hpc3982/resources/transcriptid.sh", geneName};
    
           // Process p = new ProcessBuilder("./transcriptid.sh", geneName).start();
           // Process p = Runtime.getRuntime().exec(cmd);
           // InputStream in = p.getInputStream();
          //  int c;
          //  while ((c = in.read()) != -1) {
           //     p((char)c);
          //  }
         //   in.close();
         //   System.out.println(p);
            
          //  ids[i] = p[0];
        }
        return ids;
    }
    
}