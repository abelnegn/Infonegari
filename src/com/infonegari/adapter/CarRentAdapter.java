package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.CarListing;
import com.infonegari.objects.db.CarType;
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

public class CarRentAdapter extends BaseAdapter{
	private Context context;
	private List<CarListing> carListings;
	
	public CarRentAdapter(Context context, List<CarListing> carListings){
		this.context = context;
		this.carListings = carListings;
	}
	@Override
	public int getCount() {
		return carListings.size();
	}

	@Override
	public Object getItem(int position) {
		return carListings.get(position);
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
            convertView = mInflater.inflate(R.layout.row_car_rent, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(carListings.get(position).
						getLocationId())).first();

		CarType carType = Select.from(CarType.class).
				where(Condition.prop("Car_Type_Id").eq(carListings.get(position).
						getCarTypeId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtCarType = (TextView) convertView.findViewById(R.id.car_type);
        TextView txtYear = (TextView) convertView.findViewById(R.id.year);
        
        txtName.setText(carListings.get(position).getCarName());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(carListings.get(position).getDiscription());
        txtPrice.setText(String.valueOf(carListings.get(position).getCarPrice()));
        if(carType != null)
        	txtCarType.setText(carType.getCarTypeName());
        txtYear.setText(String.valueOf(carListings.get(position).getYear()));
        
        return convertView;
	}

}
