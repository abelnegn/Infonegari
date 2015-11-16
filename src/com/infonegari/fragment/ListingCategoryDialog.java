package com.infonegari.fragment;

import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.AddListFragment;
import com.infonegari.activity.R;
import com.infonegari.objects.db.AllCategory;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ListingCategoryDialog extends DialogFragment{
	private View rootView;
	private boolean isModal = false;
	private Spinner sp_category;
	private Button btnSelect, btnBack;
	List<AllCategory> categoryList;
	HashMap<String, Long> categoryHashMap = new HashMap<String, Long>();
	
    public static ListingCategoryDialog newInstance()
    {
    	ListingCategoryDialog frag = new ListingCategoryDialog();
        frag.isModal = true;
        return frag;
    }
    
	public ListingCategoryDialog(){
		
	}
	
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    					Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_listing_dialog, container, false);
       sp_category = (Spinner)rootView.findViewById(R.id.category);
       btnSelect = (Button)rootView.findViewById(R.id.btn_select);
       btnBack = (Button)rootView.findViewById(R.id.btn_back);

       getDialog().setTitle(getString(R.string.dlg_listing_category));
 
       btnSelect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				FragmentManager fragmentManager = getFragmentManager();
				AddListFragment fragment = new AddListFragment();
				Bundle arguments = new Bundle();
				arguments.putString("Add_Category", sp_category.getSelectedItem().toString());
				arguments.putString("User_Name", getArguments().getString("User_Name"));
				fragment.setArguments(arguments);
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
				getDialog().dismiss();
			}
		});
       
       btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getDialog().dismiss();
				FragmentManager fragmentManager = getFragmentManager();
				HomeFragment fragment = new HomeFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
			}
		});
       
       fetchCategory();
       
       return rootView;
   }
   
   private void fetchCategory(){
		String[] listOfCategories = getResources().getStringArray(R.array.nav_drawer_add_list);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCategories);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        sp_category.setSelection(0);
   }
}
