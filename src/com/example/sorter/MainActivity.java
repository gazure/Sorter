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

	private int[] array;
	final Handler myHandler = new Handler();
	TextView tv ;
	Timer myTimer;
	private boolean isRunning;
	private Quicksorter qs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.array_tv);
		isRunning = false;
		array = new int[10];
		qs = new Quicksorter(array);
		
		for(int i = 0; i < array.length ; i++){
			array[i] = i;
		}
		randomizeArray();
		tv.setText(arrayPayload());

	}
	
	final Runnable myRunnable = new Runnable() {
		public void run() {
			
			qs.step();
			tv.setText(arrayPayload());
		}
	};
	
	private void randomizeArray(){
		Random rand = new Random();
		
		for(int i = 0; i < 100; i++){
			swap(rand.nextInt(10), rand.nextInt(10));
		}
	
	}
	
	private String arrayPayload(){
		String UIPayload = "";
		for(Integer i : array){
			UIPayload += i + " ";
		}
		UIPayload = UIPayload.substring(0, UIPayload.length() - 1);
		return UIPayload;
	}
	
	
	
	public void reset(View v){
		if(!isRunning){
			randomizeArray();
			tv.setText(arrayPayload());
		}
	}
	
	public void sort(View v){
		if(!isRunning){		
			isRunning = true;
			Timer myTimer = new Timer();
		      myTimer.schedule(new TimerTask() {
		         @Override
		         public void run() {
		        	 if(qs.isSorted()){
		        		cancel();
		        		isRunning = false;
		        	 }
		        		
		        	 myHandler.post(myRunnable);
		         }
		      }, 0, 100);
		}
	}
	
	
	
	private void swap(int i, int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
