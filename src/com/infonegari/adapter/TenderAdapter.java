package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.AuctionCategory;
import com.infonegari.objects.db.Tender;
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
		
		AuctionCategory category = Select.from(AuctionCategory.class).
				where(Condition.prop("acId").eq(tenders.get(position).
						getTender_Catagory())).first();
		
		final UserSite userSite = Select.from(UserSite.class).
				where(Condition.prop("UserName").eq(tenders.get(position).
						getUser_Name())).first();
		
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
        if(category != null)
        	txtCategory.setText(category.getCatagory_Name());
        txtDiscription.setText(tenders.get(position).getDiscription());   
        txtSource.setText(tenders.get(position).getSource());

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
