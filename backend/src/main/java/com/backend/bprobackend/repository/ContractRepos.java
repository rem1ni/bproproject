package com.backend.bprobackend.repository;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.EnumRole;
import com.backend.bprobackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepos extends JpaRepository<Contract,Long> {
    Optional<Contract> findByName(String name);

}