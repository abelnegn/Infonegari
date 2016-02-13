package com.infonegari.adapter;

import java.util.ArrayList;

import com.infonegari.activity.R;
import com.infonegari.model.EmergencyItem;
import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EmergencyAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<EmergencyItem> navDrawerItems;
	
	public EmergencyAdapter(Context context, ArrayList<EmergencyItem> navDrawerItems){
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
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
            convertView = mInflater.inflate(R.layout.row_emergency_call, null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtPhoneNo = (TextView) convertView.findViewById(R.id.phone_no);
         
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        txtPhoneNo.setText(navDrawerItems.get(position).getPhoneNo());
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
        
        return convertView;
	}
}