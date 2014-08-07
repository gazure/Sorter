package com.example.sorter;

public class MedOfThreeQS extends Quicksorter{

	public MedOfThreeQS(int[] array) {
		this(array, 0, array.length - 1);
		
	}
	
	public MedOfThreeQS(int[] array, int lo, int hi){
		super(array, lo, hi);
		int [] med = median(array[0], array[lo + (hi - lo) / 2], array[hi]);
		pivot = med[1];
		if(med[0] == 0)
			pivot_index = 0;
		else if(med[0] == 1)
			pivot_index = lo + (hi - lo) / 2;
		else
			pivot_index = hi;
			
	}
	
	private int[] median(int one, int two, int three){
		if(one >= two){
			if(one >= three){
				return new int[]{2, three};
			}else{
				return new int[]{0, one};
			}
		}else if(two >= three){
			return new int[]{2, three};
		}else{
			return new int[]{1, two};
		}
	}
	
}
