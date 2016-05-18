package com.clwater.llw.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.clwater.llw.R;
import com.clwater.llw.model.User;
import com.clwater.llw.ui.Teacher_student_Activity.ListStuAmdAdspter;
import com.clwater.llw.ui.Teacher_student_Activity.ListStuAmdAdspter.Zujian;
import com.clwater.llw.utils.database.SSlDatabaseHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SuiShenJiActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	public Activity activity;
	private Button ssj_add;
	private EditText id , pw ;
	private User user = new User();
	public ProgressDialog pr;
	private String tablename;
	private ListView listview;
	private Vector<Ssjdate>  allssj = new Vector<Ssjdate>();
	List<Map<String, Object>> list;
	SSlDatabaseHelper ssldh;
	SQLiteDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.suishenji);
		activity = this;
		tablename = "ssl" + LoginActivity.user.getId();
		
		ssldh = new SSlDatabaseHelper(this);
		db = null;
		db = ssldh.getReadableDatabase();
		
				
	
		gethistory();
		create();
		
		
	}
	private void gethistory() {
		
		Cursor c = db.query(tablename,null,null,null,null,null,null);//查询并获得游标
		if(c.moveToFirst()){//判断游标是否为空
			int index = c.getCount();
			Log.d("llz", "c.getCount()" + c.getCount());
			//if(c.getCount() >= 3 ) index =2;
		    for(int i=0;i<index;i++){
		        c.move(i);//移动到指定记录
		        Ssjdate ssjdate = new Ssjdate();
		        ssjdate.setText(c.getString(c.getColumnIndex("text")));
		        String name = c.getString(c.getColumnIndex("text"));
		        if(name.length() > 10){
		        	name = name.substring(0 , 10);
		        }else{
		        	name = name.substring(0 , name.length());
		        }
		        ssjdate.setData(c.getString(c.getColumnIndex("data")));
		        
		        ssjdate.setName(name);
		        
		        allssj.add(ssjdate);

		    }
		}
		db.close();
	}
	private void create() {
		ssj_add = (Button) findViewById(R.id.ssj_add);
		ssj_add.setOnClickListener(this);
		
		listview = (ListView) findViewById(R.id.ssj_list);
		
		list = getData();  
		listview.setAdapter(new ListSSjAdspter(this, list));
		
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent next = new Intent(this , SSJMessageActivity.class);
		next.putExtra("statu", "update");
		next.putExtra("text", list.get(position).get("text").toString());
		startActivity(next);
		this.finish();
		
	}
	
	public List<Map<String, Object>> getData(){  
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < allssj.size(); i++) {  
            Map<String, Object> map=new HashMap<String, Object>();  
            map.put("name", "" +  allssj.get(i).getName());  
         //   Log.d("llqa", "2name: "+allssj.get(i).getName());
            map.put("data", "" +  allssj.get(i).getData());  
           // Log.d("llqa", "2date: "+allssj.get(i).getData());
            map.put("text", "" +  allssj.get(i).getText());  
            //Log.d("llqa", "2text: "+allssj.get(i).getText() );
            list.add(map);  
        }  
        return list;  
    }  
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ssj_add:
				Intent next = new Intent(this , SSJMessageActivity.class);
				next.putExtra("statu", "add");
				startActivity(next);
				this.finish();
			break;

		default:
			break;
		}
	}

   class Ssjdate{
	  public  String name , text ,data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
   }
   
   class ListSSjAdspter extends BaseAdapter {  
		  
	   private List<Map<String, Object>> data;  
	   private LayoutInflater layoutInflater;  
	   private Context context;  
	   public ListSSjAdspter(Context context,List<Map<String, Object>> data){  
	       this.context=context;  
	       this.data=data;  
	       
	       this.layoutInflater=LayoutInflater.from(context);  
	   }  
	   /** 
	    * 组件集合，对应list.xml中的控件 
	    * @author Administrator 
	    */  
	   public final class Zujian{  
	       public TextView name ,text ,data;

	   }  
	   @Override  
	   public int getCount() {  
	       return data.size();  
	   }  
	   /** 
	    * 获得某一位置的数据 
	    */  
	   @Override  
	   public Object getItem(int position) {  
	       return data.get(position);  
	   }  
	   /** 
	    * 获得唯一标识 
	    */  
	   @Override  
	   public long getItemId(int position) {  
	       return position;  
	   }  
	 
	   @Override  
	   public View getView(final int position, View convertView, ViewGroup parent) {  
	       Zujian zujian=null;  
	       if(convertView==null){  
	           zujian=new Zujian();  
	           //获得组件，实例化组件  
	           convertView=layoutInflater.inflate(R.layout.list_ssj, null);  
	           zujian.name=(TextView)convertView.findViewById(R.id.ssj_name);  
	           zujian.text=(TextView)convertView.findViewById(R.id.ssj_text);  
	           zujian.data=(TextView)convertView.findViewById(R.id.ssj_date);  
	           
	           //final String text = zujian.text.getText().toString();
	           //final String name = zujian.name.getText().toString();
	           //final String data = zujian.data.getText().toString();
	           
	      
	           convertView.setTag(zujian);  
	       }else{  
	           zujian=(Zujian)convertView.getTag();  
	       }  
	       
	       
	       
	       //绑定数据  
	       zujian.name.setText((String)data.get(position).get("name"));  
	       zujian.text.setText((String)data.get(position).get("text")); 
	       zujian.data.setText((String)data.get(position).get("data"));  
	       return convertView;  
	   }  
	}



}
