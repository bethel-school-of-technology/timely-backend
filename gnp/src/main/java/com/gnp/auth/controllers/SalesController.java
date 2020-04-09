package com.gnp.auth.controllers;

import java.math.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnp.auth.models.Sales;
import com.gnp.auth.payload.response.MessageResponse;
import com.gnp.auth.repository.SalesRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SalesController {
	// instance of dao gives access to methods to communicate with the database
	@Autowired
	SalesRepository dao;

	// averaging method called by getEstimatedSales method
	public BigDecimal getAverage(BigDecimal total) {
		BigDecimal average = total.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP);
		return average;
	}
	/*
	 * Code below calculates the average sales for each day of the week, based on the last 28 days of sales
	 * using BigDecimal datatype to accurately calculate currency amounts
	 * but returning calculated amounts as a double to better communicate with React
	 */
	@GetMapping("/estimate")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Double> getEstimatedSales() {
		// empty array list which will be given the average of the most recent sales every time method is called
		List<Double> estimatedSales = new ArrayList<>();
		//28 most recent sales
		List<Sales> recentSales = dao.findFirst28ByOrderByDateDesc();
		List<Sales> reorderedArray = new ArrayList<>();
		//total will eventually receive the total amount of the 4 most recent sales for a particular day of the week before it gets passed to the averaging method
		BigDecimal total = new BigDecimal("0.00");
		//counter keeps track of how many days we've looked at, limiting them to 4 (since we're looking at them based on day of the week
		int counter = 0;
		int dailySales = 0;
		int nextWeekday = 0;
		//foundSales is ordered Sun-Sat, we loop through it by 7, adding every seventh entry to newArray
		for (int x = 0; nextWeekday < 7; x += 7) {
			if (counter == 0 && !estimatedSales.isEmpty()) {
				x = 0 + nextWeekday;
			}
			reorderedArray.add(recentSales.get(x));
			total = total.add(reorderedArray.get(dailySales).getDailySales());
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

	// returns most recent past sales in an array list
	@GetMapping("/sales")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Sales> getPastSales() {
		List<Sales> recentSales = dao.findFirst28ByOrderByDateDesc();
		return recentSales;
	}
	// saves past sales to database
	@PostMapping("/sales")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> sales(@RequestBody Sales newSales) {
		if (dao.existsByDate(newSales.getDate())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("The sales for these dates were already entered!"));
		} else {
			dao.save(newSales);
			return ResponseEntity.ok(new MessageResponse("Sales entered successfully!"));
		}
	}
}
