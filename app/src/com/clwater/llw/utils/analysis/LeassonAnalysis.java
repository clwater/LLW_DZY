package com.clwater.llw.utils.analysis;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.clwater.llw.model.Lesson;
import com.clwater.llw.model.Student;
import com.clwater.llw.model.User;

import android.util.Log;

public class LeassonAnalysis {
	
	public static Vector<Lesson> getstudent (String result) throws JSONException{
		 Vector<Lesson> alllesson = new  Vector<Lesson>();
		 JSONTokener jsonParser = new JSONTokener(result);
	     JSONObject person = (JSONObject) jsonParser.nextValue();

	     JSONArray stu = person.getJSONArray("student");
	     
	     for(int i = 0 ; i < stu.length() ; i++){
	    	 Lesson lesson = new Lesson();
	    	 JSONObject temp = stu.getJSONObject(i);
	    	 lesson.setStudent_id(temp.getString("id"));
	    	 lesson.setStudent_name(temp.getString("name"));
	    	 lesson.setAttendance(temp.getString("attendance"));
	    	 lesson.setLessonstatu(temp.getString("lessonstatu"));
	    	 lesson.setHomework(temp.getString("homework"));
	    	 lesson.setLessontest(temp.getString("lessontest"));
	    	 lesson.setScore(temp.getString("score"));
	    	 alllesson.add(lesson);
	     }
	    

		return alllesson;
		
	}

}
