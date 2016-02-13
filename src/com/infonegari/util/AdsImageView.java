package com.infonegari.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.infonegari.objects.db.Ads;
import com.orm.query.Condition;
import com.orm.query.Select;

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

public class AdsImageView {

	private ImageSwitcher imageSwitcher;
	Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
	private Context context;
	int imageSize = 0;
    int currentIndex=-1; 
    public static ArrayList<Drawable> adsAuctionImages;
    public static ArrayList<Drawable> adsBandImages;
    public static ArrayList<Drawable> adsBankImages;
    public static ArrayList<Drawable> adsBeautySaloonImages;
    public static ArrayList<Drawable> adsBusinessLeaseImages;
    public static ArrayList<Drawable> adsBusinessSaleImages;
    public static ArrayList<Drawable> adsCarRentImages;
    public static ArrayList<Drawable> adsCarSellImages;
    public static ArrayList<Drawable> adsCatererImages;
    public static ArrayList<Drawable> adsCinemaImages;
    public static ArrayList<Drawable> adsClinicImages;
    public static ArrayList<Drawable> adsConsupplyImages;
    public static ArrayList<Drawable> adsDecoratorImages;
    public static ArrayList<Drawable> adsDJImages;
    public static ArrayList<Drawable> adsEventImages;
    public static ArrayList<Drawable> adsGarageImages;
    public static ArrayList<Drawable> adsGuestHouseImages;
    public static ArrayList<Drawable> adsHandyManImages;
    public static ArrayList<Drawable> adsHmdtaImages;
    public static ArrayList<Drawable> adsHouseRentImages;
    public static ArrayList<Drawable> adsHouseSellImages;
    public static ArrayList<Drawable> adsJobVacancyImages;
    public static ArrayList<Drawable> adsNightClubImages;
    public static ArrayList<Drawable> adsPharmacyImages;
    public static ArrayList<Drawable> adsPhotoVideoImages;
    public static ArrayList<Drawable> adsResortImages;
    public static ArrayList<Drawable> adsRestaurantImages;
    public static ArrayList<Drawable> adsShopClothImages;
    public static ArrayList<Drawable> adsShopComputerImages;
    public static ArrayList<Drawable> adsShopElectronicImages;
    public static ArrayList<Drawable> adsShopFurnitureImages;
    public static ArrayList<Drawable> adsTaxiImages;
    public static ArrayList<Drawable> adsTenderImages;
    public static ArrayList<Drawable> adsTravelAgentImages;
    public static ArrayList<Drawable> adsUsedItemImages;
    public static ArrayList<Drawable> adsWeddingCarImages;
    public static ArrayList<Drawable> adsWeddingClothImages;
    public static ArrayList<Drawable> adsWeddingCRPImages;
    public static ArrayList<Drawable> adsWeddingHallImages;
    
    
	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}

	public AdsImageView(Context context){
		this.context = context;
	}
	
	public ImageSwitcher getImageSwitcher() {
		return imageSwitcher;
	}

	public void setImageSwitcher(ImageSwitcher imageSwitcher) {
		this.imageSwitcher = imageSwitcher;
	}

	public AdsImageView(Context context, ImageSwitcher imageSwitcher){
		this.context = context;
		this.imageSwitcher = imageSwitcher;
	}
    
    public void startTimer(ArrayList<Drawable> adsImages) {
    	imageSize = adsImages.size();
		if(imageSize > 0){			
	        timer = new Timer();
	        initializeTimerTask(adsImages);
	        timer.schedule(timerTask, 5000, 10000);
    	}
    }
 
    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
	
    public void getAuctionImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("auct")).list();  		
    		adsAuctionImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsAuctionImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getBandImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("band")).list();  		
    		adsBandImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsBandImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getBankImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("banks")).list();  		
    		adsBankImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsBankImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getBeautySaloonImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedbs")).list();  		
    		adsBeautySaloonImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsBeautySaloonImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getBusinessLeaseImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("rbusiness")).list();  		
    		adsBusinessLeaseImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsBusinessLeaseImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getBusinessSaleImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("sbusiness")).list();  		
    		adsBusinessSaleImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsBusinessSaleImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void getCarRentImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("rcar")).list();  		
    		adsCarRentImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsCarRentImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getCarSellImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("scar")).list();  		
    		adsCarSellImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsCarSellImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getCatererImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("catpast")).list();  		
    		adsCatererImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsCatererImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getCinemaImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("cinema")).list();  		
    		adsCinemaImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsCinemaImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getClinicImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("clinic")).list();  		
    		adsClinicImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsClinicImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getConSupplyImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("const")).list();  		
    		adsConsupplyImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsConsupplyImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getDecoratorImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("decor")).list();  		
    		adsDecoratorImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsDecoratorImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getDJImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("weddj")).list();  		
    		adsDJImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsDJImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getEventImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("event")).list();  		
    		adsEventImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsEventImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getGarageImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("garage")).list();  		
    		adsGarageImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsGarageImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getGuestHouseImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("rguesthouse")).list();  		
    		adsGuestHouseImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsGuestHouseImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getHmdtaImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedhd")).list();  		
    		adsHmdtaImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsHmdtaImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void getHandyManImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("const")).list();  		
    		adsHandyManImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsHandyManImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getHouseRentImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("rhouse")).list();  		
    		adsHouseRentImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsHouseRentImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getHouseSellImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("shouse")).list();  		
    		adsHouseSellImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsHouseSellImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getJobVacancyImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("jobs")).list();  		
    		adsJobVacancyImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsJobVacancyImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getNightClubImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("night")).list();  		
    		adsNightClubImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsNightClubImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getPharmacyImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("pharma")).list();  		
    		adsPharmacyImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsPharmacyImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getPhotoVideoImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedpv")).list();  		
    		adsPhotoVideoImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsPhotoVideoImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getResortImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("resort")).list();  		
    		adsResortImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsResortImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getRestaurantImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("restaurant")).list();  		
    		adsRestaurantImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsRestaurantImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getShopClothImages(){
    	try {
    		List<Ads> adsList = Ads.findWithQuery(Ads.class, 
        			"SELECT * FROM  Ads WHERE Category in ('shop_fc', 'shop_mc', 'shop_kc', 'shop_cd')"); 		
    		adsShopClothImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsShopClothImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getShopComputerImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("shop_ca")).list();  		
    		adsShopComputerImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsShopComputerImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getShopElectronicImages(){
    	try {
    		List<Ads> adsList = Ads.findWithQuery(Ads.class, 
        			"SELECT * FROM  Ads WHERE Category in ('shop_tv', 'shop_rf', 'shop_mo')"); 				
    		adsShopElectronicImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsShopElectronicImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getShopFurnitureImages(){
    	try {
    		List<Ads> adsList = Ads.findWithQuery(Ads.class, 
        			"SELECT * FROM  Ads WHERE Category in ('shop_fh', 'shop_fo')"); 		 		
    		adsShopFurnitureImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsShopFurnitureImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getTaxiImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("taxi")).list();  		
    		adsTaxiImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsTaxiImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getTenderImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("tend")).list();  		
    		adsTenderImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsTenderImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getTravelAgentImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("travel")).list();  		
    		adsTravelAgentImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsTravelAgentImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getUsedItemImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("sitem")).list();  		
    		adsUsedItemImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsUsedItemImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getWeddingCarImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedcar")).list();  		
    		adsWeddingCarImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsWeddingCarImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getWeddingClothImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedgowntux")).list();  		
    		adsWeddingClothImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsWeddingClothImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getWeddingCRPImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedcard")).list();  		
    		adsWeddingCRPImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsWeddingCRPImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getWeddingHallImages(){
    	try {
    		List<Ads> adsList = Select.from(Ads.class).where(Condition.
    				prop("Category").eq("wedhall")).list();  		
    		adsWeddingHallImages = new ArrayList<Drawable>();
        	if(adsList.size() > 0){
	       		for(Ads ads : adsList) {
	       			if(!ads.getImage_mob().equals("0")){
			       		String[] imageName = ads.getImage_mob().split("/");
		       			String imagePath = context.getFilesDir() + "/" + imageName[2];
		       			File imageFile = new File(imagePath);
			      		if (imageFile.exists()) {
			      			Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			      	    	Drawable adsDrawable = new BitmapDrawable(myBitmap);
			      	    	adsWeddingHallImages.add(adsDrawable);
			      		}	       				
	       			}	       			
	       		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void initializeTimerTask(final ArrayList<Drawable> adsImages) {
        imageSwitcher.setFactory(new ViewFactory() {           
            public View makeView() {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
                return imageView;
            }
        });
        
        Animation in = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(context,android.R.anim.slide_out_right);
       
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);        
        timerTask = new TimerTask() {
            public void run() {
                 
                handler.post(new Runnable() {
                    public void run() {
                        currentIndex++;
                        if(currentIndex==imageSize)
                            currentIndex=0;
                        	imageSwitcher.setImageDrawable(adsImages.get(currentIndex));
                    }
                });
            }
        };
    }
}
