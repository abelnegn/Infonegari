package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.NightClub;
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

public class NightClubAdapter extends BaseAdapter{
	private Context context;
	private List<NightClub> nightClubs;
	
	public NightClubAdapter(Context context, List<NightClub> nightClubs){
		this.context = context;
		this.nightClubs = nightClubs;
	}
	@Override
	public int getCount() {
		return nightClubs.size();
	}

	@Override
	public Object getItem(int position) {
		return nightClubs.get(position);
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
            convertView = mInflater.inflate(R.layout.row_night_club, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(nightClubs.get(position).
						getLocationId())).first();
		
		final UserSite userSite = Select.from(UserSite.class).
				where(Condition.prop("UserName").eq(nightClubs.get(position).
						getUser_Name())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(nightClubs.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(nightClubs.get(position).getDiscription());
        txtPrice.setText(String.valueOf(nightClubs.get(position).getPrice()));
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
