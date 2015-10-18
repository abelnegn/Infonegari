package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.WeddingCarAdapter;
import com.infonegari.objects.db.CarType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.WeddingCar;
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

public class WeddingCarFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<CarType> carTypeList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> carTypeHashMap = new HashMap<String, Long>();
	List<WeddingCar> weddingCarList;
	private ListView mWeddingCarList;
	private WeddingCarAdapter adapter;
	private Spinner sp_location, sp_carType;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public WeddingCarFragment(){
		
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
		rootView = inflater.inflate(R.layout.fragment_wedding_car, container, false);
		
		getActivity().setTitle("Wedding Cars"); 
		
		mWeddingCarList = (ListView)rootView.findViewById(R.id.list_wedding_car);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_carType = (Spinner)rootView.findViewById(R.id.car_type);
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
		
		saveWeddingCars();
		fetchLocation();
		fetchCarType();
		
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
    
	private void saveWeddingCars(){
		WeddingCar newWC = new WeddingCar();
		newWC.setCarTypeId(1);
		newWC.setDiscription("Wedding car like lemosin and different types of cars");
		newWC.setLocationId(1);
		newWC.setMemberId(1);
		newWC.setPrice(323);
		newWC.setWeddingCarId(32);
		newWC.setWeddingCarName("Adika Tour and travel");
		
		newWC.save();
	}
	private void fetchCarType(){
		List<String> listOfCarType = new ArrayList<String>();
		carTypeList = Select.from(CarType.class).list();

		listOfCarType.add("Select Car Type");
		carTypeHashMap.put("Select Car Type", 0L);
		for (CarType carType : carTypeList) {
			listOfCarType.add(carType.getCarTypeName());
			carTypeHashMap.put(carType.getCarTypeName(), carType.getCarTypeId());
        }
        ArrayAdapter<String> carTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCarType);

        carTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_carType.setAdapter(carTypeAdapter);
        sp_carType.setSelection(0);
	}
	
	private void init(){
		weddingCarList = Select.from(WeddingCar.class).list();
		adapter = new WeddingCarAdapter(getActivity(), weddingCarList);
		mWeddingCarList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		long locId = locationHashMap.get(sp_location.getSelectedItem().toString());
		weddingCarList = Select.from(WeddingCar.class).where(Condition.prop("Category").
				eq(sp_carType.getSelectedItem().toString())).and(Condition.
						prop("Location_Id").eq(locId)).list();
		adapter = new WeddingCarAdapter(getActivity(), weddingCarList);
		mWeddingCarList.setAdapter(adapter);		
	}

}