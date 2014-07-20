package com.example.sorter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	/* constants */
	private final int NUMBER_OF_RANDOM_SWAPS = 100;
	
	/* fields */
	private int[] array; //primary representation of array
	private final Handler myHandler = new Handler(); //for timer-based UI updates
	private TextView array_tv; //UI element responsible for displaying array
	private boolean isSorting; //flag whether sorting is happening
	private Quicksorter sorter;//Current sorting implementation

	//primary runnable responsible for advancing the state of the
	//sort and setting appropriate flags 
	private final Runnable myRunnable = new Runnable() {
		@Override
		public void run() {
			sorter.step();
			array_tv.setText(arrayPayload());
			isSorting = !sorter.isSorted();
		}
	};
	
	/* onCreate */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//init fields
		array_tv = (TextView) findViewById(R.id.array_tv);
		isSorting = false;
		array = new int[10];
		sorter = new Quicksorter(array);
		
		for(int i = 0; i < array.length ; i++){
			array[i] = i;
		}
		randomizeArray();
		array_tv.setText(arrayPayload());

	}
	
	/* button event handlers */

	/**
	 * scrambles the array, putting it in an unsorted state
	 * primes for another sort
	 * does nothing if app is currently sorting
	 * 
	 * @handler reset_button
	 */
	public void reset(View v){
		if(!isSorting){
			randomizeArray();
			sorter = new Quicksorter(array);
			array_tv.setText(arrayPayload());
		}
	}
	
	/**
	 * initiates the sorting process
	 * does nothing if app is currently sorting
	 * @handler sort_button
	 */
	public void sort(View v){
		if(!isSorting){		
			isSorting = true;
			Timer myTimer = new Timer();
		      myTimer.schedule(new TimerTask() {
		         @Override
		         public void run() {
		        	 if(sorter.isSorted()){
		        		cancel();
		        	 }
		        		
		        	 myHandler.post(myRunnable);
		         }
		      }, 0, 100);
		}
	}
	
	/* private helping methods */
	
	/**
	 * @modifies the primary array
	 * @effects randomizes the positions of the elements of array
	 */
	private void randomizeArray(){
		Random rand = new Random();
		
		for(int i = 0; i < NUMBER_OF_RANDOM_SWAPS; i++){
			swap(rand.nextInt(10), rand.nextInt(10));
		}
	
	}
	
	/**
	 * @return a formatted string of the current state of the array
	 */
	private String arrayPayload(){
		String UIPayload = "";
		for(Integer i : array){
			UIPayload += i + " ";
		}
		UIPayload = UIPayload.substring(0, UIPayload.length() - 1);
		return UIPayload;
	}
	
	/**
	 * swaps two indeces of the primary array
	 * @modifies primary array
	 * @requires 0 <= i & j < primary_array.length
	 * @throws ArrayIndexOutOfBoundsException if invalid indeces are given 
	 * @param i - first index
	 * @param j - second index
	 */
	private void swap(int i, int j){
		if(0 <= i || 0 <= j || i < array.length || j < array.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
