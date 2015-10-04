package com.infonegari.activity;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;

public class UserSubscription extends Activity {
	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_subscription);
		
		webview = (WebView)findViewById(R.id.infonegari_webview);  
		webview.loadUrl("http://www.infonegari.com");
	}

}
