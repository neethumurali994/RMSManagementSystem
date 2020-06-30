package com.rmsMagement.RMSManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmsMagement.RMSManagement.Entity.Rate;
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

}
