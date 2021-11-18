package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.Pay;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.ContractRepos;
import com.backend.bprobackend.repository.PayRepos;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.ContractRequest;
import com.backend.bprobackend.request.PayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class ContractController {
    @Autowired
    UserRepos userRepository;
    @Autowired
    ContractRepos contractRepository;
    @PostMapping("/usercontract")
    public ResponseEntity<?> PayUser(@RequestBody ContractRequest contractRequestRequest) {
        User user=userRepository.getById(contractRequestRequest.getIduser());
        Contract contract=contractRepository.getById(contractRequestRequest.getIdcontract());
        user.setContract(contract);
        userRepository.save(user);
        return ResponseEntity.ok("success");
    }
}
