package com.infonegari.fragment;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.infonegari.activity.R;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class NotificationFragment extends DialogFragment{
	private View rootView;
	private boolean isModal = true;
	private Button btnOk, btnCancel;
	private CheckBox cbJob, cbCarSell, cbUsedItem, cbHouseSell, cbBusinessSell;
	private CheckBox cbRentCar, cbHouseRent, cbBusinessRent, cbCinema, cbEvent;
	String notify = "";
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
        
        btnOk = (Button)rootView.findViewById(R.id.select_button);
        btnCancel = (Button)rootView.findViewById(R.id.btn_cancel);
        
        getDialog().setTitle(getString(R.string.db_notification));
        
        init();
        
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getNotify();
				updateNotifyData();
				getDialog().dismiss();
			}
		});
		
        btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getDialog().dismiss();
			}
		});
        
        return rootView;  
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
    	}
    }
    
    private void getNotify(){
    	if(cbJob.isChecked())
    		notify = notify + "5";
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
    	if(cbHouseSell.isChecked())
    		if(notify.equals(""))
    			notify = "19";
    		else
    			notify = notify + ",19";
    	if(cbBusinessSell.isChecked())
    		if(notify.equals(""))
    			notify = "20";
    		else
    			notify = notify + ",20";
    	if(cbRentCar.isChecked())
    		if(notify.equals(""))
    			notify = "21";
    		else
    			notify = notify + ",21";
    	if(cbHouseRent.isChecked())
    		if(notify.equals(""))
    			notify = "22";
    		else
    			notify = notify + ",22";
    	if(cbBusinessRent.isChecked()){
    		if(notify.equals(""))
    			notify = "24";
    		else
    			notify = notify + ",24";
    	}
    	if(cbCinema.isChecked()){
    		if(notify.equals(""))
    			notify = "31";
    		else
    			notify = notify + ",31";
    	}
    	if(cbEvent.isChecked()){
    		if(notify.equals(""))
    			notify = "34";
    		else
    			notify = notify + ",34";
    	}
    }
    
    private void updateNotifyData(){
    	final List<UserSite> userSite= Select.from(UserSite.class).
    			where(Condition.prop("is_Active").eq("1")).list();
    	if(userSite.size() > 0){
            if (Network.isOnline(this.getActivity())) {
        		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
        				"Updating", "Please Wait...");
        		safeUIBlockingUtility.safelyBlockUI();
                API.updateUserSiteService.updateUserSite(userSite.get(0).getUsId(), "'" + notify + "'",
                    new Callback<String>() {
                        @Override
                        public void success(String Updated, Response response) {
                        	userSite.get(0).setNotification(notify);
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
            	userSite.get(0).setIsSync("0");           	
            	userSite.get(0).save();
            	Toast.makeText(getActivity(), "Notification saved successfuly", Toast.LENGTH_SHORT).show();
            }    		
    	}
    }
}
