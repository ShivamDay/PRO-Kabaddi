package com.prokabaddi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prokabaddi.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

}
