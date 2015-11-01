package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Cinema;
import com.infonegari.objects.db.CinemaPlace;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.MovieType;
import com.infonegari.objects.db.UserSite;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CinemaAdapter extends BaseAdapter{
	private Context context;
	private List<Cinema> cinemas;
	
	public CinemaAdapter(Context context, List<Cinema> cinemas){
		this.context = context;
		this.cinemas = cinemas;
	}
	@Override
	public int getCount() {
		return cinemas.size();
	}

	@Override
	public Object getItem(int position) {
		return cinemas.get(position);
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
            convertView = mInflater.inflate(R.layout.row_cinema, null);
        }
		
		Location location = null;
		long locationId = cinemas.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		MovieType type = null;
		long typeId = cinemas.get(position).getMovie_Type();
		if(typeId != 0){
			type = Select.from(MovieType.class).
					where(Condition.prop("mt_Id").eq(typeId)).first();			
		}

		CinemaPlace hall = null;
		String hallId = cinemas.get(position).getHallId();
		if(!hallId.equals("")){
			hall = Select.from(CinemaPlace.class).
					where(Condition.prop("cpId").eq(hallId)).first();			
		}
		
		UserSite userSite = null;
		String userName = cinemas.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtType = (TextView) convertView.findViewById(R.id.movie_type);
        TextView txtShowTime = (TextView) convertView.findViewById(R.id.show_time);
        TextView txtShowDate = (TextView) convertView.findViewById(R.id.show_date);
        TextView txtHall = (TextView) convertView.findViewById(R.id.cinema);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(cinemas.get(position).getCinemaTitle());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtDiscription.setText(cinemas.get(position).getDiscription());
        if(type != null)
        	txtType.setText(type.getMovie_Type());
        txtShowTime.setText(cinemas.get(position).getShowTime());
        txtShowDate.setText(cinemas.get(position).getShowDate());
        if(hall != null)
        	txtHall.setText(hall.getCinema_Name());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());  
	       	 txtEmail.setText(userSite.getE_mail());
       }
	        
        return convertView;
	}

}
