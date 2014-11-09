package word;
import java.util.*;
public class Sentence {
    String sentence;
    List<String> words = new ArrayList<String>();
    List<Word> wordsObj = new ArrayList<Word>();
    public Sentence(){
        
    }
    public Sentence(List<String> temp, String Sent){
        this.words=temp;
        this.sentence=Sent;
        for(int x=0;x<words.size();x++)
        {
            Word temper=new Word(words.get(x));
            wordsObj.add(temper);
        }
    }
}
