package com.infonegari.fragment;

import com.infonegari.activity.R;
import com.infonegari.util.AdsImageView;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;

public class AboutFragment extends Fragment{
	private static final int MENU_ITEM_BACK = 2000;
	private ImageSwitcher imageSwitcher;
	View rootView;

	public AboutFragment(){
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
  
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_about, container, false);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.about_imageSwitcher);
		
		getActivity().setTitle(getString(R.string.menu_about));
		
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

}
