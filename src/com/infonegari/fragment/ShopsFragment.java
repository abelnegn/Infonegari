package com.infonegari.fragment;

import java.util.ArrayList;

import com.infonegari.activity.R;
import com.infonegari.adapter.NavDrawerListAdapter;
import com.infonegari.model.NavDrawerItem;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ShopsFragment extends Fragment{
	View rootView;
	private ListView mDrawerList;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private NavDrawerListAdapter adapter;
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	public ShopsFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_shops, container, false);
		
//		// load shop menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_shop_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_shop_icon);

		mDrawerList = (ListView)rootView.findViewById(R.id.list_shop_menu);
		
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding Shop menu nav drawer items to array
		// Cloth
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Electronic 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Furniture
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Computer Accessories
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Construction Supplies
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));	
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

//		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getActivity(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		if (savedInstanceState == null) {
			displayView(0);
		}
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
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
	private void displayView(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			
		}
	}
}
