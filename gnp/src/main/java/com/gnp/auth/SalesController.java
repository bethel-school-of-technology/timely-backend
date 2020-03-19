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
	int counter = 0;
	int innerIfCounter = 0;
	Sales e;
	List<String> estimatedSales = new ArrayList<>();
	List<Sales> recentSales = new ArrayList<>();
	
	@GetMapping("/sales")
	public List<Sales> getPastSales(String weekDay) {
		//adds 28 most recent sales to recentSales
		//delete findByWeekdta
		List<Sales> foundSales = dao.findAll();
		
		for(int i = foundSales.size() - 1; i > 0; i--) {
			List<Sales> dayList = new ArrayList<>();
			//add i to dayList until dayList.size() = 28
			e = foundSales.get(i);
			dayList.add(e);
			//add sunday's four to recent sales
			if (dayList.size() == 28) {
			recentSales.addAll(dayList);
			break;
			}
		}
		return recentSales;
		
}
	
	@GetMapping("/estimate")
	public List<String> getEstimatedSales(String weekDay) {
		double total = 0;
		int dayCounter = 1;
		int dailySales = 0;
		List<Sales> foundSales = dao.findAll();
		//for(int i = 0; i < foundSales.size(); i++) {
			//List<Sales> sales = dao.findByWeekDay(dow.get(i));
			List<Sales> dayList = new ArrayList<>();
			for (int x = foundSales.size() - dayCounter; x > 0; x-=7) {
				if (counter == 0) {
					x = foundSales.size() - dayCounter; 											//4TH = 42 - 2 8TH = 42 - 3
				}
				System.out.println("THIS is the value of x " + x);
				//foundSales = entire database, dayList = last 5 days
				e = foundSales.get(x);								//1ST = 41 2ND = 34 3RD = 27 4TH = 20 5TH = 40 6TH = 33 7TH = 26 8TH = 19 9TH = 39 10TH = 32 11TH = 25 12TH = 18
				dayList.add(e);										 //1ST[42] 2ND [42,35] 3RD [42,35,28] 4TH [42,35,28,21] 5TH [42,35,28,21,34] 6TH [42,35,28,21,34,27] 7TH [42,35,28,21,40,34,27] 8TH [42,35,28,21,41,34,27,20,40,33,26,19,39,32,25,18,38,31,24,17,37,30,23,16,36,29,22,16,36,28,22,15]
				total += dayList.get(dailySales).getDailySales();
				dailySales++;
				counter++;														//1ST = 1 2ND = 2 3RD = 3 4TH = 4 5TH = 1 6TH = 2 7TH = 3 8TH = 4
				System.out.println("this is total inside for loop" + total);
				System.out.println("this is counter before if " + counter);
				if (counter == 4) {
					System.out.println("this is the average inside for loop" + getAverage(total));
					estimatedSales.add(String.valueOf(getAverage(total)));
					counter = 0;
					if (dayCounter <= 6 && counter == 0) {
						++dayCounter;																	//SAT = 2 FRI = 3 THU = 4 WED = 5 TUE = 6 MON = 7							
						total = 0;
						System.out.println("this is dayCounter " + dayCounter);
						//2 = 3rd position in dayList 42 numbers
						System.out.println("this is " + dayList.get(innerIfCounter).getWeekDay());
						System.out.println("this is id " + dayList.get(innerIfCounter).getId());
						innerIfCounter++;
						continue;
					}
					break;
				}
			}
			//for (int x = foundSales.size() -2; x > 0; x-=7) {
				//foundSales = entire database, dayList = last 5 days
				//e = foundSales.get(x);
				//dayList.add(e);
				//System.out.println(dayList);
			//}
			
		//}
		return estimatedSales;
		
}
	public double getAverage(double total) {
			System.out.println("this is original total " + total);
			double average = total / 4;
			System.out.println("this is counter " + counter);
			System.out.println("this is total " + total);
			return average;
	}
	
	@PostMapping("/sales")
	  public void sales(@RequestBody Sales newSales) {
	    dao.save(newSales);
	}
}
 