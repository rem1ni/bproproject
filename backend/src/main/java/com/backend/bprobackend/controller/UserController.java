package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public List<User> adminAccess(){
        return userRepos.findAll();
    }

}

