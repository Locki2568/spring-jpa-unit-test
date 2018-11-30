package com.oocl.web.sampleWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @GetMapping
    public ResponseEntity<ResponseMessage> simpleRequest(){
        SingleEntity singleEntity = singleEntityRepository.findAll().get(0);
        return new ResponseEntity<>(new ResponseMessage(singleEntity.name), HttpStatus.OK);
    }
}
