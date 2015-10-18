package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.ShopCloth;
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
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(shopCloths.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtCategory = (TextView)convertView.findViewById(R.id.category);
        TextView txtType = (TextView)convertView.findViewById(R.id.cloth_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtColor = (TextView) convertView.findViewById(R.id.color);
        TextView txtSize = (TextView) convertView.findViewById(R.id.size);
        
        
        txtName.setText(shopCloths.get(position).getItem_Name());
        txtCategory.setText(shopCloths.get(position).getCatagory());
        txtType.setText(shopCloths.get(position).getType());
        txtDiscription.setText(shopCloths.get(position).getDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(shopCloths.get(position).getPrice()));
        txtColor.setText(shopCloths.get(position).getColor());
        txtSize.setText(shopCloths.get(position).getSize());
        
        
        return convertView;
	}

}