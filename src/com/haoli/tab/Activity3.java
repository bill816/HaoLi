package com.haoli.tab;

import com.haoli.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity3 extends Activity{

	private final static String TAG = "Activity3";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_layout);

        Log.i(TAG, "=============>onCreate");
    }
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		 Log.i(TAG, "=============>onResume");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		 Log.i(TAG, "=============>onDestroy");
	}
	
}