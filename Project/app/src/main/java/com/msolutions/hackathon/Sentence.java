package com.msolutions.hackathon;

/**
 * Created by Maneesh on 11/8/2014.
 */
import java.util.*;
public class Sentence {
    String sentence;
    List<Word> words = new ArrayList<Word>();
    private int rating = 0;
    String keyWords[] = {"large", "small", "great", "worse", "bad", "statistic", "most", "emergency"};

    public void setRating() {
        for(Word wordObj: words) {
            String word = wordObj.getWord().toLowerCase();
            for(String word2: keyWords) {
                if(word.contains(word2.toLowerCase())) {
                    rating++;
                }
            }
        }
    }

    public void setRatingInt(int rating ) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public Sentence(List<Word> temp, String sent){
        this.words=temp;
        this.sentence=sent;
        setRating();
    }
}