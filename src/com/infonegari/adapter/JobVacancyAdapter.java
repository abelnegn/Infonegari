package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.EducationCategory;
import com.infonegari.objects.db.JobCategory;
import com.infonegari.objects.db.Jobs;
import com.infonegari.objects.db.Location;
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
		
		JobCategory category = null;
		String catId = jobVacancy.get(position).getCategory();
		if(!catId.equals("")){
			category = Select.from(JobCategory.class).
					where(Condition.prop("jc_Id").eq(catId)).first();			
		}

		EducationCategory eduLevel = null;
		String levelId = jobVacancy.get(position).getEducation_Level();
		if(!levelId.equals("")){
			eduLevel = Select.from(EducationCategory.class).
					where(Condition.prop("ec_Id").eq(catId)).first();			
		}
		
		Location location = null;
		long locationId = jobVacancy.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		UserSite userSite = null;
		String userName = jobVacancy.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
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
        if(category != null){
        	if(SplashScreen.localization == 1)
        		txtCategory.setText(category.getCategory_Name_am());
        	else
        		txtCategory.setText(category.getCategory_Name());
        }       
        if(location != null){
        	if(SplashScreen.localization == 1)
        		txtLocation.setText(location.getLocationName_am());
        	else
        		txtLocation.setText(location.getLocationName());
        }
        if(eduLevel != null){
        	if(SplashScreen.localization == 1)
        		txtEducationLevel.setText(eduLevel.getEducation_Level_am());
        	else
        		txtEducationLevel.setText(eduLevel.getEducation_Level());
        }
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
	       	 txtEmail.setText(userSite.getE_mail());
       }        
        return convertView;
	}

}
