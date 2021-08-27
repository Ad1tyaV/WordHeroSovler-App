package com.wordheroapi.wordheroapi.Trie.Serializers;

public class WordExistence {
    
    private String word;
    private boolean existence;

    public WordExistence(String word, boolean existence){
        this.word = word;
        this.existence = existence;
    }

    public String getWord(){
        return word;
    }

    public boolean getExistence(){
        return existence;
    }

}
