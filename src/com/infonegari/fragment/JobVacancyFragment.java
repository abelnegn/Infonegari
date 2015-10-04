package com.infonegari.fragment;

import com.infonegari.activity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class JobVacancyFragment extends Fragment{
	View rootView;
	
	public JobVacancyFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_job_vacancy, container, false);
		
		return rootView;
	}
}
