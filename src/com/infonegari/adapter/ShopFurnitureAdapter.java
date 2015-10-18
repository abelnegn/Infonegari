package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopFurniture;
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
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(shopFurniture.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtCountry = (TextView)convertView.findViewById(R.id.country);
        TextView txtType = (TextView)convertView.findViewById(R.id.item_type);

        
        txtName.setText(shopFurniture.get(position).getItem_Name());
        txtPrice.setText(String.valueOf(shopFurniture.get(position).getPrice()));
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(shopFurniture.get(position).getDiscription());  
        txtCountry.setText(shopFurniture.get(position).getCountry());
        txtType.setText(shopFurniture.get(position).getItem_Type());
        
        return convertView;
	}

}
