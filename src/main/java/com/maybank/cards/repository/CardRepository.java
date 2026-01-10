package com.maybank.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maybank.cards.entity.CardHolder;

@Repository
public interface CardRepository extends JpaRepository<CardHolder, String>{
	
	

}
