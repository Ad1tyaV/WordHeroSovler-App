package com.wordheroapi.wordheroapi.Trie;

import java.io.IOException;
import java.util.List;

import com.wordheroapi.wordheroapi.ErrorHandler.InvalidInputRequestException;
import com.wordheroapi.wordheroapi.Trie.Serializers.BoardSolver;
import com.wordheroapi.wordheroapi.Trie.Serializers.ListOfWords;
import com.wordheroapi.wordheroapi.Trie.Serializers.WordExistence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrieQueries {
    
    InitTrie trie;
    int ROWS = 4;
    int COLS = 4;

    boolean isNotValid(String word){

        // if(word.length()>15 || word.length()==1)
        //     return true;

        if(word.length()<15 && word.length()!=1)
            return false;
        
        for(int index=0; index<word.length(); index++){         

            int asciiValue = (int) word.charAt(index);

            if(asciiValue>=48 && asciiValue<=57)
                return true;

        }

        return false;
    }

    @GetMapping(path = "/checkWord")
    public @ResponseBody WordExistence findWord(@RequestParam String word) throws IOException{        

        if(isNotValid(word))
            throw new InvalidInputRequestException("Try another word!");

        

        trie = InitTrie.returnInitTrie();
        if(!trie.isLoadComplete()){
            trie.readFromFile();
        }
        TrieParent instance = TrieParent.getInstance();
        
        if(instance.findWord(word.toUpperCase())){
            return new WordExistence(word, true);
        }

        return new WordExistence(word, false);
    }

    @GetMapping(path = "/startLetter")
    public @ResponseBody ListOfWords getStartWords(@RequestParam String letter) throws IOException{

        // if(isNotValid(letter))        
        //     throw new InvalidInputException("Try another word!");

        if(!isNotValid(letter))
            throw new InvalidInputRequestException("Something went Wrong");

        trie = InitTrie.returnInitTrie();
        if(!trie.isLoadComplete()){
            trie.readFromFile();
        }

        TrieParent instance = TrieParent.getInstance();
        
        return new ListOfWords(letter.charAt(0), instance.generateWordsAdapter(letter.charAt(0)));
        // return new ListOfWords(letter, instance.generateWordsAdapter(letter));

    }

    @PostMapping("/solveBoard/")
    public @ResponseBody BoardSolver solveBoard(@RequestBody String boardData) throws IOException{


        // System.out.println(boardData);
        // List<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();

        if(isNotValid(boardData))
            return new BoardSolver("Invalid Input");

        trie = InitTrie.returnInitTrie();
        if(!trie.isLoadComplete()){
            trie.readFromFile();
        }

        TrieParent instance = TrieParent.getInstance();
        char[][] board = new char[ROWS][COLS];
        int pointer = 0;

        for(int row = 0; row<ROWS; row++){
            for(int col = 0; col<COLS; col++){
                // board.get(row).set(col, boardData.charAt(pointer++));                
                board[row][col] = boardData.charAt(pointer++);
            }
        }

        // for(int row = 0; row<ROWS; row++){
        //     for(int col = 0; col<COLS; col++){
        //         // board.get(row).set(col, boardData.charAt(pointer++));                
        //         System.out.print(board[row][col]);
        //     }
        //     System.out.println();
        // }

        List<String> words = instance.generateAllWordsAdapter(board);
        
                
        BoardSolver boardSolverResponse = new BoardSolver(words);
        // System.out.println("From here"+boardSolverResponse.getWords().size());
        
        return boardSolverResponse;        
    }
    
    

}
