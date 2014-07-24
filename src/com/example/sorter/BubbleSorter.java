package com.example.sorter;

public class BubbleSorter implements Sorter{

	private int[] arr;
	private int outer_i;
	private int inner_j;
	private boolean sorted;
	private boolean comping;
	private boolean comp_result;
	public BubbleSorter(int[] arr){
		this.arr = arr;
		outer_i = 0;
		inner_j = arr.length;
		sorted = false;
		comping = false;
	}
	
	public Step step(){
		if(inner_j > outer_i){
			if(!comping){
				comping = true;
				if(arr[inner_j] < arr[inner_j - 1]){
					comp_result = true;
				}else{
					comp_result = false;
				}
				return Step.COMPARISON;
			}else if(comp_result){
				comping = false;
				swap(inner_j, inner_j - 1);
				inner_j--;
				return Step.SWAP;
			}
		}else if(outer_i != arr.length){
			inner_j = arr.length;
			outer_i++;
			return step();
		}
		sorted = true;
		return Step.DONE;
	}
	
	public boolean isSorted(){
		return sorted;
	}
	
	private void swap(int i, int j){
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}
