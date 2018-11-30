package com.oocl.web.sampleWebApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping
    public ResponseEntity<ResponseMessage> simpleRequest(){
        return new ResponseEntity<>(new ResponseMessage("HI"), HttpStatus.OK);
    }
}
