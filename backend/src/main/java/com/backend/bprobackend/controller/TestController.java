package com.backend.bprobackend.controller;


import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.ContractRepos;
import com.backend.bprobackend.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
    @RestController
    @RequestMapping("/bpro")
    public class TestController {
    private ContractRepos contractRepos;

    @Autowired
    public TestController(ContractRepos contractRepos) {
        this.contractRepos = contractRepos;
    }


    // надо доделать безопасность
    @GetMapping("/contracts")
    public List<Contract> adminAccess(){
        return  contractRepos.findAllByOrderByIdAsc();
    }
}
