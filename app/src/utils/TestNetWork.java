package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.StrictMode;

public class TestNetWork {
	 public  static String getRestaurant(Context context){
	        String result = null;


	        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	                .detectDiskReads().detectDiskWrites().detectNetwork()
	                .penaltyLog().build());

	        HttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpget = new HttpGet("http://localhost/llw/login?id=2016001&pw=123456");
	        List<NameValuePair> params  =new ArrayList<NameValuePair>();

	        try {
	            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
	            HttpResponse httpResp = httpClient.execute(httpget);
	            if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
	            }
	        } catch (IOException e) {}
	        return result;
	    }
}
