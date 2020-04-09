package com.gnp.auth.repository;


import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gnp.auth.models.Sales;

//This is a Repository
@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findFirst28ByOrderByDateDesc();
	Boolean existsByDate(LocalDate date);
}
