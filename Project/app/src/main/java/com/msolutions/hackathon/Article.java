package com.msolutions.hackathon;

import android.util.Log;

import java.util.List;

/**
 * Created by Maneesh on 11/8/2014.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Article {
    private int frequency;
    //public static void main(String[] args) {
    //}
    List<String> listWords = new ArrayList<String>();
    List<Sentence> sentences = new ArrayList<Sentence>();
    String str = "";

    String []sentences2;

    public Article(String article) {
        str = article;
    }
    public void init(){
        String temp="";

        sentences2 = str.split("\\. ");
        for(int i = 0; i < sentences2.length; i++) {
            //System.out.println(words[i]);
            ArrayList<Word> words = new ArrayList<Word>();
            String wordsInSentence[] = sentences2[i].split(" ");
            int hits = 0;
            for(String word: wordsInSentence) {
                Word wordObj = new Word();
                wordObj.setWord(word);
                hits = checkFrequency(sentences2, word);
                wordObj.setHits(hits);
                words.add(wordObj);
            }
            Sentence sentence = new Sentence(words, sentences2[i]);
            this.sentences.add(sentence);
        }
    }

    public String getSummary() {
        String s = "";
        init();

        sentences = sortRatings(sentences);
        for(Sentence sentence : sentences) {
            if(sentence.getRating() > 0) {
                Log.d("Rating", sentence.sentence);
            }
        }
        return s;
    }
    public List<Sentence> sortRatings(List<Sentence> sentences)
    {
        Sentence holder=new Sentence();
        boolean flag=true;
        while(flag) {
            for (int x = 0; x < sentences.size() - 1; x++) {
                flag = false;
                if (sentences.get(x).getRating() < sentences.get(x + 1).getRating()) {
                    holder = sentences.get(x);
                    sentences.set(x, sentences.get(x + 1));
                    sentences.set(x + 1, holder);
                    flag = true;
                }
            }
        }
        return sentences;
    }
    public int checkFrequency(String sentences[], String word) {
        int frequency = 0;
        for(String sentence : sentences) {
            sentence = sentence.replaceAll(".", "");
            sentence = sentence.replaceAll("\\&", "");
            sentence = sentence.replaceAll(",","");
            if(sentence.equalsIgnoreCase(word))
                frequency++;
        }
        return frequency;
    }


}

