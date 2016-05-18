package com.clwater.llw.ui;


import java.util.Vector;

import com.clwater.llw.MainActivity;
import com.clwater.llw.R;
import com.clwater.llw.model.Lesson;
import com.clwater.llw.model.User;
import com.clwater.llw.utils.network.LoginNetWork;
import com.clwater.llw.utils.network.UpdateLeassonNetWork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LeassonMessageActivity extends Activity {
	
	public Activity activity;
	private Button les_adm_ok , les_adm_up;
	private EditText id , pw ;
	public static User user = new User();
	private Lesson lesson = new Lesson();
	private TextView les_adm_name , les_adm_id, les_adm_score;
	private EditText les_adm_sj , les_adm_bj , les_adm_cdzt,les_adm_kk ,
						les_adm_leassonstatu ,les_adm_leassontest1,les_adm_leassontest2,les_adm_leassontest3,
						les_adm_homework1,les_adm_homework2,les_adm_homework3;
	public ProgressDialog pr;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.leassonmes);
		activity = this;
		
		
		Intent intent = getIntent();
		
		lesson.setStudent_id(intent.getStringExtra("id"));
		lesson.setStudent_name(intent.getStringExtra("name"));
		lesson.setAttendance(intent.getStringExtra("attendance"));
		lesson.setHomework(intent.getStringExtra("homework"));
		lesson.setLessonstatu(intent.getStringExtra("lessonstatu"));
		lesson.setLessontest(intent.getStringExtra("lessontest"));
		lesson.setScore(intent.getStringExtra("score"));
		
		Log.d("llee","id " +  lesson.getStudent_id());
		Log.d("llee", "name " +  lesson.getStudent_name());
		Log.d("llee", "att " +  lesson.getAttendance());
		Log.d("llee", "home " +  lesson.getHomework());
		Log.d("llee", "statu " +  lesson.getLessonstatu());
		Log.d("llee", "test " +  lesson.getLessontest());
		Log.d("llee", "score " +  lesson.getScore());
//		
		
		create();
		
		
		//Log.d("llw", ""+ lesson.getAttendance());
	}
	private void create() {
		les_adm_name  = (TextView) findViewById(R.id.les_adm_name);
		les_adm_name.setText(lesson.getStudent_name());
		les_adm_id  = (TextView) findViewById(R.id.les_adm_id);
		les_adm_id.setText(lesson.getStudent_id());
		les_adm_score  = (TextView) findViewById(R.id.les_adm_score);
		les_adm_score.setText(lesson.getScore());
		
		String attendance = lesson.getAttendance();
		String[] att = attendance.split("@");
		Log.d("llee", lesson.getAttendance());
		//Log.d("llas", att[0]);
 		les_adm_sj = (EditText) findViewById(R.id.les_adm_sj);
 		les_adm_sj.setText(att[0]);
		les_adm_bj = (EditText) findViewById(R.id.les_adm_bj);
		les_adm_bj.setText(att[1]);
		les_adm_cdzt = (EditText) findViewById(R.id.les_adm_cdzt);
		les_adm_cdzt.setText(att[2]);
		les_adm_kk = (EditText) findViewById(R.id.les_adm_kk);
		les_adm_kk.setText(att[3]);
		
		les_adm_leassonstatu = (EditText) findViewById(R.id.les_adm_leassonstatu);
		les_adm_leassonstatu.setText(lesson.getLessonstatu());
		
		String leassontest = lesson.getLessontest();
		String[] ltest = leassontest.split("@");
		les_adm_leassontest1 = (EditText) findViewById(R.id.les_adm_leassontest1);
		les_adm_leassontest1.setText(ltest[0]);
		les_adm_leassontest2 = (EditText) findViewById(R.id.les_adm_leassontest2);
		les_adm_leassontest2.setText(ltest[1]);
		les_adm_leassontest3 = (EditText) findViewById(R.id.les_adm_leassontest3);
		les_adm_leassontest3.setText(ltest[2]);
		
		
		String leassonhomework = lesson.getHomework();
		String[] lhome = leassonhomework.split("@");
		les_adm_homework1 = (EditText) findViewById(R.id.les_adm_homework1);
		les_adm_homework1.setText(lhome[0]);
		les_adm_homework2 = (EditText) findViewById(R.id.les_adm_homework2);
		les_adm_homework2.setText(lhome[1]);
		les_adm_homework3 = (EditText) findViewById(R.id.les_adm_homework3);
		les_adm_homework3.setText(lhome[2]);
		
		
		les_adm_ok =(Button)findViewById(R.id.les_adm_ok);
		les_adm_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				int sj , bj ,cdzt , kk , statu , test1,test2,test3,home1,home2,home3 , 
					kq,pscj,zycj , score;
				
				sj =  Integer.valueOf(les_adm_sj.getText().toString());
				bj =  Integer.valueOf(les_adm_bj.getText().toString());
				cdzt =  Integer.valueOf(les_adm_cdzt.getText().toString());
				kk =  Integer.valueOf(les_adm_kk.getText().toString());
				statu =  Integer.valueOf(les_adm_leassonstatu.getText().toString());
				test1 =  Integer.valueOf(les_adm_leassontest1.getText().toString());
				test2 =  Integer.valueOf(les_adm_leassontest2.getText().toString());
				test3 =  Integer.valueOf(les_adm_leassontest3.getText().toString());
				home1 =  Integer.valueOf(les_adm_homework1.getText().toString());
				home2 =  Integer.valueOf(les_adm_homework2.getText().toString());
				home3 =  Integer.valueOf(les_adm_homework3.getText().toString());
				
				
				
					score = 0;
				
					if(statu > 10 || test1 >10||test2 >10||test3 >10||home1 >10||home2 >10 || home3 >10){
						Toast.makeText(activity, "数据不合格，请检查后输入", Toast.LENGTH_SHORT).show();
					}else{
						kq = 30 - cdzt * 3 - kk * 5;
						if(kq < 0) kq = 0;
						pscj = test1 + test2 + test3;
						zycj = home1 + home2 + home3;
						
						if(kk >=3) score = 0 ;
						else score = kq + statu + pscj + zycj;
						
						lesson.setAttendance("" + sj + "@" + bj + "@" + cdzt + "@" + kk);
						lesson.setLessonstatu("" + statu);
						lesson.setLessontest("" + test1 + "@" + test2 + "@" + test3);
						lesson.setHomework("" + home1 + "@" + home2 + "@" + home3);
						
						les_adm_score.setText("" + score);
						
						lesson.setScore("" + score);
	
						
					}
					
				
				
			}
		});
		
		les_adm_up = (Button) findViewById(R.id.les_adm_up);
		les_adm_up.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pr = ProgressDialog.show(LeassonMessageActivity.this, null, "保存中......");
				LoginTask task = new LoginTask();
				task.execute();
			}
		});
		
		
		
	}
	
	private class LoginTask extends AsyncTask<Void, Void, Void> {
		String re = null;
	    protected Void doInBackground(Void... params) {
			 re = UpdateLeassonNetWork.getRestaurant(activity, lesson);
	        return null;
	    }
		protected void onProgressUpdate(Void... progress){}
	    protected void onPostExecute(Void result){
	    	pdstatu(re);
	    }
	
	}
	
	
	private void pdstatu(String re) {
		pr.dismiss();
		if(re.equals("{\"statu\":\"success\"}")){
			Toast.makeText(activity, "操作成功", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this , Teacher_lesson_Activity.class));
			this.finish();
		}else{
			Toast.makeText(activity, "操作失败，请重试", Toast.LENGTH_SHORT).show();
		}
		
		
    		
    	}

}
