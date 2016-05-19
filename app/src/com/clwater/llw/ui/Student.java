package com.clwater.llw.ui;

import com.clwater.llw.R;
import com.clwater.llw.utils.database.SSlDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Student extends Activity {
	
	private Activity activity;
	private Button stu_ssj , stu_sch;
	SSlDatabaseHelper ssldh;
	SQLiteDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.student);
		activity = this;
		create();
		
		
		
		ssldh = new SSlDatabaseHelper(this);
		db = null;
		db = ssldh.getReadableDatabase();
		
	}

	private void create() {
		stu_sch = (Button) findViewById(R.id.stu_sch);
		stu_sch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(activity , Schedule.class));
			}
		});
		
		stu_ssj = (Button) findViewById(R.id.stu_ssj);
		stu_ssj.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(activity , SuiShenJiActivity.class));
			}
		});
		
	}

}
