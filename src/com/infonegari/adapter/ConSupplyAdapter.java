package com.infonegari.adapter;

import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.objects.db.Construction;
import com.infonegari.objects.db.ConstructionMachine;
import com.infonegari.objects.db.ConstructionMaterial;
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

public class ConSupplyAdapter extends BaseAdapter{
	private Context context;
	private List<Construction> constructions;
	
	public ConSupplyAdapter(Context context, List<Construction> constructions){
		this.context = context;
		this.constructions = constructions;
	}
	@Override
	public int getCount() {
		return constructions.size();
	}

	@Override
	public Object getItem(int position) {
		return constructions.get(position);
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
            convertView = mInflater.inflate(R.layout.row_con_supply, null);
        }
		
		Location location = null;
		long locationId = constructions.get(position).getLocationId();
		if(locationId != 0){
			location = Select.from(Location.class).
					where(Condition.prop("Location_Id").eq(locationId)).first();			
		}

		ConstructionMachine machine = null;
		long conMachineId = constructions.get(position).getConstructionMachineId();
		if(conMachineId != 0){
			machine = Select.from(ConstructionMachine.class).
					where(Condition.prop("cm_Id").eq(conMachineId)).first();			
		}
		
		ConstructionMaterial material = null;
		long conMaterialId = constructions.get(position).getConstructionMaterialId();
		if(conMaterialId != 0){
			material = Select.from(ConstructionMaterial.class).
					where(Condition.prop("cmId").eq(conMaterialId)).first();			
		}
		
		UserSite userSite = null;
		String userName = constructions.get(position).getUser_Name();
		if(userName != null){
			userSite = Select.from(UserSite.class).
					where(Condition.prop("UserName").eq(userName)).first();			
		}
		
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtLocation = (TextView) convertView.findViewById(R.id.location);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        TextView txtDiscription = (TextView) convertView.findViewById(R.id.discription);
        TextView txtProfession = (TextView) convertView.findViewById(R.id.profession);
        TextView txtMachine = (TextView) convertView.findViewById(R.id.machine);
        TextView txtMaterial = (TextView) convertView.findViewById(R.id.material);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.email);
        TextView txtPhoneNo = (TextView)convertView.findViewById(R.id.phone_no);
        
        txtName.setText(constructions.get(position).getConstructionTitle());
        if(location != null)
        	txtLocation.setText(location.getLocationName());
        txtPrice.setText(String.valueOf(constructions.get(position).getPrice()));
        txtDiscription.setText(constructions.get(position).getDiscription());
        txtProfession.setText(constructions.get(position).getProfession());
        if(machine != null)
        	txtMachine.setText(machine.getMachine());
        if(material != null)
        	txtMaterial.setText(material.getMaterials());
        if(userSite != null){
	       	 txtPhoneNo.setText(userSite.getPhone_Number());
	       	 txtEmail.setText(userSite.getE_mail());
       }
        return convertView;
	}

}
