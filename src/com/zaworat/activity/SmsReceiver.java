package com.zaworat.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) 
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = "";            
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                str += msgs[i].getMessageBody().toString();      
            }
            writeToSDFile(str);
        }                         
    }
    
    private void checkExternalMedia(){
    	  boolean mExternalStorageAvailable = false;
	      boolean mExternalStorageWriteable = false;
	      String state = Environment.getExternalStorageState();
	
	      if (Environment.MEDIA_MOUNTED.equals(state)) {
	          // Can read and write the media
	          mExternalStorageAvailable = mExternalStorageWriteable = true;
	      } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	          // Can only read the media
	          mExternalStorageAvailable = true;
	          mExternalStorageWriteable = false;
	      } else {
	          // Can't read or write
	          mExternalStorageAvailable = mExternalStorageWriteable = false;
	      }   
	      String result ="\n\nExternal Media: readable="
	              +mExternalStorageAvailable+" writable="+mExternalStorageWriteable;
  }
    
    private void writeToSDFile(String sms){

        // Find the root of the external storage.
    	String cont = sms.substring(0, 5);
    	if(cont.equals("{pcId")){
        	checkExternalMedia();
            File root = android.os.Environment.getExternalStorageDirectory(); 
            File dir = new File (root.getAbsolutePath() + "/Download");
            dir.mkdirs();
            File file = new File(dir, "configFile.txt");
            try {
                FileOutputStream f = new FileOutputStream(file);
                f.write(sms.getBytes());
                PrintWriter pw = new PrintWriter(f);
                pw.flush();
                pw.close();
                f.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }     		
    	}  
    }
}
