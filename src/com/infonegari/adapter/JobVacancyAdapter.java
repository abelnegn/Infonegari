package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Jobs;
import com.infonegari.objects.db.Location;
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

public class JobVacancyAdapter extends BaseAdapter{
	private Context context;
	private List<Jobs> jobVacancy;
	
	public JobVacancyAdapter(Context context, List<Jobs> jobVacancy){
		this.context = context;
		this.jobVacancy = jobVacancy;
	}
	@Override
	public int getCount() {
		return jobVacancy.size();
	}

	@Override
	public Object getItem(int position) {
		return jobVacancy.get(position);
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
            convertView = mInflater.inflate(R.layout.row_job_vacancy, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(jobVacancy.get(position).
						getLocationId())).first();
         
		final UserSite userSite = Select.from(UserSite.class).
				where(Condition.prop("UserName").eq(jobVacancy.get(position).
						getUser_Name())).first();
		
        TextView txtTitle = (TextView) convertView.findViewById(R.id.job_title);
        TextView txtCategory = (TextView) convertView.findViewById(R.id.job_category);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.job_location);
        TextView txtEducationLevel = (TextView) convertView.findViewById(R.id.education_level);
        TextView txtQualification = (TextView) convertView.findViewById(R.id.qualification);
        TextView txtResponsibility = (TextView) convertView.findViewById(R.id.responsibility);
        TextView txtExperience = (TextView) convertView.findViewById(R.id.experience);
        TextView txtSalary = (TextView) convertView.findViewById(R.id.salary);
        TextView txtAddress = (TextView) convertView.findViewById(R.id.address);
        TextView txtWorkPlace= (TextView) convertView.findViewById(R.id.work_place);
        TextView txtDeadLine = (TextView) convertView.findViewById(R.id.dead_line);
        TextView txtJobDuration = (TextView) convertView.findViewById(R.id.job_duration);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
                 
        txtTitle.setText(jobVacancy.get(position).getJob_Title());
        txtCategory.setText(jobVacancy.get(position).getCategory());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtEducationLevel.setText(jobVacancy.get(position).getEducation_Level());
        txtQualification.setText(jobVacancy.get(position).getQualification());
        txtResponsibility.setText(jobVacancy.get(position).getResponsibility());
        txtExperience.setText(jobVacancy.get(position).getExperiance());
        txtSalary.setText(jobVacancy.get(position).getSalary());
        txtAddress.setText(jobVacancy.get(position).getAddress());
        txtWorkPlace.setText(jobVacancy.get(position).getWork_Place());
        txtDeadLine.setText(jobVacancy.get(position).getDead_Line());
        txtJobDuration.setText(jobVacancy.get(position).getJob_Duration());
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
        return convertView;
	}

}
