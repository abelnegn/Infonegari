package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.BusinessListing;
import com.infonegari.objects.db.City;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.MainCategory;
import com.infonegari.objects.db.SubCategory;
import com.orm.query.Condition;
import com.orm.query.Select;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BusinessLeaseAdapter extends BaseAdapter{
	private Context context;
	private List<BusinessListing> businessListings;
	
	public BusinessLeaseAdapter(Context context, List<BusinessListing> businessListings){
		this.context = context;
		this.businessListings = businessListings;
	}
	@Override
	public int getCount() {
		return businessListings.size();
	}

	@Override
	public Object getItem(int position) {
		return businessListings.get(position);
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
            convertView = mInflater.inflate(R.layout.row_business_lease, null);
        }
		
		Location location = Select.from(Location.class).
				where(Condition.prop("Location_Id").eq(businessListings.get(position).
						getListingLocation())).first();

		City city = Select.from(City.class).
				where(Condition.prop("City_Id").eq(businessListings.get(position).
						getListingCity())).first();
		
		MainCategory mCategory = Select.from(MainCategory.class).
				where(Condition.prop("Category_Id").eq(businessListings.get(position).
						getListingMainCategory())).first();
		
		SubCategory sCategory = Select.from(SubCategory.class).
				where(Condition.prop("Sub_Category_Id").eq(businessListings.get(position).
						getListingSubCategory())).first();
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtAddress = (TextView) convertView.findViewById(R.id.address);
        TextView txtCity = (TextView) convertView.findViewById(R.id.city);
        TextView txtPhone = (TextView) convertView.findViewById(R.id.phone_no);
        TextView txtEmail = (TextView) convertView.findViewById(R.id.email);
        TextView txtMCategory = (TextView) convertView.findViewById(R.id.main_category);
        TextView txtSCategory = (TextView) convertView.findViewById(R.id.sub_category);
        
        txtName.setText(businessListings.get(position).getListingName());
        txtDiscription.setText(businessListings.get(position).getListingDiscription());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtAddress.setText(businessListings.get(position).getFullAddress());
        if(city != null)
        	txtCity.setText(city.getCityName());
        txtPhone.setText(businessListings.get(position).getListingPhone());
        txtEmail.setText(businessListings.get(position).getListingEmail());
        if(mCategory != null)
        	txtMCategory.setText(mCategory.getCategoryName());
        if(sCategory != null)
        	txtSCategory.setText(sCategory.getSubCategoryName());
        
        return convertView;
	}

}
