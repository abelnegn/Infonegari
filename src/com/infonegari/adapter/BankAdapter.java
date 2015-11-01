package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Bank;
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

public class BankAdapter extends BaseAdapter{
	private Context context;
	private List<Bank> banks;
	
	public BankAdapter(Context context, List<Bank> banks){
		this.context = context;
		this.banks = banks;
	}
	@Override
	public int getCount() {
		return banks.size();
	}

	@Override
	public Object getItem(int position) {
		return banks.get(position);
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
            convertView = mInflater.inflate(R.layout.row_bank, null);
        }
		
		Location location = null;
		long locationId = banks.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
		
		UserSite userSite = null;
		String userName = banks.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtBranch =  (TextView)convertView.findViewById(R.id.branch_name);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(banks.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(banks.get(position).getDiscription());
        txtPrice.setText(String.valueOf(banks.get(position).getPrice()));
        txtBranch.setText(banks.get(position).getBranch_Name());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtEmail.setText(userSite.getE_mail());
       }
        
        return convertView;
	}

}
