package com.infonegari.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.CinemaAdapter;
import com.infonegari.objects.db.Cinema;
import com.infonegari.objects.db.CinemaPlace;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Condition;
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

public class CinemaFragment extends Fragment{
	View rootView;
	List<CinemaPlace> cinemaHallList;
	HashMap<String, Long> cinemaHallHashMap = new HashMap<String, Long>();
	List<Cinema> cinemaList;
	private ListView mCinemaList;
	private CinemaAdapter adapter;
	private Spinner sp_location, sp_hall;
	private Button btnSearch;
	private EditText txtTitle, txtShowTime, txtShowDate;
	SafeUIBlockingUtility safeUIBlockingUtility;
	private static final int MENU_ITEM_BACK = 2000;
	
	public CinemaFragment(){
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
			LeisureFragment fragment = new LeisureFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_cinema, container, false);
		
		getActivity().setTitle("Cinema Schedule");
		
		mCinemaList = (ListView)rootView.findViewById(R.id.list_cinema);
		sp_hall = (Spinner)rootView.findViewById(R.id.cinema_hall);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		txtShowTime = (EditText)rootView.findViewById(R.id.show_time);
//		txtShowDate = (EditText)rootView.findViewById(R.id.show_date);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);	
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		saveCinema();
		fetchHall();
		
		init();
		
		return rootView;
	}
	
	private void fetchHall(){
		List<String> listOfHall = new ArrayList<String>();
		cinemaHallList = Select.from(CinemaPlace.class).list();

		listOfHall.add("Select Cinema Hall");
		cinemaHallHashMap.put("Select Cinema Hall", 0L);
		for (CinemaPlace hall : cinemaHallList) {
			listOfHall.add(hall.getCinema_Name());
			cinemaHallHashMap.put(hall.getCinema_Name(), hall.getCp_id());
        }
        ArrayAdapter<String> hallAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfHall);

        hallAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_hall.setAdapter(hallAdapter);
        sp_hall.setSelection(0);
	}

	private void saveCinema(){
		Cinema newCinema = new Cinema();
		newCinema.setCinemaId(1);
		newCinema.setCinemaPlaceId(2);
		newCinema.setCinemaTitle("Rebuni film");
		newCinema.setDiscription("Rebuni film");
		newCinema.setLocationId(2);
		newCinema.setMovie_Type(2);
		newCinema.setPrice(434);
		newCinema.setShowTime("10:30");
		newCinema.setShowDate("24-10-2015");
		
		newCinema.save();
	}
	
	private void init(){
		cinemaList = Select.from(Cinema.class).list();
		adapter = new CinemaAdapter(getActivity(), cinemaList);
		mCinemaList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		long locId = cinemaHallHashMap.get(sp_location.getSelectedItem().toString());
		cinemaList = Select.from(Cinema.class).where(Condition.prop("CnPIdName").
				eq(txtTitle.getText().toString())).and(Condition.
						prop("Location_Id").eq(locId)).list();
		adapter = new CinemaAdapter(getActivity(), cinemaList);
		mCinemaList.setAdapter(adapter);		
	}

}