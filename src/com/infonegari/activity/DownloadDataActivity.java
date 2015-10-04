package com.infonegari.activity;

import com.infonegari.fragment.DownloadDataFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;



public class DownloadDataActivity extends FragmentActivity{
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startDownloadActivity();
        setContentView(R.layout.activity_download_data);

    }
	
    private void startDownloadActivity() {
    	DownloadDataFragment downloadDataFragment = new DownloadDataFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.download_data_container, downloadDataFragment);
        fragmentTransaction.commit();
    }
}
