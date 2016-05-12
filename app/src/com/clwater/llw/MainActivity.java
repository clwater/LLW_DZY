package com.clwater.llw;

import com.clwater.llw.ui.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button test;
	private Activity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		//startActivity(new Intent(this , LoginActivity.class));
		
		activity = this;
		
		
		test = (Button) findViewById(R.id.test);
		test.setOnClickListener(this);
		
		
		
		Log.d("llw1", "111");
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test:
			
			  
			//gettest();
			Log.d("llw1", "333");
			break;

		default:
			break;
		}
		
	}
	



}
