package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.UserSite;
import com.infonegari.objects.db.WeddingCloth;
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
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(weddingCloths.get(position).
						getLocationId())).first();
         
		final UserSite userSite = Select.from(UserSite.class).
				where(Condition.prop("UserName").eq(weddingCloths.get(position).
						getUser_Name())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtType = (TextView)convertView.findViewById(R.id.cloth_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtServiceType = (TextView) convertView.findViewById(R.id.service_type);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(weddingCloths.get(position).getWeddingClothName());
        txtType.setText(weddingCloths.get(position).getCloth_Type());
        txtDiscription.setText(weddingCloths.get(position).getDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(weddingCloths.get(position).getPrice()));
        txtServiceType.setText(weddingCloths.get(position).getService_Type());    
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
