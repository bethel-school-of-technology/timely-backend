package com.gnp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnp.auth.models.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findFirst28ByOrderByDateDesc();
}