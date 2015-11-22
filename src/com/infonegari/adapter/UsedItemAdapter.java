package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UsedItem;
import com.infonegari.objects.db.UsedItemType;
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

public class UsedItemAdapter extends BaseAdapter{
	private Context context;
	private List<UsedItem> usedItems;
	
	public UsedItemAdapter(Context context, List<UsedItem> usedItems){
		this.context = context;
		this.usedItems = usedItems;
	}
	@Override
	public int getCount() {
		return usedItems.size();
	}

	@Override
	public Object getItem(int position) {
		return usedItems.get(position);
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
            convertView = mInflater.inflate(R.layout.row_used_item, null);
        }
		
		Location location = null;
		long locationId = usedItems.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		UsedItemType type = null;
		long typeId = usedItems.get(position).getUsedItemTypeId();
		if(typeId != 0){
			type = Select.from(UsedItemType.class).
					where(Condition.prop("Used_Item_Type_Id").eq(typeId)).first();			
		}
		
		UserSite userSite = null;
		String userName = usedItems.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtType = (TextView) convertView.findViewById(R.id.type);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(usedItems.get(position).getUsedItemName());
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        txtDiscription.setText(usedItems.get(position).getDiscription());
        if(type != null){
        	if(SplashScreen.localization == 1)
        		txtType.setText(type.getUsedItemTypeName_am());
        	else
        		txtType.setText(type.getUsedItemTypeName());
        }
        txtPrice.setText(String.valueOf(usedItems.get(position).getPrice()));
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number()); 
	       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
