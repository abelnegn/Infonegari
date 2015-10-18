package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.BusinessLeaseAdapter;
import com.infonegari.objects.db.BusinessListing;
import com.infonegari.objects.db.City;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.MainCategory;
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

public class BusinessForSaleFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<City> cityList;
	List<MainCategory> mainCatList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> cityHashMap = new HashMap<String, Long>();
	HashMap<String, Long> mainCatHashMap = new HashMap<String, Long>();
	List<BusinessListing> businessList;
	private ListView mBusinessList;
	private BusinessLeaseAdapter adapter;
	private Spinner sp_location, sp_city, sp_mainCat;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public BusinessForSaleFragment(){
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
			SalesFragment fragment = new SalesFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_business_sales, container, false);
		
		getActivity().setTitle("Business for Sales");
		
		mBusinessList = (ListView)rootView.findViewById(R.id.list_business_sales);
		sp_city = (Spinner)rootView.findViewById(R.id.city);
		sp_mainCat = (Spinner)rootView.findViewById(R.id.main_category);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		saveBusiness();
		
		fetchCity();
		fetchMainCategory();
		fetchLocation();
		
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

	private void fetchCity(){
		List<String> listOfCity = new ArrayList<String>();
		cityList = Select.from(City.class).list();

		listOfCity.add("Select City");
		cityHashMap.put("Select City", 0L);
		for (City city : cityList) {
			listOfCity.add(city.getCityName());
			cityHashMap.put(city.getCityName(), city.getCityId());
        }
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCity);

        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_city.setAdapter(cityAdapter);
        sp_city.setSelection(0);
	}

	private void fetchMainCategory(){
		List<String> listOfMainCategory = new ArrayList<String>();
		mainCatList = Select.from(MainCategory.class).list();

		listOfMainCategory.add("Select Category");
		cityHashMap.put("Select Category", 0L);
		for (MainCategory category : mainCatList) {
			listOfMainCategory.add(category.getCategoryName());
			cityHashMap.put(category.getCategoryName(), category.getCategoryId());
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfMainCategory);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mainCat.setAdapter(categoryAdapter);
        sp_mainCat.setSelection(0);
	}
	
	private void saveBusiness(){
		BusinessListing newB = new BusinessListing();
		newB.setFullAddress("Addis Ababa");
		newB.setListingCity(1);
		newB.setListingDiscription("Business List Discription");
		newB.setListingEmail("ps_abello@yahoo.com");
		newB.setListingId(1);
		newB.setListingLocation(1);
		newB.setListingMainCategory(1);
		newB.setListingName("Abel Listing");
		newB.setListingPhone("0930099752");
		newB.setListingSubCategory(1);
		newB.setMemberId(2);
		
		newB.save();
	}
	
	private void init(){
		businessList = Select.from(BusinessListing.class).list();
		adapter = new BusinessLeaseAdapter(getActivity(), businessList);
		mBusinessList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		long locId = locationHashMap.get(sp_location.getSelectedItem().toString());
		businessList = Select.from(BusinessListing.class).where(Condition.prop("CnPIdName").
				eq(txtTitle.getText().toString())).and(Condition.
						prop("Location_Id").eq(locId)).list();
		adapter = new BusinessLeaseAdapter(getActivity(), businessList);
		mBusinessList.setAdapter(adapter);		
	}

}
