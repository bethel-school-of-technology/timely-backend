package com.gnp.auth;

public class GetAverage {
	 /* this is an example code for obtaining an average from 4 numbers in an array.*/
	  public static void main(String[] args) {
	        double[] arr = {2008.20, 1289.46, 1657.68, 2030.35};
	        double total = 0;

	        for(int i=0; i<arr.length; i++){
	        	total = total + arr[i];
	        }


	        /* arr.length returns the number of elements 
	         * present in the array
	         */
	        double average = total / arr.length;
	        
	        /* This is used for displaying the formatted output
	         * if you give %.2f then the output would have 2 digits
	         * after decimal point.
	         */
	        System.out.format("The average is: %.2f", average); 
	        /*this is a basic console log.*/  
	    }
	}