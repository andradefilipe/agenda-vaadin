package com.andrade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Client, Long> {
}