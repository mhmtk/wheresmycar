package com.mhmt.wheresmycar;

import com.google.android.gms.location.LocationClient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class WhereIsMyCarMain extends Activity {

	LocationClient mLocationClient;
	TextView textCurrentLoc; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_where_is_my_car_main);

		mLocationClient = new LocationClient(this, null, null);
		textCurrentLoc = (TextView) findViewById(R.id.edit_currentlocation);
		displayCurrentLoc();
	}

	/*
	 * displays the current location at it's appropriate view field
	 */
	private void displayCurrentLoc() {
		// TODO Auto-generated method stub
		mLocationClient.connect();
	}
	
	/*
	 * Called when the Activity becomes visible.
	 */
	@Override
	protected void onStart() {
		super.onStart();
		// Connect the client.
		mLocationClient.connect();
	}

	/*
	 * Called when the Activity is no longer visible.
	 */
	@Override
	protected void onStop() {
		// Disconnecting the client invalidates it.
		mLocationClient.disconnect();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.where_is_my_car_main, menu);
		return true;
	}

}
