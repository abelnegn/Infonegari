package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.WeddingHallAdapter;
import com.infonegari.objects.db.HallType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.WeddingHall;
import com.infonegari.util.AdsImageView;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Select;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ListView;
import android.widget.Spinner;

public class WeddingHallFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, String> serviceTypeHashMap = new HashMap<String, String>();
	List<WeddingHall> weddingHallList;
	private ListView mWeddingHallList;
	private WeddingHallAdapter adapter;
	private Spinner sp_location, sp_service;
	private Button btnSearch, btnSelect;
	private EditText txtTitle, txtExactDate;
	private ImageSwitcher imageSwitcher;
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
		
		getActivity().setTitle(getString(R.string.menu_wedding_hall)); 
		
		mWeddingHallList = (ListView)rootView.findViewById(R.id.list_wedding_hall);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_service = (Spinner)rootView.findViewById(R.id.service);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtExactDate = (EditText)rootView.findViewById(R.id.exact_date);
		btnSelect = (Button)rootView.findViewById(R.id.button_select);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsWeddingHallImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		btnSelect.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
			    DatePickerFragment dpf = new DatePickerFragment().newInstance();
                dpf.setCallBack(onDate);
                dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
			}
		});
		
		fetchLocation();
		fetchService();
		
		init();
		
		return rootView;
	}
	
	DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        	txtExactDate.setText(String.format("%02d",monthOfYear + 1) + "/" + String.format("%02d", dayOfMonth)
                    + "/" + String.valueOf(year));
        }
    };
    
	private void fetchLocation(){
		List<String> listOfLocations = new ArrayList<String>();
		locationList = Select.from(Location.class).orderBy("Location_Name ASC").list();

		listOfLocations.add(getString(R.string.sp_all_location));
		locationHashMap.put(getString(R.string.sp_all_location), 0L);
		if(SplashScreen.localization == 1){
			for (Location location : locationList) {
				listOfLocations.add(location.getLocationName_am());
				locationHashMap.put(location.getLocationName_am(), location.getLocationId());
	        }			
		}else{
			for (Location location : locationList) {
				listOfLocations.add(location.getLocationName());
				locationHashMap.put(location.getLocationName(), location.getLocationId());
	        }			
		}
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfLocations);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_location.setAdapter(locationAdapter);
        sp_location.setSelection(0);
	}
    
	private void fetchService(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add(getString(R.string.sp_all_service));
		listOfType.add(getString(R.string.txt_break_fast));
		listOfType.add(getString(R.string.txt_lunch));
		listOfType.add(getString(R.string.txt_dinner));
		
		serviceTypeHashMap.put(getString(R.string.sp_all_service), "0");
		serviceTypeHashMap.put(getString(R.string.txt_break_fast), "BreakFast");
		serviceTypeHashMap.put(getString(R.string.txt_lunch), "Lunch");
		serviceTypeHashMap.put(getString(R.string.txt_dinner), "Dinner");
		
			
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_service.setAdapter(typeAdapter);
        sp_service.setSelection(0);
	}
	
	private void init(){
		weddingHallList = Select.from(WeddingHall.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new WeddingHallAdapter(getActivity(), weddingHallList);
		mWeddingHallList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String exactDate = txtExactDate.getText().toString();
		String serviceId = String.valueOf(serviceTypeHashMap.get(sp_service.getSelectedItem().toString()));
		if(serviceId.equals("0")){
			serviceId = "BreakFast = BreakFast and Lunch = Lunch and Dinner = Dinner";
			exactDate = "";
		}else if(exactDate.equals("")){
			exactDate = "=" + serviceId + "";
		}else{
			exactDate = " NOT LIKE '%" + exactDate + "%'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "Wedding_Hall_Name";
		}else{
			title = "'%" + title + "%'";
		}
		
		weddingHallList = WeddingHall.findWithQuery(WeddingHall.class, 
    			"SELECT * FROM  Wedding_Hall WHERE Wedding_Hall_Name LIKE " +
    					title + " AND Location_Id = " + locationId + 
    					" AND " + serviceId + exactDate + " ORDER BY is_Featured Desc, id Desc");

		adapter = new WeddingHallAdapter(getActivity(), weddingHallList);
		mWeddingHallList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();		
	}

}
