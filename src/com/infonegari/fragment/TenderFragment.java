package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.activity.SplashScreen;
import com.infonegari.adapter.TenderAdapter;
import com.infonegari.objects.db.Location;
import com.infonegari.objects.db.Tender;
import com.infonegari.objects.db.TenderCategory;
import com.infonegari.util.AdsImageView;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Select;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
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
	private Spinner sp_category;
	private Button btnSearch;
	private EditText txtTitle;
	private ImageSwitcher imageSwitcher;
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
        	hideKeyboard();
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
		
		getActivity().setTitle(getString(R.string.menu_tender));
		
		mTenderList = (ListView)rootView.findViewById(R.id.list_tender);
		sp_category = (Spinner)rootView.findViewById(R.id.category);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
        AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer(AdsImageView.adsTenderImages);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		fetchCategory();
		
		init();
		
		return rootView;
	}

	private void fetchCategory(){
		List<String> listOfCategories = new ArrayList<String>();
		categoryList = Select.from(TenderCategory.class).orderBy("CatagoryName ASC").list();

		listOfCategories.add(getString(R.string.sp_all_category));
		categoryHashMap.put(getString(R.string.sp_all_category), 0L);
		if(SplashScreen.localization == 1){
			for (TenderCategory cat : categoryList) {
				listOfCategories.add(cat.getCatagory_Name_am());
				categoryHashMap.put(cat.getCatagory_Name_am(), cat.getTc_id());
	        }			
		}else{
			for (TenderCategory cat : categoryList) {
				listOfCategories.add(cat.getCatagory_Name());
				categoryHashMap.put(cat.getCatagory_Name(), cat.getTc_id());
	        }
		}
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfCategories);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_category.setAdapter(categoryAdapter);
        sp_category.setSelection(0);
	}
	
	private void init(){
		tenderList = Select.from(Tender.class).orderBy("id Desc").list();
		adapter = new TenderAdapter(getActivity(), tenderList);
		mTenderList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		hideKeyboard();
		String catId = String.valueOf(categoryHashMap.get(sp_category.getSelectedItem().toString()));
		if(catId.equals("0")){
			catId = "TenderCatagory";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "CompanyName";
		}else{
			title = "'%" + title + "%'";
		}
		
		tenderList = Tender.findWithQuery(Tender.class, 
    			"SELECT * FROM  Tender WHERE TenderCatagory = " + catId + 
    			" AND CompanyName LIKE " +	title + " ORDER BY id Desc");
		
		adapter = new TenderAdapter(getActivity(), tenderList);
		mTenderList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();		
	}

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
