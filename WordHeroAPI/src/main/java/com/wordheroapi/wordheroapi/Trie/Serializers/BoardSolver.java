package com.wordheroapi.wordheroapi.Trie.Serializers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoardSolver {
    
    private Map<Integer, Set<String>> words;
    String errorMessage;

    public BoardSolver(){
        words = new HashMap<Integer, Set<String>>();
    }

    public BoardSolver(String errorMessage){
        words = new HashMap<Integer, Set<String>>();
        this.errorMessage = errorMessage;
    }

    public BoardSolver(List<String> allWords){

        
        this.words = new HashMap<Integer, Set<String>>();
        allWords.stream().forEach(word->{
            
            Set<String> change = words.get(word.length());            
            if(this.words.get(word.length())==null)
                change = new HashSet<String>();
            change.add(word);
            words.put(word.length(), change);            
        });

    }

    public BoardSolver(Map<Integer, Set<String>> words){
        this.words = words;
        this.errorMessage = null;
    }

    public Map<Integer, Set<String>> getWords(){
        return words;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

}
