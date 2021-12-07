package com.backend.bprobackend.repository;

import com.backend.bprobackend.model.Contract;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepos extends JpaRepository<Contract,Integer> {
    Optional<Contract> findByName(String name);
    List<Contract>  findAllByOrderByIdAsc();

}