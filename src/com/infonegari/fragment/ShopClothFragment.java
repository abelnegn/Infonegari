package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.ShopClothAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopCloth;
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

public class ShopClothFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, String> shopCatHashMap = new HashMap<String, String>();
	HashMap<String, String> shopTypeHashMap = new HashMap<String, String>();
	List<ShopCloth> shopClothList;
	private ListView mShopClothList;
	private ShopClothAdapter adapter;
	private Spinner sp_location, sp_clothType, sp_category;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private ImageSwitcher imageSwitcher;
	private static final int MENU_ITEM_BACK = 2000;
	
	public ShopClothFragment(){
		
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
		rootView = inflater.inflate(R.layout.fragment_shop_cloth, container, false);
		
		getActivity().setTitle(getString(R.string.menu_shop_cloth)); 
		
		mShopClothList = (ListView)rootView.findViewById(R.id.list_shop_cloth);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_category = (Spinner)rootView.findViewById(R.id.category);
		sp_clothType = (Spinner)rootView.findViewById(R.id.cloth_type);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsShopClothImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchClothCategory();
		fetchLocation();
		fetchClothType();
		
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
	
	private void fetchClothCategory(){
		List<String> listOfClothCat = new ArrayList<String>();

		listOfClothCat.add(getString(R.string.sp_all_category));		
		listOfClothCat.add(getString(R.string.sp_female_cloth));
		listOfClothCat.add(getString(R.string.sp_male_cloth));
		listOfClothCat.add(getString(R.string.sp_kids_cloth));
		listOfClothCat.add(getString(R.string.sp_cloth_designer));
			
		shopCatHashMap.put(getString(R.string.sp_all_category), "0");
		shopCatHashMap.put(getString(R.string.sp_female_cloth), "female_cloth");
		shopCatHashMap.put(getString(R.string.sp_male_cloth), "Male_cloth");
		shopCatHashMap.put(getString(R.string.sp_kids_cloth), "Kids_cloth");
		shopCatHashMap.put(getString(R.string.sp_cloth_designer), "cloth_designer");
		
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfClothCat);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        sp_category.setSelection(0);
	}
	
	private void fetchClothType(){
		List<String> listOfClothType = new ArrayList<String>();

		listOfClothType.add(getString(R.string.sp_all_cloth_type));		
		listOfClothType.add(getString(R.string.sp_modern));
		listOfClothType.add(getString(R.string.sp_traditional));
			
		shopTypeHashMap.put(getString(R.string.sp_all_cloth_type), "0");
		shopTypeHashMap.put(getString(R.string.sp_modern), "modern");
		shopTypeHashMap.put(getString(R.string.sp_traditional), "traditional");
		
        ArrayAdapter<String> clothTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfClothType);

        clothTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_clothType.setAdapter(clothTypeAdapter);
        sp_clothType.setSelection(0);
	}
	
	private void init(){
		shopClothList = Select.from(ShopCloth.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new ShopClothAdapter(getActivity(), shopClothList);
		mShopClothList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		hideKeyboard();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String categoryId = String.valueOf(shopCatHashMap.get(sp_category.getSelectedItem().toString()));
		if(categoryId.equals("0")){
			categoryId = "Catagory";
		}else{
			categoryId = "'" + categoryId + "'";
		}
		
		String typeId = String.valueOf(shopTypeHashMap.get(sp_clothType.getSelectedItem().toString()));
		if(typeId.equals("0")){
			typeId = "Type";
		}else{
			typeId = "'" + typeId + "'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "ItemName";
		}else{
			title = "'%" + title + "%'";
		}
		
		shopClothList = ShopCloth.findWithQuery(ShopCloth.class, 
    			"SELECT * FROM  Shop_Cloth WHERE Type = " + typeId + 
    			" AND ItemName LIKE " +	title + " AND Location_Id = " + locationId + 
    			" AND Catagory = " + categoryId + " ORDER BY is_Featured Desc, id Desc");
		
		adapter = new ShopClothAdapter(getActivity(), shopClothList);
		mShopClothList.setAdapter(adapter);	
		safeUIBlockingUtility.safelyUnBlockUI();
	}

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
