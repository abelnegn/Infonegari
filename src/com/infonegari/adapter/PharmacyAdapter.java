package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.Pharmacy;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PharmacyAdapter extends BaseAdapter{
	private Context context;
	private List<Pharmacy> pharmacies;
	
	public PharmacyAdapter(Context context, List<Pharmacy> pharmacies){
		this.context = context;
		this.pharmacies = pharmacies;
	}
	@Override
	public int getCount() {
		return pharmacies.size();
	}

	@Override
	public Object getItem(int position) {
		return pharmacies.get(position);
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
            convertView = mInflater.inflate(R.layout.row_pharmacy, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(pharmacies.get(position).
						getLocationId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtPharmacyType =  (TextView)convertView.findViewById(R.id.pharmacy_type);
        
        txtName.setText(pharmacies.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(pharmacies.get(position).getDiscription());
        txtPrice.setText(String.valueOf(pharmacies.get(position).getPrice()));
        txtPharmacyType.setText(pharmacies.get(position).getPharma_Type());
        
        return convertView;
	}

}
