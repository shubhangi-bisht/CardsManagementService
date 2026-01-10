package com.maybank.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maybank.cards.entity.CardHolder;

@Repository
public interface CardRepository extends JpaRepository<CardHolder, String>{

	@Modifying
	@Transactional
	@Query("UPDATE CardHolder c SET c.status = :status WHERE c.accountNumber = :accountNumber")
	int updateStatusByAccountNumber(String accountNumber, String status);

}
