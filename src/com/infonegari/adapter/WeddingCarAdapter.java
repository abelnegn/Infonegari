package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.CarType;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UserSite;
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
		
		CarType carType = null;
		long typeId = weddingCars.get(position).getCarTypeId();
		if(typeId != 0){
			carType = Select.from(CarType.class).
					where(Condition.prop("Car_Type_Id").eq(weddingCars.get(position).
							getCarTypeId())).first();			
		}
		
		Location location = null;
		long locationId = weddingCars.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
         
		UserSite userSite = null;
		String userName = weddingCars.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtCarType = (TextView)convertView.findViewById(R.id.car_type);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(weddingCars.get(position).getWeddingCarName());
        if(carType != null){
        	if(SplashScreen.localization == 1)
        		txtCarType.setText(carType.getCarTypeName_am());
        	else
        		txtCarType.setText(carType.getCarTypeName());
        }
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtPrice.setText(String.valueOf(weddingCars.get(position).getPrice()));
        txtDiscription.setText(weddingCars.get(position).getDiscription());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number()); 
	       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
