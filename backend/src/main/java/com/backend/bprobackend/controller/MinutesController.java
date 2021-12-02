package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.ContractRequest;
import com.backend.bprobackend.request.MinutesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class MinutesController {
    @Autowired
    UserRepos userRepository;
    @PostMapping("/minutes")
    public ResponseEntity<?> ContractUser(@RequestBody MinutesRequest minutesRequest) {
        User user=userRepository.getById(minutesRequest.getIduser());
            user.setMinutes(user.getMinutes()+minutesRequest.getMin());
            userRepository.save(user);
            return ResponseEntity.ok("success");
        }
}
