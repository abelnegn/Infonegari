package com.infonegari.fragment;
import com.infonegari.activity.R;
import com.infonegari.util.DialogHandler;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment{
	final Handler handler = new Handler();
	final Handler handlerNews = new Handler();
    private DialogHandler dlgHandler;   
    		
	public HomeFragment(){}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
		getActivity().setTitle(getString(R.string.menu_home));
		
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        
        Button btn_notify = (Button) rootView.findViewById(R.id.btn_notify);
        
        Button btn_add_list = (Button) rootView.findViewById(R.id.btn_add_list);
        
        Button btn_short_sms = (Button) rootView.findViewById(R.id.btn_short_sms);
        
        Button btn_language = (Button) rootView.findViewById(R.id.btn_language);
        
        Button btn_download = (Button) rootView.findViewById(R.id.btn_download);
        
        Button btn_about = (Button) rootView.findViewById(R.id.btn_about);
        
        //Short sms button click
        btn_short_sms.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				ShortMessage smsFragment = new ShortMessage();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, smsFragment).commit();
			}
		});

        //Listening Language button click
        btn_language.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				LanguageFragment fragment = new LanguageFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
			}
		});

        //Listening Download button click
        btn_download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
	    		dlgHandler = new DialogHandler();
	    		dlgHandler.Confirm(getActivity(), getString(R.string.dlg_header_update), getString(R.string.dlg_detail_message), 
	    				getString(R.string.btn_later), getString(R.string.btn_ok), cancel(), ok());

			}
		});
        
        // Listening About button click
        btn_about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				AboutFragment fragment = new AboutFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
			}
		});
        
		return rootView;
    }
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    
	private Runnable ok(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	downloadData();
	        }
	    };
	    return runnable;
	}
	
	private void downloadData(){
		FragmentManager fragmentManager = getFragmentManager();
		DownloadDataFragment fragment = new DownloadDataFragment();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();
	}
	
	private Runnable cancel(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {        	
	        }
	    };
	    return runnable;
	}
}
