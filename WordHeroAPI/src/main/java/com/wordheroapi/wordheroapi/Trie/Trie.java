package com.wordheroapi.wordheroapi.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair{
    private int first;
    private int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }

    int getFirst(){
        return first;
    }

    int getSecond(){
        return second;
    }
}

class TrieParent{
    static Map<Character, TrieNode> parentLinks;
    static TrieParent singletonInstance;
    List<String> words;
    boolean[][] visited;
    // List<ArrayList<Character>> board;
    char[][] board;
    List<Pair> directions;
    int ROWS = 4;
    int COLS = 4;
    

    private TrieParent(){        
        parentLinks = new HashMap<Character, TrieNode>();
        words = new ArrayList<String>();
        visited = new boolean[4][4];
        // board = new ArrayList<ArrayList<Character>>();        
        board = new char[ROWS][COLS];

        directions = new ArrayList<Pair>();
        directions.add(new Pair(1,0));
        directions.add(new Pair(0,1));
        directions.add(new Pair(-1,0));
        directions.add(new Pair(0,-1));
        directions.add(new Pair(1,1));
        directions.add(new Pair(-1,-1));
        directions.add(new Pair(1,-1));
        directions.add(new Pair(-1,1));        

    }

    // List<String> generateAllWordsAdapter(List<ArrayList<Character>> board){
    List<String> generateAllWordsAdapter(char[][] board){
        this.board = board;
        words = new ArrayList<String>();
        for(int row = 0; row<4; row++){
            for(int col = 0; col<4; col++){                
                // generateAllWords(board, row, col, parentLinks.get(board.get(row).get(col)), new StringBuilder(""));                
                // System.out.println("row="+row+" col="+col+" board[row][col]="+board[row][col]+" parentLinks size? "+parentLinks.size());                
                visited = new boolean[4][4];
                generateAllWords(board, row, col, parentLinks.get(board[row][col]), new StringBuilder(""+board[row][col]));
            }
        }        
        return words;
    }

    // void generateAllWords(List<ArrayList<Character>> board, int xPos, int yPos, TrieNode crawl, StringBuilder word){
    void generateAllWords(char[][] board, int xPos, int yPos, TrieNode crawl, StringBuilder word){
                

        if(crawl.isLeaf){
            // System.out.println("word="+word);
            words.add(word.toString());            
        }

        // System.out.println("xPos="+xPos+" yPos="+yPos);
        
        
        if(xPos>=ROWS || xPos<0)
            return;
        
        if(yPos<0 || yPos>=COLS)
            return;

        // System.out.println("Not here ?");
        
        if(visited[xPos][yPos])
            return;
        
        

        visited[xPos][yPos] = true;

        for (Pair direction : directions) {
            
            int next_x = xPos + direction.getFirst();
            int next_y = yPos + direction.getSecond();            

            if(next_x>=ROWS || next_x<0)
                continue;

            if(next_y>=COLS || next_y<0)
                continue;

            if(visited[next_x][next_y])
                continue;
            
            if(crawl.nextLink.get(board[next_x][next_y])==null){
                continue;
            }
            else{                
                word.append(board[next_x][next_y]);                               
                generateAllWords(board, next_x, next_y, crawl.nextLink.get(board[next_x][next_y]), word);
                word.deleteCharAt(word.length()-1);
            }
            

        }

        visited[xPos][yPos] = false;


    }

    public static TrieParent getInstance(){
        if(singletonInstance==null)
            singletonInstance = new TrieParent();
        return singletonInstance;        
    }

    public Boolean findWord(String word){

        char firstLetter = word.charAt(0);        
        TrieNode crawl = TrieParent.parentLinks.get(firstLetter);        
        
        if(crawl==null){            
            return false;
        }
        
        for(int index = 1; index<word.length(); index++){

            if(crawl==null)
                return false;

            crawl = crawl.nextLink.get(word.charAt(index));            
        }

        return crawl.isLeaf;

    }

    public void insertWord(String word){

        
        int index = 0;
        TrieNode crawl = TrieParent.parentLinks.get(word.charAt(index));        

        if(crawl==null){
                                
            TrieParent.parentLinks.put(word.charAt(index), new TrieNode(word.charAt(index)));
        }        

        crawl = TrieParent.parentLinks.get(word.charAt(index++));
        
        
        while(index<word.length()){            

            if(crawl.nextLink.get(word.charAt(index))==null){                
                crawl.nextLink.put(word.charAt(index), new TrieNode(word.charAt(index)));
            }
            crawl = crawl.nextLink.get(word.charAt(index++));
            
        }        
        crawl.isLeaf = true;
    }

    public List<String> generateWordsAdapter(char letter){

        words = new ArrayList<String>();        
        generateWords(parentLinks.get(letter), new StringBuilder(""));
        return words;

    }

    public void generateWords(TrieNode letter, StringBuilder word){        
        
        if(letter.isLeaf){
            word.append(letter.thisCharacter);
            words.add(word.toString());

            for(Character character:letter.nextLink.keySet()){
                generateWords(letter.nextLink.get(character), word);                
            }
            word.deleteCharAt(word.length()-1);
            
        }
        else{
            word.append(letter.thisCharacter);
            for(Character character : letter.nextLink.keySet()){
                generateWords(letter.nextLink.get(character), word);                
            }
            word.deleteCharAt(word.length()-1);
        }


    }

}

class TrieNode{

    Character thisCharacter;
    Map<Character, TrieNode> nextLink;
    boolean isLeaf;

    TrieNode(Character thisCharacter){
        this.thisCharacter = thisCharacter;
        nextLink = new HashMap<Character, TrieNode>();
        this.isLeaf = false;
    }

}