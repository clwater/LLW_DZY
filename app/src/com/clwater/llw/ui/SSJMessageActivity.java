package com.clwater.llw.ui;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.clwater.llw.R;
import com.clwater.llw.utils.database.SSlDatabaseHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SSJMessageActivity extends Activity implements OnClickListener {
	
	public static Activity activity;
	private Button canel ,ok  ,delete;
	public ProgressDialog pr;
	private String statu;
	private String _text;
	private EditText text;
	private SSlDatabaseHelper ssldh;
	private  SQLiteDatabase db;
	private String tablename;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ssjmes);
		activity = this;
		create();
		
		tablename = "ssl" + LoginActivity.user.getId();
		ssldh = new SSlDatabaseHelper(this);
		db = null;
		db = ssldh.getReadableDatabase();
		
		Intent intent = getIntent();
		statu  = intent.getStringExtra("statu");
		Log.d("llqa", "statu : " + statu);
		if(statu.equals("update")){
			_text = intent.getStringExtra("text");
			setmes();
		}else if(statu.equals("add")){
			
		}
	}
	private void setmes() {
		text.setText(_text);
	}
	private void create() {
		text = (EditText) findViewById(R.id.ssj_text);
		canel = (Button) findViewById(R.id.ssj_canel);
		canel.setOnClickListener(this);

		ok = (Button) findViewById(R.id.ssj_ok);
		ok.setOnClickListener(this);
		
		delete = (Button) findViewById(R.id.ssj_delete);
		delete.setOnClickListener(this);
		 
	}
	


	@Override
	public void onClick(View v) {
		//_id = id.getText().toString();
		switch (v.getId()) {
		case R.id.ssj_canel:
				startActivity(new Intent(this , SuiShenJiActivity.class));
				this.finish();
			break;
		case R.id.ssj_ok:
			if(statu.equals("update")){
				if(text.getText().toString().length() < 1){
					Toast.makeText(activity, "请输入随身记。", Toast.LENGTH_SHORT).show();
				}else{
					dupdatessj();
				}
			}else if(statu.equals("add")){
				if(text.getText().toString().length() < 1){
					Toast.makeText(activity, "请输入随身记。", Toast.LENGTH_SHORT).show();
				}else{
					addssj();
				}
			}
			break;
		case R.id.ssj_delete:
			if(statu.equals("update")){		
				deletessj();
			}else{
				Toast.makeText(activity, "这是新的随身记，请按取消返回。", Toast.LENGTH_SHORT).show();
			}
			
			break;
		default:
			break;
		}
		
	}
	private void dupdatessj() {
		String _data = getdata();
		String sql = "UPDATE " + tablename + " SET text = '" + text.getText().toString() + "' , data='" + _data + "'  WHERE text = '" + _text +"'";
		db.execSQL(sql);//执行修改	
		Toast.makeText(activity, "修改成功", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this , SuiShenJiActivity.class));
		this.finish();
	}
	private void deletessj() {
		String sql = "delete from "  + tablename + " where text='"+_text +"'";//删除操作的SQL语句
		db.execSQL(sql);//执行SQL语句
		db.close();
		Toast.makeText(activity, "删除成功", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this , SuiShenJiActivity.class));
		this.finish();
		
	}
	private void addssj() {
		
		String _data = getdata();
		
		String sql = "insert into " + tablename + " (data,text) values (' "+ _data +"','"+ text.getText().toString() +"')";
		db.execSQL(sql);//执行SQL语句
		db.close();
		
		Toast.makeText(activity, "添加成功", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this , SuiShenJiActivity.class));
		this.finish();
		
	}
	private String getdata() {
		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日");     
		Date    curDate    =   new    Date(System.currentTimeMillis());     
		String    str    =    formatter.format(curDate);    
		return str;
	}


	
	



  
}  