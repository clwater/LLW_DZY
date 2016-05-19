package com.clwater.llw.ui;

import java.util.Calendar;

import com.clwater.llw.R;
import com.clwater.llw.model.User;
import com.clwater.llw.utils.tools.BetweenData;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class Schedule  extends Activity{
	public Activity activity;
	private Button login_button;
	private EditText id , pw ;
	public static User user = new User();
	public ProgressDialog pr;
	private WebView webView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.schedule);
		activity = this;
		//create();
		
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int getData_year = c.get(Calendar.YEAR);
        int getData_mouth = c.get(Calendar.MONTH);
        int getData_day = c.get(Calendar.DATE);
        int now_week = BetweenData.getWeekNumber(getData_year , getData_mouth + 1 ,getData_day); 
        now_week++;
		
		webView = (WebView) findViewById(R.id.web);
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.loadUrl("http://cityuit.wuxiwei.cn/index.php/Home/Students/appWeek/auth/201312026/week/" + now_week);
		webView.setWebViewClient(new WebViewClient(){
		      @Override
		      public boolean shouldOverrideUrlLoading(WebView view, String url) {
		          view.loadUrl(url);
		          return true;
		      }
		  });
	}

}
