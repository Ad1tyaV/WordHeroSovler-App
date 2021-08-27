package com.wordheroapi.wordheroapi.Trie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InitTrie {
    
    private String URL;
    private TrieParent trieParent;
    private static InitTrie singletonInstance;
    private static boolean loadCompleted;

    private InitTrie(){
        URL = "https://raw.githubusercontent.com/Ad1tyaV/WordHero-solver/main/scrabbleWords.txt";
        loadCompleted = false;
    }

    public static InitTrie returnInitTrie(){
        if(singletonInstance==null){
            singletonInstance = new InitTrie();
        }
        return singletonInstance;
    }
    
    public void readFromFile() throws IOException{

      HttpClient client = HttpClient.newHttpClient();
      client.followRedirects();
      HttpRequest request = HttpRequest.newBuilder()
                            .GET()                            
                            .uri(URI.create(URL)).build();
    try{                            
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());        
        // System.out.println(response.body().toString());
        response.body().toString().lines().forEach(line->{
            trieParent = TrieParent.getInstance();
            trieParent.insertWord(line);
        });
    }
    catch(Exception exception){
        exception.printStackTrace();
    }

    // TrieParent trieParent = TrieParent.getInstance();
    // System.out.println("From main"+trieParent.hashCode());

    // trieParent.insertWord("test");
    // trieParent.insertWord("testing");
    
    loadCompleted = true; 
    // System.out.println("Loading words into Trie DONE!!!");
    
    // if(trieParent.findWord("TEST"))
    //     System.out.println("found test");
    // else
    //     System.out.println("Not found test");

    // if(trieParent.findWord("TESTING"))
    //     System.out.println("found testing");
    // else
    //     System.out.println("Not found testing");

    // if(trieParent.findWord("CEHCLING"))
    //     System.out.println("Found cehcling");
    // else
    //     System.out.println("Not found cehcling");

    }


    public boolean isLoadComplete(){
        return loadCompleted;
    }

    



}
