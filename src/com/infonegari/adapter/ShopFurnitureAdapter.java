package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopFurniture;
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

public class ShopFurnitureAdapter extends BaseAdapter{
	private Context context;
	private List<ShopFurniture> shopFurniture;
	
	public ShopFurnitureAdapter(Context context, List<ShopFurniture> shopFurniture){
		this.context = context;
		this.shopFurniture = shopFurniture;
	}
	@Override
	public int getCount() {
		return shopFurniture.size();
	}

	@Override
	public Object getItem(int position) {
		return shopFurniture.get(position);
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
            convertView = mInflater.inflate(R.layout.row_shop_furniture, null);
        }
		
		Location location = null;
		long locationId = shopFurniture.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
         
		UserSite userSite = null;
		String userName = shopFurniture.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtType = (TextView)convertView.findViewById(R.id.item_type);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(shopFurniture.get(position).getItem_Name());
        txtPrice.setText(String.valueOf(shopFurniture.get(position).getPrice()));
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtDiscription.setText(shopFurniture.get(position).getDiscription()); 
        if(shopFurniture.get(position).getItem_Type().equals("home"))
        	txtType.setText(R.string.sp_home);
        else if(shopFurniture.get(position).getItem_Type().equals("office"))
        	txtType.setText(R.string.sp_home);
        else
        	txtType.setText(R.string.sp_all_furniture);
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());  
	       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
