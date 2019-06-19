package com.stackroute.springbootkafkaproducerexample.controller;


import com.stackroute.springbootkafkaproducerexample.sender.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/v1")
public class ProducerController {

    @Autowired
    private Producer producer;

    @GetMapping("/produce")
    public ResponseEntity<String> hellokafkaproducer(){
        producer.send("hey watsupp!!");

        return  new ResponseEntity<String>("Produced !!!!", HttpStatus.OK);
    }
}
