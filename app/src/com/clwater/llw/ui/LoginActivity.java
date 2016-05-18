package com.clwater.llw.ui;


import com.clwater.llw.MainActivity;
import com.clwater.llw.R;
import com.clwater.llw.model.User;
import com.clwater.llw.utils.network.LoginNetWork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	public Activity activity;
	private Button login_button;
	private EditText id , pw ;
	public static User user = new User();
	public ProgressDialog pr;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		activity = this;
		create();
	}
	private void create() {
		id = (EditText) findViewById(R.id.userid);
		pw = (EditText) findViewById(R.id.passwd);
		login_button = (Button) findViewById(R.id.log_button);
		login_button.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				
				
				
//				pr = ProgressDialog.show(LoginActivity.this, null, "登陆中......");
				user.setId(id.getText().toString());
				user.setPw(pw.getText().toString());
				
				testmeiwang();
//				LoginTask task=new LoginTask();
//		        task.execute();
			}
		});
	}
	
	private class LoginTask extends AsyncTask<Void, Void, Void> {
		String re = null;
	    protected Void doInBackground(Void... params) {
			 re = LoginNetWork.getRestaurant(activity, user);
	        return null;
	    }
		protected void onProgressUpdate(Void... progress){}
	    protected void onPostExecute(Void result){
	    	pdstatu(re);
	    }
	}
	

    private void pdstatu(String re) {
    	pr.dismiss();
    	
    	
    	
    	
    	
    	
    	if(re.equals("0"))
    		Toast.makeText(LoginActivity.this, "账号或密码错误，请重新登录", Toast.LENGTH_SHORT).show();
    	else {
    		
    		if(re.equals("1")){
    			Intent next = new Intent(this , TeacherActivity.class);
        		next.putExtra("userid", user.getId());
        		next.putExtra("userpw", user.getPw());
        		next.putExtra("userstatu", user.getStatu());
        		startActivity(next);
        		
        		this.finish();
    		}else{
//    			Intent next = new Intent(this , MainActivity.class);
//        		next.putExtra("userid", user.getId());
//        		next.putExtra("userpw", user.getPw());
//        		next.putExtra("userstatu", user.getStatu());
//        		startActivity(next);
    		}
    		
    	}
	}
	private void testmeiwang() {
		Intent next = new Intent(this , TeacherActivity.class);
		next.putExtra("userid", user.getId());
		next.putExtra("userpw", user.getPw());
		next.putExtra("userstatu", user.getStatu());
		startActivity(next);
		
		this.finish();		
	}

}
