package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Auction;
import com.infonegari.objects.db.AuctionCategory;
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

public class AuctionAdapter extends BaseAdapter{
	private Context context;
	private List<Auction> auctions;
	
	public AuctionAdapter(Context context, List<Auction> auctions){
		this.context = context;
		this.auctions = auctions;
	}
	@Override
	public int getCount() {
		return auctions.size();
	}

	@Override
	public Object getItem(int position) {
		return auctions.get(position);
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
            convertView = mInflater.inflate(R.layout.row_auction, null);
        }
		
		AuctionCategory category = null;
		long acId = auctions.get(position).getAuction_Catagory();
		if(acId != 0){
			category = Select.from(AuctionCategory.class).
					where(Condition.prop("acId").eq(acId)).first();			
		}

		UserSite userSite = null;
		String userName = auctions.get(position).getUser_Name();
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
        TextView txtMinPrice = (TextView) convertView.findViewById(R.id.minimum_price);
        TextView txtSource = (TextView) convertView.findViewById(R.id.source);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(auctions.get(position).getCompany_Name());
        txtPostDate.setText(auctions.get(position).getPost_Date());
        txtSubmissionDeadline.setText(auctions.get(position).getSubmission_Deadline());
        txtOpeningDate.setText(auctions.get(position).getOpening_Date());
        if(category != null)
        	txtCategory.setText(category.getCatagory_Name());
        txtDiscription.setText(auctions.get(position).getDescription());
        txtMinPrice.setText(String.valueOf(auctions.get(position).getMinimum_Price()));    
        txtSource.setText(auctions.get(position).getSource());
        
        if(userSite != null){
        	 txtPhoneNo.setText(userSite.getPhone_Number()); 
        	 txtEmail.setText(userSite.getE_mail());
        }
        return convertView;
	}

}
