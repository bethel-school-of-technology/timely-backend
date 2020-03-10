package com.gnp.auth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer>{
	List<Sales> findByWeekDay(String weekDay);
}
