package com.backend.bprobackend.controller;
import com.backend.bprobackend.repository.ContractRepos;
import com.backend.bprobackend.response.JwtResponse;
import com.backend.bprobackend.request.LoginRequest;
import com.backend.bprobackend.request.SignupRequest;
import com.backend.bprobackend.model.EnumRole;
import com.backend.bprobackend.model.Role;
import com.backend.bprobackend.model.User;
import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.repository.RoleRepos;
import com.backend.bprobackend.repository.UserRepos;
import com.backend.bprobackend.response.MessageResponse;
import com.backend.bprobackend.security.jwt.JwtUtils;
import com.backend.bprobackend.security.service.PayDetailService;
import com.backend.bprobackend.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bpro/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepos userRepository;

    @Autowired
    RoleRepos roleRepository;

    @Autowired
    ContractRepos contractRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAccount(),
                userDetails.getMinutes(),
                userDetails.getContracts(),
                userDetails.getContracts_sum(),
                roles,
                userDetails.getFac()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is already taken!"));
        }
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found."));
            roles.add(userRole);
            Contract userContract = contractRepository.findByName("None").orElseThrow(() -> new RuntimeException("Contract is not found."));
            user.setContract(userContract);
        }
        user.setRoles(roles);
        user.setAccount(0D);
        user.setMinutes(0D);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered!"));
    }
}