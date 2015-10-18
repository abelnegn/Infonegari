package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.TenderAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.Tender;
import com.infonegari.objects.db.TenderCategory;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Select;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class TenderFragment extends Fragment{
	View rootView;
	List<Location> locationList;
	List<TenderCategory> categoryList;
	HashMap<String, Long> categoryHashMap = new HashMap<String, Long>();
	List<Tender> tenderList;
	private ListView mTenderList;
	private TenderAdapter adapter;
	private Spinner sp_location, sp_category;
	private Button btnSearch;
	private EditText txtTitle;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public TenderFragment(){
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItem mItemSearchClient = menu.add(Menu.NONE, MENU_ITEM_BACK, Menu.NONE, "Back");
        mItemSearchClient.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_backward)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mItemSearchClient.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == MENU_ITEM_BACK) {
			FragmentManager fragmentManager = getFragmentManager();
			BusinessFragment fragment = new BusinessFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_tender, container, false);
		
		getActivity().setTitle("Tender");
		
		mTenderList = (ListView)rootView.findViewById(R.id.list_tender);
		sp_category = (Spinner)rootView.findViewById(R.id.category);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		saveTender();
		
		fetchCategory();
		
		init();
		
		return rootView;
	}

	private void fetchCategory(){
		List<String> listOfCategories = new ArrayList<String>();
		categoryList = Select.from(TenderCategory.class).list();

		listOfCategories.add("Select Category");
		categoryHashMap.put("Select Category", 0L);
		for (TenderCategory cat : categoryList) {
			listOfCategories.add(cat.getCatagory_Name());
			categoryHashMap.put(cat.getCatagory_Name(), cat.getTc_id());
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCategories);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        sp_category.setSelection(0);
	}
	
	private void saveTender(){
		Tender newT = new Tender();
		newT.setTender_Catagory("1");
		newT.setTender_id(1);
		newT.setCompany_Name("Ethiopian Road authority");
		newT.setDiscription("Ethiopian road authority");
		newT.setOpening_Date("21-02-2015");
		newT.setPhone_Number("0912343234");
		newT.setPost_Date("01-02-2015");
		newT.setSource("Addis Zemen");
		newT.setSubmission_Deadline("21-04-2015");
		
		newT.save();
	}
	
	private void init(){
		tenderList = Select.from(Tender.class).list();
		adapter = new TenderAdapter(getActivity(), tenderList);
		mTenderList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
//		long locId = locationHashMap.get(sp_location.getSelectedItem().toString());
//		tenderList = Select.from(Tender.class).where(Condition.prop("CnPIdName").
//				eq(txtTitle.getText().toString())).and(Condition.
//						prop("Location_Id").eq(locId)).list();
//		adapter = new TenderAdapter(getActivity(), tenderList);
//		mTenderList.setAdapter(adapter);		
	}

}
