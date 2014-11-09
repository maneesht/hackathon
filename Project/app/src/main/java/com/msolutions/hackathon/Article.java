package com.msolutions.hackathon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maneesh on 11/8/2014.
 */
public class Article {
    List<Sentence> sentences = new ArrayList<Sentence>();
    String str = "";
    ArrayList<Word> words;
    String []sentences2;
    String wordsInSentence[];
    public Article(String article) {
        str = article;
    }
    public void init(){
        sentences2 = str.split("\\. ");
        for(int i = 0; i < sentences2.length; i++) {
            words = new ArrayList<Word>();
            wordsInSentence = sentences2[i].split(" ");
            for(String word: wordsInSentence) {
                Word wordObj = new Word();
                wordObj.setWord(word);
                wordObj.setHits(checkFrequency(sentences2, word));
                words.add(wordObj);
            }
            Sentence sentence = new Sentence(words, sentences2[i]);
            sentences.add(sentence);
        }
    }

    public String getWholeArticle() {
        return str;
    }

    public String getSummary() {
        String s = "";
        init();
        int count = 0;
        sentences = sortRatings(sentences);
        for(Sentence sentence : sentences) {
            if(sentence.getRating() > 0) {
                s+= sentence.sentence + ". ";
                count++;
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

