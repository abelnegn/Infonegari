package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.HallType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UserSite;
import com.infonegari.objects.db.WeddingHall;
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

public class WeddingHallAdapter extends BaseAdapter{
	private Context context;
	private List<WeddingHall> weddingHalls;
	
	public WeddingHallAdapter(Context context, List<WeddingHall> weddingHalls){
		this.context = context;
		this.weddingHalls = weddingHalls;
	}
	@Override
	public int getCount() {
		return weddingHalls.size();
	}

	@Override
	public Object getItem(int position) {
		return weddingHalls.get(position);
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
            convertView = mInflater.inflate(R.layout.row_wedding_hall, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(weddingHalls.get(position).
						getLocationId())).first();

		HallType hallType = Select.from(HallType.class).
				where(Condition.prop("ht_Id").eq(weddingHalls.get(position).
						getHall_Type())).first();
		
		final UserSite userSite = Select.from(UserSite.class).
				where(Condition.prop("UserName").eq(weddingHalls.get(position).
						getUser_Name())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtServiceType = (TextView)convertView.findViewById(R.id.service_type);
        TextView txtDateAvailable = (TextView)convertView.findViewById(R.id.date_available);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtHallType = (TextView) convertView.findViewById(R.id.hall_type);
        TextView txtBreakFast = (TextView) convertView.findViewById(R.id.break_fast);
        TextView txtLunch = (TextView) convertView.findViewById(R.id.lunch);
        TextView txtDinner = (TextView) convertView.findViewById(R.id.dinner);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(weddingHalls.get(position).getWeddingHallName());
        txtServiceType.setText(weddingHalls.get(position).getServiceType());
        txtDateAvailable.setText(weddingHalls.get(position).getDateAvailable());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(weddingHalls.get(position).getPrice()));  
        txtDiscription.setText(weddingHalls.get(position).getDiscription());
        if(hallType != null)
        	txtHallType.setText(hallType.getHall_Type());
        txtBreakFast.setText(weddingHalls.get(position).getBreak_Fast());
        txtLunch.setText(weddingHalls.get(position).getLunch());
        txtDinner.setText(weddingHalls.get(position).getDinner());
        if(userSite != null){
       	 txtPhoneNo.setText(userSite.getPhone_Number());
       	 txtPhoneNo.setOnClickListener(new OnClickListener() {			
 			@Override
 			public void onClick(View arg0) {
 				Intent callIntent = new Intent(Intent.ACTION_CALL);
 				callIntent.setData(Uri.parse("tel:" + userSite.getPhone_Number()));
 				context.startActivity(callIntent);
 			}
 		});  
       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
