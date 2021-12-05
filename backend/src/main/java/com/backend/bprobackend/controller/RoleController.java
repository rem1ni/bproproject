package com.backend.bprobackend.controller;


import com.backend.bprobackend.model.EnumRole;
import com.backend.bprobackend.model.Role;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.repository.RoleRepos;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro/roles")
public class RoleController {
    @Autowired
    UserRepos userRepository;
    @Autowired
    RoleRepos rolerepos;
    @PostMapping("/add")
    public ResponseEntity<?> RoleAddUser(@RequestBody RoleRequest roleRequest) {
        User user=userRepository.getById(roleRequest.getIduser());
        Set<Role> roles = user.getRoles();
        Role userRole = rolerepos.getById(roleRequest.getIdrole());
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("success");
    }
    @PostMapping("/del")
    public ResponseEntity<?> RoleDelUser(@RequestBody RoleRequest roleRequest) {
        User user=userRepository.getById(roleRequest.getIduser());
        Set<Role> roles = user.getRoles();
        Role userRole = rolerepos.getById(roleRequest.getIdrole());
        roles.remove(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok("success");
    }
}
