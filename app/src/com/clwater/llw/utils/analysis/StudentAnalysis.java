package com.clwater.llw.utils.analysis;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.clwater.llw.model.Student;
import com.clwater.llw.model.User;

import android.util.Log;

public class StudentAnalysis {
	
	public static Vector<Student> getstudent (String result) throws JSONException{
		 Vector<Student> allstudent = new  Vector<Student>();
		 JSONTokener jsonParser = new JSONTokener(result);
	     JSONObject person = (JSONObject) jsonParser.nextValue();

	     JSONArray stu = person.getJSONArray("student");
	     
	     for(int i = 0 ; i < stu.length() ; i++){
	    	 Student student = new Student();
	    	 JSONObject temp = stu.getJSONObject(i);
	    	 student.setName(temp.getString("name"));
	    	 student.setId(temp.getString("id"));
	    	 student.setClassroom(temp.getString("class"));
	    	 allstudent.add(student);
	     }
	    
	     for(int i = 0 ; i < allstudent.size() ;i++){
	    	 Log.d("llwq", allstudent.get(i).getName());
	     }
	     
		return allstudent;
		
	}

}
