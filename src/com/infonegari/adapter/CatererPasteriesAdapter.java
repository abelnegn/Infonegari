package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.CaterersPasteries;
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

public class CatererPasteriesAdapter extends BaseAdapter{
	private Context context;
	private List<CaterersPasteries> catererPasteries;
	
	public CatererPasteriesAdapter(Context context, List<CaterersPasteries> catererPasteries){
		this.context = context;
		this.catererPasteries = catererPasteries;
	}
	@Override
	public int getCount() {
		return catererPasteries.size();
	}

	@Override
	public Object getItem(int position) {
		return catererPasteries.get(position);
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
            convertView = mInflater.inflate(R.layout.row_caterer_pasteries, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(catererPasteries.get(position).
						getLocationId())).first();
         
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtServiceType = (TextView) convertView.findViewById(R.id.service_type);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        
        txtName.setText(catererPasteries.get(position).getCnPIdName());
        txtServiceType.setText(catererPasteries.get(position).getServiceType());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(catererPasteries.get(position).getPrice()));
        txtDiscription.setText(catererPasteries.get(position).getDiscription());
        
        return convertView;
	}

}
