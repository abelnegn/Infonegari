package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.DJ;
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

public class DJAdapter extends BaseAdapter{
	private Context context;
	private List<DJ> djs;
	
	public DJAdapter(Context context, List<DJ> djs){
		this.context = context;
		this.djs = djs;
	}
	@Override
	public int getCount() {
		return djs.size();
	}

	@Override
	public Object getItem(int position) {
		return djs.get(position);
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
            convertView = mInflater.inflate(R.layout.row_dj, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(djs.get(position).
						getLocationId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        
        txtName.setText(djs.get(position).getDjName());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(djs.get(position).getDiscription());
        txtPrice.setText(String.valueOf(djs.get(position).getPrice()));
        
        return convertView;
	}

}
