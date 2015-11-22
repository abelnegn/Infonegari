package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.WeddingClothAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.WeddingCloth;
import com.infonegari.util.AdsImageView;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
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
import android.widget.ImageSwitcher;
import android.widget.ListView;
import android.widget.Spinner;

public class WeddingClothFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, String> typeHashMap = new HashMap<String, String>();
	HashMap<String, String> serviceHashMap = new HashMap<String, String>();
	List<WeddingCloth> weddingClothList;
	private ListView mWeddingClothList;
	private WeddingClothAdapter adapter;
	private Spinner sp_location, sp_clothType, sp_service;
	private Button btnSearch;
	private EditText txtTitle;
	private ImageSwitcher imageSwitcher;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public WeddingClothFragment(){
		
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
		rootView = inflater.inflate(R.layout.fragment_wedding_cloth, container, false);
		
		getActivity().setTitle(getString(R.string.menu_wedding_cloths)); 
		
		mWeddingClothList = (ListView)rootView.findViewById(R.id.list_wedding_cloth);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_clothType = (Spinner)rootView.findViewById(R.id.cloth_type);
		sp_service = (Spinner)rootView.findViewById(R.id.service_type);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsWeddingClothImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchLocation();
		fetchClothType();
		fetchService();
		
		init();
		
		return rootView;
	}
	
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

	private void fetchClothType(){
		List<String> listOfClothType = new ArrayList<String>();

		listOfClothType.add(getString(R.string.sp_all_cloth_type));		
		listOfClothType.add(getString(R.string.sp_modern));
		listOfClothType.add(getString(R.string.sp_traditional));
			
		typeHashMap.put(getString(R.string.sp_all_cloth_type), "0");
		typeHashMap.put(getString(R.string.sp_modern), "modern");
		typeHashMap.put(getString(R.string.sp_traditional), "traditional");
		
        ArrayAdapter<String> clothTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfClothType);

        clothTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_clothType.setAdapter(clothTypeAdapter);
        sp_clothType.setSelection(0);
	}

	private void fetchService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add(getString(R.string.sp_all_service));
		listOfService.add(getString(R.string.sp_rental));
		listOfService.add(getString(R.string.sp_sell));
			
		serviceHashMap.put(getString(R.string.sp_all_service), "0");
		serviceHashMap.put(getString(R.string.sp_rental), "rental");
		serviceHashMap.put(getString(R.string.sp_sell), "sell");
		
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_service.setAdapter(serviceAdapter);
        sp_service.setSelection(0);
	}
	
	private void init(){
		weddingClothList = Select.from(WeddingCloth.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new WeddingClothAdapter(getActivity(), weddingClothList);
		mWeddingClothList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String typeId = String.valueOf(typeHashMap.get(sp_clothType.getSelectedItem().toString()));
		if(typeId.equals("0")){
			typeId = "ClothType";
		}else{
			typeId = "'" + typeId + "'";
		}

		String serviceId = String.valueOf(serviceHashMap.get(sp_service.getSelectedItem().toString()));
		if(serviceId.equals("0")){
			serviceId = "ServiceType";
		}else{
			serviceId = "'" + serviceId + "'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "Wedding_Cloth_Name";
		}else{
			title = "'%" + title + "%'";
		}
		
		weddingClothList = WeddingCloth.findWithQuery(WeddingCloth.class, 
    			"SELECT * FROM  Wedding_Cloth WHERE ClothType = " + typeId + 
    			" AND Wedding_Cloth_Name LIKE " +	title + " AND Location_Id = " + locationId + 
    			" AND ServiceType = " + serviceId + " ORDER BY is_Featured Desc, id Desc");
		
		adapter = new WeddingClothAdapter(getActivity(), weddingClothList);
		mWeddingClothList.setAdapter(adapter);		
		safeUIBlockingUtility.safelyUnBlockUI();
	}

}
