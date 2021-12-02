package com.backend.bprobackend.repository;

import com.backend.bprobackend.model.Contract;
import com.backend.bprobackend.model.Pay;
import com.backend.bprobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PayRepos extends JpaRepository<Pay,Long> {
    List<Pay> findByIduser(Long  iduser);


}
