package com.example.sorter;

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
	private int comp_index_1;
	private int comp_index_2;
	private int swap_index_1;
	private int swap_index_2;
	private ComparisonQuicksorter fork_quicksorter;
	
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
		case SWAP   : return swap_step();
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
	
	public int[] comparison_indeces(){
		return new int[] {comp_index_1, comp_index_2};
	}
	
	public int[] swap_indeces(){
		return new int[] {swap_index_1, swap_index_2};
	}
	
	private Step compare_i_or_j(char i_or_j){
		int n;
		if(i_or_j == 'i'){
			n = i;
		}else{
			n = j;
		}
		comp_index_1 = n;
		comp_index_2 = pivot_index;
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
	
	private Step swap_step(){
		if(i <= j){
			swap_index_1 = i;
			swap_index_2 = j;
			swap(i,j);
			i++; j--;
			check_step();
			return Step.SWAP;
		}
		check_step();
		return step();
	}
	
	private void check_step(){
		if(i > j)
			current_step = QSStep.RECURSE;
		else
			current_step = QSStep.COMP_I;
		
	}
	
	private Step recurse(){
		if(lo < j && i < hi){
			fork_quicksorter = new ComparisonQuicksorter(array, lo, j);
			this.lo = i;
			this.j = hi;
			this.pivot = array[i + (j - i) / 2];
			this.pivot_index = i + (j - i) / 2;
			current_step = QSStep.FORK;
		}else if(lo < j){
			
			this.hi = j;
			this.pivot = array[lo + (j - lo) / 2];
			this.pivot_index = array[lo + (j - lo) / 2];
			this.i = lo;
			current_step = QSStep.COMP_I;
			
			
		}else if(i < hi){
			this.lo = i;
			this.j = hi;
			this.pivot = array[i + (j - i) / 2];
			this.pivot_index = i + (j - i) / 2;
			current_step = QSStep.COMP_I;
		}else{
			sorted = true;
			fork_quicksorter = null;
			current_step = QSStep.SORTED;
		}
		return step();
	}
	
	private Step fork(){
		Step s = fork_quicksorter.step();
		if (s == Step.DONE){
			current_step = QSStep.COMP_I;
			return step();
		}else if(s == Step.COMPARISON){
			int[] c = fork_quicksorter.comparison_indeces();
			comp_index_1 = c[0];
			comp_index_2 = c[1];
		}else{
			int[] sw = fork_quicksorter.swap_indeces();
			swap_index_1 = sw[0];
			swap_index_2 = sw[1];
		}
		return s;
	}
	
	private void swap(int i, int j){
		if(i >= 0 && j >= 0 && i < array.length && j < array.length){
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

}
