package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
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
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(weddingCloths.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtType = (TextView)convertView.findViewById(R.id.cloth_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtServiceType = (TextView) convertView.findViewById(R.id.service_type);        
        
        txtName.setText(weddingCloths.get(position).getWeddingClothName());
        txtType.setText(weddingCloths.get(position).getCloth_Type());
        txtDiscription.setText(weddingCloths.get(position).getDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(weddingCloths.get(position).getPrice()));
        txtServiceType.setText(weddingCloths.get(position).getService_Type());    
        
        return convertView;
	}

}
