package com.gnp.auth;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="sales")
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private double dailySales;
	private String weekDay;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getDailySales() {
		return dailySales;
	}
	public void setDailySales(double dailySales) {
		this.dailySales = dailySales;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
}
