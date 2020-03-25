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
	List<Double> estimatedSales = new ArrayList<>();

	// instance of dao gives access to methods to communicate with the database
	@Autowired
	SalesRepository dao;

	// method called by getEstimatedSales method
	public BigDecimal getAverage(BigDecimal total) {
		BigDecimal average = total.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP);
		return average;
	}

	@GetMapping("/estimate")
	public List<Double> getEstimatedSales() {
		List<Sales> foundSales = dao.findFirst28ByOrderByDateDesc();
		List<Sales> newArray = new ArrayList<>();
		BigDecimal total = new BigDecimal("0.00");
		int counter = 0;
		int dailySales = 0;
		int nextWeekday = 0;
		for (int x = 0; nextWeekday < 7; x += 7) {
			if (counter == 0 && !estimatedSales.isEmpty()) {
				x = 0 + nextWeekday;
			}
			newArray.add(foundSales.get(x));
			total = total.add(newArray.get(dailySales).getDailySales());
			dailySales++;
			counter++;
			if (counter == 4) {
				estimatedSales.add(Double.valueOf(String.valueOf(getAverage(total))));
				counter = 0;
				total = new BigDecimal("0.00");
				nextWeekday++;
			}
		}
		return estimatedSales;
	}

	// returns sales data from the past 28 days
	@GetMapping("/sales")
	public List<Sales> getPastSales() {
		List<Sales> foundSales = dao.findFirst28ByOrderByDateDesc();
		return foundSales;
	}

	// saves past sales data to database
	@PostMapping("/sales")
	public void sales(@RequestBody Sales newSales) {
		dao.save(newSales);
	}
}
