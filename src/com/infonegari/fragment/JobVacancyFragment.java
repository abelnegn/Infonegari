package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.JobVacancyAdapter;
import com.infonegari.objects.db.EducationCategory;
import com.infonegari.objects.db.JobCategory;
import com.infonegari.objects.db.Jobs;
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

public class JobVacancyFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<JobCategory> categoryList;
	List<EducationCategory> levelList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> categoryHashMap = new HashMap<String, Long>();
	HashMap<String, Long> levelHashMap = new HashMap<String, Long>();
	List<Jobs> jobsList;
	private ListView mJobList;
	private JobVacancyAdapter adapter;
	private Spinner sp_location, sp_category, sp_level;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private ImageSwitcher imageSwitcher;
	private static final int MENU_ITEM_BACK = 2000;
	
	public JobVacancyFragment(){
		
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
			HomeFragment fragment = new HomeFragment();
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
    			HomeFragment fragment = new HomeFragment();
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
		rootView = inflater.inflate(R.layout.fragment_job_vacancy, container, false);
		getActivity().setTitle(getString(R.string.menu_job));
		
		mJobList = (ListView)rootView.findViewById(R.id.list_job_vacancy);
		sp_location = (Spinner)rootView.findViewById(R.id.location);
		sp_category = (Spinner)rootView.findViewById(R.id.category);
		sp_level = (Spinner)rootView.findViewById(R.id.edu_level);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsJobVacancyImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchLocation();
		fetchCategory();
		fetchLevel();
		
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
	
	private void fetchCategory(){
		List<String> listOfCategories = new ArrayList<String>();
		categoryList = Select.from(JobCategory.class).orderBy("CategoryName ASC").list();

		listOfCategories.add(getString(R.string.sp_all_category));
		categoryHashMap.put(getString(R.string.sp_all_category), 0L);
		if(SplashScreen.localization == 1){
			for (JobCategory jobCategory : categoryList) {
				listOfCategories.add(jobCategory.getCategory_Name_am());
				categoryHashMap.put(jobCategory.getCategory_Name_am(), jobCategory.getJcId());
	        }			
		}else{
			for (JobCategory jobCategory : categoryList) {
				listOfCategories.add(jobCategory.getCategory_Name());
				categoryHashMap.put(jobCategory.getCategory_Name(), jobCategory.getJcId());
	        }
		}
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCategories);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        sp_category.setSelection(0);
	}
	
	private void fetchLevel(){
		List<String> listOfLevels = new ArrayList<String>();
		levelList = Select.from(EducationCategory.class).list();

		listOfLevels.add(getString(R.string.sp_all_education_level));
		levelHashMap.put(getString(R.string.sp_all_education_level), 0L);
		if(SplashScreen.localization == 1){
			for (EducationCategory level : levelList) {
				listOfLevels.add(level.getEducation_Level_am());
				levelHashMap.put(level.getEducation_Level_am(), level.getEcId());
	        }			
		}else{
			for (EducationCategory level : levelList) {
				listOfLevels.add(level.getEducation_Level());
				levelHashMap.put(level.getEducation_Level(), level.getEcId());
	        }
		}
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfLevels);

        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_level.setAdapter(levelAdapter);
        sp_level.setSelection(0);
	}
	
	private void init(){
		jobsList = Select.from(Jobs.class).orderBy("is_Featured Desc, id Desc").list();
		adapter = new JobVacancyAdapter(getActivity(), jobsList);
		mJobList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		hideKeyboard();
		String locationId = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
		if(locationId.equals("0")){
			locationId = "Location_Id";
		}

		String catId = String.valueOf(categoryHashMap.get(sp_category.getSelectedItem().toString()));
		if(catId.equals("0")){
			catId = "Category";
		}else{
			catId = "'" + catId + "'";
		}

		String levelId = String.valueOf(levelHashMap.get(sp_level.getSelectedItem().toString()));
		if(levelId.equals("0")){
			levelId = "EducationLevel";
		}else{
			levelId = "'" + levelId + "'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "JobTitle";
		}else{
			title = "'%" + title + "%'";
		}
		
		jobsList = Jobs.findWithQuery(Jobs.class, 
    			"SELECT * FROM  Jobs WHERE JobTitle LIKE " + title + " AND Category = " + catId + 
    			" AND EducationLevel = " + levelId + " AND Location_Id = " + locationId + " ORDER BY is_Featured Desc, id Desc");
		
		adapter = new JobVacancyAdapter(getActivity(), jobsList);
		mJobList.setAdapter(adapter);		
		safeUIBlockingUtility.safelyUnBlockUI();
	}

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
