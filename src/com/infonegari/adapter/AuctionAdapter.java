package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Auction;
import com.infonegari.objects.db.AuctionCategory;
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
		
		AuctionCategory category = Select.from(AuctionCategory.class).
				where(Condition.prop("acId").eq(auctions.get(position).
						getAuction_Catagory())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtPhoneNo = (TextView) convertView.findViewById(R.id.phone_no);
        TextView txtPostDate = (TextView) convertView.findViewById(R.id.post_date);
        TextView txtSubmissionDeadline = (TextView) convertView.findViewById(R.id.submission_deadline);
        TextView txtOpeningDate = (TextView) convertView.findViewById(R.id.opening_date);
        TextView txtCategory = (TextView) convertView.findViewById(R.id.category);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtMinPrice = (TextView) convertView.findViewById(R.id.minimum_price);
        TextView txtSource = (TextView) convertView.findViewById(R.id.source);
        
        txtName.setText(auctions.get(position).getCompany_Name());
        txtPhoneNo.setText(auctions.get(position).getPhone_Number());
        txtPostDate.setText(auctions.get(position).getPost_Date());
        txtSubmissionDeadline.setText(auctions.get(position).getSubmission_Deadline());
        txtOpeningDate.setText(auctions.get(position).getOpening_Date());
        if(category != null)
        	txtCategory.setText(category.getCatagory_Name());
        txtDiscription.setText(auctions.get(position).getDescription());
        txtMinPrice.setText(String.valueOf(auctions.get(position).getMinimum_Price()));    
        txtSource.setText(auctions.get(position).getSource());
        
        return convertView;
	}

}
