package com.example.sorter;

public class MedOfThreeQS extends Quicksorter{

	public MedOfThreeQS(int[] array) {
		super(array);
		pivot = median(array[0], array[array.length / 2], array[array.length-1]);
	}
	
	public MedOfThreeQS(int[] array, int lo, int hi){
		super(array, lo, hi);
		pivot = median(array[0], array[lo + (hi - lo) / 2], array[hi]);
	}
	
	private int median(int one, int two, int three){
		if(one >= two){
			if(one >= three){
				return one;
			}else{
				return three;
			}
		}else if(two >= three){
			return two;
		}else{
			return three;
		}
	}
	
}
