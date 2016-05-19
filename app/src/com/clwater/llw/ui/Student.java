package com.clwater.llw.ui;

import java.util.Vector;

import org.json.JSONException;

import com.clwater.llw.R;
import com.clwater.llw.model.Lesson;
import com.clwater.llw.utils.analysis.LeassonAnalysis;
import com.clwater.llw.utils.database.SSlDatabaseHelper;
import com.clwater.llw.utils.network.LessonAdminNetWork;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Student extends Activity {
	
	private Activity activity;
	private Button stu_ssj , stu_sch  ,stu_lesson;
	SSlDatabaseHelper ssldh;
	SQLiteDatabase db;
	private Vector<Lesson> alllesson = new Vector<Lesson>();
	private Lesson lesson;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.student);
		activity = this;
		create();
		
		
		
		ssldh = new SSlDatabaseHelper(this);
		db = null;
		db = ssldh.getReadableDatabase();
		
		
		
		String result = LessonAdminNetWork.getRestaurant(activity);
		Log.d("llw", "query: " + result);
		
		
		try {
			alllesson = LeassonAnalysis.getstudent(result);
			} catch (JSONException e) {			}
		
		Log.d("lll", "LoginActivity.user.getId():" + LoginActivity.user.getId());
		
		for (int i = 0; i < alllesson.size(); i++) {
			Log.d("lll", "alllesson.get(i).getStudent_id()" + alllesson.get(i).getStudent_id());
			if(alllesson.get(i).getStudent_id().equals(LoginActivity.user.getId())){
				lesson = alllesson.get(i);
			}
		}
		
		
		
	}

	private void create() {
		stu_sch = (Button) findViewById(R.id.stu_sch);
		stu_sch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(activity , Schedule.class));
			}
		});
		
		stu_lesson = (Button) findViewById(R.id.stu_les);
		stu_lesson.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent next = new Intent(activity , StudentLeassonMessageActivity.class);
				next.putExtra("id", lesson.getStudent_id());
				next.putExtra("name", lesson.getStudent_name());
				next.putExtra("attendance", lesson.getAttendance());
				next.putExtra("homework", lesson.getHomework());
				next.putExtra("lessonstatu", lesson.getLessonstatu());
				next.putExtra("lessontest", lesson.getLessontest());
				next.putExtra("score", lesson.getScore());
				activity.startActivity(next);
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
