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

public class SalesFragment extends Fragment{
	View rootView;
	private ListView mDrawerList;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private NavDrawerListAdapter adapter;
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	public SalesFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_sales, container, false);
		
		// load sales menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_sales_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_sales_icon);

		mDrawerList = (ListView)rootView.findViewById(R.id.list_sales_menu);
		
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding sales menu nav drawer items to array
		// Car for Sale
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// House for Sale 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Business for Sale
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Used Item for sale
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		
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