package com.infonegari.activity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

public class ShortMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.short_message);
	}

	public void btnMovie(View view){
		try{
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage("8142", null, "CF", null, null);
			Toast.makeText(this, "Message sent successfully",
			         Toast.LENGTH_LONG).show();
		}catch (Exception e) {	
	         e.printStackTrace();
				Toast.makeText(this, "Faild to send message",
				         Toast.LENGTH_LONG).show();
		}
	}
}
