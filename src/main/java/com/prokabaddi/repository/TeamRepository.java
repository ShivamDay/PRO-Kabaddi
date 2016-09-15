package com.prokabaddi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prokabaddi.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
}
