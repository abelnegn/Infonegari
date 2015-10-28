package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopComputer;
import com.infonegari.objects.db.UserSite;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShopComputerAdapter extends BaseAdapter{
	private Context context;
	private List<ShopComputer> shopComputer;
	
	public ShopComputerAdapter(Context context, List<ShopComputer> shopComputer){
		this.context = context;
		this.shopComputer = shopComputer;
	}
	@Override
	public int getCount() {
		return shopComputer.size();
	}

	@Override
	public Object getItem(int position) {
		return shopComputer.get(position);
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
            convertView = mInflater.inflate(R.layout.row_shop_computer, null);
        }
		
		Location location = null;
		long locationId = shopComputer.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
         
		UserSite userSite = null;
		String userName = shopComputer.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtBrandName = (TextView)convertView.findViewById(R.id.brand_name);
        TextView txtService = (TextView)convertView.findViewById(R.id.service_type);
        TextView txtType = (TextView)convertView.findViewById(R.id.item_type);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(shopComputer.get(position).getItem_Name());
        txtPrice.setText(String.valueOf(shopComputer.get(position).getPrice()));
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(shopComputer.get(position).getDiscription());  
        txtBrandName.setText(shopComputer.get(position).getBrand_Name());
        txtService.setText(shopComputer.get(position).getService_Type());
        txtType.setText(shopComputer.get(position).getItem_Type());
        if(userSite != null){
	       	 final String phoneNo = userSite.getPhone_Number();
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtPhoneNo.setOnClickListener(new OnClickListener() {			
	 			@Override
	 			public void onClick(View arg0) {
	 				Intent callIntent = new Intent(Intent.ACTION_CALL);
	 				callIntent.setData(Uri.parse("tel:" + phoneNo));
	 				context.startActivity(callIntent);
	 			}
	 		});  
	       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
