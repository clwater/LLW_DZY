package com.clwater.llw.ui;


import java.util.List;
import java.util.Map;

import com.clwater.llw.R;

import com.clwater.llw.utils.network.AddStudentNetWork;
import com.clwater.llw.utils.network.UpdateStudentNetWork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentAdminMessageActivity extends Activity implements OnClickListener {
	
	public static Activity activity;
	private Button canel ,ok;
	private EditText id , classroom , name ;
	public ProgressDialog pr;
	private String _id , _classroom , _name;
	private String statu;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.stuadmmes);
		activity = this;
		create();
		
		Intent intent = getIntent();
		statu  = intent.getStringExtra("statu");
		if(statu.equals("update")){
			_id = intent.getStringExtra("id");
			_name = intent.getStringExtra("name");
			_classroom = intent.getStringExtra("classroom");
			id.setKeyListener(null);
			setmes();
		}else if(statu.equals("add")){
			
		}
	}
	private void setmes() {

		id.setText(_id);
		classroom.setText(_classroom);
		name.setText(_name);
	}
	private void create() {
		id = (EditText) findViewById(R.id.sam_id);
		name = (EditText) findViewById(R.id.sam_name);
		classroom = (EditText) findViewById(R.id.sam_classroom);
		
		canel = (Button) findViewById(R.id.sam_canel);
		canel.setOnClickListener(this);

		ok = (Button) findViewById(R.id.sam_ok);
		ok.setOnClickListener(this);
		 
	}
	
	private class UpdateTask extends AsyncTask<Void, Void, Void> {
		String re = null;
	    protected Void doInBackground(Void... params) {
			re = UpdateStudentNetWork.getRestaurant(activity,  _id , _name , _classroom);
	        return null;
	    }
		protected void onProgressUpdate(Void... progress){}
	    protected void onPostExecute(Void result){
	    	pdstatu(re);
	    }
	}
	
	private class AddTask extends AsyncTask<Void, Void, Void> {
		String re = null;
	    protected Void doInBackground(Void... params) {
			re = AddStudentNetWork.getRestaurant(activity,  _id , _name , _classroom);
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
		}else{
			Toast.makeText(activity, "操作失败，请重试", Toast.LENGTH_SHORT).show();
		}
		
		startActivity(new Intent(this , Teacher_student_Activity.class));
		this.finish();
		
	}
	@Override
	public void onClick(View v) {
		_id = id.getText().toString();
		_name = name.getText().toString();
		_classroom = classroom.getText().toString();
		switch (v.getId()) {
		case R.id.sam_canel:
				startActivity(new Intent(this , Teacher_student_Activity.class));
				this.finish();
			break;
		case R.id.sam_ok:
			if(statu.equals("update")){
				setnewupdate();
			}else if(statu.equals("add")){
				setnewadd();
			}
			break;
		default:
			break;
		}
		
	}
	private void setnewadd() {
		pr = ProgressDialog.show(StudentAdminMessageActivity.this, null, "创建中......");
		AddTask task=new AddTask();
        task.execute();
		
	}
	private void setnewupdate() {
		pr = ProgressDialog.show(StudentAdminMessageActivity.this, null, "修改中......");
		UpdateTask task=new UpdateTask();
        task.execute();
		
	}
	

	
	



  
}  