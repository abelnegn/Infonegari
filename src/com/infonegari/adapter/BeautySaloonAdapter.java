package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.BeautySaloon;
import com.infonegari.objects.db.Location;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BeautySaloonAdapter extends BaseAdapter{
	private Context context;
	private List<BeautySaloon> beautySaloons;
	
	public BeautySaloonAdapter(Context context, List<BeautySaloon> beautySaloons){
		this.context = context;
		this.beautySaloons = beautySaloons;
	}
	@Override
	public int getCount() {
		return beautySaloons.size();
	}

	@Override
	public Object getItem(int position) {
		return beautySaloons.get(position);
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
            convertView = mInflater.inflate(R.layout.row_beauty_saloon, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(beautySaloons.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtType = (TextView)convertView.findViewById(R.id.saloon_type);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        
        txtName.setText(beautySaloons.get(position).getBeautysaloonsName());
        txtType.setText(beautySaloons.get(position).getBeautysaloonsType());
        txtDiscription.setText(beautySaloons.get(position).getDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(beautySaloons.get(position).getPrice()));      
        
        return convertView;
	}

}