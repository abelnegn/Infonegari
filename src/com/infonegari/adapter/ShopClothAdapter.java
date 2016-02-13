package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopCloth;
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

public class ShopClothAdapter extends BaseAdapter{
	private Context context;
	private List<ShopCloth> shopCloths;
	
	public ShopClothAdapter(Context context, List<ShopCloth> shopCloths){
		this.context = context;
		this.shopCloths = shopCloths;
	}
	@Override
	public int getCount() {
		return shopCloths.size();
	}

	@Override
	public Object getItem(int position) {
		return shopCloths.get(position);
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
            convertView = mInflater.inflate(R.layout.row_shop_cloth, null);
        }
		
		Location location = null;
		long locationId = shopCloths.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}
        
		UserSite userSite = null;
		String userName = shopCloths.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtCategory = (TextView)convertView.findViewById(R.id.category);
        TextView txtType = (TextView)convertView.findViewById(R.id.cloth_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtColor = (TextView) convertView.findViewById(R.id.color);
        TextView txtSize = (TextView) convertView.findViewById(R.id.size);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(shopCloths.get(position).getItem_Name());
        if(shopCloths.get(position).getCatagory().equals("female_cloth"))
        	txtCategory.setText(R.string.sp_female_cloth);
        else if(shopCloths.get(position).getCatagory().equals("Male_cloth"))
        	txtCategory.setText(R.string.sp_male_cloth);
        else if(shopCloths.get(position).getCatagory().equals("Kids_cloth"))
        	txtCategory.setText(R.string.sp_kids_cloth);
        else if(shopCloths.get(position).getCatagory().equals("cloth_designer"))
        	txtCategory.setText(R.string.sp_cloth_designer);
        else
        	txtCategory.setText(R.string.sp_all_category);
        
        if(shopCloths.get(position).getType().equals("modern"))
        	txtType.setText(R.string.sp_modern);
        else if(shopCloths.get(position).getType().equals("traditional"))
        	txtType.setText(R.string.sp_traditional);
        else
        	txtType.setText(R.string.sp_all_cloth_type);
        txtDiscription.setText(shopCloths.get(position).getDiscription());
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtPrice.setText(String.valueOf(shopCloths.get(position).getPrice()));
        txtColor.setText(shopCloths.get(position).getColor());
        txtSize.setText(shopCloths.get(position).getSize());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number()); 
	       	 txtEmail.setText(userSite.getE_mail());
       }
        
        return convertView;
	}

}
