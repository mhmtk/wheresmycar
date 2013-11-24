package com.mhmt.wheresmycar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

public class WhereIsMyCarMain extends FragmentActivity 
implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener

{

	private String carLat;
	private String carLng;

	private String currentLat;
	private String currentLng;

	LocationClient mLocClient;
	Location mCurrentLoc;

	SharedPreferences mSharedPrefs;
	SharedPreferences.Editor mSharedPrefsEditor;
	
	TextView textCurrentLoc;

	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_where_is_my_car_main);
		
		textCurrentLoc = (TextView) findViewById(R.id.edit_currentlocation);
		
		mLocClient = new LocationClient(this, this, this);
		
		getCurrentLoc();
		displayCurrentLoc();
	}

	/*
	 * displays the current location on its appropriate view field
	 */
	private void displayCurrentLoc() {
		// TODO Auto-generated method stub
		textCurrentLoc.setText(Double.toString(mCurrentLoc.getLatitude())
				+ ", " + Double.toString(mCurrentLoc.getLongitude()));
		
	}


	private void getCurrentLoc() {
		// TODO Auto-generated method stub
		mCurrentLoc = mLocClient.getLastLocation();
		
	}

	public void viewMap(View view){

	}

	public static class ErrorDialogFragment extends DialogFragment{
		private Dialog mDialog;

		public ErrorDialogFragment(){
			super();
			mDialog = null;
		}

		public void setDialog(Dialog dialog){
			mDialog = dialog;
		}
		// Return a Dialog to the DialogFragment.
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch (requestCode){
		case CONNECTION_FAILURE_RESOLUTION_REQUEST:
			switch (resultCode){
			case Activity.RESULT_OK:
				break;
			}
		}
	}

	private boolean servicesConnected(){
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (ConnectionResult.SUCCESS == resultCode){
			Log.d("Location Updates", "Google Play Services is available");
			return true;
		}else{
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode,this,CONNECTION_FAILURE_RESOLUTION_REQUEST);
			if (errorDialog != null) {
				// Create a new DialogFragment for the error dialog
				ErrorDialogFragment errorFragment =
						new ErrorDialogFragment();
				// Set the dialog in the DialogFragment
				errorFragment.setDialog(errorDialog);
				// Show the error dialog in the DialogFragment
				errorFragment.show(getSupportFragmentManager(),
						"Location Updates");
			}
			return false;
		}
	}

	@Override
	public void onConnected(Bundle dataBundle) {
		// Display the connection status
		getCurrentLoc(null);
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
	}

	private void getCurrentLoc(Object object) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onDisconnected() {
		// Display the connection status
		Toast.makeText(this, "Disconnected. Please re-connect.",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
		 * Google Play services can resolve some errors it detects.
		 * If the error has a resolution, try sending an Intent to
		 * start a Google Play services activity that can resolve
		 * error.
		 */
		if (connectionResult.hasResolution()) {
			try {
				// Start an Activity that tries to resolve the error
				connectionResult.startResolutionForResult(
						this,
						CONNECTION_FAILURE_RESOLUTION_REQUEST);
				/*
				 * Thrown if Google Play services canceled the original
				 * PendingIntent
				 */
			} catch (IntentSender.SendIntentException e) {
				// Log the error
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Connect the client.
		mLocClient.connect();
	}

	@Override
	protected void onStop() {
		// Disconnecting the client invalidates it.
		mLocClient.disconnect();
		super.onStop();
	}

} 