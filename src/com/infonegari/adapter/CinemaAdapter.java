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
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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

		
		String[] hallSeparated = cinemas.get(position).getCalendar().split("@");
		for(int i=1; i< hallSeparated.length; i++){
			if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater)
	                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.row_cinema, null);
	        }
			String[] schedule = hallSeparated[i].split(",");
			
			Location location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(cinemas.get(position).
							getLocationId())).first();

			MovieType type = Select.from(MovieType.class).
					where(Condition.prop("mt_Id").eq(cinemas.get(position).
							getMovie_Type())).first();

			CinemaPlace hall = Select.from(CinemaPlace.class).
					where(Condition.prop("cpId").eq(schedule[1])).first();
			
			final UserSite userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(cinemas.get(position).
							getUser_Name())).first();
			
	        TextView txtName = (TextView) convertView.findViewById(R.id.name);
	        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
	        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
	        TextView txtType = (TextView) convertView.findViewById(R.id.movie_type);
	        TextView txtShowTime = (TextView) convertView.findViewById(R.id.show_time);
	        TextView txtShowDate = (TextView) convertView.findViewById(R.id.show_date);
	        TextView txtHall = (TextView) convertView.findViewById(R.id.cinema);
	        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
	        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
	        
	        txtName.setText(cinemas.get(position).getDiscription());
	        if(location != null)
	        	txtLocation.setText(location.getLocationName());
	        txtDiscription.setText(cinemas.get(position).getDiscription());
	        if(type != null)
	        	txtType.setText(type.getMovie_Type());
	        txtShowTime.setText(schedule[2]);
	        String showDate = "";
	        for(int j =3; j< schedule.length; j++){
	        	showDate += schedule[j];
	        }
	        txtShowDate.setText(showDate);
	        if(hall != null)
	        	txtHall.setText(hall.getCinema_Name());
	        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtPhoneNo.setOnClickListener(new OnClickListener() {			
	 			@Override
	 			public void onClick(View arg0) {
	 				Intent callIntent = new Intent(Intent.ACTION_CALL);
	 				callIntent.setData(Uri.parse("tel:" + userSite.getPhone_Number()));
	 				context.startActivity(callIntent);
	 			}
	 		});  
	       	 txtEmail.setText(userSite.getE_mail());
	       }
	        
		}		

        return convertView;
	}

}
