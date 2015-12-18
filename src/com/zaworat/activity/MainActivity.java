package com.zaworat.activity;

import com.zaworat.fragment.HomeFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setFragment();
	}

    private void setFragment(){
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        HomeFragment fragment = new HomeFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        fragmentTransaction.replace(R.id.frame_container,fragment).commit();
    }
}
