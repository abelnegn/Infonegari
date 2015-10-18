package com.infonegari.fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import com.infonegari.activity.R;
import com.infonegari.activity.ShortMessage;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

public class HomeFragment extends Fragment{
	final Handler handler = new Handler();
	final Handler handlerNews = new Handler();
	private ImageSwitcher imageSwitcher, imageSwitcherNews;
	private TextView notification;
    private TextView txtShortNumber;
    private Timer timer, timerNews;
    private TimerTask timerTask, timerTaskNews;
    private ArrayList<Drawable> adsImages = new ArrayList<Drawable>();
    private ArrayList<Drawable> newsImages = new ArrayList<Drawable>();
    private int currentIndex = -1;
    private int adsMessageCount=0;
    private int newsMessageCount=0;
    private int currentIndexNews = -1;
    private static final int MENU_ITEM_ABOUT = 2000;
    private static final int MENU_ITEM_HELP = 3000;
    private static final int MENU_ITEM_LANGUAGE = 4000;
    
	public HomeFragment(){}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
		getActivity().setTitle("Menu");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        imageSwitcher = (ImageSwitcher) rootView.findViewById(R.id.adi_imageSwitcher);
        imageSwitcherNews = (ImageSwitcher) rootView.findViewById(R.id.info_imageSwitcher);
        notification = (TextView) rootView.findViewById(R.id.notification_text);
        txtShortNumber = (TextView)rootView.findViewById(R.id.short_number_txt);
        txtShortNumber.setMovementMethod(LinkMovementMethod.getInstance());
        txtShortNumber.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent smsIntent = new Intent(getActivity(), ShortMessage.class);
				startActivity(smsIntent);
				
			}
		});
		getAdsImages();
		adsMessageCount = adsImages.size();
		
		getNewsImages();
		newsMessageCount = newsImages.size();
		if(adsMessageCount != 0)
			startTimerAds();
		if(newsMessageCount != 0)
			startTimerNews();
		
        imageSwitcherNews.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(newsMessageCount != 0){
	            	imageSwitcherNews.setImageDrawable(newsImages.get(currentIndexNews));
	            	currentIndexNews = (currentIndexNews + 1) % newsImages.size();
            	}
            }
        });
        
		displayNotification();
		
        return rootView;
    }
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItem mLanguge = menu.add(Menu.NONE, MENU_ITEM_LANGUAGE, Menu.NONE, "Language");
        mLanguge.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_language)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	mLanguge.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        
        //Help Menu
        MenuItem mHelp = menu.add(Menu.NONE, MENU_ITEM_HELP, Menu.NONE, "Help");
        mHelp.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_info)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	mHelp.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        
        //About
        MenuItem mAbout = menu.add(Menu.NONE, MENU_ITEM_ABOUT, Menu.NONE, "About");
        mAbout.setTitle("About");
        mAbout.setIcon(new IconDrawable(getActivity(), Iconify.IconValue.fa_adn)
        .colorRes(R.color.black)
        .actionBarSize());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	mAbout.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == MENU_ITEM_LANGUAGE) {
			FragmentManager fragmentManager = getFragmentManager();
			LanguageFragment fragment = new LanguageFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }else if(id == MENU_ITEM_HELP){
			FragmentManager fragmentManager = getFragmentManager();
			HelpFragment fragment = new HelpFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();       	
        }else{
			FragmentManager fragmentManager = getFragmentManager();
			AboutFragment fragment = new AboutFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
        }
        return super.onOptionsItemSelected(item);
    }
    
	private void displayNotification(){
    	try {
			FileInputStream fileInputStream = getActivity().openFileInput("notification.txt");
        	BufferedReader reader;
        	if(fileInputStream != null){
        		String notimsg = "";
        		reader = new BufferedReader(new InputStreamReader(fileInputStream));
	       		String line;
	       		while ((line = reader.readLine()) != null) {
	       			notimsg += line + "\n";	       			
	       		}
	       		notification.setText(notimsg);
        	}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void imageClick(View view){
    	imageSwitcherNews.setFactory(new ViewFactory(){           
	        public View makeView() {
	            ImageView imageView = new ImageView(getActivity());
	            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	            return imageView;
	        }
	    });
	    
	    // Declare the animations and initialize them
	    Animation in = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in);
	    Animation out = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out);
	   
	    imageSwitcher.setOnTouchListener(new OnTouchListener() {

	    	@Override
            public boolean onTouch(View v, MotionEvent event) {
                imageSwitcher.showNext();

                return false;
            }

        });		
	}
	
    private void getAdsImages(){
    	try {
			FileInputStream fileInputStream = getActivity().openFileInput("adsImageNames.txt");
        	BufferedReader reader;
        	if(fileInputStream != null){
        		reader = new BufferedReader(new InputStreamReader(fileInputStream));
	       		String line;
	       		while ((line = reader.readLine()) != null) {
	       			String imagePath = getActivity().getFilesDir() + "/" + line;
	       			File imageFile = new File(imagePath);
		      		if (imageFile.exists()) {
		      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		      	    	Drawable newsDrawable = new BitmapDrawable(myBitmap);
		      	    	adsImages.add(newsDrawable);
		      		}	       			
	       		}
        	}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    private void getNewsImages(){
    	try {
			FileInputStream fileInputStream = getActivity().openFileInput("newsImageNames.txt");
        	BufferedReader reader;
        	if(fileInputStream != null){
        		reader = new BufferedReader(new InputStreamReader(fileInputStream));
	       		String line;
	       		while ((line = reader.readLine()) != null) {
	       			String imagePath = getActivity().getFilesDir() + "/" + line;
	       			File imageFile = new File(imagePath);
		      		if (imageFile.exists()) {
		      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		      	    	Drawable newsDrawable = new BitmapDrawable(myBitmap);
		      	    	newsImages.add(newsDrawable);
		      		}	       			
	       		}
        	}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void startTimerAds() {
        timer = new Timer();
        initializeTimerTaskAdi();
        timer.schedule(timerTask, 5000, 10000); 
    }
    
    public void startTimerNews() {
        timerNews = new Timer();
        initializeTimerTaskNews();
        timerNews.schedule(timerTaskNews, 5000, 10000); 
    }
 
    public void initializeTimerTaskAdi() {
	    imageSwitcher.setFactory(new ViewFactory(){           
	        public View makeView() {
	            ImageView imageView = new ImageView(getActivity());
	            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	            return imageView;
	        }
	    });
	    
	    // Declare the animations and initialize them
	    Animation in = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_in_left);
	    Animation out = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_out_right);
	   
	    // set the animation type to imageSwitcher
	    imageSwitcher.setInAnimation(in);
	    imageSwitcher.setOutAnimation(out);        
	    timerTask = new TimerTask() {
	        public void run() {
	             
	            //use a handler to run a toast that shows the current timestamp
	            handler.post(new Runnable() {
	                public void run() {
                        currentIndex++;
                        if(currentIndex==adsMessageCount)
                            currentIndex=0;
                        	imageSwitcher.setImageDrawable(adsImages.get(currentIndex));
	                }
	            });
	        }
	    };
    }
    
    public void initializeTimerTaskNews() {
    	imageSwitcherNews.setFactory(new ViewFactory(){           
	        public View makeView() {
	            ImageView imageView = new ImageView(getActivity());
	            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	            return imageView;
	        }
	    });
	    
	    // Declare the animations and initialize them
	    Animation in = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in);
	    Animation out = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out);
	   
	    // set the animation type to imageSwitcher
	    imageSwitcherNews.setInAnimation(in);
	    imageSwitcherNews.setOutAnimation(out);        
	    timerTaskNews = new TimerTask() {
	        public void run() {
	             
	            //use a handler to run a toast that shows the current timestamp
	        	handlerNews.post(new Runnable() {
	                public void run() {
	                	currentIndexNews++;
                        if(currentIndexNews==newsMessageCount)
                        	currentIndexNews=0;
                        	imageSwitcherNews.setImageDrawable(newsImages.get(currentIndexNews));
	                }
	            });
	        }
	    };
    }
}
