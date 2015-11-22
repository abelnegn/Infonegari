package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.HandyCategory;
import com.infonegari.objects.db.HandyMan;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UserSite;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HandyManAdapter extends BaseAdapter{
	private Context context;
	private List<HandyMan> handyMans;
	
	public HandyManAdapter(Context context, List<HandyMan> handyMans){
		this.context = context;
		this.handyMans = handyMans;
	}
	@Override
	public int getCount() {
		return handyMans.size();
	}

	@Override
	public Object getItem(int position) {
		return handyMans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_handy_man, null);
        }
		
		Location location = null;
		long locationId = handyMans.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		HandyCategory category = null;
		String categoryId = handyMans.get(position).getCategory();
		if(!categoryId.equals("")){
			category = Select.from(HandyCategory.class).
					where(Condition.prop("hc_Id").eq(categoryId)).first();			
		}
		
		UserSite userSite = null;
		String userName = handyMans.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtCategory = (TextView) convertView.findViewById(R.id.category);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(handyMans.get(position).getItem_Name());
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtDiscription.setText(handyMans.get(position).getDiscription());
        if(category != null){
        	if(SplashScreen.localization == 1)
        		txtCategory.setText(category.getCategory_am());
        	else
        		txtCategory.setText(category.getCategory());
        }
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());  
	       	 txtEmail.setText(userSite.getE_mail());
       }
        
        return convertView;
	}

}
