package com.infonegari.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;


public class NewsImageView {
	private ImageSwitcher imageSwitcher;
	Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
	private Context context;
	int imageSize = 0;
    int currentIndex=-1; 
    private ArrayList<Drawable> newsImages = new ArrayList<Drawable>();
    
	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
    
	public NewsImageView(Context context, ImageSwitcher imageSwitcher){
		this.context = context;
		this.imageSwitcher = imageSwitcher;
		getImages();
		imageSize = newsImages.size();
	}
	
    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 5000, 10000); //
    }
 
    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
	
    private void getImages(){
    	try {
			FileInputStream fileInputStream = context.openFileInput("newsImageNames.txt");
        	BufferedReader reader;
        	if(fileInputStream != null){
        		reader = new BufferedReader(new InputStreamReader(fileInputStream));
	       		String line;
	       		while ((line = reader.readLine()) != null) {
	       			String imagePath = context.getFilesDir() + "/" + line;
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
    
    public void initializeTimerTask() {
        imageSwitcher.setFactory(new ViewFactory() {           
            public View makeView() {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
                return imageView;
            }
        });
        
        // Declare the animations and initialize them
        Animation in = AnimationUtils.loadAnimation(context,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(context,android.R.anim.fade_out);
       
        // set the animation type to imageSwitcher
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);        
        timerTask = new TimerTask() {
            public void run() {
                 
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        currentIndex++;
                        if(currentIndex==imageSize)
                            currentIndex=0;
                        	imageSwitcher.setImageDrawable(newsImages.get(currentIndex));
                    }
                });
            }
        };
    }
}
