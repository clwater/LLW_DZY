package com.clwater.llw;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import utils.TestNetWork;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String re = TestNetWork.getRestaurant(this);
		Log.d("llw1", re);
	}

}
