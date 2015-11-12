package com.infonegari.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;

import com.infonegari.util.AdsImageView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashScreen extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
            	getLocalization();
            	AdsImageView adsView = new AdsImageView(SplashScreen.this);
            	adsView.getAuctionImages();
            	adsView.getBandImages();
            	adsView.getBankImages();
            	adsView.getBeautySaloonImages();
            	adsView.getBusinessLeaseImages();
            	adsView.getBusinessSaleImages();
            	adsView.getCarRentImages();
            	adsView.getCarSellImages();
            	adsView.getCatererImages();
            	adsView.getCinemaImages();
            	adsView.getClinicImages();
            	adsView.getConSupplyImages();
            	adsView.getDecoratorImages();
            	adsView.getDJImages();
            	adsView.getEventImages();
            	adsView.getGarageImages();
            	adsView.getGuestHouseImages();
            	adsView.getHmdtaImages();
            	adsView.getHouseRentImages();
            	adsView.getHouseSellImages();
            	adsView.getJobVacancyImages();
            	adsView.getNightClubImages();
            	adsView.getPharmacyImages();
            	adsView.getPhotoVideoImages();
            	adsView.getResortImages();
            	adsView.getRestaurantImages();
            	adsView.getShopClothImages();
            	adsView.getShopComputerImages();
            	adsView.getShopElectronicImages();
            	adsView.getShopFurnitureImages();
            	adsView.getTaxiImages();
            	adsView.getTenderImages();
            	adsView.getTravelAgentImages();
            	adsView.getUsedItemImages();
            	adsView.getWeddingCarImages();
            	adsView.getWeddingClothImages();
            	adsView.getWeddingCRPImages();
            	adsView.getWeddingHallImages();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
        
    private void createFile(){
    	 FileInputStream fos = null;
    	    try {
    	       fos = openFileInput("settingFile.txt");
    	       if (fos != null) {
    	       }
    	    } catch (FileNotFoundException e) {
    			FileOutputStream fileOutputStream = null;
    			File file = null;
    			String setting = "Amharic,False";
    			try {
    				file = getFilesDir();
    				fileOutputStream = openFileOutput("settingFile.txt", Context.MODE_PRIVATE);
    				fileOutputStream.write(setting.getBytes());
    	            PrintWriter pw = new PrintWriter(fileOutputStream);
    	            pw.flush();
    	            pw.close();
    				fileOutputStream.close();
    			} catch (IOException f) { 
    				e.printStackTrace();
    			}
    	    }
    }
    private void getLocalization(){
    	try {
    		createFile();
			FileInputStream fileInputStream = openFileInput("settingFile.txt");
        	BufferedReader reader;
        	if(fileInputStream != null){
        		reader = new BufferedReader(new InputStreamReader(fileInputStream));
	       		String line;
	       		while ((line = reader.readLine()) != null) {
	       			String[] locValue = line.split(",");
	       			Configuration config = new Configuration();
	       			if(locValue[1].equals("True")){	       				
					    if(locValue[0].equals("Amharic")){
					    	config.locale = Locale.CANADA;					    	
					    }else if(locValue[0].equals("English")){
					    	config.locale = Locale.ENGLISH;
					    }
					}else{
						config.locale = Locale.ENGLISH;
					}
				    getResources().updateConfiguration(config, null);
	    			Intent intent = new Intent(SplashScreen.this, MainActivity.class);
	    			startActivity(intent);
	       		}
        	}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
}