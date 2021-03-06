package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.ConSupplyAdapter;
import com.infonegari.objects.db.Construction;
import com.infonegari.objects.db.ConstructionMachine;
import com.infonegari.objects.db.ConstructionMaterial;
import com.infonegari.objects.db.Location;
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

public class ConSupplyFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<ConstructionMachine> conMachineList;
	List<ConstructionMaterial> conMaterialList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> conMachineHashMap = new HashMap<String, Long>();
	HashMap<String, Long> conMaterialHashMap = new HashMap<String, Long>();
	List<Construction> constructionList;
	private ListView mConstructionList;
	private ConSupplyAdapter adapter;
	private Spinner sp_location, sp_conMachine, sp_conMaterial;
	private Button btnSearch;
	private EditText txtTitle;
	private ImageSwitcher imageSwitcher;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public ConSupplyFragment(){
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
		rootView = inflater.inflate(R.layout.fragment_construction_supply, container, false);
		
		getActivity().setTitle(getString(R.string.menu_con_supply));
		
		mConstructionList = (ListView)rootView.findViewById(R.id.list_con_supply);
		sp_conMachine = (Spinner)rootView.findViewById(R.id.machine);
		sp_conMaterial = (Spinner)rootView.findViewById(R.id.material);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsConsupplyImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchLocation();
		fetchMachine();
		fetchMaterial();
		
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
	
	private void fetchMachine(){
		List<String> listOfMachines = new ArrayList<String>();
		conMachineList = Select.from(ConstructionMachine.class).orderBy("Machine ASC").list();

		listOfMachines.add(getString(R.string.sp_all_machine));
		conMachineHashMap.put(getString(R.string.sp_all_machine), 0L);
		if(SplashScreen.localization == 1){
			for (ConstructionMachine machine : conMachineList) {
				listOfMachines.add(machine.getMachine_am());
				conMachineHashMap.put(machine.getMachine_am(), machine.getCmId());
	        }			
		}else{
			for (ConstructionMachine machine : conMachineList) {
				listOfMachines.add(machine.getMachine());
				conMachineHashMap.put(machine.getMachine(), machine.getCmId());
	        }
		}
        ArrayAdapter<String> machineAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfMachines);

        machineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_conMachine.setAdapter(machineAdapter);
        sp_conMachine.setSelection(0);
	}

	private void fetchMaterial(){
		List<String> listOfMaterials = new ArrayList<String>();
		conMaterialList = Select.from(ConstructionMaterial.class).orderBy("Materials ASC").list();

		listOfMaterials.add(getString(R.string.sp_all_material));
		conMaterialHashMap.put(getString(R.string.sp_all_material), 0L);
		if(SplashScreen.localization == 1){
			for (ConstructionMaterial material : conMaterialList) {
				listOfMaterials.add(material.getMaterials_am());
				conMaterialHashMap.put(material.getMaterials_am(), material.getCm_id());
	        }			
		}else{
			for (ConstructionMaterial material : conMaterialList) {
				listOfMaterials.add(material.getMaterials());
				conMaterialHashMap.put(material.getMaterials(), material.getCm_id());
	        }
		}
        ArrayAdapter<String> materialAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfMaterials);

        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_conMaterial.setAdapter(materialAdapter);
        sp_conMaterial.setSelection(0);
	}
	
	private void init(){
		constructionList = Select.from(Construction.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new ConSupplyAdapter(getActivity(), constructionList);
		mConstructionList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		hideKeyboard();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String machineId = String.valueOf(conMachineHashMap.get(sp_conMachine.getSelectedItem().toString()));
		if(machineId.equals("0")){
			machineId = "Construction_Machine_Id";
		}

		String materialId = String.valueOf(conMaterialHashMap.get(sp_conMaterial.getSelectedItem().toString()));
		if(materialId.equals("0")){
			materialId = "Construction_Material_Id";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "Construction_Title";
		}else{
			title = "'%" + title + "%'";
		}
		
		constructionList = Construction.findWithQuery(Construction.class, 
    			"SELECT * FROM  Construction WHERE Construction_Title LIKE " + title + 
    			" AND Construction_Machine_Id = " + machineId + " AND Construction_Material_Id = " +
    			materialId + " AND Location_Id = " + locationId + " ORDER BY is_Featured Desc, id Desc");
		
		adapter = new ConSupplyAdapter(getActivity(), constructionList);
		mConstructionList.setAdapter(adapter);	
		safeUIBlockingUtility.safelyUnBlockUI();
	}

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
