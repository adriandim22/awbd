package com.proiect.awbd.Repository;

import com.proiect.awbd.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }

