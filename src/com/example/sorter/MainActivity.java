package com.example.sorter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int[] array;
	final Handler myHandler = new Handler();
	TextView tv ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.array_tv);
		
		array = new int[10];
		
		for(int i = 0; i < array.length ; i++){
			array[i] = i;
		}
		randomizeArray();
		
		Timer myTimer = new Timer();
	      myTimer.schedule(new TimerTask() {
	         @Override
	         public void run() {update();}
	      }, 0, 1000);
	}
	
	final Runnable myRunnable = new Runnable() {
		public void run() {
			tv.setText(arrayPayload());
		}
	};
	
	private void randomizeArray(){
		Random rand = new Random();
		
		for(int i = 0; i < 100; i++){
			swap(rand.nextInt(10), rand.nextInt(10));
		}
	
	}
	
	private void update(){
		myHandler.post(myRunnable);
	}
		
	
	private String arrayPayload(){
		String UIPayload = "";
		for(Integer i : array){
			UIPayload += i + " ";
		}
		return UIPayload.substring(0, UIPayload.length() - 1); 
	}
	
	
	
	public void reset(View v){
		randomizeArray();
		
	}
	
	public void sort(View v){
		quickSort(0, array.length - 1);
		
	}
	
	private void quickSort(int p, int r){
		int i = p;
		int j = r;
		int pivot = array[i + (j - i)/2];
		while(i <= j){
			while(array[i] < pivot){
				i++;
			}
			while(array[j] > pivot){
				j--;
			}
			
			if(i <= j) {
				swap(i, j);
				i++;
				j--;
			}
		}
		
		if(p < j)
			quickSort(p, j);
		if(i < r)
			quickSort(i, r);
	}
	
	private void swap(int i, int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
