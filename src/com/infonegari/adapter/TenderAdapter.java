package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.objects.db.Tender;
import com.infonegari.objects.db.TenderCategory;
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

public class TenderAdapter extends BaseAdapter{
	private Context context;
	private List<Tender> tenders;
	
	public TenderAdapter(Context context, List<Tender> tenders){
		this.context = context;
		this.tenders = tenders;
	}
	@Override
	public int getCount() {
		return tenders.size();
	}

	@Override
	public Object getItem(int position) {
		return tenders.get(position);
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
            convertView = mInflater.inflate(R.layout.row_tender, null);
        }
		
		TenderCategory category = null;
		String catId = tenders.get(position).getTender_Catagory();
		if(!catId.equals("")){
			category = Select.from(TenderCategory.class).
					where(Condition.prop("tcId").eq(catId)).first();			
		}
		
		UserSite userSite = null;
		String userName = tenders.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtPostDate = (TextView) convertView.findViewById(R.id.post_date);
        TextView txtSubmissionDeadline = (TextView) convertView.findViewById(R.id.submission_deadline);
        TextView txtOpeningDate = (TextView) convertView.findViewById(R.id.opening_date);
        TextView txtCategory = (TextView) convertView.findViewById(R.id.category);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtSource = (TextView) convertView.findViewById(R.id.source);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(tenders.get(position).getCompany_Name());
        txtPostDate.setText(tenders.get(position).getPost_Date());
        txtSubmissionDeadline.setText(tenders.get(position).getSubmission_Deadline());
        txtOpeningDate.setText(tenders.get(position).getOpening_Date());
        if(category != null){
        	if(SplashScreen.localization == 1)
        		txtCategory.setText(category.getCatagory_Name_am());
        	else
        		txtCategory.setText(category.getCatagory_Name());
        }
        txtDiscription.setText(tenders.get(position).getDiscription());   
        txtSource.setText(tenders.get(position).getSource());

       if(userSite != null){
         	 txtPhoneNo.setText(userSite.getPhone_Number());    	   
         	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
