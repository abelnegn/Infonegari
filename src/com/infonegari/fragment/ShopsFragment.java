package com.infonegari.fragment;

import java.util.ArrayList;

import com.infonegari.activity.R;
import com.infonegari.adapter.NavDrawerListAdapter;
import com.infonegari.model.NavDrawerItem;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	private static final int MENU_ITEM_BACK = 2000;
	
	public ShopsFragment(){
		
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
		rootView = inflater.inflate(R.layout.fragment_shops, container, false);
		getActivity().setTitle(getString(R.string.menu_shops));
		
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
			fragment = new ShopClothFragment();
			break;
		case 1:
			fragment = new ShopElectronicsFragment();
			break;
		case 2:
			fragment = new ShopFurnitureFragment();
			break;
		case 3:
			fragment = new ShopComputerFragment();
			break;
		case 4:
			fragment = new ConSupplyFragment();
			break;
		default:
			break;
		}
		
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
		} else {
			// error in creating fragment
			Log.e("Shop Activity", "Error in creating fragment");
		}
	}
}
