package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class Test1Controller {
    @Autowired
    UserRepos userRepository;
    @GetMapping("/time")
    public String adminAccess(){
        String time=new SimpleDateFormat("dd_HHmmss").format(Calendar.getInstance().getTime());
        User users = userRepository.getById(1L);
        users.setAccount(users.getAccount()-users.getMinutes()*users.getContract().getSum());
        userRepository.save(users);
        return time;
    }
}
