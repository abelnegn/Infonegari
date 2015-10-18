package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopElectronic;
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
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(shopElectronics.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtCategory = (TextView)convertView.findViewById(R.id.category);
        TextView txtServiceType = (TextView)convertView.findViewById(R.id.service_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtBrand = (TextView) convertView.findViewById(R.id.brand_name);
        
        
        txtName.setText(shopElectronics.get(position).getItem_Name());
        txtCategory.setText(shopElectronics.get(position).getCatagory());
        txtServiceType.setText(shopElectronics.get(position).getService_Type());
        txtDiscription.setText(shopElectronics.get(position).getDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(shopElectronics.get(position).getPrice()));
        txtBrand.setText(shopElectronics.get(position).getBrand_Name());
        
        return convertView;
	}

}
