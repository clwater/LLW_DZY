package com.clwater.llw.ui.adapter;

import java.util.List;
import java.util.Map;

import com.clwater.llw.MainActivity;
import com.clwater.llw.R;
import com.clwater.llw.ui.StudentAdminMessageActivity;
import com.clwater.llw.ui.Teacher_student_Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListStuAmdAdspter_base extends BaseAdapter {  
  
    private List<Map<String, Object>> data;  
    private LayoutInflater layoutInflater;  
    private Context context;  
    public ListStuAmdAdspter_base(Context context,List<Map<String, Object>> data){  
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
			            	//StudentAdminMessageActivity s =  (StudentAdminMessageActivity) context;
			            	//s.setdelete(id);
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