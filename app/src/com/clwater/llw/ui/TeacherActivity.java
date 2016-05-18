package com.clwater.llw.ui;


import com.clwater.llw.R;
import com.clwater.llw.ui.LoginActivity;
import com.clwater.llw.utils.network.StudentAdminNetWork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class TeacherActivity extends Activity implements OnClickListener {

	private Button test;
	private Activity activity;
	private Button student_admin , suishenji  ,lesson_admin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.teacher);
		activity = this;
			
		create();
	}
	private void create() {
		student_admin = (Button) findViewById(R.id.student_admin);
		student_admin.setOnClickListener(this);
		
		suishenji = (Button) findViewById(R.id.suishenji);
		suishenji.setOnClickListener(this);
		
		lesson_admin = (Button) findViewById(R.id.lesson_admin);
		lesson_admin.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.student_admin:
			startActivity(new Intent(this , Teacher_student_Activity.class));
			break;
		case R.id.suishenji:
			startActivity(new Intent(this , SuiShenJiActivity.class));
			break;
		case R.id.lesson_admin:
			startActivity(new Intent(this , Teacher_lesson_Activity.class));
			break;
		default:
			break;
		}
		
	}
	



}
