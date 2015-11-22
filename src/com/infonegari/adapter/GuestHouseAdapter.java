package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.GuestHouse;
import com.infonegari.objects.db.HouseType;
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

public class GuestHouseAdapter extends BaseAdapter{
	private Context context;
	private List<GuestHouse> guestHouses;
	
	public GuestHouseAdapter(Context context, List<GuestHouse> guestHouses){
		this.context = context;
		this.guestHouses = guestHouses;
	}
	@Override
	public int getCount() {
		return guestHouses.size();
	}

	@Override
	public Object getItem(int position) {
		return guestHouses.get(position);
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
            convertView = mInflater.inflate(R.layout.row_guest_house, null);
        }
		
		Location location = null;
		long locationId = guestHouses.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
		
		UserSite userSite = null;
		String userName = guestHouses.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtNoRooms = (TextView) convertView.findViewById(R.id.no_rooms);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(guestHouses.get(position).getGuestHouseName());
        if(location != null){
    		if(SplashScreen.localization == 1)
    			txtLocation.setText(location.getLocationName_am());
    		else
    			txtLocation.setText(location.getLocationName());
        }
        txtDiscription.setText(guestHouses.get(position).getGuestHouseDiscripton());
        txtPrice.setText(String.valueOf(guestHouses.get(position).getPrice()));
        txtNoRooms.setText(guestHouses.get(position).getNoRooms());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtEmail.setText(userSite.getE_mail());
       }        
        return convertView;
	}

}
