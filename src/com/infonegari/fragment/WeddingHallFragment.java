package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.WeddingHallAdapter;
import com.infonegari.objects.db.HallType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.WeddingHall;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class WeddingHallFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<HallType> hallList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> hallTypeHashMap = new HashMap<String, Long>();
	List<WeddingHall> weddingHallList;
	private ListView mWeddingHallList;
	private WeddingHallAdapter adapter;
	private Spinner sp_location, sp_type;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public WeddingHallFragment(){
		
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
			WeddingFragment fragment = new WeddingFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_wedding_hall, container, false);
		
		getActivity().setTitle("Wedding Halls"); 
		
		mWeddingHallList = (ListView)rootView.findViewById(R.id.list_wedding_hall);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_type = (Spinner)rootView.findViewById(R.id.type);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		saveWH();
		fetchLocation();
		fetchType();
		
		init();
		
		return rootView;
	}
	
	private void fetchLocation(){
		List<String> listOfLocations = new ArrayList<String>();
		locationList = Select.from(Location.class).list();

		listOfLocations.add("Select Location");
		locationHashMap.put("Select Location", 0L);
		for (Location location : locationList) {
			listOfLocations.add(location.getLocationName());
			locationHashMap.put(location.getLocationName(), location.getLocationId());
        }
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfLocations);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_location.setAdapter(locationAdapter);
        sp_location.setSelection(0);
	}
    
	private void saveWH(){
		WeddingHall newWH = new WeddingHall();
		newWH.setBreak_Fast("Ready");
		newWH.setDateAvailable("02-01-2015");
		newWH.setDinner("Available");
		newWH.setDiscription("Shall Wedding Hall");
		newWH.setHall_Type("Wedding Hall");
		newWH.setLocationId(2);
		newWH.setLunch("Available");
		newWH.setMemberId(1);
		newWH.setPrice(324);
		newWH.setServiceType("Full Service");
		newWH.setWeddingHallId(3);
		newWH.setWeddingHallName("Shalla Wedding Hall");
		
		newWH.save();
	}
	private void fetchType(){
		List<String> listOfType = new ArrayList<String>();

		hallList = Select.from(HallType.class).list();

		listOfType.add("Select Hall Type");
		hallTypeHashMap.put("Select Hall Type", 0L);
		for (HallType type : hallList) {
			listOfType.add(type.getHall_Type());
			hallTypeHashMap.put(type.getHall_Type(), type.getHtId());
        }
			
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(typeAdapter);
        sp_type.setSelection(0);
	}
	
	private void init(){
		weddingHallList = Select.from(WeddingHall.class).list();
		adapter = new WeddingHallAdapter(getActivity(), weddingHallList);
		mWeddingHallList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		long locId = locationHashMap.get(sp_location.getSelectedItem().toString());
		weddingHallList = Select.from(WeddingHall.class).where(Condition.prop("Category").
				eq(sp_type.getSelectedItem().toString())).and(Condition.
						prop("Location_Id").eq(locId)).list();
		adapter = new WeddingHallAdapter(getActivity(), weddingHallList);
		mWeddingHallList.setAdapter(adapter);		
	}

}