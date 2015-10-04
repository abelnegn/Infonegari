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
import com.infonegari.activity.R.id;
import com.infonegari.activity.R.layout;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
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
    private int messageCountNews=0;
    private int notifyCount = 0;
    
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
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
