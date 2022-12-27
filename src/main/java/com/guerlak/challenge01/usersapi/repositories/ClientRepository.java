package com.guerlak.challenge01.usersapi.repositories;

import com.guerlak.challenge01.usersapi.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
