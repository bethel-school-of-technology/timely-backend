package com.gnp.auth.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;


@Entity
@Table(name="sales", uniqueConstraints = { @UniqueConstraint(columnNames = "date") } )
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date;
	private BigDecimal dailySales;
	private String weekDay;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getDailySales() {
		return dailySales;
	}
	public void setDailySales(BigDecimal dailySales) {
		this.dailySales = dailySales;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
}
