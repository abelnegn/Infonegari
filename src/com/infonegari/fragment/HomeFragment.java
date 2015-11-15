package com.infonegari.fragment;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.UserSite;
import com.infonegari.util.DialogHandler;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
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
    private static final int MENU_ITEM_LOGIN = 2000;
    		
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
        
        //Short Notification button click
        btn_notify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				List<UserSite> userSite= Select.from(UserSite.class).
		    			where(Condition.prop("is_Active").eq("1")).list();
				if(userSite.size() > 0){
			        NotificationFragment notifyFragment = new NotificationFragment().newInstance();
			        notifyFragment.show(getFragmentManager().beginTransaction(), "NotificationFragment");					
				}else{
					LoginFragment login = new LoginFragment().newInstance();
					Bundle arguments = new Bundle();
					arguments.putString("Menu_Id", "Notification");
					login.setArguments(arguments);
					login.show(getFragmentManager().beginTransaction(), "LoginFragment");
				}
			}
		});

        //Add List button click
        btn_add_list.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				List<UserSite> userSite= Select.from(UserSite.class).
		    			where(Condition.prop("is_Active").eq("1")).list();
				if(userSite.size() > 0){
					ListingCategoryDialog categoryDialog = new ListingCategoryDialog().newInstance();
					categoryDialog.show(getFragmentManager().beginTransaction(), "ListingDialog");
				}else{
					LoginFragment login = new LoginFragment().newInstance();
					Bundle arguments = new Bundle();
					arguments.putString("Menu_Id", "AddList");
					login.setArguments(arguments);
					login.show(getFragmentManager().beginTransaction(), "LoginFragment");
				}				
			}
		});
        
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
        MenuItem mItemSearchClient = menu.add(Menu.NONE, MENU_ITEM_LOGIN, Menu.NONE, "Login");
        mItemSearchClient.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_power_off)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mItemSearchClient.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == MENU_ITEM_LOGIN) {
        	loginAsDiffUser();
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
	
	private void loginAsDiffUser(){
		LoginFragment login = new LoginFragment().newInstance();
		Bundle arguments = new Bundle();
		arguments.putString("Menu_Id", "DifferentUser");
		login.setArguments(arguments);
		login.show(getFragmentManager().beginTransaction(), "LoginFragment");
	}
}
