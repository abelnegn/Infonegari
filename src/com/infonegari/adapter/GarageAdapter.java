package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Guarage;
import com.infonegari.objects.db.Location;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GarageAdapter extends BaseAdapter{
	private Context context;
	private List<Guarage> garages;
	
	public GarageAdapter(Context context, List<Guarage> garages){
		this.context = context;
		this.garages = garages;
	}
	@Override
	public int getCount() {
		return garages.size();
	}

	@Override
	public Object getItem(int position) {
		return garages.get(position);
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
            convertView = mInflater.inflate(R.layout.row_garage, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(garages.get(position).
						getLocationId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtGarageType =  (TextView)convertView.findViewById(R.id.garage_type);
        TextView txtJobType =  (TextView)convertView.findViewById(R.id.job_type);
        
        txtName.setText(garages.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(garages.get(position).getDiscription());
        txtPrice.setText(String.valueOf(garages.get(position).getPrice()));
        txtGarageType.setText(garages.get(position).getGarage_Type());
        txtJobType.setText(garages.get(position).getJob_Type());
        
        return convertView;
	}

}