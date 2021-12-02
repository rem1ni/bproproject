package com.backend.bprobackend.controller;


import com.backend.bprobackend.model.Pay;
import com.backend.bprobackend.repository.PayRepos;
import com.backend.bprobackend.request.PayInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class PayInfoController {
    @Autowired
    PayRepos payRepos;

    @PostMapping("/userpay/info")
    public ResponseEntity<?> PayInfoUser(@RequestBody PayInfoRequest payInfoRequest) {
        List<Pay> pay=payRepos.findByIduser(payInfoRequest.getIduser());
        System.out.println(pay);

        return ResponseEntity.ok(pay);
    }
}
