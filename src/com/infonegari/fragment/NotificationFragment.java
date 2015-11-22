package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.infonegari.activity.R;
import com.infonegari.objects.db.JobCategory;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UserSite;
import com.infonegari.service.API;
import com.infonegari.util.Network;
import com.infonegari.util.SafeUIBlockingUtility;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class NotificationFragment extends DialogFragment{
	private View rootView;
	private boolean isModal = true;
	private Button btnOk, btnCancel;
	private CheckBox cbJob, cbCarSell, cbUsedItem, cbHouseSell, cbBusinessSell;
	private CheckBox cbRentCar, cbHouseRent, cbBusinessRent, cbCinema, cbEvent;
	private Spinner sp_category, sp_location;
	List<Location> locationList;
	List<JobCategory> categoryList;
	HashMap<String, Long> locationHashMap = new HashMap<String, Long>();
	HashMap<String, Long> categoryHashMap = new HashMap<String, Long>();
	String notify = "";
	String notifyCategory = "";
	String notifyLocation = "";
	SafeUIBlockingUtility safeUIBlockingUtility;
	
    public static NotificationFragment newInstance()
    {
    	NotificationFragment frag = new NotificationFragment();
        frag.isModal = true;
        return frag;
    }
    
	public NotificationFragment(){
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        cbJob = (CheckBox)rootView.findViewById(R.id.cb_job);
        cbCarSell = (CheckBox)rootView.findViewById(R.id.cb_car_sell);
        cbUsedItem = (CheckBox)rootView.findViewById(R.id.cb_used_item);
        cbHouseSell = (CheckBox)rootView.findViewById(R.id.cb_house_sell);
        cbBusinessSell = (CheckBox)rootView.findViewById(R.id.cb_business_sell);
        cbRentCar = (CheckBox)rootView.findViewById(R.id.cb_car_rental);
        cbHouseRent = (CheckBox)rootView.findViewById(R.id.cb_rental_house);
        cbBusinessRent = (CheckBox)rootView.findViewById(R.id.cb_rental_business);
        cbCinema = (CheckBox)rootView.findViewById(R.id.cb_movie_theater);
        cbEvent = (CheckBox)rootView.findViewById(R.id.cb_event);
        sp_category = (Spinner)rootView.findViewById(R.id.job_category);
        sp_location = (Spinner)rootView.findViewById(R.id.location);
        btnOk = (Button)rootView.findViewById(R.id.select_button);
        btnCancel = (Button)rootView.findViewById(R.id.btn_cancel);
        
        getDialog().setTitle(getString(R.string.db_notification));
        
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getNotify();
			}
		});
		
        btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getDialog().dismiss();
			}
		});
        
        fetchLocation();
        fetchCategory();
        
        init();
        
        return rootView;  
    }
    
	private void fetchLocation(){
		List<String> listOfLocations = new ArrayList<String>();
		locationList = Select.from(Location.class).orderBy("Location_Name ASC").list();

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

	private void fetchCategory(){
		List<String> listOfCategories = new ArrayList<String>();
		categoryList = Select.from(JobCategory.class).orderBy("CategoryName ASC").list();

		listOfCategories.add("Select Category");
		categoryHashMap.put("Select Category", 0L);
		for (JobCategory jobCategory : categoryList) {
			listOfCategories.add(jobCategory.getCategory_Name());
			categoryHashMap.put(jobCategory.getCategory_Name(), jobCategory.getJcId());
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCategories);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        sp_category.setSelection(0);
	}
	
    private void init(){
    	List<UserSite> userSite= Select.from(UserSite.class).
    			where(Condition.prop("is_Active").eq("1")).list();
    	if(userSite.size() > 0){
    		String notify = userSite.get(0).getNotification();
    		String[] notifyList = notify.split(",");
    		for(int i=0; i<notifyList.length; i++){
    			if(notifyList[i].equals("5"))
    				cbJob.setChecked(true);
    			if(notifyList[i].equals("17"))
    				cbCarSell.setChecked(true);
    			if(notifyList[i].equals("18"))
    				cbUsedItem.setChecked(true);
    			if(notifyList[i].equals("19"))
    				cbHouseSell.setChecked(true);
    			if(notifyList[i].equals("20"))
    				cbBusinessSell.setChecked(true);
    			if(notifyList[i].equals("21"))
    				cbRentCar.setChecked(true);
    			if(notifyList[i].equals("22"))
    				cbHouseRent.setChecked(true);
    			if(notifyList[i].equals("24"))
    				cbBusinessRent.setChecked(true);
    			if(notifyList[i].equals("31"))
    				cbCinema.setChecked(true);
    			if(notifyList[i].equals("34"))
    				cbEvent.setChecked(true);
    		}
    		String notifyLoc = userSite.get(0).getNotify_Loc();
    		if(notifyLoc != null){
    			if(notifyLoc.equals("")){
    				notifyLoc = "0";
    			}
	    		List<Location> locList = Select.from(Location.class).where(Condition.
	    				prop("Location_Id").eq(Long.valueOf(notifyLoc))).list();
	    					
	    		if(locList.size() > 0){
	        		for (int i = 0; i < sp_location.getCount(); i++) {
	    		        String s = (String) sp_location.getItemAtPosition(i);
	    		        if (s.equalsIgnoreCase(locList.get(0).getLocationName())) {
	    		        	sp_location.setSelection(i);
	    		        }
	    		    }    			
	    		}
    		}
    		String notifyJob = userSite.get(0).getNotify_Job();
    		if(notifyJob != null){
    			if(notifyJob.equals("")){
    				notifyJob = "0";
    			}
        		List<JobCategory> catList = Select.from(JobCategory.class).where(Condition.
        				prop("jc_Id").eq(Long.valueOf(notifyJob))).list();
        					
        		if(catList.size() > 0){
            		for (int i = 0; i < sp_category.getCount(); i++) {
        		        String s = (String) sp_category.getItemAtPosition(i);
        		        if (s.equalsIgnoreCase(catList.get(0).getCategory_Name())) {
        		        	sp_category.setSelection(i);
        		        }
        		    }    			
        		}    			
    		}
    	}
    }
    
    private void getNotify(){
    	if(cbJob.isChecked()){
    		if(categoryHashMap.get(sp_category.getSelectedItem().toString()) != 0){
    			notify = notify + "5";		
    		}else{
    			Toast.makeText(getActivity(), "Please select job category", Toast.LENGTH_SHORT).show();
    			return;
    		}
    	}
    	if(cbCarSell.isChecked())
    		if(notify.equals(""))
    			notify = "17";
    		else
    			notify = notify + ",17";
    	if(cbUsedItem.isChecked())
    		if(notify.equals(""))
    			notify = "18";
    		else
    			notify = notify + ",18";
    	if(cbHouseSell.isChecked()){
    		if(locationHashMap.get(sp_location.getSelectedItem().toString()) != 0){
        		if(notify.equals(""))
        			notify = "19";
        		else
        			notify = notify + ",19";	
    		}else{
    			Toast.makeText(getActivity(), "Please select Location", Toast.LENGTH_SHORT).show();
    			return;
    		}    		
    	}
    	if(cbBusinessSell.isChecked()){
    		if(locationHashMap.get(sp_location.getSelectedItem().toString()) != 0){
        		if(notify.equals(""))
        			notify = "20";
        		else
        			notify = notify + ",20";    			
    		}else{
    			Toast.makeText(getActivity(), "Please select Location", Toast.LENGTH_SHORT).show();
    			return;
    		}
    	}
    	if(cbRentCar.isChecked()){
    		if(locationHashMap.get(sp_location.getSelectedItem().toString()) != 0){
        		if(notify.equals(""))
        			notify = "21";
        		else
        			notify = notify + ",21";    			
    		}else{
    			Toast.makeText(getActivity(), "Please select Location", Toast.LENGTH_SHORT).show();
    			return;    			
    		}
    	}
    	if(cbHouseRent.isChecked()){
    		if(locationHashMap.get(sp_location.getSelectedItem().toString()) != 0){
        		if(notify.equals(""))
        			notify = "22";
        		else
        			notify = notify + ",22";    			
    		}else{
    			Toast.makeText(getActivity(), "Please select Location", Toast.LENGTH_SHORT).show();
    			return;    			
    		}
    	}
    	if(cbBusinessRent.isChecked()){
    		if(locationHashMap.get(sp_location.getSelectedItem().toString()) != 0){
        		if(notify.equals(""))
        			notify = "24";
        		else
        			notify = notify + ",24";   			
    		}else{
    			Toast.makeText(getActivity(), "Please select Location", Toast.LENGTH_SHORT).show();
    			return;    			
    		}
    	}
    	if(cbCinema.isChecked()){
    		if(locationHashMap.get(sp_location.getSelectedItem().toString()) != 0){
        		if(notify.equals(""))
        			notify = "31";
        		else
        			notify = notify + ",31";    			
    		}else{
    			Toast.makeText(getActivity(), "Please select Location", Toast.LENGTH_SHORT).show();
    			return;    			
    		}
    	}
    	if(cbEvent.isChecked()){
    		if(notify.equals(""))
    			notify = "34";
    		else
    			notify = notify + ",34";
    	}
		updateNotifyData();
		getDialog().dismiss();
    }
    
    private void updateNotifyData(){
    	final List<UserSite> userSite= Select.from(UserSite.class).
    			where(Condition.prop("is_Active").eq("1")).list();
    	if(userSite.size() > 0){
            if (Network.isOnline(this.getActivity())) {
        		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
        				"Updating", "Please Wait...");
        		safeUIBlockingUtility.safelyBlockUI();
        		notifyCategory = String.valueOf(categoryHashMap.get(sp_category.getSelectedItem().toString()));
        		notifyLocation = String.valueOf(locationHashMap.get(sp_location.getSelectedItem().toString()));
                API.updateUserSiteService.updateUserSite(userSite.get(0).getUsId(), 
                		"'" + notify + "'", notifyCategory, notifyLocation, new Callback<String>() {
                        @Override
                        public void success(String Updated, Response response) {
                        	userSite.get(0).setNotification(notify);
                        	userSite.get(0).setNotify_Job(notifyCategory);
                        	userSite.get(0).setNotify_Loc(notifyLocation);
                        	userSite.get(0).setIsSync("1");
                        	userSite.get(0).save();
                        	safeUIBlockingUtility.safelyUnBlockUI();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                        	safeUIBlockingUtility.safelyUnBlockUI();
                        }

                    });
            }else{
            	userSite.get(0).setNotification(notify);
            	userSite.get(0).setNotify_Job(notifyCategory);
            	userSite.get(0).setNotify_Loc(notifyLocation);
            	userSite.get(0).setIsSync("0");           	
            	userSite.get(0).save();
            	Toast.makeText(getActivity(), "Notification saved successfuly", Toast.LENGTH_SHORT).show();
            }    		
    	}
    }
}
