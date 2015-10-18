package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.NightClub;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NightClubAdapter extends BaseAdapter{
	private Context context;
	private List<NightClub> nightClubs;
	
	public NightClubAdapter(Context context, List<NightClub> nightClubs){
		this.context = context;
		this.nightClubs = nightClubs;
	}
	@Override
	public int getCount() {
		return nightClubs.size();
	}

	@Override
	public Object getItem(int position) {
		return nightClubs.get(position);
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
            convertView = mInflater.inflate(R.layout.row_night_club, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(nightClubs.get(position).
						getLocationId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        
        txtName.setText(nightClubs.get(position).getItem_Name());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(nightClubs.get(position).getDiscription());
        txtPrice.setText(String.valueOf(nightClubs.get(position).getPrice()));
        
        return convertView;
	}

}
