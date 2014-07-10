package com.example.sorter;

//performs quicksort in stages, each stage being a swap between
//two elements in the algorithm
	

public class Quicksorter {
	private int[] array;
	private int lo;
	private int hi;
	private int pivot;
	private int i;
	private int j;
	
	
	private boolean recurse;
	private boolean sorted;
	/**
	 * initializes a staged-quicksort algorithm 
	 * @param array - array to be sorted
	 * @throws IllegalArgumentException if array is null or 0 length
	 */
	public Quicksorter(int[] array){
		if(array != null || array.length == 0){
			throw new IllegalArgumentException();
		}
		this.array = array;
		this.lo = 0;
		this.hi = array.length;
		this.pivot = array[hi / 2];
		this.i = lo;
		this.j = hi;
		this.recurse = false;
		this.sorted = false;
	}
	
	public int[] step(){
		if(!recurse){		
			while(i <= j){
				while(array[i] < pivot)
					i++;
				while(array[j] > pivot)
					j--;
				
				if(i <= j){
					swap(i, j);
					i++;
					j--;
					
					if(i > j) 
						recurse = true;
					
					int[] ret = {i - 1, j + 1};
					return ret;
				}
			}
			recurse = true;
			step();
		}else{
			
		}
		
		return new int[2];
	}
	
	private void swap(int i, int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
}
