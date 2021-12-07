package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class UserController {

    private UserRepos userRepos;

    @Autowired
    public UserController(UserRepos userRepos) {
        this.userRepos = userRepos;
    }


    // надо доделать безопасность
    @GetMapping("/admin")
    public List<User> adminAccess(){
        List<User> users = userRepos.findAllByOrderByIdAsc();
        return users;
    }

}

