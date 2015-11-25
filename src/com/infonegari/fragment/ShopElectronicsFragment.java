package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.ShopElectronicsAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopElectronic;
import com.infonegari.util.AdsImageView;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Select;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ListView;
import android.widget.Spinner;

public class ShopElectronicsFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, String> categoryHashMap = new HashMap<String, String>();
	HashMap<String, String> serviceHashMap = new HashMap<String, String>();
	List<ShopElectronic> shopElectronicList;
	private ListView mShopElectronicList;
	private ShopElectronicsAdapter adapter;
	private Spinner sp_location, sp_type, sp_service;
	private Button btnSearch;
	private EditText txtTitle;
	private ImageSwitcher imageSwitcher;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public ShopElectronicsFragment(){
		
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
        	hideKeyboard();
			FragmentManager fragmentManager = getFragmentManager();
			ShopsFragment fragment = new ShopsFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_shop_electronics, container, false);
		
		getActivity().setTitle(getString(R.string.menu_shop_electronics)); 
		
		mShopElectronicList = (ListView)rootView.findViewById(R.id.list_shop_electronics);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_service = (Spinner)rootView.findViewById(R.id.service_type);
		sp_type = (Spinner)rootView.findViewById(R.id.type);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsShopElectronicImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchLocation();
		fetchElectronicCategory();
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
	
	private void fetchElectronicCategory(){
		List<String> listOfElectronicCat = new ArrayList<String>();

		listOfElectronicCat.add(getString(R.string.sp_all_electronics));
		listOfElectronicCat.add(getString(R.string.sp_tv));		
		listOfElectronicCat.add(getString(R.string.sp_refrigrator));
		listOfElectronicCat.add(getString(R.string.sp_mobiles));

		categoryHashMap.put(getString(R.string.sp_all_electronics), "0");
		categoryHashMap.put(getString(R.string.sp_tv), "tv");
		categoryHashMap.put(getString(R.string.sp_refrigrator), "refrigirator");
		categoryHashMap.put(getString(R.string.sp_mobiles), "mobile");
		
        ArrayAdapter<String> serviceTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfElectronicCat);

        serviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(serviceTypeAdapter);
        sp_type.setSelection(0);
	}
	
	private void fetchService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add(getString(R.string.sp_all_service));
		listOfService.add(getString(R.string.sp_sell));		
		listOfService.add(getString(R.string.sp_maintenance));

		serviceHashMap.put(getString(R.string.sp_all_service), "0");
		serviceHashMap.put(getString(R.string.sp_sell), "sell");
		serviceHashMap.put(getString(R.string.sp_maintenance), "maintenance");
		
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_service.setAdapter(serviceAdapter);
        sp_service.setSelection(0);
	}
	
	private void init(){
		shopElectronicList = Select.from(ShopElectronic.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new ShopElectronicsAdapter(getActivity(), shopElectronicList);
		mShopElectronicList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		hideKeyboard();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String categoryId = String.valueOf(categoryHashMap.get(sp_type.getSelectedItem().toString()));
		if(categoryId.equals("0")){
			categoryId = "Catagory";
		}else{
			categoryId = "'" + categoryId + "'";
		}
		
		String typeId = String.valueOf(serviceHashMap.get(sp_service.getSelectedItem().toString()));
		if(typeId.equals("0")){
			typeId = "ServiceType";
		}else{
			typeId = "'" + typeId + "'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "ItemName";
		}else{
			title = "'%" + title + "%'";
		}
		
		shopElectronicList = ShopElectronic.findWithQuery(ShopElectronic.class, 
    			"SELECT * FROM  Shop_Electronic WHERE ServiceType = " + typeId + 
    			" AND ItemName LIKE " +	title + " AND Location_Id = " + locationId + 
    			" AND Catagory = " + categoryId + " ORDER BY is_Featured Desc, id Desc");
		
		adapter = new ShopElectronicsAdapter(getActivity(), shopElectronicList);
		mShopElectronicList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
