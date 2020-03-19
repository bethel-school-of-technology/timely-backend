package com.gnp.auth;

import java.math.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {
	List<Sales> recentSales = new ArrayList<>();
	List<String> estimatedSales = new ArrayList<>();
	Sales e;
	int counter = 0;
	
	// instance of dao gives access to methods to communicate with the database
	@Autowired
	SalesRepository dao;
	
	// method called by getEstimatedSales method
	public BigDecimal getAverage(BigDecimal total) {
		BigDecimal average = total.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP);
		return average;
	}
	
	// returns an array of estimated sales for the week based on past month's sales
	@GetMapping("/estimate")
	public List<String> getEstimatedSales() {
		BigDecimal total = new BigDecimal("0.00");
		int dayCounter = 1;
		int dailySales = 0;
		List<Sales> foundSales = dao.findAll(); // found Sales = entire database
		List<Sales> dayList = new ArrayList<>(); // dayList = last 4 days
		for (int x = foundSales.size() - dayCounter; x > 0; x -= 7) {
			if (counter == 0) {
				x = foundSales.size() - dayCounter;
			}
			e = foundSales.get(x);
			dayList.add(e);
			total = total.add(dayList.get(dailySales).getDailySales());
			dailySales++;
			counter++;
			if (counter == 4) {
				estimatedSales.add(String.valueOf(getAverage(total)));
				counter = 0;
				if (dayCounter <= 6 && counter == 0) {
					++dayCounter;
					total = new BigDecimal("0.00");
					continue;
				}
				break;
			}
		}
		return estimatedSales;

	}
	
	// returns sales data from the past 28 days 
	@GetMapping("/sales")
	public List<Sales> getPastSales() {
		List<Sales> foundSales = dao.findAll();
		List<Sales> dayList = new ArrayList<>();
		for (int i = foundSales.size() - 1; i >= 0; i--) {
			e = foundSales.get(i);
			dayList.add(e);
			if (dayList.size() == 28) {
				recentSales.addAll(dayList);
			}
		}
		return recentSales;

	}
	
	// saves past sales data to database
	@PostMapping("/sales")
	public void sales(@RequestBody Sales newSales) {
		dao.save(newSales);
	}
}
