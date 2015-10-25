package com.infonegari.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadAdsImage {
	private static final String SERVICE_URL = "http://www.infonegari.com/img/ads/mobile";
	private Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	public DownloadAdsImage(Context context){
		this.context = context;
		
		if (Network.isOnline(context)) {
	        WebServiceTask wsAdsImage = new WebServiceTask(WebServiceTask.GET_TASK, context, "");
	        String fileAddress = SERVICE_URL + "/" + "adsImageNames.txt";
	        wsAdsImage.execute(new String[] { fileAddress });
	    }	   
	}
	
    public void writeToSDFile(String fileName, byte[] imageFile){

		FileOutputStream fileOutputStream = null;
		File file = null;

		try {
			file = context.getFilesDir();
			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(imageFile);
            PrintWriter pw = new PrintWriter(fileOutputStream);
            pw.flush();
            pw.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
    }
    
	private class WebServiceTask extends AsyncTask<String, Integer, String>{
    	public static final int POST_TASK = 1;
        public static final int GET_TASK = 2;
         
        private static final String TAG = "WebServiceTask";
        private static final int CONN_TIMEOUT = 30000;
        private static final int SOCKET_TIMEOUT = 50000;
        private int taskType = GET_TASK;
        private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        
        public WebServiceTask(int taskType, Context mContext, String processMessage) {
            this.taskType = taskType;
        }

        @Override
        protected void onPreExecute() {
        }
        
        private void downloadNotification(){
        	byte[] resultFile = null;
        	final String NOTIFICATION_URL = "http://www.infonegari.com/images/mobile/notification";
        	String notAddress = NOTIFICATION_URL + "/" + "notification.txt";
        	try { 
		        HttpClient httpClient = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet(notAddress);
		        HttpResponse resp = httpClient.execute(httpGet);
		        if(resp.getStatusLine().getStatusCode() == 200){
		        	resultFile = inputStreamByteArray(resp.getEntity().getContent());
		        	 writeToSDFile("notification.txt", resultFile);
		        } 
        	 } catch (Exception e) {      
                 Log.e(TAG, e.getLocalizedMessage(), e);    
             }     
        }
        private void getImageFile(byte[] resultFile){
    		 FileOutputStream fileOutputStream = null;
    		 File file = null;
    		 
			try {
				file = context.getFilesDir();
				fileOutputStream = context.openFileOutput("adsImageNames.txt", Context.MODE_PRIVATE);
				fileOutputStream.write(resultFile);
                PrintWriter pw = new PrintWriter(fileOutputStream);
                pw.flush();
                pw.close();
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			}
        }
        
        protected String doInBackground(String... urls) {
        	 
            String url = urls[0];
    		byte[] resultFile = null;
            
            HttpResponse response = doResponse(url);
 
            if (response != null) {
                try { 
                	resultFile = inputStreamByteArray(response.getEntity().getContent());
                	getImageFile(resultFile);
                	FileInputStream fileInputStream = context.openFileInput("adsImageNames.txt");
                	BufferedReader reader;

                	if(fileInputStream != null){
                		reader = new BufferedReader(new InputStreamReader(fileInputStream));
                		 byte[] resultImage = null;
                		String line;
                		while ((line = reader.readLine()) != null) {
                	        HttpClient httpClient = new DefaultHttpClient();
                	        HttpGet httpGet = new HttpGet(SERVICE_URL + "/" + line);
                	        HttpResponse resp = httpClient.execute(httpGet);
                	        if(resp.getStatusLine().getStatusCode() == 200){
                	        	resultImage = inputStreamByteArray(resp.getEntity().getContent());
                	        	 writeToSDFile(line, resultImage);
                	        }               	                       			
                		}
                	}
//                	downloadNotification();
                } catch (Exception e) {      
                    Log.e(TAG, e.getLocalizedMessage(), e);    
                }
 
            } 
            return url;
        }   
        
        @Override
        protected void onPostExecute(String response) {                   
        }
        
        // Establish connection and socket (data retrieval) timeouts
        private HttpParams getHttpParams() {
             
            HttpParams htpp = new BasicHttpParams();
             
            HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
            HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);
             
            return htpp;
        }
        
        private HttpResponse doResponse(String url) {
            
            // Use our connection and data timeouts as parameters for our
            // DefaultHttpClient
            HttpClient httpclient = new DefaultHttpClient(getHttpParams());
 
            HttpResponse response = null;
 
            try {
                switch (taskType) {
 
                case POST_TASK:
                    HttpPost httppost = new HttpPost(url);
                    // Add parameters
                    httppost.setEntity(new UrlEncodedFormEntity(params));
 
                    response = httpclient.execute(httppost);
                    break;
                case GET_TASK:
                    HttpGet httpget = new HttpGet(url);
                    response = httpclient.execute(httpget);
                    break;
                }
            } catch (Exception e) {
 
                Log.e(TAG, e.getLocalizedMessage(), e);
 
            }
            return response;
        }
        
        private byte[] inputStreamByteArray(InputStream is) {
        	 
        	ByteArrayOutputStream os = new ByteArrayOutputStream();
        		 	
            try {
            	byte[] buffer = new byte[0xFFFF];
            	for (int len; (len = is.read(buffer)) != -1;){
                    os.write(buffer, 0, len);
	                os.flush();
                }
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }
			return os.toByteArray();
        }        
    }

}
