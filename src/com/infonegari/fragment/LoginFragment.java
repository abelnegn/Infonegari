package com.infonegari.fragment;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.infonegari.activity.R;
import com.infonegari.objects.db.UserSite;
import com.infonegari.service.API;
import com.infonegari.util.Network;
import com.infonegari.util.OfflineDataHelper;
import com.infonegari.util.SafeUIBlockingUtility;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class LoginFragment extends DialogFragment implements OfflineDataHelper.OfflineDataSaveListener{
	private View rootView;
	private boolean isModal = false;
	private Button btnLogin, btnCancel;
	private EditText txtUserName, txtPassword;
	SafeUIBlockingUtility safeUIBlockingUtility;

    public static LoginFragment newInstance()
    {
    	LoginFragment frag = new LoginFragment();
        frag.isModal = true;
        return frag;
    }
    
	public LoginFragment(){
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        txtUserName = (EditText)rootView.findViewById(R.id.user_name);
        txtPassword = (EditText)rootView.findViewById(R.id.password);
        btnLogin = (Button)rootView.findViewById(R.id.btn_login);
        btnCancel = (Button)rootView.findViewById(R.id.btn_cancel);
        
        getDialog().setTitle(getString(R.string.menu_login));
        
        final String MenuId = getArguments().getString("Menu_Id");
        if(MenuId.equals("DifferentUser")){
    		List<UserSite> userList = Select.from(UserSite.class).where(Condition.prop("is_Active").eq("1")).list();
    		if(userList.size() > 0){
    			txtUserName.setText(userList.get(0).getUser_Name());
    			txtPassword.setText(userList.get(0).getPass_Word());
    		}
        }
        
        btnLogin.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				if(txtUserName.getText().toString().equals("") || 
						txtPassword.getText().toString().equals("")){
					Toast.makeText(getActivity(), "User name or Password are empty", Toast.LENGTH_LONG).show();
					return;
				}
				UserSite user = getUserInfo();
		    	if(user != null){
		    		if(user.getPass_Word().equals(txtPassword.getText().toString())){
		    			List<UserSite> userList = Select.from(UserSite.class).where(Condition.prop("is_Active").eq("1")).list();
		        		if(userList.size() > 0){			
		        			userList.get(0).setIsActive("0");
		        			userList.get(0).save();
		        		}
		    			user.setIsActive("1");
		    			user.save();
		    			getDialog().dismiss();
		    			if(MenuId.equals("Notification"))
		    				callNotify();
		    			if(MenuId.equals("AddList"))
		    				callAddList();
		    		}else{
		    			Toast.makeText(getActivity(), "Invalid Passowrd please try again.", Toast.LENGTH_LONG).show();
		    		}
		    	}else{
		    		downloadUser();
		    		Toast.makeText(getActivity(), "Invalid User name or Passowrd please try again.", Toast.LENGTH_LONG).show();
		    	}
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

    private void callNotify(){
    	Bundle arguments = new Bundle();
    	arguments.putString("User_Name", txtUserName.getText().toString());
        NotificationFragment notifyFragment = new NotificationFragment().newInstance();
        notifyFragment.setArguments(arguments);
        notifyFragment.show(getFragmentManager().beginTransaction(), "NotificationFragment");					
    }

    private void callAddList(){
    	Bundle arguments = new Bundle();
    	arguments.putString("User_Name", txtUserName.getText().toString());
        ListingCategoryDialog listFragment = new ListingCategoryDialog().newInstance();
        listFragment.setArguments(arguments);
        listFragment.show(getFragmentManager().beginTransaction(), "ListDialogFragment");					
    }
    
    private UserSite getUserInfo(){
    	UserSite userSite = null;
    	List<UserSite> userList = Select.from(UserSite.class).where(Condition.
    			prop("UserName").eq(txtUserName.getText().toString())).list();
    	if(userList.size() > 0){
    		userSite = userList.get(0);
    	}
    	return userSite;
    }

    private void downloadUser(){
        if (Network.isOnline(this.getActivity())) {
    		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
    				"Updating", "Please Wait...");
    		safeUIBlockingUtility.safelyBlockUI();
        	long usId = 0;
        	List<UserSite> usList= UserSite.findWithQuery(UserSite.class, 
        			"SELECT * FROM  User_site WHERE us_Id = " +
        			"(SELECT max(us_Id) FROM  User_site)");
        	if(usList.size() > 0)
        		usId = usList.get(0).getUsId();
        	
            API.userSiteService.getUserSite(usId,
                new Callback<List<UserSite>>() {
                    @Override
                    public void success(List<UserSite> userSites, Response response) {
                        for (UserSite userSite : userSites) {
                            if (userSite != null) {
                            	userSite.setIsActive("0");
                            	userSite.setIsSync("1");
                                OfflineDataHelper helper = new OfflineDataHelper();
                                helper.setOfflineDataSaveListener(LoginFragment.this);
                                helper.saveUserSiteData(userSite);
                            }
                        }
                        safeUIBlockingUtility.safelyUnBlockUI();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    	safeUIBlockingUtility.safelyUnBlockUI();
                    }
            });
        }else{
        	Toast.makeText(getActivity(), "Invalid User name or Passowrd please try again.", Toast.LENGTH_LONG).show();
        }
    }
    
	@Override
	public void dataSaved() {

	}
}
