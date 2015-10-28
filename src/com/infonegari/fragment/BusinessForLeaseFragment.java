package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.HouseRentAdapter;
import com.infonegari.objects.db.HouseListing;
import com.infonegari.objects.db.HouseType;
import com.infonegari.objects.db.Location;
import com.infonegari.util.AdsImageView;
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
import android.widget.ImageSwitcher;
import android.widget.ListView;
import android.widget.Spinner;

public class BusinessForLeaseFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<HouseType> houseTypeList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> houseTypeHashMap = new HashMap<String, Long>();
	List<HouseListing> businessList;
	private ListView mBusinessList;
	private HouseRentAdapter adapter;
	private Spinner sp_location, sp_houseType;
	private Button btnSearch;
	private EditText txtTitle;
	private ImageSwitcher imageSwitcher;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public BusinessForLeaseFragment(){
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
			RentFragment fragment = new RentFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_business_lease, container, false);
		
		getActivity().setTitle(getString(R.string.menu_business_lease));
		
		mBusinessList = (ListView)rootView.findViewById(R.id.list_business_lease);
		sp_houseType = (Spinner)rootView.findViewById(R.id.house_type);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
		AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer();
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		saveBusiness();
		
		fetchHouseType();
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

	private void fetchHouseType(){
		List<String> listOfHouseTypes = new ArrayList<String>();
		houseTypeList = Select.from(HouseType.class).list();

		listOfHouseTypes.add("Select House Type");
		houseTypeHashMap.put("Select House Type", 0L);
		for (HouseType type : houseTypeList) {
			listOfHouseTypes.add(type.getHouseTypeName());
			houseTypeHashMap.put(type.getHouseTypeName(), type.getHouseTypeId());
        }
        ArrayAdapter<String> houseTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfHouseTypes);

        houseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_houseType.setAdapter(houseTypeAdapter);
        sp_houseType.setSelection(0);
	}
	
	private void saveBusiness(){
		HouseListing newHL = new HouseListing();
		newHL.setHouse_Name("Abel for Business");
		newHL.setHouseDiscription("House for business");
		newHL.setHouseListingId(1);
		newHL.setHousePrice(324);
		newHL.setHouseTypeId(1);
		newHL.setIsBusiness(true);
		newHL.setLocationId(2);
		newHL.setLotSize("32");
		newHL.setMemberId(2);
		newHL.setNoRooms(3);
		newHL.setSale(false);
		newHL.setUser_Name("kebede");
		
		newHL.save();
	}
	
	private void init(){
		businessList = Select.from(HouseListing.class).where(Condition.prop("is_Sale").
				eq(0)).and(Condition.prop("Is_Business").eq(1)).orderBy("id Desc").list();
		adapter = new HouseRentAdapter(getActivity(), businessList);
		mBusinessList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}
		
		String typeId = String.valueOf(houseTypeHashMap.get(sp_houseType.getSelectedItem().toString()));
		if(typeId.equals("0")){
			typeId = "House_Type_Id";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "HouseName";
		}else{
			title = "'%" + title + "%'";
		}
		
		businessList = HouseListing.findWithQuery(HouseListing.class, 
    			"SELECT * FROM  House_Listing WHERE is_Sale = 0 AND Is_Business = 0 AND HouseName LIKE " +
    					title + " AND House_Type_Id = " + typeId + " AND Location_Id = " + locationId +
    					" ORDER BY id Desc");
		
		adapter = new HouseRentAdapter(getActivity(), businessList);
		mBusinessList.setAdapter(adapter);	
		safeUIBlockingUtility.safelyUnBlockUI();
	}

}
