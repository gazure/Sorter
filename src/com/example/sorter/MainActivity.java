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
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int[] array;
	final Handler myHandler = new Handler();
	TextView tv ;
	private Quicksorter qs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.array_tv);
		
		array = new int[10];
		qs = new Quicksorter(array);
		
		for(int i = 0; i < array.length ; i++){
			array[i] = i;
		}
		randomizeArray();
		
//		Timer myTimer = new Timer();
//	      myTimer.schedule(new TimerTask() {
//	         @Override
//	         public void run() {update();}
//	      }, 0, 1000);
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
	
	private void update(){
		
		myHandler.post(myRunnable);
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
		//randomizeArray();
		
	}
	
	public void sort(View v){
		Button sb = (Button) findViewById(R.id.sort_button);
		sb.setEnabled(false);
		
		Timer myTimer = new Timer();
	      myTimer.schedule(new TimerTask() {
	         @Override
	         public void run() {update();}
	      }, 0, 1000);
	}
	
	
	
	private void swap(int i, int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
