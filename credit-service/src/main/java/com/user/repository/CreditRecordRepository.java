package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.CreditRecord;

@Repository
public interface CreditRecordRepository extends JpaRepository<CreditRecord, Long>{
	List<CreditRecord> findbyUserId( long LongId);
}
