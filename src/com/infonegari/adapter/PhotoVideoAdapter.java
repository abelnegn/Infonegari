package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.PhotoVideo;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PhotoVideoAdapter extends BaseAdapter{
	private Context context;
	private List<PhotoVideo> photoVideos;
	
	public PhotoVideoAdapter(Context context, List<PhotoVideo> photoVideos){
		this.context = context;
		this.photoVideos = photoVideos;
	}
	@Override
	public int getCount() {
		return photoVideos.size();
	}

	@Override
	public Object getItem(int position) {
		return photoVideos.get(position);
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
            convertView = mInflater.inflate(R.layout.row_photo_video, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(photoVideos.get(position).
						getLocationId())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtWorkType = (TextView) convertView.findViewById(R.id.work_type);
        
        txtName.setText(photoVideos.get(position).getPhotoVideoName());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(photoVideos.get(position).getDiscription());
        txtPrice.setText(String.valueOf(photoVideos.get(position).getPrice()));
        txtWorkType.setText(photoVideos.get(position).getWorkType());
        
        return convertView;
	}

}
