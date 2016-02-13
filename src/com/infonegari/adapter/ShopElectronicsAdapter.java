package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopElectronic;
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

public class ShopElectronicsAdapter extends BaseAdapter{
	private Context context;
	private List<ShopElectronic> shopElectronics;
	
	public ShopElectronicsAdapter(Context context, List<ShopElectronic> shopElectronics){
		this.context = context;
		this.shopElectronics = shopElectronics;
	}
	@Override
	public int getCount() {
		return shopElectronics.size();
	}

	@Override
	public Object getItem(int position) {
		return shopElectronics.get(position);
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
            convertView = mInflater.inflate(R.layout.row_shop_electronics, null);
        }
		
		Location location = null;
		long locationId = shopElectronics.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
         
		UserSite userSite = null;
		String userName = shopElectronics.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtCategory = (TextView)convertView.findViewById(R.id.category);
        TextView txtServiceType = (TextView)convertView.findViewById(R.id.service_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(shopElectronics.get(position).getItem_Name());
        if(shopElectronics.get(position).getCatagory().equals("tv"))
        	txtCategory.setText(R.string.sp_tv);
        else if(shopElectronics.get(position).getCatagory().equals("refrigirator"))
        	txtCategory.setText(R.string.sp_refrigrator);
        else if(shopElectronics.get(position).getCatagory().equals("mobile"))
        	txtCategory.setText(R.string.sp_mobiles);
        else
        	txtCategory.setText(R.string.sp_all_electronics);
        if(shopElectronics.get(position).getService_Type().equals("sell"))
        	txtServiceType.setText(R.string.sp_sell);
        else if(shopElectronics.get(position).getService_Type().equals("maintenance"))
        	txtServiceType.setText(R.string.sp_maintenance);
        else
        	txtServiceType.setText(R.string.sp_all_service);
        txtDiscription.setText(shopElectronics.get(position).getDiscription());
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtPrice.setText(String.valueOf(shopElectronics.get(position).getPrice()));
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());  
	       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
