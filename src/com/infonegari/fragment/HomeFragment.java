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
        
        /**
         * Creating all buttons instances
         * */
        // Dashboard News feed button
        Button btn_newsfeed = (Button) rootView.findViewById(R.id.btn_news_feed);
        
        // Dashboard Friends button
        Button btn_friends = (Button) rootView.findViewById(R.id.btn_friends);
        
        // Dashboard Messages button
        Button btn_messages = (Button) rootView.findViewById(R.id.btn_messages);
        
        // Dashboard Places button
        Button btn_places = (Button) rootView.findViewById(R.id.btn_places);
        
        // Dashboard Events button
        Button btn_events = (Button) rootView.findViewById(R.id.btn_events);
        
        // Dashboard Photos button
        Button btn_photos = (Button) rootView.findViewById(R.id.btn_photos);
        
        // Listening Messages button click
        btn_messages.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				ShortMessage smsFragment = new ShortMessage();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, smsFragment).commit();
			}
		});

        // Listening Language button click
        btn_newsfeed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				LanguageFragment fragment = new LanguageFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
			}
		});

        // Listening Download button click
        btn_events.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
	    		dlgHandler = new DialogHandler();
	    		dlgHandler.Confirm(getActivity(), getString(R.string.dlg_header_update), getString(R.string.dlg_detail_message), 
	    				getString(R.string.btn_later), getString(R.string.btn_ok), cancel(), ok());

			}
		});
        
        // Listening About button click
        btn_places.setOnClickListener(new View.OnClickListener() {
			
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
