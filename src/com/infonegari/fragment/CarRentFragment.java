package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.CarRentAdapter;
import com.infonegari.objects.db.CarListing;
import com.infonegari.objects.db.CarType;
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

public class CarRentFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<CarType> carTypeList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> carTypeHashMap = new HashMap<String, Long>();
	List<CarListing> carList;
	private ListView mCarList;
	private CarRentAdapter adapter;
	private Spinner sp_location, sp_carType;
	private Button btnSearch;
	private EditText txtTitle, txtYear;
	private ImageSwitcher imageSwitcher;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public CarRentFragment(){
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
		rootView = inflater.inflate(R.layout.fragment_car_rent, container, false);
		
		getActivity().setTitle("Car Rent");
		
		mCarList = (ListView)rootView.findViewById(R.id.list_car_rent);
		sp_carType = (Spinner)rootView.findViewById(R.id.car_type);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		txtYear = (EditText)rootView.findViewById(R.id.year);
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
		
//		saveCar();
		
		fetchCarType();
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

	private void fetchCarType(){
		List<String> listOfCarTypes = new ArrayList<String>();
		carTypeList = Select.from(CarType.class).list();

		listOfCarTypes.add("Select Car Type");
		carTypeHashMap.put("Select Car Type", 0L);
		for (CarType type : carTypeList) {
			listOfCarTypes.add(type.getCarTypeName());
			carTypeHashMap.put(type.getCarTypeName(), type.getCarTypeId());
        }
        ArrayAdapter<String> carTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCarTypes);

        carTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_carType.setAdapter(carTypeAdapter);
        sp_carType.setSelection(0);
	}
	
	private void saveCar(){
		CarListing newC = new CarListing();
		newC.setDiscription("Abel Car");
		newC.setCarName("Lifan");
		newC.setLocationId(4);
		newC.setCarPrice(432);
		newC.setCarListingId(1);
		newC.setCarTypeId(1);
		newC.setMemberId(1);
		newC.setYear(1945);
		newC.setCarSale(true);
		
		newC.save();
	}
	
	private void init(){
		carList = Select.from(CarListing.class).
				where(Condition.prop("is_Car_Sale").eq(0)).
				orderBy("id Desc").list();
		adapter = new CarRentAdapter(getActivity(), carList);
		mCarList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		long locId = locationHashMap.get(sp_location.getSelectedItem().toString());
		carList = Select.from(CarListing.class).where(Condition.prop("CnPIdName").
				eq(txtTitle.getText().toString())).and(Condition.
						prop("Location_Id").eq(locId)).list();
		adapter = new CarRentAdapter(getActivity(), carList);
		mCarList.setAdapter(adapter);		
	}

}
