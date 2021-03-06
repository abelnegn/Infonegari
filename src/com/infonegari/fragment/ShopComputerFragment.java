package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.ShopComputerAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopComputer;
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
import android.view.KeyEvent;
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

public class ShopComputerFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, String> serviceTypeHashMap = new HashMap<String, String>();
	HashMap<String, String> itemTypeHashMap = new HashMap<String, String>();
	List<ShopComputer> shopComputerList;
	private ListView mShopComputerList;
	private ShopComputerAdapter adapter;
	private Spinner sp_location, sp_type, sp_service;
	private Button btnSearch;
	private EditText txtTitle;
	private ImageSwitcher imageSwitcher;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public ShopComputerFragment(){
		
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
    public void onResume() {
       super.onResume();

       getView().setFocusableInTouchMode(true);
       getView().requestFocus();
       getView().setOnKeyListener(new View.OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {

              if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
              	hideKeyboard();
    			FragmentManager fragmentManager = getFragmentManager();
    			ShopsFragment fragment = new ShopsFragment();
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
		rootView = inflater.inflate(R.layout.fragment_shop_computer, container, false);
		
		getActivity().setTitle(getString(R.string.menu_shop_computer)); 
		
		mShopComputerList = (ListView)rootView.findViewById(R.id.list_shop_computer);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_type = (Spinner)rootView.findViewById(R.id.type);
		sp_service = (Spinner)rootView.findViewById(R.id.service);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsShopComputerImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchLocation();
		fetchType();
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
	
	private void fetchType(){
		List<String> listOfType = new ArrayList<String>();

		listOfType.add(getString(R.string.sp_all_type));
		listOfType.add(getString(R.string.sp_brand_new));		
		listOfType.add(getString(R.string.sp_assembled));
		listOfType.add(getString(R.string.sp_used));

		itemTypeHashMap.put(getString(R.string.sp_all_type), "0");
		itemTypeHashMap.put(getString(R.string.sp_brand_new), "new");
		itemTypeHashMap.put(getString(R.string.sp_assembled), "assembled");
		itemTypeHashMap.put(getString(R.string.sp_used), "used");
		
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfType);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(typeAdapter);
        sp_type.setSelection(0);
	}
	
	private void fetchService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add(getString(R.string.sp_all_service));
		listOfService.add(getString(R.string.sp_sell));
		listOfService.add(getString(R.string.sp_maintenance));

		serviceTypeHashMap.put(getString(R.string.sp_all_service), "0");
		serviceTypeHashMap.put(getString(R.string.sp_sell), "sell");
		serviceTypeHashMap.put(getString(R.string.sp_maintenance), "maintenance");
		
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_service.setAdapter(serviceAdapter);
        sp_service.setSelection(0);
	}
	
	private void init(){
		shopComputerList = Select.from(ShopComputer.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new ShopComputerAdapter(getActivity(), shopComputerList);
		mShopComputerList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		hideKeyboard();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String typeId = String.valueOf(itemTypeHashMap.get(sp_type.getSelectedItem().toString()));
		if(typeId.equals("0")){
			typeId = "ItemType";
		}else{
			typeId = "'" + typeId + "'";
		}
		
		String serviceId = String.valueOf(serviceTypeHashMap.get(sp_service.getSelectedItem().toString()));
		if(serviceId.equals("0")){
			serviceId = "ServiceType";
		}else{
			serviceId = "'" + serviceId + "'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "ItemName";
		}else{
			title = "'%" + title + "%'";
		}
		
		shopComputerList = ShopComputer.findWithQuery(ShopComputer.class, 
    			"SELECT * FROM  Shop_Computer WHERE ServiceType = " + serviceId + 
    			" AND ItemName LIKE " +	title + " AND Location_Id = " + locationId + 
    			" AND ItemType = " + typeId + " ORDER BY is_Featured Desc, id Desc");
		
		adapter = new ShopComputerAdapter(getActivity(), shopComputerList);
		mShopComputerList.setAdapter(adapter);	
		safeUIBlockingUtility.safelyUnBlockUI();
	}

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
