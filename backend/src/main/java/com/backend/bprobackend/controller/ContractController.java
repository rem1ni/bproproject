package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.Pay;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.ContractRepos;
import com.backend.bprobackend.repository.PayRepos;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.ContractRequest;
import com.backend.bprobackend.request.EditRequest;
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
    public ResponseEntity<?> ContractUser(@RequestBody ContractRequest contractRequestRequest) {
        User user=userRepository.getById(contractRequestRequest.getIduser());
        if (user.getMinutes()==0d) {
            Contract contract = contractRepository.getById(contractRequestRequest.getIdcontract());
            user.setContract(contract);
            userRepository.save(user);
            return ResponseEntity.ok("success");
        }
        else
            return ResponseEntity.ok("fail");
    }
    @PostMapping("/edit")
    public ResponseEntity<?> ContractEdit(@RequestBody EditRequest editRequest) {
        Contract contract=contractRepository.getById(editRequest.getIdcontract());
        contract.setName(editRequest.getTitle());
        contract.setSum(editRequest.getBody());
        contractRepository.save(contract);
            return ResponseEntity.ok("success");
        }
}
