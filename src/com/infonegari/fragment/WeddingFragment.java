package com.infonegari.fragment;

import java.util.ArrayList;

import com.infonegari.activity.R;
import com.infonegari.adapter.NavDrawerListAdapter;
import com.infonegari.model.NavDrawerItem;
import com.infonegari.util.AdsImageView;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ListView;

public class WeddingFragment extends Fragment{
	View rootView;
	private ListView mDrawerList;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private NavDrawerListAdapter adapter;
	private ImageSwitcher imageSwitcher;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private static final int MENU_ITEM_BACK = 2000;
	
	public WeddingFragment(){
		
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_wedding, container, false);
		getActivity().setTitle(getString(R.string.menu_wedding));
		
		// load wedding menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_wedding_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_wedding_icon);

		mDrawerList = (ListView)rootView.findViewById(R.id.list_wedding_menu);
		
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding Wedding menu nav drawer items to array
		// Caterer and Pasteries
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Decorators 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Wedding Cars
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Wedding Gown and toxido
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Wedding halls
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// Wedding card
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		//Beauty Saloons
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		//Band
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		//Honey moon destination
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		//DJ
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1)));
		//Photo Video
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons.getResourceId(10, -1)));
	
		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

//		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getActivity(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.wedding_imageSwitcher);
		AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer();
		
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
			fragment = new CatererPasteriesFragment();
			break;
		case 1:
			fragment = new DecoratorsFragment();
			break;
		case 2:
			fragment = new WeddingCarFragment();
			break;
		case 3:
			fragment = new WeddingClothFragment();
			break;
		case 4:
			fragment =  new WeddingHallFragment();
			break;
		case 5:
			fragment = new WeddingCRPFragment();
			break;
		case 6:
			fragment = new BeautySaloonFragment();
			break;
		case 7:
			fragment = new BandFragment();
			break;
		case 8:
			fragment = new HmdtaFragment();
			break;
		case 9:
			fragment = new DJFragment();
			break;
		case 10:
			fragment = new PhotoVideoFragment();
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
			Log.e("Caterer Activity", "Error in creating fragment");
		}
	}
}
