package com.infonegari.fragment;

import java.util.ArrayList;

import com.infonegari.activity.R;
import com.infonegari.adapter.EmergencyAdapter;
import com.infonegari.model.EmergencyItem;
import com.infonegari.util.DialogHandler;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class EmergencyCallFragment extends Fragment{
	private static final int MENU_ITEM_BACK = 2000;
	View rootView;
	private ListView mDrawerList;
	private String[] navMenuTitles;
	private String[] navMenuPhone;
	private TypedArray navMenuIcons;
	private EmergencyAdapter adapter;
	private ArrayList<EmergencyItem> navDrawerItems;
	private DialogHandler dlgHandler;
	private String phoneNo;
	
	public EmergencyCallFragment(){
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
  
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_emergency_call, container, false);
		mDrawerList = (ListView)rootView.findViewById(R.id.list_emergency_call);
		
		getActivity().setTitle(getString(R.string.db_important_info));
		
		//load important information items
		navMenuTitles = getResources().getStringArray(R.array.nav_important_info);
		navMenuPhone = getResources().getStringArray(R.array.nav_important_info_phone);
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		
		navDrawerItems = new ArrayList<EmergencyItem>();
		
		navDrawerItems.add(new EmergencyItem(navMenuTitles[0], navMenuIcons.getResourceId(8, -1), navMenuPhone[0]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[1], navMenuIcons.getResourceId(8, -1), navMenuPhone[1]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[2], navMenuIcons.getResourceId(8, -1), navMenuPhone[2]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[3], navMenuIcons.getResourceId(8, -1), navMenuPhone[3]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[4], navMenuIcons.getResourceId(8, -1), navMenuPhone[4]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[5], navMenuIcons.getResourceId(8, -1), navMenuPhone[5]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[6], navMenuIcons.getResourceId(8, -1), navMenuPhone[6]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[7], navMenuIcons.getResourceId(8, -1), navMenuPhone[7]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1), navMenuPhone[8]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[9], navMenuIcons.getResourceId(8, -1), navMenuPhone[9]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[10], navMenuIcons.getResourceId(8, -1), navMenuPhone[10]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[11], navMenuIcons.getResourceId(8, -1), navMenuPhone[11]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[12], navMenuIcons.getResourceId(8, -1), navMenuPhone[12]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[13], navMenuIcons.getResourceId(8, -1), navMenuPhone[13]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[14], navMenuIcons.getResourceId(8, -1), navMenuPhone[14]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[15], navMenuIcons.getResourceId(8, -1), navMenuPhone[15]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[16], navMenuIcons.getResourceId(8, -1), navMenuPhone[16]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[17], navMenuIcons.getResourceId(8, -1), navMenuPhone[17]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[18], navMenuIcons.getResourceId(8, -1), navMenuPhone[18]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[19], navMenuIcons.getResourceId(8, -1), navMenuPhone[19]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[20], navMenuIcons.getResourceId(8, -1), navMenuPhone[20]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[21], navMenuIcons.getResourceId(8, -1), navMenuPhone[21]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[22], navMenuIcons.getResourceId(8, -1), navMenuPhone[22]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[23], navMenuIcons.getResourceId(8, -1), navMenuPhone[23]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[24], navMenuIcons.getResourceId(8, -1), navMenuPhone[24]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[25], navMenuIcons.getResourceId(8, -1), navMenuPhone[25]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[26], navMenuIcons.getResourceId(8, -1), navMenuPhone[26]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[27], navMenuIcons.getResourceId(8, -1), navMenuPhone[27]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[28], navMenuIcons.getResourceId(8, -1), navMenuPhone[28]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[29], navMenuIcons.getResourceId(8, -1), navMenuPhone[29]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[30], navMenuIcons.getResourceId(8, -1), navMenuPhone[30]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[31], navMenuIcons.getResourceId(8, -1), navMenuPhone[31]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[32], navMenuIcons.getResourceId(8, -1), navMenuPhone[32]));
		navDrawerItems.add(new EmergencyItem(navMenuTitles[33], navMenuIcons.getResourceId(8, -1), navMenuPhone[33]));
		
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		adapter = new EmergencyAdapter(getActivity(), navDrawerItems);
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
		phoneNo = navMenuPhone[position];
		dlgHandler = new DialogHandler();
		dlgHandler.Confirm(getActivity(), navMenuTitles[position], phoneNo + " " + getString(R.string.btn_call) , 
				getString(R.string.btn_cancel), getString(R.string.btn_ok), cancel(), ok());
	}
	
	private Runnable ok(){
		Runnable runnable = new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	Intent intent = new Intent(Intent.ACTION_CALL);

	        	intent.setData(Uri.parse("tel:" + phoneNo));
	        	getActivity().startActivity(intent);
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
}
