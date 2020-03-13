package com.gnp.auth;

import java.util.ArrayList;
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
	Sales e;
	List<Sales> recentSales = new ArrayList<>();
	
	@GetMapping("/allsales")
	public List<Sales> getSales() {
		List<Sales> foundSales = dao.findAll();
		return foundSales;
	}
	
	@GetMapping("/estimate")
	public void  estimateSales() {
	}
	
	@GetMapping("/monday")
	public List<Sales> mondaySales(String weekDay) {
		ArrayList<String> dow = new ArrayList<String>();
		dow.add("Sunday");
		dow.add("Monday");
		dow.add("Tuesday");
		dow.add("Wednesday");
		dow.add("Thursday");
		dow.add("Friday");
		dow.add("Saturday");
		
		for(int i = 0; i < dow.size(); i++) {
			List<Sales> sales = dao.findByWeekDay(dow.get(i));
			List<Sales> dayList = new ArrayList<>();
			for (int x = 0; x < 4; x++) {
				e = sales.get(x);
				dayList.add(e);
			}
			recentSales.addAll(dayList);
		}
		return recentSales;
		
}
		
	
	
	@PostMapping("/sales")
	  public void sales(@RequestBody Sales newSales) {
	    dao.save(newSales);
	}
}
 