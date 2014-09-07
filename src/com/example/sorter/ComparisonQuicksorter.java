package com.example.sorter;

import com.example.sorter.Sorter.Step;

public class ComparisonQuicksorter implements Sorter{
	
	private boolean sorted;
	private static enum QSStep  {COMP_I, COMP_J, SWAP, RECURSE, FORK, SORTED};
	private QSStep current_step;
	private int[] array;
	private int lo;
	private int hi;
	private int pivot;
	private int i;
	private int j;
	private int pivot_index;
	private int[] comparison_indeces;
	
	public ComparisonQuicksorter(int[] array, int lo, int hi){
		if(array == null || array.length == 0){
			throw new IllegalArgumentException();
		}
		this.current_step = QSStep.COMP_I;
		this.array = array;
		this.lo = lo;
		this.hi = hi;
		this.pivot_index = lo + (hi - lo) /2 ;
		this.pivot = array[pivot_index];
		this.i = lo;
		this.j = hi;
	}
	
	@Override
	public Step step() {
		switch(current_step){
		case COMP_I : return compare_i();
		case COMP_J : return compare_j();
		case SWAP   : return swap();
		case RECURSE: return recurse();
		case FORK   : return fork();
		case SORTED : return Step.DONE;
		default		: return Step.DONE;
		}
	}

	@Override
	public boolean isSorted(){
		return sorted;
	}
	
	private Step compare_i_or_j(char i_or_j){
		int n;
		if(i_or_j == 'i'){
			n = i;
		}else{
			n = j;
		}
		int[] temp = {n, pivot_index};
		comparison_indeces = temp;
		if(array[n] > pivot){
			if(n == i) { i++; }
			else if (n == j) { j--; }
		}else{
			current_step = QSStep.COMP_J;
		}
		return Step.COMPARISON;
	}
	
	private Step compare_i(){
		return compare_i_or_j('i');
	}
	
	private Step compare_j(){
		return compare_i_or_j('j');
	}
	
	private Step swap(){
		if(i <= j){
			
		}
		return Step.SWAP;
	}
	
	private Step recurse(){
		return Step.DONE;
	}
	
	private Step fork(){
		return Step.DONE;
	}

}
