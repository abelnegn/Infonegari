package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.Restaurant;
import com.infonegari.objects.db.RestaurantType;
import com.infonegari.objects.db.UserSite;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RestaurantAdapter extends BaseAdapter{
	private Context context;
	private List<Restaurant> restaurants;
	
	public RestaurantAdapter(Context context, List<Restaurant> restaurants){
		this.context = context;
		this.restaurants = restaurants;
	}
	@Override
	public int getCount() {
		return restaurants.size();
	}

	@Override
	public Object getItem(int position) {
		return restaurants.get(position);
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
            convertView = mInflater.inflate(R.layout.row_restaurant, null);
        }
		
		Location location = null;
		long locationId = restaurants.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		RestaurantType type = null;
		long typeId = restaurants.get(position).getRestaurant_id();
		if(typeId != 0){
			type = Select.from(RestaurantType.class).
					where(Condition.prop("Restaurant_Type_Id").eq(restaurants.get(position).
							getRestaurantTypeId())).first();			
		}
		
		UserSite userSite = null;
		String userName = restaurants.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtType = (TextView) convertView.findViewById(R.id.type);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(restaurants.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(restaurants.get(position).getDiscription());
        if(type != null)
        	txtType.setText(type.getRestaurantTypeName());
        if(userSite != null){
	       	 final String phoneNo = userSite.getPhone_Number();
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtPhoneNo.setOnClickListener(new OnClickListener() {			
	 			@Override
	 			public void onClick(View arg0) {
	 				Intent callIntent = new Intent(Intent.ACTION_CALL);
	 				callIntent.setData(Uri.parse("tel:" + phoneNo));
	 				context.startActivity(callIntent);
	 			}
	 		});  
	       	 txtEmail.setText(userSite.getE_mail());
       }        
        return convertView;
	}

}
