package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.ShopElectronicsAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopElectronic;
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

public class ShopElectronicsFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
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
		
		getActivity().setTitle("Shop Electronics"); 
		
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
		imageView.startTimer();
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
//		saveShopElectronic();
		fetchLocation();
		fetchElectronicType();
		fetchService();
		
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

	private void saveShopElectronic(){
		ShopElectronic newE = new ShopElectronic();
		newE.setBrand_Name("Toshiba american brand computers");
		newE.setCatagory("New Category");
		newE.setDiscription("Toshiba american brand computer");
		newE.setItem_Name("Toshiba");
		newE.setLocationId(1);
		newE.setPrice(456);
		newE.setSeId(3);
		newE.setService_Type("Maintenance");
		
		newE.save();
	}
	
	private void fetchElectronicType(){
		List<String> listOfElectronicType = new ArrayList<String>();

		listOfElectronicType.add("Select Electronics");
		listOfElectronicType.add("TV,DVD,Tape");		
		listOfElectronicType.add("Refrigrator");
		listOfElectronicType.add("Mobiles");

        ArrayAdapter<String> serviceTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfElectronicType);

        serviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(serviceTypeAdapter);
        sp_type.setSelection(0);
	}
	
	private void fetchService(){
		List<String> listOfService = new ArrayList<String>();

		listOfService.add("Select Service");
		listOfService.add("Buy");		
		listOfService.add("Maintenance");

        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfService);

        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_service.setAdapter(serviceAdapter);
        sp_service.setSelection(0);
	}
	
	private void init(){
		shopElectronicList = Select.from(ShopElectronic.class).orderBy("id Desc").list();
		adapter = new ShopElectronicsAdapter(getActivity(), shopElectronicList);
		mShopElectronicList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		long locId = locationHashMap.get(sp_location.getSelectedItem().toString());
		shopElectronicList = Select.from(ShopElectronic.class).where(Condition.prop("Category").
				eq(sp_type.getSelectedItem().toString())).and(Condition.
						prop("Location_Id").eq(locId)).list();
		adapter = new ShopElectronicsAdapter(getActivity(), shopElectronicList);
		mShopElectronicList.setAdapter(adapter);		
	}

}
