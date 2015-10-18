package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.CarType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.WeddingCar;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeddingCarAdapter extends BaseAdapter{
	private Context context;
	private List<WeddingCar> weddingCars;
	
	public WeddingCarAdapter(Context context, List<WeddingCar> weddingCars){
		this.context = context;
		this.weddingCars = weddingCars;
	}
	@Override
	public int getCount() {
		return weddingCars.size();
	}

	@Override
	public Object getItem(int position) {
		return weddingCars.get(position);
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
            convertView = mInflater.inflate(R.layout.row_wedding_car, null);
        }
		
		CarType carType = Select.from(CarType.class).
				where(Condition.prop("Car_Type_Id").eq(weddingCars.get(position).
						getCarTypeId())).first();
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(weddingCars.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtCarType = (TextView)convertView.findViewById(R.id.car_type);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        
        txtName.setText(weddingCars.get(position).getWeddingCarName());
        if(carType != null)
        	txtCarType.setText(carType.getCarTypeName());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(weddingCars.get(position).getPrice()));
        txtDiscription.setText(weddingCars.get(position).getDiscription());
        
        return convertView;
	}

}
