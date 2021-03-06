package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UserSite;
import com.infonegari.objects.db.WeddingCloth;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeddingClothAdapter extends BaseAdapter{
	private Context context;
	private List<WeddingCloth> weddingCloths;
	
	public WeddingClothAdapter(Context context, List<WeddingCloth> weddingCloths){
		this.context = context;
		this.weddingCloths = weddingCloths;
	}
	@Override
	public int getCount() {
		return weddingCloths.size();
	}

	@Override
	public Object getItem(int position) {
		return weddingCloths.get(position);
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
            convertView = mInflater.inflate(R.layout.row_wedding_cloth, null);
        }
		
		Location location = null;
		long locationId = weddingCloths.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
         
		UserSite userSite = null;
		String userName = weddingCloths.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtType = (TextView)convertView.findViewById(R.id.cloth_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtServiceType = (TextView) convertView.findViewById(R.id.service_type);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(weddingCloths.get(position).getWeddingClothName());
        if(weddingCloths.get(position).getCloth_Type().equals("modern"))
        	txtType.setText(R.string.sp_modern);
        else if(weddingCloths.get(position).getCloth_Type().equals("traditional"))
        	txtType.setText(R.string.sp_traditional);
        else
        	txtType.setText(R.string.sp_all_cloth_type);
        
        txtDiscription.setText(weddingCloths.get(position).getDiscription());
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtPrice.setText(String.valueOf(weddingCloths.get(position).getPrice()));
        if(weddingCloths.get(position).getService_Type().equals("rental"))
        	txtServiceType.setText(R.string.sp_rental); 
        else if(weddingCloths.get(position).getService_Type().equals("sell"))
        	txtServiceType.setText(R.string.sp_sell);
        else
        	txtServiceType.setText(R.string.sp_all_service);
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number()); 
	       	 txtEmail.setText(userSite.getE_mail());
       }
        
        return convertView;
	}

}
