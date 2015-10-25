package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UsedItem;
import com.infonegari.objects.db.UsedItemType;
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
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(usedItems.get(position).
						getLocationId())).first();

		UsedItemType type = Select.from(UsedItemType.class).
				where(Condition.prop("Used_Item_Type_Id").eq(usedItems.get(position).
						getUsedItemTypeId())).first();
		
		final UserSite userSite = Select.from(UserSite.class).
				where(Condition.prop("UserName").eq(usedItems.get(position).
						getUser_Name())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtType = (TextView) convertView.findViewById(R.id.type);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(usedItems.get(position).getUsedItemName());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(usedItems.get(position).getDiscription());
        if(type != null)
        	txtType.setText(type.getUsedItemTypeName());
        txtPrice.setText(String.valueOf(usedItems.get(position).getPrice()));
        if(userSite != null){
       	 txtPhoneNo.setText(userSite.getPhone_Number());
       	 txtPhoneNo.setOnClickListener(new OnClickListener() {			
 			@Override
 			public void onClick(View arg0) {
 				Intent callIntent = new Intent(Intent.ACTION_CALL);
 				callIntent.setData(Uri.parse("tel:" + userSite.getPhone_Number()));
 				context.startActivity(callIntent);
 			}
 		});  
       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
