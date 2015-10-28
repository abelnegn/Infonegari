package com.infonegari.fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.infonegari.activity.R;
import com.infonegari.adapter.CinemaAdapter;
import com.infonegari.objects.db.Cinema;
import com.infonegari.objects.db.CinemaPlace;
import com.infonegari.objects.db.MovieType;
import com.infonegari.util.AdsImageView;
import com.infonegari.util.SafeUIBlockingUtility;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.orm.query.Select;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ListView;
import android.widget.Spinner;

public class CinemaFragment extends Fragment{
	View rootView;
	List<CinemaPlace> cinemaHallList;
	List<MovieType> movieTypeList;
	HashMap<String, Long> cinemaHallHashMap = new HashMap<String, Long>();
	HashMap<String, Long> movieTypeHashMap = new HashMap<String, Long>();
	List<Cinema> cinemaList;
	List<Cinema> cinemaAllList;
	private ListView mCinemaList;
	private CinemaAdapter adapter;
	private Spinner sp_movie_type, sp_hall;
	private Button btnSearch;
	private EditText txtTitle, txtShowDate;
	private ImageSwitcher imageSwitcher;
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
		
		getActivity().setTitle(getString(R.string.menu_cinema));
		
		mCinemaList = (ListView)rootView.findViewById(R.id.list_cinema);
		sp_hall = (Spinner)rootView.findViewById(R.id.cinema_hall);
		sp_movie_type = (Spinner)rootView.findViewById(R.id.movie_type);
		txtTitle = (EditText)rootView.findViewById(R.id.title);
		txtShowDate = (EditText)rootView.findViewById(R.id.show_date);
		btnSearch = (Button)rootView.findViewById(R.id.search_button);	
		imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.item_imageSwitcher);
		safeUIBlockingUtility = new SafeUIBlockingUtility(getActivity(), 
				"Progress", "Please Wait...");
		safeUIBlockingUtility.safelyBlockUI();
		
		AdsImageView imageView = new AdsImageView(getActivity(), imageSwitcher);
		imageView.startTimer();
		btnSearch.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				btnSearch();
			}
		});
		
		txtShowDate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
			    DatePickerFragment dpf = new DatePickerFragment().newInstance();
                dpf.setCallBack(onDate);
                dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
			}
		});
		
