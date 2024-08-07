package com.asgteach.service;

import com.asgteach.WordishApp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WordData {
    
    private static WordData instance = null;

    private String theWord = "";
    private Set<Integer> usedIndices = new HashSet<>();
    private List<String> words;
    private Random rand = new Random();

    private WordData() {
        System.out.println("building word data");
        try (InputStream is = WordishApp.class.getResourceAsStream("/com/asgteach/service/wordlist.txt")) {
            words = new BufferedReader(
                    new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
    }
    
    public static WordData getInstance() {
        if (instance == null) {
            instance = new WordData();
        }
        return instance;
    }

    public String getTheWord() {
        return theWord;
    }
    
    public boolean isAWord(String guess) {
        return hasDistinctCharacters(guess);
//        return words.stream().anyMatch(w -> w.equals(guess));
    }

    int index;
    public void setNewWord() {   
        do {
//            index = rand.nextInt(words.size());
            theWord = getRandomNumber();
            index = Integer.parseInt(theWord);
        } while (!usedIndices.add(index));

//        theWord = words.get(index);
        System.out.println("theWord = " + theWord + ", index =" + index);
    }

    private String getRandomNumber() {
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < 5) {
            set.add(rand.nextInt(10));
        }
        return set.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

    }

    private boolean hasDistinctCharacters(String word) {
        return word.chars().distinct().count() == word.length();
    }

}
