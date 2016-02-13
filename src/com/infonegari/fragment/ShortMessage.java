package com.infonegari.fragment;

import java.util.ArrayList;

import com.infonegari.activity.R;
import com.infonegari.adapter.NavDrawerListAdapter;
import com.infonegari.model.NavDrawerItem;
import com.infonegari.util.DialogHandler;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ShortMessage extends Fragment{
	private ListView mDrawerList;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private NavDrawerListAdapter adapter;
	private ArrayList<NavDrawerItem> navDrawerItems;
	View rootView;
	String smsCode = "";
	private DialogHandler dlgHandler;
	private static final int MENU_ITEM_BACK = 2000;
	
	public ShortMessage(){
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItem mItemSearchClient = menu.add(Menu.NONE, MENU_ITEM_BACK, Menu.NONE, "Back");
        mItemSearchClient.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_backward)
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

        if (id == MENU_ITEM_BACK) {
			FragmentManager fragmentManager = getFragmentManager();
			HomeFragment fragment = new HomeFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onResume() {
       super.onResume();

       getView().setFocusableInTouchMode(true);
       getView().requestFocus();
       getView().setOnKeyListener(new View.OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {

              if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
      			FragmentManager fragmentManager = getFragmentManager();
    			HomeFragment fragment = new HomeFragment();
    			fragmentManager.beginTransaction()
    					.replace(R.id.frame_container, fragment).commit();
                   return true;
               }
               return false;
           }
       });
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.short_message, container, false);
		
		getActivity().setTitle(getString(R.string.title_activity_short_message));
		
		// load short message menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_sms);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_sms_icon);

		mDrawerList = (ListView)rootView.findViewById(R.id.list_sms_menu);
		
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding sms menu nav drawer items to array
		// Christian Girl
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(1, -1)));
		// Christian Boy 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(0, -1)));
		// Muslim Girl
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(1, -1)));
		// Muslim Boy
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(0, -1)));
		// Hagerigna Girl
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(1, -1)));
		// Hagerigna Boy
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(0, -1)));
		
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

//		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getActivity(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		return rootView;
	}
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}
	
	private void displayView(int position) {
		String messageDetail = "";
		switch (position) {
		case 0:
			smsCode = "CG";
			messageDetail = navMenuTitles[0];
			break;
		case 1:
			smsCode = "CB";
			messageDetail = navMenuTitles[1];
			break;
		case 2:
			smsCode = "MG";
			messageDetail = navMenuTitles[2];
			break;
		case 3:
			smsCode = "MB";
			messageDetail = navMenuTitles[3];
			break;
		case 4:
			smsCode = "HG";
			messageDetail = navMenuTitles[4];
			break;
		case 5:
			smsCode = "HB";
			messageDetail = navMenuTitles[5];
			break;
		default:
			break;
		}
		
		if (!smsCode.equals("")) {
    		dlgHandler = new DialogHandler();
    		dlgHandler.Confirm(getActivity(), getString(R.string.sms_request_header), messageDetail + "?", 
    				getString(R.string.btn_No), getString(R.string.btn_yes), cancel(), ok());
		}
	}
	
	private void sendSms(){
		try{
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage("8142", null, smsCode, null, null);
			Toast.makeText(getActivity(), "Message sent successfully",
			         Toast.LENGTH_LONG).show();
		}catch (Exception e) {	
	         e.printStackTrace();
				Toast.makeText(getActivity(), "Faild to send message",
				         Toast.LENGTH_LONG).show();
		}		
	}
	
	private Runnable ok(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	sendSms();
	        }
	    };
	    return runnable;
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