//		saveCinema();
		
		fetchGener();
		fetchHall();
		
		init();
		
		return rootView;
	}
	
	DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            txtShowDate.setText(String.valueOf(monthOfYear + 1) + "/" + String.valueOf(dayOfMonth)
                    + "/" + String.valueOf(year));
        }
    };

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

	private void fetchGener(){
		List<String> listOfMovieType = new ArrayList<String>();
		movieTypeList = Select.from(MovieType.class).list();

		listOfMovieType.add("Select Movie Type");
		movieTypeHashMap.put("Select Movie Type", 0L);
		for (MovieType movieType : movieTypeList) {
			listOfMovieType.add(movieType.getMovie_Type());
			movieTypeHashMap.put(movieType.getMovie_Type(), movieType.getMtId());
        }
        ArrayAdapter<String> movieTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listOfMovieType);

        movieTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_movie_type.setAdapter(movieTypeAdapter);
        sp_movie_type.setSelection(0);
	}
	
	private void saveCinema(){
		Cinema newCinema = new Cinema();
		newCinema.setCinemaId(1);
		newCinema.setCinemaTitle("Rebuni film");
		newCinema.setDiscription("Rebuni film");
		newCinema.setCalendar("*/@,1,12:45,10/26/2015, 10/28/2015,*/@,3,11:30,10/22/2015,");
		newCinema.setLocationId(2);
		newCinema.setMovie_Type(2);
		newCinema.setUser_Name("developer");
		
		newCinema.save();
	}
	
	private Cinema getCinema(Cinema cinema){
		Cinema newCinema = new Cinema();
		newCinema.setCinemaTitle(cinema.getCinemaTitle());
		newCinema.setDiscription(cinema.getDiscription());
		newCinema.setHallId(cinema.getHallId());
		newCinema.setLocationId(cinema.getLocationId());
		newCinema.setMovie_Type(cinema.getMovie_Type());
		newCinema.setShowDate(cinema.getShowDate());
		newCinema.setShowTime(cinema.getShowTime());
		newCinema.setUser_Name(cinema.getUser_Name());
		
		return newCinema;
	}
	
	private void init(){
		cinemaList = Select.from(Cinema.class).orderBy("id Desc").list(); 
		cinemaAllList = new ArrayList<Cinema>();
		for(Cinema cinema : cinemaList){
			String[] hallSeparated = cinema.getCalendar().split("@");
			for(int i=1; i< hallSeparated.length; i++){
				String[] schedule = hallSeparated[i].split(",");
				String showDate = "";
		        for(int j =3; j< schedule.length; j++){
		        	if((schedule.length - j)==1){
		        		if (schedule[j].charAt(schedule[j].length()-2)=='*'){
		        			schedule[j] = schedule[j].replace(schedule[j].substring(schedule[j].length()-2), "");
		        	    } 
		        	}
		        	showDate += schedule[j];
		        }
				cinema.setHallId(schedule[1]);
				cinema.setShowTime(schedule[2]);
				cinema.setShowDate(showDate);	
				
				cinemaAllList.add(getCinema(cinema));
			}			
		}

		adapter = new CinemaAdapter(getActivity(), cinemaAllList);
		mCinemaList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	}
	
	private void btnSearch(){
		safeUIBlockingUtility.safelyBlockUI();
		String calHall = String.valueOf(cinemaHallHashMap.get(sp_hall.getSelectedItem().toString()));

		String typeId = String.valueOf(movieTypeHashMap.get(sp_movie_type.getSelectedItem().toString()));
		if(typeId.equals("0")){
			typeId = "MovieType";
		}

		String calShowDate = txtShowDate.getText().toString();
		if(calShowDate.equals("")){
			calShowDate = "Calendar";
		}else{
			calShowDate = "'%" + calShowDate + "%'";
		}
		
		String title = txtTitle.getText().toString();
		if(title.equals("")){
			title = "Cinema_Title";
		}else{
			title = "'%" + title + "%'";
		}
		
		cinemaList = Cinema.findWithQuery(Cinema.class, 
    			"SELECT * FROM  Cinema WHERE Cinema_Title LIKE " + title + 
    			" AND Calendar LIKE " + calShowDate + " AND MovieType = " + typeId + " ORDER BY id Desc");

		cinemaAllList = new ArrayList<Cinema>();
		for(Cinema cinema : cinemaList){
			String[] hallSeparated = cinema.getCalendar().split("@");
			for(int i=1; i< hallSeparated.length; i++){
				String[] schedule = hallSeparated[i].split(",");
				if(!calHall.equals("0") && calHall.equals(schedule[1])){
					String showDate = "";
			        for(int j =3; j< schedule.length; j++){
			        	if((schedule.length - j)==1){
			        		if (schedule[j].charAt(schedule[j].length()-2)=='*'){
			        			schedule[j] = schedule[j].replace(schedule[j].substring(schedule[j].length()-2), "");
			        	    } 
			        	}		        	
			        	showDate += schedule[j];
			        }
					cinema.setHallId(schedule[1]);
					cinema.setShowTime(schedule[2]);
					cinema.setShowDate(showDate);	
					
					cinemaAllList.add(getCinema(cinema));					
				}else if(calHall.equals("0")){
					
				}
			}			
		}

		adapter = new CinemaAdapter(getActivity(), cinemaAllList);
		mCinemaList.setAdapter(adapter);
		safeUIBlockingUtility.safelyUnBlockUI();
	
	}
}
