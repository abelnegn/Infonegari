package com.infonegari.fragment;
import com.infonegari.activity.R;
import com.infonegari.activity.ShortMessage;
import com.infonegari.util.AdsImageView;
import com.infonegari.util.DialogHandler;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

public class HomeFragment extends Fragment{
	final Handler handler = new Handler();
	final Handler handlerNews = new Handler();
	private ImageSwitcher imageSwitcher;
    private TextView txtShortNumber;
    private DialogHandler dlgHandler;
    private static final int MENU_ITEM_ABOUT = 2000;
    private static final int MENU_ITEM_HELP = 3000;
    private static final int MENU_ITEM_LANGUAGE = 4000;
    
	public HomeFragment(){}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
		getActivity().setTitle("Menu");
		getActivity().setTheme(R.id.myTitle);
		getActivity().setTitleColor(R.id.myTitle);
		
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        imageSwitcher = (ImageSwitcher) rootView.findViewById(R.id.adi_imageSwitcher);
        txtShortNumber = (TextView)rootView.findViewById(R.id.short_number_txt);
        txtShortNumber.setMovementMethod(LinkMovementMethod.getInstance());
        txtShortNumber.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent smsIntent = new Intent(getActivity(), ShortMessage.class);
				startActivity(smsIntent);
				
			}
		});

		AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer();
		
        return rootView;
    }
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItem mLanguge = menu.add(Menu.NONE, MENU_ITEM_LANGUAGE, Menu.NONE, "Language");
        mLanguge.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_language)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	mLanguge.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        
        //Help Menu
        MenuItem mHelp = menu.add(Menu.NONE, MENU_ITEM_HELP, Menu.NONE, "Update");
        mHelp.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_download)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	mHelp.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        
        //About
        MenuItem mAbout = menu.add(Menu.NONE, MENU_ITEM_ABOUT, Menu.NONE, "About");
        mAbout.setTitle("About");
        mAbout.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_adn)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	mAbout.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == MENU_ITEM_LANGUAGE) {
			FragmentManager fragmentManager = getFragmentManager();
			LanguageFragment fragment = new LanguageFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }else if(id == MENU_ITEM_HELP){
//			FragmentManager fragmentManager = getFragmentManager();
//			HelpFragment fragment = new HelpFragment();
//			fragmentManager.beginTransaction()
//					.replace(R.id.frame_container, fragment).commit(); 
    		dlgHandler = new DialogHandler();
    		dlgHandler.Confirm(getActivity(), "Update now?", "Please update the information. The update requires internet connection", 
    				 "Cancel", "Ok", cancel(), ok());
        }else{
			FragmentManager fragmentManager = getFragmentManager();
			AboutFragment fragment = new AboutFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
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
