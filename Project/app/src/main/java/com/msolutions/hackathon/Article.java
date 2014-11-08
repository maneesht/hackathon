package com.msolutions.hackathon;

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
    public void String(){
        String temp="";

        BufferedReader read;
        String str = "";
        String []sentences;
        try {
            read = new BufferedReader(new FileReader("src/word/Ebola.txt"));
            String s;
            while((s = read.readLine()) != null) {
                str += s;
            }
        } catch (Exception e) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, e);
        }
        sentences = str.split("\\. ");
        for(int i = 0; i < sentences.length; i++) {
            //System.out.println(words[i]);
            ArrayList<Word> words = new ArrayList<Word>();
            String wordsInSentence[] = sentences[i].split(" ");
            int hits = 0;
            for(String word: wordsInSentence) {
                Word wordObj = new Word();
                wordObj.setWord(word);
                hits = checkFrequency(sentences, word);
                wordObj.setHits(hits);
                words.add(wordObj);
            }
            Sentence sentence = new Sentence(sentences[i], words);
        }

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

