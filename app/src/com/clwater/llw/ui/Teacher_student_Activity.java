package com.clwater.llw.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONException;

import com.clwater.llw.R;
import com.clwater.llw.model.Student;
import com.clwater.llw.utils.analysis.StudentAnalysis;
import com.clwater.llw.utils.network.AddStudentNetWork;
import com.clwater.llw.utils.network.DeleteStudentNetWork;
import com.clwater.llw.utils.network.StudentAdminNetWork;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Teacher_student_Activity extends Activity implements OnClickListener {

	//private Button test;
	public static  Activity activity;
	private Button tea_stuadm_delete , tea_stuadm_add , tea_stuadm_update;
	private ListView listview ;
	public ProgressDialog pr;
	private String chhooseid;
	List<Map<String, Object>> list;
	private Vector<Student> allstudent = new Vector<Student>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tea_stuadm);
		activity = this;
		
		String result = StudentAdminNetWork.getRestaurant(activity);
		//Log.d("llw", "query: " + result);
		
		if(result.equals("{\"statu\":\"error\"}")){
			Toast.makeText(activity, "暂无数据，请添加", Toast.LENGTH_SHORT).show();
		}else{
			try {
				allstudent = StudentAnalysis.getstudent(result);
				} catch (JSONException e) {			}
			//Toast.makeText(activity, "xing", Toast.LENGTH_SHORT).show();
		}
		

			
		create();
	}
	private void create() {
		tea_stuadm_add = (Button) findViewById(R.id.tea_stuadm_add);
		tea_stuadm_add.setOnClickListener(this);
		
		listview = (ListView) findViewById(R.id.tes_stu_list);
		
		 list = getData();  
		 listview.setAdapter(new ListStuAmdAdspter(this, list));
		
	}
	
	public List<Map<String, Object>> getData(){  
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < allstudent.size(); i++) {  
            Map<String, Object> map=new HashMap<String, Object>();  
            map.put("name", "" +  allstudent.get(i).getName());  
            map.put("id", "" +  allstudent.get(i).getId());  
            map.put("classroom", "" +  allstudent.get(i).getClassroom());  
            list.add(map);  
        }  
        return list;  
    }  
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tea_stuadm_add:
			Intent insert = new Intent(this , StudentAdminMessageActivity.class);
			insert.putExtra("statu", "add");
			startActivity(insert);
			this.finish();
			break;
		//case R.id.

		default:
			break;
		}
		
	}
	
	public  void setdelete(String id){
		chhooseid = id ;
		pr = ProgressDialog.show(Teacher_student_Activity.this, null, "删除中......");
		DeleteTask task=new DeleteTask();
        task.execute();

	}
	
	
	private class DeleteTask extends AsyncTask<Void, Void, Void> {
		String re = null;
	    protected Void doInBackground(Void... params) {
			re = DeleteStudentNetWork.getRestaurant(activity, chhooseid);
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
			//String result = StudentAdminNetWork.getRestaurant(activity);
			//listview.setAdapter(new ListStuAmdAdspter(this, list));
			activity.startActivity(new Intent(activity , Teacher_student_Activity.class));
			activity.finish();
		}else{
			Toast.makeText(activity, "操作失败，请重试", Toast.LENGTH_SHORT).show();
		}
	}

	



class ListStuAmdAdspter extends BaseAdapter {  
	  
   private List<Map<String, Object>> data;  
   private LayoutInflater layoutInflater;  
   private Context context;  
   public ListStuAmdAdspter(Context context,List<Map<String, Object>> data){  
       this.context=context;  
       this.data=data;  
       this.layoutInflater=LayoutInflater.from(context);  
   }  
   /** 
    * 组件集合，对应list.xml中的控件 
    * @author Administrator 
    */  
   public final class Zujian{  
       public TextView name ,id ,classname;
       public Button delete ,update;

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
           convertView=layoutInflater.inflate(R.layout.list_tea_stu, null);  
           zujian.name=(TextView)convertView.findViewById(R.id.list_name);  
           zujian.id=(TextView)convertView.findViewById(R.id.list_id);  
           zujian.classname=(TextView)convertView.findViewById(R.id.list_classroom);  
           zujian.update=(Button)convertView.findViewById(R.id.list_update);
           
           final String id = zujian.id.getText().toString();
           final String name = zujian.name.getText().toString();
           final String classroom = zujian.classname.getText().toString();
           
           zujian.update.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent update = new Intent(context , StudentAdminMessageActivity.class);
					update.putExtra("statu", "update");
					update.putExtra("id", (String)data.get(position).get("id"));
					update.putExtra("name", (String)data.get(position).get("name"));
					update.putExtra("classroom", (String)data.get(position).get("classroom"));
					
					context.startActivity(update);
					
					Teacher_student_Activity.activity.finish();
;				}
			});
           zujian.delete=(Button)convertView.findViewById(R.id.list_delete);
           zujian.delete.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					notidialog();
				}

				private void notidialog() {
					AlertDialog.Builder builder=new AlertDialog.Builder(context);  
			        builder.setTitle("提示");   
			        builder.setMessage("是否确认删除id为" + (String)data.get(position).get("id") + "的学生信息？"); 
			        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			            @Override  
			            public void onClick(DialogInterface dialog, int which) {  
			            	setdelete((String)data.get(position).get("id"));
			                dialog.dismiss(); 
			            }  
			        });  
			        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { 
			            @Override  
			            public void onClick(DialogInterface dialog, int which) {  
			                dialog.dismiss();  
			            }  
			        });  
			        builder.create().show();  
					
				}
			});
           convertView.setTag(zujian);  
       }else{  
           zujian=(Zujian)convertView.getTag();  
       }  
       
       
       
       //绑定数据  
       zujian.name.setText((String)data.get(position).get("name"));  
       zujian.id.setText((String)data.get(position).get("id")); 
       zujian.classname.setText((String)data.get(position).get("classroom"));  
       return convertView;  
   }  
}

}