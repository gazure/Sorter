package com.example.sorter;

public interface Sorter {
	public void step();	 //computes the next swap in the sort 
	public boolean isSorted(); //returns whether or not the sort is complete
}
