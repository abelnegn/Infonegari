package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Clinic;
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

public class ClinicAdapter extends BaseAdapter{
	private Context context;
	private List<Clinic> clinics;
	
	public ClinicAdapter(Context context, List<Clinic> clinics){
		this.context = context;
		this.clinics = clinics;
	}
	@Override
	public int getCount() {
		return clinics.size();
	}

	@Override
	public Object getItem(int position) {
		return clinics.get(position);
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
            convertView = mInflater.inflate(R.layout.row_clinic, null);
        }
		
		Location location = null;
		long locationId = clinics.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
		
		UserSite userSite = null;
		String userName = clinics.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtClinicType =  (TextView)convertView.findViewById(R.id.clinic_type);
        TextView txtJobType =  (TextView)convertView.findViewById(R.id.job_type);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(clinics.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(clinics.get(position).getDiscription());
        txtPrice.setText(String.valueOf(clinics.get(position).getPrice()));
        txtClinicType.setText(clinics.get(position).getClinic_Type());
        txtJobType.setText(clinics.get(position).getJob_Type());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtEmail.setText(userSite.getE_mail());
        }
        
        return convertView;
	}

}
