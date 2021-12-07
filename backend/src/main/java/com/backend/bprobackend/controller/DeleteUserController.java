package com.backend.bprobackend.controller;

import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.AllInfoRequest;
import com.backend.bprobackend.request.DeleteRequest;
import com.backend.bprobackend.security.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro")
public class DeleteUserController {
    @Autowired
    UserRepos userRepository;

    @PostMapping("/delete")
    public ResponseEntity<?> DeleteUser(@RequestBody DeleteRequest deleteRequest) {
        System.out.println(deleteRequest.getIduser());
        User user=userRepository.getById(deleteRequest.getIduser());
        if (user.getMinutes()==0D) {
            userRepository.deleteById(deleteRequest.getIduser());
        }
        return ResponseEntity.ok("success");
    }
}
