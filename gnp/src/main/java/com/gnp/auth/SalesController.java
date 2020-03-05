package com.gnp.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {
	
	@Autowired
	SalesRepository dao;
	
	@GetMapping("/sales")
	public List<Sales> getSales() {
		List<Sales> foundSales = dao.findAll();
		return foundSales;
	}
	
	@GetMapping("/estimate")
	public List<Sales> estimateSales() {
		List<Sales> foundSales = dao.findAll();
		
		if (foundSales.contains("weekday"))	{
			System.out.println(foundSales);
		}
		
		return foundSales;
	}
	
}
