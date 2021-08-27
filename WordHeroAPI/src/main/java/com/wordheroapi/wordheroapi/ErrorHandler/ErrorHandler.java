package com.wordheroapi.wordheroapi.ErrorHandler;

import com.wordheroapi.wordheroapi.Trie.Serializers.ErrorReport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandler {
    
    @GetMapping("/error")
    public ErrorReport errorHandler(){
        // return "Something went wrong!";

        return new ErrorReport(400, "Something Went Wrong!");
    }

}
