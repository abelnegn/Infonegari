package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.HouseListing;
import com.infonegari.objects.db.HouseType;
import com.infonegari.objects.db.Location;
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

public class HouseRentAdapter extends BaseAdapter{
	private Context context;
	private List<HouseListing> houseListings;
	
	public HouseRentAdapter(Context context, List<HouseListing> houseListings){
		this.context = context;
		this.houseListings = houseListings;
	}
	@Override
	public int getCount() {
		return houseListings.size();
	}

	@Override
	public Object getItem(int position) {
		return houseListings.get(position);
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
            convertView = mInflater.inflate(R.layout.row_house_rent, null);
        }
		
		Location location = null;
		long locationId = houseListings.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		HouseType houseType = null;
		long typeId = houseListings.get(position).getHouseTypeId();
		if(typeId !=0){
			houseType = Select.from(HouseType.class).
					where(Condition.prop("House_Type_Id").eq(houseListings.get(position).
							getHouseTypeId())).first();			
		}
		
		UserSite userSite = null;
		String userName = houseListings.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtHouseType = (TextView) convertView.findViewById(R.id.house_type);
        TextView txtNoRooms = (TextView) convertView.findViewById(R.id.no_rooms);
        TextView txtLotSize = (TextView) convertView.findViewById(R.id.lot_size);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(houseListings.get(position).getHouse_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(houseListings.get(position).getHouseDiscription());
        txtPrice.setText(String.valueOf(houseListings.get(position).getHousePrice()));
        if(houseType != null)
        	txtHouseType.setText(houseType.getHouseTypeName());
        txtNoRooms.setText(String.valueOf(houseListings.get(position).getNoRooms()));
        txtLotSize.setText(houseListings.get(position).getLotSize());
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
