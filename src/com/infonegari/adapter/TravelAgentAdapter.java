package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.TravelAgent;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TravelAgentAdapter extends BaseAdapter{
	private Context context;
	private List<TravelAgent> travelAgents;
	
	public TravelAgentAdapter(Context context, List<TravelAgent> travelAgents){
		this.context = context;
		this.travelAgents = travelAgents;
	}
	@Override
	public int getCount() {
		return travelAgents.size();
	}

	@Override
	public Object getItem(int position) {
		return travelAgents.get(position);
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
            convertView = mInflater.inflate(R.layout.row_travel_agent, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(travelAgents.get(position).
						getLocationId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        
        txtName.setText(travelAgents.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(travelAgents.get(position).getDiscription());
        txtPrice.setText(String.valueOf(travelAgents.get(position).getPrice()));
        
        return convertView;
	}

}