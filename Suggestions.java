package word;

import java.io.*;
import java.util.*;


public class Suggestions{
    
    public static void main (String args[]) throws FileNotFoundException, IOException{
        boolean check;
        List<String> al = new ArrayList<String>();
        Scanner s = new Scanner(System.in);
        String st = s.nextLine();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src\\word\\diction.txt"), "UTF-8"));
        String str;
        while ((str = in.readLine()) != null) {
            if(str.contains(st)){
                al.add(str);
            }   
        }
        
        for(int x=0;x<5;x++){
            String clean ="";
            clean=al.get(x);
            int tempLength=clean.length();
            clean=clean.replaceAll(",", "");
            al.set(x,clean);
            }
        for(int x=0;x<2;x++){
        System.out.println(al.get(x));
        }
    }

    
    

}
