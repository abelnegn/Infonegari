package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.HDTAAdapter;
import com.infonegari.objects.db.Bank;
import com.infonegari.objects.db.Hdnta;
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

public class HmdtaFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	List<Hdnta> hdntaList;
	private ListView mHdntaList;
	private HDTAAdapter adapter;
	private Spinner sp_location;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private ImageSwitcher imageSwitcher;
	private static final int MENU_ITEM_BACK = 2000;
	
	public HmdtaFragment(){
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
		rootView = inflater.inflate(R.layout.fragment_hmdta, container, false);
		
		getActivity().setTitle(getString(R.string.menu_hmdta));
		
		mHdntaList = (ListView)rootView.findViewById(R.id.list_hdnta);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
//		saveHM();
		
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

	private void saveHM(){
		Hdnta newH = new Hdnta();
		newH.setDiscription("DJ Fastu");
		newH.setHdtaName("DJ Fastu");
		newH.setHdntaId(23);
		newH.setLocationId(1);
		newH.setPrice(324);
		newH.setMemberId(1);
		
		newH.save();
	}
	
	private void init(){
		hdntaList = Select.from(Hdnta.class).orderBy("id Desc").list();
		adapter = new HDTAAdapter(getActivity(), hdntaList);
		mHdntaList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "hdta_Name";
		}else{
			title = "'%" + title + "%'";
		}
		
		hdntaList = Hdnta.findWithQuery(Hdnta.class, 
    			"SELECT * FROM  Hdnta WHERE hdta_Name LIKE " +
    					title + " AND Location_Id = " + locationId + " ORDER BY id Desc");
		
		adapter = new HDTAAdapter(getActivity(), hdntaList);
		mHdntaList.setAdapter(adapter);	
		safeUIBlockingUtility.safelyUnBlockUI();
	}

}
