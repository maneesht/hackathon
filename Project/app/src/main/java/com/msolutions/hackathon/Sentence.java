package com.msolutions.hackathon;

/**
 * Created by Maneesh on 11/8/2014.
 */
import java.util.*;
public class Sentence {
    String sentence;
    List<Word> words = new ArrayList<Word>();
    private int rating;
    String keyWords[] = {"larg", "small", "great", "worse", "bad", "statistic"};
    public Sentence(){
        setRating();
    }

    public void setRating() {
        for(Word wordObj: words) {
            String word = wordObj.getWord();
            for(String word2: keyWords) {
                if(word.contains(word2.toLowerCase())) {
                    rating++;
                }
            }
        }
    }

    public int getRating() {
        return rating;
    }

    public Sentence(List<Word> temp, String sent){
        this.words=temp;
        this.sentence=sent;

    }
}