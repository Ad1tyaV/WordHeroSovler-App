package com.wordheroapi.wordheroapi.Trie.Serializers;

import java.util.ArrayList;
import java.util.List;

public class ListOfWords {
    
    private char letter;
    private List<String> words;
    
    public ListOfWords(){
        words = new ArrayList<String>();
    }

    public ListOfWords(char letter, List<String> words){   
        this.letter = letter;
        this.words = words;
    }

    public List<String> getWords(){
        return words;
    }

    public char getLetter(){
        return letter;
    }

}
