package com.gnp.auth;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public void  estimateSales() {
	}
	
	@GetMapping("/monday")
	public List<Sales> mondaySales(String weekDay) {
		List<Sales> listmonday = dao.findByWeekDay("Monday");
		return listmonday;
	}
	
	@PostMapping("/sales")
	  public void sales(@RequestBody Sales newSales) {
	    dao.save(newSales);
	}
}
