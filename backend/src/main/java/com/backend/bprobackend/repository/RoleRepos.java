package com.backend.bprobackend.repository;

import com.backend.bprobackend.model.EnumRole;
import com.backend.bprobackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepos extends JpaRepository<Role,Long> {
    Optional<Role> findByName(EnumRole name);

}
