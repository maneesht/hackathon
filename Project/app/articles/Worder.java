package word;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Worder {
    private int frequency;
    //public static void main(String[] args) {   
    //}
    List<String> listWords = new ArrayList<String>();
    public void String(){
        String temp="";
        
        BufferedReader read;
        String str = "";
        String []words;
        try {
            read = new BufferedReader(new FileReader("src/word/Ebola.txt"));
            String s;
            while((s = read.readLine()) != null) {
                str += s;
            }
        } catch (Exception e) {
            Logger.getLogger(Worder.class.getName()).log(Level.SEVERE, null, e);
        }
        words = str.split("\\. ");
        for(int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }
    
    
}
