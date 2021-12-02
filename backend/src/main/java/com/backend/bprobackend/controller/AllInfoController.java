package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.AllInfoRequest;
import com.backend.bprobackend.request.ContractRequest;
import com.backend.bprobackend.security.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("")
public class AllInfoController {
    @Autowired
    UserRepos userRepository;

    @PostMapping("/info")
    public ResponseEntity<?> PayUser(@RequestBody AllInfoRequest allInfoRequest) {
        User user=userRepository.getById(allInfoRequest.getIduser());


        UserInfo info=new UserInfo(user.getId(),user.getUsername(),user.getAccount(),user.getMinutes(),user.getContract().getId(),user.getContract().getName(),user.getContract().getSum(),user.getMinutes()*user.getContract().getSum());
        return ResponseEntity.ok(info);
    }
}
