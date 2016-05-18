package com.clwater.llw.utils.database;

import com.clwater.llw.model.User;
import com.clwater.llw.ui.LoginActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SSlDatabaseHelper extends SQLiteOpenHelper {  
     private static final String name = "llw";  
     private static final int version = 1; 
     private String tablename;

     public SSlDatabaseHelper(Context context) {  
            super(context, name, null, version);  
            tablename = "ssl"+ LoginActivity.user.getId();
            //Log.d("llqa", " " + LoginActivity.user.getId());
     }  

    @Override  
    public void onCreate(SQLiteDatabase db) {  
          db.execSQL("CREATE TABLE IF NOT EXISTS  "+ tablename+ "(data varchar(20), text varchar(1000))");     
     }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}  
	

  
}  