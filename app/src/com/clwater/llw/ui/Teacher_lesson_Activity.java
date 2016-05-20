package com.clwater.llw.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONException;

import com.clwater.llw.R;
import com.clwater.llw.model.Lesson;
import com.clwater.llw.utils.analysis.LeassonAnalysis;
import com.clwater.llw.utils.analysis.StudentAnalysis;
import com.clwater.llw.utils.network.LessonAdminNetWork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Teacher_lesson_Activity extends Activity implements OnItemClickListener  {

	//private Button test;
	public static  Activity activity;
	private ListView listView ;
	public ProgressDialog pr;
	private String chhooseid;
	List<Map<String, Object>> list;
	private Vector<Lesson> alllesson = new Vector<Lesson>();
	//private Lesson lesson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tea_lesadm);
		activity = this;
		
		String result = LessonAdminNetWork.getRestaurant(activity);
		Log.d("llw", "query: " + result);
		
		if(result.equals("{\"statu\":\"error\"}")){
			Toast.makeText(activity, "暂无数据，请添加", Toast.LENGTH_SHORT).show();
		}else{
			try {
				alllesson = LeassonAnalysis.getstudent(result);
				} catch (JSONException e) {			}
		}
		
		

		
		
			
		create();
	}
	private void create() {

		listView = (ListView) findViewById(R.id.tes_les_list);
		listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getData()));
        
        
        listView.setOnItemClickListener(this);
        
		
	}
	
	 private List<String> getData(){
         
	        List<String> data = new ArrayList<String>();
	        for (int i = 0; i < alllesson.size(); i++) {
	        	data.add("" + alllesson.get(i).getStudent_name());
			}
	        

	         
	        return data;
	    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent next = new Intent(activity , LeassonMessageActivity.class);
		next.putExtra("id", alllesson.get(position).getStudent_id());
		next.putExtra("name", alllesson.get(position).getStudent_name());
		next.putExtra("attendance", alllesson.get(position).getAttendance());
		next.putExtra("homework", alllesson.get(position).getHomework());
		next.putExtra("lessonstatu", alllesson.get(position).getLessonstatu());
		next.putExtra("lessontest", alllesson.get(position).getLessontest());
		next.putExtra("score", alllesson.get(position).getScore());
		activity.startActivity(next);
		
		activity.finish();
		
	}


	



}