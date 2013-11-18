package com.mhmt.wheresmycar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WhereIsMyCarMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_where_is_my_car_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.where_is_my_car_main, menu);
		return true;
	}

}
