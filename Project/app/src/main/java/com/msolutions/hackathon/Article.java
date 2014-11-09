package com.msolutions.hackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    InputStream is;
    public Article(String article, InputStream is) {
        str = article;
        this.is = is;
        str=str.replaceAll("\\p{Punct}\\d","");
    }
    public void init(){
        sentences2 = str.split("\\. ");
        for(int i = 0; i < sentences2.length; i++) {
            words = new ArrayList<Word>();
            wordsInSentence = sentences2[i].split(" ");
            for(String word: wordsInSentence) {
                Word wordObj = new Word();
                wordObj.setWord(word);
                wordObj.setHits(1);
                contains(wordObj);
            }
            Sentence sentence = new Sentence(words, sentences2[i]);
            sentences.add(sentence);
        }
    }

    public void contains(Word word) {
        boolean flag = false;
        for(int i = 0; i< words.size(); i++) {
            if(words.get(i).getWord().toLowerCase().contains(word.getWord().toLowerCase())) {
                Word w = words.get(i);
                w.setHits(words.get(i).getHits() + 1);
                words.set(i,w);
                flag = true;
            }
        }
        if(!flag) {
            words.add(word);
        }
    }

    public String getWholeArticle() {
        return str;
    }

    public String getSummary() {
        String s = "";
        init();

        sentences = sortRatings(sentences);
        for(Sentence sentence : sentences) {
            if(sentence.getRating() > 0)
                s+= sentence.sentence + ". ";
        }
        if(s.length() <= 1) {
            sentences = sortFrequency(sentences);
            sentences = sortRatings(sentences);
            for(Sentence sentence : sentences) {
                if(sentence.getRating() > 0)
                    s+= sentence.sentence + ". ";
            }
        }
        return s;
    }

    public List<Sentence> sortFrequency(List<Sentence> sentences) {
            for (int j = 0; j < sentences.size(); j++) {
                for(int i = 0; i < sentences.get(j).words.size(); i++) {
                    if (sentences.get(j).words.get(i).getHits() >= 1) {
                        Sentence s = sentences.get(j);
                        s.setRatingInt(s.getRating()+1);
                        sentences.set(j, s);
                    }
                }
            }
        return sentences;
    }

    public List<Sentence> sortRatings(List<Sentence> sentences)
    {
        Sentence holder;
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
    /*public int checkFrequency(String article, String word) {
        int frequency = 0;
        BufferedReader read;
        read = new BufferedReader(new InputStreamReader(is));
        String s;
        try {
            while((s = read.readLine()) != null) {
                String [] tokens = s.split("\\s+");
                for(String token : tokens) {
                    if(token.toLowerCase().contains(word)) {
                        frequency++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequency;
    }*/
}

