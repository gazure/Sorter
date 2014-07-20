package com.example.sorter;

//performs quicksort in stages, each stage being a swap between
//two elements in the algorithm
	

public class Quicksorter implements Sorter{
	private int[] array;
	private int lo;
	private int hi;
	protected int pivot;
	private int i;
	private int j;
	
	
	private boolean recurse;
	private boolean sorted;
	
	//for use if both recursive calls need to be made
	private Quicksorter forkQuicksorter;
	
	
	public Quicksorter(int[] array){
		this(array, 0, array.length - 1);
	}
	
	/**
	 * initializes a staged-quicksort algorithm 
	 * @param array - array to be sorted
	 * @throws IllegalArgumentException if array is null or 0 length
	 */
	public Quicksorter(int[] array, int lo, int hi){
		if(array == null || array.length == 0){
			throw new IllegalArgumentException();
		}
		this.array = array;
		this.lo = lo;
		this.hi = hi;
		this.pivot = array[lo + (hi - lo) / 2];
		this.i = lo;
		this.j = hi;
		this.recurse = false;
		this.sorted = false;
	}
	
	/**{@inheritDoc}} */
	public void step(){
		if(forkQuicksorter != null && !forkQuicksorter.isSorted()){
			forkQuicksorter.step();
		}else if(!sorted && !recurse){		
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
					return;
				}
			}
			recurse = true;
			step();
		}else if(!sorted){
			if(lo < j && i < hi){
				forkQuicksorter = new Quicksorter(array, lo, j);
				this.lo = i;
				this.j = hi;
				this.pivot = array[i + (j - i) / 2];
				this.recurse = false;
			}else if(lo < j){
				
				this.hi = j;
				this.pivot = array[lo + (j - lo) / 2];
				this.i = lo;
				this.recurse = false;
				
				
			}else if(i < hi){
				this.lo = i;
				this.j = hi;
				this.pivot = array[i + (j - i) / 2];
				this.recurse = false;
			}else{
				sorted = true;
				forkQuicksorter = null;
			}
			step();
		}
		
	}
	
	/**{@inheritDoc} */
	public boolean isSorted(){
		return sorted;
	}
	
	private void swap(int i, int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
}
