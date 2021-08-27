package com.wordheroapi.wordheroapi.Trie.Serializers;

public class ErrorReport {
    
    private int status;
    private String message;

    public ErrorReport(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

}
