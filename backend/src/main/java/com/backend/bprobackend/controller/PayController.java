package com.backend.bprobackend.controller;


import com.backend.bprobackend.model.Pay;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.PayRepos;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.LoginRequest;
import com.backend.bprobackend.request.PayRequest;
import com.backend.bprobackend.response.JwtResponse;
import com.backend.bprobackend.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class PayController {
    @Autowired
    UserRepos userRepository;
    @Autowired
    PayRepos payRepos;

    @PostMapping("/userpay")
    public ResponseEntity<?> PayUser(@RequestBody PayRequest payRequest) {
        int a = (int) ( Math.random() * 3 );
        if (payRequest.getNum() <= 10000 && payRequest.getNum() >= 1) {
            if(a==0){
                User user = userRepository.getById(payRequest.getI());
                user.setAccount(payRequest.getNum() + user.getAccount());
                userRepository.save(user);
                String time = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                Pay pay = new Pay(payRequest.getI(), payRequest.getNum(), user.getAccount(), time);
                payRepos.save(pay);

                return ResponseEntity.ok("Платеж успешно выполнен");
            }
            else {if(a==1) return ResponseEntity.ok("Недостаточно средств"); else return ResponseEntity.ok("Нет соединения с банком");}
        }
        else
        { return ResponseEntity.ok("Неверная сумма(мин: 1, макс: 10000)");}
    }
}
