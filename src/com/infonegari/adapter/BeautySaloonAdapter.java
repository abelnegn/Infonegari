package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.BeautySaloon;
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

public class BeautySaloonAdapter extends BaseAdapter{
	private Context context;
	private List<BeautySaloon> beautySaloons;
	
	public BeautySaloonAdapter(Context context, List<BeautySaloon> beautySaloons){
		this.context = context;
		this.beautySaloons = beautySaloons;
	}
	@Override
	public int getCount() {
		return beautySaloons.size();
	}

	@Override
	public Object getItem(int position) {
		return beautySaloons.get(position);
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
            convertView = mInflater.inflate(R.layout.row_beauty_saloon, null);
        }
		
		Location location = null;
		long locationId = beautySaloons.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
         
		UserSite userSite = null;
		String userName = beautySaloons.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtType = (TextView)convertView.findViewById(R.id.saloon_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(beautySaloons.get(position).getBeautysaloonsName());
        txtType.setText(beautySaloons.get(position).getBeautysaloonsType());
        txtDiscription.setText(beautySaloons.get(position).getDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(beautySaloons.get(position).getPrice()));      
        if(userSite != null){
       	 	txtPhoneNo.setText(userSite.getPhone_Number());
       	 	txtEmail.setText(userSite.getE_mail());
       }
        
        return convertView;
	}

}
